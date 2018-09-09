package com.cecilsoftwares.reussoftmiddleend.dto;

import com.cecilsoftwares.reussoftmiddleend.model.*;
import java.math.BigDecimal;

/**
 * @author Plamedi L. Lusembo
 */
public class ItemDispatchDTO {

    private Dispatch dispatch;
    private Shop shop;
    private Produit produit;

    //Actual quantity of stock before the addicional operation
    private BigDecimal quantiteProduitAnterior;

    //Addicional quantity to the stock
    private BigDecimal quantiteProduitAjoutee;

    //Total of the quantiteProduitAnterior plus the quantiteProduitAjoutee
    private BigDecimal quantitetotalProduit;

    public ItemDispatchDTO() {

    }

    public ItemDispatchDTO(Dispatch dispatch, Produit produit, Shop shop) {
        this.dispatch = dispatch;
        this.produit = produit;
        this.shop = shop;
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

    public BigDecimal getQuantiteProduitAnterior() {
        return quantiteProduitAnterior;
    }

    public void setQuantiteProduitAnterior(BigDecimal quantiteProduitAnterior) {
        this.quantiteProduitAnterior = quantiteProduitAnterior;
    }

    public BigDecimal getQuantiteProduitAjoutee() {
        return quantiteProduitAjoutee;
    }

    public void setQuantiteProduitAjoutee(BigDecimal quantiteProduitAjoutee) {
        this.quantiteProduitAjoutee = quantiteProduitAjoutee;
    }

    public BigDecimal getQuantitetotalProduit() {
        return quantitetotalProduit;
    }

    public void setQuantitetotalProduit(BigDecimal quantitetotalProduit) {
        this.quantitetotalProduit = quantitetotalProduit;
    }
}
