package com.cecilsystems.mistersoftbackend.model;

/**
 * @author Plamedi L. Lusembo
 */
public class UnidadeMedida {

    private int cdUnidadeMedida;
    private String dsUnidadeMedida;
    private String daUnidadeMedida;

    public UnidadeMedida() {
    }

    public int getCdUnidadeMedida() {
        return cdUnidadeMedida;
    }

    public void setCdUnidadeMedida(int cdUnidadeMedida) {
        this.cdUnidadeMedida = cdUnidadeMedida;
    }

    public String getDsUnidadeMedida() {
        return dsUnidadeMedida;
    }

    public void setDsUnidadeMedida(String dsUnidadeMedida) {
        this.dsUnidadeMedida = dsUnidadeMedida;
    }

    public String getDaUnidadeMedida() {
        return daUnidadeMedida;
    }

    public void setDaUnidadeMedida(String daUnidadeMedida) {
        this.daUnidadeMedida = daUnidadeMedida;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) getCdUnidadeMedida();
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof UnidadeMedida)) {
            return false;
        }
        UnidadeMedida other = (UnidadeMedida) object;
        return this.getCdUnidadeMedida() == other.getCdUnidadeMedida();
    }

    @Override
    public String toString() {
        return getDsUnidadeMedida() + " (" + getDaUnidadeMedida() + ")";
    }

}
