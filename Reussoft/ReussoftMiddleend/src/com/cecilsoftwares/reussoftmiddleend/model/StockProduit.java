package com.cecilsoftwares.reussoftmiddleend.model;

import java.math.BigDecimal;

/**
 * @author Plamedi L. Lusembo
 */
public class StockProduit {

    private final Produit produit;
    private final Shop shop;
    private final BigDecimal QuantiteStock;
    private final BigDecimal quantiteMaximumStock;
    private final BigDecimal quantiteMinimumStock;
    private final String observation;

    public StockProduit(StockBuilder stockBuilder) {
        produit = stockBuilder.produit;
        shop = stockBuilder.shop;
        QuantiteStock = stockBuilder.QuantiteStock;
        quantiteMaximumStock = stockBuilder.quantiteMaximumStock;
        quantiteMinimumStock = stockBuilder.quantiteMinimumStock;
        observation = stockBuilder.observation;
    }

    public Produit getProduit() {
        return produit;
    }

    public Shop getShop() {
        return shop;
    }

    public BigDecimal getQuantiteProduit() {
        return QuantiteStock;
    }

    public BigDecimal getQuantiteMaximumStock() {
        return quantiteMaximumStock;
    }

    public BigDecimal getQuantiteMinimumStock() {
        return quantiteMinimumStock;
    }

    public String getObservation() {
        return observation;
    }

    public static class StockBuilder {

        private Produit produit;
        private Shop shop;
        private BigDecimal QuantiteStock;
        private BigDecimal quantiteMaximumStock;
        private BigDecimal quantiteMinimumStock;
        private String observation;

        public StockBuilder(Produit produit, Shop shop) {
            this.produit = produit;
            this.shop = shop;
        }

        public StockBuilder QuantiteStock(BigDecimal QuantiteStock) {
            this.QuantiteStock = QuantiteStock;
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

        public StockBuilder observation(String observation) {
            this.observation = observation;
            return this;
        }

        public StockProduit build() {
            return new StockProduit(this);
        }

    }

}
