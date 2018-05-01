package com.cecilsystems.mistersoftbackend.enumerable;

/**
 * @author Plamedi L. Lusembo
 */
public enum TipoPecaEnum {
    SIMPLES('S'),
    COMPOSTA('C');

    private final char tipo;

    TipoPecaEnum(char tipo) {
        this.tipo = tipo;
    }

    public char getTipo() {
        return tipo;
    }

}
