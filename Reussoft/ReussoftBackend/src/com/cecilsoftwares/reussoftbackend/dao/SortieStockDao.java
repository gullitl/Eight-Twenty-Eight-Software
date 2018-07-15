package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoftmiddleend.model.Client;
import com.cecilsoftwares.reussoftmiddleend.model.ItemSortieStock;
import com.cecilsoftwares.reussoftmiddleend.model.PrixVenteProduit;
import com.cecilsoftwares.reussoftmiddleend.model.Produit;
import com.cecilsoftwares.reussoftmiddleend.model.Shop;
import com.cecilsoftwares.reussoftmiddleend.model.SortieStock;
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
public class SortieStockDao {

    private StringBuilder scriptSQL;
    private static SortieStockDao uniqueInstance;

    public SortieStockDao() {
    }

    public static synchronized SortieStockDao getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new SortieStockDao();
        }
        return uniqueInstance;
    }

    public List<SortieStock> listerToutesLesSortiesStockSansItems() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<SortieStock> listeSortiesStock;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            listeSortiesStock = new ArrayList();

            scriptSQL = new StringBuilder("SELECT sortiestock.id, sortiestock.dateHeure,");
            scriptSQL.append(" sortiestock.idShop, shop.nom,");
            scriptSQL.append(" sortiestock.idClient, client.nom, client.entreprise, client.telephone");
            scriptSQL.append(" FROM sortiestock");
            scriptSQL.append(" LEFT JOIN shop ON sortiestock.idShop = shop.id");
            scriptSQL.append(" LEFT JOIN client ON sortiestock.idClient = client.id");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {

                    SortieStock entreeStock = new SortieStock(res.getString(1));
                    entreeStock.setDateHeure(res.getTimestamp(2));

                    Shop shop = new Shop(res.getString(3));
                    shop.setNom(res.getString(4));
                    entreeStock.setShop(shop);

                    Client client = new Client(res.getString(5));
                    client.setEntreprise(res.getString(6));
                    client.setNom(res.getString(7));
                    client.setTelephone(res.getString(8));
                    entreeStock.setClient(client);

                    listeSortiesStock.add(entreeStock);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listeSortiesStock;
    }

    public List<SortieStock> listerToutesLesSortiesStockAvecItems() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<SortieStock> listeSortiesStock;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            listeSortiesStock = new ArrayList();

            scriptSQL = new StringBuilder("SELECT itemsortiestock.quantiteProduit,");
            scriptSQL.append(" itemsortiestock.idSortieStock, sortiestock.dateHeure,");
            scriptSQL.append(" sortiestock.idShop, shop.nom,");
            scriptSQL.append(" sortiestock.idClient, client.nom, client.entreprise, client.telephone,");
            scriptSQL.append(" itemsortiestock.idProduto, produit.description,");
            scriptSQL.append(" itemsortiestock.idPrixVenteProduit, prixventeproduit.valeurUSD, prixventeproduit.valeurFC, prixventeproduit.dateHeure");
            scriptSQL.append(" FROM itemsortiestock");
            scriptSQL.append(" LEFT JOIN sortiestock ON itemsortiestock.idEntreestock = entreestock.id");
            scriptSQL.append(" LEFT JOIN shop ON sortiestock.idShop = shop.id");
            scriptSQL.append(" LEFT JOIN client ON sortiestock.idClient = client.id");
            scriptSQL.append(" LEFT JOIN produit ON itemsortiestock.idProduit = produit.id");
            scriptSQL.append(" LEFT JOIN prixventeproduit ON itemsortiestock.idPrixVenteProduit = prixventeproduit.id");
            scriptSQL.append(" GROUP BY itemsortiestock.idSortieStock, sortiestock.dateHeure,");
            scriptSQL.append(" sortiestock.idShop, shop.nom,");
            scriptSQL.append(" sortiestock.idClient, client.nom, client.entreprise, client.telephone,");
            scriptSQL.append(" itemsortiestock.idProduto, produit.description,");
            scriptSQL.append(" itemsortiestock.idPrixVenteProduit, prixventeproduit.valeurUSD, prixventeproduit.dateHeure");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();
            if (res != null) {

                String id = "";
                SortieStock srtstck = new SortieStock();
                List<ItemSortieStock> listeItemsSortieStock = new ArrayList();

                while (res.next()) {

                    SortieStock sortieStock = new SortieStock(res.getString(2));
                    sortieStock.setDateHeure(res.getTimestamp(3));

                    Shop shop = new Shop(res.getString(4));
                    shop.setNom(res.getString(5));
                    sortieStock.setShop(shop);

                    Client client = new Client(res.getString(6));
                    client.setNom(res.getString(7));
                    client.setEntreprise(res.getString(8));
                    client.setTelephone(res.getString(9));
                    sortieStock.setClient(client);

                    Produit produit = new Produit(res.getString(10));
                    produit.setDescription(res.getString(11));

                    ItemSortieStock itemSortieStock = new ItemSortieStock(sortieStock, produit);
                    itemSortieStock.setQuantiteProduit(new BigDecimal(res.getString(1)));

                    PrixVenteProduit prixVenteProduit = new PrixVenteProduit(res.getString(12));
                    prixVenteProduit.setValeurUSD(res.getBigDecimal(13));
                    prixVenteProduit.setDateHeure(res.getTimestamp(14));
                    itemSortieStock.setPrixVenteProduit(prixVenteProduit);

                    if (id.equals(sortieStock.getId())) {
                        listeItemsSortieStock.add(itemSortieStock);
                    } else {
                        if (!res.first()) {
                            srtstck.setItemsSortieStock(listeItemsSortieStock);
                            listeSortiesStock.add(srtstck);
                        }
                        id = sortieStock.getId();

                        srtstck.setId(sortieStock.getId());
                        srtstck.setDateHeure(sortieStock.getDateHeure());
                        srtstck.setShop(sortieStock.getShop());
                        srtstck.setClient(sortieStock.getClient());

                        listeItemsSortieStock = new ArrayList();
                        listeItemsSortieStock.add(itemSortieStock);
                    }

                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listeSortiesStock;
    }

    public SortieStock selectionnerSortieStockParId(int idSortieStock) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        SortieStock srtstck = new SortieStock();
        List<ItemSortieStock> listeItemsSortieStock = new ArrayList();

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {

            scriptSQL = new StringBuilder("SELECT itemsortiestock.quantiteProduit,");
            scriptSQL.append(" itemsortiestock.idSortieStock, sortiestock.dateHeure,");
            scriptSQL.append(" sortiestock.idShop, shop.nom,");
            scriptSQL.append(" sortiestock.idClient, client.nom, client.entreprise, client.telephone,");
            scriptSQL.append(" itemsortiestock.idProduto, produit.description,");
            scriptSQL.append(" itemsortiestock.idPrixVenteProduit, prixventeproduit.valeurUSD, prixventeproduit.valeurFC, prixventeproduit.dateHeure");
            scriptSQL.append(" FROM itemsortiestock");
            scriptSQL.append(" LEFT JOIN sortiestock ON itemsortiestock.idEntreestock = entreestock.id");
            scriptSQL.append(" LEFT JOIN shop ON sortiestock.idShop = shop.id");
            scriptSQL.append(" LEFT JOIN client ON sortiestock.idClient = client.id");
            scriptSQL.append(" LEFT JOIN produit ON itemsortiestock.idProduit = produit.id");
            scriptSQL.append(" LEFT JOIN prixventeproduit ON itemsortiestock.idPrixVenteProduit = prixventeproduit.id");
            scriptSQL.append(" GROUP BY itemsortiestock.idSortieStock, sortiestock.dateHeure,");
            scriptSQL.append(" sortiestock.idShop, shop.nom,");
            scriptSQL.append(" sortiestock.idClient, client.nom, client.entreprise, client.telephone,");
            scriptSQL.append(" itemsortiestock.idProduto, produit.description,");
            scriptSQL.append(" itemsortiestock.idPrixVenteProduit, prixventeproduit.valeurUSD, prixventeproduit.dateHeure");
            scriptSQL.append(" WHERE itementreestock.idSortieStock=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, idSortieStock);
            res = prs.executeQuery();
            if (res != null) {

                while (res.next()) {

                    SortieStock sortieStock = new SortieStock(res.getString(2));
                    sortieStock.setDateHeure(res.getTimestamp(3));

                    Shop shop = new Shop(res.getString(4));
                    shop.setNom(res.getString(5));
                    sortieStock.setShop(shop);

                    Client client = new Client(res.getString(6));
                    client.setNom(res.getString(7));
                    client.setEntreprise(res.getString(8));
                    client.setTelephone(res.getString(9));
                    sortieStock.setClient(client);

                    Produit produit = new Produit(res.getString(10));
                    produit.setDescription(res.getString(11));

                    ItemSortieStock itemSortieStock = new ItemSortieStock(sortieStock, produit);
                    itemSortieStock.setQuantiteProduit(new BigDecimal(res.getString(1)));

                    PrixVenteProduit prixVenteProduit = new PrixVenteProduit(res.getString(12));
                    prixVenteProduit.setValeurUSD(res.getBigDecimal(13));
                    prixVenteProduit.setDateHeure(res.getTimestamp(14));
                    itemSortieStock.setPrixVenteProduit(prixVenteProduit);

                    listeItemsSortieStock.add(itemSortieStock);

                    if (res.first()) {
                        srtstck.setId(sortieStock.getId());
                        srtstck.setDateHeure(sortieStock.getDateHeure());
                        srtstck.setShop(sortieStock.getShop());
                        srtstck.setClient(sortieStock.getClient());
                    }

                }

                srtstck.setItemsSortieStock(listeItemsSortieStock);

            }
            prs.close();
            res.close();
            conexao.close();
        }
        return srtstck;
    }

    public boolean enregistrerSortieStock(SortieStock sortieStock) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {

            scriptSQL = new StringBuilder("INSERT INTO sortiestock(");
            scriptSQL.append(" idShop, idClient, dateHeure, id)");
            scriptSQL.append(" VALUES (?, ?, ?, ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setString(1, sortieStock.getShop().getId());
            prs.setString(2, sortieStock.getClient().getId());
            prs.setTimestamp(3, new Timestamp(sortieStock.getDateHeure().getTime()));
            prs.setString(4, sortieStock.getId());
            prs.execute();

            for (ItemSortieStock itemSortieStock : sortieStock.getItemsSortieStock()) {
                scriptSQL = new StringBuilder("INSERT INTO itemsortiestock(");
                scriptSQL.append(" idSortieStock, idProduit, idPrixVenteProduit, quantite )");
                scriptSQL.append(" VALUES (?, ?, ?, ?)");

                prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

                prs.setString(1, itemSortieStock.getSortieStock().getId());
                prs.setString(2, itemSortieStock.getProduit().getId());
                prs.setString(3, itemSortieStock.getPrixVenteProduit().getId());
                prs.setBigDecimal(4, itemSortieStock.getQuantiteProduit());
                prs.execute();
            }

            prs.close();
            conexao.close();
        }
        return true;
    }
}
