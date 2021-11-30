package org.afpa.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.afpa.environnemnt.EnvironnementVariables;
import org.afpa.model.User;
import org.afpa.utils.Tools;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class DaoBase {

    public static HttpResponse<String> buildRequest(String api, String method, HashMap<String, String> values) {
        HttpResponse<String> response = null;
        HttpClient client = HttpClient.newHttpClient();
        switch (method) {
            case "POSTAUTH" -> {
                try {
                    ObjectMapper objMapper = new ObjectMapper();
                    String bodyRequest = objMapper.writeValueAsString(values);
                    HttpRequest request = HttpRequest.newBuilder().version(HttpClient.Version.HTTP_1_1)
                            .uri(URI.create(EnvironnementVariables.baseUrl + api))
                            .POST(HttpRequest.BodyPublishers.ofString(bodyRequest))
                            .build();
                    response = client.send(request, HttpResponse.BodyHandlers.ofString());
                } catch (Exception e) {
                    Tools.alertTool("error", "ERRCODE: ERRL-001", "Un problème est survenu " +
                            "lors de la connexion. Contactez un administrateur." + e);
                }
            }

            case "POST" -> {
                try {
                    ObjectMapper objMapper = new ObjectMapper();
                    String bodyRequest = objMapper.writeValueAsString(values);
                    HttpRequest request = HttpRequest.newBuilder().version(HttpClient.Version.HTTP_1_1)
                            .uri(URI.create(EnvironnementVariables.baseUrl + api))
                            .POST(HttpRequest.BodyPublishers.ofString(bodyRequest))
                            .setHeader("Authorization", "Bearer " + EnvironnementVariables.jwt)
                            .build();
                    response = client.send(request, HttpResponse.BodyHandlers.ofString());
                } catch (Exception e) {
                    Tools.alertTool("error", "ERRCODE: ERRL-001", "Un problème est survenu " +
                            "lors de l'ajout." + e);
                }
            }

            case "GET" -> {
                try {
                    HttpRequest request = HttpRequest.newBuilder().version(HttpClient.Version.HTTP_1_1)
                            .uri(URI.create(EnvironnementVariables.baseUrl + api))
                            .GET()
                            .setHeader("Authorization", "Bearer " + EnvironnementVariables.jwt)
                            .build();
                    response = client.send(request, HttpResponse.BodyHandlers.ofString());
                } catch (Exception e) {
                    Tools.alertTool("error", "ERRCODE: ERRL-001", "Un problème est survenu " +
                            "lors de la récupération des données." + e);
                }
            }

            case "PUT" -> {
                try {
                    ObjectMapper objMapper = new ObjectMapper();
                    String bodyRequest = objMapper.writeValueAsString(values);
                    HttpRequest request = HttpRequest.newBuilder().version(HttpClient.Version.HTTP_1_1)
                            .uri(URI.create(EnvironnementVariables.baseUrl + api))
                            .PUT(HttpRequest.BodyPublishers.ofString(bodyRequest))
                            .setHeader("Authorization", "Bearer " + EnvironnementVariables.jwt)
                            .build();
                    response = client.send(request, HttpResponse.BodyHandlers.ofString());
                    break;
                } catch (Exception e) {
                    Tools.alertTool("error", "ERRCODE: ERRL-001", "Un problème est survenu " +
                            "lors de la connexion. Contactez un administrateur." + e);
                }
            }

            case "DELETE" -> {
                try {
                    ObjectMapper objMapper = new ObjectMapper();
                    String bodyRequest = objMapper.writeValueAsString(values);
                    HttpRequest request = HttpRequest.newBuilder().version(HttpClient.Version.HTTP_1_1)
                            .uri(URI.create(EnvironnementVariables.baseUrl + api))
                            .method("DELETE", HttpRequest.BodyPublishers.ofString(bodyRequest))
                            .setHeader("Authorization", "Bearer " + EnvironnementVariables.jwt)
                            .build();
                    response = client.send(request, HttpResponse.BodyHandlers.ofString());
                } catch (Exception e) {
                    Tools.alertTool("warning", "ERRCODE: WAU001", "Un problème est survenu au moment de l'ajout" +
                            "d'un utilisateur." + e);
                }
            }

            default -> {
                try {
                    HttpRequest request = HttpRequest.newBuilder().version(HttpClient.Version.HTTP_1_1)
                            .uri(URI.create(EnvironnementVariables.baseUrl + api))
                            .GET()
                            .setHeader("Authorization", "Bearer " + EnvironnementVariables.jwt)
                            .build();
                    return client.send(request, HttpResponse.BodyHandlers.ofString());
                } catch (Exception e) {
                    Tools.alertTool("error", "ERRCODE: ERRL-001", "Un problème est survenu " +
                            "lors de la connexion. Contactez un administrateur." + e);
                }
            }
        }
        System.out.println(response.body());
        return response;
    }


}
