package com.cecilsoftwares.reussoftbackend.service;

import com.cecilsoftwares.reussoftbackend.dao.CategorieProduitDao;
import com.cecilsoftwares.reussoftbackend.dao.EntreeStockDao;
import com.cecilsoftwares.reussoftmiddleend.enumarable.TypeMouvementStockEnum;
import com.cecilsoftwares.reussoftmiddleend.model.CategorieProduit;
import com.cecilsoftwares.reussoftmiddleend.model.EntreeStock;
import java.sql.SQLException;
import java.util.List;

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

    public List<EntreeStock> listerMouvementStockPatType(TypeMouvementStockEnum typeMouvementStockEnum) throws ClassNotFoundException, SQLException {
        return EntreeStockDao.getInstance().listerMouvementStockPatType(typeMouvementStockEnum);
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
