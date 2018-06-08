package com.cecilsoftwares.reussoftmiddleend.model;

import java.math.BigDecimal;

/**
 * @author Plamedi L. Lusembo
 */
public class EntreeStock {

    private final MouvementStock mouvementStock;
    private final Produit produit;
    private final Fournisseur fournisseur;
    private final BigDecimal prixAchatUSD;
    private final BigDecimal prixAchatFC;
    private final BigDecimal quantiteProduit;

    public EntreeStock(EntreeStockBuilder entreeStockBuilder) {
        mouvementStock = entreeStockBuilder.mouvementStock;
        produit = entreeStockBuilder.produit;
        fournisseur = entreeStockBuilder.fournisseur;
        prixAchatUSD = entreeStockBuilder.prixAchatUSD;
        prixAchatFC = entreeStockBuilder.prixAchatFC;
        quantiteProduit = entreeStockBuilder.quantiteProduit;
    }

    public MouvementStock getMouvementStock() {
        return mouvementStock;
    }

    public Produit getProduit() {
        return produit;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
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

        private MouvementStock mouvementStock;
        private Produit produit;
        private Fournisseur fournisseur;
        private BigDecimal prixAchatUSD;
        private BigDecimal prixAchatFC;
        private BigDecimal quantiteProduit;

        public EntreeStockBuilder(MouvementStock mouvementStock, Fournisseur fournisseur, Produit produit) {
            this.mouvementStock = mouvementStock;
            this.fournisseur = fournisseur;
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

        public EntreeStock build() {
            return new EntreeStock(this);
        }
    }

}
