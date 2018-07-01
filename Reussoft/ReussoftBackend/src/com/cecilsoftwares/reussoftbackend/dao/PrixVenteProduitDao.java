package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoftmiddleend.model.PrixVenteProduit;
import com.cecilsoftwares.reussoftmiddleend.model.PrixVenteProduitShop;
import com.cecilsoftwares.reussoftmiddleend.model.Produit;
import com.cecilsoftwares.reussoftmiddleend.model.Shop;
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
public class PrixVenteProduitDao {

    private StringBuilder scriptSQL;
    private static PrixVenteProduitDao uniqueInstance;

    public PrixVenteProduitDao() {
    }

    public static synchronized PrixVenteProduitDao getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new PrixVenteProduitDao();
        }
        return uniqueInstance;
    }

    public List<PrixVenteProduit> listerTousLesPrixVenteProduits() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<PrixVenteProduit> listePrixVenteProduits;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT prixventeproduit.code, prixventeproduit.valeurUSD, prixventeproduit.dateHeure");
            scriptSQL.append(" prixventeproduit.idProduit, produit.Description,");
            scriptSQL.append(" prixventeproduit.idShop, shop.nom, shop.adresse, shop.active");
            scriptSQL.append(" FROM prixventeproduit");
            scriptSQL.append(" LEFT JOIN produit ON prixventeproduit.idProduit = produit.code");
            scriptSQL.append(" LEFT JOIN shop ON prixventeproduit.idShop = shop.code");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();

            listePrixVenteProduits = new ArrayList();
            if (res != null) {
                while (res.next()) {

                    PrixVenteProduit prixVenteProduit = new PrixVenteProduit(1);
                    prixVenteProduit.setValeurUSD(res.getBigDecimal(2));
                    prixVenteProduit.setDateHeure(res.getTimestamp(3));

                    Produit produit = new Produit(res.getInt(4));
                    produit.setDescription(res.getString(5));
                    prixVenteProduit.setProduit(produit);

                    Shop shop = new Shop(res.getInt(6));
                    shop.setNom(res.getString(7));
                    shop.setAdresse(res.getString(8));
                    shop.setActive(res.getInt(9) == 0);
                    prixVenteProduit.setShop(shop);

                    listePrixVenteProduits.add(prixVenteProduit);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listePrixVenteProduits;
    }

    public List<PrixVenteProduit> selectionnerPrixVenteProduitParCodeProduit(int codeProduit) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<PrixVenteProduit> listePrixVenteProduits;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT prixventeproduit.code, prixventeproduit.valeurUSD, prixventeproduit.dateHeure");
            scriptSQL.append(" prixventeproduit.idProduit, produit.Description,");
            scriptSQL.append(" prixventeproduit.idShop, shop.nom, shop.adresse, shop.active");
            scriptSQL.append(" FROM prixventeproduit");
            scriptSQL.append(" LEFT JOIN produit ON prixventeproduit.idProduit = produit.code");
            scriptSQL.append(" LEFT JOIN shop ON prixventeproduit.idShop = shop.code");
            scriptSQL.append(" WHERE prixventeproduit.code=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codeProduit);
            res = prs.executeQuery();

            listePrixVenteProduits = new ArrayList();
            if (res != null) {
                if (res.next()) {

                    PrixVenteProduit prixVenteProduit = new PrixVenteProduit(1);
                    prixVenteProduit.setValeurUSD(res.getBigDecimal(2));
                    prixVenteProduit.setDateHeure(res.getTimestamp(3));

                    Produit produit = new Produit(res.getInt(4));
                    produit.setDescription(res.getString(5));
                    prixVenteProduit.setProduit(produit);

                    Shop shop = new Shop(res.getInt(6));
                    shop.setNom(res.getString(7));
                    shop.setAdresse(res.getString(8));
                    shop.setActive(res.getInt(9) == 0);
                    prixVenteProduit.setShop(shop);

                    listePrixVenteProduits.add(prixVenteProduit);

                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listePrixVenteProduits;
    }

    public PrixVenteProduit selectionnerPrixVenteProduitParCode(int codePrixVenteProduit) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT prixventeproduit.code, prixventeproduit.valeurUSD, prixventeproduit.dateHeure");
            scriptSQL.append(" prixventeproduit.idProduit, produit.Description,");
            scriptSQL.append(" prixventeproduit.idShop, shop.nom, shop.adresse, shop.active");
            scriptSQL.append(" FROM prixventeproduit");
            scriptSQL.append(" LEFT JOIN produit ON prixventeproduit.idProduit = produit.code");
            scriptSQL.append(" LEFT JOIN shop ON prixventeproduit.idShop = shop.code");
            scriptSQL.append(" WHERE prixventeproduit.code=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codePrixVenteProduit);
            res = prs.executeQuery();

            if (res != null) {
                if (res.next()) {

                    PrixVenteProduit prixVenteProduit = new PrixVenteProduit(1);
                    prixVenteProduit.setValeurUSD(res.getBigDecimal(2));
                    prixVenteProduit.setDateHeure(res.getTimestamp(3));

                    Produit produit = new Produit(res.getInt(4));
                    produit.setDescription(res.getString(5));
                    prixVenteProduit.setProduit(produit);

                    Shop shop = new Shop(res.getInt(6));
                    shop.setNom(res.getString(7));
                    shop.setAdresse(res.getString(8));
                    shop.setActive(res.getInt(9) == 0);
                    prixVenteProduit.setShop(shop);

                    prs.close();
                    res.close();
                    conexao.close();

                    return prixVenteProduit;
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return null;
    }

    public boolean enregistrerPrixVenteProduit(PrixVenteProduit prixVenteProduit) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("INSERT INTO prixventeproduit(");
            scriptSQL.append(" idProduit, idShop, valeurUSD, dateHeure, code)");
            scriptSQL.append(" VALUES (?, ?, ?, ?, ?, ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setInt(1, prixVenteProduit.getProduit().getCode());
            prs.setInt(2, prixVenteProduit.getShop().getCode());
            prs.setBigDecimal(3, prixVenteProduit.getValeurUSD());
            prs.setTimestamp(4, new Timestamp(prixVenteProduit.getDateHeure().getTime()));
            prs.setInt(5, prixVenteProduit.getCode());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

    public boolean enregistrerPrixVenteProduitShop(PrixVenteProduit prixVenteProduit, List<PrixVenteProduitShop> prixVenteProduitShops) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("INSERT INTO prixventeproduit(");
            scriptSQL.append(" code, idProduit, , dateHeure, valeurUSD, idShop)");
            scriptSQL.append(" VALUES (?, ?, ?, ?, ?, ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setInt(1, prixVenteProduit.getCode());
            prs.setInt(2, prixVenteProduit.getProduit().getCode());
            prs.setTimestamp(3, new Timestamp(prixVenteProduit.getDateHeure().getTime()));

            for (PrixVenteProduitShop prixVenteProduitShop : prixVenteProduitShops) {
                prs.setBigDecimal(4, prixVenteProduitShop.getValeurUSD());
                prs.setInt(5, prixVenteProduitShop.getShop().getCode());
                prs.execute();
            }
            prs.close();
            conexao.close();
        }
        return true;
    }

    public boolean exclurePrixVenteProduit(int codePrixVenteProduit) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("DELETE FROM prixventeproduit WHERE code=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codePrixVenteProduit);

            prs.execute();
            prs.close();
            conexao.close();
        }

        return true;
    }

}
