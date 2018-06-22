package com.cecilsoftwares.reussoftmiddleend.model;

import java.math.BigDecimal;

/**
 * @author Plamedi L. Lusembo
 */
public class Produit {

    private int code;
    private Reseau reseau;
    private String description;
    private CategorieProduit categorieProduit;
    private BigDecimal prixAchatUSD;
    private BigDecimal prixAchatFC;
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

    public BigDecimal getPrixAchatUSD() {
        return prixAchatUSD;
    }

    public void setPrixAchatUSD(BigDecimal prixAchatUSD) {
        this.prixAchatUSD = prixAchatUSD;
    }

    public BigDecimal getPrixAchatFC() {
        return prixAchatFC;
    }

    public void setPrixAchatFC(BigDecimal prixAchatFC) {
        this.prixAchatFC = prixAchatFC;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
