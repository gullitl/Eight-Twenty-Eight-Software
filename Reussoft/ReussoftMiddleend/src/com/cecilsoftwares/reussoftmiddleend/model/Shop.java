package com.cecilsoftwares.reussoftmiddleend.model;

/**
 * @author Plamedi L. Lusembo
 */
public class Shop {

    private final int code;
    private final String nom;
    private final String adresse;
    private final boolean active;

    public Shop(ShopBuilder shopBuilder) {
        code = shopBuilder.code;
        nom = shopBuilder.nom;
        adresse = shopBuilder.adresse;
        active = shopBuilder.active;
    }

    public int getCode() {
        return code;
    }

    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public boolean isActive() {
        return active;
    }

    public static class ShopBuilder {

        private int code;
        private String nom;
        private String adresse;
        private boolean active;

        public ShopBuilder(int code) {
            this.code = code;
        }

        public ShopBuilder nom(String nom) {
            this.nom = nom;
            return this;
        }

        public ShopBuilder adresse(String adresse) {
            this.adresse = adresse;
            return this;
        }

        public ShopBuilder active(boolean active) {
            this.active = active;
            return this;
        }

        public Shop build() {
            return new Shop(this);
        }

    }

}
