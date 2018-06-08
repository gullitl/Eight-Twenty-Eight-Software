package com.cecilsoftwares.reussoftmiddleend.enumarable;

/**
 * @author Plamedi L. Lusembo
 */
public enum TypeMouvementStockEnum {
    BAS_UELE(1, "Entreé Stock"),
    EQUATEUR(2, "Sortie Stock"),
    HAUT_KATANGA(3, "Dispatch");

    private final int code;
    private final String decription;

    TypeMouvementStockEnum(int code, String decription) {
        this.code = code;
        this.decription = decription;
    }

    public int getCode() {
        return code;
    }

    public String getDecription() {
        return decription;
    }

}
