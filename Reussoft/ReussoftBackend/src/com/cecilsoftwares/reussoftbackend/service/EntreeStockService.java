/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cecilsoftwares.reussoftbackend.service;

import com.cecilsoftwares.reussoftbackend.dao.EntreeStockDao;
import com.cecilsoftwares.reussoftmiddleend.model.EntreeStock;
import java.sql.SQLException;

/**
 *
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

    public boolean enregistrerEntreeStock(EntreeStock entreeStock) throws ClassNotFoundException, SQLException {
        return EntreeStockDao.getInstance().enregistrerEntreeStock(entreeStock);
    }

}
