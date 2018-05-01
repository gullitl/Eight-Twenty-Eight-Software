package com.cecilsystems.mistersoftbackend.model;

/**
 * @author Plamedi L. Lusembo
 */
public class Versao {

    private int cdVersao;
    private int xmaior;
    private int ymenor;
    private int zcorrecao;
    private String dsVersao;
    private int cdVersaoAnterior;
    private String observacao;

    public Versao() {
    }

    public Versao(int xmaior, int ymenor, int zcorrecao, String dsVersao) {
        this.xmaior = xmaior;
        this.ymenor = ymenor;
        this.zcorrecao = zcorrecao;
        this.dsVersao = dsVersao;
    }

    public Versao(int xmaior, int ymenor, int zxorrecao, String dsVersao, int nrVersao, int cdVersaoAnterior) {
        this.xmaior = xmaior;
        this.ymenor = ymenor;
        this.xmaior = zcorrecao;
        this.cdVersaoAnterior = cdVersaoAnterior;
        this.dsVersao = dsVersao;
    }

    public int getCdVersao() {
        return cdVersao;
    }

    public void setCdVersao(int cdVersao) {
        this.cdVersao = cdVersao;
    }

    public int getXmaior() {
        return xmaior;
    }

    public void setXmaior(int xmaior) {
        this.xmaior = xmaior;
    }

    public int getYmenor() {
        return ymenor;
    }

    public void setYmenor(int ymenor) {
        this.ymenor = ymenor;
    }

    public int getZcorrecao() {
        return zcorrecao;
    }

    public void setZcorrecao(int zcorrecao) {
        this.zcorrecao = zcorrecao;
    }

    public String getDsVersao() {
        return dsVersao;
    }

    public void setDsVersao(String dsVersao) {
        this.dsVersao = dsVersao;
    }

    public int getCdVersaoAnterior() {
        return cdVersaoAnterior;
    }

    public void setCdVersaoAnterior(int cdVersaoAnterior) {
        this.cdVersaoAnterior = cdVersaoAnterior;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Versao)) {
            return false;
        }
        Versao other = (Versao) object;
        return this.getXmaior() == other.getXmaior()
                && this.getYmenor() == other.getYmenor()
                && this.getZcorrecao() == other.getZcorrecao()
                && this.getDsVersao().toLowerCase().equals(other.getDsVersao().toLowerCase());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.cdVersao;
        hash = 97 * hash + this.xmaior;
        hash = 97 * hash + this.ymenor;
        hash = 97 * hash + this.zcorrecao;
        return hash;
    }

    @Override
    public String toString() {
        if (getDsVersao().isEmpty()) {
            return getXmaior() + "." + getYmenor() + "." + getZcorrecao();
        } else {
            return getXmaior() + "." + getYmenor() + "." + getZcorrecao() + " " + getDsVersao();
        }
    }
}
