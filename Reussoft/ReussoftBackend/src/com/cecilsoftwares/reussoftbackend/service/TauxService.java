package com.cecilsoftwares.reussoftbackend.service;

import com.cecilsoftwares.reussoftbackend.dao.ShopDao;
import com.cecilsoftwares.reussoftbackend.dao.TauxDao;
import com.cecilsoftwares.reussoftmiddleend.model.Shop;
import com.cecilsoftwares.reussoftmiddleend.model.TauxCarte;
import com.cecilsoftwares.reussoftmiddleend.model.TauxMonnaie;
import static com.cecilsoftwares.reussoftmiddleend.util.IdGenerator.generateId;
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

    //Taux Carte
    public List<TauxCarte> listerTousLesTauxCartes() throws ClassNotFoundException, SQLException {
        return TauxDao.getInstance().listerTousLesTauxCartes();
    }

    public TauxCarte selectionnerTauxCarteParId(String idTauxCarte) throws ClassNotFoundException, SQLException {
        return TauxDao.getInstance().selectionnerTauxCarteParId(idTauxCarte);
    }

    public List<Shop> listerTousLesShopsAvecTauxCarte() throws ClassNotFoundException, SQLException {
        return ShopDao.getInstance().listerTousLesShops();
    }

    public boolean enregistrerShopTauxCarte(Shop shopTauxCarte) throws ClassNotFoundException, SQLException, Exception {
        shopTauxCarte.getTauxCarte().setId(generateId());
        return TauxDao.getInstance().enregistrerShopTauxCarte(shopTauxCarte);
    }

    //Taux Monnaie
    public List<TauxMonnaie> listerTousLesTauxMonnaies() throws ClassNotFoundException, SQLException {
        return TauxDao.getInstance().listerTousLesTauxMonnaies();
    }

    public TauxMonnaie selectionnerTauxMonnaieParId(String idTauxMonnaie) throws ClassNotFoundException, SQLException {
        return TauxDao.getInstance().selectionnerTauxMonnaieParId(idTauxMonnaie);
    }

    public boolean sauvegarderTauxMonnaie(TauxMonnaie tauxMonnaie) throws ClassNotFoundException, SQLException {
        return TauxDao.getInstance().enregistrerTauxMonnaie(tauxMonnaie);
    }

}
