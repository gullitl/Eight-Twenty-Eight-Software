package com.cecilsoftwares.reussoftbackend.model;

import java.util.Date;

/**
 * @author Plamedi L. Lusembo
 */
public class SessionUtilisateur {

    private final int codeSessionUtilisateur;
    private final Collaborateur collaborateur;
    private final Shop idShop;
    private final String log;
    private final Date dateHeure;

    public SessionUtilisateur(SessionUtilisateurBuilder sessionUtilisateurBuilder) {
        codeSessionUtilisateur = sessionUtilisateurBuilder.codeSessionUtilisateur;
        collaborateur = sessionUtilisateurBuilder.collaborateur;
        idShop = sessionUtilisateurBuilder.idShop;
        log = sessionUtilisateurBuilder.log;
        dateHeure = sessionUtilisateurBuilder.dateHeure;
    }

    public int getCodeSessionUtilisateur() {
        return codeSessionUtilisateur;
    }

    public Collaborateur getCollaborateur() {
        return collaborateur;
    }

    public Shop getIdShop() {
        return idShop;
    }

    public String getLog() {
        return log;
    }

    public Date getDateHeure() {
        return dateHeure;
    }

    public static class SessionUtilisateurBuilder {

        private int codeSessionUtilisateur;
        private Collaborateur collaborateur;
        private Shop idShop;
        private String log;
        private Date dateHeure;

        public SessionUtilisateurBuilder(int codeSessionUtilisateur) {
            this.codeSessionUtilisateur = codeSessionUtilisateur;
        }

        public SessionUtilisateurBuilder collaborateur(Collaborateur collaborateur) {
            this.collaborateur = collaborateur;
            return this;
        }

        public SessionUtilisateurBuilder idShop(Shop idShop) {
            this.idShop = idShop;
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

        public SessionUtilisateur build() {
            return new SessionUtilisateur(this);
        }

    }

}
