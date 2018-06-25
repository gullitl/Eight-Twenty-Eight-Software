package com.cecilsoftwares.reussoftmiddleend.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Plamedi L. Lusembo
 */
public abstract class PrixProduit {

    private int code;
    private Produit produit;
    private BigDecimal valeurUSD;
    private Date dateHeure;

    public PrixProduit() {

    }

    public PrixProduit(int code) {
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

    public BigDecimal getValeurUSD() {
        return valeurUSD;
    }

    public void setValeurUSD(BigDecimal valeurUSD) {
        this.valeurUSD = valeurUSD;
    }

    public Date getDateHeure() {
        return dateHeure;
    }

    public void setDateHeure(Date dateHeure) {
        this.dateHeure = dateHeure;
    }

}
