package Controller;

import java.net.URL;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;

import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddMemberController implements Initializable {

	@FXML
	private TextField name, memberID, phoneNbr, email, address;
	@FXML
	private Button saveBtn, cancelBtn;
	@FXML
	private VBox Adding;

	@FXML
	public void saveCancelAction(ActionEvent event) {
		if (event.getSource().equals(saveBtn)) {
			if (name.getText().isEmpty() || memberID.getText().isEmpty() || phoneNbr.getText().isEmpty()
					|| email.getText().isEmpty() || address.getText().isEmpty()) {
				AlertMaker.showWarningAlert("Invalid Inputs", "All field are REQUIRED for saving this member");
			}else if (name.getText().split(" ").length != 2) {
				AlertMaker.showWarningAlert("Invalid Name", "Name should only contain First and Last name");
			}
			else if (phoneNbr.getText().chars().allMatch(Character::isDigit)) {
				if (phoneNbr.getText().length() < 8) {
					AlertMaker.showWarningAlert("Invalid Phone Number", "The Phone Number should be at least 8 digits");
				} else {
					Optional<ButtonType> response = AlertMaker.showConfigurationAlert(null, "Proceed ?");
					if (response.get().equals(ButtonType.OK)) {
						AlertMaker.showInformationAlert("Save Member", "Member added successfully to your list");
						Member p = new Member(name.getText(), phoneNbr.getText(), memberID.getText(),
								email.getText(), address.getText());
						System.out.println(p.toString());
						Main.memDAO.add(p);
						Stage stage = (Stage) saveBtn.getScene().getWindow();
						stage.close();
					}
				}
			} else {
				AlertMaker.showWarningAlert("Invalid Phone Number", "The Phone Number should be contains only digits");
			}
		}
		if (event.getSource().equals(cancelBtn)) {
			Stage stage = (Stage) cancelBtn.getScene().getWindow();
			stage.close();
		}
	}

	@FXML
	void generateId(ActionEvent event) {
		Random rand = new Random();
		int nb = rand.nextInt(100);
		String []names=name.getText().split(" ");

		memberID.setText(""+names[0].charAt(0)+names[1].charAt(0)+ nb);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
}
