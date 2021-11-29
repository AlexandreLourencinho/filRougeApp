package org.afpa.gui.admin;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.afpa.App;
import org.afpa.dao.UserDAO;
import org.afpa.environnemnt.EnvironnementVariables;
import org.afpa.model.User;
import org.afpa.utils.Tools;

public class HomeAdminController {

    @FXML
    private Button adminGestionUtilisateurs;
    @FXML
    private Button adminGestionClients;
    @FXML
    private Button adminGestionCommandes;


    public void initialize() {

        adminGestionUtilisateurs.setOnAction(event -> {
            try {
                UserDAO.fetchGetUsers();
                if (EnvironnementVariables.UsersLists.size() != 0 ) {
                    App.changeFxml("admin/users/manageusers.fxml");
                }
            } catch (Exception e) {
                Tools.alertTool("error", "ERRCODE: EFU-001", "une erreur est survenue lors de la récupération des données."
                        + e.getMessage());
                EnvironnementVariables.UsersLists.clear();
            }
        });
    }

}
