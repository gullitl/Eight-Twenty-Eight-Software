package com.cecilsoftwares.reussoftmiddleend.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Plamedi L. Lusembo
 */
public class Dispatch {

    private final int code;
    private final Produit produit;
    private final Shop shopExpediteur;
    private final Shop shopDestinataire;
    private final BigDecimal quantiteProduit;
    private final Date dateHeure;
    private final boolean valide;
    private final String observation;

    public Dispatch(DispatchBuilder dispatchBuilder) {
        code = dispatchBuilder.code;
        produit = dispatchBuilder.produit;
        shopExpediteur = dispatchBuilder.shopExpediteur;
        shopDestinataire = dispatchBuilder.shopDestinataire;
        quantiteProduit = dispatchBuilder.quantiteProduit;
        dateHeure = dispatchBuilder.dateHeure;
        valide = dispatchBuilder.valide;
        observation = dispatchBuilder.observation;
    }

    public int getCode() {
        return code;
    }

    public Produit getProduit() {
        return produit;
    }

    public Shop getShopExpediteur() {
        return shopExpediteur;
    }

    public Shop getShopDestinataire() {
        return shopDestinataire;
    }

    public BigDecimal getQuantiteProduit() {
        return quantiteProduit;
    }

    public Date getDateHeure() {
        return dateHeure;
    }

    public boolean isValide() {
        return valide;
    }

    public String getObservation() {
        return observation;
    }

    public class DispatchBuilder {

        private int code;
        private Produit produit;
        private Shop shopExpediteur;
        private Shop shopDestinataire;
        private BigDecimal quantiteProduit;
        private Date dateHeure;
        private boolean valide;
        private String observation;

        public DispatchBuilder(int code) {
            this.code = code;
        }

        public DispatchBuilder produit(Produit produit) {
            this.produit = produit;
            return this;
        }

        public DispatchBuilder shopExpediteur(Shop shopExpediteur) {
            this.shopExpediteur = shopExpediteur;
            return this;
        }

        public DispatchBuilder shopDestinataire(Shop shopDestinataire) {
            this.shopDestinataire = shopDestinataire;
            return this;
        }

        public DispatchBuilder quantiteProduit(BigDecimal quantiteProduit) {
            this.quantiteProduit = quantiteProduit;
            return this;
        }

        public DispatchBuilder dateHeure(Date dateHeure) {
            this.dateHeure = dateHeure;
            return this;
        }

        public DispatchBuilder valide(boolean valide) {
            this.valide = valide;
            return this;
        }

        public DispatchBuilder observation(String observation) {
            this.observation = observation;
            return this;
        }

        public Dispatch build() {
            return new Dispatch(this);
        }

    }

}
