package com.cecilsoftwares.reussoftmiddleend.model;

/**
 * @author Plamedi L. Lusembo
 */
public class Fournisseur {

    private final int code;
    private final String entreprise;
    private final String responsable;
    private final String telephone;
    private final String observation;

    public Fournisseur(FournisseurBuilder fournisseurBuilder) {
        code = fournisseurBuilder.code;
        entreprise = fournisseurBuilder.entreprise;
        responsable = fournisseurBuilder.responsable;
        telephone = fournisseurBuilder.telephone;
        observation = fournisseurBuilder.observation;
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

    public String getTelephone() {
        return telephone;
    }

    public String getObservation() {
        return observation;
    }

    public static class FournisseurBuilder {

        private int code;
        private String entreprise;
        private String responsable;
        private String telephone;
        private String observation;

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

        public FournisseurBuilder telephone(String telephone) {
            this.telephone = telephone;
            return this;
        }

        public FournisseurBuilder observation(String observation) {
            this.observation = observation;
            return this;
        }

        public Fournisseur build() {
            return new Fournisseur(this);
        }

    }

}
