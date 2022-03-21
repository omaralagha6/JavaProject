package Model;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.SessionFactory;

import Database.DbConnection;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	
//	public static EmployeeDao empDAO=null;
//	public static MemberDao memDAO=null;
	@Override
	public void start(Stage stage) {
		try {
//			empDAO=new EmployeeDao();
//			memDAO=new MemberDao();
			SessionFactory factory=DbConnection.getSession();
			Parent root = FXMLLoader.load(getClass().getResource("/View/LoginView.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Login");
			stage.setMinHeight(440);
			stage.setMinWidth(615);
			stage.show();
		} catch (IOException ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
