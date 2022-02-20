package library.management.settings;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.apache.commons.codec.digest.DigestUtils;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class Preference {

	public static final String CONFIG_FILE="config.txt";
	public int getnDaysWithoutFine() {
		return nDaysWithoutFine;
	}

	public void setnDaysWithoutFine(int nDaysWithoutFine) {
		this.nDaysWithoutFine = nDaysWithoutFine;
	}

	public float getFinePerDay() {
		return finePerDay;
	}

	public void setFinePerDay(float finePerDay) {
		this.finePerDay = finePerDay;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if(password.length()<16) {
		this.password = DigestUtils.shaHex(password);
	}
		else
			{this.password=password;}
	}

	int nDaysWithoutFine;
	float finePerDay;
	String username,password;
	
	public Preference() {
		nDaysWithoutFine=14;
		finePerDay=2;
		username="admin";
		setPassword("admin");
	}
	public static void initConfig()
	{
		Writer writer=null;
		
		try {
			Preference preference = new Preference();
			Gson gson=new Gson();
			 writer=new FileWriter(CONFIG_FILE);
			 gson.toJson(preference,writer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public static Preference getPreference() {
		Gson gson=new Gson();
		Preference preference=null;
		
		try {
			preference=gson.fromJson(new FileReader(CONFIG_FILE),Preference.class);
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonIOException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			initConfig();
			e.printStackTrace();
		} 
		return preference;
	}
	public static void writePreference(Preference preference)
	{
		Writer writer=null;
		
		try {
			
			Gson gson=new Gson();
			 writer=new FileWriter(CONFIG_FILE);
			 gson.toJson(preference,writer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
}
