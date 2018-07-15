package com.cecilsoftwares.reussoftbackend.service;

import com.cecilsoftwares.reussoftbackend.dao.SortieStockDao;
import com.cecilsoftwares.reussoftmiddleend.model.SortieStock;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class SortieStockService {

    private static SortieStockService uniqueInstance;

    public SortieStockService() {
    }

    public static synchronized SortieStockService getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new SortieStockService();
        }
        return uniqueInstance;
    }

    public List<SortieStock> listerToutesLesSortiesStockSansItems() throws ClassNotFoundException, SQLException {
        return SortieStockDao.getInstance().listerToutesLesSortiesStockSansItems();
    }

    public List<SortieStock> listerToutesLesSortiesStockAvecItems() throws ClassNotFoundException, SQLException {
        return SortieStockDao.getInstance().listerToutesLesSortiesStockAvecItems();
    }

    public SortieStock selectionnerSortieStockParCode(int codeSortieStock) throws ClassNotFoundException, SQLException {
        return SortieStockDao.getInstance().selectionnerSortieStockParId(codeSortieStock);
    }

    public boolean enregistrerSortieStock(SortieStock sortieStock) throws ClassNotFoundException, SQLException {
        return SortieStockDao.getInstance().enregistrerSortieStock(sortieStock);
    }
}
