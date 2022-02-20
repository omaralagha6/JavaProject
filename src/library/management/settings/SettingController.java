package library.management.settings;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SettingController implements Initializable {

	@FXML
	private AnchorPane rootPane;
	@FXML
	private JFXTextField nDaysWithoutFine;

	@FXML
	private JFXTextField finePerDay;

	@FXML
	private JFXTextField username;

	@FXML
	private JFXPasswordField password;

	@FXML
	private JFXButton saveButton;

	@FXML
	private JFXButton cancelButton;

	@FXML
	void handleCancelButtonAction(ActionEvent event) {
		Stage stage = (Stage) rootPane.getScene().getWindow();
		stage.close();
	}

	@FXML
	void handleSaveButtonAction(ActionEvent event) {
		int ndays = Integer.parseInt(nDaysWithoutFine.getText());
		float fine = Float.parseFloat(finePerDay.getText());
		String uname = username.getText();
		String pass = password.getText();

		Preference preference = Preference.getPreference();
		preference.setnDaysWithoutFine(ndays);
		preference.setFinePerDay(fine);
		preference.setUsername(uname);
		preference.setPassword(pass);
		Preference.writePreference(preference);
		Stage stage = (Stage) rootPane.getScene().getWindow();
		stage.close();

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initDefaultValues();

	}

	private void initDefaultValues() {
		// TODO Auto-generated method stub
		Preference preference = Preference.getPreference();
		nDaysWithoutFine.setText(String.valueOf(preference.getnDaysWithoutFine()));
		finePerDay.setText(String.valueOf(preference.getFinePerDay()));
		username.setText(String.valueOf(preference.getUsername()));
		password.setText(String.valueOf(preference.getPassword()));

	}

}
