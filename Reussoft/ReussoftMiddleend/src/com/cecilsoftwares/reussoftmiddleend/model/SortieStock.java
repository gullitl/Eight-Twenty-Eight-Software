package com.cecilsoftwares.reussoftmiddleend.model;

import java.math.BigDecimal;

/**
 * @author Plamedi L. Lusembo
 */
public class SortieStock {

    private final EntreeStock mouvementStock;
    private final Shop shop;
    private final Produit produit;
    private final Client client;
    private final BigDecimal prixVenteUSD;
    private final BigDecimal prixVenteFC;
    private final BigDecimal quantiteProduit;

    public SortieStock(SortieStockBuilder sortieStockBuilder) {
        mouvementStock = sortieStockBuilder.mouvementStock;
        shop = sortieStockBuilder.shop;
        produit = sortieStockBuilder.produit;
        client = sortieStockBuilder.client;
        prixVenteUSD = sortieStockBuilder.prixVenteUSD;
        prixVenteFC = sortieStockBuilder.prixVenteFC;
        quantiteProduit = sortieStockBuilder.quantiteProduit;
    }

    public EntreeStock getmouvementStock() {
        return mouvementStock;
    }

    public Produit getProduit() {
        return produit;
    }

    public Shop getShop() {
        return shop;
    }

    public Client getClient() {
        return client;
    }

    public BigDecimal getPrixVenteUSD() {
        return prixVenteUSD;
    }

    public BigDecimal getPrixVenteFC() {
        return prixVenteFC;
    }

    public BigDecimal getQtdProduit() {
        return quantiteProduit;
    }

    public static class SortieStockBuilder {

        private EntreeStock mouvementStock;
        private Shop shop;
        private Produit produit;
        private Client client;
        private BigDecimal prixVenteUSD;
        private BigDecimal prixVenteFC;
        private BigDecimal quantiteProduit;

        public SortieStockBuilder(EntreeStock mouvementStock, Shop shop, Produit produit) {
            this.mouvementStock = mouvementStock;
            this.shop = shop;
            this.produit = produit;
        }

        public SortieStockBuilder client(Client client) {
            this.client = client;
            return this;
        }

        public SortieStockBuilder prixVenteUSD(BigDecimal prixVenteUSD) {
            this.prixVenteUSD = prixVenteUSD;
            return this;
        }

        public SortieStockBuilder prixVenteFC(BigDecimal prixVenteFC) {
            this.prixVenteFC = prixVenteFC;
            return this;
        }

        public SortieStockBuilder quantiteProduit(BigDecimal quantiteProduit) {
            this.quantiteProduit = quantiteProduit;
            return this;
        }

        public SortieStock build() {
            return new SortieStock(this);
        }

    }

}
