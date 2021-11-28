package org.afpa.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.afpa.environnemnt.EnvironnementVariables;
import org.afpa.model.User;
import org.afpa.utils.Tools;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class UserDAO {

    public static void listeUtilisateurs() {
        try {

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().version(HttpClient.Version.HTTP_1_1)
                    .uri(URI.create(EnvironnementVariables.baseUrl + "/users"))
                    .GET()
                    .setHeader("Authorization", "Bearer " + User.jwt)
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.toString());
            System.out.println(response.body());
            JSONArray respJson = new JSONArray(response.body());
            System.out.println(respJson);
            for (int i = 0; i < respJson.length(); i++) {
                JSONObject obj = (JSONObject) respJson.get(i);
                User user = new User(obj.get("nom").toString(), obj.get("prenom").toString(),
                        obj.get("mail").toString(),
                        obj.get("roles").toString()).setId((Integer) obj.get("id"));
                System.out.println(user.toString());
                User.listeUtilisateurs.add(user);
            }
            System.out.println(User.listeUtilisateurs);
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
            ObjectMapper objMapper = new ObjectMapper();
            String bodyRequest = objMapper.writeValueAsString(values);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().version(HttpClient.Version.HTTP_1_1)
                    .uri(URI.create(EnvironnementVariables.baseUrl + "/users"))
                    .POST(HttpRequest.BodyPublishers.ofString(bodyRequest))
                    .setHeader("Authorization", "Bearer " + User.jwt)
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response);
            System.out.println(response.body());

            if (response.statusCode() == 201) {
                Tools.alertTool("info", "Ajout réussi", "L'utilisateur à bien été ajouté en base de donnée.");

                return true;
            } else if (response.statusCode() == 401) {
                Tools.alertTool("warning", "ERRCODE: EJWT","Votre session a expiré. veuillez vous reconnecter.");

                return false;
            } else {
                Tools.alertTool("error", "ERRCODE: EAUDAO002", "Une erreur est survenue au moment de l'insertion.");

                return false;
            }

        } catch (Exception e) {
            Tools.alertTool("warning","ERRCODE: WAU001","Un problème est survenu au moment de l'ajout" +
                    "d'un utilisateur." + e);

            return false;
        }
    }

    public boolean fetchUpdateUser (User user) {
        try {
            HashMap<String, String> values = new HashMap<>();
            values.put("id", String.valueOf(user.getId()));
            values.put("nom", user.getNom());
            values.put("prenom", user.getPrenom());
            values.put("email", user.getEmail());
            values.put("role", user.getRoles());
            ObjectMapper objMapper = new ObjectMapper();
            String bodyRequest = objMapper.writeValueAsString(values);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().version(HttpClient.Version.HTTP_1_1)
                    .uri(URI.create(EnvironnementVariables.baseUrl + "/users"))
                    .PUT(HttpRequest.BodyPublishers.ofString(bodyRequest))
                    .setHeader("Authorization", "Bearer " + User.jwt)
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response);
            System.out.println(response.body());

            if (response.statusCode() == 201) {
                Tools.alertTool("info", "Ajout réussi", "L'utilisateur à bien été ajouté en base de donnée.");

                return true;
            } else if (response.statusCode() == 401) {
                Tools.alertTool("warning", "ERRCODE: EJWT","Votre session a expiré. veuillez vous reconnecter.");

                return false;
            } else {
                Tools.alertTool("error", "ERRCODE: EAUDAO002", "Une erreur est survenue au moment de l'insertion.");

                return false;
            }

        } catch (Exception e) {
            Tools.alertTool("warning","ERRCODE: WAU001","Un problème est survenu au moment de l'ajout" +
                    "d'un utilisateur." + e);

            return false;
        }
    }
}
