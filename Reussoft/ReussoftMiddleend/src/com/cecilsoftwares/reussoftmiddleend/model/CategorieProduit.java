package com.cecilsoftwares.reussoftmiddleend.model;

/**
 * @author Plamedi L. Lusembo
 */
public class CategorieProduit {

    private final int code;
    private final String description;
    private final String descriptionAbregee;

    public CategorieProduit(CategorieProduitBuilder categorieProduitBuilder) {
        code = categorieProduitBuilder.code;
        description = categorieProduitBuilder.description;
        descriptionAbregee = categorieProduitBuilder.descriptionAbregee;
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

    public static class CategorieProduitBuilder {

        private int code;
        private String description;
        private String descriptionAbregee;

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

        public CategorieProduit build() {
            return new CategorieProduit(this);
        }
    }
}
