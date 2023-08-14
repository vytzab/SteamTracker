package application.api;

import java.lang.reflect.Type;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import application.Constants;
import application.models.App;

public class SteamAPI {

	public static App getApp(String appid) throws IOException, InterruptedException, URISyntaxException {
		App app = new App();
		app.setAppid(appid);
		Gson gson = new Gson();
		HttpClient httpClient = HttpClient.newHttpClient();

		HttpRequest getRequest = HttpRequest.newBuilder().uri(new URI(Constants.BASE_STORE + Constants.APP_INTERFACE + 
				Constants.GET_APP + "/?appids=" + appid)).GET().build();
		HttpResponse<String> getResponse = httpClient.send(getRequest, BodyHandlers.ofString());
		
		app = gson.fromJson(JsonParser.parseString(getResponse.body()).getAsJsonObject().get(appid)
				.getAsJsonObject().get("data"),App.class);
		
		app.setAppid(appid);
		
		if (!app.getIs_free().equalsIgnoreCase("true")) {
			app.setDiscount_percent(JsonParser.parseString(getResponse.body()).getAsJsonObject().get(appid)
					.getAsJsonObject().get("data").getAsJsonObject().get("price_overview")
					.getAsJsonObject().get("discount_percent").getAsString());
			
			app.setInitial(JsonParser.parseString(getResponse.body()).getAsJsonObject().get(appid)
					.getAsJsonObject().get("data").getAsJsonObject().get("price_overview")
					.getAsJsonObject().get("initial").getAsString());
		} else {
			app.setDiscount_percent("0");
			app.setInitial("0");
		}
		
		return app;
	}
	
	public static List<App> getApps () throws Exception {
		HttpClient httpClient = HttpClient.newHttpClient();
		List<App> apps = new ArrayList<App>();
		Gson gson = new Gson();

		HttpRequest getRequest = HttpRequest.newBuilder().uri(new URI(Constants.BASE_API + Constants.STORE_INTERFACE + Constants.GET_APPS
				+ "/?key=" + Constants.STEAM_API_KEY + "&steamids=" + Constants.STEAM_ID + "&max_results=50000")).GET().build();

		HttpResponse<String> getResponse = httpClient.send(getRequest, BodyHandlers.ofString());
		
		Type listType = new TypeToken<ArrayList<App>>() {
		}.getType();

		apps = gson.fromJson(JsonParser.parseString(getResponse.body()).getAsJsonObject().get("response").getAsJsonObject()
				.getAsJsonArray("apps"), listType);
				
		System.out.println(apps.size() + " games found in total.");
		return apps;
	}
}
