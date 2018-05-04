package com.cecilsoftwares.reussoftbackend.model;

/**
 * @author Plamedi L. Lusembo
 */
public class Fournisseur {

    private final int code;
    private final String entreprise;
    private final String responsable;

    public Fournisseur(FournisseurBuilder fournisseurBuilder) {
        code = fournisseurBuilder.code;
        entreprise = fournisseurBuilder.entreprise;
        responsable = fournisseurBuilder.responsable;
    }

    public int getCode() {
        return code;
    }

    public String getEntreprise() {
        return entreprise;
    }

    public String getResponsable() {
        return responsable;
    }

    public static class FournisseurBuilder {

        private int code;
        private String entreprise;
        private String responsable;

        public FournisseurBuilder(int code) {
            this.code = code;
        }

        public FournisseurBuilder entreprise(String entreprise) {
            this.entreprise = entreprise;
            return this;
        }

        public FournisseurBuilder responsable(String responsable) {
            this.responsable = responsable;
            return this;
        }

        public Fournisseur build() {
            return new Fournisseur(this);
        }

    }

}
