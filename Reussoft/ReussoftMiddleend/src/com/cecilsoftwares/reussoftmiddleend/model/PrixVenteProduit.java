package com.cecilsoftwares.reussoftmiddleend.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Plamedi L. Lusembo
 */
public class PrixVenteProduit {

    private final int code;
    private final Produit produit;
    private final Shop shop;
    private final BigDecimal prixUSD;
    private final BigDecimal prixFC;
    private final Date dateHeure;

    public PrixVenteProduit(PrixVenteProduitBuilder prixVenteProduitBuilder) {
        code = prixVenteProduitBuilder.code;
        produit = prixVenteProduitBuilder.produit;
        shop = prixVenteProduitBuilder.shop;
        prixUSD = prixVenteProduitBuilder.prixUSD;
        prixFC = prixVenteProduitBuilder.prixFC;
        dateHeure = prixVenteProduitBuilder.dateHeure;
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

    public BigDecimal getPrixUSD() {
        return prixUSD;
    }

    public BigDecimal getPrixFC() {
        return prixFC;
    }

    public Date getDateHeure() {
        return dateHeure;
    }

    public static class PrixVenteProduitBuilder {

        private int code;
        private Produit produit;
        private Shop shop;
        private BigDecimal prixUSD;
        private BigDecimal prixFC;
        private Date dateHeure;

        public PrixVenteProduitBuilder(int code) {
            this.code = code;
        }

        public PrixVenteProduitBuilder produit(Produit produit) {
            this.produit = produit;
            return this;
        }

        public PrixVenteProduitBuilder shop(Shop shop) {
            this.shop = shop;
            return this;
        }

        public PrixVenteProduitBuilder prixUSD(BigDecimal prixUSD) {
            this.prixUSD = prixUSD;
            return this;
        }

        public PrixVenteProduitBuilder prixFC(BigDecimal prixFC) {
            this.prixFC = prixFC;
            return this;
        }

        public PrixVenteProduitBuilder dateHeure(Date dateHeure) {
            this.dateHeure = dateHeure;
            return this;
        }

        public PrixVenteProduit build() {
            return new PrixVenteProduit(this);
        }

    }

}
