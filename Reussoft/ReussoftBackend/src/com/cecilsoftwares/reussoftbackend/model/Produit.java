package com.cecilsoftwares.reussoftbackend.model;

import java.math.BigDecimal;

/**
 * @author Plamedi L. Lusembo
 */
public class Produit {

    private int codeProduit;
    private Reseau idReseau;
    private String description;
    private BigDecimal prixUSD;
    private BigDecimal prixFC;
    private CategorieProduit idCategorieProduit;

    public Produit(ProduitBuilder produitBuilder) {
        codeProduit = produitBuilder.codeProduit;
        idReseau = produitBuilder.idReseau;
        description = produitBuilder.description;
        prixUSD = produitBuilder.prixUSD;
        prixFC = produitBuilder.prixFC;
        idCategorieProduit = produitBuilder.idCategorieProduit;
    }

    public Reseau getIdReseau() {
        return idReseau;
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

    public CategorieProduit getIdCategorieProduit() {
        return idCategorieProduit;
    }

    public static class ProduitBuilder {

        private int codeProduit;
        private Reseau idReseau;
        private String description;
        private BigDecimal prixUSD;
        private BigDecimal prixFC;
        private CategorieProduit idCategorieProduit;

        public ProduitBuilder(int codeProduit) {
            this.codeProduit = codeProduit;
        }

        public ProduitBuilder idReseau(Reseau idReseau) {
            this.idReseau = idReseau;
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

        public ProduitBuilder idCategorieProduit(CategorieProduit idCategorieProduit) {
            this.idCategorieProduit = idCategorieProduit;
            return this;
        }

    }
}
