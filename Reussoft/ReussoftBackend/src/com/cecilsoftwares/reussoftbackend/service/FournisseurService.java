/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cecilsoftwares.reussoftbackend.service;

import com.cecilsoftwares.reussoftbackend.dao.FournisseurDao;
import com.cecilsoftwares.reussoftmiddleend.model.Fournisseur;
import java.sql.SQLException;

/**
 *
 * @author Plamedi L. Lusembo
 */
public class FournisseurService {

    private static FournisseurService uniqueInstance;

    public FournisseurService() {
    }

    public static synchronized FournisseurService getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new FournisseurService();
        }
        return uniqueInstance;
    }

    public boolean enregistrerFournisseur(Fournisseur fournisseur) throws ClassNotFoundException, SQLException {
        return FournisseurDao.getInstance().enregistrerFournisseur(fournisseur);
    }

}
