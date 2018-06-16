package com.cecilsoftwares.reussoftbackend.service;

import com.cecilsoftwares.reussoftbackend.dao.ItemEntreeStockDao;
import com.cecilsoftwares.reussoftmiddleend.model.ItemEntreeStock;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class ItemEntreeStockService {

    private static ItemEntreeStockService uniqueInstance;

    public ItemEntreeStockService() {
    }

    public static synchronized ItemEntreeStockService getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ItemEntreeStockService();
        }
        return uniqueInstance;
    }

    public List<ItemEntreeStock> listerToutesLesItemsEntreeStocks() throws ClassNotFoundException, SQLException {
        return ItemEntreeStockDao.getInstance().listerTousLesItemsEntreeStock();
    }

    public List<ItemEntreeStock> listerItemsEntreeStocksParCodeEntreeStock(int codeEntreeStock) throws ClassNotFoundException, SQLException {
        return ItemEntreeStockDao.getInstance().listerItemsEntreeStockParCodeEntreeStock(codeEntreeStock);
    }

    public List<ItemEntreeStock> listerItemsEntreeStocksParCodeProduit(int codeProduit) throws ClassNotFoundException, SQLException {
        return ItemEntreeStockDao.getInstance().listerItemsEntreeStockParCodeProduit(codeProduit);
    }

    public ItemEntreeStock selectionnerItemEntreeStockParCodeEntreeStockECodeProduit(int codeEntreeStock, int codeProduit) throws ClassNotFoundException, SQLException {
        return ItemEntreeStockDao.getInstance().selectionnerItemEntreeStockParCodeEntreeStockECodeProduit(codeEntreeStock, codeProduit);
    }

    public boolean enregistrerItemEntreeStock(ItemEntreeStock itemEntreeStock) throws ClassNotFoundException, SQLException {
        return ItemEntreeStockDao.getInstance().enregistrerItemEntreeStock(itemEntreeStock);
    }
}
