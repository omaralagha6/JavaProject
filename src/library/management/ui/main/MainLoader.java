package library.management.ui.main;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import library.management.settings.Preference;

public class MainLoader extends Application {
	@Override
    public void start(Stage stage) throws ClassNotFoundException {
        try {
           
            Parent root = FXMLLoader.load(getClass().getResource("/library/management/ui/login/Login.fxml"));
            Scene scene = new Scene(root);
            stage.getIcons().add(new Image("/icons/library2.png"));
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
        	Logger.getLogger(MainLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public static void main(String[] args) {
        launch(args);
    }

	
}
