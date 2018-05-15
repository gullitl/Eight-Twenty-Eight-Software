package com.cecilsoftwares.reussoftmiddleend.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Plamedi L. Lusembo
 */
public class EntreeStock {

    private final int code;
    private final Produit produit;
    private final Fournisseur fournisseur;
    private final TauxCarte tauxCarte;
    private final BigDecimal prixAchatUSD;
    private final BigDecimal prixAchatFC;
    private final BigDecimal quantiteProduit;
    private final Date dateHeure;
    private final String observation;

    public EntreeStock(EntreeStockBuilder entreeStockBuilder) {
        code = entreeStockBuilder.code;
        prixAchatUSD = entreeStockBuilder.prixAchatUSD;
        prixAchatFC = entreeStockBuilder.prixAchatFC;
        tauxCarte = entreeStockBuilder.tauxCarte;
        dateHeure = entreeStockBuilder.dateHeure;
        produit = entreeStockBuilder.produit;
        fournisseur = entreeStockBuilder.fournisseur;
        quantiteProduit = entreeStockBuilder.quantiteProduit;
        observation = entreeStockBuilder.observation;
    }

    public int getCode() {
        return code;
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

    public TauxCarte getTauxCarte() {
        return tauxCarte;
    }

    public Date getDateHeure() {
        return dateHeure;
    }

    public String getObservation() {
        return observation;
    }

    public static class EntreeStockBuilder {

        private int code;
        private Produit produit;
        private Fournisseur fournisseur;
        private BigDecimal prixAchatUSD;
        private BigDecimal prixAchatFC;
        private TauxCarte tauxCarte;
        private Date dateHeure;
        private BigDecimal quantiteProduit;
        private String observation;

        public EntreeStockBuilder(int code) {
            this.code = code;
        }

        public EntreeStockBuilder produit(Produit produit) {
            this.produit = produit;
            return this;
        }

        public EntreeStockBuilder fournisseur(Fournisseur fournisseur) {
            this.fournisseur = fournisseur;
            return this;
        }

        public EntreeStockBuilder prixAchatUSD(BigDecimal prixAchatUSD) {
            this.prixAchatUSD = prixAchatUSD;
            return this;
        }

        public EntreeStockBuilder prixAchatFC(BigDecimal prixAchatFC) {
            this.prixAchatFC = prixAchatFC;
            return this;
        }

        public EntreeStockBuilder tauxCarte(TauxCarte tauxCarte) {
            this.tauxCarte = tauxCarte;
            return this;
        }

        public EntreeStockBuilder dateHeure(Date dateHeure) {
            this.dateHeure = dateHeure;
            return this;
        }

        public EntreeStockBuilder quantiteProduit(BigDecimal quantiteProduit) {
            this.quantiteProduit = quantiteProduit;
            return this;
        }

        public EntreeStockBuilder observation(String observation) {
            this.observation = observation;
            return this;
        }

        public EntreeStock build() {
            return new EntreeStock(this);
        }
    }

}
