package com.cecilsoftwares.reussoftmiddleend.model;

import java.math.BigDecimal;

/**
 * @author Plamedi L. Lusembo
 */
public class PrixVenteProduit {

    private final Produit produit;
    private final Shop shop;
    private final BigDecimal prixUSD;
    private final BigDecimal prixFC;
    private final String observation;

    public PrixVenteProduit(PrixVenteProduitBuilder prixVenteProduitBuilder) {
        produit = prixVenteProduitBuilder.produit;
        shop = prixVenteProduitBuilder.shop;
        prixUSD = prixVenteProduitBuilder.prixUSD;
        prixFC = prixVenteProduitBuilder.prixFC;
        observation = prixVenteProduitBuilder.observation;
    }

    public Produit getProduit() {
        return produit;
    }

    public Shop getShop() {
        return shop;
    }

    public BigDecimal getPrixUSD() {
        return prixUSD;
    }

    public BigDecimal getPrixFC() {
        return prixFC;
    }

    public String getObservation() {
        return observation;
    }

    public static class PrixVenteProduitBuilder {

        private Produit produit;
        private Shop shop;
        private BigDecimal prixUSD;
        private BigDecimal prixFC;
        private String observation;

        public PrixVenteProduitBuilder(Produit produit, Shop shop) {
            this.produit = produit;
            this.shop = shop;
        }

        public PrixVenteProduitBuilder prixUSD(BigDecimal prixUSD) {
            this.prixUSD = prixUSD;
            return this;
        }

        public PrixVenteProduitBuilder prixFC(BigDecimal prixFC) {
            this.prixFC = prixFC;
            return this;
        }

        public PrixVenteProduitBuilder observation(String observation) {
            this.observation = observation;
            return this;
        }

        public PrixVenteProduit build() {
            return new PrixVenteProduit(this);
        }

    }

}
