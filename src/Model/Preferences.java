package Model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.apache.commons.codec.digest.DigestUtils;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class Preferences {
	private int nbrOfDays;
	private double finePerDay;
	private String username, password;
	private static final String CONFIG_FILE = "config.txt";

	public Preferences() {
		nbrOfDays = 14;
		finePerDay = 2;
		username = "admin";
		this.setPassword("admin@1234");
	}

	public void setNbrOfDays(int nb) {
		this.nbrOfDays = nb;
	}

	public void setFinePerDay(double fine) {
		this.finePerDay = fine;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = DigestUtils.shaHex(password);
	}

	public int getNbrOfDays() {
		return this.nbrOfDays;
	}

	public double getFinePerDay() {
		return this.finePerDay;
	}

	public String getUsername() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}

	private static void initConfig() {
		Writer writer = null;
		Gson gson = new Gson();
		try {
			writer = new FileWriter(CONFIG_FILE);
			gson.toJson(new Preferences(), writer);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static Preferences getConfigurations() {
		Gson gson = new Gson();
		Preferences preferences = new Preferences();
		try {
			preferences = gson.fromJson(new FileReader(CONFIG_FILE), Preferences.class);
		} catch (FileNotFoundException e) {
			initConfig();
		} catch (JsonSyntaxException | JsonIOException e) {
			AlertMaker.showErrorAlert(null,
					"Something went wrong while retrieving the data...\nPlease try again in a minute.");
		}
		return preferences;
	}

	public static void editPreferences(Preferences pref) {
		Writer writer = null;
		Gson gson = new Gson();
		try {
			writer = new FileWriter(CONFIG_FILE);
			gson.toJson(pref, writer);
		} catch (IOException e) {
			AlertMaker.showErrorAlert(null,
					"Something went wrong while editing the data...\nPlease try again in a minute.");
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
