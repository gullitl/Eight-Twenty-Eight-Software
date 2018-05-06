package com.cecilsoftwares.reussoftmiddleend.model;

import java.util.Date;

/**
 * @author Plamedi L. Lusembo
 */
public class SessionUtilisateur {

    private final int code;
    private final Collaborateur collaborateur;
    private final Shop shop;
    private final String log;
    private final Date dateHeure;
    private final String observation;

    public SessionUtilisateur(SessionUtilisateurBuilder sessionUtilisateurBuilder) {
        code = sessionUtilisateurBuilder.code;
        collaborateur = sessionUtilisateurBuilder.collaborateur;
        shop = sessionUtilisateurBuilder.shop;
        log = sessionUtilisateurBuilder.log;
        dateHeure = sessionUtilisateurBuilder.dateHeure;
        observation = sessionUtilisateurBuilder.observation;
    }

    public int getCode() {
        return code;
    }

    public Collaborateur getCollaborateur() {
        return collaborateur;
    }

    public Shop getShop() {
        return shop;
    }

    public String getLog() {
        return log;
    }

    public Date getDateHeure() {
        return dateHeure;
    }

    public String getObservation() {
        return observation;
    }

    public static class SessionUtilisateurBuilder {

        private int code;
        private Collaborateur collaborateur;
        private Shop shop;
        private String log;
        private Date dateHeure;
        private String observation;

        public SessionUtilisateurBuilder(int code) {
            this.code = code;
        }

        public SessionUtilisateurBuilder collaborateur(Collaborateur collaborateur) {
            this.collaborateur = collaborateur;
            return this;
        }

        public SessionUtilisateurBuilder shop(Shop shop) {
            this.shop = shop;
            return this;
        }

        public SessionUtilisateurBuilder log(String log) {
            this.log = log;
            return this;
        }

        public SessionUtilisateurBuilder dateHeure(Date dateHeure) {
            this.dateHeure = dateHeure;
            return this;
        }

        public SessionUtilisateurBuilder observation(String observation) {
            this.observation = observation;
            return this;
        }

        public SessionUtilisateur build() {
            return new SessionUtilisateur(this);
        }

    }

}
