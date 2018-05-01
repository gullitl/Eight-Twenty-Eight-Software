package com.cecilsystems.mistersoftbackend.model;

import com.cecilsystems.mistersoftbackend.util.CecilDecimalFormatter;
import com.cecilsystems.mistersoftbackend.util.HoraDecimalConversor;
import java.math.BigDecimal;

/**
 * @author Plamedi L. Lusembo
 */
public class MaoDeObraPecaComposta extends MaoDeObra {

    private PecaComposta pecaComposta;
    private BigDecimal qtHoras;

    public MaoDeObraPecaComposta() {
    }

    public PecaComposta getPecaComposta() {
        return pecaComposta;
    }

    public void setPecaComposta(PecaComposta pecaComposta) {
        this.pecaComposta = pecaComposta;
    }

    public BigDecimal getQtHoras() {
        return qtHoras;
    }

    public void setQtHoras(BigDecimal qtHoras) {
        this.qtHoras = qtHoras;
    }

    public String getQtHorasEmHHMM() {
        return HoraDecimalConversor.getInstance().converteDecimalEmHHMM(getQtHoras());
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) getPecaComposta().getCdPeca();
        hash += (int) getCdMaoDeObra();
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof MaoDeObraPecaComposta)) {
            return false;
        }
        MaoDeObraPecaComposta other = (MaoDeObraPecaComposta) object;
        return this.getPecaComposta().getCdPeca() == other.getPecaComposta().getCdPeca()
                && this.getCdMaoDeObra() == other.getCdMaoDeObra();
    }

    @Override
    public String toString() {
        return "[Mão_de_Obra: " + getCdMaoDeObra() + " Peça_Composta: " + getPecaComposta().getCdPeca();
    }

    public BigDecimal getVlCustoTotal() {
        return this.getVlCusto().multiply(getQtHoras());
    }

    public String getVlCustoTotalFormatted() {
        return CecilDecimalFormatter.getInstance().formattedValueSemSimbolo(getVlCustoTotal());
    }

}
