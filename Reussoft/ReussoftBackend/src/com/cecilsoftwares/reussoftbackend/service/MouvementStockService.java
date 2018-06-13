package com.cecilsoftwares.reussoftbackend.service;

import com.cecilsoftwares.reussoftbackend.dao.CategorieProduitDao;
import com.cecilsoftwares.reussoftmiddleend.model.CategorieProduit;
import java.sql.SQLException;

/**
 * @author Plamedi L. Lusembo
 */
public class MouvementStockService {

    private static MouvementStockService uniqueInstance;

    public MouvementStockService() {
    }

    public static synchronized MouvementStockService getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new MouvementStockService();
        }
        return uniqueInstance;
    }

    public CategorieProduit selectionnerCategorieProduitParCode(int codeCategorieProduit) throws ClassNotFoundException, SQLException {
        return CategorieProduitDao.getInstance().selectionnerCategorieProduitParCode(codeCategorieProduit);
    }

    public boolean enregistrerCategorieProduit(CategorieProduit categorieProduit) throws ClassNotFoundException, SQLException {
        return CategorieProduitDao.getInstance().enregistrerCategorieProduit(categorieProduit);
    }

    public boolean exclureCategorieProduit(int codeCategorieProduit) throws ClassNotFoundException, SQLException {
        return CategorieProduitDao.getInstance().exclureCategorieProduit(codeCategorieProduit);
    }

}
