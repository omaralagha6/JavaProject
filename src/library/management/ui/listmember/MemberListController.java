package library.management.ui.listmember;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class MemberListController implements Initializable {
	@FXML
	private TableView tableView;
	@FXML
	private TableColumn nameColl;
	@FXML
	private TableColumn idColl;
	@FXML
	private TableColumn mobileColl;
	@FXML
	private TableColumn emailColl;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	@FXML
	void handleMemberDelete(ActionEvent event) {

	}

	@FXML
	void handleRefresh(ActionEvent event) {

	}

	@FXML
	void handleMemberEdit(ActionEvent event) {

	}

}
