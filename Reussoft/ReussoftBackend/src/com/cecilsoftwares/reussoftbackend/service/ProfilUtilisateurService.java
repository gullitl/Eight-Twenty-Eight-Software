/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cecilsoftwares.reussoftbackend.service;

import com.cecilsoftwares.reussoftbackend.dao.ProfilUtilisateurDao;
import com.cecilsoftwares.reussoftmiddleend.model.ProfilUtilisateur;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Plamedi L. Lusembo
 */
public class ProfilUtilisateurService {

    private static ProfilUtilisateurService uniqueInstance;

    public ProfilUtilisateurService() {
    }

    public static synchronized ProfilUtilisateurService getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ProfilUtilisateurService();
        }
        return uniqueInstance;
    }

    public List<ProfilUtilisateur> listerTousLesProfilUtilisateurs() throws ClassNotFoundException, SQLException {
        return ProfilUtilisateurDao.getInstance().listerTousLesProfilUtilisateurs();
    }

    public ProfilUtilisateur selectionnerProfilUtilisateurParCode(int codeProfilUtilisateur) throws ClassNotFoundException, SQLException {
        return ProfilUtilisateurDao.getInstance().selectionnerProfilUtilisateurParCode(codeProfilUtilisateur);
    }

    public boolean enregistrerProfilUtilisateur(ProfilUtilisateur profilUtilisateur) throws ClassNotFoundException, SQLException {
        return ProfilUtilisateurDao.getInstance().enregistrerProfilUtilisateur(profilUtilisateur);
    }
}
