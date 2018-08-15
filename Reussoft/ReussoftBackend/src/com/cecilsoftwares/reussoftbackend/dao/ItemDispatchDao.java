package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoftmiddleend.model.ItemDispatch;
import com.cecilsoftwares.reussoftmiddleend.model.Dispatch;
import com.cecilsoftwares.reussoftmiddleend.model.Produit;
import com.cecilsoftwares.reussoftmiddleend.model.Shop;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class ItemDispatchDao {

    private StringBuilder scriptSQL;
    private static ItemDispatchDao uniqueInstance;

    public ItemDispatchDao() {
    }

    public static synchronized ItemDispatchDao getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ItemDispatchDao();
        }
        return uniqueInstance;
    }

    public List<ItemDispatch> listerTousLesItemsDispatch() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<ItemDispatch> listeItemsDispatch;

        try (Connection connection = ConnectionFactory.getInstance().habiliterConnection()) {
            listeItemsDispatch = new ArrayList();

            scriptSQL = new StringBuilder("SELECT itemdispatch.quantite,");
            scriptSQL.append(" itemdispatch.idDispatch, dispatch.dateHeure, dispatch.valide,");
            scriptSQL.append(" dispatch.idShop, shopExpediteur.nom,");
            scriptSQL.append(" itemdispatch.idShop, shopDestinataire.nom,");
            scriptSQL.append(" itemdispatch.idProduto, produit.description");
            scriptSQL.append(" FROM itemdispatch");
            scriptSQL.append(" LEFT JOIN dispatch ON itemdispatch.idDispatch = dispatch.id");
            scriptSQL.append(" LEFT JOIN shop as shopExpediteur ON dispatch.idShop = shopExpediteur.id");
            scriptSQL.append(" LEFT JOIN shop as shopDestinataire ON itemdispatch.idShop = shopDestinataire.id");
            scriptSQL.append(" LEFT JOIN produit ON itemdispatch.idProduit = produit.id");

            prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {

                    Dispatch dispatch = new Dispatch(res.getString(2));
                    dispatch.setDateHeure(res.getTimestamp(3));
                    dispatch.setValide(res.getInt(4) == 1);

                    Shop shopExpediteur = new Shop(res.getString(5));
                    shopExpediteur.setNom(res.getString(6));
                    dispatch.setShop(shopExpediteur);

                    Produit produit = new Produit(res.getString(9));
                    produit.setDescription(res.getString(10));

                    ItemDispatch itemDispatch = new ItemDispatch(dispatch, produit);
                    itemDispatch.setQuantiteProduit(res.getBigDecimal(1));
                    itemDispatch.setQuantiteProduit(res.getBigDecimal(3));

                    Shop shopDestinataire = new Shop(res.getString(7));
                    shopDestinataire.setNom(res.getString(8));
                    itemDispatch.setShop(shopDestinataire);

                    listeItemsDispatch.add(itemDispatch);
                }
            }
            prs.close();
            res.close();
            connection.close();
        }
        return listeItemsDispatch;
    }

    public List<ItemDispatch> listerItemsDispatchParIdDispatch(String idDispatch) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<ItemDispatch> listeItemsDispatch;

        try (Connection connection = ConnectionFactory.getInstance().habiliterConnection()) {
            listeItemsDispatch = new ArrayList();

            scriptSQL = new StringBuilder("SELECT itemdispatch.quantite,");
            scriptSQL.append(" itemdispatch.idDispatch, dispatch.dateHeure, dispatch.valide,");
            scriptSQL.append(" dispatch.idShop, shopExpediteur.nom,");
            scriptSQL.append(" itemdispatch.idShop, shopDestinataire.nom,");
            scriptSQL.append(" itemdispatch.idProduto, produit.description");
            scriptSQL.append(" FROM itemdispatch");
            scriptSQL.append(" LEFT JOIN dispatch ON itemdispatch.idDispatch = dispatch.id");
            scriptSQL.append(" LEFT JOIN shop as shopExpediteur ON dispatch.idShop = shopExpediteur.id");
            scriptSQL.append(" LEFT JOIN shop as shopDestinataire ON itemdispatch.idShop = shopDestinataire.id");
            scriptSQL.append(" LEFT JOIN produit ON itemdispatch.idProduit = produit.id");
            scriptSQL.append(" WHERE dispatch.id=?");

            prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));
            prs.setString(1, idDispatch);
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {

                    Dispatch dispatch = new Dispatch(res.getString(2));
                    dispatch.setDateHeure(res.getTimestamp(3));
                    dispatch.setValide(res.getInt(4) == 1);

                    Shop shopExpediteur = new Shop(res.getString(5));
                    shopExpediteur.setNom(res.getString(6));
                    dispatch.setShop(shopExpediteur);

                    Produit produit = new Produit(res.getString(9));
                    produit.setDescription(res.getString(10));

                    ItemDispatch itemDispatch = new ItemDispatch(dispatch, produit);
                    itemDispatch.setQuantiteProduit(res.getBigDecimal(1));
                    itemDispatch.setQuantiteProduit(res.getBigDecimal(3));

                    Shop shopDestinataire = new Shop(res.getString(7));
                    shopDestinataire.setNom(res.getString(8));
                    itemDispatch.setShop(shopDestinataire);

                    listeItemsDispatch.add(itemDispatch);
                }
            }
            prs.close();
            res.close();
            connection.close();
        }
        return listeItemsDispatch;
    }

    public List<ItemDispatch> listerItemsDispatchParIdProduit(String idProduit) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<ItemDispatch> listeItemsDispatch;

        try (Connection connection = ConnectionFactory.getInstance().habiliterConnection()) {
            listeItemsDispatch = new ArrayList();

            scriptSQL = new StringBuilder("SELECT itemdispatch.quantite,");
            scriptSQL.append(" itemdispatch.idDispatch, dispatch.dateHeure, dispatch.valide,");
            scriptSQL.append(" dispatch.idShop, shopExpediteur.nom,");
            scriptSQL.append(" itemdispatch.idShop, shopDestinataire.nom,");
            scriptSQL.append(" itemdispatch.idProduto, produit.description");
            scriptSQL.append(" FROM itemdispatch");
            scriptSQL.append(" LEFT JOIN dispatch ON itemdispatch.idDispatch = dispatch.id");
            scriptSQL.append(" LEFT JOIN shop as shopExpediteur ON dispatch.idShop = shopExpediteur.id");
            scriptSQL.append(" LEFT JOIN shop as shopDestinataire ON itemdispatch.idShop = shopDestinataire.id");
            scriptSQL.append(" LEFT JOIN produit ON itemdispatch.idProduit = produit.id");
            scriptSQL.append(" WHERE produit.id=?");

            prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));
            prs.setString(1, idProduit);
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {

                    Dispatch dispatch = new Dispatch(res.getString(2));
                    dispatch.setDateHeure(res.getTimestamp(3));
                    dispatch.setValide(res.getInt(4) == 1);

                    Shop shopExpediteur = new Shop(res.getString(5));
                    shopExpediteur.setNom(res.getString(6));
                    dispatch.setShop(shopExpediteur);

                    Produit produit = new Produit(res.getString(9));
                    produit.setDescription(res.getString(10));

                    ItemDispatch itemDispatch = new ItemDispatch(dispatch, produit);
                    itemDispatch.setQuantiteProduit(res.getBigDecimal(1));
                    itemDispatch.setQuantiteProduit(res.getBigDecimal(3));

                    Shop shopDestinataire = new Shop(res.getString(7));
                    shopDestinataire.setNom(res.getString(8));
                    itemDispatch.setShop(shopDestinataire);

                    listeItemsDispatch.add(itemDispatch);
                }
            }
            prs.close();
            res.close();
            connection.close();
        }
        return listeItemsDispatch;
    }

    public ItemDispatch selectionnerItemDispatchParIdDispatchEIdProduit(String idDispatch, String idProduit) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection connection = ConnectionFactory.getInstance().habiliterConnection()) {

            scriptSQL = new StringBuilder("SELECT itemdispatch.quantite,");
            scriptSQL.append(" itemdispatch.idDispatch, dispatch.dateHeure, dispatch.valide,");
            scriptSQL.append(" dispatch.idShop, shopExpediteur.nom,");
            scriptSQL.append(" itemdispatch.idShop, shopDestinataire.nom,");
            scriptSQL.append(" itemdispatch.idProduto, produit.description");
            scriptSQL.append(" FROM itemdispatch");
            scriptSQL.append(" LEFT JOIN dispatch ON itemdispatch.idDispatch = dispatch.id");
            scriptSQL.append(" LEFT JOIN shop as shopExpediteur ON dispatch.idShop = shopExpediteur.id");
            scriptSQL.append(" LEFT JOIN shop as shopDestinataire ON itemdispatch.idShop = shopDestinataire.id");
            scriptSQL.append(" LEFT JOIN produit ON itemdispatch.idProduit = produit.id");
            scriptSQL.append(" WHERE dispatch.id=? AND produit.id=?");

            prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));
            prs.setString(1, idDispatch);
            prs.setString(2, idProduit);
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {

                    Dispatch dispatch = new Dispatch(res.getString(2));
                    dispatch.setDateHeure(res.getTimestamp(3));
                    dispatch.setValide(res.getInt(4) == 1);

                    Shop shopExpediteur = new Shop(res.getString(5));
                    shopExpediteur.setNom(res.getString(6));
                    dispatch.setShop(shopExpediteur);

                    Produit produit = new Produit(res.getString(9));
                    produit.setDescription(res.getString(10));

                    ItemDispatch itemDispatch = new ItemDispatch(dispatch, produit);
                    itemDispatch.setQuantiteProduit(res.getBigDecimal(1));
                    itemDispatch.setQuantiteProduit(res.getBigDecimal(3));

                    Shop shopDestinataire = new Shop(res.getString(7));
                    shopDestinataire.setNom(res.getString(8));
                    itemDispatch.setShop(shopDestinataire);

                    prs.close();
                    res.close();
                    connection.close();

                    return itemDispatch;
                }
            }
            prs.close();
            res.close();
            connection.close();
        }
        return null;
    }

}
