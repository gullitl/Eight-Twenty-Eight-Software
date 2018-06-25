package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoftmiddleend.model.ItemDispatch;
import com.cecilsoftwares.reussoftmiddleend.model.Dispatch;
import com.cecilsoftwares.reussoftmiddleend.model.Produit;
import com.cecilsoftwares.reussoftmiddleend.model.Shop;
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

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            listeItemsDispatch = new ArrayList();

            scriptSQL = new StringBuilder("SELECT itemdispatch.quantiteProduit,");
            scriptSQL.append(" itemdispatch.idDispatch, dispatch.dateHeure,");
            scriptSQL.append(" dispatch.idShopExpediteur, shopexpediteur.nom,");
            scriptSQL.append(" dispatch.idShopDestinataire, shopdestinataire.nom,");
            scriptSQL.append(" itemdispatch.idProduto, produit.description");
            scriptSQL.append(" FROM itemdispatch");
            scriptSQL.append(" LEFT JOIN dispatch ON itemdispatch.idDispatch = dispatch.code");
            scriptSQL.append(" LEFT JOIN shop as shopexpediteur ON dispatch.idShopExpediteur = shopexpediteur.code");
            scriptSQL.append(" LEFT JOIN shop as shopdestinataire ON dispatch.idShopDestinataire = shopdestinataire.code");
            scriptSQL.append(" LEFT JOIN produit ON itemdispatch.idProduit = produit.code");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {

                    Dispatch dispatch = new Dispatch(res.getInt(1));

                    Shop shopDestinataire = new Shop(res.getInt(5));
                    shopDestinataire.setNom(res.getString(6));
                    dispatch.setShop(shopDestinataire);

                    Produit produit = new Produit(res.getInt(12));
                    produit.setDescription(res.getString(13));

                    ItemDispatch itemDispatch = new ItemDispatch(dispatch, produit);

                    Shop shopExpediteur = new Shop(res.getInt(5));
                    shopExpediteur.setNom(res.getString(6));
                    itemDispatch.setShop(shopExpediteur);

                    itemDispatch.setQuantiteProduit(new BigDecimal(res.getString(3)));

                    listeItemsDispatch.add(itemDispatch);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listeItemsDispatch;
    }

    public List<ItemDispatch> listerItemsDispatchParCodeDispatch(int codeDispatch) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<ItemDispatch> listeItemsDispatch;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            listeItemsDispatch = new ArrayList();

            scriptSQL = new StringBuilder("SELECT itemdispatch.quantiteProduit,");
            scriptSQL.append(" itemdispatch.idDispatch, dispatch.dateHeure,");
            scriptSQL.append(" dispatch.idShopExpediteur, shopexpediteur.nom,");
            scriptSQL.append(" dispatch.idShopDestinataire, shopdestinataire.nom,");
            scriptSQL.append(" itemdispatch.idProduto, produit.description");
            scriptSQL.append(" FROM itemdispatch");
            scriptSQL.append(" LEFT JOIN dispatch ON itemdispatch.idDispatch = dispatch.code");
            scriptSQL.append(" LEFT JOIN shop as shopexpediteur ON dispatch.idShopExpediteur = shopexpediteur.code");
            scriptSQL.append(" LEFT JOIN shop as shopdestinataire ON dispatch.idShopDestinataire = shopdestinataire.code");
            scriptSQL.append(" LEFT JOIN produit ON itemdispatch.idProduit = produit.code");
            scriptSQL.append(" WHERE dispatch.code=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codeDispatch);
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {

                    Dispatch dispatch = new Dispatch(res.getInt(1));

                    Shop shopDestinataire = new Shop(res.getInt(5));
                    shopDestinataire.setNom(res.getString(6));
                    dispatch.setShop(shopDestinataire);

                    Produit produit = new Produit(res.getInt(12));
                    produit.setDescription(res.getString(13));

                    ItemDispatch itemDispatch = new ItemDispatch(dispatch, produit);

                    Shop shopExpediteur = new Shop(res.getInt(5));
                    shopExpediteur.setNom(res.getString(6));
                    itemDispatch.setShop(shopExpediteur);

                    itemDispatch.setQuantiteProduit(new BigDecimal(res.getString(3)));

                    listeItemsDispatch.add(itemDispatch);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listeItemsDispatch;
    }

    public List<ItemDispatch> listerItemsDispatchParCodeProduit(int codeProduit) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<ItemDispatch> listeItemsDispatch;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            listeItemsDispatch = new ArrayList();

            scriptSQL = new StringBuilder("SELECT itemdispatch.quantiteProduit,");
            scriptSQL.append(" itemdispatch.idDispatch, dispatch.dateHeure,");
            scriptSQL.append(" dispatch.idShopExpediteur, shopexpediteur.nom,");
            scriptSQL.append(" dispatch.idShopDestinataire, shopdestinataire.nom,");
            scriptSQL.append(" itemdispatch.idProduto, produit.description");
            scriptSQL.append(" FROM itemdispatch");
            scriptSQL.append(" LEFT JOIN dispatch ON itemdispatch.idDispatch = dispatch.code");
            scriptSQL.append(" LEFT JOIN shop as shopexpediteur ON dispatch.idShopExpediteur = shopexpediteur.code");
            scriptSQL.append(" LEFT JOIN shop as shopdestinataire ON dispatch.idShopDestinataire = shopdestinataire.code");
            scriptSQL.append(" LEFT JOIN produit ON itemdispatch.idProduit = produit.code");
            scriptSQL.append(" WHERE produit.code=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codeProduit);
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {

                    Dispatch dispatch = new Dispatch(res.getInt(1));

                    Shop shopDestinataire = new Shop(res.getInt(5));
                    shopDestinataire.setNom(res.getString(6));
                    dispatch.setShop(shopDestinataire);

                    Produit produit = new Produit(res.getInt(12));
                    produit.setDescription(res.getString(13));

                    ItemDispatch itemDispatch = new ItemDispatch(dispatch, produit);

                    Shop shopExpediteur = new Shop(res.getInt(5));
                    shopExpediteur.setNom(res.getString(6));
                    itemDispatch.setShop(shopExpediteur);

                    itemDispatch.setQuantiteProduit(new BigDecimal(res.getString(3)));

                    listeItemsDispatch.add(itemDispatch);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listeItemsDispatch;
    }

    public ItemDispatch selectionnerItemDispatchParCodeDispatchECodeProduit(int codeDispatch, int codeProduit) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {

            scriptSQL = new StringBuilder("SELECT itemdispatch.quantiteProduit,");
            scriptSQL.append(" itemdispatch.idDispatch, dispatch.dateHeure,");
            scriptSQL.append(" dispatch.idShopExpediteur, shopexpediteur.nom,");
            scriptSQL.append(" dispatch.idShopDestinataire, shopdestinataire.nom,");
            scriptSQL.append(" itemdispatch.idProduto, produit.description");
            scriptSQL.append(" FROM itemdispatch");
            scriptSQL.append(" LEFT JOIN dispatch ON itemdispatch.idDispatch = dispatch.code");
            scriptSQL.append(" LEFT JOIN shop as shopexpediteur ON dispatch.idShopExpediteur = shopexpediteur.code");
            scriptSQL.append(" LEFT JOIN shop as shopdestinataire ON dispatch.idShopDestinataire = shopdestinataire.code");
            scriptSQL.append(" LEFT JOIN produit ON itemdispatch.idProduit = produit.code");
            scriptSQL.append(" WHERE dispatch.code=? AND produit.code=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codeDispatch);
            prs.setInt(2, codeProduit);
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {

                    Dispatch dispatch = new Dispatch(res.getInt(1));

                    Shop shopDestinataire = new Shop(res.getInt(5));
                    shopDestinataire.setNom(res.getString(6));
                    dispatch.setShop(shopDestinataire);

                    Produit produit = new Produit(res.getInt(12));
                    produit.setDescription(res.getString(13));

                    ItemDispatch itemDispatch = new ItemDispatch(dispatch, produit);

                    Shop shopExpediteur = new Shop(res.getInt(5));
                    shopExpediteur.setNom(res.getString(6));
                    itemDispatch.setShop(shopExpediteur);

                    itemDispatch.setQuantiteProduit(new BigDecimal(res.getString(3)));

                    prs.close();
                    res.close();
                    conexao.close();

                    return itemDispatch;
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return null;
    }

    public boolean enregistrerItemDispatch(ItemDispatch itemDispatch) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {

            scriptSQL = new StringBuilder("INSERT INTO itemdispatch(");
            scriptSQL.append(" idDispatch, idProduit, quantiteProduit )");
            scriptSQL.append(" VALUES (?, ?, ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setInt(1, itemDispatch.getDispatch().getCode());
            prs.setInt(8, itemDispatch.getProduit().getCode());
            prs.setBigDecimal(5, itemDispatch.getQuantiteProduit());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

}
