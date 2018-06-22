package com.cecilsoftwares.reussoftmiddleend.model;

import java.math.BigDecimal;

/**
 * @author Plamedi L. Lusembo
 */
public class StockProduit {

    private Produit produit;
    private Shop shop;
    private BigDecimal QuantiteStock;
    private BigDecimal quantiteMaximumStock;
    private BigDecimal quantiteMinimumStock;

    public StockProduit() {

    }

    public StockProduit(Produit produit) {
        this.produit = produit;
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

    public BigDecimal getQuantiteStock() {
        return QuantiteStock;
    }

    public void setQuantiteStock(BigDecimal QuantiteStock) {
        this.QuantiteStock = QuantiteStock;
    }

    public BigDecimal getQuantiteMaximumStock() {
        return quantiteMaximumStock;
    }

    public void setQuantiteMaximumStock(BigDecimal quantiteMaximumStock) {
        this.quantiteMaximumStock = quantiteMaximumStock;
    }

    public BigDecimal getQuantiteMinimumStock() {
        return quantiteMinimumStock;
    }

    public void setQuantiteMinimumStock(BigDecimal quantiteMinimumStock) {
        this.quantiteMinimumStock = quantiteMinimumStock;
    }

}
