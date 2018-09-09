package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoftmiddleend.model.Shop;
import com.cecilsoftwares.reussoftmiddleend.model.Shop.ShopBuilder;
import com.cecilsoftwares.reussoftmiddleend.model.TauxCarte;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class ShopDao {

    private StringBuilder scriptSQL;
    private static ShopDao uniqueInstance;

    public ShopDao() {
    }

    public static synchronized ShopDao getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ShopDao();
        }
        return uniqueInstance;
    }

    public List<Shop> listerTousLesShops() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<Shop> listeShops;

        try (Connection connection = ConnectionFactory.getInstance().habiliterConnection()) {

            scriptSQL = new StringBuilder("SELECT shop.id, shop.nom, shop.adresse, shop.active,");
            scriptSQL.append(" shop.idTauxCarte, tauxcarte.valeur, tauxcarte.dateHeure");
            scriptSQL.append(" FROM shop LEFT JOIN tauxcarte ON shop.idTauxCarte=tauxcarte.id");
            scriptSQL.append(" ORDER BY shop.nom");

            prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();

            listeShops = new ArrayList();
            if (res != null) {
                while (res.next()) {

                    Shop shop = new ShopBuilder(res.getString(1))
                            .withNom(res.getString(2))
                            .withAdresse(res.getString(3))
                            .withActive(res.getInt(4) == 1)
                            .create();

                    TauxCarte tauxCarte = new TauxCarte(res.getString(5));
                    tauxCarte.setValeur(res.getBigDecimal(6));
                    tauxCarte.setDateHeure(res.getTimestamp(7));

                    shop.setTauxCarte(tauxCarte);

                    listeShops.add(shop);
                }
            }
            prs.close();
            res.close();
            connection.close();
        }
        return listeShops;
    }

    public Shop selectionnerShopParId(String idShop) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection connection = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT shop.id, shop.nom, shop.adresse, shop.active,");
            scriptSQL.append(" shop.idTauxCarte, tauxcarte.id, tauxcarte.valeur, tauxcarte.dateHeure");
            scriptSQL.append(" FROM shop LEFT JOIN tauxcarte ON shop.idTauxCarte=tauxcarte.id");
            scriptSQL.append(" WHERE shop.id=?");

            prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));
            prs.setString(1, idShop);
            res = prs.executeQuery();

            if (res != null) {
                if (res.next()) {

                    Shop shop = new ShopBuilder(res.getString(1))
                            .withNom(res.getString(2))
                            .withAdresse(res.getString(3))
                            .withActive(res.getInt(4) == 1)
                            .create();

                    TauxCarte tauxCarte = new TauxCarte(res.getString(5));
                    tauxCarte.setValeur(res.getBigDecimal(6));
                    tauxCarte.setDateHeure(res.getTimestamp(7));

                    shop.setTauxCarte(tauxCarte);

                    prs.close();
                    res.close();
                    connection.close();

                    return shop;
                }
            }
            prs.close();
            res.close();
            connection.close();
        }
        return null;
    }

    public boolean enregistrerShop(Shop shop) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection connection = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("INSERT INTO shop(");
            scriptSQL.append(" nom, adresse, active, id)");
            scriptSQL.append(" VALUES (?, ?, ?, ?)");

            prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));

            prs.setString(1, shop.getNom());
            prs.setString(2, shop.getAdresse());
            prs.setInt(3, shop.isActive() ? 1 : 0);
            prs.setString(4, shop.getId());

            prs.execute();
            prs.close();
            connection.close();
        }
        return true;
    }

    public boolean actualiserShop(Shop shop) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection connection = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("UPDATE shop");
            scriptSQL.append(" SET nom=?, adresse=?, active=?");
            scriptSQL.append(" WHERE id=?");

            prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));

            prs.setString(1, shop.getNom());
            prs.setString(2, shop.getAdresse());
            prs.setInt(3, shop.isActive() ? 1 : 0);
            prs.setString(4, shop.getId());

            prs.execute();
            prs.close();
            connection.close();
        }
        return true;
    }

    public boolean exclureShop(Shop shop) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection connection = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("DELETE FROM shop WHERE id=?");

            prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));
            prs.setString(1, shop.getId());

            prs.execute();
            prs.close();
            connection.close();
        }

        return true;
    }

}
