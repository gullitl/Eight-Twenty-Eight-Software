package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoftmiddleend.model.Client;
import com.cecilsoftwares.reussoftmiddleend.model.Client.ClientBuilder;
import com.cecilsoftwares.reussoftmiddleend.model.Collaborateur;
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
        SortieStockBuilder entreeStockBuilder = new SortieStockBuilder(0);
        List<ItemSortieStock> listeItemsEntreeStock = new ArrayList();

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
            scriptSQL.append(" WHERE itementreestock.idEntreeStock=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codeSortieStock);
            res = prs.executeQuery();
            if (res != null) {

                while (res.next()) {

                    Produit produit = new ProduitBuilder(res.getInt(12))
                            .description(res.getString(13))
                            .build();

                    Fournisseur fournisseur = new FournisseurBuilder(res.getInt(8))
                            .entreprise(res.getString(9))
                            .responsable(res.getString(10))
                            .telephone(res.getString(11))
                            .build();

                    TauxCarte tauxCarte = new TauxCarteBuilder(res.getInt(6))
                            .valeur(new BigDecimal(res.getString(7)))
                            .build();

                    EntreeStock entreeStock = new EntreeStockBuilder(res.getInt(4))
                            .dateHeure(res.getTimestamp(5))
                            .tauxCarte(tauxCarte)
                            .fournisseur(fournisseur)
                            .build();

                    ItemEntreeStock itemEntreeStock = new ItemEntreeStockBuilder(entreeStock, produit)
                            .prixAchatUSD(new BigDecimal(res.getString(1)))
                            .prixAchatFC(new BigDecimal(res.getString(2)))
                            .quantiteProduit(new BigDecimal(res.getString(3)))
                            .build();

                    listeItemsEntreeStock.add(itemEntreeStock);

                    if (res.first()) {
                        entreeStockBuilder = new EntreeStockBuilder(entreeStock.getCode())
                                .dateHeure(entreeStock.getDateHeure())
                                .tauxCarte(tauxCarte)
                                .fournisseur(fournisseur);
                    }

                }

                entreeStockBuilder.itemsEntreeStock(listeItemsEntreeStock);

            }
            prs.close();
            res.close();
            conexao.close();
        }
        return entreeStockBuilder.build();
    }

    public boolean sauvegarder(Collaborateur collaborateur) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("INSERT INTO collaborateur(");
            scriptSQL.append(" codeCollaborateur, preNom, nom, postnom, surnom,");
            scriptSQL.append(" utilisateur, idGroupeUtilisateur, motDePasse, idShop )");
            scriptSQL.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setInt(1, collaborateur.getCode());
            prs.setString(2, collaborateur.getPrenom());
            prs.setString(3, collaborateur.getNom());
            prs.setString(4, collaborateur.getPostnom());
            prs.setString(5, collaborateur.getSurnom());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

    public boolean actualiser(Collaborateur collaborateur) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("UPDATE collaborateur");
            scriptSQL.append(" SET preNom=?, nom=?, postnom=?, surnom=?, utilisateur=?,");
            scriptSQL.append(" idGroupeUtilisateur=?, motDePasse=?, idShop=?");
            scriptSQL.append(" WHERE codeCollaborateur=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setInt(1, collaborateur.getCode());
            prs.setString(2, collaborateur.getPrenom());
            prs.setString(3, collaborateur.getNom());
            prs.setString(4, collaborateur.getPostnom());
            prs.setString(5, collaborateur.getSurnom());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

    public boolean exclure(int codeCollaborateur) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("DELETE FROM collaborateur WHERE codeCollaborateur=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codeCollaborateur);

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

    public int selectionnerCodeCollaborateurSubsequent() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT Max(codeCollaborateur)+1 FROM collaborateur");
            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();
            if (res != null) {
                if (res.next()) {
                    int cdSubsequente = res.getInt(1);

                    prs.close();
                    res.close();
                    conexao.close();

                    return cdSubsequente;
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return 0;
    }
}
