package org.afpa.gui.admin.users.userscontrols;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.afpa.dao.UserDAO;
import org.afpa.environnemnt.Constants;
import org.afpa.model.User;

public class UpdateUserController {

    @FXML
    private ComboBox<String> listeRoles;
    @FXML
    private TextField nameField;
    @FXML
    private TextField firstnameField;
    @FXML
    private TextField mailField;
    @FXML
    private Button update;
    private final ObservableList<String> listeDesRoles = FXCollections.observableArrayList();

    public void initialize() {
        listeDesRoles.addAll(Constants.listRoles());
        listeRoles.setPromptText("Choisissez un rôle pour cet utilisateur.");
        listeRoles.setItems(listeDesRoles);
        nameField.setText(Constants.staticUser.getNom());
        firstnameField.setText(Constants.staticUser.getPrenom());
        mailField.setText(Constants.staticUser.getEmail());
        listeRoles.setValue(Constants.staticUser.getRoles());
        update.setOnAction(event -> {
            User user = new User()
                    .setId(Constants.staticUser.getId())
                    .setNom(this.nameField.getText())
                    .setPrenom(this.firstnameField.getText())
                    .setEmail(this.mailField.getText())
                    .setRoles(this.listeRoles.getSelectionModel().getSelectedItem());
            System.out.println(user);
            UserDAO dao = new UserDAO();
            boolean result = dao.fetchUpdateUser(user);
            if (result) {
                UserDAO.fetchGetUsers();
            }

        });
    }


}
