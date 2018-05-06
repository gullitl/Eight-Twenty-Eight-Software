package com.cecilsoftwares.reussoftmiddleend.model;

import java.math.BigDecimal;

/**
 * @author Plamedi L. Lusembo
 */
public class Produit {

    private final int code;
    private final Reseau reseau;
    private final String description;
    private final BigDecimal prixAchatUSD;
    private final BigDecimal prixAchatFC;
    private final CategorieProduit categorieProduit;
    private final String observation;

    public Produit(ProduitBuilder produitBuilder) {
        code = produitBuilder.code;
        reseau = produitBuilder.reseau;
        description = produitBuilder.description;
        prixAchatUSD = produitBuilder.prixAchatUSD;
        prixAchatFC = produitBuilder.prixAchatFC;
        categorieProduit = produitBuilder.categorieProduit;
        observation = produitBuilder.observation;
    }

    public int getCode() {
        return code;
    }

    public Reseau getReseau() {
        return reseau;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrixAchatUSD() {
        return prixAchatUSD;
    }

    public BigDecimal getPrixAchatFC() {
        return prixAchatFC;
    }

    public CategorieProduit getCategorieProduit() {
        return categorieProduit;
    }

    public String getObservateur() {
        return observation;
    }

    public static class ProduitBuilder {

        private int code;
        private Reseau reseau;
        private String description;
        private BigDecimal prixAchatUSD;
        private BigDecimal prixAchatFC;
        private CategorieProduit categorieProduit;
        private String observation;

        public ProduitBuilder(int code) {
            this.code = code;
        }

        public ProduitBuilder reseau(Reseau reseau) {
            this.reseau = reseau;
            return this;
        }

        public ProduitBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ProduitBuilder prixAchatUSD(BigDecimal prixAchatUSD) {
            this.prixAchatUSD = prixAchatUSD;
            return this;
        }

        public ProduitBuilder prixAchatFC(BigDecimal prixAchatFC) {
            this.prixAchatFC = prixAchatFC;
            return this;
        }

        public ProduitBuilder categorieProduit(CategorieProduit categorieProduit) {
            this.categorieProduit = categorieProduit;
            return this;
        }

        public ProduitBuilder observation(String observation) {
            this.observation = observation;
            return this;
        }

        public Produit build() {
            return new Produit(this);
        }

    }
}
