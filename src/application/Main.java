package application;
	
import java.lang.reflect.Type;
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
import com.lukaspradel.steamapi.core.exception.SteamApiException;
import com.lukaspradel.steamapi.data.json.appnews.GetNewsForApp;
import com.lukaspradel.steamapi.data.json.ownedgames.Game;
import com.lukaspradel.steamapi.data.json.ownedgames.GetOwnedGames;
import com.lukaspradel.steamapi.webapi.client.SteamWebApiClient;
import com.lukaspradel.steamapi.webapi.request.GetNewsForAppRequest;
import com.lukaspradel.steamapi.webapi.request.GetOwnedGamesRequest;
import com.lukaspradel.steamapi.webapi.request.builders.SteamWebApiRequestFactory;

import application.models.App;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {

		HttpClient httpClient = HttpClient.newHttpClient();
		List<App> apps = new ArrayList<App>();
		App app = new App();
		Gson gson = new Gson();
		int i = 0;

//		HttpRequest getRequest = HttpRequest.newBuilder().uri(new URI(Constants.BASE_API + Constants.STORE_INTERFACE + Constants.GET_APPS
//				+ "/?key=" + Constants.STEAM_API_KEY + "&steamids=" + Constants.STEAM_ID + "&max_results=50000")).GET().build();
//
//		HttpResponse<String> getResponse = httpClient.send(getRequest, BodyHandlers.ofString());
//		
//		Type listType = new TypeToken<ArrayList<App>>() {
//		}.getType();
//
//		apps = gson.fromJson(JsonParser.parseString(getResponse.body()).getAsJsonObject().get("response").getAsJsonObject()
//				.getAsJsonArray("apps"), listType);
//		
//		for (App application : apps) {
////			i += 1;
//			if (app.getName().equalsIgnoreCase("Total War: WARHAMMER III")) {
//				System.out.println(app);
//			}
//		}
//		
//		System.out.println("i = " + i);
		

		HttpRequest getRequest = HttpRequest.newBuilder().uri(new URI(Constants.BASE_STORE + Constants.APP_INTERFACE + 
				Constants.GET_APP + "/?appids=1142710")).GET().build();

		HttpResponse<String> getResponse = httpClient.send(getRequest, BodyHandlers.ofString());
		
//		System.out.println(Constants.BASE_STORE + Constants.APP_INTERFACE + Constants.GET_APP + "/?appids=1142710");
//		System.out.println(getResponse.body());
		
		app = gson.fromJson(JsonParser.parseString(getResponse.body()).getAsJsonObject().get("1142710")
				.getAsJsonObject().get("data"),App.class);
		
		app.setAppid(JsonParser.parseString(getResponse.body()).getAsJsonObject().get("1142710")
				.getAsJsonObject().get("data").getAsJsonObject().get("steam_appid").getAsString());
		
		app.setDiscount_percent(JsonParser.parseString(getResponse.body()).getAsJsonObject().get("1142710")
				.getAsJsonObject().get("data").getAsJsonObject().get("price_overview")
				.getAsJsonObject().get("discount_percent").getAsString());
		
		app.setInitial(JsonParser.parseString(getResponse.body()).getAsJsonObject().get("1142710")
				.getAsJsonObject().get("data").getAsJsonObject().get("price_overview")
				.getAsJsonObject().get("initial").getAsString());
		
		System.out.println(app);
		
	    
//        SteamWebApiClient client = new SteamWebApiClient.SteamWebApiClientBuilder("7635F5ECB3026E835F6270DFF55EAED7").build();
////        GetNewsForAppRequest request = SteamWebApiRequestFactory.createGetNewsForAppRequest(570); // appId of Dota 2
////        GetNewsForApp getNewsForApp = client.<GetNewsForApp> processRequest(request);
//        
////        GetOwnedGamesRequest request = SteamWebApiRequestFactory.createGetOwnedGamesRequest("76561198043065582");
//        GetOwnedGamesRequest req = new GetOwnedGamesRequest.GetOwnedGamesRequestBuilder( "76561198043065582" ).includeAppInfo(true).buildRequest();
//        GetOwnedGames getOwnedGames = client.<GetOwnedGames> processRequest(req);
//        
//        List<Game> ownedGames = getOwnedGames.getResponse().getGames();
//        for (Game game : ownedGames) {
//        	System.out.println(game.getName());
//        }
//        	
        
//		try {
//			BorderPane root = new BorderPane();
//			Scene scene = new Scene(root,400,400);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			primaryStage.setScene(scene);
//			primaryStage.show();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
