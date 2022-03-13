package Model;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;

public class AlertMaker {
	public static void showWarningAlert(String title, String content) {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.initModality(Modality.APPLICATION_MODAL);
		alert.setTitle(title);
		alert.setContentText(content);
		alert.showAndWait();
	}
	public static void showInformationAlert(String title, String content) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.initModality(Modality.APPLICATION_MODAL);
		alert.setTitle(title);
		alert.setContentText(content);
		alert.showAndWait();
	}
	public static void showErrorAlert(String title, String content) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.initModality(Modality.APPLICATION_MODAL);
		alert.setTitle(title);
		alert.setContentText(content);
		alert.showAndWait();
	}
	
	public static Optional<ButtonType> showConfigurationAlert(String title, String content){

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.initModality(Modality.APPLICATION_MODAL);
		alert.setTitle(title);
		alert.setContentText(content);
		return alert.showAndWait();
	}
}
