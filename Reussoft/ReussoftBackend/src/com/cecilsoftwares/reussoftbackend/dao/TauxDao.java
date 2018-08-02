package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoftmiddleend.model.Shop;
import com.cecilsoftwares.reussoftmiddleend.model.TauxCarte;
import com.cecilsoftwares.reussoftmiddleend.model.TauxMonnaie;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class TauxDao {

    private StringBuilder scriptSQL;
    private static TauxDao uniqueInstance;

    public TauxDao() {
    }

    public static synchronized TauxDao getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new TauxDao();
        }
        return uniqueInstance;
    }

    //Taux Carte
    public List<TauxCarte> listerTousLesTauxCartes() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<TauxCarte> listeTauxCartes;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            listeTauxCartes = new ArrayList();

            scriptSQL = new StringBuilder("SELECT tauxcarte.id, tauxcarte.dateHeure, tauxcarte.valeur,");
            scriptSQL.append(" tauxcarte.idShop, shop.nom, shop.adresse");
            scriptSQL.append(" FROM tauxcarte");
            scriptSQL.append(" LEFT JOIN shop ON tauxcarte.idShop = shop.id");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {

                    TauxCarte tauxCarte = new TauxCarte(res.getString(1));
                    tauxCarte.setDateHeure(res.getTimestamp(2));
                    tauxCarte.setValeur(new BigDecimal(res.getString(3)));

                    Shop shop = new Shop(res.getString(4));
                    shop.setNom(res.getString(5));
                    shop.setAdresse(res.getString(6));
                    tauxCarte.setShop(shop);

                    listeTauxCartes.add(tauxCarte);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listeTauxCartes;
    }

    public TauxCarte selectionnerTauxCarteParId(String idTauxCarte) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {

            scriptSQL = new StringBuilder("SELECT tauxcarte.id, tauxcarte.dateHeure, tauxcarte.valeur,");
            scriptSQL.append(" tauxcarte.idShop, shop.nom, shop.adresse");
            scriptSQL.append(" FROM tauxcarte");
            scriptSQL.append(" LEFT JOIN shop ON tauxcarte.idShop = shop.id");
            scriptSQL.append(" WHERE tauxcarte.id=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setString(1, idTauxCarte);
            res = prs.executeQuery();
            if (res != null) {
                if (res.next()) {

                    TauxCarte tauxCarte = new TauxCarte(res.getString(1));
                    tauxCarte.setDateHeure(res.getTimestamp(2));
                    tauxCarte.setValeur(new BigDecimal(res.getString(3)));

                    Shop shop = new Shop(res.getString(4));
                    shop.setNom(res.getString(5));
                    shop.setAdresse(res.getString(6));
                    tauxCarte.setShop(shop);

                    prs.close();
                    res.close();
                    conexao.close();

                    return tauxCarte;
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return null;
    }

    public boolean sauvegarderTauxCarte(List<TauxCarte> listeTauxCarte) throws ClassNotFoundException, SQLException {
        PreparedStatement prs = null;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {

            for (TauxCarte tauxCarte : listeTauxCarte) {

                scriptSQL = new StringBuilder("INSERT INTO tauxcarte(");
                scriptSQL.append(" dateHeure, valeur, idShop, id, idShop )");
                scriptSQL.append(" VALUES (?, ?, ?, ?, ?, ?)");

                prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

                prs.setTimestamp(1, new Timestamp(tauxCarte.getDateHeure().getTime()));
                prs.setBigDecimal(2, tauxCarte.getValeur());
                prs.setString(3, tauxCarte.getShop().getId());
                prs.setString(4, tauxCarte.getId());
                prs.setString(5, tauxCarte.getShop().getId());

                prs.execute();

                scriptSQL = new StringBuilder("UPDATE shop");
                scriptSQL.append(" SET idTauxCarte=?");
                scriptSQL.append(" WHERE id=?");

                prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

                prs.setString(1, tauxCarte.getId());
                prs.setString(2, tauxCarte.getShop().getId());

                prs.execute();

            }

            prs.close();
            conexao.close();
        }
        return true;
    }

    public boolean actualiserTauxCarte(TauxCarte tauxCarte) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {

            scriptSQL = new StringBuilder("UPDATE sessionutilisateur");
            scriptSQL.append(" SET dateHeure=?, valeur=?, idShop=?");
            scriptSQL.append(" WHERE id=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setTimestamp(1, new Timestamp(tauxCarte.getDateHeure().getTime()));
            prs.setBigDecimal(2, tauxCarte.getValeur());
            prs.setString(3, tauxCarte.getShop().getId());
            prs.setString(4, tauxCarte.getId());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

    public TauxCarte selectionnerDerniersTauxCarteShopEnDate() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {

            scriptSQL = new StringBuilder("SELECT Max(tauxcarte.dateHeure), tauxcarte.id, tauxcarte.valeur,");
            scriptSQL.append(" tauxcarte.idShop, shop.nom, shop.adresse");
            scriptSQL.append(" FROM tauxcarte");
            scriptSQL.append(" LEFT JOIN shop ON tauxcarte.idShop = shop.id");
            scriptSQL.append(" WHERE tauxcarte.idShop=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
//            prs.setString(1, shop.getId());
            res = prs.executeQuery();
            if (res != null) {
                if (res.next()) {

                    TauxCarte tauxCarte = new TauxCarte(res.getString(1));
                    tauxCarte.setDateHeure(res.getTimestamp(2));
                    tauxCarte.setValeur(new BigDecimal(res.getString(3)));

                    Shop shp = new Shop(res.getString(4));
                    shp.setNom(res.getString(5));
                    shp.setAdresse(res.getString(6));
                    tauxCarte.setShop(shp);

                    prs.close();
                    res.close();
                    conexao.close();

                    return tauxCarte;
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return null;
    }

    //Taux Monnaie
    public List<TauxMonnaie> listerTousLesTauxMonnaies() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<TauxMonnaie> listeTauxMonnaies;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            listeTauxMonnaies = new ArrayList();

            scriptSQL = new StringBuilder("SELECT tauxmonnaie.id, tauxmonnaie.dateHeure, tauxmonnaie.valeur,");
            scriptSQL.append(" FROM tauxmonnaie");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {

                    TauxMonnaie tauxMonnaie = new TauxMonnaie(res.getString(1));
                    tauxMonnaie.setDateHeure(res.getTimestamp(2));
                    tauxMonnaie.setValeur(new BigDecimal(res.getString(3)));

                    listeTauxMonnaies.add(tauxMonnaie);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listeTauxMonnaies;
    }

    public TauxMonnaie selectionnerTauxMonnaieParId(String idTauxMonnaie) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {

            scriptSQL = new StringBuilder("SELECT tauxmonnaie.id, tauxmonnaie.dateHeure, tauxmonnaie.valeur,");
            scriptSQL.append(" FROM tauxmonnaie");
            scriptSQL.append(" WHERE tauxmonnaie.id=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setString(1, idTauxMonnaie);
            res = prs.executeQuery();
            if (res != null) {
                if (res.next()) {

                    TauxMonnaie tauxMonnaie = new TauxMonnaie(res.getString(1));
                    tauxMonnaie.setDateHeure(res.getTimestamp(2));
                    tauxMonnaie.setValeur(new BigDecimal(res.getString(3)));

                    prs.close();
                    res.close();
                    conexao.close();

                    return tauxMonnaie;
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return null;
    }

    public boolean sauvegarderTauxMonnaie(TauxMonnaie tauxMonnaie) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("INSERT INTO tauxcarte(");
            scriptSQL.append(" dateHeure, valeur, id )");
            scriptSQL.append(" VALUES (?, ?, ?, ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setTimestamp(1, new Timestamp(tauxMonnaie.getDateHeure().getTime()));
            prs.setBigDecimal(2, tauxMonnaie.getValeur());
            prs.setString(3, tauxMonnaie.getId());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

    public boolean actualiserTauxMonnaie(TauxMonnaie tauxMonnaie) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {

            scriptSQL = new StringBuilder("UPDATE sessionutilisateur");
            scriptSQL.append(" SET dateHeure=?, valeur=?");
            scriptSQL.append(" WHERE id=?");
            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setTimestamp(1, new Timestamp(tauxMonnaie.getDateHeure().getTime()));
            prs.setBigDecimal(2, tauxMonnaie.getValeur());
            prs.setString(3, tauxMonnaie.getId());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

    public TauxMonnaie selectionnerDerniersTauxMonnaieEnDate() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {

            scriptSQL = new StringBuilder("SELECT Max(dateHeure), id, tauxmonnaie.valeur");
            scriptSQL.append(" FROM tauxmonnaie");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();
            if (res != null) {
                if (res.next()) {

                    TauxMonnaie tauxMonnaie = new TauxMonnaie(res.getString(1));
                    tauxMonnaie.setDateHeure(res.getTimestamp(2));
                    tauxMonnaie.setValeur(new BigDecimal(res.getString(3)));

                    prs.close();
                    res.close();
                    conexao.close();

                    return tauxMonnaie;
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return null;
    }
}
