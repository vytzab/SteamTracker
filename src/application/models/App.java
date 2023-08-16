package application.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;

public class App {

	private String appid;

	private String name;

	private String header_image;

	private String is_free;

	private String discount_percent;

	private String initial;
	
	private String last_modified;

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getHeader_image() {
		return header_image;
	}

	public void setHeader_image(String header_image) {
		this.header_image = header_image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIs_free() {
		return is_free;
	}

	public void setIs_free(String is_free) {
		this.is_free = is_free;
	}

	public String getDiscount_percent() {
		return discount_percent;
	}

	public void setDiscount_percent(String discount_percent) {
		this.discount_percent = discount_percent;
	}

	public String getInitial() {
		return initial;
	}

	public void setInitial(String initial) {
		this.initial = initial;
	}

	public String getLast_modified() {
		return last_modified;
	}

	public void setLast_modified(String last_modified) {
		this.last_modified = last_modified;
	}
	
	@Override
	public String toString() {
		return "Application [discount_percent=" + discount_percent + "appid=" + appid + ", name=" + name +
				", header_image=" + header_image +  ", is_free=" + is_free +
				", discount_percent=" + discount_percent + ", initial=" + initial + ", last_modified=" + last_modified + "]";
	}
}