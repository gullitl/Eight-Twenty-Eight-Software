package com.cecilsoftwares.reussoftbackend.model;

import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class CategorieProduit {

    private int codeCategorieProduit;
    private String description;
    private String descriptionAbregee;
    private List<Produit> listeProduits;

    public CategorieProduit(CategorieProduitBuilder categorieProduitBuilder) {
        codeCategorieProduit = categorieProduitBuilder.codeCategorieProduit;
        description = categorieProduitBuilder.description;
        descriptionAbregee = categorieProduitBuilder.descriptionAbregee;
        listeProduits = categorieProduitBuilder.listeProduits;
    }

    public int getCodeCategorieProduit() {
        return codeCategorieProduit;
    }

    public void setCodeCategorieProduit(int codeCategorieProduit) {
        this.codeCategorieProduit = codeCategorieProduit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionAbregee() {
        return descriptionAbregee;
    }

    public void setDescriptionAbregee(String descriptionAbregee) {
        this.descriptionAbregee = descriptionAbregee;
    }

    public static class CategorieProduitBuilder {

        private int codeCategorieProduit;
        private String description;
        private String descriptionAbregee;
        private List<Produit> listeProduits;

        public CategorieProduitBuilder(int codeCategorieProduit) {
            this.codeCategorieProduit = codeCategorieProduit;
        }

        public CategorieProduitBuilder description(String description) {
            this.description = description;
            return this;
        }

        public CategorieProduitBuilder descriptionAbregee(String descriptionAbregee) {
            this.descriptionAbregee = descriptionAbregee;
            return this;
        }

        public CategorieProduitBuilder listeProduits(List<Produit> listeProduits) {
            this.listeProduits = listeProduits;
            return this;
        }

        public CategorieProduit build() {
            return new CategorieProduit(this);
        }

    }

}
