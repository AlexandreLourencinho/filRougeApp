package org.afpa.model;

import java.util.ArrayList;

public class User {

    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String roles;
    private String jwtKey;
    public static String jwt;
    public static ArrayList<User> listeUtilisateurs = new ArrayList<>();

    public User() {

    }

    public User(String nom, String prenom, String email, String roles) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public User setId(int id) {
        this.id = id;
        return this;
    }

    public String getNom() {
        return nom;
    }

    public User setNom(String nom) {
        this.nom = nom;
        return this;
    }

    public String getPrenom() {
        return prenom;
    }

    public User setPrenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getRoles() {
        return roles;
    }

    public User setRoles(String roles) {
        this.roles = roles;
        return this;
    }

    public String getJwtKey() {
        return jwtKey;
    }

    public void setJwtKey(String jwtKey) {
        this.jwtKey = jwtKey;
    }

    @Override
    public String toString() {
        return "nom:" + this.nom + ", prénom: " + this.prenom + ", id:" + this.getId() + ", role:" + this.getRoles() +
                ", mail:" + this.getEmail();
    }
}
