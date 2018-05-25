package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoftmiddleend.model.PrixVenteProduit;
import com.cecilsoftwares.reussoftmiddleend.model.PrixVenteProduit.PrixVenteProduitBuilder;
import com.cecilsoftwares.reussoftmiddleend.model.Produit;
import com.cecilsoftwares.reussoftmiddleend.model.Produit.ProduitBuilder;
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

    //valide = true
    public List<PrixVenteProduit> listerTousLesPrixVenteProduits() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<PrixVenteProduit> listePrixVenteProduits;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT prixVenteProduit.prixUSD, prixVenteProduit.prixFC, prixVenteProduit.observation");
            scriptSQL.append(" prixVenteProduit.idProduit, produit.Description,");
            scriptSQL.append(" prixVenteProduit.idShop, shop.nom, shop.adresse, shop.observation, shop.active");
            scriptSQL.append(" FROM prixVenteProduit");
            scriptSQL.append(" LEFT JOIN produit ON prixVenteProduit.idProduit = produit.code");
            scriptSQL.append(" LEFT JOIN shop ON prixVenteProduit.idShop = shop.code");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();

            listePrixVenteProduits = new ArrayList();
            if (res != null) {
                while (res.next()) {

                    Shop shop = new ShopBuilder(res.getInt(6))
                            .nom(res.getString(7))
                            .adresse(res.getString(8))
                            .observation(res.getString(9))
                            .active(res.getInt(10) == 0)
                            .build();

                    Produit produit = new ProduitBuilder(res.getInt(4))
                            .description(res.getString(5))
                            .build();

                    PrixVenteProduit prixVenteProduit = new PrixVenteProduitBuilder(produit, shop)
                            .prixUSD(res.getBigDecimal(1))
                            .prixFC(res.getBigDecimal(2))
                            .observation(res.getString(3))
                            .build();

                    listePrixVenteProduits.add(prixVenteProduit);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listePrixVenteProduits;
    }

    //valide = true
    public PrixVenteProduit selectionnerPrixVenteProduitParCode(int codePrixVenteProduit) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT prixVenteProduit.prixUSD, prixVenteProduit.prixFC, prixVenteProduit.observation");
            scriptSQL.append(" prixVenteProduit.idProduit, produit.Description,");
            scriptSQL.append(" prixVenteProduit.idShop, shop.nom, shop.adresse, shop.observation, shop.active");
            scriptSQL.append(" FROM prixVenteProduit");
            scriptSQL.append(" LEFT JOIN produit ON prixVenteProduit.idProduit = produit.code");
            scriptSQL.append(" LEFT JOIN shop ON prixVenteProduit.idShop = shop.code");
            scriptSQL.append(" WHERE prixVenteProduit.code=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codePrixVenteProduit);
            res = prs.executeQuery();

            if (res != null) {
                if (res.next()) {

                    Shop shop = new ShopBuilder(res.getInt(6))
                            .nom(res.getString(7))
                            .adresse(res.getString(8))
                            .observation(res.getString(9))
                            .active(res.getInt(10) == 0)
                            .build();

                    Produit produit = new ProduitBuilder(res.getInt(4))
                            .description(res.getString(5))
                            .build();

                    PrixVenteProduit prixVenteProduit = new PrixVenteProduitBuilder(produit, shop)
                            .prixUSD(res.getBigDecimal(1))
                            .prixFC(res.getBigDecimal(2))
                            .observation(res.getString(3))
                            .build();

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

    //Valide = true
    public boolean enregistrerPrixVenteProduit(PrixVenteProduit prixVenteProduit) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("INSERT INTO prixVenteProduit(");
            scriptSQL.append(" idProduit, idShop, prixUSD, prixFC, observation)");
            scriptSQL.append(" VALUES (?, ?, ?, ?, ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setInt(1, prixVenteProduit.getProduit().getCode());
            prs.setInt(2, prixVenteProduit.getShop().getCode());
            prs.setBigDecimal(3, prixVenteProduit.getPrixUSD());
            prs.setBigDecimal(4, prixVenteProduit.getPrixFC());
            prs.setString(5, prixVenteProduit.getObservation());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

    //valide = true
    public boolean actualiserPrixVenteProduit(PrixVenteProduit prixVenteProduit) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("UPDATE prixVenteProduit");
            scriptSQL.append(" SET prixUSD=?, prixFC=?, observation=?");
            scriptSQL.append(" WHERE idProduit=? AND idShop=?,");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setBigDecimal(1, prixVenteProduit.getPrixUSD());
            prs.setBigDecimal(2, prixVenteProduit.getPrixFC());
            prs.setString(3, prixVenteProduit.getObservation());
            prs.setInt(4, prixVenteProduit.getProduit().getCode());
            prs.setInt(5, prixVenteProduit.getShop().getCode());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

    //valide = true
    public int selectionnerCodePrixVenteProduitSubsequent() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT Max(code)+1 FROM prixVenteProduit");
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
