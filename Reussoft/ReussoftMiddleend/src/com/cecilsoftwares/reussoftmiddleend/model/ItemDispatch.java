package com.cecilsoftwares.reussoftmiddleend.model;

import java.math.BigDecimal;

/**
 * @author Plamedi L. Lusembo
 */
public class ItemDispatch {

    private Dispatch dispatch;
    private Shop shop;
    private Produit produit;
    private BigDecimal quantiteProduit;

    public ItemDispatch() {

    }

    public ItemDispatch(Dispatch dispatch, Produit produit) {
        this.dispatch = dispatch;
        this.produit = produit;
    }

    public Dispatch getDispatch() {
        return dispatch;
    }

    public void setDispatch(Dispatch dispatch) {
        this.dispatch = dispatch;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public BigDecimal getQuantiteProduit() {
        return quantiteProduit;
    }

    public void setQuantiteProduit(BigDecimal quantiteProduit) {
        this.quantiteProduit = quantiteProduit;
    }

}
