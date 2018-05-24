package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoftmiddleend.model.Shop;
import com.cecilsoftwares.reussoftmiddleend.model.Shop.ShopBuilder;
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

    //valide = true
    public List<Shop> listerTousLesShops() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<Shop> listeShops;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT code, nom, adresse, observation, active");
            scriptSQL.append(" FROM shop");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();

            listeShops = new ArrayList();
            if (res != null) {
                while (res.next()) {

                    Shop shop = new ShopBuilder(res.getInt(1))
                            .nom(res.getString(2))
                            .adresse(res.getString(3))
                            .observation(res.getString(4))
                            .active(res.getInt(5) == 1)
                            .build();

                    listeShops.add(shop);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listeShops;
    }

    //valide = true
    public Shop selectionnerShopParCode(int codeShop) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT code, nom, adresse, observation, active");
            scriptSQL.append(" FROM shop WHERE code=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codeShop);
            res = prs.executeQuery();

            if (res != null) {
                if (res.next()) {

                    Shop shop = new ShopBuilder(res.getInt(1))
                            .nom(res.getString(2))
                            .adresse(res.getString(3))
                            .observation(res.getString(4))
                            .active(res.getInt(5) == 1)
                            .build();

                    return shop;
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return null;
    }

    //Valide = true
    public boolean enregistrerShop(Shop shop) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("INSERT INTO shop(");
            scriptSQL.append(" code, nom, adresse, observation, active)");
            scriptSQL.append(" VALUES (?, ?, ?, ?, ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setInt(1, shop.getCode());
            prs.setString(2, shop.getNom());
            prs.setString(3, shop.getAdresse());
            prs.setString(4, shop.getObservation());
            prs.setInt(5, shop.isActive() ? 1 : 0);

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

    //valide = true
    public boolean actualiserShop(Shop shop) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("UPDATE shop");
            scriptSQL.append(" SET nom=?, adresse=?, observation=?, active=?");
            scriptSQL.append(" WHERE code=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setString(1, shop.getNom());
            prs.setString(2, shop.getAdresse());
            prs.setString(3, shop.getObservation());
            prs.setInt(4, shop.getCode());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

    //valide = true
    public int selectionnerCodeShopSubsequent() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT Max(code)+1 FROM shop");
            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();
            if (res != null) {
                if (res.next()) {
                    int cdSubsequente = res.getInt(1);

                    prs.close();
                    res.close();
                    conexao.close();

                    return cdSubsequente;
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return 0;
    }
}
