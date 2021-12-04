package org.afpa.gui.admin.clients.clientscontrols;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.afpa.dao.UserDAO;
import org.afpa.environnemnt.Constants;
import org.afpa.model.User;

public class UpdateClientsController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField firstnameField;
    @FXML
    private TextField mailField;
    @FXML
    private Button update;

    public void initialize() {
        nameField.setText(Constants.staticUser.getNom());
        firstnameField.setText(Constants.staticUser.getPrenom());
        mailField.setText(Constants.staticUser.getEmail());
        update.setOnAction(event -> {
            User user = new User()
                    .setId(Constants.staticUser.getId())
                    .setNom(this.nameField.getText())
                    .setPrenom(this.firstnameField.getText())
                    .setEmail(this.mailField.getText())
                    .setRoles("Client");
            UserDAO dao = new UserDAO();
            boolean result = dao.fetchUpdateUser(user);
            if (result) {
                UserDAO.fetchGetUsers();
            }

        });
    }
}
