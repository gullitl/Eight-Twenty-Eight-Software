package com.cecilsoftwares.reussoftbackend.service;

import com.cecilsoftwares.reussoftbackend.dao.ItemDispatchDao;
import com.cecilsoftwares.reussoftmiddleend.model.ItemDispatch;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class ItemDispatchService {

    private static ItemDispatchService uniqueInstance;

    public ItemDispatchService() {
    }

    public static synchronized ItemDispatchService getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ItemDispatchService();
        }
        return uniqueInstance;
    }

    public List<ItemDispatch> listerToutesLesItemsDispatch() throws ClassNotFoundException, SQLException {
        return ItemDispatchDao.getInstance().listerTousLesItemsDispatch();
    }

    public List<ItemDispatch> listerItemsDispatchParCodeDispatch(int codeDispatch) throws ClassNotFoundException, SQLException {
        return ItemDispatchDao.getInstance().listerItemsDispatchParCodeDispatch(codeDispatch);
    }

    public List<ItemDispatch> listerItemsDispatchParCodeProduit(int codeProduit) throws ClassNotFoundException, SQLException {
        return ItemDispatchDao.getInstance().listerItemsDispatchParCodeProduit(codeProduit);
    }

    public ItemDispatch selectionnerItemDispatchParCodeDispatchECodeProduit(int codeDispatch, int codeProduit) throws ClassNotFoundException, SQLException {
        return ItemDispatchDao.getInstance().selectionnerItemDispatchParCodeDispatchECodeProduit(codeDispatch, codeProduit);
    }
}
