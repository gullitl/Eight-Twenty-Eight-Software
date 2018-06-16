package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoftmiddleend.model.ItemDispatch;
import com.cecilsoftwares.reussoftmiddleend.model.Dispatch.DispatchBuilder;
import com.cecilsoftwares.reussoftmiddleend.model.Dispatch;
import com.cecilsoftwares.reussoftmiddleend.model.ItemDispatch.ItemDispatchBuilder;
import com.cecilsoftwares.reussoftmiddleend.model.Produit;
import com.cecilsoftwares.reussoftmiddleend.model.Produit.ProduitBuilder;
import com.cecilsoftwares.reussoftmiddleend.model.Shop;
import com.cecilsoftwares.reussoftmiddleend.model.Shop.ShopBuilder;
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

                    Shop shopDestinataire = new ShopBuilder(res.getInt(5))
                            .nom(res.getString(6))
                            .build();
                    Shop shopExpediteur = new ShopBuilder(res.getInt(5))
                            .nom(res.getString(6))
                            .build();

                    Dispatch dispatch = new DispatchBuilder(res.getInt(1))
                            .shopDestinataire(shopDestinataire)
                            .shopExpediteur(shopExpediteur)
                            .build();

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
                DispatchBuilder dispatchBuilder = new DispatchBuilder(0);
                List<ItemDispatch> listeItemsDispatch = new ArrayList();

                while (res.next()) {

                    Produit produit = new ProduitBuilder(res.getInt(12))
                            .description(res.getString(13))
                            .build();

                    Shop shopDestinataire = new ShopBuilder(res.getInt(5))
                            .nom(res.getString(6))
                            .build();

                    Shop shopExpediteur = new ShopBuilder(res.getInt(5))
                            .nom(res.getString(6))
                            .build();

                    Dispatch dispatch = new DispatchBuilder(res.getInt(1))
                            .shopDestinataire(shopDestinataire)
                            .shopExpediteur(shopExpediteur)
                            .build();

                    ItemDispatch itemDispatch = new ItemDispatchBuilder(dispatch, produit)
                            .quantiteProduit(new BigDecimal(res.getString(3)))
                            .build();

                    if (code == dispatch.getCode()) {
                        listeItemsDispatch.add(itemDispatch);
                    } else {
                        if (!res.first()) {
                            dispatchBuilder.itemsDispatch(listeItemsDispatch);
                            listeDispatch.add(dispatchBuilder.build());
                        }
                        code = dispatch.getCode();

                        dispatchBuilder = new DispatchBuilder(dispatch.getCode())
                                .dateHeure(dispatch.getDateHeure())
                                .shopDestinataire(dispatch.getShopDestinataire())
                                .shopExpediteur(dispatch.getShopExpediteur());

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
        DispatchBuilder dispatchBuilder = new DispatchBuilder(0);
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

                    Produit produit = new ProduitBuilder(res.getInt(12))
                            .description(res.getString(13))
                            .build();

                    Shop shopDestinataire = new ShopBuilder(res.getInt(5))
                            .nom(res.getString(6))
                            .build();

                    Shop shopExpediteur = new ShopBuilder(res.getInt(5))
                            .nom(res.getString(6))
                            .build();

                    Dispatch dispatch = new DispatchBuilder(res.getInt(1))
                            .shopDestinataire(shopDestinataire)
                            .shopExpediteur(shopExpediteur)
                            .build();

                    ItemDispatch itemDispatch = new ItemDispatchBuilder(dispatch, produit)
                            .quantiteProduit(new BigDecimal(res.getString(3)))
                            .build();

                    listeItemsDispatch.add(itemDispatch);

                    if (res.first()) {
                        dispatchBuilder = new DispatchBuilder(dispatch.getCode())
                                .shopDestinataire(dispatch.getShopDestinataire())
                                .shopExpediteur(dispatch.getShopExpediteur());
                    }

                }

                dispatchBuilder.itemsDispatch(listeItemsDispatch);

            }
            prs.close();
            res.close();
            conexao.close();
        }
        return dispatchBuilder.build();
    }

    public boolean enregistrerDispatch(Dispatch dispatch) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {

            scriptSQL = new StringBuilder("INSERT INTO dispatch(");
            scriptSQL.append(" idShopDestinataire, idShopExpediteur, dateHeure, active, code)");
            scriptSQL.append(" VALUES (?, ?, ?, ?, ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setInt(1, dispatch.getShopDestinataire().getCode());
            prs.setInt(2, dispatch.getShopExpediteur().getCode());
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
