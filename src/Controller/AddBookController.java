package Controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import Model.AlertMaker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddBookController implements Initializable {

	@FXML
	private TextField bookTitle, bookID, bookAuthor, publisher;
	@FXML
	private Button saveBtn, cancelBtn;
	@FXML
	private VBox Adding;

	@FXML
	public void saveCancelAction(ActionEvent event) {
		if (event.getSource().equals(saveBtn)) {
			if (bookTitle.getText().isEmpty() || bookID.getText().isEmpty() || bookAuthor.getText().isEmpty()
					|| publisher.getText().isEmpty()) {
				AlertMaker.showWarningAlert("Invalid Inputs", "All field are REQUIRED for saving this book");
			} else {
				Optional<ButtonType> response = AlertMaker.showConfigurationAlert(null, "Proceed ?");
				if (response.get().equals(ButtonType.OK)) {
					AlertMaker.showInformationAlert("Save Book", "Book added successfully to your list");
					Stage stage = (Stage) saveBtn.getScene().getWindow();
					stage.close();
				}
			}
		}
		if (event.getSource().equals(cancelBtn)) {
			Stage stage = (Stage) cancelBtn.getScene().getWindow();
			stage.close();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}

}
