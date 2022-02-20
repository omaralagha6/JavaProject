package library.management.ui.listbook;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class BookListController implements Initializable {
	@FXML
	private AnchorPane rootPane;
	@FXML
	private TableView tableView;
	@FXML
	private TableColumn titleColl;
	@FXML
	private TableColumn idColl;
	@FXML
	private TableColumn authorColl;
	@FXML
	private TableColumn publisherColl;
	@FXML
	private TableColumn availableColl;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	@FXML
	void handleBookDelete(ActionEvent event) {

	}

	@FXML
	void handleRefresh(ActionEvent event) {

	}

	@FXML
	void handleBookEdit(ActionEvent event) {

	}

}
