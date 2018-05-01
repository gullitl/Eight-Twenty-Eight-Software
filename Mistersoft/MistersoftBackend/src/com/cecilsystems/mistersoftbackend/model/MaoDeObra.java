package com.cecilsystems.mistersoftbackend.model;

import com.cecilsystems.mistersoftbackend.util.CecilDecimalFormatter;
import java.math.BigDecimal;

/**
 * @author Plamedi L. Lusembo
 */
public class MaoDeObra {

    private int cdMaoDeObra;
    private String dsMaoDeObra;
    private String daMaoDeObra;
    private BigDecimal vlCusto;
    private String observacao;

    public MaoDeObra() {
    }

    public int getCdMaoDeObra() {
        return cdMaoDeObra;
    }

    public void setCdMaoDeObra(int cdMaoDeObra) {
        this.cdMaoDeObra = cdMaoDeObra;
    }

    public String getDsMaoDeObra() {
        return dsMaoDeObra;
    }

    public void setDsMaoDeObra(String dsMaoDeObra) {
        this.dsMaoDeObra = dsMaoDeObra;
    }

    public String getDaMaoDeObra() {
        return daMaoDeObra;
    }

    public void setDaMaoDeObra(String daMaoDeObra) {
        this.daMaoDeObra = daMaoDeObra;
    }

    public BigDecimal getVlCusto() {
        return vlCusto;
    }

    public void setVlCusto(BigDecimal vlCusto) {
        this.vlCusto = vlCusto;
    }

    public String getVlCustoFormatted() {
        return CecilDecimalFormatter.getInstance().formattedValueSemSimbolo(getVlCusto());
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) getCdMaoDeObra();
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof MaoDeObra)) {
            return false;
        }
        MaoDeObra other = (MaoDeObra) object;
        return this.getCdMaoDeObra() == other.getCdMaoDeObra();
    }

    @Override
    public String toString() {
        return "[Id=" + getCdMaoDeObra() + " Descrição=" + getDsMaoDeObra() + "]";
    }

}
