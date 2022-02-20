package resource;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import library.management.ui.main.MainController;

public class Transition {
	public  void loadWindow(String loc, String title) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource(loc));
			Stage stage = new Stage(StageStyle.DECORATED);
			stage.getIcons().add(new Image("/icons/library2.png"));
			stage.setTitle(title);
			stage.setScene(new Scene(parent));
			stage.show();

		} catch (Exception e) {
			Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, e);
		}
	}
}
