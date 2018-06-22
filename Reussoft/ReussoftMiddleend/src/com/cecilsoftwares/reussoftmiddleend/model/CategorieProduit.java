package com.cecilsoftwares.reussoftmiddleend.model;

/**
 * @author Plamedi L. Lusembo
 */
public class CategorieProduit {

    private int code;
    private String description;
    private String descriptionAbregee;

    public CategorieProduit() {

    }

    public CategorieProduit(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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
