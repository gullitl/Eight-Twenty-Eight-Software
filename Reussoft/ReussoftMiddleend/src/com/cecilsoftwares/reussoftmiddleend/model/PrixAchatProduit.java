package com.cecilsoftwares.reussoftmiddleend.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Plamedi L. Lusembo
 */
public class PrixAchatProduit {

    private final int code;
    private final Produit produit;
    private final BigDecimal prixUSD;
    private final BigDecimal prixFC;
    private final Date dateHeure;

    public PrixAchatProduit(PrixAchatProduitBuilder prixAchatProduitBuilder) {
        code = prixAchatProduitBuilder.code;
        produit = prixAchatProduitBuilder.produit;
        prixUSD = prixAchatProduitBuilder.prixUSD;
        prixFC = prixAchatProduitBuilder.prixFC;
        dateHeure = prixAchatProduitBuilder.dateHeure;
    }

    public int getCode() {
        return code;
    }

    public Produit getProduit() {
        return produit;
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

    public static class PrixAchatProduitBuilder {

        private int code;
        private Produit produit;
        private BigDecimal prixUSD;
        private BigDecimal prixFC;
        private Date dateHeure;

        public PrixAchatProduitBuilder(int code) {
            this.code = code;
        }

        public PrixAchatProduitBuilder produit(Produit produit) {
            this.produit = produit;
            return this;
        }

        public PrixAchatProduitBuilder prixUSD(BigDecimal prixUSD) {
            this.prixUSD = prixUSD;
            return this;
        }

        public PrixAchatProduitBuilder prixFC(BigDecimal prixFC) {
            this.prixFC = prixFC;
            return this;
        }

        public PrixAchatProduitBuilder dateHeure(Date dateHeure) {
            this.dateHeure = dateHeure;
            return this;
        }

        public PrixAchatProduit build() {
            return new PrixAchatProduit(this);
        }

    }

}
