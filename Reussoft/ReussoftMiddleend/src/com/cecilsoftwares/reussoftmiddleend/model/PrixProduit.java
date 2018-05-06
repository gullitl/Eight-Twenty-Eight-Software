package com.cecilsoftwares.reussoftmiddleend.model;

import java.math.BigDecimal;

/**
 * @author Plamedi L. Lusembo
 */
public class PrixProduit {

    private final int code;
    private final Produit produit;
    private final Shop shop;
    private final BigDecimal valeur;
    private final String observation;

    public PrixProduit(PrixProduitBuilder prixProduitBuilder) {
        code = prixProduitBuilder.code;
        produit = prixProduitBuilder.produit;
        shop = prixProduitBuilder.shop;
        valeur = prixProduitBuilder.valeur;
        observation = prixProduitBuilder.observation;
    }

    public int getCode() {
        return code;
    }

    public Produit getProduit() {
        return produit;
    }

    public Shop getShop() {
        return shop;
    }

    public BigDecimal getValeur() {
        return valeur;
    }

    public String getObservation() {
        return observation;
    }

    public static class PrixProduitBuilder {

        private int code;
        private Produit produit;
        private Shop shop;
        private BigDecimal valeur;
        private String observation;

        public PrixProduitBuilder(int code) {
            this.code = code;
        }

        public PrixProduitBuilder produit(Produit produit) {
            this.produit = produit;
            return this;
        }

        public PrixProduitBuilder shop(Shop shop) {
            this.shop = shop;
            return this;
        }

        public PrixProduitBuilder valeur(BigDecimal valeur) {
            this.valeur = valeur;
            return this;
        }

        public PrixProduitBuilder observation(String observation) {
            this.observation = observation;
            return this;
        }

        public PrixProduit build() {
            return new PrixProduit(this);
        }

    }

}
