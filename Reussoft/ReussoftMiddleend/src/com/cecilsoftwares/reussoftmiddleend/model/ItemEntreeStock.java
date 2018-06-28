package com.cecilsoftwares.reussoftmiddleend.model;

import java.math.BigDecimal;

/**
 * @author Plamedi L. Lusembo
 */
public class ItemEntreeStock {

    private EntreeStock entreeStock;
    private Produit produit;
    private PrixAchatProduit prixAchatProduit;
    private BigDecimal quantiteProduit;

    public ItemEntreeStock() {

    }

    public ItemEntreeStock(EntreeStock entreeStock, Produit produit) {
        this.entreeStock = entreeStock;
        this.produit = produit;
    }

    public EntreeStock getEntreeStock() {
        return entreeStock;
    }

    public void setEntreeStock(EntreeStock entreeStock) {
        this.entreeStock = entreeStock;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public PrixAchatProduit getPrixAchatProduit() {
        return prixAchatProduit;
    }

    public void setPrixAchatProduit(PrixAchatProduit prixAchatProduit) {
        this.prixAchatProduit = prixAchatProduit;
    }

    public BigDecimal getQuantiteProduit() {
        return quantiteProduit;
    }

    public void setQuantiteProduit(BigDecimal quantiteProduit) {
        this.quantiteProduit = quantiteProduit;
    }

}
