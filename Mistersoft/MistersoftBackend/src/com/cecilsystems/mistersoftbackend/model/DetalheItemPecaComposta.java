package com.cecilsystems.mistersoftbackend.model;

import java.math.BigDecimal;

/**
 * @author Plamedi L. Lusembo
 */
public class DetalheItemPecaComposta {

    private int cdPecaComposta;
    private int cdPecaSimples;
    private int cdPecaCompostaMae;
    private int cdPecaMediadora;
    private BigDecimal qtdNaPecaCompostaMae;
    private BigDecimal qtdNaPecaMediadora;

    public DetalheItemPecaComposta() {
    }

    public int getCdPecaComposta() {
        return cdPecaComposta;
    }

    public void setCdPecaComposta(int cdPecaComposta) {
        this.cdPecaComposta = cdPecaComposta;
    }

    public int getCdPecaSimples() {
        return cdPecaSimples;
    }

    public void setCdPecaSimples(int cdPecaSimples) {
        this.cdPecaSimples = cdPecaSimples;
    }

    public int getCdPecaCompostaMae() {
        return cdPecaCompostaMae;
    }

    public void setCdPecaCompostaMae(int cdPecaCompostaMae) {
        this.cdPecaCompostaMae = cdPecaCompostaMae;
    }

    public int getCdPecaMediadora() {
        return cdPecaMediadora;
    }

    public void setCdPecaMediadora(int cdPecaMediadora) {
        this.cdPecaMediadora = cdPecaMediadora;
    }

    public BigDecimal getQtdNaPecaCompostaMae() {
        return qtdNaPecaCompostaMae;
    }

    public void setQtdNaPecaCompostaMae(BigDecimal qtdNaPecaCompostaMae) {
        this.qtdNaPecaCompostaMae = qtdNaPecaCompostaMae;
    }

    public BigDecimal getQtdNaPecaMediadora() {
        return qtdNaPecaMediadora;
    }

    public void setQtdNaPecaMediadora(BigDecimal qtdNaPecaMediadora) {
        this.qtdNaPecaMediadora = qtdNaPecaMediadora;
    }

}
