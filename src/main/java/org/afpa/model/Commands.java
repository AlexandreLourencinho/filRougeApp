package org.afpa.model;

import java.util.Date;

public class Commands {

    private int id;
    private DeliveryAddress deliveryAddress;
    private DeliveryMod deliveryMod;
    private int reducId;
    private Date dateCom;
    private float totalCommand;
    private String status;

    public Commands() {

    }

    public Commands(int id, DeliveryAddress deliveryAddress, DeliveryMod deliveryMod, Date dateCom, float totalCommand, String status) {
        this.id = id;
        this.deliveryAddress = deliveryAddress;
        this.deliveryMod = deliveryMod;
        this.dateCom = dateCom;
        this.totalCommand = totalCommand;
        this.status = status;
    }

    public Commands(int id, DeliveryAddress deliveryAddress, DeliveryMod deliveryMod, int reducId, Date dateCom, float totalCommand, String status) {
        this.id = id;
        this.deliveryAddress = deliveryAddress;
        this.deliveryMod = deliveryMod;
        this.reducId = reducId;
        this.dateCom = dateCom;
        this.totalCommand = totalCommand;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DeliveryAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(DeliveryAddress deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public DeliveryMod getDeliveryMod() {
        return deliveryMod;
    }

    public void setDeliveryMod(DeliveryMod deliveryMod) {
        this.deliveryMod = deliveryMod;
    }

    public int getReducId() {
        return reducId;
    }

    public void setReducId(int reducId) {
        this.reducId = reducId;
    }

    public Date getDateCom() {
        return dateCom;
    }

    public void setDateCom(Date dateCom) {
        this.dateCom = dateCom;
    }

    public float getTotalCommand() {
        return totalCommand;
    }

    public void setTotalCommand(float totalCommand) {
        this.totalCommand = totalCommand;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
