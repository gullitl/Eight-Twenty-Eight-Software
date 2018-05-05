package com.cecilsoftwares.reussoftmiddleend.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Plamedi L. Lusembo
 */
public class TauxCarte {

    private final int codeTauxCarte;
    private final Shop shop;
    private final BigDecimal tauxCarte;
    private final Date dateHeure;

    public TauxCarte(TauxCarteBuilder tauxCarteBuilder) {
        codeTauxCarte = tauxCarteBuilder.codeTauxCarte;
        shop = tauxCarteBuilder.shop;
        tauxCarte = tauxCarteBuilder.tauxCarte;
        dateHeure = tauxCarteBuilder.dateHeure;
    }

    public int getCodeTauxCarte() {
        return codeTauxCarte;
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

        private int codeTauxCarte;
        private Shop shop;
        private BigDecimal tauxCarte;
        private Date dateHeure;

        public TauxCarteBuilder(int codeTauxCarte) {
            this.codeTauxCarte = codeTauxCarte;
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
