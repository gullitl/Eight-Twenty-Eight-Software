package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoftmiddleend.model.ItemEntreeStock;
import com.cecilsoftwares.reussoftmiddleend.model.Fournisseur;
import com.cecilsoftwares.reussoftmiddleend.model.EntreeStock;
import com.cecilsoftwares.reussoftmiddleend.model.Produit;
import com.cecilsoftwares.reussoftmiddleend.model.TauxMonnaie;
import java.math.BigDecimal;
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

                    Fournisseur fournisseur = new Fournisseur(res.getInt(5));
                    fournisseur.setEntreprise(res.getString(6));
                    fournisseur.setResponsable(res.getString(7));
                    fournisseur.setTelephone(res.getString(8));

                    TauxMonnaie tauxMonnaie = new TauxMonnaie(res.getInt(3));
                    tauxMonnaie.setValeur(new BigDecimal(res.getString(4)));

                    EntreeStock entreeStock = new EntreeStock(res.getInt(1));
                    entreeStock.setDateHeure(res.getTimestamp(2));
                    entreeStock.setTauxMonnaie(tauxMonnaie);
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

            scriptSQL = new StringBuilder("SELECT itementreestock.quantiteProduit,");
            scriptSQL.append(" itementreestock.idEntreeStock, entreestock.dateHeure,");
            scriptSQL.append(" entreestock.idTauxmonnaie, tauxmonnaie.valeur,");
            scriptSQL.append(" entreestock.idFournisseur, fournisseur.entreprise, fournisseur.responsable, fournisseur.telephone,");
            scriptSQL.append(" itementreestock.idProduto, produit.description");
            scriptSQL.append(" FROM itementreestock");
            scriptSQL.append(" LEFT JOIN entreestock ON itementreestock.idEntreestock = entreestock.code");
            scriptSQL.append(" LEFT JOIN tauxmonnaie ON itementreestock.idTauxmonnaie = tauxcarte.code");
            scriptSQL.append(" LEFT JOIN fournisseur ON itementreestock.idFournisseur = fournisseur.code");
            scriptSQL.append(" LEFT JOIN produit ON itementreestock.idProduit = produit.code");
            scriptSQL.append(" GROUP BY itementreestock.idEntreeStock, entreestock.dateHeure, entreestock.idTauxCarte, tauxcarte.valeur,");
            scriptSQL.append(" itementreestock.prixUSD, itementreestock.prixFC, itementreestock.quantiteProduit,");
            scriptSQL.append(" entreestock.idFournisseur, fournisseur.entreprise, fournisseur.responsable, fournisseur.telephone,");
            scriptSQL.append(" itementreestock.idProduto, produit.description");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();
            if (res != null) {

                int code = 0;
                EntreeStock etrstck = new EntreeStock();
                List<ItemEntreeStock> listeItemsEntreeStock = new ArrayList();

                while (res.next()) {

                    EntreeStock entreeStock = new EntreeStock(res.getInt(4));
                    entreeStock.setDateHeure(res.getTimestamp(5));

                    TauxMonnaie tauxMonnaie = new TauxMonnaie(res.getInt(6));
                    tauxMonnaie.setValeur(new BigDecimal(res.getString(7)));
                    entreeStock.setTauxMonnaie(tauxMonnaie);

                    Fournisseur fournisseur = new Fournisseur(res.getInt(8));
                    fournisseur.setEntreprise(res.getString(9));
                    fournisseur.setResponsable(res.getString(10));
                    fournisseur.setTelephone(res.getString(11));
                    entreeStock.setFournisseur(fournisseur);

                    Produit produit = new Produit(res.getInt(12));
                    produit.setDescription(res.getString(13));

                    ItemEntreeStock itemEntreeStock = new ItemEntreeStock(entreeStock, produit);
                    itemEntreeStock.setQuantiteProduit(new BigDecimal(res.getString(1)));

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
            scriptSQL.append(" GROUP BY itementreestock.idEntreeStock, entreestock.dateHeure, entreestock.idTauxCarte, tauxcarte.valeur,");
            scriptSQL.append(" itementreestock.prixUSD, itementreestock.prixFC, itementreestock.quantiteProduit,");
            scriptSQL.append(" entreestock.idFournisseur, fournisseur.entreprise, fournisseur.responsable, fournisseur.telephone,");
            scriptSQL.append(" itementreestock.idProduto, produit.description");
            scriptSQL.append(" WHERE itementreestock.idEntreeStock=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codeEntreeStock);
            res = prs.executeQuery();
            if (res != null) {

                while (res.next()) {

                    EntreeStock entreeStock = new EntreeStock(res.getInt(4));
                    entreeStock.setDateHeure(res.getTimestamp(5));

                    TauxMonnaie tauxMonnaie = new TauxMonnaie(res.getInt(6));
                    tauxMonnaie.setValeur(new BigDecimal(res.getString(7)));
                    entreeStock.setTauxMonnaie(tauxMonnaie);

                    Fournisseur fournisseur = new Fournisseur(res.getInt(8));
                    fournisseur.setEntreprise(res.getString(9));
                    fournisseur.setResponsable(res.getString(10));
                    fournisseur.setTelephone(res.getString(11));
                    entreeStock.setFournisseur(fournisseur);

                    Produit produit = new Produit(res.getInt(12));
                    produit.setDescription(res.getString(13));

                    ItemEntreeStock itemEntreeStock = new ItemEntreeStock(entreeStock, produit);
                    itemEntreeStock.setQuantiteProduit(new BigDecimal(res.getString(1)));

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
            scriptSQL.append(" idFournisseur, idTauxCarte, dateHeure, code)");
            scriptSQL.append(" VALUES (?, ?, ?, ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setInt(1, entreeStock.getFournisseur().getCode());
            prs.setInt(2, entreeStock.getTauxMonnaie().getCode());
            prs.setTimestamp(3, new Timestamp(entreeStock.getDateHeure().getTime()));
            prs.setInt(4, entreeStock.getCode());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

}
