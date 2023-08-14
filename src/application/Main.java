package application;

import java.util.List;

import application.api.SteamAPI;
import application.helper.Helper;
import application.models.App;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		List<App> apps = Helper.getApps();

		for (App app : apps) {
			System.out.println(app);
		}

		apps = Helper.getDiscApps(apps);

		for (App app : apps) {
			System.out.println(app);
		}
		
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
