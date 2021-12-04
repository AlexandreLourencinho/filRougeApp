package org.afpa.environnemnt;

import org.afpa.model.User;

import java.util.ArrayList;

public class EnvironnementVariables {

    public static String baseUrl = "http://localhost:8080";
    public static String jwt;
    public static ArrayList<User> UsersLists = new ArrayList<>();
    public static ArrayList<User> ClientsLists = new ArrayList<>();

    public static void clearUsers() {
        EnvironnementVariables.UsersLists.clear();
        EnvironnementVariables.ClientsLists.clear();
    }

}
