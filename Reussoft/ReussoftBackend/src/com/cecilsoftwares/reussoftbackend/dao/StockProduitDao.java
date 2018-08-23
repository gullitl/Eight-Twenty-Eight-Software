package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoftmiddleend.model.Produit;
import com.cecilsoftwares.reussoftmiddleend.model.Shop;
import com.cecilsoftwares.reussoftmiddleend.model.StockProduit;
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
public class StockProduitDao {

    private StringBuilder scriptSQL;
    private static StockProduitDao uniqueInstance;

    public StockProduitDao() {
    }

    public static synchronized StockProduitDao getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new StockProduitDao();
        }
        return uniqueInstance;
    }

    public List<StockProduit> listerTousLesStockProduit() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<StockProduit> listeStocksProduit;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT stockproduit.idProduit, produit.description, produit.active,");
            scriptSQL.append(" stockproduit.idShop, shop.nom, shop.adresse, shop.active");
            scriptSQL.append(" stockproduit.quantiteStock");
            scriptSQL.append(" FROM stockproduit");
            scriptSQL.append(" LEFT JOIN produit ON stockproduit.idProduit = produit.id");
            scriptSQL.append(" LEFT JOIN shop ON stockproduit.idShop = shop.id");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();

            listeStocksProduit = new ArrayList();
            if (res != null) {
                while (res.next()) {

                    Produit produit = new Produit(res.getString(1));
                    produit.setDescription(res.getString(2));
                    produit.setActive(res.getInt(3) == 1);

                    Shop shop = new Shop(res.getString(4));
                    shop.setNom(res.getString(5));
                    shop.setAdresse(res.getString(6));
                    shop.setActive(res.getInt(7) == 0);

                    StockProduit stockProduit = new StockProduit(produit, shop);
                    stockProduit.setQuantiteStock(res.getBigDecimal(8));

                    listeStocksProduit.add(stockProduit);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listeStocksProduit;
    }

    public StockProduit selectionnerStockProduitParProduitId(String idProduit) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT stockproduit.idProduit, produit.description, produit.active,");
            scriptSQL.append(" stockproduit.idShop, shop.nom, shop.adresse, shop.active");
            scriptSQL.append(" stockproduit.quantiteStock");
            scriptSQL.append(" FROM stockproduit");
            scriptSQL.append(" LEFT JOIN produit ON stockproduit.idProduit = produit.id");
            scriptSQL.append(" LEFT JOIN shop ON stockproduit.idShop = shop.id");
            scriptSQL.append(" WHERE stockproduit.idProduit=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setString(1, idProduit);
            res = prs.executeQuery();

            if (res != null) {
                if (res.next()) {

                    Produit produit = new Produit(res.getString(1));
                    produit.setDescription(res.getString(2));
                    produit.setActive(res.getInt(3) == 1);

                    Shop shop = new Shop(res.getString(4));
                    shop.setNom(res.getString(5));
                    shop.setAdresse(res.getString(6));
                    shop.setActive(res.getInt(7) == 0);

                    StockProduit stockProduit = new StockProduit(produit, shop);
                    stockProduit.setQuantiteStock(res.getBigDecimal(8));

                    prs.close();
                    res.close();
                    conexao.close();

                    return stockProduit;
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return null;
    }

    public boolean entrerStock(Produit produit, Shop shop, BigDecimal quantiteMouvement, Connection connection)
            throws ClassNotFoundException, SQLException {
        return actualiserStock(produit, shop, quantiteMouvement, connection);
    }

    public boolean sortirStock(Produit produit, Shop shop, BigDecimal quantiteMouvement, Connection connection)
            throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        scriptSQL = new StringBuilder("SELECT quantiteStock");
        scriptSQL.append(" FROM stockproduit");
        scriptSQL.append(" WHERE idProduit=? AND idShop=?");

        prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));
        prs.setString(1, produit.getId());
        prs.setString(2, shop.getId());
        res = prs.executeQuery();

        if (res != null) {
            if (res.next()) {
                if (quantiteMouvement.compareTo(res.getBigDecimal(1)) > 0) {
                    //Le stock du produit é insufisant
                    prs.close();
                    res.close();
                    return false;
                }
            }
        } else {
            //Le produit ne même pas encore entré dans le stock
            prs.close();
            res.close();
            return false;
        }
        prs.close();
        res.close();

        return actualiserStock(produit, shop, quantiteMouvement.multiply(new BigDecimal("-1")), connection);
    }

    private boolean actualiserStock(Produit produit, Shop shop, BigDecimal quantiteStockMouvemente, Connection connection) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        int conteur = 0;

        String teste = quantiteStockMouvemente.add(new BigDecimal(2)).toString();
        System.out.println(teste);

        scriptSQL = new StringBuilder("SELECT count(*) FROM stockproduit WHERE idProduit=? AND idShop=?");
        prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));

        prs.setString(1, produit.getId());
        prs.setString(2, shop.getId());
        res = prs.executeQuery();

        if (res != null) {
            while (res.next()) {
                conteur = res.getInt(1);
            }
        }

        if (conteur > 0) {
            scriptSQL = new StringBuilder("UPDATE stockproduit SET quantiteStock = quantiteStock + ?");
            scriptSQL.append(" WHERE idProduit=? AND idShop=?");

            prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));

            prs.setBigDecimal(1, quantiteStockMouvemente);
            prs.setString(2, produit.getId());
            prs.setString(3, shop.getId());

            prs.execute();

            prs.close();
            res.close();
            return true;

        } else {
            scriptSQL = new StringBuilder("INSERT INTO stockproduit (quantiteStock, idProduit, idShop)");
            scriptSQL.append(" VALUES (?, ?, ?)");

            prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));

            prs.setBigDecimal(1, quantiteStockMouvemente);
            prs.setString(2, produit.getId());
            prs.setString(3, shop.getId());

            prs.execute();

            prs.close();
            res.close();
            return true;
        }
    }
}
