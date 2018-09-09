package com.cecilsoftwares.reussoftmiddleend.enumarable;

/**
 * @author Plamedi L. Lusembo
 */
public enum SexEnum {
    MALE(1),
    FEMALE(2);

    private final int code;

    SexEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
