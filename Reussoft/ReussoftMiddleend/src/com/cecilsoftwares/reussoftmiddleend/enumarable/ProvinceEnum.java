package com.cecilsoftwares.reussoftmiddleend.enumarable;

/**
 * @author Plamedi L. Lusembo
 */
public enum ProvinceEnum {
    BAS_UELE(1, "Bas-Uele"),
    EQUATEUR(2, "Équateur"),
    HAUT_KATANGA(3, "Haut-Katanga"),
    HAUT_LOMAMI(4, "Haut-Lomami"),
    HAUT_UELE(5, "Haut-Uele"),
    ITURI(6, "Ituri"),
    KASAI(7, "Kasaï"),
    KASAI_CENTRAL(8, "Kasaï-Central"),
    KASAI_ORIENTAL(9, "Kasaï-Oriental"),
    KINSHASA(10, "Kinshasa"),
    KONGO_CENTRAL(11, "Kongo-Central"),
    KWANGO(12, "Kwango"),
    KWILU(13, "Kwilu"),
    LOMAMI(14, "Lomami"),
    LUALABA(15, "Lualaba"),
    MAI_NDOMBE(16, "Mai-Ndombe"),
    MANIEMA(17, "Maniema"),
    MONGALA(18, "Mongala"),
    NORD_KIVU(19, "Nord-Kivu"),
    NORD_UBANGI(20, "Nord-Ubangi"),
    SANKURU(21, "Sankuru"),
    SUD_KIVU(22, "Sud-Kivu"),
    SUD_UBANGI(23, "Sud-Ubangi"),
    TANGANYIKA(24, "Tanganyika"),
    TSHOPO(25, "Tshopo"),
    TSHUAPA(26, "Tshuapa");

    private final int code;
    private final String nom;

    ProvinceEnum(int code, String nom) {
        this.code = code;
        this.nom = nom;
    }

    public int getCode() {
        return code;
    }

    public String getNom() {
        return nom;
    }

}
