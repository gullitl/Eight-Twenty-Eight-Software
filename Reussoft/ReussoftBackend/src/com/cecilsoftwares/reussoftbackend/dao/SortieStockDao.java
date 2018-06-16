package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoftmiddleend.model.Client;
import com.cecilsoftwares.reussoftmiddleend.model.Client.ClientBuilder;
import com.cecilsoftwares.reussoftmiddleend.model.ItemSortieStock;
import com.cecilsoftwares.reussoftmiddleend.model.ItemSortieStock.ItemSortieStockBuilder;
import com.cecilsoftwares.reussoftmiddleend.model.Produit;
import com.cecilsoftwares.reussoftmiddleend.model.Produit.ProduitBuilder;
import com.cecilsoftwares.reussoftmiddleend.model.Shop;
import com.cecilsoftwares.reussoftmiddleend.model.Shop.ShopBuilder;
import com.cecilsoftwares.reussoftmiddleend.model.SortieStock;
import com.cecilsoftwares.reussoftmiddleend.model.SortieStock.SortieStockBuilder;
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

            scriptSQL = new StringBuilder("SELECT sortiestock.code, entreestock.dateHeure,");
            scriptSQL.append(" sortiestock.idShop, shop.nom,");
            scriptSQL.append(" sortiestock.idClient, client.nom, client.entreprise, client.telephone");
            scriptSQL.append(" FROM sortiestock");
            scriptSQL.append(" LEFT JOIN shop ON sortiestock.idShop = shop.code");
            scriptSQL.append(" LEFT JOIN client ON sortiestock.idClient = client.code");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {

                    Client client = new ClientBuilder(res.getInt(5))
                            .entreprise(res.getString(6))
                            .nom(res.getString(7))
                            .telephone(res.getString(8))
                            .build();

                    Shop shop = new ShopBuilder(res.getInt(3))
                            .nom(res.getString(4))
                            .build();

                    SortieStock entreeStock = new SortieStockBuilder(res.getInt(1))
                            .dateHeure(res.getTimestamp(2))
                            .shop(shop)
                            .client(client)
                            .build();

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

            scriptSQL = new StringBuilder("SELECT itemsortiestock.prixVenteUSD, itemsortiestock.prixVenteFC, itemsortiestock.quantiteProduit,");
            scriptSQL.append(" itemsortiestock.idSortieStock, sortiestock.dateHeure,");
            scriptSQL.append(" sortiestock.idShop, shop.nom,");
            scriptSQL.append(" sortiestock.idClient, client.nom, client.entreprise, client.telephone,");
            scriptSQL.append(" itemsortiestock.idProduto, produit.description");
            scriptSQL.append(" FROM itemsortiestock");
            scriptSQL.append(" LEFT JOIN sortiestock ON itemsortiestock.idEntreestock = entreestock.code");
            scriptSQL.append(" LEFT JOIN shop ON itemsortiestock.idShop = shop.code");
            scriptSQL.append(" LEFT JOIN client ON itemsortiestock.idClient = client.code");
            scriptSQL.append(" LEFT JOIN produit ON itemsortiestock.idProduit = produit.code");
            scriptSQL.append(" GROUP BY itemsortiestock.idSortieStock, sortiestock.dateHeure, sortiestock.idShop, shop.nom,");
            scriptSQL.append(" itemsortiestock.prixVenteUSD, itemsortiestock.prixVenteFC, itemsortiestock.quantiteProduit,");
            scriptSQL.append(" sortiestock.idClient, client.nom, cleint.entreprise, client.telephone,");
            scriptSQL.append(" itemsortiestock.idProduto, produit.description");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();
            if (res != null) {

                int code = 0;
                SortieStockBuilder sortieStockBuilder = new SortieStockBuilder(0);
                List<ItemSortieStock> listeItemsSortieStock = new ArrayList();

                while (res.next()) {

                    Produit produit = new ProduitBuilder(res.getInt(12))
                            .description(res.getString(13))
                            .build();

                    Client client = new ClientBuilder(res.getInt(8))
                            .entreprise(res.getString(9))
                            .nom(res.getString(10))
                            .telephone(res.getString(11))
                            .build();

                    Shop shop = new ShopBuilder(res.getInt(6))
                            .nom(res.getString(7))
                            .build();

                    SortieStock sortieStock = new SortieStockBuilder(res.getInt(4))
                            .dateHeure(res.getTimestamp(5))
                            .shop(shop)
                            .client(client)
                            .build();

                    ItemSortieStock itemSortieStock = new ItemSortieStockBuilder(sortieStock, produit)
                            .prixVenteUSD(new BigDecimal(res.getString(1)))
                            .prixVenteFC(new BigDecimal(res.getString(2)))
                            .quantiteProduit(new BigDecimal(res.getString(3)))
                            .build();

                    if (code == sortieStock.getCode()) {
                        listeItemsSortieStock.add(itemSortieStock);
                    } else {
                        if (!res.first()) {
                            sortieStockBuilder.itemsSortieStock(listeItemsSortieStock);
                            listeSortiesStock.add(sortieStockBuilder.build());
                        }
                        code = sortieStock.getCode();

                        sortieStockBuilder = new SortieStockBuilder(sortieStock.getCode())
                                .dateHeure(sortieStock.getDateHeure())
                                .shop(sortieStock.getShop())
                                .client(sortieStock.getClient());

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

    public SortieStock selectionnerSortieStockParCode(int codeSortieStock) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        SortieStockBuilder sortieStockBuilder = new SortieStockBuilder(0);
        List<ItemSortieStock> listeItemsSortieStock = new ArrayList();

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {

            scriptSQL = new StringBuilder("SELECT itemsortiestock.prixVenteUSD, itemsortiestock.prixVenteFC, itemsortiestock.quantiteProduit,");
            scriptSQL.append(" itemsortiestock.idSortieStock, sortiestock.dateHeure,");
            scriptSQL.append(" sortiestock.idShop, shop.nom,");
            scriptSQL.append(" sortiestock.idClient, client.nom, client.entreprise, client.telephone,");
            scriptSQL.append(" itemsortiestock.idProduto, produit.description");
            scriptSQL.append(" FROM itemsortiestock");
            scriptSQL.append(" LEFT JOIN sortiestock ON itemsortiestock.idEntreestock = entreestock.code");
            scriptSQL.append(" LEFT JOIN shop ON itemsortiestock.idShop = shop.code");
            scriptSQL.append(" LEFT JOIN client ON itemsortiestock.idClient = client.code");
            scriptSQL.append(" LEFT JOIN produit ON itemsortiestock.idProduit = produit.code");
            scriptSQL.append(" GROUP BY itemsortiestock.idSortieStock, sortiestock.dateHeure, sortiestock.idShop, shop.nom,");
            scriptSQL.append(" itemsortiestock.prixVenteUSD, itemsortiestock.prixVenteFC, itemsortiestock.quantiteProduit,");
            scriptSQL.append(" sortiestock.idClient, client.nom, cleint.entreprise, client.telephone,");
            scriptSQL.append(" itemsortiestock.idProduto, produit.description");
            scriptSQL.append(" WHERE itementreestock.idSortieStock=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codeSortieStock);
            res = prs.executeQuery();
            if (res != null) {

                while (res.next()) {

                    Produit produit = new ProduitBuilder(res.getInt(12))
                            .description(res.getString(13))
                            .build();

                    Client client = new ClientBuilder(res.getInt(8))
                            .entreprise(res.getString(9))
                            .nom(res.getString(10))
                            .telephone(res.getString(11))
                            .build();

                    Shop shop = new ShopBuilder(res.getInt(6))
                            .nom(res.getString(7))
                            .build();

                    SortieStock sortieStock = new SortieStockBuilder(res.getInt(4))
                            .dateHeure(res.getTimestamp(5))
                            .shop(shop)
                            .client(client)
                            .build();

                    ItemSortieStock itemSortieStock = new ItemSortieStockBuilder(sortieStock, produit)
                            .prixVenteUSD(new BigDecimal(res.getString(1)))
                            .prixVenteFC(new BigDecimal(res.getString(2)))
                            .quantiteProduit(new BigDecimal(res.getString(3)))
                            .build();

                    listeItemsSortieStock.add(itemSortieStock);

                    if (res.first()) {
                        sortieStockBuilder = new SortieStockBuilder(sortieStock.getCode())
                                .dateHeure(sortieStock.getDateHeure())
                                .shop(sortieStock.getShop())
                                .client(sortieStock.getClient());
                    }

                }

                sortieStockBuilder.itemsSortieStock(listeItemsSortieStock);

            }
            prs.close();
            res.close();
            conexao.close();
        }
        return sortieStockBuilder.build();
    }

    public boolean enregistrerSortieStock(SortieStock sortieStock) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {

            scriptSQL = new StringBuilder("INSERT INTO sortiestock(");
            scriptSQL.append(" idShop, idClient, dateHeure, code)");
            scriptSQL.append(" VALUES (?, ?, ?, ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setInt(1, sortieStock.getShop().getCode());
            prs.setInt(2, sortieStock.getClient().getCode());
            prs.setTimestamp(3, new Timestamp(sortieStock.getDateHeure().getTime()));
            prs.setInt(4, sortieStock.getCode());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }
}
