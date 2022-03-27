package Controller;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import Database.BookDao;
import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;

public class BookListController implements Initializable {
	@FXML
	private TableView<BookTable> tableView;
	@FXML
	private TableColumn<BookTable, String> titleColumn, idColumn, authorColumn, publisherColumn;
	@FXML
	private TableColumn<BookTable, Boolean> availableColumn;
	private ObservableList<BookTable> books = FXCollections.observableArrayList();
	private BookDao bookDAO=null;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		bookDAO=BookDao.getBookDao();
		fillTable();
	}

	@FXML
	void handleBookDelete(ActionEvent event) {
		 Optional<ButtonType> response = AlertMaker.showConfigurationAlert("", "Proceed ? ");
	        if (response.get().equals(ButtonType.OK)) {

	            AlertMaker.showInformationAlert("Delete Member", "Member deleted successfully");
	            BookTable temp = tableView.getSelectionModel().getSelectedItem();
	            bookDAO.delete(temp.getID());
	            books.clear();
	            fillTable();


	        }
	}

	@FXML
	void handleRefresh(ActionEvent event) {

	}

	@FXML
	void handleBookEdit(ActionEvent event) {
	}
	
	
	void fillTable()
	{
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
		idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
		authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
		publisherColumn.setCellValueFactory(new PropertyValueFactory<>("publisher"));
		availableColumn.setCellValueFactory(new PropertyValueFactory<>("available"));
		List<Model.Book> temp=bookDAO.getAll();
		for(Model.Book b:temp)
			books.add(new BookTable(b.getBookName(),b.getIsbn(),b.getBookAuthor(),b.getPublisher(),b.isAvailable()?"Yes":"No"));
		tableView.getItems().setAll(books);
	}

}
