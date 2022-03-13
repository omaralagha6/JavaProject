package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import Model.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;

public class BookListController implements Initializable {
	@FXML
	private TableView<Book> tableView;
	@FXML
	private TableColumn<Book, String> titleColumn, idColumn, authorColumn, publisherColumn;
	@FXML
	private TableColumn<Book, Boolean> availableColumn;
	private ObservableList<Book> books = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
		idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
		authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
		publisherColumn.setCellValueFactory(new PropertyValueFactory<>("publisher"));
		availableColumn.setCellValueFactory(new PropertyValueFactory<>("available"));
		books.add(new Book("Harry Poter", "HP101", "MK J.", "2000-02-04", true));
		books.add(new Book("Harry Poter 2", "HP102", "MK J.", "2001-02-04", true));
		books.add(new Book("Harry Poter 3", "HP103", "MK J.", "2002-02-04", true));
		books.add(new Book("Harry Poter 4", "HP104", "MK J.", "2003-02-04", true));
		books.add(new Book("Harry Poter 5", "HP105", "MK J.", "2004-02-04", true));
		books.add(new Book("Harry Poter 6", "HP106", "MK J.", "2005-02-04", true));
		books.add(new Book("Harry Poter 7", "HP107", "MK J.", "2006-02-04", true));
		books.add(new Book("Harry Poter 8", "HP108", "MK J.", "2007-02-04", true));
		books.add(new Book("Harry Poter 9", "HP109", "MK J.", "2008-02-04", true));
		books.add(new Book("Harry Poter 10", "HP1010", "MK J.", "2009-02-04", true));
		books.add(new Book("Harry Poter 11", "HP1011", "MK J.", "2010-02-04", true));
		books.add(new Book("Harry Poter 12", "HP1012", "MK J.", "2011-02-04", true));
		books.add(new Book("Harry Poter 13", "HP1013", "MK J.", "2012-02-04", true));
		books.add(new Book("Harry Poter 14", "HP1014", "MK J.", "2013-02-04", true));
		tableView.getItems().setAll(books);
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
