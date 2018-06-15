package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoftmiddleend.model.ItemEntreeStock;
import com.cecilsoftwares.reussoftmiddleend.model.Fournisseur;
import com.cecilsoftwares.reussoftmiddleend.model.Fournisseur.FournisseurBuilder;
import com.cecilsoftwares.reussoftmiddleend.model.EntreeStock;
import com.cecilsoftwares.reussoftmiddleend.model.EntreeStock.EntreeStockBuilder;
import com.cecilsoftwares.reussoftmiddleend.model.ItemEntreeStock.ItemEntreeStockBuilder;
import com.cecilsoftwares.reussoftmiddleend.model.Produit;
import com.cecilsoftwares.reussoftmiddleend.model.Produit.ProduitBuilder;
import com.cecilsoftwares.reussoftmiddleend.model.TauxCarte;
import com.cecilsoftwares.reussoftmiddleend.model.TauxCarte.TauxCarteBuilder;
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
public class ItemSortieStockDao {

    private StringBuilder scriptSQL;
    private static ItemSortieStockDao uniqueInstance;

    public ItemSortieStockDao() {
    }

    public static synchronized ItemSortieStockDao getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ItemSortieStockDao();
        }
        return uniqueInstance;
    }

    public List<ItemEntreeStock> listerToutesLesItemsEntreeStocks() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<ItemEntreeStock> listeItemsEntreeStock;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            listeItemsEntreeStock = new ArrayList();

            scriptSQL = new StringBuilder("SELECT itementreestock.prixUSD, itementreestock.prixFC, itementreestock.quantiteProduit,");
            scriptSQL.append(" itementreestock.idEntreeStock, entreestock.dateHeure,");
            scriptSQL.append(" entreestock.idTauxCarte, tauxcarte.valeur,");
            scriptSQL.append(" entreestock.idFournisseur, fournisseur.entreprise, fournisseur.responsable, fournisseur.telephone,");
            scriptSQL.append(" itementreestock.idProduto, produit.description");
            scriptSQL.append(" FROM itementreestock");
            scriptSQL.append(" LEFT JOIN entreestock ON itementreestock.idEntreestock = entreestock.code");
            scriptSQL.append(" LEFT JOIN tauxcarte ON entreestock.idTauxCarte = tauxcarte.code");
            scriptSQL.append(" LEFT JOIN fournisseur ON entreestock.idFournisseur = fournisseur.code");
            scriptSQL.append(" LEFT JOIN produit ON itementreestock.idProduit = produit.code");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {

                    Produit produit = new ProduitBuilder(res.getInt(12))
                            .description(res.getString(13))
                            .build();

                    Fournisseur fournisseur = new FournisseurBuilder(res.getInt(8))
                            .entreprise(res.getString(9))
                            .responsable(res.getString(10))
                            .telephone(res.getString(11))
                            .build();

                    TauxCarte tauxCarte = new TauxCarteBuilder(res.getInt(6))
                            .valeur(new BigDecimal(res.getString(7)))
                            .build();

                    EntreeStock entreeStock = new EntreeStockBuilder(res.getInt(4))
                            .dateHeure(res.getTimestamp(5))
                            .tauxCarte(tauxCarte)
                            .fournisseur(fournisseur)
                            .build();

                    ItemEntreeStock itemEntreeStock = new ItemEntreeStockBuilder(entreeStock, produit)
                            .prixAchatUSD(new BigDecimal(res.getString(1)))
                            .prixAchatFC(new BigDecimal(res.getString(2)))
                            .quantiteProduit(new BigDecimal(res.getString(3)))
                            .build();

                    listeItemsEntreeStock.add(itemEntreeStock);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listeItemsEntreeStock;
    }

    public List<ItemEntreeStock> listerItemsEntreeStocksParCodeEntreeStock(int codeEntreeStock) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<ItemEntreeStock> listeItemsEntreeStock;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            listeItemsEntreeStock = new ArrayList();

            scriptSQL = new StringBuilder("SELECT itementreestock.prixUSD, itementreestock.prixFC, itementreestock.quantiteProduit,");
            scriptSQL.append(" itementreestock.idEntreeStock, entreestock.dateHeure,");
            scriptSQL.append(" entreestock.idTauxCarte, tauxcarte.valeur,");
            scriptSQL.append(" entreestock.idFournisseur, fournisseur.entreprise, fournisseur.responsable, fournisseur.telephone,");
            scriptSQL.append(" itementreestock.idProduto, produit.description");
            scriptSQL.append(" FROM itementreestock");
            scriptSQL.append(" LEFT JOIN entreestock ON itementreestock.idEntreestock = entreestock.code");
            scriptSQL.append(" LEFT JOIN tauxcarte ON entreestock.idTauxCarte = tauxcarte.code");
            scriptSQL.append(" LEFT JOIN fournisseur ON entreestock.idFournisseur = fournisseur.code");
            scriptSQL.append(" LEFT JOIN produit ON itementreestock.idProduit = produit.code");
            scriptSQL.append(" WHERE entreestock.code=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codeEntreeStock);
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {

                    Produit produit = new ProduitBuilder(res.getInt(12))
                            .description(res.getString(13))
                            .build();

                    Fournisseur fournisseur = new FournisseurBuilder(res.getInt(8))
                            .entreprise(res.getString(9))
                            .responsable(res.getString(10))
                            .telephone(res.getString(11))
                            .build();

                    TauxCarte tauxCarte = new TauxCarteBuilder(res.getInt(6))
                            .valeur(new BigDecimal(res.getString(7)))
                            .build();

                    EntreeStock entreeStock = new EntreeStockBuilder(res.getInt(4))
                            .dateHeure(res.getTimestamp(5))
                            .tauxCarte(tauxCarte)
                            .fournisseur(fournisseur)
                            .build();

                    ItemEntreeStock itemEntreeStock = new ItemEntreeStockBuilder(entreeStock, produit)
                            .prixAchatUSD(new BigDecimal(res.getString(1)))
                            .prixAchatFC(new BigDecimal(res.getString(2)))
                            .quantiteProduit(new BigDecimal(res.getString(3)))
                            .build();

                    listeItemsEntreeStock.add(itemEntreeStock);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listeItemsEntreeStock;
    }

    public List<ItemEntreeStock> listerItemsEntreeStocksParCodeProduit(int codeProduit) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<ItemEntreeStock> listeItemsEntreeStock;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            listeItemsEntreeStock = new ArrayList();

            scriptSQL = new StringBuilder("SELECT itementreestock.prixUSD, itementreestock.prixFC, itementreestock.quantiteProduit,");
            scriptSQL.append(" itementreestock.idEntreeStock, entreestock.dateHeure,");
            scriptSQL.append(" entreestock.idTauxCarte, tauxcarte.valeur,");
            scriptSQL.append(" entreestock.idFournisseur, fournisseur.entreprise, fournisseur.responsable, fournisseur.telephone,");
            scriptSQL.append(" itementreestock.idProduto, produit.description");
            scriptSQL.append(" FROM itementreestock");
            scriptSQL.append(" LEFT JOIN entreestock ON itementreestock.idEntreestock = entreestock.code");
            scriptSQL.append(" LEFT JOIN tauxcarte ON entreestock.idTauxCarte = tauxcarte.code");
            scriptSQL.append(" LEFT JOIN fournisseur ON entreestock.idFournisseur = fournisseur.code");
            scriptSQL.append(" LEFT JOIN produit ON itementreestock.idProduit = produit.code");
            scriptSQL.append(" WHERE produit.code=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codeProduit);
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {

                    Produit produit = new ProduitBuilder(res.getInt(12))
                            .description(res.getString(13))
                            .build();

                    Fournisseur fournisseur = new FournisseurBuilder(res.getInt(8))
                            .entreprise(res.getString(9))
                            .responsable(res.getString(10))
                            .telephone(res.getString(11))
                            .build();

                    TauxCarte tauxCarte = new TauxCarteBuilder(res.getInt(6))
                            .valeur(new BigDecimal(res.getString(7)))
                            .build();

                    EntreeStock entreeStock = new EntreeStockBuilder(res.getInt(4))
                            .dateHeure(res.getTimestamp(5))
                            .tauxCarte(tauxCarte)
                            .fournisseur(fournisseur)
                            .build();

                    ItemEntreeStock itemEntreeStock = new ItemEntreeStockBuilder(entreeStock, produit)
                            .prixAchatUSD(new BigDecimal(res.getString(1)))
                            .prixAchatFC(new BigDecimal(res.getString(2)))
                            .quantiteProduit(new BigDecimal(res.getString(3)))
                            .build();

                    listeItemsEntreeStock.add(itemEntreeStock);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listeItemsEntreeStock;
    }

    public ItemEntreeStock selectionnerItemEntreeStockParCodeEntreeStockECodeProduit(int codeEntreeStock, int codeProduit) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {

            scriptSQL = new StringBuilder("SELECT itementreestock.prixUSD, itementreestock.prixFC, itementreestock.quantiteProduit,");
            scriptSQL.append(" itementreestock.idEntreeStock, entreestock.dateHeure,");
            scriptSQL.append(" entreestock.idTauxCarte, tauxcarte.valeur,");
            scriptSQL.append(" entreestock.idFournisseur, fournisseur.entreprise, fournisseur.responsable, fournisseur.telephone,");
            scriptSQL.append(" itementreestock.idProduto, produit.description");
            scriptSQL.append(" FROM itementreestock");
            scriptSQL.append(" LEFT JOIN entreestock ON itementreestock.idEntreestock = entreestock.code");
            scriptSQL.append(" LEFT JOIN tauxcarte ON entreestock.idTauxCarte = tauxcarte.code");
            scriptSQL.append(" LEFT JOIN fournisseur ON entreestock.idFournisseur = fournisseur.code");
            scriptSQL.append(" LEFT JOIN produit ON itementreestock.idProduit = produit.code");
            scriptSQL.append(" WHERE entreestock.code=? AND produit.code=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codeEntreeStock);
            prs.setInt(2, codeProduit);
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {

                    Produit produit = new ProduitBuilder(res.getInt(12))
                            .description(res.getString(13))
                            .build();

                    Fournisseur fournisseur = new FournisseurBuilder(res.getInt(8))
                            .entreprise(res.getString(9))
                            .responsable(res.getString(10))
                            .telephone(res.getString(11))
                            .build();

                    TauxCarte tauxCarte = new TauxCarteBuilder(res.getInt(6))
                            .valeur(new BigDecimal(res.getString(7)))
                            .build();

                    EntreeStock entreeStock = new EntreeStockBuilder(res.getInt(4))
                            .dateHeure(res.getTimestamp(5))
                            .tauxCarte(tauxCarte)
                            .fournisseur(fournisseur)
                            .build();

                    ItemEntreeStock itemEntreeStock = new ItemEntreeStockBuilder(entreeStock, produit)
                            .prixAchatUSD(new BigDecimal(res.getString(1)))
                            .prixAchatFC(new BigDecimal(res.getString(2)))
                            .quantiteProduit(new BigDecimal(res.getString(3)))
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

    public boolean enregistrerItemEntreeStock(ItemEntreeStock itemEntreeStock) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {

            scriptSQL = new StringBuilder("INSERT INTO itementreestock(");
            scriptSQL.append(" idEntreeStock, idProduit, prixAchatUSD, prixAchatFC, quantiteProduit )");
            scriptSQL.append(" VALUES (?, ?, ?, ?, ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setInt(1, itemEntreeStock.getEntreeStock().getCode());
            prs.setInt(8, itemEntreeStock.getProduit().getCode());
            prs.setBigDecimal(3, itemEntreeStock.getPrixAchatUSD());
            prs.setBigDecimal(4, itemEntreeStock.getPrixAchatFC());
            prs.setBigDecimal(5, itemEntreeStock.getQuantiteProduit());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

}