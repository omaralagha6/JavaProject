package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.events.JFXDialogEvent;
import com.jfoenix.effects.JFXDepthManager;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.*;

public class MainController implements Initializable {

	@FXML
	private JFXTextField searchBookID, searchMemberID, bookID;
	@FXML
	private Text bookTitleTXT, authorTXT, memberNameTXT, contactTXT, statusTXT;
	@FXML
	private Text memName, memEmail, memNumber, bookName, bookAuthor, bookPublisher, issueDate, numDays, fine;
	@FXML
	private JFXButton addMemberBtn, addBookBtn, viewMembersBtn, viewBooksBtn, settingsBtn, renewBtn, submissionBtn, issueBtn;
	@FXML
	private HBox bookInfo, memberInfo, submissionContainer;
	@FXML
	private ListView<BookTable> books;
	@FXML
	private StackPane mainPane, bookInfoContainer, memberInfoContainer;
	@FXML
	private AnchorPane rootAnchor;
	@FXML
	private MenuItem logoutMenuItem, closeMenuItem, membersMenuItem, booksMenuItem, employeesMenuItem;
	@FXML
	private JFXDrawer drawer;
	@FXML
	private JFXHamburger hamburger;
	 @FXML
	private Tab bookIssueTab;
	
	private PieChart bookChart, memberChart;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		JFXDepthManager.setDepth(bookInfo, 1);
		JFXDepthManager.setDepth(memberInfo, 1);
		
		initDrawer();
		initGraph();
	}
	
	private void initGraph() {
		bookChart = new PieChart(getBookStats());
		memberChart = new PieChart(getMemberStats());
		bookInfoContainer.getChildren().add(bookChart);
		memberInfoContainer.getChildren().add(memberChart);
		
		bookIssueTab.setOnSelectionChanged(new EventHandler<Event>() {
			@Override
			public void handle (Event event) {
				if (bookIssueTab.isSelected()) {
					refreshGraphs();
				}
			}
		});
	}

	private void initDrawer() {
		try {
			VBox toolbar = FXMLLoader.load(getClass().getResource("/View/Toolbar.fxml"));
			drawer.setSidePane(toolbar);
		} catch (IOException e) {
			e.printStackTrace();
		}
		HamburgerSlideCloseTransition task = new HamburgerSlideCloseTransition(hamburger);
		task.setRate(-1);
		hamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event event) -> {
			
			task.setRate(task.getRate() * -1);
			task.play();
			if (drawer.isClosed())
			{
				drawer.open();
			}
			else
			{
				drawer.close();
			}
		});
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
		disEnGraphs(false);
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
	private void loadBookInfo (ActionEvent Event) {
		// This is for Renew tab
		clearLabels();
		//if book is found
		disEnButtons(true);
		submissionContainer.setOpacity(1);
		// if book isn't found
		BoxBlur blur = new BoxBlur(3, 3, 3);
		
		JFXDialogLayout dialogLayout = new JFXDialogLayout();
		JFXButton button = new JFXButton("Okay");
		button.getStyleClass().add("dialog-button");
		
		JFXDialog dialog = new JFXDialog(mainPane, dialogLayout, JFXDialog.DialogTransition.TOP);
		button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
			dialog.close();
		});
		dialogLayout.setHeading(new Label("This book doesn't exist in the records"));
		dialogLayout.setActions(button);
		dialog.show();
		
		dialog.setOnDialogClosed((JFXDialogEvent event) -> {
			rootAnchor.setEffect(null);
		});
		
		rootAnchor.setEffect(blur);
	}
	
	private void clearLabels() {
		memName.setText("");
		memEmail.setText("");
		memNumber.setText("");
		
		bookName.setText("");
		bookAuthor.setText("");
		bookPublisher.setText("");
		
		issueDate.setText("");
		numDays.setText("");
		fine.setText("");
		
		disEnButtons(false);
		
		submissionContainer.setOpacity(0);
	}
	
	private void disEnButtons(Boolean flag) {
		// If true => buttons are enabled
		
		renewBtn.setDisable(!flag);
		submissionBtn.setDisable(!flag);
	}
	
	private void disEnGraphs(Boolean flag) {
		// If true => graphs are enabled
		
		bookChart.setOpacity(Double.parseDouble(flag.toString()));
		memberChart.setOpacity(Double.parseDouble(flag.toString()));
	}
	
	public ObservableList<PieChart.Data> getBookStats() {
		ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
		int books = 0;
		//get number of books from DB
		data.add(new PieChart.Data("Total Books (" + books + ")", books));
		//get number of issued books from DB
		data.add(new PieChart.Data("Issued Books (" + books + ")", books));
		
		return data;
	}
	
	public ObservableList<PieChart.Data> getMemberStats() {
		ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
		int members = 0;
		//get number of members from DB
		data.add(new PieChart.Data("Total Members (" + members + ")", members));
		//get number of members with books from DB
		data.add(new PieChart.Data("Members With Books (" + members + ")", members));
		
		return data;
	}
	
	private void refreshGraphs() {
		bookChart.setData(getBookStats());
		memberChart.setData(getMemberStats());
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
