/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cecilsoftwares.reussoftbackend.service;

import com.cecilsoftwares.reussoftbackend.dao.ProduitDao;
import com.cecilsoftwares.reussoftmiddleend.model.Produit;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Plamedi L. Lusembo
 */
public class ProduitService {

    private static ProduitService uniqueInstance;

    public ProduitService() {
    }

    public static synchronized ProduitService getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ProduitService();
        }
        return uniqueInstance;
    }

    public boolean enregistrerProduit(Produit produit) throws ClassNotFoundException, SQLException {
        return ProduitDao.getInstance().enregistrerProduit(produit);
    }

    public List<Produit> listerTousLesProduits() throws ClassNotFoundException, SQLException {
        return ProduitDao.getInstance().listerTousLesProduits();
    }

    public Produit selectionnerProduitParCode(int codeProduit) throws ClassNotFoundException, SQLException {
        return ProduitDao.getInstance().selectionnerProduitParCode(codeProduit);
    }
}
