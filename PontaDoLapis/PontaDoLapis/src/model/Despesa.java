package model;

import java.math.BigDecimal;
import java.util.Date;
import util.DespesaEnum;

/**
 * @author Plamedi Luzolo Lusembo
 */
public class Despesa {

    private String id;
    private Date dataPagamentoDespesa;
    private DespesaEnum despesaEnum;
    private TipoDespesa tipoDespesa;
    private BigDecimal valorPago;

    public Despesa() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDataPagamentoDespesa() {
        return dataPagamentoDespesa;
    }

    public void setDataPagamentoDespesa(Date dataPagamentoDespesa) {
        this.dataPagamentoDespesa = dataPagamentoDespesa;
    }

    public DespesaEnum getDespesaEnum() {
        return despesaEnum;
    }

    public void setDespesaEnum(DespesaEnum despesaEnum) {
        this.despesaEnum = despesaEnum;
    }

    public TipoDespesa getTipoDespesa() {
        return tipoDespesa;
    }

    public void setTipoDespesa(TipoDespesa tipoDespesa) {
        this.tipoDespesa = tipoDespesa;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    public static class DespesaBuilder {

        private final String id;
        private Date dataPagamentoDespesa;
        private DespesaEnum despesaEnum;
        private TipoDespesa tipoDespesa;
        private BigDecimal valorPago;

        public DespesaBuilder(String id) {
            this.id = id;
        }

        public DespesaBuilder withDataPagamentoDespesa(Date dataPagamentoDespesa) {
            this.dataPagamentoDespesa = dataPagamentoDespesa;
            return this;
        }

        public DespesaBuilder withDespesaEnum(DespesaEnum despesaEnum) {
            this.despesaEnum = despesaEnum;
            return this;
        }

        public DespesaBuilder withTipoDespesa(TipoDespesa tipoDespesa) {
            this.tipoDespesa = tipoDespesa;
            return this;
        }

        public DespesaBuilder withValorPago(BigDecimal valorPago) {
            this.valorPago = valorPago;
            return this;
        }

        public Despesa create() {
            Despesa despesa = new Despesa();
            despesa.setId(this.id);
            despesa.setDataPagamentoDespesa(this.dataPagamentoDespesa);
            despesa.setTipoDespesa(this.tipoDespesa);
            despesa.setValorPago(this.valorPago);

            return despesa;

        }

    }

}
