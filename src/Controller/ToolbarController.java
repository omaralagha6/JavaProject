package Controller;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import Model.AlertMaker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ToolbarController {
	@FXML
	private JFXButton addMemberBtn, addBookBtn, viewMembersBtn, viewBooksBtn, settingsBtn;
	
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
	
	private void loadWindow(String location, String title, ActionEvent event) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource(location));
			Stage stage = new Stage(StageStyle.DECORATED);
			stage.setTitle(title);
			stage.setScene(new Scene(parent));
			stage.setMinHeight(440);
			stage.setMinWidth(615);
			if (event.getSource().equals(viewBooksBtn) || event.getSource().equals(viewMembersBtn))
				stage.setMaximized(true);
			stage.show();
		} catch (IOException e) {
			AlertMaker.showErrorAlert("Opps",
					"Something went wrong while loading the page...\nPlease try again in a minute.");
			e.printStackTrace();
		}
	}
}
