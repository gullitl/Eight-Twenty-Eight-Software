package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoftmiddleend.model.ItemEntreeStock;
import com.cecilsoftwares.reussoftmiddleend.model.Fournisseur;
import com.cecilsoftwares.reussoftmiddleend.model.Fournisseur.FournisseurBuilder;
import com.cecilsoftwares.reussoftmiddleend.model.EntreeStock;
import com.cecilsoftwares.reussoftmiddleend.model.EntreeStock.EntreeStockBuilder;
import com.cecilsoftwares.reussoftmiddleend.model.ItemEntreeStock.ItemEntreeStockBuilder;
import com.cecilsoftwares.reussoftmiddleend.model.Produit;
import com.cecilsoftwares.reussoftmiddleend.model.Produit.ProduitBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class ItemEntreeStockDao {

    private StringBuilder scriptSQL;
    private static ItemEntreeStockDao uniqueInstance;

    public ItemEntreeStockDao() {
    }

    public static synchronized ItemEntreeStockDao getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ItemEntreeStockDao();
        }
        return uniqueInstance;
    }

    public List<ItemEntreeStock> listerToutesLesItemsEntreeStocks() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<ItemEntreeStock> listeEntreeStocks;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            listeEntreeStocks = new ArrayList();

            scriptSQL = new StringBuilder("SELECT entreestock.code, entreestock.dateHeure, entreestock.observation,");
            scriptSQL.append(" entreestock.prixUSD, entreestock.prixFC, entreestock.qunatiteProduit,");
            scriptSQL.append(" entreestock.idFournisseur, fournisseur.entreprise, fournisseur.responsable,");
            scriptSQL.append(" fournisseur.telephone, fournisseur.observation,");
            scriptSQL.append(" entreestock.idProduit, produit.description, produit.observation,");
            scriptSQL.append(" entreestock.idTauxCarte, tauxcarte.dateHeure, tauxcarte.valeur, ");
            scriptSQL.append(" FROM entreestock");
            scriptSQL.append(" LEFT JOIN fournisseur ON entreestock.idFournisseur = fournisseur.code");
            scriptSQL.append(" LEFT JOIN produit ON fournisseur.idProduit = produit.code");
            scriptSQL.append(" LEFT JOIN tauxcarte ON entreestock.idTauxCarte = tauxcarte.code");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {

                    EntreeStock entreeStock = new EntreeStockBuilder(res.getInt(1)).build();

                    Fournisseur fournisseur = new FournisseurBuilder(res.getInt(6))
                            .entreprise(res.getString(7))
                            .responsable(res.getString(8))
                            .telephone(res.getString(9))
                            .observation(res.getString(10))
                            .build();

                    Produit produit = new ProduitBuilder(res.getInt(11))
                            .description(res.getString(12))
                            .observation(res.getString(13))
                            .build();

                    ItemEntreeStock itemEntreeStock = new ItemEntreeStockBuilder(entreeStock, produit)
                            .build();

                    listeEntreeStocks.add(itemEntreeStock);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listeEntreeStocks;
    }

    public List<ItemEntreeStock> listerItemsEntreeStocksParCodeEntreeStock(int codeEntreeStock) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<ItemEntreeStock> listeEntreeStocks;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            listeEntreeStocks = new ArrayList();

            scriptSQL = new StringBuilder("SELECT entreestock.code, entreestock.dateHeure, entreestock.observation,");
            scriptSQL.append(" entreestock.prixUSD, entreestock.prixFC, entreestock.qunatiteProduit,");
            scriptSQL.append(" entreestock.idFournisseur, fournisseur.entreprise, fournisseur.responsable,");
            scriptSQL.append(" fournisseur.telephone, fournisseur.observation,");
            scriptSQL.append(" entreestock.idProduit, produit.description, produit.observation,");
            scriptSQL.append(" entreestock.idTauxCarte, tauxcarte.dateHeure, tauxcarte.valeur, ");
            scriptSQL.append(" FROM entreestock");
            scriptSQL.append(" LEFT JOIN fournisseur ON entreestock.idFournisseur = fournisseur.code");
            scriptSQL.append(" LEFT JOIN produit ON fournisseur.idProduit = produit.code");
            scriptSQL.append(" LEFT JOIN tauxcarte ON entreestock.idTauxCarte = tauxcarte.code");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {

                    EntreeStock entreeStock = new EntreeStockBuilder(res.getInt(1)).build();

                    Fournisseur fournisseur = new FournisseurBuilder(res.getInt(6))
                            .entreprise(res.getString(7))
                            .responsable(res.getString(8))
                            .telephone(res.getString(9))
                            .observation(res.getString(10))
                            .build();

                    Produit produit = new ProduitBuilder(res.getInt(11))
                            .description(res.getString(12))
                            .observation(res.getString(13))
                            .build();

                    ItemEntreeStock itemEntreeStock = new ItemEntreeStockBuilder(entreeStock, produit)
                            .build();

                    listeEntreeStocks.add(itemEntreeStock);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listeEntreeStocks;
    }

    public List<ItemEntreeStock> listerItemsEntreeStocksParCodeProduit(int codeProduit) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<ItemEntreeStock> listeEntreeStocks;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            listeEntreeStocks = new ArrayList();

            scriptSQL = new StringBuilder("SELECT entreestock.code, entreestock.dateHeure, entreestock.observation,");
            scriptSQL.append(" entreestock.prixUSD, entreestock.prixFC, entreestock.qunatiteProduit,");
            scriptSQL.append(" entreestock.idFournisseur, fournisseur.entreprise, fournisseur.responsable,");
            scriptSQL.append(" fournisseur.telephone, fournisseur.observation,");
            scriptSQL.append(" entreestock.idProduit, produit.description, produit.observation,");
            scriptSQL.append(" entreestock.idTauxCarte, tauxcarte.dateHeure, tauxcarte.valeur, ");
            scriptSQL.append(" FROM entreestock");
            scriptSQL.append(" LEFT JOIN fournisseur ON entreestock.idFournisseur = fournisseur.code");
            scriptSQL.append(" LEFT JOIN produit ON fournisseur.idProduit = produit.code");
            scriptSQL.append(" LEFT JOIN tauxcarte ON entreestock.idTauxCarte = tauxcarte.code");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {

                    EntreeStock entreeStock = new EntreeStockBuilder(res.getInt(1)).build();

                    Fournisseur fournisseur = new FournisseurBuilder(res.getInt(6))
                            .entreprise(res.getString(7))
                            .responsable(res.getString(8))
                            .telephone(res.getString(9))
                            .observation(res.getString(10))
                            .build();

                    Produit produit = new ProduitBuilder(res.getInt(11))
                            .description(res.getString(12))
                            .observation(res.getString(13))
                            .build();

                    ItemEntreeStock itemEntreeStock = new ItemEntreeStockBuilder(entreeStock, produit)
                            .build();

                    listeEntreeStocks.add(itemEntreeStock);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listeEntreeStocks;
    }

    public ItemEntreeStock selectionnerItemEntreeStockParCode(int codeEntreeStock, int codeProduit) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {

            scriptSQL = new StringBuilder("SELECT entreestock.code, entreestock.dateHeure, entreestock.observation,");
            scriptSQL.append(" entreestock.prixUSD, entreestock.prixFC, entreestock.qunatiteProduit,");
            scriptSQL.append(" entreestock.idFournisseur, fournisseur.entreprise, fournisseur.responsable,");
            scriptSQL.append(" fournisseur.telephone, fournisseur.observation,");
            scriptSQL.append(" entreestock.idProduit, produit.description, produit.observation,");
            scriptSQL.append(" entreestock.idTauxCarte, tauxcarte.dateHeure, tauxcarte.valeur, ");
            scriptSQL.append(" FROM entreestock");
            scriptSQL.append(" LEFT JOIN fournisseur ON entreestock.idFournisseur = fournisseur.code");
            scriptSQL.append(" LEFT JOIN produit ON fournisseur.idProduit = produit.code");
            scriptSQL.append(" LEFT JOIN tauxcarte ON entreestock.idTauxCarte = tauxcarte.code");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codeEntreeStock);
            res = prs.executeQuery();
            if (res != null) {
                if (res.next()) {

                    EntreeStock entreeStock = new EntreeStockBuilder(res.getInt(1)).build();

                    Fournisseur fournisseur = new FournisseurBuilder(res.getInt(6))
                            .entreprise(res.getString(7))
                            .responsable(res.getString(8))
                            .telephone(res.getString(9))
                            .observation(res.getString(10))
                            .build();

                    Produit produit = new ProduitBuilder(res.getInt(11))
                            .description(res.getString(12))
                            .observation(res.getString(13))
                            .build();

                    ItemEntreeStock itemEntreeStock = new ItemEntreeStockBuilder(entreeStock, produit)
                            .build();

                    prs.close();
                    res.close();
                    conexao.close();

                    return itemEntreeStock;
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return null;
    }

    public boolean enregistrerEntreeStock(ItemEntreeStock entreeStock) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {

            scriptSQL = new StringBuilder("INSERT INTO entreestock(");
            scriptSQL.append(" dateHeure, prixAchatUSD, prixAchatFC, quantiteProduit, observation,");
            scriptSQL.append("  idFournisseur, idProduit, idTauxCarte, code)");
            scriptSQL.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setInt(1, entreeStock.getMouvementStock().getCode());
            prs.setInt(8, entreeStock.getProduit().getCode());
            prs.setBigDecimal(3, entreeStock.getPrixAchatUSD());
            prs.setBigDecimal(4, entreeStock.getPrixAchatFC());
            prs.setBigDecimal(5, entreeStock.getQuantiteProduit());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

}
