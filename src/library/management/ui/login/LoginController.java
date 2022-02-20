package library.management.ui.login;

import java.net.URL;
import java.util.ResourceBundle;

import org.apache.commons.codec.digest.DigestUtils;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import library.management.settings.Preference;
import resource.Transition;

public class LoginController implements Initializable {

	@FXML
	private AnchorPane rootPane;

	@FXML
	private Label titleLabel;
	@FXML
	private JFXTextField username;

	@FXML
	private JFXPasswordField password;
	Preference preference;
	private Transition tr;
	@FXML
	void handleCancelButtonAction(ActionEvent event) {
		System.exit(0);
	}

	@FXML
	void handleLoginButtonAction(ActionEvent event) {
		titleLabel.setText("Library Management Login");
		titleLabel.setStyle("-fx-background-color:black;-fx-text-fill:white");
		String uname = username.getText();
		String pass = DigestUtils.shaHex(password.getText());
		if (uname.equals(preference.getUsername()) && pass.equals(preference.getPassword())) {
			closeStage();
			tr.loadWindow("/library/management/ui/main/Main.fxml","Library Management");
		} else {
			titleLabel.setText("Invalid Credentials");
			titleLabel.setStyle("-fx-background-color:#d32f2f;-fx-text-fill:white");
		}
	}

	private void closeStage() {
		Stage stage = (Stage) rootPane.getScene().getWindow();
		stage.close();
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		preference = Preference.getPreference();
		tr=new Transition();

	}

}
