/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cecilsoftwares.reussoftbackend.service;

import com.cecilsoftwares.reussoftbackend.dao.CategorieProduitDao;
import com.cecilsoftwares.reussoftbackend.dao.ShopDao;
import com.cecilsoftwares.reussoftmiddleend.model.CategorieProduit;
import com.cecilsoftwares.reussoftmiddleend.model.Shop;
import java.sql.SQLException;

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
        return CategorieProduitDao.getInstance().sauvegarder(categorieProduit);
    }

}
