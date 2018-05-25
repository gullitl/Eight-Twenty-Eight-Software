package com.cecilsoftwares.reussoftmiddleend.model;

import java.math.BigDecimal;

/**
 * @author Plamedi L. Lusembo
 */
public class Produit {

    private final int code;
    private final Reseau reseau;
    private final String description;
    private final CategorieProduit categorieProduit;
    private final BigDecimal prixAchatUSD;
    private final BigDecimal prixAchatFC;
    private final String observation;
    private final boolean active;

    public Produit(ProduitBuilder produitBuilder) {
        code = produitBuilder.code;
        reseau = produitBuilder.reseau;
        description = produitBuilder.description;
        categorieProduit = produitBuilder.categorieProduit;
        prixAchatUSD = produitBuilder.prixAchatUSD;
        prixAchatFC = produitBuilder.prixAchatFC;
        observation = produitBuilder.observation;
        active = produitBuilder.active;
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

    public CategorieProduit getCategorieProduit() {
        return categorieProduit;
    }

    public BigDecimal getPrixAchatUSD() {
        return prixAchatUSD;
    }

    public BigDecimal getPrixAchatFC() {
        return prixAchatFC;
    }

    public String getObservation() {
        return observation;
    }

    public boolean isActive() {
        return active;
    }

    public static class ProduitBuilder {

        private int code;
        private Reseau reseau;
        private String description;
        private CategorieProduit categorieProduit;
        private BigDecimal prixAchatUSD;
        private BigDecimal prixAchatFC;
        private String observation;
        private boolean active;

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

        public ProduitBuilder categorieProduit(CategorieProduit categorieProduit) {
            this.categorieProduit = categorieProduit;
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

        public ProduitBuilder observation(String observation) {
            this.observation = observation;
            return this;
        }

        public ProduitBuilder active(boolean active) {
            this.active = active;
            return this;
        }

        public Produit build() {
            return new Produit(this);
        }

    }
}
