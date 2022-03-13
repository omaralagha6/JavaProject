package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.effects.JFXDepthManager;

import Model.AlertMaker;
import Model.Book;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.*;

public class MainController implements Initializable {

	@FXML
	private TextField searchBookID, searchMemberID, bookID;
	@FXML
	private Text bookTitleTXT, authorTXT, memberNameTXT, contactTXT, statusTXT;
	@FXML
	private JFXButton addMemberBtn, addBookBtn, viewMembersBtn, viewBooksBtn, settingsBtn;
	@FXML
	private Button renewBtn, submissionBtn, issueBtn;
	@FXML
	private HBox bookInfo, memberInfo;
	@FXML
	private ListView<Book> books;
	@FXML
	private BorderPane mainPane;
	@FXML
	private MenuItem logoutMenuItem, closeMenuItem, membersMenuItem, booksMenuItem, employeesMenuItem;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		JFXDepthManager.setDepth(bookInfo, 1);
		JFXDepthManager.setDepth(memberInfo, 1);
	}

	@FXML
	private void addingAction(ActionEvent event) {
		if (event.getSource().equals(addMemberBtn))
			loadWindow("/View/AddMember.fxml", "Add Member", event);
		else
			loadWindow("/View/AddBook.fxml", "Add Book", event);
	}

	@FXML
	private void viewingAction(ActionEvent event) {
		if (event.getSource().equals(viewMembersBtn))
			loadWindow("/View/MemberList.fxml", "All Members", event);
		else
			loadWindow("/View/BookList.fxml", "All Books", event);
	}

	@FXML
	private void settingsAction(ActionEvent event) {
		loadWindow("/View/SettingsView.fxml", "Settings", event);
	}

	@FXML
	private void searchAction(ActionEvent event) {
		if (event.getSource().equals(searchBookID)) {
			bookTitleTXT.setText("");
			authorTXT.setText("");
			statusTXT.setText("");
		} else if (event.getSource().equals(searchMemberID)) {
			memberNameTXT.setText("");
			contactTXT.setText("");
		}
	}

	@FXML
	private void issueBookAction(ActionEvent event) {
	}

	@FXML
	private void renewSubmissionAction(ActionEvent event) {

	}

	@FXML
	private void MenuItemsAction(ActionEvent event) {
		if (event.getSource().equals(logoutMenuItem)) {
			try {
				Parent view = FXMLLoader.load(getClass().getResource("/View/LoginView.fxml"));
				Scene scene = new Scene(view);
				Stage stage = (Stage) ((Node) mainPane).getScene().getWindow();
				stage.setScene(scene);
				stage.show();
			} catch (IOException ex) {
				AlertMaker.showErrorAlert("Opps",
						"Something went wrong while logging out.\nPlease try again in a minute.");
				ex.printStackTrace();
			}
		} else if (event.getSource().equals(closeMenuItem)) {
			Optional<ButtonType> response = AlertMaker.showConfigurationAlert(null,
					"Are you sure you want to close the system ?");
			if (response.get().equals(ButtonType.OK)) {
				System.exit(0);
			}
		} else if (event.getSource().equals(membersMenuItem)) {
			loadWindow("/View/MemberList.fxml", "Members", event);
		} else if (event.getSource().equals(booksMenuItem)) {
			loadWindow("/View/BookList.fxml", "Books", event);
		} else if (event.getSource().equals(employeesMenuItem)) {
//			loadWindow("/View/EmployeeList.fxml", "Employees", event);
		}
	}

	private void loadWindow(String location, String title, ActionEvent event) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource(location));
			Stage stage = new Stage(StageStyle.DECORATED);
			stage.setTitle(title);
			stage.setScene(new Scene(parent));
			stage.setMinHeight(440);
			stage.setMinWidth(615);
			if (event.getSource().equals(viewBooksBtn) || event.getSource().equals(viewMembersBtn)
					|| event.getSource().equals(membersMenuItem) || event.getSource().equals(booksMenuItem)
					|| event.getSource().equals(employeesMenuItem))
				stage.setMaximized(true);
			stage.show();
		} catch (IOException e) {
			AlertMaker.showErrorAlert("Opps",
					"Something went wrong while loading the page...\nPlease try again in a minute.");
			e.printStackTrace();
		}
	}
}
