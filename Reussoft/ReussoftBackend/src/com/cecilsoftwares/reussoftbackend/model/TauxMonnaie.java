package com.cecilsoftwares.reussoftbackend.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Plamedi L. Lusembo
 */
public class TauxMonnaie {

    private final int codeTauxMonnaie;
    private final Shop shop;
    private final BigDecimal tauxCarte;
    private final Date dateHeure;

    public TauxMonnaie(TauxMonnaieBuilder tauxMonnaieBuilder) {
        codeTauxMonnaie = tauxMonnaieBuilder.codeTauxMonnaie;
        shop = tauxMonnaieBuilder.shop;
        tauxCarte = tauxMonnaieBuilder.tauxCarte;
        dateHeure = tauxMonnaieBuilder.dateHeure;
    }

    public int getCodeTauxMonnaie() {
        return codeTauxMonnaie;
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

    public static class TauxMonnaieBuilder {

        private int codeTauxMonnaie;
        private Shop shop;
        private BigDecimal tauxCarte;
        private Date dateHeure;

        public TauxMonnaieBuilder(int codeTauxMonnaie) {
            this.codeTauxMonnaie = codeTauxMonnaie;
        }

        public TauxMonnaieBuilder shop(Shop shop) {
            this.shop = shop;
            return this;
        }

        public TauxMonnaieBuilder tauxCarte(BigDecimal tauxCarte) {
            this.tauxCarte = tauxCarte;
            return this;
        }

        public TauxMonnaieBuilder dateHeure(Date dateHeure) {
            this.dateHeure = dateHeure;
            return this;
        }

        public TauxMonnaie build() {
            return new TauxMonnaie(this);
        }

    }

}
