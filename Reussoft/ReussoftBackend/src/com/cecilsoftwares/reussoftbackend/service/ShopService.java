/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cecilsoftwares.reussoftbackend.service;

import com.cecilsoftwares.reussoftbackend.dao.ShopDao;
import com.cecilsoftwares.reussoftmiddleend.model.Shop;
import java.sql.SQLException;

/**
 *
 * @author Plamedi L. Lusembo
 */
public class ShopService {

    private static ShopService uniqueInstance;

    public ShopService() {
    }

    public static synchronized ShopService getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ShopService();
        }
        return uniqueInstance;
    }

    public boolean enregistrerShop(Shop shop) throws ClassNotFoundException, SQLException {
        return ShopDao.getInstance().sauvegarder(shop);
    }

}
