/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cecilsoftwares.reussoftbackend.model;

import java.math.BigDecimal;

/**
 * @author Plamedi L. Lusembo
 */
public class Stock {

    private Produit idProduit;
    private Shop idShop;
    private BigDecimal qtdStock;
    private BigDecimal qtdMaximumStock;
    private BigDecimal qtdMinimumStock;

    public Stock(StockBuilder stockBuilder) {
        idProduit = stockBuilder.idProduit;
        idShop = stockBuilder.idShop;
        qtdStock = stockBuilder.qtdStock;
        qtdMaximumStock = stockBuilder.qtdMaximumStock;
        qtdMinimumStock = stockBuilder.qtdMinimumStock;
    }

    public Produit getIdProduit() {
        return idProduit;
    }

    public Shop getIdShop() {
        return idShop;
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

        private Produit idProduit;
        private Shop idShop;
        private BigDecimal qtdStock;
        private BigDecimal qtdMaximumStock;
        private BigDecimal qtdMinimumStock;

        public StockBuilder(Produit idProduit, Shop idShop) {
            this.idProduit = idProduit;
            this.idShop = idShop;
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

    }

}
