package com.cecilsoftwares.reussoftmiddleend.model;

/**
 * @author Plamedi L. Lusembo
 */
public class CategorieProduit {

    private final int code;
    private final String description;
    private final String descriptionAbregee;
    private final String observation;

    public CategorieProduit(CategorieProduitBuilder categorieProduitBuilder) {
        code = categorieProduitBuilder.code;
        description = categorieProduitBuilder.description;
        descriptionAbregee = categorieProduitBuilder.descriptionAbregee;
        observation = categorieProduitBuilder.observation;
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

    public String getObservation() {
        return observation;
    }

    public static class CategorieProduitBuilder {

        private int code;
        private String description;
        private String descriptionAbregee;
        private String observation;

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

        public CategorieProduitBuilder observation(String observation) {
            this.observation = observation;
            return this;
        }

        public CategorieProduit build() {
            return new CategorieProduit(this);
        }
    }
}
