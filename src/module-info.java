module SteamTracker {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires com.google.gson;
	requires lombok;
	requires org.slf4j;
	requires retrofit2;
	requires retrofit2.converter.gson;
	requires java.net.http;
	requires jakarta.persistence;
	requires org.hibernate.orm.core;
	requires org.apache.logging.log4j;

	opens application to javafx.graphics, javafx.fxml, com.google.gson;
	opens application.models to com.google.gson, javafx.base, org.hibernate.orm.core;
}
