package org.afpa.gui;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.afpa.App;
import org.afpa.model.User;
import org.afpa.utils.Tools;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
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

        nameField.setOnKeyReleased(event -> {
            this.name = this.nameField.getText();
            System.out.println(this.name);
        });

        passwordField.setOnKeyReleased(event -> {
            this.password = this.passwordField.getText();
            System.out.println(this.password);
        });

        mailField.setOnKeyReleased(event -> {
            this.mail = this.mailField.getText();
            System.out.println(this.mail);
        });

        loginButton.setOnAction(event -> {
            System.out.printf("le nom de compte est %s et le mot de passe est %s", this.name, this.password);
            HashMap<String, String> values = new HashMap<>();
            values.put("nom", this.name);
            values.put("password", this.password);
            values.put("mail", this.mail);
            try {

            ObjectMapper objMapper = new ObjectMapper();
            String bodyRequest = objMapper.writeValueAsString(values);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().version(HttpClient.Version.HTTP_1_1)
                    .uri(URI.create("http://localhost:8080/auth"))
                    .POST(HttpRequest.BodyPublishers.ofString(bodyRequest))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                JSONObject json = new JSONObject(response.body());
                JSONObject jsonNested = (JSONObject) json.get("user");
                User.jwt = (String) json.get("token");
                System.out.println(jsonNested.get("roles").toString());
                System.out.println(User.jwt);
                String role = jsonNested.get("roles").toString();
                switch (role) {
                    case "admin":
                        App.changeFxml("admin/homeadmin.fxml");
                        break;
                    case "commercial":
                        System.out.println("not implemented yet");
                    default:
                        Tools.alertTool("ERROR","ERRCODE: ECF-001", "une erreur est survenue " +
                                "au moment du chargement du module. Veuillez contacter un administrateur.");
                }
            } catch (Exception e) {
                Tools.alertTool("error","ERRCODE: ERRL-001","Un problème est survenu " +
                        "lors de la connexion. Contactez un administrateur." + e);
            }


        });

    }
}
