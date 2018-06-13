package com.cecilsoftwares.reussoftmiddleend.model;

import java.math.BigDecimal;

/**
 * @author Plamedi L. Lusembo
 */
public class ItemEntreeStock {

    private final EntreeStock entreeStock;
    private final Produit produit;
    private final BigDecimal prixAchatUSD;
    private final BigDecimal prixAchatFC;
    private final BigDecimal quantiteProduit;

    public ItemEntreeStock(ItemEntreeStockBuilder itemEntreeStockBuilder) {
        entreeStock = itemEntreeStockBuilder.entreeStock;
        produit = itemEntreeStockBuilder.produit;
        prixAchatUSD = itemEntreeStockBuilder.prixAchatUSD;
        prixAchatFC = itemEntreeStockBuilder.prixAchatFC;
        quantiteProduit = itemEntreeStockBuilder.quantiteProduit;
    }

    public EntreeStock getEntreeStock() {
        return entreeStock;
    }

    public Produit getProduit() {
        return produit;
    }

    public BigDecimal getPrixAchatUSD() {
        return prixAchatUSD;
    }

    public BigDecimal getPrixAchatFC() {
        return prixAchatFC;
    }

    public BigDecimal getQuantiteProduit() {
        return quantiteProduit;
    }

    public static class ItemEntreeStockBuilder {

        private EntreeStock entreeStock;
        private Produit produit;
        private BigDecimal prixAchatUSD;
        private BigDecimal prixAchatFC;
        private BigDecimal quantiteProduit;

        public ItemEntreeStockBuilder(EntreeStock entreeStock, Produit produit) {
            this.entreeStock = entreeStock;
            this.produit = produit;
        }

        public ItemEntreeStockBuilder prixAchatUSD(BigDecimal prixAchatUSD) {
            this.prixAchatUSD = prixAchatUSD;
            return this;
        }

        public ItemEntreeStockBuilder prixAchatFC(BigDecimal prixAchatFC) {
            this.prixAchatFC = prixAchatFC;
            return this;
        }

        public ItemEntreeStockBuilder quantiteProduit(BigDecimal quantiteProduit) {
            this.quantiteProduit = quantiteProduit;
            return this;
        }

        public ItemEntreeStock build() {
            return new ItemEntreeStock(this);
        }
    }

}
