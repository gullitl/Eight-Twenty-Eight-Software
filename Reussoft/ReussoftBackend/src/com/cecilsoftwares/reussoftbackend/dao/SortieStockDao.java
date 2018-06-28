package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoftmiddleend.model.Client;
import com.cecilsoftwares.reussoftmiddleend.model.ItemSortieStock;
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

                    Client client = new Client(res.getInt(5));
                    client.setEntreprise(res.getString(6));
                    client.setNom(res.getString(7));
                    client.setTelephone(res.getString(8));

                    Shop shop = new Shop(res.getInt(3));
                    shop.setNom(res.getString(4));

                    SortieStock entreeStock = new SortieStock(res.getInt(1));
                    entreeStock.setDateHeure(res.getTimestamp(2));
                    entreeStock.setShop(shop);
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
                SortieStock srtstck = new SortieStock();
                List<ItemSortieStock> listeItemsSortieStock = new ArrayList();

                while (res.next()) {

                    Produit produit = new Produit(res.getInt(12));
                    produit.setDescription(res.getString(13));

                    Client client = new Client(res.getInt(8));
                    client.setEntreprise(res.getString(9));
                    client.setNom(res.getString(10));
                    client.setTelephone(res.getString(11));

                    Shop shop = new Shop(res.getInt(6));
                    shop.setNom(res.getString(7));

                    SortieStock sortieStock = new SortieStock(res.getInt(4));
                    sortieStock.setDateHeure(res.getTimestamp(5));
                    sortieStock.setShop(shop);
                    sortieStock.setClient(client);

                    ItemSortieStock itemSortieStock = new ItemSortieStock(sortieStock, produit);
                    itemSortieStock.setQuantiteProduit(new BigDecimal(res.getString(3)));

                    if (code == sortieStock.getCode()) {
                        listeItemsSortieStock.add(itemSortieStock);
                    } else {
                        if (!res.first()) {
                            srtstck.setItemsSortieStock(listeItemsSortieStock);
                            listeSortiesStock.add(srtstck);
                        }
                        code = sortieStock.getCode();

                        srtstck.setCode(sortieStock.getCode());
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

    public SortieStock selectionnerSortieStockParCode(int codeSortieStock) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        SortieStock srtstck = new SortieStock();
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

                    Produit produit = new Produit(res.getInt(12));
                    produit.setDescription(res.getString(13));

                    Client client = new Client(res.getInt(8));
                    client.setEntreprise(res.getString(9));
                    client.setNom(res.getString(10));
                    client.setTelephone(res.getString(11));

                    Shop shop = new Shop(res.getInt(6));
                    shop.setNom(res.getString(7));

                    SortieStock sortieStock = new SortieStock(res.getInt(4));
                    sortieStock.setDateHeure(res.getTimestamp(5));
                    sortieStock.setShop(shop);
                    sortieStock.setClient(client);

                    ItemSortieStock itemSortieStock = new ItemSortieStock(sortieStock, produit);
                    itemSortieStock.setQuantiteProduit(new BigDecimal(res.getString(3)));

                    listeItemsSortieStock.add(itemSortieStock);

                    if (res.first()) {
                        srtstck.setCode(sortieStock.getCode());
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
