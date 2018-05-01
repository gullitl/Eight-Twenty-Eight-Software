package com.cecilsystems.mistersoftbackend.model;

import com.cecilsystems.mistersoftbackend.util.CecilDecimalFormatter;
import java.math.BigDecimal;

/**
 * @author Plamedi L. Lusembo
 */
public class Peca {

    private int cdPeca;
    private String dsPeca;
    private UnidadeMedida unidadeMedida;
    private BigDecimal vlCusto;
    private BigDecimal markup;
    private BigDecimal vlVenda;
    private char tipoPeca;

    public Peca() {
    }

    public int getCdPeca() {
        return cdPeca;
    }

    public void setCdPeca(int cdPeca) {
        this.cdPeca = cdPeca;
    }

    public String getDsPeca() {
        return dsPeca;
    }

    public void setDsPeca(String dsPeca) {
        this.dsPeca = dsPeca;
    }

    public UnidadeMedida getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
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

    public BigDecimal getMarkup() {
        return markup;
    }

    public void setMarkup(BigDecimal markup) {
        this.markup = markup;
    }

    public String getMarkupFormatted() {
        return CecilDecimalFormatter.getInstance().formattedValueSemSimbolo(getMarkup());
    }

    public BigDecimal getVlVenda() {
        return vlVenda;
    }

    public void setVlVenda(BigDecimal vlVenda) {
        this.vlVenda = vlVenda;
    }

    public String getVlVendaFormatted() {
        return CecilDecimalFormatter.getInstance().formattedValueSemSimbolo(getVlVenda());
    }

    public char getTipoPeca() {
        return tipoPeca;
    }

    public void setTipoPeca(char tipoPeca) {
        this.tipoPeca = tipoPeca;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) getCdPeca();
        hash += getTipoPeca();
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Peca)) {
            return false;
        }
        Peca other = (Peca) object;
        return this.getTipoPeca() == other.getTipoPeca() && this.getCdPeca() == other.getCdPeca();
    }

    @Override
    public String toString() {
        return "[Id=" + getCdPeca() + " Descrição=" + getDsPeca() + " Tipo=" + getTipoPeca() + "]";
    }

}
