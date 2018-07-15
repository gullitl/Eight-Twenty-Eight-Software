package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoftmiddleend.model.Produit;
import com.cecilsoftwares.reussoftmiddleend.model.Shop;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    private boolean actualiserStock(Produit produit, Shop shop, int quantiteMouvement) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        int conteur = 0;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT count(*) FROM stockproduit WHERE idProduit=? AND idShop=?");
            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

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

                prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

                prs.setInt(1, quantiteMouvement);
                prs.setString(2, produit.getId());
                prs.setString(3, shop.getId());

                prs.execute();
                conexao.close();
                prs.close();
                res.close();
                return true;

            } else {
                scriptSQL = new StringBuilder("INSERT INTO stockproduit (quantiteStock, idProduto, idShop)");
                scriptSQL.append(" VALUES (?, ?, ?)");

                prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

                prs.setInt(1, quantiteMouvement);
                prs.setString(2, produit.getId());
                prs.setString(3, shop.getId());

                prs.execute();
                conexao.close();
                prs.close();
                res.close();
                return true;
            }
        }
    }

    public boolean entrerStock(Produit produit, Shop shop, int quantiteMouvement)
            throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("INSERT INTO entreestoque(");
            scriptSQL.append("idProduto, quantiteProduit)");
            scriptSQL.append(" VALUES (?, ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setString(1, produit.getId());
            prs.setInt(2, quantiteMouvement);

            prs.execute();

            actualiserStock(produit, shop, quantiteMouvement);

            prs.close();
            conexao.close();
        }

        return true;
    }

    public boolean sortirStock(Produit produit, Shop shop, int quantiteMouvement)
            throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("INSERT INTO sortiestock(");
            scriptSQL.append("idProduto, quantiteProduit)");
            scriptSQL.append(" VALUES (?, ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setString(1, produit.getId());
            prs.setInt(2, quantiteMouvement);

            prs.execute();

            actualiserStock(produit, shop, -quantiteMouvement);

            prs.close();
            conexao.close();
        }

        return true;
    }

    public int selectionnerIdCollaborateurSubsequent() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT Max(idCollaborateur)+1 FROM collaborateur");
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

    public boolean exclureStock(String idStock) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("DELETE FROM stock WHERE id=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setString(1, idStock);

            prs.execute();
            prs.close();
            conexao.close();
        }

        return true;
    }

}
