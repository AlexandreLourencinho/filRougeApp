package org.afpa.dao;

import org.afpa.environnemnt.EnvironnementVariables;
import org.afpa.model.User;
import org.afpa.utils.Tools;
import org.json.JSONArray;
import org.json.JSONObject;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class UserDAO {

    public static void fetchGetUsers() {

        try {
            HttpResponse<String> response = DaoBase.buildRequest("/users", "GET", null);
            JSONArray respJson = new JSONArray(response.body());
            for (int i = 0; i < respJson.length(); i++) {
                JSONObject obj = (JSONObject) respJson.get(i);
                User user = new User(obj.get("nom").toString(), obj.get("prenom").toString(),
                        obj.get("mail").toString(),
                        obj.get("roles").toString()).setId((Integer) obj.get("id"));
                if (user.getRoles().equals("Client")) {
                    EnvironnementVariables.ClientsLists.add(user);
                } else {
                    EnvironnementVariables.UsersLists.add(user);
                }
            }
        } catch (Exception e) {
            Tools.alertTool("error", "ERRCODE: EFU001", "impossible de récupérer la liste.");
        }
    }

    public static boolean fetchAddUser(User user) {

        try {
            HashMap<String, String> values = new HashMap<>();
            values.put("nom", user.getNom());
            values.put("prenom", user.getPrenom());
            values.put("email", user.getEmail());
            values.put("role", user.getRoles());
            HttpResponse<String> response = DaoBase.buildRequest("/users", "POST", values);

            switch (response.statusCode()) {
                case 201 -> {
                    Tools.alertTool("info", "Ajout réussi", "L'utilisateur à bien été ajouté en base de donnée.");
                    return true;
                }
                case 401 -> {
                    Tools.alertTool("warning", "ERRCODE: EJWT", "Votre session a expiré. veuillez vous reconnecter.");
                    return false;
                }
                default -> {
                    Tools.alertTool("error", "ERRCODE: EAUDAO002", "Une erreur est survenue au moment de l'insertion.");
                    return false;
                }
            }
        } catch (Exception e) {
            Tools.alertTool("warning", "ERRCODE: WAU001", "Un problème est survenu au moment de l'ajout" +
                    "d'un utilisateur." + e);
            return false;
        }
    }

    public boolean fetchUpdateUser(User user) {

        try {
            HashMap<String, String> values = new HashMap<>();
            values.put("id", String.valueOf(user.getId()));
            values.put("nom", user.getNom());
            values.put("prenom", user.getPrenom());
            values.put("email", user.getEmail());
            values.put("role", user.getRoles());
            HttpResponse<String> response = DaoBase.buildRequest("/users", "PUT", values);

            switch (response.statusCode()) {
                case 201 -> {
                    Tools.alertTool("info", "Ajout réussi", "L'utilisateur à bien été ajouté en base de donnée.");
                    return true;
                }
                case 401 -> {
                    Tools.alertTool("warning", "ERRCODE: EJWT", "Votre session a expiré. veuillez vous reconnecter.");
                    return false;
                }
                default -> {
                    Tools.alertTool("error", "ERRCODE: EAUDAO002", "Une erreur est survenue au moment de l'insertion.");
                    return false;
                }
            }
        } catch (Exception e) {
            Tools.alertTool("warning", "ERRCODE: WAU001", "Un problème est survenu au moment de l'ajout" +
                    "d'un utilisateur." + e);
            return false;
        }
    }

    public boolean fetchDeleteUser(User user) {

        try {
            HashMap<String, String> values = new HashMap<>();
            values.put("id", String.valueOf(user.getId()));
            HttpResponse<String> response = DaoBase.buildRequest("/users", "DELETE", values);
            switch (response.statusCode()) {
                case 200 -> {
                    Tools.alertTool("info", "Ajout réussi", "L'utilisateur à bien été supprimé.");
                    return true;
                }
                case 401 -> {
                    Tools.alertTool("warning", "ERRCODE: EJWT", "Votre session a expiré. veuillez vous reconnecter.");
                    return false;
                }
                default -> {
                    Tools.alertTool("error", "ERRCODE: EAUDAO002", "Une erreur est survenue au moment de l'insertion.");
                    return false;
                }
            }

        } catch (Exception e) {
            Tools.alertTool("warning", "ERRCODE: WAU001", "Un problème est survenu au moment de l'ajout" +
                    "d'un utilisateur." + e);
            return false;
        }
    }
}
