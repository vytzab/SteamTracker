module SteamTracker {
	requires javafx.controls;
	requires steam.web.api;
	requires jakarta.persistence;
	requires java.net.http;
	requires com.google.gson;

	opens application to javafx.graphics, javafx.fxml, com.google.gson;
	opens application.models to com.google.gson, javafx.base, org.hibernate.orm.core;
}
