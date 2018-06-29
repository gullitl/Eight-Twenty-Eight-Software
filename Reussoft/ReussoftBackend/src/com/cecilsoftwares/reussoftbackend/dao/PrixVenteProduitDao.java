package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoftmiddleend.model.PrixVenteProduit;
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
            scriptSQL = new StringBuilder("SELECT prixventeproduit.code, prixventeproduit.valeurUSD, prixventeproduit.valeurFC, prixventeproduit.dateHeure");
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
                    prixVenteProduit.setValeurFC(res.getBigDecimal(3));
                    prixVenteProduit.setDateHeure(res.getTimestamp(4));

                    Produit produit = new Produit(res.getInt(5));
                    produit.setDescription(res.getString(6));
                    prixVenteProduit.setProduit(produit);

                    Shop shop = new Shop(res.getInt(7));
                    shop.setNom(res.getString(8));
                    shop.setAdresse(res.getString(9));
                    shop.setActive(res.getInt(10) == 0);
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
            scriptSQL = new StringBuilder("SELECT prixventeproduit.code, prixventeproduit.valeurUSD, prixventeproduit.valeurFC, prixventeproduit.dateHeure");
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
                    prixVenteProduit.setValeurFC(res.getBigDecimal(3));
                    prixVenteProduit.setDateHeure(res.getTimestamp(4));

                    Produit produit = new Produit(res.getInt(5));
                    produit.setDescription(res.getString(6));
                    prixVenteProduit.setProduit(produit);

                    Shop shop = new Shop(res.getInt(7));
                    shop.setNom(res.getString(8));
                    shop.setAdresse(res.getString(9));
                    shop.setActive(res.getInt(10) == 0);
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
            scriptSQL.append(" idProduit, idShop, valeurUSD, valeurFC, dateHeure, code)");
            scriptSQL.append(" VALUES (?, ?, ?, ?, ?, ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setInt(1, prixVenteProduit.getProduit().getCode());
            prs.setInt(2, prixVenteProduit.getShop().getCode());
            prs.setBigDecimal(3, prixVenteProduit.getValeurUSD());
            prs.setBigDecimal(4, prixVenteProduit.getValeurFC());
            prs.setTimestamp(5, new Timestamp(prixVenteProduit.getDateHeure().getTime()));
            prs.setInt(6, prixVenteProduit.getCode());

            prs.execute();
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
