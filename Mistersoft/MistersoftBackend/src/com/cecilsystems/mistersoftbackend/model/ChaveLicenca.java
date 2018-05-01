package com.cecilsystems.mistersoftbackend.model;

import java.util.Calendar;

/**
 * @author Plamedi L. Lusembo
 */
public class ChaveLicenca {

    private int cdChave;
    private String nrChave;
    private int diasValidade;
    private Calendar dataVencimento;

    public ChaveLicenca() {
    }

    public int getCdChave() {
        return cdChave;
    }

    public void setCdChave(int cdChave) {
        this.cdChave = cdChave;
    }

    public String getNrChave() {
        return nrChave;
    }

    public void setNrChave(String nrChave) {
        this.nrChave = nrChave;
    }

    public int getDiasValidade() {
        return diasValidade;
    }

    public void setDiasValidade(int diasValidade) {
        this.diasValidade = diasValidade;
    }

    public Calendar getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Calendar dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) getCdChave();
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ChaveLicenca)) {
            return false;
        }
        ChaveLicenca other = (ChaveLicenca) object;
        return this.getCdChave() == other.getCdChave()
                && this.getNrChave().equals(other.getNrChave());
    }

    @Override
    public String toString() {
        return "[Código=" + getCdChave() + " Número=" + getNrChave() + "]";
    }

}
