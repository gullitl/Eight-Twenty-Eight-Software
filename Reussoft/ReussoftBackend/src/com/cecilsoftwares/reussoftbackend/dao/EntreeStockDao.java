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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class EntreeStockDao {

    private StringBuilder scriptSQL;
    private static EntreeStockDao uniqueInstance;

    public EntreeStockDao() {
    }

    public static synchronized EntreeStockDao getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new EntreeStockDao();
        }
        return uniqueInstance;
    }

    public List<EntreeStock> listerTousLesEntreesStockSansItems() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<EntreeStock> listeEntreesStock;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            listeEntreesStock = new ArrayList();

            scriptSQL = new StringBuilder("SELECT entreestock.id, entreestock.numeroEntreeStock, entreestock.dateHeure,");
            scriptSQL.append(" entreestock.valeuTotalCoutUSD, entreestock.valeurTotalCoutFC, entreestock.valeurTauxCarte,");
            scriptSQL.append(" entreestock.idFournisseur, fournisseur.entreprise, fournisseur.responsable, fournisseur.telephone");
            scriptSQL.append(" FROM entreestock");
            scriptSQL.append(" LEFT JOIN fournisseur ON entreestock.idFournisseur = fournisseur.id");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {

                    EntreeStock entreeStock = new EntreeStock(res.getString(1));
                    entreeStock.setNumeroEntreeStock(res.getString(2));
                    entreeStock.setDateHeure(res.getTimestamp(3));
                    entreeStock.setValeurTotalCoutUSD(res.getBigDecimal(4));
                    entreeStock.setValeurTotalCoutFC(res.getBigDecimal(5));
                    entreeStock.setValeurTauxCarte(res.getBigDecimal(6));

                    Fournisseur fournisseur = new Fournisseur(res.getString(7));
                    fournisseur.setEntreprise(res.getString(8));
                    fournisseur.setResponsable(res.getString(9));
                    fournisseur.setTelephone(res.getString(10));
                    entreeStock.setFournisseur(fournisseur);

                    listeEntreesStock.add(entreeStock);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listeEntreesStock;
    }

    public List<EntreeStock> listerTousLesEntreesStockAvecItems() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<EntreeStock> listeEntreesStock;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            listeEntreesStock = new ArrayList();

            scriptSQL = new StringBuilder("SELECT itementreestock.quantite");
            scriptSQL.append(" itementreestock.idEntreeStock, entreestock.valeuTotalCoutUSD, entreestock.valeurTotalCoutFC,");
            scriptSQL.append(" entreestock.valeurTauxCarte, entreestock.dateHeure, entreestock.numeroEntreeStock,");
            scriptSQL.append(" entreestock.idFournisseur, fournisseur.entreprise, fournisseur.responsable, fournisseur.telephone,");
            scriptSQL.append(" itementreestock.idProduit, produit.description,");
            scriptSQL.append(" itementreestock.idPrixAchatProduit, prixachatproduit.valeurUSD, prixachatproduit.dateHeure");
            scriptSQL.append(" FROM itementreestock");
            scriptSQL.append(" LEFT JOIN entreestock ON itementreestock.idEntreestock = entreestock.id");
            scriptSQL.append(" LEFT JOIN fournisseur ON entreestock.idFournisseur = fournisseur.id");
            scriptSQL.append(" LEFT JOIN produit ON itementreestock.idProduit = produit.id");
            scriptSQL.append(" LEFT JOIN prixachatproduit ON itementreestock.idPrixAchatProduit = prixachatproduit.id");
            scriptSQL.append(" GROUP BY itementreestock.quantite, itementreestock.idEntreeStock, entreestock.valeuTotalCoutUSD, entreestock.valeurTotalCoutFC,");
            scriptSQL.append(" entreestock.valeurTauxCarte, entreestock.dateHeure, entreestock.numeroEntreeStock,");
            scriptSQL.append(" entreestock.idFournisseur, fournisseur.entreprise, fournisseur.responsable, fournisseur.telephone,");
            scriptSQL.append(" itementreestock.idProduit, produit.description,");
            scriptSQL.append(" itementreestock.idPrixAchatProduit, prixachatproduit.valeurUSD, prixachatproduit.dateHeure");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();
            if (res != null) {

                String id = "";
                EntreeStock etrstck = new EntreeStock();
                List<ItemEntreeStock> listeItemsEntreeStock = new ArrayList();

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

                    if (id.equals(entreeStock.getId())) {
                        listeItemsEntreeStock.add(itemEntreeStock);
                    } else {
                        if (!res.first()) {
                            etrstck.setItemsEntreeStock(listeItemsEntreeStock);
                            listeEntreesStock.add(etrstck);
                        }
                        id = entreeStock.getId();

                        etrstck.setId(entreeStock.getId());
                        etrstck.setDateHeure(entreeStock.getDateHeure());
                        etrstck.setTauxMonnaie(entreeStock.getTauxMonnaie());
                        etrstck.setFournisseur(entreeStock.getFournisseur());

                        listeItemsEntreeStock = new ArrayList();
                        listeItemsEntreeStock.add(itemEntreeStock);
                    }
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listeEntreesStock;
    }

    public EntreeStock selectionnerEntreeStockParId(String idEntreeStock) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        EntreeStock etrstck = new EntreeStock();
        List<ItemEntreeStock> listeItemsEntreeStock = new ArrayList();

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
            scriptSQL.append(" GROUP BY itementreestock.idEntreeStock, entreestock.dateHeure, entreestock.idTauxMonnaie, tauxmonnaie.valeur,");
            scriptSQL.append(" entreestock.idFournisseur, fournisseur.entreprise, fournisseur.responsable, fournisseur.telephone,");
            scriptSQL.append(" itementreestock.idProduit, produit.description, itementreestock.quantite,");
            scriptSQL.append(" itementreestock.idPrixAchatProduit, prixachatproduit.valeurUSD, prixachatproduit.dateHeure");
            scriptSQL.append(" WHERE itementreestock.idEntreeStock=?");

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

                    if (res.first()) {
                        etrstck.setId(entreeStock.getId());
                        etrstck.setDateHeure(entreeStock.getDateHeure());
                        etrstck.setTauxMonnaie(entreeStock.getTauxMonnaie());
                        etrstck.setFournisseur(entreeStock.getFournisseur());
                    }
                }

                etrstck.setItemsEntreeStock(listeItemsEntreeStock);

            }
            prs.close();
            res.close();
            conexao.close();
        }
        return etrstck;
    }

    public boolean enregistrerEntreeStock(EntreeStock entreeStock) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {

            scriptSQL = new StringBuilder("INSERT INTO entreestock(");
            scriptSQL.append(" idFournisseur, valeuTotalCoutUSD, valeurTotalCoutFC, valeurTauxCarte, dateHeure, numeroEntreeStock, id )");
            scriptSQL.append(" VALUES (?, ?, ?, ?, ?, ?, ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setString(1, entreeStock.getFournisseur().getId());
            prs.setBigDecimal(2, entreeStock.getValeurTotalCoutUSD());
            prs.setBigDecimal(3, entreeStock.getValeurTotalCoutFC());
            prs.setBigDecimal(4, entreeStock.getValeurTauxCarte());
            prs.setTimestamp(5, new Timestamp(entreeStock.getDateHeure().getTime()));
            prs.setString(6, entreeStock.getNumeroEntreeStock());
            prs.setString(7, entreeStock.getId());
            prs.execute();

            for (ItemEntreeStock itemEntreeStock : entreeStock.getItemsEntreeStock()) {

                scriptSQL = new StringBuilder("INSERT INTO itementreestock(");
                scriptSQL.append(" idEntreeStock, idProduit, idPrixAchat, quantite )");
                scriptSQL.append(" VALUES (?, ?, ?, ?)");

                prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

                prs.setString(1, itemEntreeStock.getEntreeStock().getId());
                prs.setString(2, itemEntreeStock.getProduit().getId());
                prs.setString(3, itemEntreeStock.getProduit().getPrixAchatProduit().getId());
                prs.setBigDecimal(4, itemEntreeStock.getQuantiteProduit());
                prs.execute();
            }

            prs.close();
            conexao.close();
        }
        return true;
    }

    public boolean actualiserEntreeStock(EntreeStock entreeStock) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {

            scriptSQL = new StringBuilder("UPDATE entreestock");
            scriptSQL.append(" SET idFournisseur=?, valeuTotalCoutUSD=?, valeurTotalCoutFC=?, valeurTauxCarte=?,");
            scriptSQL.append(" dateHeure=?, numeroEntreeStock=?");
            scriptSQL.append(" WHERE id=?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setString(1, entreeStock.getFournisseur().getId());
            prs.setBigDecimal(2, entreeStock.getValeurTotalCoutUSD());
            prs.setBigDecimal(3, entreeStock.getValeurTotalCoutFC());
            prs.setBigDecimal(4, entreeStock.getValeurTauxCarte());
            prs.setTimestamp(5, new Timestamp(entreeStock.getDateHeure().getTime()));
            prs.setString(6, entreeStock.getNumeroEntreeStock());
            prs.setString(7, entreeStock.getId());
            prs.execute();

            scriptSQL = new StringBuilder("DELETE FROM itementreestock WHERE idEntreeStock=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setString(1, entreeStock.getId());

            for (ItemEntreeStock itemEntreeStock : entreeStock.getItemsEntreeStock()) {

                scriptSQL = new StringBuilder("INSERT INTO itementreestock(");
                scriptSQL.append(" idEntreeStock, idProduit, idPrixAchatProduit, quantite )");
                scriptSQL.append(" VALUES (?, ?, ?, ?)");

                prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

                prs.setString(1, itemEntreeStock.getEntreeStock().getId());
                prs.setString(2, itemEntreeStock.getProduit().getId());
                prs.setString(3, itemEntreeStock.getProduit().getPrixAchatProduit().getId());
                prs.setBigDecimal(4, itemEntreeStock.getQuantiteProduit());
                prs.execute();
            }

            prs.close();
            conexao.close();
        }
        return true;
    }

}
