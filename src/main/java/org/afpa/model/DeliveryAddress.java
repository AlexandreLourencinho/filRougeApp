package org.afpa.model;

public class DeliveryAddress {


    private int id;
    private User user;
    private int countryId;
    private String city;
    private String address;
    private String postalCode;

    public DeliveryAddress() {

    }

    public DeliveryAddress(int id, User user, int countryId, String city, String address, String postalCode) {
        this.id = id;
        this.user = user;
        this.countryId = countryId;
        this.city = city;
        this.address = address;
        this.postalCode = postalCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

}
