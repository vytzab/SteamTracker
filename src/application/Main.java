package application;

import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	@Override
	public void start(Stage stage) throws Exception {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("HomeScene.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//	List<App> apps = SteamAPI.getApps();
//	for (App app : apps) {
//		System.out.println(app);
//	}
//	
//	
//
//	apps = Helper.getDiscApps(apps);
//
//	for (App app : apps) {
//		System.out.println(app);
//	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
