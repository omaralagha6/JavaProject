package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Database.EmployeeDao;
import org.apache.commons.codec.digest.DigestUtils;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import com.jfoenix.controls.JFXTooltip;

import Model.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginController implements Initializable {

	@FXML
	private JFXTextField username,viewPass;
	@FXML
	private JFXButton loginBtn;
	@FXML
    private JFXPasswordField hiddenPass;

	@FXML
	private JFXToggleButton isAdmin;
	@FXML
	private JFXCheckBox isVisible;

	private Preferences pref;
	public static boolean admin = false;
	public EmployeeDao empDAO ;

	@FXML
	private void loginAction(ActionEvent event) {
		String temp=!hiddenPass.getText().isEmpty()?hiddenPass.getText():viewPass.getText();
		if (username.getText().isEmpty() || temp.isEmpty())
			AlertMaker.showWarningAlert(null, "Username and password are REQUIRED to login");
		else {
			if (isAdmin.isSelected()) {
				admin = true;
				pref = Preferences.getConfigurations();
				String user = username.getText();
				String pass = DigestUtils.shaHex(temp);
				if (user.equals(pref.getUsername()) && pass.equals(pref.getPassword())) {
					try {
						Parent parent = FXMLLoader.load(getClass().getResource("/View/AdminView.fxml"));
						Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
						stage.setTitle("Admin");
						stage.setScene(new Scene(parent));
						stage.setMinHeight(440);
						stage.setMinWidth(615);
						stage.show();
					} catch (IOException e) {
						AlertMaker.showErrorAlert("Opps",
								"Something went wrong while logging in.\nPlease try again in a minute.");
						e.printStackTrace();
					}
				} else {
					//AlertMaker.showWarningAlert("Attention", "Invalid username or password");
					username.getStyleClass().add("wrong-credentials");
					hiddenPass.getStyleClass().add("wrong-credentials");
					viewPass.getStyleClass().add("wrong-credentials");
				}
			} else {
				admin = false;
				Person p =empDAO.getEmployee(username.getText(),hiddenPass.getText());
				
				
				if (p!=null) {
					try {
						Parent parent = FXMLLoader.load(getClass().getResource("/View/MainView.fxml"));
						Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
						stage.setUserData(p);
						stage.setTitle("Home");
						stage.setScene(new Scene(parent));
						stage.setMinHeight(440);
						stage.setMinWidth(615);
						stage.show();
					} catch (IOException e) {
						AlertMaker.showErrorAlert("Opps",
								"Something went wrong while logging in.\nPlease try again in a minute.");
						e.printStackTrace();
					}
				} else {
					//AlertMaker.showWarningAlert("Attention", "Invalid username or password");

					username.getStyleClass().add("wrong-credentials");
					hiddenPass.getStyleClass().add("wrong-credentials");
				}
			}
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		empDAO=EmployeeDao.getEmployeeDao();
		Platform.runLater(new Runnable() {
	        @Override
	        public void run() {
	        	username.requestFocus();
	        }
	    });
	}

	@FXML
	void showPass(ActionEvent event) {
		if(isVisible.isSelected())
		{
			String temp=hiddenPass.getText();
			viewPass.setText(temp);
			viewPass.setPrefWidth(583);
			viewPass.setPrefHeight(30);
			
			hiddenPass.setPrefHeight(0);
			hiddenPass.setPrefWidth(0);
			
			hiddenPass.setVisible(false);
			viewPass.setVisible(true);
	
		}
		else {
			String temp = viewPass.getText();
			hiddenPass.setText(temp);
			hiddenPass.setPrefWidth(583);
			hiddenPass.setPrefHeight(30);
			
			viewPass.setPrefHeight(0);
			viewPass.setPrefWidth(0);
			
			hiddenPass.setVisible(true);
			viewPass.setVisible(false);
		}
	}
}
