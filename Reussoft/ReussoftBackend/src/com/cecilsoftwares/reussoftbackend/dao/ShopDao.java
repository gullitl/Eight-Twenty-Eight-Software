package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoftmiddleend.model.Shop;
import com.cecilsoftwares.reussoftmiddleend.model.TauxCarte;
import java.math.BigDecimal;
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

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {

            scriptSQL = new StringBuilder("SELECT shop.id, shop.nom, shop.adresse, shop.active,");
            scriptSQL.append(" shop.idTauxCarte, tauxcarte.id, tauxcarte.valeur, tauxcarte.dateHeure");
            scriptSQL.append(" FROM shop JOIN tauxcarte ON shop.idTauxCarte=tauxcarte.id");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();

            listeShops = new ArrayList();
            if (res != null) {
                while (res.next()) {

                    Shop shop = new Shop(res.getString(1));
                    shop.setNom(res.getString(2));
                    shop.setAdresse(res.getString(3));
                    shop.setActive(res.getInt(4) == 1);

                    TauxCarte tauxCarte = new TauxCarte(res.getString(5));
                    tauxCarte.setValeur(new BigDecimal(res.getString(6)));
                    tauxCarte.setDateHeure(res.getTimestamp(7));

                    shop.setTauxCarte(tauxCarte);

                    listeShops.add(shop);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listeShops;
    }

    public Shop selectionnerShopParId(String idShop) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT shop.id, shop.nom, shop.adresse, shop.active,");
            scriptSQL.append(" shop.idTauxCarte, tauxcarte.id, tauxcarte.valeur, tauxcarte.dateHeure");
            scriptSQL.append(" FROM shop JOIN tauxcarte ON shop.idTauxCarte=tauxcarte.id");
            scriptSQL.append(" WHERE shop.id=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setString(1, idShop);
            res = prs.executeQuery();

            if (res != null) {
                if (res.next()) {

                    Shop shop = new Shop(res.getString(1));
                    shop.setNom(res.getString(2));
                    shop.setAdresse(res.getString(3));
                    shop.setActive(res.getInt(4) == 1);

                    TauxCarte tauxCarte = new TauxCarte(res.getString(5));
                    tauxCarte.setValeur(new BigDecimal(res.getString(6)));
                    tauxCarte.setDateHeure(res.getTimestamp(7));

                    shop.setTauxCarte(tauxCarte);

                    prs.close();
                    res.close();
                    conexao.close();

                    return shop;
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return null;
    }

    public boolean enregistrerShop(Shop shop) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("INSERT INTO shop(");
            scriptSQL.append(" nom, adresse, active, id)");
            scriptSQL.append(" VALUES (?, ?, ?, ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setString(1, shop.getNom());
            prs.setString(2, shop.getAdresse());
            prs.setInt(3, shop.isActive() ? 1 : 0);
            prs.setString(4, shop.getId());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

    public boolean actualiserShop(Shop shop) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("UPDATE shop");
            scriptSQL.append(" SET nom=?, adresse=?, active=?");
            scriptSQL.append(" WHERE id=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setString(1, shop.getNom());
            prs.setString(2, shop.getAdresse());
            prs.setInt(3, shop.isActive() ? 1 : 0);
            prs.setString(4, shop.getId());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

    public boolean exclureShop(String idShop) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("DELETE FROM shop WHERE id=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setString(1, idShop);

            prs.execute();
            prs.close();
            conexao.close();
        }

        return true;
    }

}
