package com.cecilsoftwares.reussoftbackend.model;

import java.util.Date;

/**
 * @author Plamedi L. Lusembo
 */
public class SessionUtilisateur {

    private int codeSessionUtilisateur;
    private Collaborateur idCollaborateur;
    private Shop idShop;
    private String log;
    private Date dateHeure;

    public SessionUtilisateur(SessionUtilisateurBuilder sessionUtilisateurBuilder) {
        codeSessionUtilisateur = sessionUtilisateurBuilder.codeSessionUtilisateur;
        idCollaborateur = sessionUtilisateurBuilder.idCollaborateur;
        idShop = sessionUtilisateurBuilder.idShop;
        log = sessionUtilisateurBuilder.log;
        dateHeure = sessionUtilisateurBuilder.dateHeure;
    }

    public int getCodeSessionUtilisateur() {
        return codeSessionUtilisateur;
    }

    public Collaborateur getIdCollaborateur() {
        return idCollaborateur;
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
        private Collaborateur idCollaborateur;
        private Shop idShop;
        private String log;
        private Date dateHeure;

        public SessionUtilisateurBuilder(int codeSessionUtilisateur) {
            this.codeSessionUtilisateur = codeSessionUtilisateur;
        }

        public SessionUtilisateurBuilder idCollaborateur(Collaborateur idCollaborateur) {
            this.idCollaborateur = idCollaborateur;
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

    }

}
