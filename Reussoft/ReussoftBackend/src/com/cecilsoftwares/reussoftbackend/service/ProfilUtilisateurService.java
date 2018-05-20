/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cecilsoftwares.reussoftbackend.service;

import com.cecilsoftwares.reussoftbackend.dao.ProfilUtilisateurDao;
import com.cecilsoftwares.reussoftmiddleend.model.ProfilUtilisateur;
import java.sql.SQLException;

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

    public boolean enregistrerProfilUtilisateur(ProfilUtilisateur profilUtilisateur) throws ClassNotFoundException, SQLException {
        return ProfilUtilisateurDao.getInstance().enregistrerProfilUtilisateur(profilUtilisateur);
    }

}
