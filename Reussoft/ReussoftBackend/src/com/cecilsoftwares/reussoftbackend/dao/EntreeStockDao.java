package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoftbackend.service.StockProduitService;
import com.cecilsoftwares.reussoftmiddleend.model.ItemEntreeStock;
import com.cecilsoftwares.reussoftmiddleend.model.Fournisseur;
import com.cecilsoftwares.reussoftmiddleend.model.EntreeStock;
import com.cecilsoftwares.reussoftmiddleend.model.PrixAchatProduit;
import com.cecilsoftwares.reussoftmiddleend.model.Produit;
import com.cecilsoftwares.reussoftmiddleend.model.Shop;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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

        try (Connection connection = ConnectionFactory.getInstance().habiliterConnection()) {
            listeEntreesStock = new ArrayList();

            scriptSQL = new StringBuilder("SELECT entreestock.id, entreestock.numeroEntreeStock, entreestock.dateHeure,");
            scriptSQL.append(" entreestock.valeurTotalCoutUSD, entreestock.valeurTotalCoutFC, entreestock.valeurTauxCarte,");
            scriptSQL.append(" entreestock.idFournisseur, fournisseur.entreprise, fournisseur.responsable, fournisseur.telephone");
            scriptSQL.append(" FROM entreestock");
            scriptSQL.append(" LEFT JOIN fournisseur ON entreestock.idFournisseur = fournisseur.id");

            prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));
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
            connection.close();
        }
        return listeEntreesStock;
    }

    public List<EntreeStock> listerTousLesEntreesStockAvecItems() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<EntreeStock> listeEntreesStock;

        try (Connection connection = ConnectionFactory.getInstance().habiliterConnection()) {
            listeEntreesStock = new ArrayList();

            scriptSQL = new StringBuilder("SELECT itementreestock.quantite,");
            scriptSQL.append(" itementreestock.idEntreeStock, entreestock.valeurTotalCoutUSD, entreestock.valeurTotalCoutFC,");
            scriptSQL.append(" entreestock.valeurTauxCarte, entreestock.dateHeure, entreestock.numeroEntreeStock,");
            scriptSQL.append(" entreestock.idFournisseur, fournisseur.entreprise, fournisseur.responsable, fournisseur.telephone,");
            scriptSQL.append(" itementreestock.idProduit, produit.description,");
            scriptSQL.append(" itementreestock.idPrixAchat, prixachatproduit.valeurUSD, prixachatproduit.dateHeure");
            scriptSQL.append(" FROM itementreestock");
            scriptSQL.append(" LEFT JOIN entreestock ON itementreestock.idEntreestock = entreestock.id");
            scriptSQL.append(" LEFT JOIN fournisseur ON entreestock.idFournisseur = fournisseur.id");
            scriptSQL.append(" LEFT JOIN produit ON itementreestock.idProduit = produit.id");
            scriptSQL.append(" LEFT JOIN prixachatproduit ON itementreestock.idPrixAchat = prixachatproduit.id");
            scriptSQL.append(" ORDER BY itementreestock.idEntreeStock, entreestock.dateHeure");

            prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();
            if (res != null) {
                String id = "";
                EntreeStock etrstck = new EntreeStock();
                List<ItemEntreeStock> listeItemsEntreeStock = new ArrayList();

                while (res.next()) {
                    EntreeStock entreeStock = new EntreeStock(res.getString(2));
                    entreeStock.setValeurTotalCoutUSD(res.getBigDecimal(3));
                    entreeStock.setValeurTotalCoutFC(res.getBigDecimal(4));
                    entreeStock.setValeurTauxCarte(res.getBigDecimal(5));
                    entreeStock.setDateHeure(res.getTimestamp(6));
                    entreeStock.setNumeroEntreeStock(res.getString(7));

                    Fournisseur fournisseur = new Fournisseur(res.getString(8));
                    fournisseur.setEntreprise(res.getString(9));
                    fournisseur.setResponsable(res.getString(10));
                    fournisseur.setTelephone(res.getString(11));
                    entreeStock.setFournisseur(fournisseur);

                    Produit produit = new Produit(res.getString(12));
                    produit.setDescription(res.getString(13));

                    PrixAchatProduit prixAchatProduit = new PrixAchatProduit(res.getString(14));
                    prixAchatProduit.setValeurUSD(res.getBigDecimal(15));
                    prixAchatProduit.setDateHeure(res.getTimestamp(16));
                    produit.setPrixAchatProduit(prixAchatProduit);

                    ItemEntreeStock itemEntreeStock = new ItemEntreeStock(entreeStock, produit);
                    itemEntreeStock.setQuantiteProduit(res.getBigDecimal(1));

                    if (id.equals(entreeStock.getId())) {
                        listeItemsEntreeStock.add(itemEntreeStock);
                        if (res.isLast()) {
                            etrstck.setItemsEntreeStock(listeItemsEntreeStock);
                            listeEntreesStock.add(etrstck);
                        }
                    } else {
                        if (!res.isFirst()) {
                            etrstck.setItemsEntreeStock(listeItemsEntreeStock);
                            listeEntreesStock.add(etrstck);
                        }
                        id = entreeStock.getId();

                        etrstck = new EntreeStock();
                        etrstck.setId(entreeStock.getId());
                        etrstck.setNumeroEntreeStock(entreeStock.getNumeroEntreeStock());
                        etrstck.setValeurTotalCoutUSD(entreeStock.getValeurTotalCoutUSD());
                        etrstck.setValeurTotalCoutFC(entreeStock.getValeurTotalCoutFC());
                        etrstck.setValeurTauxCarte(entreeStock.getValeurTauxCarte());
                        etrstck.setDateHeure(entreeStock.getDateHeure());
                        etrstck.setFournisseur(entreeStock.getFournisseur());

                        listeItemsEntreeStock = new ArrayList();
                        listeItemsEntreeStock.add(itemEntreeStock);

                    }
                }
            }
            prs.close();
            res.close();
            connection.close();
        }
        return listeEntreesStock;
    }

    public EntreeStock selectionnerEntreeStockParId(String idEntreeStock) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        EntreeStock etrstck = new EntreeStock();
        List<ItemEntreeStock> listeItemsEntreeStock = new ArrayList();

        try (Connection connection = ConnectionFactory.getInstance().habiliterConnection()) {

            scriptSQL = new StringBuilder("SELECT itementreestock.quantite,");
            scriptSQL.append(" itementreestock.idEntreeStock, entreestock.valeurTotalCoutUSD, entreestock.valeurTotalCoutFC,");
            scriptSQL.append(" entreestock.valeurTauxCarte, entreestock.dateHeure, entreestock.numeroEntreeStock,");
            scriptSQL.append(" entreestock.idFournisseur, fournisseur.entreprise, fournisseur.responsable, fournisseur.telephone,");
            scriptSQL.append(" itementreestock.idProduit, produit.description,");
            scriptSQL.append(" itementreestock.idPrixAchat, prixachatproduit.valeurUSD, prixachatproduit.dateHeure");
            scriptSQL.append(" FROM itementreestock");
            scriptSQL.append(" LEFT JOIN entreestock ON itementreestock.idEntreestock = entreestock.id");
            scriptSQL.append(" LEFT JOIN fournisseur ON entreestock.idFournisseur = fournisseur.id");
            scriptSQL.append(" LEFT JOIN produit ON itementreestock.idProduit = produit.id");
            scriptSQL.append(" LEFT JOIN prixachatproduit ON itementreestock.idPrixAchat = prixachatproduit.id");
            scriptSQL.append(" ORDER BY itementreestock.idEntreeStock, entreestock.dateHeure");
            scriptSQL.append(" WHERE itementreestock.idEntreeStock=?");

            prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));
            prs.setString(1, idEntreeStock);
            res = prs.executeQuery();

            if (res != null) {
                while (res.next()) {
                    EntreeStock entreeStock = new EntreeStock(res.getString(2));
                    entreeStock.setValeurTotalCoutUSD(res.getBigDecimal(3));
                    entreeStock.setValeurTotalCoutFC(res.getBigDecimal(4));
                    entreeStock.setValeurTauxCarte(res.getBigDecimal(5));
                    entreeStock.setDateHeure(res.getTimestamp(6));
                    entreeStock.setNumeroEntreeStock(res.getString(7));

                    Fournisseur fournisseur = new Fournisseur(res.getString(8));
                    fournisseur.setEntreprise(res.getString(9));
                    fournisseur.setResponsable(res.getString(10));
                    fournisseur.setTelephone(res.getString(11));
                    entreeStock.setFournisseur(fournisseur);

                    Produit produit = new Produit(res.getString(12));
                    produit.setDescription(res.getString(13));

                    PrixAchatProduit prixAchatProduit = new PrixAchatProduit(res.getString(14));
                    prixAchatProduit.setValeurUSD(res.getBigDecimal(15));
                    prixAchatProduit.setDateHeure(res.getTimestamp(16));
                    produit.setPrixAchatProduit(prixAchatProduit);

                    ItemEntreeStock itemEntreeStock = new ItemEntreeStock(entreeStock, produit);
                    itemEntreeStock.setQuantiteProduit(res.getBigDecimal(1));

                    listeItemsEntreeStock.add(itemEntreeStock);

                    if (res.isFirst()) {
                        etrstck.setId(entreeStock.getId());
                        etrstck.setNumeroEntreeStock(entreeStock.getNumeroEntreeStock());
                        etrstck.setValeurTotalCoutUSD(entreeStock.getValeurTotalCoutUSD());
                        etrstck.setValeurTotalCoutFC(entreeStock.getValeurTotalCoutFC());
                        etrstck.setValeurTauxCarte(entreeStock.getValeurTauxCarte());
                        etrstck.setDateHeure(entreeStock.getDateHeure());
                        etrstck.setFournisseur(entreeStock.getFournisseur());
                    }
                }

                etrstck.setItemsEntreeStock(listeItemsEntreeStock);

            }
            prs.close();
            res.close();
            connection.close();
        }
        return etrstck;
    }

    public boolean enregistrerEntreeStock(EntreeStock entreeStock) {
        PreparedStatement prs;

        Connection connection = null;
        try {
            connection = ConnectionFactory.getInstance().habiliterConnection();

            connection.setAutoCommit(false);

            scriptSQL = new StringBuilder("INSERT INTO entreestock(");
            scriptSQL.append(" idFournisseur, valeurTotalCoutUSD, valeurTotalCoutFC, valeurTauxCarte, dateHeure, numeroEntreeStock, id )");
            scriptSQL.append(" VALUES (?, ?, ?, ?, ?, ?, ?)");

            prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));

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

                prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));

                prs.setString(1, entreeStock.getId());
                prs.setString(2, itemEntreeStock.getProduit().getId());
                prs.setString(3, itemEntreeStock.getProduit().getPrixAchatProduit().getId());
                prs.setBigDecimal(4, itemEntreeStock.getQuantiteProduit());

                StockProduitService.getInstance()
                        .entrerStock(itemEntreeStock.getProduit(),
                                new Shop("[B@7bb11784652#a6f0bc88e"), itemEntreeStock.getQuantiteProduit(), connection);

                prs.execute();
            }

            connection.commit();

            prs.close();
            connection.close();
            return true;

        } catch (ClassNotFoundException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(EntreeStockDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(EntreeStockDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(EntreeStockDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(EntreeStockDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean actualiserEntreeStock(EntreeStock entreeStock) {
        PreparedStatement prs;
        ResultSet res;
        Connection connection = null;
        try {
            connection = ConnectionFactory.getInstance().habiliterConnection();

            connection.setAutoCommit(false);

            scriptSQL = new StringBuilder("UPDATE entreestock");
            scriptSQL.append(" SET idFournisseur=?, valeurTotalCoutUSD=?, valeurTotalCoutFC=?, valeurTauxCarte=?,");
            scriptSQL.append(" dateHeure=?, numeroEntreeStock=?");
            scriptSQL.append(" WHERE id=?");

            prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));

            prs.setString(1, entreeStock.getFournisseur().getId());
            prs.setBigDecimal(2, entreeStock.getValeurTotalCoutUSD());
            prs.setBigDecimal(3, entreeStock.getValeurTotalCoutFC());
            prs.setBigDecimal(4, entreeStock.getValeurTauxCarte());
            prs.setTimestamp(5, new Timestamp(entreeStock.getDateHeure().getTime()));
            prs.setString(6, entreeStock.getNumeroEntreeStock());
            prs.setString(7, entreeStock.getId());
            prs.execute();

            //Aatualiser le stock de produit
            scriptSQL = new StringBuilder("SELECT idProduit, quantite");
            scriptSQL.append(" FROM itementreestock");
            scriptSQL.append(" WHERE idEntreestock=?");

            prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));
            prs.setString(1, entreeStock.getId());
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {
                    ItemEntreeStock itemEntreeStock = new ItemEntreeStock();
                    itemEntreeStock.setProduit(new Produit(res.getString(1)));
                    itemEntreeStock.setQuantiteProduit(res.getBigDecimal(2));

                    if (!StockProduitService.getInstance()
                            .sortirStock(itemEntreeStock.getProduit(),
                                    new Shop("[B@7bb11784652#a6f0bc88e"), itemEntreeStock.getQuantiteProduit(), connection)) {
                        // throw insuficient stock exception

                        try {
                            connection.rollback();
                            return false;
                        } catch (SQLException ex1) {
                            Logger.getLogger(EntreeStockDao.class.getName()).log(Level.SEVERE, null, ex1);
                        }
                    }
                }
            }
            res.close();

            scriptSQL = new StringBuilder("DELETE FROM itementreestock WHERE idEntreeStock=?");

            prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));
            prs.setString(1, entreeStock.getId());
            prs.execute();

            for (ItemEntreeStock itemEntreeStock : entreeStock.getItemsEntreeStock()) {

                scriptSQL = new StringBuilder("INSERT INTO itementreestock(");
                scriptSQL.append(" idEntreeStock, idProduit, idPrixAchat, quantite )");
                scriptSQL.append(" VALUES (?, ?, ?, ?)");

                prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));

                prs.setString(1, entreeStock.getId());
                prs.setString(2, itemEntreeStock.getProduit().getId());
                prs.setString(3, itemEntreeStock.getProduit().getPrixAchatProduit().getId());
                prs.setBigDecimal(4, itemEntreeStock.getQuantiteProduit());

                StockProduitService.getInstance()
                        .entrerStock(itemEntreeStock.getProduit(),
                                new Shop("[B@7bb11784652#a6f0bc88e"), itemEntreeStock.getQuantiteProduit(), connection);

                prs.execute();
            }

            connection.commit();
            prs.close();
            connection.close();
            return true;
        } catch (ClassNotFoundException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(EntreeStockDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(EntreeStockDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(EntreeStockDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(EntreeStockDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
