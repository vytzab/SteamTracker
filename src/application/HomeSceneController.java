package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import application.helper.Helper;
import application.models.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class HomeSceneController {
//	to show price
	@FXML
	private Label initPriceLabel;
//	to show discount percentage
	@FXML
	private Label discountPercentLabel;
//	to show current status of the app (e.g. "loading games/etc.")
	@FXML
	private Label statusLabel;
//	to enter what percentage discounted games to look for
	@FXML
	private TextField discountPercentTextField;
//	to start the search for games
	@FXML
	private Button checkGamesButton;
//	to show previous game in the list
	@FXML
	private Button previousGameButton;
//	to show next game in the list
	@FXML
	private Button nextGameButton;
//	to show the image of the game
	@FXML
	private ImageView gameImageView;
	
	private List<App> apps = new ArrayList<App>();
	
	private App displayedApp = new App();

//	start application
	@FXML
	private void initialize() {
		statusLabel.setText("Waiting");
		gameImageView.setImage(new Image("https://seeklogo.com/images/S/steam-logo-73274B19E3-seeklogo.com.png"));
		checkGamesButton.setOnAction(event -> loadData());
		checkGamesButton.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");
	}

	private void loadData() {
		statusLabel.setText("Loading");
//		System.out.println(apps.size());
		try {
			apps = Helper.getModifiedApps();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (apps.size() > 0) {
			statusLabel.setText("Checkpoint 2");
			try {
				apps = Helper.getDiscApps(apps);
				statusLabel.setText("Checkpoint 3");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (apps.size() > 0) {
			displayFirstApp();
		}
		statusLabel.setText("Completed");
		//
//			for (App app : apps) {
//				System.out.println(app);
//			}
//		try {
//			System.out.println("Checking for games");
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			Thread.currentThread().interrupt();
//		}


		// display previous game
		previousGameButton.setOnAction(event -> displayPrevApp());
		
		// display next game
		nextGameButton.setOnAction(event -> displayNextApp());
	}

	private void displayStatusLoading() {
		statusLabel.setText("Loading");
	}

	private void displayStatusCompleted() {
		statusLabel.setText("Completed");
	}

	private void displayStatusWaiting() {
		statusLabel.setText("Waiting");
	}
	private void displayFirstApp() {
		displayedApp = apps.get(0);
		refreshImage();
	}

	private void displayNextApp() {
		statusLabel.setText("Displaying next game");
		int currIndex = apps.indexOf(displayedApp);
		System.out.println("next app curr index = " + currIndex);
		if (currIndex < apps.size()-1) {
		displayedApp = apps.get(currIndex+1);
		} else {
			displayedApp = apps.get(0);
		}
		refreshImage();
	}

	private void displayPrevApp() {
		statusLabel.setText("Displaying previous game");
		int currIndex = apps.indexOf(displayedApp);
		System.out.println("prev app curr index = " + currIndex);
		if (currIndex == 0) {
		displayedApp = apps.get(apps.size()-1);
		} else {
			displayedApp = apps.get(currIndex-1);
		}
		refreshImage();
	}

	private void refreshImage() {
		gameImageView.setImage(new Image(displayedApp.getHeader_image()));
		initPriceLabel.setText(displayedApp.getInitial());
		discountPercentLabel.setText(displayedApp.getDiscount_percent());
	}
}
