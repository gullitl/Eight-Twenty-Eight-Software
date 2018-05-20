/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cecilsoftwares.reussoftbackend.service;

import com.cecilsoftwares.reussoftbackend.dao.UtilisateurDao;
import com.cecilsoftwares.reussoftmiddleend.model.Utilisateur;
import java.sql.SQLException;

/**
 *
 * @author Plamedi L. Lusembo
 */
public class UtilisateurService {

    private static UtilisateurService uniqueInstance;

    public UtilisateurService() {
    }

    public static synchronized UtilisateurService getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new UtilisateurService();
        }
        return uniqueInstance;
    }

    public boolean enregistrerUtilisateur(Utilisateur utilisateur)
            throws ClassNotFoundException, SQLException {
        return UtilisateurDao.getInstance().enregistrerUtilisateur(utilisateur);
    }

    public Utilisateur consulterUtilisateurParCode(int codeUtilisateur)
            throws ClassNotFoundException, SQLException {
        return UtilisateurDao.getInstance().consulterUtilisateurParCode(codeUtilisateur);
    }

}
