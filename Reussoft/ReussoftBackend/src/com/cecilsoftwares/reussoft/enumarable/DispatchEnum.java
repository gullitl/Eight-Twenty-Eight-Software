package com.cecilsoftwares.reussoft.enumarable;

/**
 * @author Plamedi L. Lusembo
 */
public enum DispatchEnum {

    VRAI('V'),
    FAUX('F');

    private final char type;

    DispatchEnum(char type) {
        this.type = type;
    }

    public char getType() {
        return type;
    }
}
