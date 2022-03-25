package Controller;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import Database.MemberDao;
import Model.AlertMaker;
import Model.Main;
import Model.Member;
import Model.MemberTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MemberListController implements Initializable {

    @FXML
    private TableView<MemberTable> tableView;
    @FXML
    private TableColumn<MemberTable, String> nameColumn, idColumn, phoneNbrColumn, emailColumn, addressColumn;
    private ObservableList<MemberTable> members = FXCollections.observableArrayList();
    private MemberDao memDAO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        memDAO = MemberDao.getMemDAO();
        fillTable();


    }

    @FXML
    void handleMemberDelete(ActionEvent event) {
        Optional<ButtonType> response = AlertMaker.showConfigurationAlert("", "Proceed ? ");
        if (response.get().equals(ButtonType.OK)) {

            AlertMaker.showInformationAlert("Delete Member", "Member deleted successfully");
            MemberTable temp = tableView.getSelectionModel().getSelectedItem();
            memDAO.delete(temp.getID());
            members.clear();
            fillTable();


        }
    }

    @FXML
    void handleRefresh(ActionEvent event) {
    }

    @FXML
    void handleBookEdit(ActionEvent event) {

    }

    void fillTable() {

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        phoneNbrColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNbr"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        List<Member> temp = memDAO.getAll();
        for (Model.Member mem : temp) {
            members.add(new MemberTable(mem.getName(), mem.getId(), mem.getPhoneNbr(), mem.getEmail(), mem.getAddress()));
        }
        tableView.getItems().setAll(members);
    }


}
