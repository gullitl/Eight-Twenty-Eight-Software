package com.cecilsystems.mistersoftbackend.model;

import com.cecilsystems.mistersoftbackend.util.CecilDecimalFormatter;
import java.math.BigDecimal;

/**
 * @author Plamedi L. Lusembo
 */
public class ItemPecaComposta extends Peca {

    private PecaComposta pecaComposta;
    private BigDecimal qtdItens;

    public ItemPecaComposta() {
    }

    public PecaComposta getPecaComposta() {
        return pecaComposta;
    }

    public void setPecaComposta(PecaComposta pecaComposta) {
        this.pecaComposta = pecaComposta;
    }

    public BigDecimal getQtdItens() {
        return qtdItens;
    }

    public void setQtdItens(BigDecimal qtdItens) {
        this.qtdItens = qtdItens;
    }

    public String getQtdItensFormatted() {
        return CecilDecimalFormatter.getInstance().formattedValueSemSimbolo4Zero(getQtdItens());
    }

    public String getQtdItensFormattedComUnidadeMedida() {
        return CecilDecimalFormatter.getInstance().formattedValueSemSimbolo4Zero(getQtdItens())
                + " " + getUnidadeMedida().getDaUnidadeMedida();
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) getPecaComposta().getCdPeca();
        hash += (int) getCdPeca();
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ItemPecaComposta)) {
            return false;
        }
        ItemPecaComposta other = (ItemPecaComposta) object;
        return this.getPecaComposta().getCdPeca() == other.getPecaComposta().getCdPeca()
                && this.getCdPeca() == other.getCdPeca();
    }

    @Override
    public String toString() {
        return "[Peça=" + getCdPeca() + " Peça_Composta=" + getPecaComposta().getCdPeca() + "]";
    }

    public BigDecimal getVlCustoTotal() {
        return this.getVlCusto().multiply(new BigDecimal(String.valueOf(getQtdItens())));
    }

    public String getVlCustoTotalFormatted() {
        return CecilDecimalFormatter.getInstance().formattedValueSemSimbolo(getVlCustoTotal());
    }

}
