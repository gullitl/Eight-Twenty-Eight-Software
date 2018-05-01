package com.cecilsystems.mistersoftbackend.model;

import com.cecilsystems.mistersoftbackend.util.CecilDecimalFormatter;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class PecaComposta extends Peca {

    private BigDecimal vlTotalItens;
    private BigDecimal vlTotalMaoDeObra;
    private List<ItemPecaComposta> lstItensPecaComposta;
    private List<MaoDeObraPecaComposta> lstMaoDeObra;
    private List<DetalheItemPecaComposta> lstDetalheItemPecaComposta;

    public PecaComposta() {
    }

    public BigDecimal getVlTotalItens() {
        return vlTotalItens;
    }

    public void setVlTotalItens(BigDecimal vlTotalItens) {
        this.vlTotalItens = vlTotalItens;
    }

    public String getVlTotalItensFormatted() {
        return CecilDecimalFormatter.getInstance().formattedValueSemSimbolo(getVlTotalItens());
    }

    public BigDecimal getVlTotalMaoDeObra() {
        return vlTotalMaoDeObra;
    }

    public void setVlTotalMaoDeObra(BigDecimal vlTotalMaoDeObra) {
        this.vlTotalMaoDeObra = vlTotalMaoDeObra;
    }

    public String getVlTotalMaoDeObraFormatted() {
        return CecilDecimalFormatter.getInstance().formattedValueSemSimbolo(getVlTotalMaoDeObra());
    }

    public List<ItemPecaComposta> getLstItensPecaComposta() {
        return lstItensPecaComposta;
    }

    public void setLstItensPecaComposta(List<ItemPecaComposta> lstItensPecaComposta) {
        this.lstItensPecaComposta = lstItensPecaComposta;
    }

    public List<MaoDeObraPecaComposta> getLstMaoDeObra() {
        return lstMaoDeObra;
    }

    public void setLstMaoDeObra(List<MaoDeObraPecaComposta> lstMaoDeObra) {
        this.lstMaoDeObra = lstMaoDeObra;
    }

    public List<DetalheItemPecaComposta> getLstDetalheItemPecaComposta() {
        return lstDetalheItemPecaComposta;
    }

    public void setLstDetalheItemPecaComposta(List<DetalheItemPecaComposta> lstDetalheItemPecaComposta) {
        this.lstDetalheItemPecaComposta = lstDetalheItemPecaComposta;
    }

//    public BigDecimal getVlCustoTotalGeralItens() {
//        BigDecimal valor = new BigDecimal("0");
//        for (ItemPecaComposta itemPecaComposta : getLstItensPecaComposta()) {
//            valor = valor.add(itemPecaComposta.getVlCustoTotal());
//        }
//        return valor;
//    }
//
//    public BigDecimal getVlCustoTotalGeralMaodeObra() {
//        BigDecimal valor = new BigDecimal("0");
//        for (MaoDeObraPecaComposta maoDeObraPecaComposta : getLstMaoDeObra()) {
//            valor = valor.add(maoDeObraPecaComposta.getVlCustoTotal());
//        }
//        return valor;
//    }
}
