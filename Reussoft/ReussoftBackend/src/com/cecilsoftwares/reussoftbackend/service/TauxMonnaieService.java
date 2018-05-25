package com.cecilsoftwares.reussoftbackend.service;

import com.cecilsoftwares.reussoftbackend.dao.TauxMonnaieDao;
import com.cecilsoftwares.reussoftmiddleend.model.TauxMonnaie;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class TauxMonnaieService {

    private static TauxMonnaieService uniqueInstance;

    public TauxMonnaieService() {
    }

    public static synchronized TauxMonnaieService getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new TauxMonnaieService();
        }
        return uniqueInstance;
    }

    public List<TauxMonnaie> listerTousLesTauxMonnaies() throws ClassNotFoundException, SQLException {
        return TauxMonnaieDao.getInstance().listerTousLesTauxMonnaies();
    }

    public TauxMonnaie selectionnerTauxMonnaieParCode(int codeTauxMonnaie) throws ClassNotFoundException, SQLException {
        return TauxMonnaieDao.getInstance().selectionnerTauxMonnaieParCode(codeTauxMonnaie);
    }

    public boolean sauvegarderTauxMonnaie(TauxMonnaie sessionUtilisateur) throws ClassNotFoundException, SQLException {
        return TauxMonnaieDao.getInstance().sauvegarderTauxMonnaie(sessionUtilisateur);
    }

    public boolean actualiserTauxMonnaie(TauxMonnaie sessionUtilisateur) throws ClassNotFoundException, SQLException {
        return TauxMonnaieDao.getInstance().actualiserTauxMonnaie(sessionUtilisateur);
    }

}
