/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cecilsoftwares.reussoftbackend.service;

import com.cecilsoftwares.reussoftbackend.dao.CategorieProduitDao;
import com.cecilsoftwares.reussoftmiddleend.model.CategorieProduit;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Plamedi L. Lusembo
 */
public class CategorieProduitService {

    private static CategorieProduitService uniqueInstance;

    public CategorieProduitService() {
    }

    public static synchronized CategorieProduitService getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new CategorieProduitService();
        }
        return uniqueInstance;
    }

    public boolean enregistrerCategorieProduit(CategorieProduit categorieProduit) throws ClassNotFoundException, SQLException {
        return CategorieProduitDao.getInstance().enregistrerCategorieProduit(categorieProduit);
    }

    public List<CategorieProduit> listerTousLesCategorieProduits() throws ClassNotFoundException, SQLException {
        return CategorieProduitDao.getInstance().listerTousLesCategorieProduits();
    }

    public CategorieProduit selectionnerCategorieProduitParCode(int codeCategorieProduit) throws ClassNotFoundException, SQLException {
        return CategorieProduitDao.getInstance().selectionnerCategorieProduitParCode(codeCategorieProduit);
    }
}
