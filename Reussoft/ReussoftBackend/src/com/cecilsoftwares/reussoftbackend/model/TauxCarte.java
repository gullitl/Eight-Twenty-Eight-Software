package com.cecilsoftwares.reussoftbackend.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Plamedi L. Lusembo
 */
public class TauxCarte {

    private final int code;
    private final Shop shop;
    private final BigDecimal tauxCarte;
    private final Date dateHeure;

    public TauxCarte(TauxCarteBuilder tauxCarteBuilder) {
        code = tauxCarteBuilder.code;
        shop = tauxCarteBuilder.shop;
        tauxCarte = tauxCarteBuilder.tauxCarte;
        dateHeure = tauxCarteBuilder.dateHeure;
    }

    public int getCode() {
        return code;
    }

    public Shop getShop() {
        return shop;
    }

    public BigDecimal getTauxCarte() {
        return tauxCarte;
    }

    public Date getDateHeure() {
        return dateHeure;
    }

    public static class TauxCarteBuilder {

        private int code;
        private Shop shop;
        private BigDecimal tauxCarte;
        private Date dateHeure;

        public TauxCarteBuilder(int code) {
            this.code = code;
        }

        public TauxCarteBuilder shop(Shop shop) {
            this.shop = shop;
            return this;
        }

        public TauxCarteBuilder tauxCarte(BigDecimal tauxCarte) {
            this.tauxCarte = tauxCarte;
            return this;
        }

        public TauxCarteBuilder dateHeure(Date dateHeure) {
            this.dateHeure = dateHeure;
            return this;
        }

        public TauxCarte build() {
            return new TauxCarte(this);
        }

    }

}
