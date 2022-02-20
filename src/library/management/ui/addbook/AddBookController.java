package library.management.ui.addbook;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AddBookController implements Initializable {

	@FXML
	private AnchorPane rootPane;

	@FXML
	private JFXTextField title;

	@FXML
	private JFXTextField id;

	@FXML
	private JFXTextField author;

	@FXML
	private JFXTextField publisher;

	@FXML
	private JFXButton saveButton;

	@FXML
	private JFXButton cancelButton;

	@FXML
	void addBook(ActionEvent event) {
		Stage stage = (Stage) rootPane.getScene().getWindow();
		stage.close();
	}

	@FXML
	void cancel(ActionEvent event) {
		Stage stage = (Stage) rootPane.getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
