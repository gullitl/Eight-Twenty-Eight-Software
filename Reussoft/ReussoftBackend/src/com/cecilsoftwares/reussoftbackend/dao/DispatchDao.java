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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class DispatchDao {

    private StringBuilder scriptSQL;
    private static DispatchDao uniqueInstance;

    public DispatchDao() {
    }

    public static synchronized DispatchDao getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new DispatchDao();
        }
        return uniqueInstance;
    }

    public List<Dispatch> listerTousLesDispatchsSansItems() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<Dispatch> listeDispatch;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            listeDispatch = new ArrayList();

            scriptSQL = new StringBuilder("SELECT dispatch.code, dispatch.dateHeure,");
            scriptSQL.append(" dispatch.idShopExpediteur, shopexpediteur.nom,");
            scriptSQL.append(" dispatch.idShopDestinataire, shopdestinataire.nom,");
            scriptSQL.append(" FROM dispatch");
            scriptSQL.append(" LEFT JOIN shop as shopexpediteur ON dispatch.idShopExpediteur = shopexpediteur.code");
            scriptSQL.append(" LEFT JOIN shop as shopdestinataire ON dispatch.idShopDestinataire = shopdestinataire.code");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {

                    Dispatch dispatch = new Dispatch(res.getInt(1));

                    Shop shop = new Shop(res.getInt(5));
                    shop.setNom(res.getString(6));
                    dispatch.setShop(shop);

                    listeDispatch.add(dispatch);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listeDispatch;
    }

    public List<Dispatch> listerTousLesDispatchsAvecItems() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<Dispatch> listeDispatch;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            listeDispatch = new ArrayList();

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
            scriptSQL.append(" GROUP BY itemdispatch.idDispatch, dispatch.dateHeure, itemdispatch.quantiteProduit,");
            scriptSQL.append(" dispatch.idShopExpediteur, shopexpediteur.nom,");
            scriptSQL.append(" dispatch.idShopDestinataire, shopdestinataire.nom,");
            scriptSQL.append(" itemdispatch.idProduto, produit.description");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();
            if (res != null) {

                int code = 0;
                Dispatch dsptch = new Dispatch();
                List<ItemDispatch> listeItemsDispatch = new ArrayList();

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

                    if (code == dispatch.getCode()) {
                        listeItemsDispatch.add(itemDispatch);
                    } else {
                        if (!res.first()) {
                            dsptch.setItemsDispatch(listeItemsDispatch);
                            listeDispatch.add(dsptch);
                        }
                        code = dispatch.getCode();

                        dsptch.setCode(dispatch.getCode());
                        dsptch.setDateHeure(dispatch.getDateHeure());
                        dsptch.setShop(dispatch.getShop());

                        listeItemsDispatch = new ArrayList();
                        listeItemsDispatch.add(itemDispatch);
                    }

                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listeDispatch;
    }

    public Dispatch selectionnerDispatchParCode(int codeDispatch) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        Dispatch dsptch = new Dispatch();

        List<ItemDispatch> listeItemsDispatch = new ArrayList();

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
            scriptSQL.append(" GROUP BY itemdispatch.idDispatch, dispatch.dateHeure, itemdispatch.quantiteProduit,");
            scriptSQL.append(" dispatch.idShopExpediteur, shopexpediteur.nom,");
            scriptSQL.append(" dispatch.idShopDestinataire, shopdestinataire.nom,");
            scriptSQL.append(" itemdispatch.idProduto, produit.description");
            scriptSQL.append(" WHERE itemdispatch.idDispatch=?");

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

                    if (res.first()) {
                        dsptch.setCode(dispatch.getCode());
                        dsptch.setShop(dispatch.getShop());
                    }

                }

                dsptch.setItemsDispatch(listeItemsDispatch);

            }
            prs.close();
            res.close();
            conexao.close();
        }
        return dsptch;
    }

    public boolean enregistrerDispatch(Dispatch dispatch) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {

            scriptSQL = new StringBuilder("INSERT INTO dispatch(");
            scriptSQL.append(" idShopDestinataire, idShopExpediteur, dateHeure, active, code)");
            scriptSQL.append(" VALUES (?, ?, ?, ?, ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setInt(1, dispatch.getShop().getCode());
            prs.setTimestamp(3, new Timestamp(dispatch.getDateHeure().getTime()));
            prs.setInt(4, dispatch.isActive() ? 1 : 0);
            prs.setInt(5, dispatch.getCode());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

}
