/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cecilsoftwares.reussoftbackend.service;

import com.cecilsoftwares.reussoftbackend.dao.ClientDao;
import com.cecilsoftwares.reussoftbackend.dao.ShopDao;
import com.cecilsoftwares.reussoftmiddleend.model.Client;
import com.cecilsoftwares.reussoftmiddleend.model.Shop;
import java.sql.SQLException;

/**
 *
 * @author Plamedi L. Lusembo
 */
public class ClientService {

    private static ClientService uniqueInstance;

    public ClientService() {
    }

    public static synchronized ClientService getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ClientService();
        }
        return uniqueInstance;
    }

    public boolean enregistrerClient(Client client) throws ClassNotFoundException, SQLException {
        return ClientDao.getInstance().sauvegarder(client);
    }

}
