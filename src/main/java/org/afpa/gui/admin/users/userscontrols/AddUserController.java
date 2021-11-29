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
import org.afpa.utils.Tools;

public class AddUserController {

    @FXML
    private ComboBox<String> listeRoles;
    @FXML
    private TextField nameField;
    @FXML
    private TextField firstnameField;
    @FXML
    private TextField mailField;
    @FXML
    private Button add;
    private final ObservableList<String> listeDesRoles = FXCollections.observableArrayList();
    private String nom;
    private String prenom;
    private String mail;
    private String role;

    public void initialize() {
        listeDesRoles.addAll(Constants.listRoles());
        listeRoles.setPromptText("Choisissez un rôle pour cet utilisateur.");
        listeRoles.setItems(listeDesRoles);
        add.setOnAction(event -> {
            User user = new User()
                    .setNom(this.nameField.getText())
                    .setPrenom(this.firstnameField.getText())
                    .setEmail(this.mailField.getText())
                    .setRoles(this.listeRoles.getSelectionModel().getSelectedItem());
            boolean result = UserDAO.fetchAddUser(user);
            if (result) {
                this.nameField.clear();
                this.firstnameField.clear();
                this.mailField.clear();
                this.listeRoles.getSelectionModel().clearSelection();
            } else {
                Tools.alertTool("warning", "ERRCODE: EAU002", "Un problème à eu lieu lors de l'ajout de l'utilisateur.");
            }
        });

    }


}
