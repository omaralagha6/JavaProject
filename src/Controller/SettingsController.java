package Controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import Model.AlertMaker;
import Model.Preferences;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SettingsController implements Initializable {

	@FXML
	private TextField nbrOfDays, finePerDay, username;
	@FXML
	private PasswordField password, empPassword;
	@FXML
	private Button saveBtn, cancelBtn;
	@FXML
	private TextField empName, employeeID, phoneNbr, email;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (LoginController.admin)
			initDefaultValues();
	}

	private void initDefaultValues() {
		Preferences preferences = Preferences.getConfigurations();
		nbrOfDays.setText("" + preferences.getNbrOfDays());
		finePerDay.setText("" + preferences.getFinePerDay());
		username.setText("" + preferences.getUsername());
	}

	@FXML
	private void saveAction(ActionEvent event) {

	}

	@FXML
	private void adminSaveAction(ActionEvent event) {
		if (nbrOfDays.getText().isEmpty() || finePerDay.getText().isEmpty() || username.getText().isEmpty()
				|| password.getText().isEmpty()) {
			AlertMaker.showWarningAlert("Required", "All Field are REQUIRED!");
		} else {
			Optional<ButtonType> response = AlertMaker.showConfigurationAlert(null, "Proceed ?");
			if (response.get().equals(ButtonType.OK)) {
				Preferences pref = Preferences.getConfigurations();
				pref.setFinePerDay(Double.parseDouble(finePerDay.getText()));
				pref.setNbrOfDays(Integer.parseInt(nbrOfDays.getText()));
				pref.setPassword(password.getText());
				pref.setUsername(username.getText());
				Preferences.editPreferences(pref);
				AlertMaker.showInformationAlert(null, "All Changes has been successfully done");
				Stage stage = (Stage) saveBtn.getScene().getWindow();
				stage.close();
			}
		}
	}

	@FXML
	private void cancelAction(ActionEvent event) {
		Stage stage = (Stage) cancelBtn.getScene().getWindow();
		stage.close();
	}
}
