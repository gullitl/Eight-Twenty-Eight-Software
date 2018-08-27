package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoftmiddleend.model.ItemDispatch;
import com.cecilsoftwares.reussoftmiddleend.model.Dispatch;
import com.cecilsoftwares.reussoftmiddleend.model.Produit;
import com.cecilsoftwares.reussoftmiddleend.model.Shop;
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

        try (Connection connection = ConnectionFactory.getInstance().habiliterConnection()) {
            listeDispatch = new ArrayList();

            scriptSQL = new StringBuilder("SELECT dispatch.id, dispatch.dateHeure, dispatch.valide");
            scriptSQL.append(" dispatch.idShop, shop.nom,");
            scriptSQL.append(" FROM dispatch");
            scriptSQL.append(" LEFT JOIN shop as shop ON dispatch.idShop = shop.id");

            prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {

                    Dispatch dispatch = new Dispatch(res.getString(1));
                    dispatch.setDateHeure(res.getTimestamp(2));
                    dispatch.setValide(res.getInt(3) == 1);

                    Shop shop = new Shop(res.getString(4));
                    shop.setNom(res.getString(5));
                    dispatch.setShop(shop);

                    listeDispatch.add(dispatch);
                }
            }
            prs.close();
            res.close();
            connection.close();
        }
        return listeDispatch;
    }

    public List<Dispatch> listerTousLesDispatchsAvecItems() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<Dispatch> listeDispatch;

        try (Connection connection = ConnectionFactory.getInstance().habiliterConnection()) {
            listeDispatch = new ArrayList();

            scriptSQL = new StringBuilder("SELECT itemdispatch.quantite,");
            scriptSQL.append(" itemdispatch.idDispatch, dispatch.dateHeure, dispatch.valide,");
            scriptSQL.append(" dispatch.idShop, shopExpediteur.nom,");
            scriptSQL.append(" itemdispatch.idShop, shopDestinataire.nom,");
            scriptSQL.append(" itemdispatch.idProduit, produit.description");
            scriptSQL.append(" FROM itemdispatch");
            scriptSQL.append(" LEFT JOIN dispatch ON itemdispatch.idDispatch = dispatch.id");
            scriptSQL.append(" LEFT JOIN shop as shopExpediteur ON dispatch.idShop = shopExpediteur.id");
            scriptSQL.append(" LEFT JOIN shop as shopDestinataire ON itemdispatch.idShop = shopDestinataire.id");
            scriptSQL.append(" LEFT JOIN produit ON itemdispatch.idProduit = produit.id");
            scriptSQL.append(" ORDER BY itemdispatch.idDispatch, dispatch.dateHeure");

            prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();
            if (res != null) {

                String id = "";
                Dispatch dsptch = new Dispatch();
                List<ItemDispatch> listeItemsDispatch = new ArrayList();

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

                    Shop shopDestinataire = new Shop(res.getString(7));
                    shopDestinataire.setNom(res.getString(8));
                    itemDispatch.setShop(shopDestinataire);

                    if (id.equals(dispatch.getId())) {
                        listeItemsDispatch.add(itemDispatch);
                        if (res.isLast()) {
                            dsptch.setItemsDispatch(listeItemsDispatch);
                            listeDispatch.add(dsptch);
                        }
                    } else {
                        if (!res.first()) {
                            dsptch.setItemsDispatch(listeItemsDispatch);
                            listeDispatch.add(dsptch);
                        }
                        id = dispatch.getId();

                        dsptch.setId(dispatch.getId());
                        dsptch.setDateHeure(dispatch.getDateHeure());
                        dsptch.setShop(dispatch.getShop());

                        listeItemsDispatch = new ArrayList();
                        listeItemsDispatch.add(itemDispatch);
                    }
                }
            }
            prs.close();
            res.close();
            connection.close();
        }
        return listeDispatch;
    }

    public Dispatch selectionnerDispatchParId(String idDispatch) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        Dispatch dsptch = new Dispatch();

        List<ItemDispatch> listeItemsDispatch = new ArrayList();

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
            scriptSQL.append(" GROUP BY itemdispatch.idDispatch, dispatch.dateHeure, dispatch.valide, itemdispatch.quantite,");
            scriptSQL.append(" dispatch.idShop, shopExpediteur.nom,");
            scriptSQL.append(" dispatch.idShopDestinataire, shopdestinataire.nom,");
            scriptSQL.append(" itemdispatch.idProduto, produit.description");
            scriptSQL.append(" WHERE itemdispatch.idDispatch=?");

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

                    if (res.first()) {
                        dsptch.setId(dispatch.getId());
                        dsptch.setShop(dispatch.getShop());
                    }
                }

                dsptch.setItemsDispatch(listeItemsDispatch);

            }
            prs.close();
            res.close();
            connection.close();
        }
        return dsptch;
    }

    public boolean enregistrerDispatch(Dispatch dispatch) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection connection = ConnectionFactory.getInstance().habiliterConnection()) {

            scriptSQL = new StringBuilder("INSERT INTO dispatch(");
            scriptSQL.append(" idShop, dateHeure, active, id)");
            scriptSQL.append(" VALUES (?, ?, ?, ?)");

            prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));

            prs.setString(1, dispatch.getShop().getId());
            prs.setTimestamp(2, new Timestamp(dispatch.getDateHeure().getTime()));
            prs.setInt(3, dispatch.isValide() ? 1 : 0);
            prs.setString(4, dispatch.getId());

            prs.execute();

            for (ItemDispatch itemDispatch : dispatch.getItemsDispatch()) {

                scriptSQL = new StringBuilder("INSERT INTO itemdispatch(");
                scriptSQL.append(" idDispatch, idProduit, idShop, quantite )");
                scriptSQL.append(" VALUES (?, ?, ?)");

                prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));

                prs.setString(1, itemDispatch.getDispatch().getId());
                prs.setString(2, itemDispatch.getProduit().getId());
                prs.setString(3, itemDispatch.getShop().getId());
                prs.setBigDecimal(4, itemDispatch.getQuantiteProduit());
                prs.execute();
            }

            prs.close();
            connection.close();
        }
        return true;
    }

}
