package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoftmiddleend.exception.StockInsuffisantException;
import com.cecilsoftwares.reussoftmiddleend.model.CategorieProduit;
import com.cecilsoftwares.reussoftmiddleend.model.PrixAchatProduit;
import com.cecilsoftwares.reussoftmiddleend.model.Produit;
import com.cecilsoftwares.reussoftmiddleend.model.Reseau;
import com.cecilsoftwares.reussoftmiddleend.model.Shop;
import com.cecilsoftwares.reussoftmiddleend.model.Shop.ShopBuilder;
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

    public List<StockProduit> listerTousLesStockProduitAvecDetail() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<StockProduit> listeStocksProduit;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT stockproduit.idProduit, produit.description, produit.active,");
            scriptSQL.append(" produit.idCategorieProduit, categorieproduit.description, categorieproduit.descriptionAbregee,");
            scriptSQL.append(" produit.idReseau, reseau.nom, reseau.nomAbrege,");
            scriptSQL.append(" produit.idPrixachat, prixachatproduit.valeurUSD, prixachatproduit.dateHeure,");
            scriptSQL.append(" stockproduit.idShop, shop.nom,");
            scriptSQL.append(" stockproduit.quantiteStock");
            scriptSQL.append(" FROM stockproduit");
            scriptSQL.append(" LEFT JOIN categorieproduit ON produit.idCategorieProduit = categorieproduit.id");
            scriptSQL.append(" LEFT JOIN reseau ON produit.idReseau = reseau.id");
            scriptSQL.append(" LEFT JOIN prixachatproduit ON produit.idPrixAchat = prixachatproduit.id");
            scriptSQL.append(" LEFT JOIN shop ON stockproduit.idShop = shop.id");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();

            listeStocksProduit = new ArrayList();
            if (res != null) {
                while (res.next()) {

                    Produit produit = new Produit(res.getString(1));
                    produit.setDescription(res.getString(2));
                    produit.setActive(res.getInt(3) == 1);

                    CategorieProduit categorieProduit = new CategorieProduit(res.getString(4));
                    categorieProduit.setDescription(res.getString(5));
                    categorieProduit.setDescriptionAbregee(res.getString(6));
                    produit.setCategorieProduit(categorieProduit);

                    Reseau reseau = new Reseau(res.getString(7));
                    reseau.setNom(res.getString(8));
                    reseau.setNomAbrege(res.getString(9));
                    produit.setReseau(reseau);

                    PrixAchatProduit prixAchatProduit = new PrixAchatProduit(res.getString(10));
                    prixAchatProduit.setValeurUSD(res.getBigDecimal(11));
                    prixAchatProduit.setDateHeure(res.getTimestamp(12));
                    produit.setPrixAchatProduit(prixAchatProduit);

                    Shop shop = new ShopBuilder(res.getString(13))
                            .withNom(res.getString(14))
                            .create();

                    StockProduit stockProduit = new StockProduit(produit, shop);
                    stockProduit.setQuantiteStock(res.getBigDecimal(res.getString(15)));

                    listeStocksProduit.add(stockProduit);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listeStocksProduit;
    }

    public List<StockProduit> listerTousLesStockProduitSansDetail() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<StockProduit> listeStocksProduit;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT idProduit, idShop, quantiteStock FROM stockproduit");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();

            listeStocksProduit = new ArrayList();
            if (res != null) {
                while (res.next()) {
                    StockProduit stockProduit = new StockProduit(new Produit(res.getString(1)), new ShopBuilder(res.getString(2)).create());
                    stockProduit.setQuantiteStock(res.getBigDecimal(res.getString(3)));
                    listeStocksProduit.add(stockProduit);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listeStocksProduit;
    }

    public List<StockProduit> listerTotalStockProduit() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<StockProduit> listeStocksProduit;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT idProduit, SUM(quantiteStock)");
            scriptSQL = scriptSQL.append("FROM stockproduit");
            scriptSQL = scriptSQL.append("GROUP BY idProduit");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();

            listeStocksProduit = new ArrayList();
            if (res != null) {
                while (res.next()) {
                    StockProduit stockProduit = new StockProduit(new Produit(res.getString(1)));
                    stockProduit.setQuantiteStock(res.getBigDecimal(res.getString(2)));
                    listeStocksProduit.add(stockProduit);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listeStocksProduit;
    }

    public StockProduit selectionnerStockProduitParIdProduitEIdShopAvecDetail(String idProduit, String idShop) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT stockproduit.idProduit, produit.description, produit.active,");
            scriptSQL.append(" produit.idCategorieProduit, categorieproduit.description, categorieproduit.descriptionAbregee,");
            scriptSQL.append(" produit.idReseau, reseau.nom, reseau.nomAbrege,");
            scriptSQL.append(" produit.idPrixachat, prixachatproduit.valeurUSD, prixachatproduit.dateHeure,");
            scriptSQL.append(" stockproduit.idShop, shop.nom,");
            scriptSQL.append(" stockproduit.quantiteStock");
            scriptSQL.append(" FROM stockproduit");
            scriptSQL.append(" LEFT JOIN produit ON stockproduit.idProduit = produit.id");
            scriptSQL.append(" LEFT JOIN categorieproduit ON produit.idCategorieProduit = categorieproduit.id");
            scriptSQL.append(" LEFT JOIN reseau ON produit.idReseau = reseau.id");
            scriptSQL.append(" LEFT JOIN prixachatproduit ON produit.idPrixAchat = prixachatproduit.id");
            scriptSQL.append(" LEFT JOIN shop ON stockproduit.idShop = shop.id");
            scriptSQL.append(" WHERE stockproduit.idProduit=? AND stockproduit.idShop=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setString(1, idProduit);
            prs.setString(2, idShop);
            res = prs.executeQuery();

            if (res != null) {
                if (res.next()) {

                    Produit produit = new Produit(res.getString(1));
                    produit.setDescription(res.getString(2));
                    produit.setActive(res.getInt(3) == 1);

                    CategorieProduit categorieProduit = new CategorieProduit(res.getString(4));
                    categorieProduit.setDescription(res.getString(5));
                    categorieProduit.setDescriptionAbregee(res.getString(6));
                    produit.setCategorieProduit(categorieProduit);

                    Reseau reseau = new Reseau(res.getString(7));
                    reseau.setNom(res.getString(8));
                    reseau.setNomAbrege(res.getString(9));
                    produit.setReseau(reseau);

                    PrixAchatProduit prixAchatProduit = new PrixAchatProduit(res.getString(10));
                    prixAchatProduit.setValeurUSD(res.getBigDecimal(11));
                    prixAchatProduit.setDateHeure(res.getTimestamp(12));
                    produit.setPrixAchatProduit(prixAchatProduit);

                    Shop shop = new ShopBuilder(res.getString(13))
                            .withNom(res.getString(14))
                            .create();

                    StockProduit stockProduit = new StockProduit(produit, shop);
                    stockProduit.setQuantiteStock(res.getBigDecimal((15)));

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

    public StockProduit selectionnerStockProduitParIdProduitEIdShopSansDetail(String idProduit, String idShop) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        StockProduit stockProduit = new StockProduit(new Produit(idProduit), new ShopBuilder(idShop).create());

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT idProduit, idShop, quantiteStock");
            scriptSQL.append(" FROM stockproduit");
            scriptSQL.append(" WHERE idProduit=? AND idShop=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setString(1, idProduit);
            prs.setString(2, idShop);
            res = prs.executeQuery();

            if (res != null) {
                if (res.next()) {

                    stockProduit.setQuantiteStock(res.getBigDecimal(3));

                    prs.close();
                    res.close();
                    conexao.close();

                    return stockProduit;
                } else {
                    stockProduit.setQuantiteStock(new BigDecimal("0"));
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return stockProduit;
    }

    public StockProduit selectionnerStockProduitTousLesShopsParIdProduitAvecDetail(String idProduit) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT stockproduit.idProduit, produit.description, produit.active,");
            scriptSQL.append(" produit.idCategorieProduit, categorieproduit.description, categorieproduit.descriptionAbregee,");
            scriptSQL.append(" produit.idReseau, reseau.nom, reseau.nomAbrege,");
            scriptSQL.append(" produit.idPrixachat, prixachatproduit.valeurUSD, prixachatproduit.dateHeure,");
            scriptSQL.append(" stockproduit.idShop, shop.nom,");
            scriptSQL.append(" stockproduit.quantiteStock");
            scriptSQL.append(" FROM stockproduit");
            scriptSQL.append(" LEFT JOIN produit ON stockproduit.idProduit = produit.id");
            scriptSQL.append(" LEFT JOIN categorieproduit ON produit.idCategorieProduit = categorieproduit.id");
            scriptSQL.append(" LEFT JOIN reseau ON produit.idReseau = reseau.id");
            scriptSQL.append(" LEFT JOIN prixachatproduit ON produit.idPrixAchat = prixachatproduit.id");
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

                    CategorieProduit categorieProduit = new CategorieProduit(res.getString(4));
                    categorieProduit.setDescription(res.getString(5));
                    categorieProduit.setDescriptionAbregee(res.getString(6));
                    produit.setCategorieProduit(categorieProduit);

                    Reseau reseau = new Reseau(res.getString(7));
                    reseau.setNom(res.getString(8));
                    reseau.setNomAbrege(res.getString(9));
                    produit.setReseau(reseau);

                    PrixAchatProduit prixAchatProduit = new PrixAchatProduit(res.getString(10));
                    prixAchatProduit.setValeurUSD(res.getBigDecimal(11));
                    prixAchatProduit.setDateHeure(res.getTimestamp(12));
                    produit.setPrixAchatProduit(prixAchatProduit);

                    Shop shop = new ShopBuilder(res.getString(13))
                            .withNom(res.getString(14))
                            .create();

                    StockProduit stockProduit = new StockProduit(produit, shop);
                    stockProduit.setQuantiteStock(res.getBigDecimal((15)));

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

    public StockProduit selectionnerStockProduitTousLesShopsParIdProduitSansDetail(String idProduit) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        StockProduit stockProduit = new StockProduit(new Produit(idProduit));

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT idProduit, idShop, quantiteStock");
            scriptSQL.append(" FROM stockproduit");
            scriptSQL.append(" WHERE idProduit=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setString(1, idProduit);
            res = prs.executeQuery();

            if (res != null) {

                BigDecimal quantiteStockProduit = new BigDecimal("0");

                while (res.next()) {
                    quantiteStockProduit = quantiteStockProduit.add(res.getBigDecimal(3));
                }

                stockProduit.setQuantiteStock(quantiteStockProduit);
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return stockProduit;
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
                    throw new StockInsuffisantException("Le stock du produit é insufisant");
                }
            }
        } else {
            prs.close();
            res.close();
            throw new StockInsuffisantException("Le produit n'est même pas encore entré dans le stock");
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
