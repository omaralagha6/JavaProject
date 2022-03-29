package Controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import Database.BookDao;
import Database.IssueDao;
import Database.MemberDao;
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
	private Text memName, memEmail, memNumber, bookName, bookAuthor, bookPublisher, issueDate, numDays, fine,nbOfRenew;
	@FXML
	private JFXButton addMemberBtn, addBookBtn, viewMembersBtn, viewBooksBtn, settingsBtn, renewBtn, submissionBtn,
			issueBtn;
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

	@FXML
	private static PieChart bookChart;
	@FXML
	private static PieChart memberChart;
	private static MemberDao memDAO;
	private static BookDao bookDAO;
	private static IssueDao issueDAO;
	private static Model.Employee emp;
	private Model.Member mem;
	private Model.Book book;
	private Issue issue;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		memDAO = MemberDao.getMemDAO();
		bookDAO = BookDao.getBookDao();
		issueDAO = IssueDao.getIssueDao();

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
			public void handle(Event event) {
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
			if (drawer.isClosed()) {
				drawer.open();
			} else {
				drawer.close();
			}
		});
	}


	@FXML
	private void searchAction(ActionEvent event) {
		book = bookDAO.get(searchBookID.getText());
		mem = memDAO.get(searchMemberID.getText());

		disEnGraphs(book == null, mem == null);
	}


	@FXML
	private void issueBookAction(ActionEvent event) {
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		// Step 2
		emp = (Model.Employee) stage.getUserData();
		if (book == null || mem == null) {
			AlertMaker.showWarningAlert("Invalid Inputs", "A book and a member are needed t make this issue");
			return;
		}
		if (book.isAvailable() == false) {
			AlertMaker.showWarningAlert("Invalid Inputs", "This book can't be issued");
			return;
		}

		issueDAO.add(new Issue(book, emp, mem));
		book.setAvailable(false);
		bookDAO.update(book);

		searchBookID.setText("");
		searchMemberID.setText("");
		bookTitleTXT.setVisible(false);
		authorTXT.setVisible(false);
		statusTXT.setVisible(false);

		memberNameTXT.setVisible(false);
		contactTXT.setVisible(false);
		
		refreshGraphs();
		disEnGraphs(true,true);

	}

	@FXML
	private void renewSubmissionAction(ActionEvent event) {
		issue.setIssueDate(LocalDate.now());
		issue.setNbRenewal(issue.getNbRenewal()+1);
		issue.setFine();
		issueDAO.update(issue);
		
		Member mem = issue.getMember();

		memName.setText("Name: " + mem.getName());
		memEmail.setText("Email: " + mem.getEmail());
		memNumber.setText("Phone Number: " + mem.getNumber());

		bookName.setText(book.getClass().getSimpleName() + ": " + book.getBookName());
		bookAuthor.setText("Author: " + book.getBookAuthor());
		bookPublisher.setText("Publisher: " + book.getPublisher());

		issueDate.setText("Issue Date: " + issue.getIssueDate().toString());
		numDays.setText((LocalDate.now().getDayOfYear() - issue.getIssueDate().getDayOfYear()) + " days since issued");
		fine.setText("Fine: " + issue.getFine());
		nbOfRenew.setText("Renewed " + issue.getNbRenewal()+ " times");
	}

	@FXML
	private void loadBookInfo(ActionEvent Event) {
		// This is for Renew tab
		clearLabels();
		if (bookID.getText() == "") return;
		book = bookDAO.get(bookID.getText());
		issue = issueDAO.get(book.getIsbn());
		// if book isn't found
		if (issue == null) {
			BoxBlur blur = new BoxBlur(3, 3, 3);

			JFXDialogLayout dialogLayout = new JFXDialogLayout();
			JFXButton button = new JFXButton("Okay");
			button.getStyleClass().add("dialog-button");

			JFXDialog dialog = new JFXDialog(mainPane, dialogLayout, JFXDialog.DialogTransition.TOP);
			button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
				dialog.close();
			});
			dialogLayout.setHeading(new Label("This book is not issued"));
			dialogLayout.setActions(button);
			dialog.show();

			dialog.setOnDialogClosed((JFXDialogEvent event) -> {
				rootAnchor.setEffect(null);
			});

			rootAnchor.setEffect(blur);
		}
		// if book is found
		else {
			Member mem = issue.getMember();

			memName.setText("Name: " + mem.getName());
			memEmail.setText("Email: " + mem.getEmail());
			memNumber.setText("Phone Number: " + mem.getNumber());

			bookName.setText(book.getClass().getSimpleName() + ": "  + book.getBookName());
			bookAuthor.setText("Author: " + book.getBookAuthor());
			bookPublisher.setText("Publisher: " + book.getPublisher());

			issueDate.setText("Issue Date: " + issue.getIssueDate().toString());
			numDays.setText((LocalDate.now().getDayOfYear() - issue.getIssueDate().getDayOfYear()) + " days since issued");
			fine.setText("Fine: " + issue.getFine());
			nbOfRenew.setText("Renewed " + issue.getNbRenewal()+ " times");

			disEnButtons(true);
			submissionContainer.setOpacity(1);
		}

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
		nbOfRenew.setText("");

		disEnButtons(false);

		submissionContainer.setOpacity(0);
	}

	private void disEnButtons(Boolean flag) {
		// If true => buttons are enabled

		renewBtn.setDisable(!flag);
		submissionBtn.setDisable(!flag);
	}

	private void disEnGraphs(Boolean flag, Boolean flag1) {
		// If true => graphs are enabled
		double op = flag.toString().equals("false") ? 0 : 1;
		double op1 = flag1.toString().equals("false") ? 0 : 1;
		if (op == 0) {
			bookTitleTXT.setText(book.getBookName());
			authorTXT.setText(book.getBookAuthor());
			statusTXT.setText(book.isAvailable() ? "Available" : "Not Available");
			bookTitleTXT.setVisible(true);
			authorTXT.setVisible(true);
			statusTXT.setVisible(true);
		} else {
			bookTitleTXT.setVisible(false);
			authorTXT.setVisible(false);
			statusTXT.setVisible(false);
		}
		if (op1 == 0) {

			memberNameTXT.setText(mem.getName());
			contactTXT.setText(mem.getPhoneNbr());
			memberNameTXT.setVisible(true);
			contactTXT.setVisible(true);

		} else {
			memberNameTXT.setVisible(false);
			contactTXT.setVisible(false);
		}
		bookChart.setOpacity(op);
		memberChart.setOpacity(op1);
	}

	public static ObservableList<PieChart.Data> getBookStats() {
		ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
		int books = 0;
		// get number of books from DB
		books = bookDAO.getAll().size();
		data.add(new PieChart.Data("Total Books (" + books + ")", books));
		// get number of issued books from DB
		books = issueDAO.getAll().size();
		data.add(new PieChart.Data("Issued Books (" + books + ")", books));

		return data;
	}

	public static ObservableList<PieChart.Data> getMemberStats() {
		ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
		int members = 0;
		// get number of members from DB
		members = memDAO.getAll().size();
		data.add(new PieChart.Data("Total Members (" + members + ")", members));
		// get number of members with books from DB
		members = issueDAO.getAllDistinct().size();
		data.add(new PieChart.Data("Members With Books (" + members + ")", members));

		return data;
	}

	public static void refreshGraphs() {
		if (bookChart == null || memberChart == null)
			return;
		bookChart.setData(getBookStats());
		memberChart.setData(getMemberStats());
	}
	
	public static Employee getEmp() {
		return emp;
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
			stage.show();
		} catch (IOException e) {
			AlertMaker.showErrorAlert("Opps",
					"Something went wrong while loading the page...\nPlease try again in a minute.");
			e.printStackTrace();
		}
	}
}
