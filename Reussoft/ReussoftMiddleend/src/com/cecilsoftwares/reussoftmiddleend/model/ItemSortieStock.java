package com.cecilsoftwares.reussoftmiddleend.model;

import java.math.BigDecimal;

/**
 * @author Plamedi L. Lusembo
 */
public class ItemSortieStock {

    private final SortieStock sortieStock;
    private final Produit produit;
    private final BigDecimal prixVenteUSD;
    private final BigDecimal prixVenteFC;
    private final BigDecimal quantiteProduit;

    public ItemSortieStock(ItemSortieStockBuilder itemSortieStockBuilder) {
        sortieStock = itemSortieStockBuilder.sortieStock;
        produit = itemSortieStockBuilder.produit;
        prixVenteUSD = itemSortieStockBuilder.prixVenteUSD;
        prixVenteFC = itemSortieStockBuilder.prixVenteFC;
        quantiteProduit = itemSortieStockBuilder.quantiteProduit;
    }

    public SortieStock getSortieStock() {
        return sortieStock;
    }

    public Produit getProduit() {
        return produit;
    }

    public BigDecimal getPrixVenteUSD() {
        return prixVenteUSD;
    }

    public BigDecimal getPrixVenteFC() {
        return prixVenteFC;
    }

    public BigDecimal getQuantiteProduit() {
        return quantiteProduit;
    }

    public static class ItemSortieStockBuilder {

        private SortieStock sortieStock;
        private Produit produit;
        private BigDecimal prixVenteUSD;
        private BigDecimal prixVenteFC;
        private BigDecimal quantiteProduit;

        public ItemSortieStockBuilder(SortieStock sortieStock, Produit produit) {
            this.sortieStock = sortieStock;
            this.produit = produit;
        }

        public ItemSortieStockBuilder prixVenteUSD(BigDecimal prixVenteUSD) {
            this.prixVenteUSD = prixVenteUSD;
            return this;
        }

        public ItemSortieStockBuilder prixVenteFC(BigDecimal prixVenteFC) {
            this.prixVenteFC = prixVenteFC;
            return this;
        }

        public ItemSortieStockBuilder quantiteProduit(BigDecimal quantiteProduit) {
            this.quantiteProduit = quantiteProduit;
            return this;
        }

        public ItemSortieStock build() {
            return new ItemSortieStock(this);
        }

    }

}
