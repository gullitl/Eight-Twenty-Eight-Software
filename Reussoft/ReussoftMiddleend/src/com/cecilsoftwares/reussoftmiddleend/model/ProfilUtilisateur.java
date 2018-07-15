package com.cecilsoftwares.reussoftmiddleend.model;

/**
 * @author Plamedi L. Lusembo
 */
public class ProfilUtilisateur {

    private String id;
    private String description;
    private String descriptionAbregee;

    public ProfilUtilisateur() {

    }

    public ProfilUtilisateur(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionAbregee() {
        return descriptionAbregee;
    }

    public void setDescriptionAbregee(String descriptionAbregee) {
        this.descriptionAbregee = descriptionAbregee;
    }

}
