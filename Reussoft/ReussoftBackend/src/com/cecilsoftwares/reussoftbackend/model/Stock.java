package com.cecilsoftwares.reussoftbackend.model;

import java.math.BigDecimal;

/**
 * @author Plamedi L. Lusembo
 */
public class Stock {

    private final Produit produit;
    private final Shop shop;
    private final BigDecimal quantiteStock;
    private final BigDecimal quantiteMaximumStock;
    private final BigDecimal quantiteMinimumStock;

    public Stock(StockBuilder stockBuilder) {
        produit = stockBuilder.produit;
        shop = stockBuilder.shop;
        quantiteStock = stockBuilder.quantiteStock;
        quantiteMaximumStock = stockBuilder.quantiteMaximumStock;
        quantiteMinimumStock = stockBuilder.quantiteMinimumStock;
    }

    public Produit getProduit() {
        return produit;
    }

    public Shop getShop() {
        return shop;
    }

    public BigDecimal getQuantiteStock() {
        return quantiteStock;
    }

    public BigDecimal getQuantiteMaximumStock() {
        return quantiteMaximumStock;
    }

    public BigDecimal getQuantiteMinimumStock() {
        return quantiteMinimumStock;
    }

    public static class StockBuilder {

        private Produit produit;
        private Shop shop;
        private BigDecimal quantiteStock;
        private BigDecimal quantiteMaximumStock;
        private BigDecimal quantiteMinimumStock;

        public StockBuilder(Produit produit, Shop shop) {
            this.produit = produit;
            this.shop = shop;
        }

        public StockBuilder quantiteStock(BigDecimal quantiteStock) {
            this.quantiteStock = quantiteStock;
            return this;
        }

        public StockBuilder quantiteMaximumStock(BigDecimal quantiteMaximumStock) {
            this.quantiteMaximumStock = quantiteMaximumStock;
            return this;
        }

        public StockBuilder qtdMinimumStock(BigDecimal quantiteMinimumStock) {
            this.quantiteMinimumStock = quantiteMinimumStock;
            return this;
        }

        public Stock build() {
            return new Stock(this);
        }

    }

}
