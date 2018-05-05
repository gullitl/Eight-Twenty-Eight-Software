/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cecilsoftwares.reussoftmiddleend.model;

import java.math.BigDecimal;

/**
 * @author Plamedi L. Lusembo
 */
public class Stock {

    private final Produit produit;
    private final Shop shop;
    private final BigDecimal qtdStock;
    private final BigDecimal qtdMaximumStock;
    private final BigDecimal qtdMinimumStock;

    public Stock(StockBuilder stockBuilder) {
        produit = stockBuilder.produit;
        shop = stockBuilder.shop;
        qtdStock = stockBuilder.qtdStock;
        qtdMaximumStock = stockBuilder.qtdMaximumStock;
        qtdMinimumStock = stockBuilder.qtdMinimumStock;
    }

    public Produit getProduit() {
        return produit;
    }

    public Shop getShop() {
        return shop;
    }

    public BigDecimal getQtdStock() {
        return qtdStock;
    }

    public BigDecimal getQtdMaximumStock() {
        return qtdMaximumStock;
    }

    public BigDecimal getQtdMinimumStock() {
        return qtdMinimumStock;
    }

    public static class StockBuilder {

        private Produit produit;
        private Shop shop;
        private BigDecimal qtdStock;
        private BigDecimal qtdMaximumStock;
        private BigDecimal qtdMinimumStock;

        public StockBuilder(Produit produit, Shop shop) {
            this.produit = produit;
            this.shop = shop;
        }

        public StockBuilder qtdStock(BigDecimal qtdStock) {
            this.qtdStock = qtdStock;
            return this;
        }

        public StockBuilder qtdMaximumStock(BigDecimal qtdMaximumStock) {
            this.qtdMaximumStock = qtdMaximumStock;
            return this;
        }

        public StockBuilder qtdMinimumStock(BigDecimal qtdMinimumStock) {
            this.qtdMinimumStock = qtdMinimumStock;
            return this;
        }

        public Stock build() {
            return new Stock(this);
        }

    }

}
