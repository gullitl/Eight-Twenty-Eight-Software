package com.cecilsoftwares.reussoftmiddleend.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Plamedi L. Lusembo
 */
public class TauxMonnaie {

    private final int code;
    private final Shop shop;
    private final BigDecimal valeur;
    private final Date dateHeure;
    private final String observation;

    public TauxMonnaie(TauxMonnaieBuilder tauxMonnaieBuilder) {
        code = tauxMonnaieBuilder.code;
        shop = tauxMonnaieBuilder.shop;
        valeur = tauxMonnaieBuilder.valeur;
        dateHeure = tauxMonnaieBuilder.dateHeure;
        observation = tauxMonnaieBuilder.observation;
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

    public static class TauxMonnaieBuilder {

        private int code;
        private Shop shop;
        private BigDecimal valeur;
        private Date dateHeure;
        private String observation;

        public TauxMonnaieBuilder(int code) {
            this.code = code;
        }

        public TauxMonnaieBuilder shop(Shop shop) {
            this.shop = shop;
            return this;
        }

        public TauxMonnaieBuilder valeur(BigDecimal valeur) {
            this.valeur = valeur;
            return this;
        }

        public TauxMonnaieBuilder dateHeure(Date dateHeure) {
            this.dateHeure = dateHeure;
            return this;
        }

        public TauxMonnaieBuilder observation(String observation) {
            this.observation = observation;
            return this;
        }

        public TauxMonnaie build() {
            return new TauxMonnaie(this);
        }

    }

}
