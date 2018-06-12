package com.cecilsoftwares.reussoftbackend.service;

import com.cecilsoftwares.reussoftbackend.dao.ItemEntreeStockDao;
import com.cecilsoftwares.reussoftmiddleend.model.ItemEntreeStock;
import java.sql.SQLException;

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

    public boolean enregistrerEntreeStock(ItemEntreeStock entreeStock) throws ClassNotFoundException, SQLException {
        return ItemEntreeStockDao.getInstance().enregistrerEntreeStock(entreeStock);
    }
}
