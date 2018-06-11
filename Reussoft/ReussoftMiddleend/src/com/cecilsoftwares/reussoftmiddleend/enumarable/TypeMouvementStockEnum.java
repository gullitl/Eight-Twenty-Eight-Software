package com.cecilsoftwares.reussoftmiddleend.enumarable;

/**
 * @author Plamedi L. Lusembo
 */
public enum TypeMouvementStockEnum {
    ENTREE_STOCK(1, "Entreé Stock"),
    SORTIE_STOCK(2, "Sortie Stock"),
    DISPATCH(3, "Dispatch");

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
