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

        try (Connection connection = ConnectionFactory.getInstance().habiliterConnection()) {
            listeTauxCartes = new ArrayList();

            scriptSQL = new StringBuilder("SELECT tauxcarte.id, tauxcarte.dateHeure, tauxcarte.valeur,");
            scriptSQL.append(" tauxcarte.idShop, shop.nom, shop.adresse");
            scriptSQL.append(" FROM tauxcarte");
            scriptSQL.append(" LEFT JOIN shop ON tauxcarte.idShop = shop.id");
            scriptSQL.append(" ORDER BY tauxcarte.dateHeure");

            prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));
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
            connection.close();
        }
        return listeTauxCartes;
    }

    public TauxCarte selectionnerTauxCarteParId(String idTauxCarte) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection connection = ConnectionFactory.getInstance().habiliterConnection()) {

            scriptSQL = new StringBuilder("SELECT tauxcarte.id, tauxcarte.dateHeure, tauxcarte.valeur,");
            scriptSQL.append(" tauxcarte.idShop, shop.nom, shop.adresse");
            scriptSQL.append(" FROM tauxcarte");
            scriptSQL.append(" LEFT JOIN shop ON tauxcarte.idShop = shop.id");
            scriptSQL.append(" WHERE tauxcarte.id=?");

            prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));
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
                    connection.close();

                    return tauxCarte;
                }
            }
            prs.close();
            res.close();
            connection.close();
        }
        return null;
    }

    public boolean enregistrerShopTauxCarte(Shop shopTauxCarte) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection connection = ConnectionFactory.getInstance().habiliterConnection()) {

            scriptSQL = new StringBuilder("INSERT INTO tauxcarte(");
            scriptSQL.append(" dateHeure, valeur, idShop, id )");
            scriptSQL.append(" VALUES (?, ?, ?, ?)");

            prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));

            prs.setTimestamp(1, new Timestamp(shopTauxCarte.getTauxCarte().getDateHeure().getTime()));
            prs.setBigDecimal(2, shopTauxCarte.getTauxCarte().getValeur());
            prs.setString(3, shopTauxCarte.getId());
            prs.setString(4, shopTauxCarte.getTauxCarte().getId());

            prs.execute();

            scriptSQL = new StringBuilder("UPDATE shop");
            scriptSQL.append(" SET idTauxCarte=?");
            scriptSQL.append(" WHERE id=?");

            prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));

            prs.setString(1, shopTauxCarte.getTauxCarte().getId());
            prs.setString(2, shopTauxCarte.getId());

            prs.execute();

            prs.close();
            connection.close();
        }
        return true;
    }

    //Taux Monnaie
    public List<TauxMonnaie> listerTousLesTauxMonnaies() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<TauxMonnaie> listeTauxMonnaies;

        try (Connection connection = ConnectionFactory.getInstance().habiliterConnection()) {
            listeTauxMonnaies = new ArrayList();

            scriptSQL = new StringBuilder("SELECT tauxmonnaie.id, tauxmonnaie.dateHeure, tauxmonnaie.valeur,");
            scriptSQL.append(" FROM tauxmonnaie");

            prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));
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
            connection.close();
        }
        return listeTauxMonnaies;
    }

    public TauxMonnaie selectionnerTauxMonnaieParId(String idTauxMonnaie) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection connection = ConnectionFactory.getInstance().habiliterConnection()) {

            scriptSQL = new StringBuilder("SELECT tauxmonnaie.id, tauxmonnaie.dateHeure, tauxmonnaie.valeur,");
            scriptSQL.append(" FROM tauxmonnaie");
            scriptSQL.append(" WHERE tauxmonnaie.id=?");

            prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));
            prs.setString(1, idTauxMonnaie);
            res = prs.executeQuery();
            if (res != null) {
                if (res.next()) {

                    TauxMonnaie tauxMonnaie = new TauxMonnaie(res.getString(1));
                    tauxMonnaie.setDateHeure(res.getTimestamp(2));
                    tauxMonnaie.setValeur(new BigDecimal(res.getString(3)));

                    prs.close();
                    res.close();
                    connection.close();

                    return tauxMonnaie;
                }
            }
            prs.close();
            res.close();
            connection.close();
        }
        return null;
    }

    public boolean enregistrerTauxMonnaie(TauxMonnaie tauxMonnaie) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection connection = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("INSERT INTO tauxcarte(");
            scriptSQL.append(" dateHeure, valeur, id )");
            scriptSQL.append(" VALUES (?, ?, ?, ?)");

            prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));

            prs.setTimestamp(1, new Timestamp(tauxMonnaie.getDateHeure().getTime()));
            prs.setBigDecimal(2, tauxMonnaie.getValeur());
            prs.setString(3, tauxMonnaie.getId());

            prs.execute();
            prs.close();
            connection.close();
        }
        return true;
    }

}
