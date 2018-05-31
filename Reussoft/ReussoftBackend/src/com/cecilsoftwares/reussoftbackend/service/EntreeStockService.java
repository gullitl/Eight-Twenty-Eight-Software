package com.cecilsoftwares.reussoftbackend.service;

import com.cecilsoftwares.reussoftbackend.dao.EntreeStockDao;
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

    public List<EntreeStock> listerToutesLesEntreeStocks() throws ClassNotFoundException, SQLException {
        return EntreeStockDao.getInstance().listerToutesLesEntreeStocks();
    }

    public EntreeStock selectionnerEntreeStockParCode(int codeEntreeStock) throws ClassNotFoundException, SQLException {
        return EntreeStockDao.getInstance().selectionnerEntreeStockParCode(codeEntreeStock);
    }

    public boolean enregistrerEntreeStock(EntreeStock entreeStock) throws ClassNotFoundException, SQLException {
        return EntreeStockDao.getInstance().enregistrerEntreeStock(entreeStock);
    }
}
