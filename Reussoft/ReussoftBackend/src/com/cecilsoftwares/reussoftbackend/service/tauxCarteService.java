package com.cecilsoftwares.reussoftbackend.service;

import com.cecilsoftwares.reussoftbackend.dao.TauxCarteDao;
import com.cecilsoftwares.reussoftmiddleend.model.TauxCarte;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class tauxCarteService {

    private static tauxCarteService uniqueInstance;

    public tauxCarteService() {
    }

    public static synchronized tauxCarteService getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new tauxCarteService();
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
