package org.afpa.gui.admin.users;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.afpa.App;
import org.afpa.environnemnt.Constants;
import org.afpa.model.User;

public class ManageUsersController {

    @FXML
    private Button addUser;
    @FXML
    private Button updateUser;
    @FXML
    private Button deleteUser;
    @FXML
    private Button backManageAdmin;
    @FXML
    private TableView<User> userTable;
    @FXML
    private TableColumn<User, String> columnName;
    @FXML
    private TableColumn<User, String> columnFirstname;
    @FXML
    private TableColumn<User, String> columnMail;
    @FXML
    private TableColumn<User, String> columnRole;
    private ObservableList<User> model = FXCollections.observableArrayList();

    public void initialize() {
        this.columnName.setCellValueFactory(new PropertyValueFactory<>("nom"));
        this.columnFirstname.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        this.columnMail.setCellValueFactory(new PropertyValueFactory<>("email"));
        this.columnRole.setCellValueFactory(new PropertyValueFactory<>("roles"));
        this.listeUtilisateurs();
        addUser.setOnAction(event -> this.addUserWindow());
        updateUser.setOnAction(event -> this.updateUserWindow());
        backManageAdmin.setOnAction(event -> {
            User.listeUtilisateurs.clear();
            App.changeFxml("admin/homeadmin.fxml");
        });
        updateUser.disableProperty().bind(Bindings.isEmpty(userTable.getSelectionModel().getSelectedItems()));

    }

    public void listeUtilisateurs() {
        this.model.clear();
        this.model.addAll(User.listeUtilisateurs);
        this.userTable.setItems(model);
    }

    public void addUserWindow() {
        App.newFen("admin/users/userscontrols/adduser.fxml");
    }

    public void updateUserWindow() {
        Constants.staticUser = userTable.getSelectionModel().getSelectedItem();
        System.out.println(Constants.staticUser);
        App.newFen("admin/users/userscontrols/updateuser.fxml");
    }


}
