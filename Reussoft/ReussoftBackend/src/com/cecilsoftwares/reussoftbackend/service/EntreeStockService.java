package com.cecilsoftwares.reussoftbackend.service;

import com.cecilsoftwares.reussoftbackend.dao.EntreeStockDao;
import com.cecilsoftwares.reussoftbackend.util.IdGenerator;
import com.cecilsoftwares.reussoftmiddleend.model.EntreeStock;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class EntreeStockService {

    private static EntreeStockService uniqueInstance;

    public EntreeStockService() {
    }

    public static synchronized EntreeStockService getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new EntreeStockService();
        }
        return uniqueInstance;
    }

    public List<EntreeStock> listerTousLesEntreesStockSansItems() throws ClassNotFoundException, SQLException {
        return EntreeStockDao.getInstance().listerTousLesEntreesStockSansItems();
    }

    public List<EntreeStock> listerTousLesEntreesStockAvecItems() throws ClassNotFoundException, SQLException {
        return EntreeStockDao.getInstance().listerTousLesEntreesStockAvecItems();
    }

    public EntreeStock selectionnerEntreeStockParId(String idEntreeStock) throws ClassNotFoundException, SQLException {
        return EntreeStockDao.getInstance().selectionnerEntreeStockParId(idEntreeStock);
    }

    public boolean enregistrerEntreeStock(EntreeStock entreeStock) throws ClassNotFoundException, SQLException, Exception {
        if (entreeStock.getId().isEmpty()) {
            entreeStock.setId(IdGenerator.generateId());
            entreeStock.setNumeroEntreeStock(IdGenerator.generateOperationNumber());
            return EntreeStockDao.getInstance().enregistrerEntreeStock(entreeStock);
        } else {
            return EntreeStockDao.getInstance().actualiserEntreeStock(entreeStock);
        }

    }
}
