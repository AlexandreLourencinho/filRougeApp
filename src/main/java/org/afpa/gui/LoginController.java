package org.afpa.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.afpa.App;
import org.afpa.Launcher;
import org.afpa.dao.DaoBase;
import org.afpa.environnemnt.EnvironnementVariables;
import org.afpa.utils.Tools;
import org.json.JSONObject;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class LoginController {

    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField nameField;
    @FXML
    private Button loginButton;
    @FXML
    private TextField mailField;
    private String name;
    private String password;
    private String mail;

    public void initialize() {
        loginButton.setDisable(true);
        nameField.setOnKeyReleased(event -> {
            this.name = this.nameField.getText();
            disableLogin();
            System.out.println(this.name);
        });

        passwordField.setOnKeyReleased(event -> {
            this.password = this.passwordField.getText();
            disableLogin();
            System.out.println(this.password);
        });

        mailField.setOnKeyReleased(event -> {
            this.mail = this.mailField.getText();
            disableLogin();
            System.out.println(this.mail);
        });
        loginButton.setOnAction(event -> {
            System.out.printf("le nom de compte est %s et le mot de passe est %s", this.name, this.password);
            HashMap<String, String> values = new HashMap<>();
            values.put("nom", this.name);
            values.put("password", this.password);
            values.put("mail", this.mail);
            try {
                HttpResponse<String> response = DaoBase.buildRequest("/auth", "POSTAUTH", values);
                if (response.statusCode() == 401) {
                    Tools.alertTool("warning","Utilisateur non trouvé", "Les informations de connection sont incorrectes.");
                    return;
                }
                JSONObject json = new JSONObject(response.body());
                JSONObject jsonNested = (JSONObject) json.get("user");
                EnvironnementVariables.jwt = (String) json.get("token");
                String role = jsonNested.get("roles").toString();
                switch (role) {
                    case "admin" -> App.changeFxml("admin/homeadmin.fxml");
                    case "commercial" -> System.out.println("not implemented yet");
                    default -> Tools.alertTool("ERROR", "ERRCODE: ECF-001", "une erreur est survenue " +
                            "au moment du chargement du module. Veuillez contacter un administrateur.");
                }
            } catch (Exception e) {
                Tools.alertTool("error", "ERRCODE: ERRL-001", "Un problème est survenu " +
                        "lors de la connexion. Contactez un administrateur." + e);
            }
        });
    }

    public void disableLogin() {
        loginButton.setDisable(nameField.getText().equals("") || nameField.getText() == null
                || passwordField.getText().equals("") || passwordField.getText() == null
                || mailField.getText().equals("") || mailField.getText() == null);
    }

}
