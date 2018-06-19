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
            scriptSQL = new StringBuilder("SELECT prixventeproduit.code, prixventeproduit.prixUSD, prixventeproduit.prixFC, prixventeproduit.dateHeure");
            scriptSQL.append(" prixventeproduit.idProduit, produit.Description,");
            scriptSQL.append(" prixVpventeproduit.idShop, shop.nom, shop.adresse, shop.active");
            scriptSQL.append(" FROM prixventeproduit");
            scriptSQL.append(" LEFT JOIN produit ON prixventeproduit.idProduit = produit.code");
            scriptSQL.append(" LEFT JOIN shop ON prixventeproduit.idShop = shop.code");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();

            listePrixVenteProduits = new ArrayList();
            if (res != null) {
                while (res.next()) {

                    Shop shop = new ShopBuilder(res.getInt(6))
                            .nom(res.getString(7))
                            .adresse(res.getString(8))
                            .active(res.getInt(9) == 0)
                            .build();

                    Produit produit = new ProduitBuilder(res.getInt(4))
                            .description(res.getString(5))
                            .build();

                    PrixVenteProduit prixVenteProduit = new PrixVenteProduitBuilder(1)
                            .prixUSD(res.getBigDecimal(2))
                            .prixFC(res.getBigDecimal(3))
                            .dateHeure(res.getTimestamp(4))
                            .produit(produit)
                            .shop(shop)
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

    public PrixVenteProduit selectionnerPrixVenteProduitParCode(int codePrixVenteProduit) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT prixventeproduit.code, prixventeproduit.prixUSD, prixventeproduit.prixFC, prixventeproduit.dateHeure");
            scriptSQL.append(" prixventeproduit.idProduit, produit.Description,");
            scriptSQL.append(" prixVenteProduit.idShop, shop.nom, shop.adresse, shop.active");
            scriptSQL.append(" FROM prixventeproduit");
            scriptSQL.append(" LEFT JOIN produit ON prixVenteProduit.idProduit = produit.code");
            scriptSQL.append(" LEFT JOIN shop ON prixventeproduit.idShop = shop.code");
            scriptSQL.append(" WHERE prixVenteProduit.code=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codePrixVenteProduit);
            res = prs.executeQuery();

            if (res != null) {
                if (res.next()) {

                    Shop shop = new ShopBuilder(res.getInt(6))
                            .nom(res.getString(7))
                            .adresse(res.getString(8))
                            .active(res.getInt(9) == 0)
                            .build();

                    Produit produit = new ProduitBuilder(res.getInt(4))
                            .description(res.getString(5))
                            .build();

                    PrixVenteProduit prixVenteProduit = new PrixVenteProduitBuilder(1)
                            .prixUSD(res.getBigDecimal(2))
                            .prixFC(res.getBigDecimal(3))
                            .dateHeure(res.getTimestamp(4))
                            .produit(produit)
                            .shop(shop)
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

    public boolean enregistrerPrixVenteProduit(PrixVenteProduit prixVenteProduit) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("INSERT INTO prixventeproduit(");
            scriptSQL.append(" idProduit, idShop, prixUSD, prixFC, dateHeure, code)");
            scriptSQL.append(" VALUES (?, ?, ?, ?, ?, ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setInt(1, prixVenteProduit.getProduit().getCode());
            prs.setInt(2, prixVenteProduit.getShop().getCode());
            prs.setBigDecimal(3, prixVenteProduit.getPrixUSD());
            prs.setBigDecimal(4, prixVenteProduit.getPrixFC());
            prs.setTimestamp(5, new Timestamp(prixVenteProduit.getDateHeure().getTime()));
            prs.setInt(6, prixVenteProduit.getCode());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }
}
