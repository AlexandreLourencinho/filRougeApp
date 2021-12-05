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
import org.afpa.Launcher;
import org.afpa.dao.UserDAO;
import org.afpa.environnemnt.Constants;
import org.afpa.environnemnt.EnvironnementVariables;
import org.afpa.model.User;
import org.afpa.utils.Tools;

public class ManageUsersController {

    @FXML
    private Button disconnect;
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
    private final ObservableList<User> model = FXCollections.observableArrayList();


    public void initialize() {
        this.columnName.setCellValueFactory(new PropertyValueFactory<>("nom"));
        this.columnFirstname.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        this.columnMail.setCellValueFactory(new PropertyValueFactory<>("email"));
        this.columnRole.setCellValueFactory(new PropertyValueFactory<>("roles"));
        this.UsersList();
        addUser.setOnAction(event -> this.addUserWindow());
        updateUser.setOnAction(event -> this.updateUserWindow());
        backManageAdmin.setOnAction(event -> {
        EnvironnementVariables.clearUsers();
            App.changeFxml("admin/homeadmin.fxml");
        });
        updateUser.disableProperty().bind(Bindings.isEmpty(userTable.getSelectionModel().getSelectedItems()));
        deleteUser.disableProperty().bind(Bindings.isEmpty(userTable.getSelectionModel().getSelectedItems()));
        deleteUser.setOnAction(event -> this.deleteUserActivity());
        disconnect.setOnAction(event -> Tools.disconnectApp());
        App.stage.focusedProperty().addListener((ov, onHidden, onShown) -> {
            if (onShown) {
                EnvironnementVariables.clearUsers();
                UserDAO.fetchGetUsers();
                this.UsersList();
            }
        });
    }

    public void UsersList() {
        this.model.clear();
        this.model.addAll(EnvironnementVariables.UsersLists);
        this.userTable.setItems(model);
    }

    public void addUserWindow() {
        App.newFen("admin/users/userscontrols/adduser.fxml");
    }

    public void updateUserWindow() {
        Constants.staticUser = userTable.getSelectionModel().getSelectedItem();
        App.newFen("admin/users/userscontrols/updateuser.fxml");
    }

    public void deleteUserActivity() {
        User user = userTable.getSelectionModel().getSelectedItem();
        boolean dao = new UserDAO().fetchDeleteUser(user);
        if (dao) {
            EnvironnementVariables.clearUsers();
            UserDAO.fetchGetUsers();
            this.UsersList();
        }
    }


}
