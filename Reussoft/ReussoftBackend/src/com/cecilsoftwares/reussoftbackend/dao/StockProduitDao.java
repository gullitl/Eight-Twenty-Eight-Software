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

    public List<StockProduit> listerTousLeStockProduit() throws ClassNotFoundException, SQLException {
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

    // Considerar a partir daqui
    public boolean actualiserEstoque(Produit produit, Shop shop, BigDecimal quantiteStockMouvemente)
            throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        boolean retorno;
        int contador = 0;
        StringBuilder scriptSQL;
        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT count(*) FROM estoque WHERE id_produto = ?");
            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setString(1, produit.getId());
            res = prs.executeQuery();

            if (res != null) {
                while (res.next()) {
                    contador = res.getInt(1);
                }
            }

            if (contador > 0) {
                scriptSQL = new StringBuilder("UPDATE estoque SET qtde=qtde + ?, valor_unitario= ?");
                scriptSQL.append(" WHERE id_produto = ?");

                prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

                prs.setBigDecimal(1, quantiteStockMouvemente);
                prs.setString(3, produit.getId());

                prs.execute();
                retorno = true;
            } else {
                scriptSQL = new StringBuilder("INSERT INTO estoque (qtde, valor_unitario, id_produto)");
                scriptSQL.append(" VALUES (?, ?, ?)");

                prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

                prs.setBigDecimal(1, quantiteStockMouvemente);
                prs.setString(3, produit.getId());

                prs.execute();
                retorno = true;
            }
            conexao.close();
        }
        prs.close();
        res.close();
        return retorno;
    }

}
