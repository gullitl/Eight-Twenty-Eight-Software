package com.cecilsoftwares.reussoftmiddleend.model;

import java.math.BigDecimal;

/**
 * @author Plamedi L. Lusembo
 */
public class ItemSortieStock {

    private SortieStock sortieStock;
    private Produit produit;
    private PrixVenteProduit prixVenteProduit;
    private BigDecimal quantiteProduit;

    public ItemSortieStock() {

    }

    public ItemSortieStock(SortieStock sortieStock, Produit produit) {
        this.sortieStock = sortieStock;
        this.produit = produit;
    }

    public SortieStock getSortieStock() {
        return sortieStock;
    }

    public void setSortieStock(SortieStock sortieStock) {
        this.sortieStock = sortieStock;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public PrixVenteProduit getPrixVenteProduit() {
        return prixVenteProduit;
    }

    public void setPrixVenteProduit(PrixVenteProduit prixVenteProduit) {
        this.prixVenteProduit = prixVenteProduit;
    }

    public BigDecimal getQuantiteProduit() {
        return quantiteProduit;
    }

    public void setQuantiteProduit(BigDecimal quantiteProduit) {
        this.quantiteProduit = quantiteProduit;
    }

}
