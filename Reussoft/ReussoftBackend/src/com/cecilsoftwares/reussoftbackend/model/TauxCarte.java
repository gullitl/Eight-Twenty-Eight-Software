package com.cecilsoftwares.reussoftbackend.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Plamedi L. Lusembo
 */
public class TauxCarte {

    private int codeTauxCarte;
    private Shop idShop;
    private BigDecimal tauxCarte;
    private Date dateHeure;

    public TauxCarte(TauxCarteBuilder tauxCarteBuilder) {
        codeTauxCarte = tauxCarteBuilder.codeTauxCarte;
        idShop = tauxCarteBuilder.idShop;
        tauxCarte = tauxCarteBuilder.tauxCarte;
        dateHeure = tauxCarteBuilder.dateHeure;
    }

    public int getCodeTauxCarte() {
        return codeTauxCarte;
    }

    public Shop getIdShop() {
        return idShop;
    }

    public BigDecimal getTauxCarte() {
        return tauxCarte;
    }

    public Date getDateHeure() {
        return dateHeure;
    }

    public static class TauxCarteBuilder {

        private int codeTauxCarte;
        private Shop idShop;
        private BigDecimal tauxCarte;
        private Date dateHeure;

        public TauxCarteBuilder(int codeTauxCarte) {
            this.codeTauxCarte = codeTauxCarte;
        }

        public TauxCarteBuilder idShop(Shop idShop) {
            this.idShop = idShop;
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

    }

}
