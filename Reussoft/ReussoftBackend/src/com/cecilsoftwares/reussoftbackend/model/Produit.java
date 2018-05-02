package com.cecilsoftwares.reussoftbackend.model;

import java.math.BigDecimal;

/**
 * @author Plamedi L. Lusembo
 */
public class Produit {

    private final int codeProduit;
    private final Reseau reseau;
    private final String description;
    private final BigDecimal prixUSD;
    private final BigDecimal prixFC;
    private final CategorieProduit categorieProduit;

    public Produit(ProduitBuilder produitBuilder) {
        codeProduit = produitBuilder.codeProduit;
        reseau = produitBuilder.reseau;
        description = produitBuilder.description;
        prixUSD = produitBuilder.prixUSD;
        prixFC = produitBuilder.prixFC;
        categorieProduit = produitBuilder.categorieProduit;
    }

    public int getCodeProduit() {
        return codeProduit;
    }

    public Reseau getReseau() {
        return reseau;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrixUSD() {
        return prixUSD;
    }

    public BigDecimal getPrixFC() {
        return prixFC;
    }

    public CategorieProduit getCategorieProduit() {
        return categorieProduit;
    }

    public static class ProduitBuilder {

        private int codeProduit;
        private Reseau reseau;
        private String description;
        private BigDecimal prixUSD;
        private BigDecimal prixFC;
        private CategorieProduit categorieProduit;

        public ProduitBuilder(int codeProduit) {
            this.codeProduit = codeProduit;
        }

        public ProduitBuilder reseau(Reseau reseau) {
            this.reseau = reseau;
            return this;
        }

        public ProduitBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ProduitBuilder prixUSD(BigDecimal prixUSD) {
            this.prixUSD = prixUSD;
            return this;
        }

        public ProduitBuilder prixFC(BigDecimal prixFC) {
            this.prixFC = prixFC;
            return this;
        }

        public ProduitBuilder categorieProduit(CategorieProduit categorieProduit) {
            this.categorieProduit = categorieProduit;
            return this;
        }

        public Produit build() {
            return new Produit(this);
        }

    }
}
