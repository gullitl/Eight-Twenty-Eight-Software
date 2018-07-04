package com.cecilsoftwares.reussoftmiddleend.model;

import java.util.Date;

/**
 * @author Plamedi L. Lusembo
 */
public class SessionUtilisateur {

    private int code;
    private Collaborateur collaborateur;
    private boolean actionEntree;
    private Date dateHeure;

    public SessionUtilisateur() {

    }

    public SessionUtilisateur(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Collaborateur getCollaborateur() {
        return collaborateur;
    }

    public void setCollaborateur(Collaborateur collaborateur) {
        this.collaborateur = collaborateur;
    }

    public boolean isActionEntree() {
        return actionEntree;
    }

    public void setActionEntree(boolean actionEntree) {
        this.actionEntree = actionEntree;
    }

    public Date getDateHeure() {
        return dateHeure;
    }

    public void setDateHeure(Date dateHeure) {
        this.dateHeure = dateHeure;
    }

}
