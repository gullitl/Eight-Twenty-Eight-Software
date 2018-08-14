package com.cecilsoftwares.reussoftmiddleend.model;

import java.math.BigDecimal;

/**
 * @author Plamedi L. Lusembo
 */
public class Produit {

    private String id;
    private Reseau reseau;
    private String description;
    private CategorieProduit categorieProduit;
    private PrixAchatProduit prixAchatProduit;
    private BigDecimal quantiteStockMininum;
    private boolean active;

    public Produit() {

    }

    public Produit(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public BigDecimal getQuantiteStockMininum() {
        return quantiteStockMininum;
    }

    public void setQuantiteStockMininum(BigDecimal quantiteStockMininum) {
        this.quantiteStockMininum = quantiteStockMininum;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
