package com.cecilsoftwares.reussoftbackend.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Plamedi L. Lusembo
 */
public class TauxMonnaie {

    private int codeTauxMonnaie;
    private Shop idShop;
    private BigDecimal tauxCarte;
    private Date dateHeure;

    public TauxMonnaie(TauxMonnaieBuilder tauxMonnaieBuilder) {
        codeTauxMonnaie = tauxMonnaieBuilder.codeTauxMonnaie;
        idShop = tauxMonnaieBuilder.idShop;
        tauxCarte = tauxMonnaieBuilder.tauxCarte;
        dateHeure = tauxMonnaieBuilder.dateHeure;
    }

    public int getCodeTauxMonnaie() {
        return codeTauxMonnaie;
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

    public static class TauxMonnaieBuilder {

        private int codeTauxMonnaie;
        private Shop idShop;
        private BigDecimal tauxCarte;
        private Date dateHeure;

        public TauxMonnaieBuilder(int codeTauxMonnaie) {
            this.codeTauxMonnaie = codeTauxMonnaie;
        }

        public TauxMonnaieBuilder idShop(Shop idShop) {
            this.idShop = idShop;
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

    }

}
