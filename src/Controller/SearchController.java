package Controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import Model.AlertMaker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class SearchController implements Initializable {

	@FXML
	private TextField employeeID, bookID, memberID;
	@FXML
	private Text bookTitleTXT, authorTXT, statusTXT, empIDTXT, empNameTXT, empNbrTXT, memberIDTXT, memberNameTXT,
			memberNbrTXT;
	@FXML
	private JFXButton editBook, deleteBook, editEmployee, deleteEmployee, editMember;
	@FXML
	private VBox bookEditDelete, empEditDelete, memberEdit;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		bookEditDelete.setVisible(false);
		empEditDelete.setVisible(false);
		memberEdit.setVisible(false);
	}

	@FXML
	private void searchAction(ActionEvent event) {
		if (event.getSource().equals(bookID)) {
			if (bookID.getText().isEmpty()) {
				AlertMaker.showWarningAlert("Empty", "Please enter a Book ID");
			} else {
				clearBookCache();
				if (bookID.getText().equals("MH100")) {
					authorTXT.setText("No such book exist");
					bookEditDelete.setVisible(false);
				} else {
					bookEditDelete.setVisible(true);
					bookTitleTXT.setText("Book Title");
					authorTXT.setText("Author");
					statusTXT.setText("Status");
				}
			}
		} else if (event.getSource().equals(employeeID)) {
			if (employeeID.getText().isEmpty()) {
				AlertMaker.showWarningAlert("Empty", "Please enter an Employee ID");
			} else {
				clearEmpCache();
			}
		} else if (event.getSource().equals(memberID)) {
			if (memberID.getText().isEmpty()) {
				AlertMaker.showWarningAlert("Empty", "Please enter a Member ID");
			} else {
				clearMemberCache();
			}
		}
	}

	@FXML
	private void bookEditDeleteAction(ActionEvent event) {
		if (event.getSource().equals(deleteBook)) {
			Optional<ButtonType> response = AlertMaker.showConfigurationAlert("Confirm Delete",
					"Are you sure you want to delete this book ?");
			if (response.get().equals(ButtonType.OK)) {
				AlertMaker.showInformationAlert(null, "The book with ID : '" + this.bookID.getText()
						+ "' has been removed from your system successfully");
				bookID.clear();
			}
		}
	}

	@FXML
	private void employeeEditDeleteAction(ActionEvent event) {

	}

	@FXML
	private void memberEditAction(ActionEvent event) {

	}

	private void clearBookCache() {
		this.bookTitleTXT.setText("");
		this.authorTXT.setText("");
		this.statusTXT.setText("");
	}

	private void clearEmpCache() {
		this.empIDTXT.setText("");
		this.empNameTXT.setText("");
		this.empNbrTXT.setText("");
	}

	private void clearMemberCache() {
		this.memberIDTXT.setText("");
		this.memberNameTXT.setText("");
		this.memberNbrTXT.setText("");
	}
}
