package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoftmiddleend.model.ItemEntreeStock;
import com.cecilsoftwares.reussoftmiddleend.model.Fournisseur;
import com.cecilsoftwares.reussoftmiddleend.model.EntreeStock;
import com.cecilsoftwares.reussoftmiddleend.model.PrixAchatProduit;
import com.cecilsoftwares.reussoftmiddleend.model.Produit;
import com.cecilsoftwares.reussoftmiddleend.model.TauxMonnaie;
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

            scriptSQL = new StringBuilder("SELECT itementreestock.quantite,");
            scriptSQL.append(" itementreestock.idEntreeStock, entreestock.dateHeure,");
            scriptSQL.append(" entreestock.idTauxMonnaie, tauxmonnaie.valeur,");
            scriptSQL.append(" entreestock.idFournisseur, fournisseur.entreprise, fournisseur.responsable, fournisseur.telephone,");
            scriptSQL.append(" itementreestock.idProduit, produit.description,");
            scriptSQL.append(" itementreestock.idPrixAchatProduit, prixachatproduit.valeurUSD, prixachatproduit.dateHeure");
            scriptSQL.append(" FROM itementreestock");
            scriptSQL.append(" LEFT JOIN entreestock ON itementreestock.idEntreestock = entreestock.id");
            scriptSQL.append(" LEFT JOIN tauxmonnaie ON entreestock.idTauxmonnaie = tauxcarte.id");
            scriptSQL.append(" LEFT JOIN fournisseur ON entreestock.idFournisseur = fournisseur.id");
            scriptSQL.append(" LEFT JOIN produit ON itementreestock.idProduit = produit.id");
            scriptSQL.append(" LEFT JOIN prixachatproduit ON itementreestock.idPrixAchatProduit = prixachatproduit.id");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {

                    EntreeStock entreeStock = new EntreeStock(res.getString(2));
                    entreeStock.setDateHeure(res.getTimestamp(3));

                    TauxMonnaie tauxMonnaie = new TauxMonnaie(res.getString(4));
                    tauxMonnaie.setValeur(res.getBigDecimal(5));
                    entreeStock.setTauxMonnaie(tauxMonnaie);

                    Fournisseur fournisseur = new Fournisseur(res.getString(6));
                    fournisseur.setEntreprise(res.getString(7));
                    fournisseur.setResponsable(res.getString(8));
                    fournisseur.setTelephone(res.getString(9));
                    entreeStock.setFournisseur(fournisseur);

                    Produit produit = new Produit(res.getString(10));
                    produit.setDescription(res.getString(11));

                    PrixAchatProduit prixAchatProduit = new PrixAchatProduit(res.getString(12));
                    prixAchatProduit.setValeurUSD(res.getBigDecimal(13));
                    prixAchatProduit.setDateHeure(res.getTimestamp(14));
                    produit.setPrixAchatProduit(prixAchatProduit);

                    ItemEntreeStock itemEntreeStock = new ItemEntreeStock(entreeStock, produit);
                    itemEntreeStock.setQuantiteProduit(res.getBigDecimal(1));

                    listeItemsEntreeStock.add(itemEntreeStock);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listeItemsEntreeStock;
    }

    public List<ItemEntreeStock> listerItemsEntreeStockParIdEntreeStock(String idEntreeStock) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<ItemEntreeStock> listeItemsEntreeStock;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            listeItemsEntreeStock = new ArrayList();

            scriptSQL = new StringBuilder("SELECT itementreestock.quantite,");
            scriptSQL.append(" itementreestock.idEntreeStock, entreestock.dateHeure,");
            scriptSQL.append(" entreestock.idTauxMonnaie, tauxmonnaie.valeur,");
            scriptSQL.append(" entreestock.idFournisseur, fournisseur.entreprise, fournisseur.responsable, fournisseur.telephone,");
            scriptSQL.append(" itementreestock.idProduit, produit.description,");
            scriptSQL.append(" itementreestock.idPrixAchatProduit, prixachatproduit.valeurUSD, prixachatproduit.dateHeure");
            scriptSQL.append(" FROM itementreestock");
            scriptSQL.append(" LEFT JOIN entreestock ON itementreestock.idEntreestock = entreestock.id");
            scriptSQL.append(" LEFT JOIN tauxmonnaie ON entreestock.idTauxmonnaie = tauxcarte.id");
            scriptSQL.append(" LEFT JOIN fournisseur ON entreestock.idFournisseur = fournisseur.id");
            scriptSQL.append(" LEFT JOIN produit ON itementreestock.idProduit = produit.id");
            scriptSQL.append(" LEFT JOIN prixachatproduit ON itementreestock.idPrixAchatProduit = prixachatproduit.id");
            scriptSQL.append(" WHERE entreestock.id=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setString(1, idEntreeStock);
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {

                    EntreeStock entreeStock = new EntreeStock(res.getString(2));
                    entreeStock.setDateHeure(res.getTimestamp(3));

                    TauxMonnaie tauxMonnaie = new TauxMonnaie(res.getString(4));
                    tauxMonnaie.setValeur(res.getBigDecimal(5));
                    entreeStock.setTauxMonnaie(tauxMonnaie);

                    Fournisseur fournisseur = new Fournisseur(res.getString(6));
                    fournisseur.setEntreprise(res.getString(7));
                    fournisseur.setResponsable(res.getString(8));
                    fournisseur.setTelephone(res.getString(9));
                    entreeStock.setFournisseur(fournisseur);

                    Produit produit = new Produit(res.getString(10));
                    produit.setDescription(res.getString(11));

                    PrixAchatProduit prixAchatProduit = new PrixAchatProduit(res.getString(12));

                    prixAchatProduit.setValeurUSD(res.getBigDecimal(13));
                    prixAchatProduit.setDateHeure(res.getTimestamp(14));
                    produit.setPrixAchatProduit(prixAchatProduit);

                    ItemEntreeStock itemEntreeStock = new ItemEntreeStock(entreeStock, produit);
                    itemEntreeStock.setQuantiteProduit(res.getBigDecimal(1));

                    listeItemsEntreeStock.add(itemEntreeStock);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listeItemsEntreeStock;
    }

    public List<ItemEntreeStock> listerItemsEntreeStockParIdProduit(String idProduit) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<ItemEntreeStock> listeItemsEntreeStock;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            listeItemsEntreeStock = new ArrayList();

            scriptSQL = new StringBuilder("SELECT itementreestock.quantite,");
            scriptSQL.append(" itementreestock.idEntreeStock, entreestock.dateHeure,");
            scriptSQL.append(" entreestock.idTauxMonnaie, tauxmonnaie.valeur,");
            scriptSQL.append(" entreestock.idFournisseur, fournisseur.entreprise, fournisseur.responsable, fournisseur.telephone,");
            scriptSQL.append(" itementreestock.idProduit, produit.description,");
            scriptSQL.append(" itementreestock.idPrixAchatProduit, prixachatproduit.valeurUSD, prixachatproduit.dateHeure");
            scriptSQL.append(" FROM itementreestock");
            scriptSQL.append(" LEFT JOIN entreestock ON itementreestock.idEntreestock = entreestock.id");
            scriptSQL.append(" LEFT JOIN tauxmonnaie ON entreestock.idTauxmonnaie = tauxcarte.id");
            scriptSQL.append(" LEFT JOIN fournisseur ON entreestock.idFournisseur = fournisseur.id");
            scriptSQL.append(" LEFT JOIN produit ON itementreestock.idProduit = produit.id");
            scriptSQL.append(" LEFT JOIN prixachatproduit ON itementreestock.idPrixAchatProduit = prixachatproduit.id");
            scriptSQL.append(" WHERE produit.id=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setString(1, idProduit);
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {

                    EntreeStock entreeStock = new EntreeStock(res.getString(2));
                    entreeStock.setDateHeure(res.getTimestamp(3));

                    TauxMonnaie tauxMonnaie = new TauxMonnaie(res.getString(4));
                    tauxMonnaie.setValeur(res.getBigDecimal(5));
                    entreeStock.setTauxMonnaie(tauxMonnaie);

                    Fournisseur fournisseur = new Fournisseur(res.getString(6));
                    fournisseur.setEntreprise(res.getString(7));
                    fournisseur.setResponsable(res.getString(8));
                    fournisseur.setTelephone(res.getString(9));
                    entreeStock.setFournisseur(fournisseur);

                    Produit produit = new Produit(res.getString(10));
                    produit.setDescription(res.getString(11));

                    PrixAchatProduit prixAchatProduit = new PrixAchatProduit(res.getString(12));
                    prixAchatProduit.setValeurUSD(res.getBigDecimal(13));
                    prixAchatProduit.setDateHeure(res.getTimestamp(14));
                    produit.setPrixAchatProduit(prixAchatProduit);

                    ItemEntreeStock itemEntreeStock = new ItemEntreeStock(entreeStock, produit);
                    itemEntreeStock.setQuantiteProduit(res.getBigDecimal(1));

                    listeItemsEntreeStock.add(itemEntreeStock);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listeItemsEntreeStock;
    }

    public ItemEntreeStock selectionnerItemEntreeStockParIdEntreeStockEIdProduit(String idEntreeStock, String idProduit) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {

            scriptSQL = new StringBuilder("SELECT itementreestock.quantite,");
            scriptSQL.append(" itementreestock.idEntreeStock, entreestock.dateHeure,");
            scriptSQL.append(" entreestock.idTauxMonnaie, tauxmonnaie.valeur,");
            scriptSQL.append(" entreestock.idFournisseur, fournisseur.entreprise, fournisseur.responsable, fournisseur.telephone,");
            scriptSQL.append(" itementreestock.idProduit, produit.description,");
            scriptSQL.append(" itementreestock.idPrixAchatProduit, prixachatproduit.valeurUSD, prixachatproduit.dateHeure");
            scriptSQL.append(" FROM itementreestock");
            scriptSQL.append(" LEFT JOIN entreestock ON itementreestock.idEntreestock = entreestock.id");
            scriptSQL.append(" LEFT JOIN tauxmonnaie ON entreestock.idTauxmonnaie = tauxcarte.id");
            scriptSQL.append(" LEFT JOIN fournisseur ON entreestock.idFournisseur = fournisseur.id");
            scriptSQL.append(" LEFT JOIN produit ON itementreestock.idProduit = produit.id");
            scriptSQL.append(" LEFT JOIN prixachatproduit ON itementreestock.idPrixAchatProduit = prixachatproduit.id");
            scriptSQL.append(" WHERE entreestock.id=? AND produit.id=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setString(1, idEntreeStock);
            prs.setString(2, idProduit);
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {

                    EntreeStock entreeStock = new EntreeStock(res.getString(2));
                    entreeStock.setDateHeure(res.getTimestamp(3));

                    TauxMonnaie tauxMonnaie = new TauxMonnaie(res.getString(4));
                    tauxMonnaie.setValeur(res.getBigDecimal(5));
                    entreeStock.setTauxMonnaie(tauxMonnaie);

                    Fournisseur fournisseur = new Fournisseur(res.getString(6));
                    fournisseur.setEntreprise(res.getString(7));
                    fournisseur.setResponsable(res.getString(8));
                    fournisseur.setTelephone(res.getString(9));
                    entreeStock.setFournisseur(fournisseur);

                    Produit produit = new Produit(res.getString(10));
                    produit.setDescription(res.getString(11));

                    PrixAchatProduit prixAchatProduit = new PrixAchatProduit(res.getString(12));
                    prixAchatProduit.setValeurUSD(res.getBigDecimal(13));
                    prixAchatProduit.setDateHeure(res.getTimestamp(14));
                    produit.setPrixAchatProduit(prixAchatProduit);

                    ItemEntreeStock itemEntreeStock = new ItemEntreeStock(entreeStock, produit);
                    itemEntreeStock.setQuantiteProduit(res.getBigDecimal(1));

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

}
