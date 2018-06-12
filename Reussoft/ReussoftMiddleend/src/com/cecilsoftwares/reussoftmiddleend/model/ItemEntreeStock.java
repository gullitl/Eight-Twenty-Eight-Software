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

    public ItemEntreeStock(EntreeStockBuilder entreeStockBuilder) {
        entreeStock = entreeStockBuilder.entreeStock;
        produit = entreeStockBuilder.produit;
        prixAchatUSD = entreeStockBuilder.prixAchatUSD;
        prixAchatFC = entreeStockBuilder.prixAchatFC;
        quantiteProduit = entreeStockBuilder.quantiteProduit;
    }

    public EntreeStock getMouvementStock() {
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

    public static class EntreeStockBuilder {

        private EntreeStock entreeStock;
        private Produit produit;
        private BigDecimal prixAchatUSD;
        private BigDecimal prixAchatFC;
        private BigDecimal quantiteProduit;

        public EntreeStockBuilder(EntreeStock entreeStock, Produit produit) {
            this.entreeStock = entreeStock;
            this.produit = produit;
        }

        public EntreeStockBuilder prixAchatUSD(BigDecimal prixAchatUSD) {
            this.prixAchatUSD = prixAchatUSD;
            return this;
        }

        public EntreeStockBuilder prixAchatFC(BigDecimal prixAchatFC) {
            this.prixAchatFC = prixAchatFC;
            return this;
        }

        public EntreeStockBuilder quantiteProduit(BigDecimal quantiteProduit) {
            this.quantiteProduit = quantiteProduit;
            return this;
        }

        public ItemEntreeStock build() {
            return new ItemEntreeStock(this);
        }
    }

}
