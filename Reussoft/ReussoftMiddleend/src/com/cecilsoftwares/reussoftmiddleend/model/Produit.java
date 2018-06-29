package com.cecilsoftwares.reussoftmiddleend.model;

/**
 * @author Plamedi L. Lusembo
 */
public class Produit {

    private int code;
    private Reseau reseau;
    private String description;
    private CategorieProduit categorieProduit;
    private PrixAchatProduit prixAchatProduit;
    private boolean active;

    public Produit() {

    }

    public Produit(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Reseau getReseau() {
        return reseau;
    }

    public void setReseau(Reseau reseau) {
        this.reseau = reseau;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CategorieProduit getCategorieProduit() {
        return categorieProduit;
    }

    public void setCategorieProduit(CategorieProduit categorieProduit) {
        this.categorieProduit = categorieProduit;
    }

    public PrixAchatProduit getPrixAchatProduit() {
        return prixAchatProduit;
    }

    public void setPrixAchatProduit(PrixAchatProduit prixAchatProduit) {
        this.prixAchatProduit = prixAchatProduit;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
