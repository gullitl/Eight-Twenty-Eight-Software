package com.cecilsoftwares.reussoftbackend.service;

import com.cecilsoftwares.reussoftbackend.dao.DispatchDao;
import com.cecilsoftwares.reussoftmiddleend.model.Dispatch;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class DispatchService {

    private static DispatchService uniqueInstance;

    public DispatchService() {
    }

    public static synchronized DispatchService getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new DispatchService();
        }
        return uniqueInstance;
    }

    public List<Dispatch> listerTousLesDispatchsSansItems() throws ClassNotFoundException, SQLException {
        return DispatchDao.getInstance().listerTousLesDispatchsSansItems();
    }

    public List<Dispatch> listerTousLesDispatchsAvecItems() throws ClassNotFoundException, SQLException {
        return DispatchDao.getInstance().listerTousLesDispatchsAvecItems();
    }

    public Dispatch selectionnerDispatchParCode(int codeDispatch) throws ClassNotFoundException, SQLException {
        return DispatchDao.getInstance().selectionnerDispatchParCode(codeDispatch);
    }

    public boolean enregistrerDispatch(Dispatch sortieStock) throws ClassNotFoundException, SQLException {
        return DispatchDao.getInstance().enregistrerDispatch(sortieStock);
    }
}
