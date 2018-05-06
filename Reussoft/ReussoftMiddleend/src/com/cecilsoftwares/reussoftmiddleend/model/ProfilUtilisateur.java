package com.cecilsoftwares.reussoftmiddleend.model;

/**
 * @author Plamedi L. Lusembo
 */
public class ProfilUtilisateur {

    private final int code;
    private final String description;
    private final String descriptionAbregee;
    private final String observation;

    public ProfilUtilisateur(ProfilUtilisateurBuilder profilUtilisateurBuilder) {
        code = profilUtilisateurBuilder.codeProfilUtilizateur;
        description = profilUtilisateurBuilder.description;
        descriptionAbregee = profilUtilisateurBuilder.descriptionAbregee;
        observation = profilUtilisateurBuilder.observation;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getDescriptionAbregee() {
        return descriptionAbregee;
    }

    public String getObservation() {
        return observation;
    }

    public static class ProfilUtilisateurBuilder {

        private int codeProfilUtilizateur;
        private String description;
        private String descriptionAbregee;
        private String observation;

        public ProfilUtilisateurBuilder(int codeProfilUtilizateur) {
            this.codeProfilUtilizateur = codeProfilUtilizateur;
        }

        public ProfilUtilisateurBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ProfilUtilisateurBuilder descriptionAbregee(String descriptionAbregee) {
            this.descriptionAbregee = descriptionAbregee;
            return this;
        }

        public ProfilUtilisateurBuilder observation(String observation) {
            this.observation = observation;
            return this;
        }

        public ProfilUtilisateur build() {
            return new ProfilUtilisateur(this);
        }
    }
}
