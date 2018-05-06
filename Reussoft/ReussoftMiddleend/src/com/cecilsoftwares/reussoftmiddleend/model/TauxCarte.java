package com.cecilsoftwares.reussoftmiddleend.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Plamedi L. Lusembo
 */
public class TauxCarte {

    private final int code;
    private final Shop shop;
    private final BigDecimal valeur;
    private final Date dateHeure;
    private final String observation;

    public TauxCarte(TauxCarteBuilder tauxCarteBuilder) {
        code = tauxCarteBuilder.code;
        shop = tauxCarteBuilder.shop;
        valeur = tauxCarteBuilder.valeur;
        dateHeure = tauxCarteBuilder.dateHeure;
        observation = tauxCarteBuilder.observation;
    }

    public int getCode() {
        return code;
    }

    public Shop getShop() {
        return shop;
    }

    public BigDecimal getValeur() {
        return valeur;
    }

    public Date getDateHeure() {
        return dateHeure;
    }

    public String getObservation() {
        return observation;
    }

    public static class TauxCarteBuilder {

        private int code;
        private Shop shop;
        private BigDecimal valeur;
        private Date dateHeure;
        private String observation;

        public TauxCarteBuilder(int code) {
            this.code = code;
        }

        public TauxCarteBuilder shop(Shop shop) {
            this.shop = shop;
            return this;
        }

        public TauxCarteBuilder valeur(BigDecimal valeur) {
            this.valeur = valeur;
            return this;
        }

        public TauxCarteBuilder dateHeure(Date dateHeure) {
            this.dateHeure = dateHeure;
            return this;
        }

        public TauxCarteBuilder observation(String observation) {
            this.observation = observation;
            return this;
        }

        public TauxCarte build() {
            return new TauxCarte(this);
        }

    }

}
