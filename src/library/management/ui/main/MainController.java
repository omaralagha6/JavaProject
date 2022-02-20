package library.management.ui.main;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.effects.JFXDepthManager;
import com.jfoenix.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import resource.Transition;

public class MainController implements Initializable {

	@FXML
	private HBox book_info;

	@FXML
	private HBox member_info;
	@FXML
	private TextField bookIdInput;

	@FXML
	private Text bookName;

	@FXML
	private Text bookAuthor;

	@FXML
	private Text bookStatus;
	@FXML
	private TextField memberIdinput;

	@FXML
	private Text memberName;

	@FXML
	private Text memberMobile;
	@FXML
	private ListView<?> issueDataList;
	private Transition tr;
	@FXML
	private StackPane rootPane;

	@FXML
	void handleMenuClose(ActionEvent event) {
		((Stage) rootPane.getScene().getWindow()).close();
	}

	@FXML
	void handleFullScreen(ActionEvent event) {
		Stage stage = (Stage) rootPane.getScene().getWindow();
		stage.setFullScreen(!stage.isFullScreen());
	}

	@FXML
	void loadBookInfo(ActionEvent event) {

	}

	@FXML
	void loadBookInfo2(ActionEvent event) {

	}

	@FXML
	void loadMemberInfo(ActionEvent event) {

	}

	@FXML
	void loadAddBook(ActionEvent event) {
		tr.loadWindow("/library/management/ui/addbook/AddBook.fxml", "Add new Book");
	}

	@FXML
	void loadAddMember(ActionEvent event) {
		tr.loadWindow("/library/management/ui/addmember/AddMember.fxml", "Add new Member");
	}

	@FXML
	void loadBookTable(ActionEvent event) {
		tr.loadWindow("/library/management/ui/listbook/BookList.fxml", "Book List");
	}

	@FXML
	void loadMemberTable(ActionEvent event) {
		tr.loadWindow("/library/management/ui/listmember/MemberList.fxml", "Member List");
	}

	@FXML
	void loadIssueOperation(ActionEvent event) {

	}

	@FXML
	void loadSubmissionOp(ActionEvent event) {

	}

	@FXML
	void loadRenewOp(ActionEvent event) {

	}

	@FXML
	void loadSetting(ActionEvent event) {
		tr.loadWindow("/library/management/settings/Setting.fxml", "Settings");
	}

	/**
	 *
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		JFXDepthManager.setDepth(book_info, 1);
		JFXDepthManager.setDepth(member_info, 1);
		tr = new Transition();

	}

}
