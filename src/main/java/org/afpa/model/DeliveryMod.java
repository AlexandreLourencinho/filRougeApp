package org.afpa.model;

public class DeliveryMod {

    private int id;
    private String modName;
    private String label;
    private int averageDeliveryDelay;

    public DeliveryMod() {

    }

    public DeliveryMod(int id, String modName, String label, int averageDeliveryDelay) {
        this.id = id;
        this.modName = modName;
        this.label = label;
        this.averageDeliveryDelay = averageDeliveryDelay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModName() {
        return modName;
    }

    public void setModName(String modName) {
        this.modName = modName;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getAverageDeliveryDelay() {
        return averageDeliveryDelay;
    }

    public void setAverageDeliveryDelay(int averageDeliveryDelay) {
        this.averageDeliveryDelay = averageDeliveryDelay;
    }
}
