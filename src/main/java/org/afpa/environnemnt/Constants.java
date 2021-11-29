package org.afpa.environnemnt;

import org.afpa.model.User;

import java.util.ArrayList;

public class Constants {

    public static User staticUser;

    public static ArrayList<String> listRoles() {
        ArrayList<String> strListeRoles = new ArrayList<>();
        strListeRoles.add("admin");
        strListeRoles.add("commercial");
        strListeRoles.add("fournisseur");
        return strListeRoles;
    }
}
