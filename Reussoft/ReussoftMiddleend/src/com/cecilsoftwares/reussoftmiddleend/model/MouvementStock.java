package com.cecilsoftwares.reussoftmiddleend.model;

import com.cecilsoftwares.reussoftmiddleend.enumarable.TypeMouvementStockEnum;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Plamedi L. Lusembo
 */
public class MouvementStock {

    private final int code;
    private final TauxCarte tauxCarte;
    private final BigDecimal prixAchatUSD;
    private final BigDecimal prixAchatFC;
    private final Date dateHeure;
    private final String observation;
    private final TypeMouvementStockEnum typeMouvementStockEnum;

    public MouvementStock(MouvementStockBuilder mouvementStockBuilder) {
        code = mouvementStockBuilder.code;
        prixAchatUSD = mouvementStockBuilder.prixAchatUSD;
        prixAchatFC = mouvementStockBuilder.prixAchatFC;
        tauxCarte = mouvementStockBuilder.tauxCarte;
        dateHeure = mouvementStockBuilder.dateHeure;
        observation = mouvementStockBuilder.observation;
        typeMouvementStockEnum = mouvementStockBuilder.typeMouvementStockEnum;
    }

    public int getCode() {
        return code;
    }

    public BigDecimal getPrixAchatUSD() {
        return prixAchatUSD;
    }

    public BigDecimal getPrixAchatFC() {
        return prixAchatFC;
    }

    public TauxCarte getTauxCarte() {
        return tauxCarte;
    }

    public Date getDateHeure() {
        return dateHeure;
    }

    public String getObservation() {
        return observation;
    }

    public TypeMouvementStockEnum getTypeMouvementStockEnum() {
        return typeMouvementStockEnum;
    }

    public static class MouvementStockBuilder {

        private int code;
        private BigDecimal prixAchatUSD;
        private BigDecimal prixAchatFC;
        private TauxCarte tauxCarte;
        private Date dateHeure;
        private String observation;
        private TypeMouvementStockEnum typeMouvementStockEnum;

        public MouvementStockBuilder(int code) {
            this.code = code;
        }

        public MouvementStockBuilder prixAchatUSD(BigDecimal prixAchatUSD) {
            this.prixAchatUSD = prixAchatUSD;
            return this;
        }

        public MouvementStockBuilder prixAchatFC(BigDecimal prixAchatFC) {
            this.prixAchatFC = prixAchatFC;
            return this;
        }

        public MouvementStockBuilder tauxCarte(TauxCarte tauxCarte) {
            this.tauxCarte = tauxCarte;
            return this;
        }

        public MouvementStockBuilder dateHeure(Date dateHeure) {
            this.dateHeure = dateHeure;
            return this;
        }

        public MouvementStockBuilder observation(String observation) {
            this.observation = observation;
            return this;
        }

        public MouvementStockBuilder typeMouvementStockEnum(TypeMouvementStockEnum typeMouvementStockEnum) {
            this.typeMouvementStockEnum = typeMouvementStockEnum;
            return this;
        }

        public MouvementStock build() {
            return new MouvementStock(this);
        }

    }

}
