package com.cecilsoftwares.reussoftbackend.service;

import com.cecilsoftwares.reussoftbackend.dao.ItemEntreeStockDao;
import com.cecilsoftwares.reussoftmiddleend.model.ItemEntreeStock;
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

    public List<ItemEntreeStock> listerToutesLesEntreeStocks() throws ClassNotFoundException, SQLException {
        return ItemEntreeStockDao.getInstance().listerToutesLesEntreeStocks();
    }

    public ItemEntreeStock selectionnerEntreeStockParCode(int codeEntreeStock) throws ClassNotFoundException, SQLException {
        return ItemEntreeStockDao.getInstance().selectionnerEntreeStockParCode(codeEntreeStock);
    }

    public boolean enregistrerEntreeStock(ItemEntreeStock entreeStock) throws ClassNotFoundException, SQLException {
        return ItemEntreeStockDao.getInstance().enregistrerEntreeStock(entreeStock);
    }
}
