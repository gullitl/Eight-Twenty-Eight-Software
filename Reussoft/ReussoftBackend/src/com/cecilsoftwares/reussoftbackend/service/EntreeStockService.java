package com.cecilsoftwares.reussoftbackend.service;

import com.cecilsoftwares.reussoftbackend.dao.EntreeStockDao;
import com.cecilsoftwares.reussoftmiddleend.model.EntreeStock;
import com.cecilsoftwares.reussoftmiddleend.model.ItemEntreeStock;
import java.sql.SQLException;
import java.util.ArrayList;
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

    public List<EntreeStock> listerTousLesEntreeStockSansItems() throws ClassNotFoundException, SQLException {
        return EntreeStockDao.getInstance().listerTousLesEntreeStockSansItems();
    }

    public List<ItemEntreeStock> listerTousLesEntreeStockAvecItems() throws ClassNotFoundException, SQLException {
        List<ItemEntreeStock> teste = EntreeStockDao.getInstance().listerTousLesEntreeStockAvecItems();
        List<ItemEntreeStock> teste2 = teste;

        for (ItemEntreeStock ies : teste) {
            int t = ies.getEntreeStock().getCode();

            for (ItemEntreeStock ies2 : teste2) {

            }

            if (t == ies.getEntreeStock().getCode()) {
                List<ItemEntreeStock> li = new ArrayList();

                EntreeStock entreeStock = ies.getEntreeStock();
            }

        }

        teste.forEach(ies -> {

        });

        return EntreeStockDao.getInstance().listerTousLesEntreeStockAvecItems();
    }

    public ItemEntreeStock selectionnerEntreeStockParCode(int codeEntreeStock) throws ClassNotFoundException, SQLException {
        return EntreeStockDao.getInstance().selectionnerEntreeStockParCode(codeEntreeStock);
    }

    public boolean enregistrerEntreeStock(EntreeStock entreeStock) throws ClassNotFoundException, SQLException {
        return EntreeStockDao.getInstance().enregistrerEntreeStock(entreeStock);
    }
}
