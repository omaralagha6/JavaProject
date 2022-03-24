package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Database.EmployeeDao;
import Model.Employee;
import com.jfoenix.controls.JFXButton;

import Model.AlertMaker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AdminController implements Initializable {

	@FXML
	private JFXButton addEmpBtn, addBookBtn, searchBtn, settingsBtn, logoutBtn;
	@FXML
	private ListView<String> employees, currBooks;
	private static ObservableList<String> employeeList;
	private static ObservableList<String> bookList;
	private EmployeeDao empDAO;

	@FXML
	private void addingAction(ActionEvent event) {
		if (event.getSource().equals(addEmpBtn))
			loadWindow("/View/AddEmployee.fxml", "Add Employee", event);
		else
			loadWindow("/View/AddBook.fxml", "Add Book", event);
	}

	@FXML
	private void settingsAction(ActionEvent event) {
		loadWindow("/View/AdminSettingsView.fxml", "Settings", event);
	}

	@FXML
	private void searchAction(ActionEvent event) {
		loadWindow("/View/SearchView.fxml", "Search", event);
	}

	@FXML
	private void logoutAction(ActionEvent event) {
		try {
			Parent view = FXMLLoader.load(getClass().getResource("/View/LoginView.fxml"));
			Scene scene = new Scene(view);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		} catch (IOException ex) {
			AlertMaker.showErrorAlert("Opps",
					"Something went wrong while logging out.\nPlease try again in a minute.");
			ex.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		empDAO=EmployeeDao.getEmployeeDao();
		employeeList = FXCollections.observableArrayList();
		fillEmployee();



	}

	private void loadWindow(String location, String title, ActionEvent event) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource(location));
			Stage stage = new Stage(StageStyle.DECORATED);
			stage.setTitle(title);
			stage.setScene(new Scene(parent));
			stage.setMinHeight(440);
			stage.setMinWidth(615);
			stage.show();
		} catch (IOException e) {
			AlertMaker.showErrorAlert("Opps",
					"Something went wrong while loading the page...\nPlease try again in a minute.");
			e.printStackTrace();
		}
	}


	@FXML
	void handleRefresh(ActionEvent event) {
employees.getItems().removeAll(employeeList);
fillEmployee();

	}

	void fillEmployee(){

		employeeList.clear();
		List<Employee> temp=empDAO.getAll();
		for(Employee e:temp)
			employeeList.add(e.toString());

		employees.getItems().setAll(employeeList);

	}

}
