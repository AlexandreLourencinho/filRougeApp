package org.afpa;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.afpa.gui.LoginController;
import org.afpa.model.User;
import org.afpa.utils.Tools;

import java.io.IOException;
import java.util.Objects;

public class App extends Application {

    public static Stage stage;
    static Scene scene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(LoginController.class.getResource("login.fxml")));
            scene = new Scene(root);
            stage = new Stage();
            stage.setResizable(true);
            stage.setTitle("Connexion");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            Tools.alertTool("error", "ERRCODE: ES001", "probléme au démarrage du programme." + e);
        }
    }

    public static void changeFxml (String fxml) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("gui/" + fxml)));
            scene.setRoot(root);
            stage.show();
        } catch(Exception e) {
            Tools.alertTool("warning", "ERRCODE: ECF001", "erreur au chargement de la page." + e);
        }
    }

    public static void newFen(String fxml) {
        try {
            Stage stage1 = new Stage();
            Parent root = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("gui/" + fxml)));
            Scene scene = new Scene(root);
            stage1.setScene(scene);
            stage1.show();
        } catch (IOException e) {
            Tools.alertTool("error", "ERRCODE: ENF001", "impossible d'ouvrir la page." + e);
        }

    }
}
