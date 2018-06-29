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

            scriptSQL = new StringBuilder("SELECT entreestock.code, entreestock.dateHeure,");
            scriptSQL.append(" entreestock.idTauxCarte, tauxcarte.valeur,");
            scriptSQL.append(" entreestock.idFournisseur, fournisseur.entreprise, fournisseur.responsable, fournisseur.telephone");
            scriptSQL.append(" FROM entreestock");
            scriptSQL.append(" LEFT JOIN tauxmonnaie ON entreestock.idTauxmonnaie = tauxmonnaie.code");
            scriptSQL.append(" LEFT JOIN fournisseur ON entreestock.idFournisseur = fournisseur.code");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {

                    EntreeStock entreeStock = new EntreeStock(res.getInt(1));
                    entreeStock.setDateHeure(res.getTimestamp(2));

                    TauxMonnaie tauxMonnaie = new TauxMonnaie(res.getInt(3));
                    tauxMonnaie.setValeur(res.getBigDecimal(4));
                    entreeStock.setTauxMonnaie(tauxMonnaie);

                    Fournisseur fournisseur = new Fournisseur(res.getInt(5));
                    fournisseur.setEntreprise(res.getString(6));
                    fournisseur.setResponsable(res.getString(7));
                    fournisseur.setTelephone(res.getString(8));
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

            scriptSQL = new StringBuilder("SELECT itementreestock.quantite,");
            scriptSQL.append(" itementreestock.idEntreeStock, entreestock.dateHeure,");
            scriptSQL.append(" entreestock.idTauxMonnaie, tauxmonnaie.valeur,");
            scriptSQL.append(" entreestock.idFournisseur, fournisseur.entreprise, fournisseur.responsable, fournisseur.telephone,");
            scriptSQL.append(" itementreestock.idProduit, produit.description,");
            scriptSQL.append(" itementreestock.idPrixAchatProduit, prixachatproduit.valeurUSD, prixachatproduit.dateHeure");
            scriptSQL.append(" FROM itementreestock");
            scriptSQL.append(" LEFT JOIN entreestock ON itementreestock.idEntreestock = entreestock.code");
            scriptSQL.append(" LEFT JOIN tauxmonnaie ON entreestock.idTauxmonnaie = tauxcarte.code");
            scriptSQL.append(" LEFT JOIN fournisseur ON entreestock.idFournisseur = fournisseur.code");
            scriptSQL.append(" LEFT JOIN produit ON itementreestock.idProduit = produit.code");
            scriptSQL.append(" LEFT JOIN prixachatproduit ON itementreestock.idPrixAchatProduit = prixachatproduit.code");
            scriptSQL.append(" GROUP BY itementreestock.idEntreeStock, entreestock.dateHeure, entreestock.idTauxMonnaie, tauxmonnaie.valeur,");
            scriptSQL.append(" entreestock.idFournisseur, fournisseur.entreprise, fournisseur.responsable, fournisseur.telephone,");
            scriptSQL.append(" itementreestock.idProduit, produit.description, itementreestock.quantite,");
            scriptSQL.append(" itementreestock.idPrixAchatProduit, prixachatproduit.valeurUSD, prixachatproduit.dateHeure");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();
            if (res != null) {

                int code = 0;
                EntreeStock etrstck = new EntreeStock();
                List<ItemEntreeStock> listeItemsEntreeStock = new ArrayList();

                while (res.next()) {

                    Produit produit = new Produit(res.getInt(10));
                    produit.setDescription(res.getString(11));

                    EntreeStock entreeStock = new EntreeStock(res.getInt(2));
                    entreeStock.setDateHeure(res.getTimestamp(3));

                    TauxMonnaie tauxMonnaie = new TauxMonnaie(res.getInt(4));
                    tauxMonnaie.setValeur(res.getBigDecimal(5));
                    entreeStock.setTauxMonnaie(tauxMonnaie);

                    Fournisseur fournisseur = new Fournisseur(res.getInt(6));
                    fournisseur.setEntreprise(res.getString(7));
                    fournisseur.setResponsable(res.getString(8));
                    fournisseur.setTelephone(res.getString(9));
                    entreeStock.setFournisseur(fournisseur);

                    ItemEntreeStock itemEntreeStock = new ItemEntreeStock(entreeStock, produit);
                    itemEntreeStock.setQuantiteProduit(res.getBigDecimal(1));

                    PrixAchatProduit prixAchatProduit = new PrixAchatProduit(res.getInt(12));
                    prixAchatProduit.setValeurUSD(res.getBigDecimal(13));
                    prixAchatProduit.setDateHeure(res.getTimestamp(14));
                    itemEntreeStock.setPrixAchatProduit(prixAchatProduit);

                    if (code == entreeStock.getCode()) {
                        listeItemsEntreeStock.add(itemEntreeStock);
                    } else {
                        if (!res.first()) {
                            etrstck.setItemsEntreeStock(listeItemsEntreeStock);
                            listeEntreesStock.add(etrstck);
                        }
                        code = entreeStock.getCode();

                        etrstck.setCode(entreeStock.getCode());
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

    public EntreeStock selectionnerEntreeStockParCode(int codeEntreeStock) throws ClassNotFoundException, SQLException {
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
            scriptSQL.append(" LEFT JOIN entreestock ON itementreestock.idEntreestock = entreestock.code");
            scriptSQL.append(" LEFT JOIN tauxmonnaie ON entreestock.idTauxmonnaie = tauxcarte.code");
            scriptSQL.append(" LEFT JOIN fournisseur ON entreestock.idFournisseur = fournisseur.code");
            scriptSQL.append(" LEFT JOIN produit ON itementreestock.idProduit = produit.code");
            scriptSQL.append(" LEFT JOIN prixachatproduit ON itementreestock.idPrixAchatProduit = prixachatproduit.code");
            scriptSQL.append(" GROUP BY itementreestock.idEntreeStock, entreestock.dateHeure, entreestock.idTauxMonnaie, tauxmonnaie.valeur,");
            scriptSQL.append(" entreestock.idFournisseur, fournisseur.entreprise, fournisseur.responsable, fournisseur.telephone,");
            scriptSQL.append(" itementreestock.idProduit, produit.description, itementreestock.quantite,");
            scriptSQL.append(" itementreestock.idPrixAchatProduit, prixachatproduit.valeurUSD, prixachatproduit.dateHeure");
            scriptSQL.append(" WHERE itementreestock.idEntreeStock=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codeEntreeStock);
            res = prs.executeQuery();
            if (res != null) {

                while (res.next()) {

                    Produit produit = new Produit(res.getInt(10));
                    produit.setDescription(res.getString(11));

                    EntreeStock entreeStock = new EntreeStock(res.getInt(2));
                    entreeStock.setDateHeure(res.getTimestamp(3));

                    TauxMonnaie tauxMonnaie = new TauxMonnaie(res.getInt(4));
                    tauxMonnaie.setValeur(res.getBigDecimal(5));
                    entreeStock.setTauxMonnaie(tauxMonnaie);

                    Fournisseur fournisseur = new Fournisseur(res.getInt(6));
                    fournisseur.setEntreprise(res.getString(7));
                    fournisseur.setResponsable(res.getString(8));
                    fournisseur.setTelephone(res.getString(9));
                    entreeStock.setFournisseur(fournisseur);

                    ItemEntreeStock itemEntreeStock = new ItemEntreeStock(entreeStock, produit);
                    itemEntreeStock.setQuantiteProduit(res.getBigDecimal(1));

                    PrixAchatProduit prixAchatProduit = new PrixAchatProduit(res.getInt(12));
                    prixAchatProduit.setValeurUSD(res.getBigDecimal(13));
                    prixAchatProduit.setDateHeure(res.getTimestamp(14));
                    itemEntreeStock.setPrixAchatProduit(prixAchatProduit);

                    listeItemsEntreeStock.add(itemEntreeStock);

                    if (res.first()) {
                        etrstck.setCode(entreeStock.getCode());
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
            scriptSQL.append(" idFournisseur, idTauxCarte, dateHeure, code )");
            scriptSQL.append(" VALUES (?, ?, ?, ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setInt(1, entreeStock.getFournisseur().getCode());
            prs.setInt(2, entreeStock.getTauxMonnaie().getCode());
            prs.setTimestamp(3, new Timestamp(entreeStock.getDateHeure().getTime()));
            prs.setInt(4, entreeStock.getCode());
            prs.execute();

            for (ItemEntreeStock itemEntreeStock : entreeStock.getItemsEntreeStock()) {

                scriptSQL = new StringBuilder("INSERT INTO itementreestock(");
                scriptSQL.append(" idEntreeStock, idProduit, idPrixAchatProduit, quantite )");
                scriptSQL.append(" VALUES (?, ?, ?, ?)");

                prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

                prs.setInt(1, itemEntreeStock.getEntreeStock().getCode());
                prs.setInt(2, itemEntreeStock.getProduit().getCode());
                prs.setInt(3, itemEntreeStock.getPrixAchatProduit().getCode());
                prs.setBigDecimal(4, itemEntreeStock.getQuantiteProduit());
                prs.execute();
            }

            prs.close();
            conexao.close();
        }
        return true;
    }

}
