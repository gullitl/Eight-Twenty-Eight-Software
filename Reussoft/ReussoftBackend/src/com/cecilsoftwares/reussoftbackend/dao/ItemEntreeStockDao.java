package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoftmiddleend.model.ItemEntreeStock;
import com.cecilsoftwares.reussoftmiddleend.model.Fournisseur;
import com.cecilsoftwares.reussoftmiddleend.model.EntreeStock;
import com.cecilsoftwares.reussoftmiddleend.model.Produit;
import com.cecilsoftwares.reussoftmiddleend.model.TauxCarte;
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

    public List<ItemEntreeStock> listerTousLesItemsEntreeStock() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<ItemEntreeStock> listeItemsEntreeStock;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            listeItemsEntreeStock = new ArrayList();

            scriptSQL = new StringBuilder("SELECT itementreestock.quantiteProduit,");
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

                    EntreeStock entreeStock = new EntreeStock(res.getInt(4));
                    entreeStock.setDateHeure(res.getTimestamp(5));

                    TauxCarte tauxCarte = new TauxCarte(res.getInt(6));
                    tauxCarte.setValeur(new BigDecimal(res.getString(7)));
                    entreeStock.setTauxCarte(tauxCarte);

                    Fournisseur fournisseur = new Fournisseur(res.getInt(8));
                    fournisseur.setEntreprise(res.getString(9));
                    fournisseur.setResponsable(res.getString(10));
                    fournisseur.setTelephone(res.getString(11));
                    entreeStock.setFournisseur(fournisseur);

                    Produit produit = new Produit(res.getInt(12));
                    produit.setDescription(res.getString(13));

                    ItemEntreeStock itemEntreeStock = new ItemEntreeStock(entreeStock, produit);
                    itemEntreeStock.setQuantiteProduit(new BigDecimal(res.getString(3)));

                    listeItemsEntreeStock.add(itemEntreeStock);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listeItemsEntreeStock;
    }

    public List<ItemEntreeStock> listerItemsEntreeStockParCodeEntreeStock(int codeEntreeStock) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<ItemEntreeStock> listeItemsEntreeStock;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            listeItemsEntreeStock = new ArrayList();

            scriptSQL = new StringBuilder("SELECT itementreestock.quantiteProduit,");
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

                    EntreeStock entreeStock = new EntreeStock(res.getInt(4));
                    entreeStock.setDateHeure(res.getTimestamp(5));

                    TauxCarte tauxCarte = new TauxCarte(res.getInt(6));
                    tauxCarte.setValeur(new BigDecimal(res.getString(7)));
                    entreeStock.setTauxCarte(tauxCarte);

                    Fournisseur fournisseur = new Fournisseur(res.getInt(8));
                    fournisseur.setEntreprise(res.getString(9));
                    fournisseur.setResponsable(res.getString(10));
                    fournisseur.setTelephone(res.getString(11));
                    entreeStock.setFournisseur(fournisseur);

                    Produit produit = new Produit(res.getInt(12));
                    produit.setDescription(res.getString(13));

                    ItemEntreeStock itemEntreeStock = new ItemEntreeStock(entreeStock, produit);
                    itemEntreeStock.setQuantiteProduit(new BigDecimal(res.getString(3)));

                    listeItemsEntreeStock.add(itemEntreeStock);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listeItemsEntreeStock;
    }

    public List<ItemEntreeStock> listerItemsEntreeStockParCodeProduit(int codeProduit) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<ItemEntreeStock> listeItemsEntreeStock;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            listeItemsEntreeStock = new ArrayList();

            scriptSQL = new StringBuilder("SELECT itementreestock.quantiteProduit,");
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

                    EntreeStock entreeStock = new EntreeStock(res.getInt(4));
                    entreeStock.setDateHeure(res.getTimestamp(5));

                    TauxCarte tauxCarte = new TauxCarte(res.getInt(6));
                    tauxCarte.setValeur(new BigDecimal(res.getString(7)));
                    entreeStock.setTauxCarte(tauxCarte);

                    Fournisseur fournisseur = new Fournisseur(res.getInt(8));
                    fournisseur.setEntreprise(res.getString(9));
                    fournisseur.setResponsable(res.getString(10));
                    fournisseur.setTelephone(res.getString(11));
                    entreeStock.setFournisseur(fournisseur);

                    Produit produit = new Produit(res.getInt(12));
                    produit.setDescription(res.getString(13));

                    ItemEntreeStock itemEntreeStock = new ItemEntreeStock(entreeStock, produit);
                    itemEntreeStock.setQuantiteProduit(new BigDecimal(res.getString(3)));

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

            scriptSQL = new StringBuilder("SELECT itementreestock.quantiteProduit,");
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

                    EntreeStock entreeStock = new EntreeStock(res.getInt(4));
                    entreeStock.setDateHeure(res.getTimestamp(5));

                    TauxCarte tauxCarte = new TauxCarte(res.getInt(6));
                    tauxCarte.setValeur(new BigDecimal(res.getString(7)));
                    entreeStock.setTauxCarte(tauxCarte);

                    Fournisseur fournisseur = new Fournisseur(res.getInt(8));
                    fournisseur.setEntreprise(res.getString(9));
                    fournisseur.setResponsable(res.getString(10));
                    fournisseur.setTelephone(res.getString(11));
                    entreeStock.setFournisseur(fournisseur);

                    Produit produit = new Produit(res.getInt(12));
                    produit.setDescription(res.getString(13));

                    ItemEntreeStock itemEntreeStock = new ItemEntreeStock(entreeStock, produit);
                    itemEntreeStock.setQuantiteProduit(new BigDecimal(res.getString(3)));

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
            scriptSQL.append(" idEntreeStock, idProduit, quantiteProduit )");
            scriptSQL.append(" VALUES (?, ?, ?, ?, ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setInt(1, itemEntreeStock.getEntreeStock().getCode());
            prs.setInt(8, itemEntreeStock.getProduit().getCode());
            prs.setBigDecimal(5, itemEntreeStock.getQuantiteProduit());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

}
