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

    public List<ItemEntreeStock> listerItemsEntreeStockParIdEntreeStock(String idEntreeStock) throws ClassNotFoundException, SQLException {
        return ItemEntreeStockDao.getInstance().listerItemsEntreeStockParIdEntreeStock(idEntreeStock);
    }

    public List<ItemEntreeStock> listerItemsEntreeStocksParIdProduit(String idProduit) throws ClassNotFoundException, SQLException {
        return ItemEntreeStockDao.getInstance().listerItemsEntreeStockParIdProduit(idProduit);
    }

    public ItemEntreeStock selectionnerItemEntreeStockParIdEntreeStockEIdProduit(String idEntreeStock, String idProduit)
            throws ClassNotFoundException, SQLException {
        return ItemEntreeStockDao.getInstance().selectionnerItemEntreeStockParIdEntreeStockEIdProduit(idEntreeStock, idProduit);
    }

}
