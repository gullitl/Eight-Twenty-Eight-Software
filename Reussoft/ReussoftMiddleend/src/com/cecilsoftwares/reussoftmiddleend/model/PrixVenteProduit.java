package com.cecilsoftwares.reussoftmiddleend.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Plamedi L. Lusembo
 */
public class PrixVenteProduit {

    private int code;
    private Produit produit;
    private Shop shop;
    private BigDecimal prixUSD;
    private BigDecimal prixFC;
    private Date dateHeure;

    public PrixVenteProduit() {

    }

    public PrixVenteProduit(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public BigDecimal getPrixUSD() {
        return prixUSD;
    }

    public void setPrixUSD(BigDecimal prixUSD) {
        this.prixUSD = prixUSD;
    }

    public BigDecimal getPrixFC() {
        return prixFC;
    }

    public void setPrixFC(BigDecimal prixFC) {
        this.prixFC = prixFC;
    }

    public Date getDateHeure() {
        return dateHeure;
    }

    public void setDateHeure(Date dateHeure) {
        this.dateHeure = dateHeure;
    }

}
