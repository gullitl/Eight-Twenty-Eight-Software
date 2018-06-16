package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoftmiddleend.model.Client;
import com.cecilsoftwares.reussoftmiddleend.model.Client.ClientBuilder;
import com.cecilsoftwares.reussoftmiddleend.model.ItemSortieStock;
import com.cecilsoftwares.reussoftmiddleend.model.SortieStock;
import com.cecilsoftwares.reussoftmiddleend.model.SortieStock.SortieStockBuilder;
import com.cecilsoftwares.reussoftmiddleend.model.ItemSortieStock.ItemSortieStockBuilder;
import com.cecilsoftwares.reussoftmiddleend.model.Produit;
import com.cecilsoftwares.reussoftmiddleend.model.Produit.ProduitBuilder;
import com.cecilsoftwares.reussoftmiddleend.model.Shop;
import com.cecilsoftwares.reussoftmiddleend.model.Shop.ShopBuilder;
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
public class ItemSortieStockDao {

    private StringBuilder scriptSQL;
    private static ItemSortieStockDao uniqueInstance;

    public ItemSortieStockDao() {
    }

    public static synchronized ItemSortieStockDao getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ItemSortieStockDao();
        }
        return uniqueInstance;
    }

    public List<ItemSortieStock> listerTousLesItemsSortieStock() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<ItemSortieStock> listeItemsSortieStock;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            listeItemsSortieStock = new ArrayList();

            scriptSQL = new StringBuilder("SELECT itemsortiestock.prixVenteUSD, itemsortiestock.prixVenteFC, itemsortiestock.quantiteProduit,");
            scriptSQL.append(" itemsortiestock.idSortieStock, sortiestock.dateHeure,");
            scriptSQL.append(" sortiestock.idShop, shop.nom,");
            scriptSQL.append(" sortiestock.idClient, client.nom, client.responsable, client.telephone,");
            scriptSQL.append(" itemsortiestock.idProduto, produit.description");
            scriptSQL.append(" FROM itemsortiestock");
            scriptSQL.append(" LEFT JOIN sortiestock ON itemsortiestock.idSortiestock = sortiestock.code");
            scriptSQL.append(" LEFT JOIN shop ON sortiestock.idShop = shop.code");
            scriptSQL.append(" LEFT JOIN client ON sortiestock.idClient = client.code");
            scriptSQL.append(" LEFT JOIN produit ON itemsortiestock.idProduit = produit.code");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {

                    Produit produit = new ProduitBuilder(res.getInt(12))
                            .description(res.getString(13))
                            .build();

                    Client client = new ClientBuilder(res.getInt(8))
                            .nom(res.getString(9))
                            .entreprise(res.getString(10))
                            .telephone(res.getString(11))
                            .build();

                    Shop shop = new ShopBuilder(res.getInt(6))
                            .nom(res.getString(7))
                            .build();

                    SortieStock sortieStock = new SortieStockBuilder(res.getInt(4))
                            .dateHeure(res.getTimestamp(5))
                            .client(client)
                            .shop(shop)
                            .build();

                    ItemSortieStock itemSortieStock = new ItemSortieStockBuilder(sortieStock, produit)
                            .prixVenteUSD(new BigDecimal(res.getString(1)))
                            .prixVenteFC(new BigDecimal(res.getString(2)))
                            .quantiteProduit(new BigDecimal(res.getString(3)))
                            .build();

                    listeItemsSortieStock.add(itemSortieStock);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listeItemsSortieStock;
    }

    public List<ItemSortieStock> listerItemsSortieStockParCodeSortieStock(int codeSortieStock) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<ItemSortieStock> listeItemsSortieStock;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            listeItemsSortieStock = new ArrayList();

            scriptSQL = new StringBuilder("SELECT itemsortiestock.prixVenteUSD, itemsortiestock.prixVenteFC, itemsortiestock.quantiteProduit,");
            scriptSQL.append(" itemsortiestock.idSortieStock, sortiestock.dateHeure,");
            scriptSQL.append(" sortiestock.idShop, shop.nom,");
            scriptSQL.append(" sortiestock.idClient, client.nom, client.responsable, client.telephone,");
            scriptSQL.append(" itemsortiestock.idProduto, produit.description");
            scriptSQL.append(" FROM itemsortiestock");
            scriptSQL.append(" LEFT JOIN sortiestock ON itemsortiestock.idSortiestock = sortiestock.code");
            scriptSQL.append(" LEFT JOIN shop ON sortiestock.idShop = shop.code");
            scriptSQL.append(" LEFT JOIN client ON sortiestock.idClient = client.code");
            scriptSQL.append(" LEFT JOIN produit ON itemsortiestock.idProduit = produit.code");
            scriptSQL.append(" WHERE sortiestock.code=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codeSortieStock);
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {

                    Produit produit = new ProduitBuilder(res.getInt(12))
                            .description(res.getString(13))
                            .build();

                    Client client = new ClientBuilder(res.getInt(8))
                            .nom(res.getString(9))
                            .entreprise(res.getString(10))
                            .telephone(res.getString(11))
                            .build();

                    Shop shop = new ShopBuilder(res.getInt(6))
                            .nom(res.getString(7))
                            .build();

                    SortieStock sortieStock = new SortieStockBuilder(res.getInt(4))
                            .dateHeure(res.getTimestamp(5))
                            .client(client)
                            .shop(shop)
                            .build();

                    ItemSortieStock itemSortieStock = new ItemSortieStockBuilder(sortieStock, produit)
                            .prixVenteUSD(new BigDecimal(res.getString(1)))
                            .prixVenteFC(new BigDecimal(res.getString(2)))
                            .quantiteProduit(new BigDecimal(res.getString(3)))
                            .build();

                    listeItemsSortieStock.add(itemSortieStock);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listeItemsSortieStock;
    }

    public List<ItemSortieStock> listerItemsSortieStockParCodeProduit(int codeProduit) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<ItemSortieStock> listeItemsSortieStock;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            listeItemsSortieStock = new ArrayList();

            scriptSQL = new StringBuilder("SELECT itemsortiestock.prixVenteUSD, itemsortiestock.prixVenteFC, itemsortiestock.quantiteProduit,");
            scriptSQL.append(" itemsortiestock.idSortieStock, sortiestock.dateHeure,");
            scriptSQL.append(" sortiestock.idShop, shop.nom,");
            scriptSQL.append(" sortiestock.idClient, client.nom, client.responsable, client.telephone,");
            scriptSQL.append(" itemsortiestock.idProduto, produit.description");
            scriptSQL.append(" FROM itemsortiestock");
            scriptSQL.append(" LEFT JOIN sortiestock ON itemsortiestock.idSortiestock = sortiestock.code");
            scriptSQL.append(" LEFT JOIN shop ON sortiestock.idShop = shop.code");
            scriptSQL.append(" LEFT JOIN client ON sortiestock.idClient = client.code");
            scriptSQL.append(" LEFT JOIN produit ON itemsortiestock.idProduit = produit.code");
            scriptSQL.append(" WHERE produit.code=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codeProduit);
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {

                    Produit produit = new ProduitBuilder(res.getInt(12))
                            .description(res.getString(13))
                            .build();

                    Client client = new ClientBuilder(res.getInt(8))
                            .nom(res.getString(9))
                            .entreprise(res.getString(10))
                            .telephone(res.getString(11))
                            .build();

                    Shop shop = new ShopBuilder(res.getInt(6))
                            .nom(res.getString(7))
                            .build();

                    SortieStock sortieStock = new SortieStockBuilder(res.getInt(4))
                            .dateHeure(res.getTimestamp(5))
                            .client(client)
                            .shop(shop)
                            .build();

                    ItemSortieStock itemSortieStock = new ItemSortieStockBuilder(sortieStock, produit)
                            .prixVenteUSD(new BigDecimal(res.getString(1)))
                            .prixVenteFC(new BigDecimal(res.getString(2)))
                            .quantiteProduit(new BigDecimal(res.getString(3)))
                            .build();

                    listeItemsSortieStock.add(itemSortieStock);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listeItemsSortieStock;
    }

    public ItemSortieStock selectionnerItemSortieStockParCodeSortieStockECodeProduit(int codeSortieStock, int codeProduit) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {

            scriptSQL = new StringBuilder("SELECT itemsortiestock.prixVenteUSD, itemsortiestock.prixVenteFC, itemsortiestock.quantiteProduit,");
            scriptSQL.append(" itemsortiestock.idSortieStock, sortiestock.dateHeure,");
            scriptSQL.append(" sortiestock.idShop, shop.nom,");
            scriptSQL.append(" sortiestock.idClient, client.nom, client.responsable, client.telephone,");
            scriptSQL.append(" itemsortiestock.idProduto, produit.description");
            scriptSQL.append(" FROM itemsortiestock");
            scriptSQL.append(" LEFT JOIN sortiestock ON itemsortiestock.idSortiestock = sortiestock.code");
            scriptSQL.append(" LEFT JOIN shop ON sortiestock.idShop = shop.code");
            scriptSQL.append(" LEFT JOIN client ON sortiestock.idClient = client.code");
            scriptSQL.append(" LEFT JOIN produit ON itemsortiestock.idProduit = produit.code");
            scriptSQL.append(" WHERE sortiestock.code=? AND produit.code=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codeSortieStock);
            prs.setInt(2, codeProduit);
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {

                    Produit produit = new ProduitBuilder(res.getInt(12))
                            .description(res.getString(13))
                            .build();

                    Client client = new ClientBuilder(res.getInt(8))
                            .nom(res.getString(9))
                            .entreprise(res.getString(10))
                            .telephone(res.getString(11))
                            .build();

                    Shop shop = new ShopBuilder(res.getInt(6))
                            .nom(res.getString(7))
                            .build();

                    SortieStock sortieStock = new SortieStockBuilder(res.getInt(4))
                            .dateHeure(res.getTimestamp(5))
                            .client(client)
                            .shop(shop)
                            .build();

                    ItemSortieStock itemSortieStock = new ItemSortieStockBuilder(sortieStock, produit)
                            .prixVenteUSD(new BigDecimal(res.getString(1)))
                            .prixVenteFC(new BigDecimal(res.getString(2)))
                            .quantiteProduit(new BigDecimal(res.getString(3)))
                            .build();

                    prs.close();
                    res.close();
                    conexao.close();

                    return itemSortieStock;
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return null;
    }

    public boolean enregistrerItemSortieStock(ItemSortieStock itemSortieStock) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {

            scriptSQL = new StringBuilder("INSERT INTO itemsortiestock(");
            scriptSQL.append(" idSortieStock, idProduit, prixVenteUSD, prixVenteFC, quantiteProduit )");
            scriptSQL.append(" VALUES (?, ?, ?, ?, ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setInt(1, itemSortieStock.getSortieStock().getCode());
            prs.setInt(8, itemSortieStock.getProduit().getCode());
            prs.setBigDecimal(3, itemSortieStock.getPrixVenteUSD());
            prs.setBigDecimal(4, itemSortieStock.getPrixVenteFC());
            prs.setBigDecimal(5, itemSortieStock.getQuantiteProduit());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

}
