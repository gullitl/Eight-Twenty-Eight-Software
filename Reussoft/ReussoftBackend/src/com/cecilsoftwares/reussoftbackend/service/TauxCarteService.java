package com.cecilsoftwares.reussoftbackend.service;

import com.cecilsoftwares.reussoftbackend.dao.TauxCarteDao;
import com.cecilsoftwares.reussoftmiddleend.model.TauxCarte;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class TauxCarteService {

    private static TauxCarteService uniqueInstance;

    public TauxCarteService() {
    }

    public static synchronized TauxCarteService getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new TauxCarteService();
        }
        return uniqueInstance;
    }

    public List<TauxCarte> listerTousLesTauxCartes() throws ClassNotFoundException, SQLException {
        return TauxCarteDao.getInstance().listerTousLesTauxCartes();
    }

    public TauxCarte selectionnerTauxCarteParCode(int codeTauxCarte) throws ClassNotFoundException, SQLException {
        return TauxCarteDao.getInstance().selectionnerTauxCarteParCode(codeTauxCarte);
    }

    public boolean sauvegarderTauxCarte(TauxCarte sessionUtilisateur) throws ClassNotFoundException, SQLException {
        return TauxCarteDao.getInstance().sauvegarderTauxCarte(sessionUtilisateur);
    }

    public boolean actualiserTauxCarte(TauxCarte sessionUtilisateur) throws ClassNotFoundException, SQLException {
        return TauxCarteDao.getInstance().actualiserTauxCarte(sessionUtilisateur);
    }

}
