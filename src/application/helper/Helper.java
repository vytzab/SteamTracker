package application.helper;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import application.Constants;
import application.api.SteamAPI;
import application.models.App;

public class Helper {

	public static List<App> removeFreeApps () throws Exception {
		List<App> appsInit = SteamAPI.getApps();
		List<App> appsSubset = appsInit.subList(40000, 50000);
		List<App> appsAfter = new ArrayList<App>();
		int i = 0;
		for (App app : appsSubset) {
			System.out.println("Working on app " + i + " , out of " + appsSubset.size() + " apps.");
			try {
				app = SteamAPI.getApp(app.getAppid());
				appsAfter.add(app);
			} catch (Exception e) {
//				System.out.println("Exception happened while getting app: " + e);
			}
			i++;
		}
		System.out.println(appsAfter.size() + " apps remain after clearing free apps.");
		return appsAfter;
	}

//	No need to check all apps anymore.
	public static List<App> getModifiedApps () throws Exception {
		List<App> appsInit = SteamAPI.getApps();
		List<App> appsAfter = new ArrayList<App>();
		int i = 0;
		for (App app : appsInit) {
			System.out.println("Working on app " + i + " , out of " + appsInit.size() + "apps.");
			if (Helper.checkLastMod(app.getLast_modified())) {
				try {
					app = SteamAPI.getApp(app.getAppid());
					appsAfter.add(app);
				} catch (Exception e) {
//					System.out.println("Exception happened while getting app: " + e);
				}
			}
			i++;
		}
		System.out.println(appsAfter.size() + " apps whose price was modified after given date remain.");
		return appsAfter;
	}

	public static List<App> getDiscApps (List<App> appsInit) throws Exception {
		List<App> appsAfter = new ArrayList<App>();
		int i = 0;
		for (App app : appsInit) {
			System.out.println("Working on app " + i + " , out of " + appsInit.size() + " apps.");
			if (Helper.checkDiscount(app.getDiscount_percent())) {
				appsAfter.add(app);
			}
			i++;
		}
		System.out.println(appsAfter.size() + " apps remain after checking discounts.");
		return appsAfter;
	}

	public static boolean checkDiscount (String discount) throws Exception {
		if (Integer.parseInt(discount)>80) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean checkLastMod (String date) throws Exception {
		if (Integer.parseInt(date)>1688346061) {
			return true;
		} else {
			return false;
		}
	}
}
