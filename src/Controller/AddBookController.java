package Controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import Database.BookDao;
import Model.*;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
	private JFXComboBox<String> bookType;

	ObservableList<String> typeList = FXCollections.observableArrayList("Manga", "Novel", "Comic");
	private String selectedType;
	private BookDao bookDAO=null;

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
					//Refresh must be after book is added to DB
					Model.Book b =BookFactory.createBook(selectedType, bookID.getText(), bookAuthor.getText(), bookTitle.getText(), publisher.getText());
					if(b==null) System.out.println("Empty");
					else {
						System.out.println(b.getClass().getSimpleName());
						bookDAO.add(b);
					}
					


					MainController.refreshGraphs();
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

	@FXML
	public void typeSelected (ActionEvent event) {
		selectedType = bookType.getValue();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		bookType.setItems(typeList);

		bookDAO=BookDao.getBookDao();
	}

}
