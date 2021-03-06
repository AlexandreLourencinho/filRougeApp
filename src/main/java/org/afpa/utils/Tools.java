package org.afpa.utils;

import javafx.scene.control.Alert;
import org.afpa.App;
import org.afpa.Launcher;
import org.afpa.environnemnt.Constants;
import org.afpa.environnemnt.EnvironnementVariables;

public class Tools {

    public Tools() {
    }


    public static void alertTool(String type, String title, String contentText) {
        Alert alert = switch (type) {
            case "error" -> new Alert(Alert.AlertType.ERROR);
            case "warning" -> new Alert(Alert.AlertType.WARNING);
            case "info" -> new Alert(Alert.AlertType.INFORMATION);
            case "confirm" -> new Alert(Alert.AlertType.CONFIRMATION);
            case "none" -> new Alert(Alert.AlertType.NONE);
            default -> new Alert(Alert.AlertType.ERROR);
        };
        alert.setTitle(title);
        if (type.equals("error")) {
            alert.setContentText(contentText + "Veuillez contacter un administrateur.");
        } else {
            alert.setContentText(contentText);
        }

        alert.showAndWait();
    }

    public static void disconnectApp() {
        Constants.staticUser = null;
        EnvironnementVariables.ClientsLists = null;
        EnvironnementVariables.UsersLists = null;
        EnvironnementVariables.jwt = null;
        App.changeFxml("login.fxml");
    }

}
