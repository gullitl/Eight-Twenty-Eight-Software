package model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Plamedi Luzolo Lusembo
 */
public class Rendimento {

    private String id;
    private TipoRendimento tipoRendimento;
    private Date dataRecebimento;
    private BigDecimal valorRecebido;

    public Rendimento() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TipoRendimento getTipoRendimento() {
        return tipoRendimento;
    }

    public void setTipoRendimento(TipoRendimento tipoRendimento) {
        this.tipoRendimento = tipoRendimento;
    }

    public Date getDataRecebimento() {
        return dataRecebimento;
    }

    public void setDataRecebimento(Date dataRecebimento) {
        this.dataRecebimento = dataRecebimento;
    }

    public BigDecimal getValorRecebido() {
        return valorRecebido;
    }

    public void setValorRecebido(BigDecimal valorRecebido) {
        this.valorRecebido = valorRecebido;
    }

    public static class RendimentoBuilder {

        private final String id;
        private TipoRendimento tipoRendimento;
        private Date dataRecebimento;
        private BigDecimal valorRecebido;

        public RendimentoBuilder(String id) {
            this.id = id;
        }

        public RendimentoBuilder withTipoRendimento(TipoRendimento tipoRendimento) {
            this.tipoRendimento = tipoRendimento;
            return this;
        }

        public RendimentoBuilder withDataRecebimento(Date dataRecebimento) {
            this.dataRecebimento = dataRecebimento;
            return this;
        }

        public RendimentoBuilder withValor(BigDecimal valorRecebido) {
            this.valorRecebido = valorRecebido;
            return this;
        }

        public Rendimento create() {
            Rendimento rendimento = new Rendimento();
            rendimento.setId(this.id);
            rendimento.setTipoRendimento(this.tipoRendimento);
            rendimento.setDataRecebimento(this.dataRecebimento);
            rendimento.setValorRecebido(this.valorRecebido);
            return rendimento;
        }

    }

}
