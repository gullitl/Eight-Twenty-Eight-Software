package com.cecilsoftwares.reussoftbackend.service;

import com.cecilsoftwares.reussoftbackend.dao.TauxDao;
import com.cecilsoftwares.reussoftmiddleend.model.Shop;
import com.cecilsoftwares.reussoftmiddleend.model.TauxCarte;
import com.cecilsoftwares.reussoftmiddleend.model.TauxMonnaie;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class TauxService {

    private static TauxService uniqueInstance;

    public TauxService() {
    }

    public static synchronized TauxService getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new TauxService();
        }
        return uniqueInstance;
    }

    public List<TauxCarte> listerTousLesTauxCartesActuelParShop() throws ClassNotFoundException, SQLException {
        return TauxDao.getInstance().listerTousLesTauxCartesActuelParShop();
    }

    public List<TauxMonnaie> listerTousLesTauxMonnaiesActuelParShop() throws ClassNotFoundException, SQLException {
        return TauxDao.getInstance().listerTousLesTauxMonnaiesActuelParShop();
    }

    public TauxCarte selectionnerTauxCarteParId(String idTauxCarte) throws ClassNotFoundException, SQLException {
        return TauxDao.getInstance().selectionnerTauxCarteParId(idTauxCarte);
    }

    public TauxMonnaie selectionnerTauxMonnaieParId(String idTauxMonnaie) throws ClassNotFoundException, SQLException {
        return TauxDao.getInstance().selectionnerTauxMonnaieParId(idTauxMonnaie);
    }

    public boolean sauvegarderTauxCarte(TauxCarte tauxCarte) throws ClassNotFoundException, SQLException {
        return TauxDao.getInstance().sauvegarderTauxCarte(tauxCarte);
    }

    public boolean sauvegarderTauxMonnaie(TauxMonnaie sessionUtilisateur) throws ClassNotFoundException, SQLException {
        return TauxDao.getInstance().sauvegarderTauxMonnaie(sessionUtilisateur);
    }

    public TauxCarte selectionnerDerniersTauxCarteEnDate(Shop shop) throws ClassNotFoundException, SQLException {
        return TauxDao.getInstance().selectionnerDerniersTauxCarteEnDate(shop);
    }

    public TauxMonnaie selectionnerDerniersTauxMonnaieEnDate() throws ClassNotFoundException, SQLException {
        return TauxDao.getInstance().selectionnerDerniersTauxMonnaieEnDate();
    }

}
