package com.cecilsoftwares.reussoftbackend.model;

import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class CategorieProduit {

    private final int code;
    private final String description;
    private final String descriptionAbregee;
    private final List<Produit> listeProduits;

    public CategorieProduit(CategorieProduitBuilder categorieProduitBuilder) {
        code = categorieProduitBuilder.code;
        description = categorieProduitBuilder.description;
        descriptionAbregee = categorieProduitBuilder.descriptionAbregee;
        listeProduits = categorieProduitBuilder.listeProduits;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getDescriptionAbregee() {
        return descriptionAbregee;
    }

    public List<Produit> getListeProduits() {
        return listeProduits;
    }

    public static class CategorieProduitBuilder {

        private int code;
        private String description;
        private String descriptionAbregee;
        private List<Produit> listeProduits;

        public CategorieProduitBuilder(int code) {
            this.code = code;
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
