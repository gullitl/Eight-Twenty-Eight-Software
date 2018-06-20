package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoftmiddleend.model.ItemEntreeStock;
import com.cecilsoftwares.reussoftmiddleend.model.EntreeStock.EntreeStockBuilder;
import com.cecilsoftwares.reussoftmiddleend.model.Fournisseur;
import com.cecilsoftwares.reussoftmiddleend.model.Fournisseur.FournisseurBuilder;
import com.cecilsoftwares.reussoftmiddleend.model.EntreeStock;
import com.cecilsoftwares.reussoftmiddleend.model.ItemEntreeStock.ItemEntreeStockBuilder;
import com.cecilsoftwares.reussoftmiddleend.model.Produit;
import com.cecilsoftwares.reussoftmiddleend.model.Produit.ProduitBuilder;
import com.cecilsoftwares.reussoftmiddleend.model.TauxCarte;
import com.cecilsoftwares.reussoftmiddleend.model.TauxCarte.TauxCarteBuilder;
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
public class EntreeStockDao {

    private StringBuilder scriptSQL;
    private static EntreeStockDao uniqueInstance;

    public EntreeStockDao() {
    }

    public static synchronized EntreeStockDao getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new EntreeStockDao();
        }
        return uniqueInstance;
    }

    public List<EntreeStock> listerTousLesEntreesStockSansItems() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<EntreeStock> listeEntreesStock;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            listeEntreesStock = new ArrayList();

            scriptSQL = new StringBuilder("SELECT entreestock.code, entreestock.dateHeure,");
            scriptSQL.append(" entreestock.idTauxCarte, tauxcarte.valeur,");
            scriptSQL.append(" entreestock.idFournisseur, fournisseur.entreprise, fournisseur.responsable, fournisseur.telephone");
            scriptSQL.append(" FROM entreestock");
            scriptSQL.append(" LEFT JOIN tauxcarte ON entreestock.idTauxCarte = tauxcarte.code");
            scriptSQL.append(" LEFT JOIN fournisseur ON entreestock.idFournisseur = fournisseur.code");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {

                    Fournisseur fournisseur = new FournisseurBuilder(res.getInt(5))
                            .entreprise(res.getString(6))
                            .responsable(res.getString(7))
                            .telephone(res.getString(8))
                            .build();

                    TauxCarte tauxCarte = new TauxCarteBuilder(res.getInt(3))
                            .valeur(new BigDecimal(res.getString(4)))
                            .build();

                    EntreeStock entreeStock = new EntreeStockBuilder(res.getInt(1))
                            .dateHeure(res.getTimestamp(2))
                            .tauxCarte(tauxCarte)
                            .fournisseur(fournisseur)
                            .build();

                    listeEntreesStock.add(entreeStock);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listeEntreesStock;
    }

    public List<EntreeStock> listerTousLesEntreesStockAvecItems() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<EntreeStock> listeEntreesStock;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            listeEntreesStock = new ArrayList();

            scriptSQL = new StringBuilder("SELECT itementreestock.quantiteProduit,");
            scriptSQL.append(" itementreestock.idEntreeStock, entreestock.dateHeure,");
            scriptSQL.append(" entreestock.idTauxCarte, tauxcarte.valeur,");
            scriptSQL.append(" entreestock.idFournisseur, fournisseur.entreprise, fournisseur.responsable, fournisseur.telephone,");
            scriptSQL.append(" itementreestock.idProduto, produit.description");
            scriptSQL.append(" FROM itementreestock");
            scriptSQL.append(" LEFT JOIN entreestock ON itementreestock.idEntreestock = entreestock.code");
            scriptSQL.append(" LEFT JOIN tauxcarte ON itementreestock.idTauxCarte = tauxcarte.code");
            scriptSQL.append(" LEFT JOIN fournisseur ON itementreestock.idFournisseur = fournisseur.code");
            scriptSQL.append(" LEFT JOIN produit ON itementreestock.idProduit = produit.code");
            scriptSQL.append(" GROUP BY itementreestock.idEntreeStock, entreestock.dateHeure, entreestock.idTauxCarte, tauxcarte.valeur,");
            scriptSQL.append(" itementreestock.prixUSD, itementreestock.prixFC, itementreestock.quantiteProduit,");
            scriptSQL.append(" entreestock.idFournisseur, fournisseur.entreprise, fournisseur.responsable, fournisseur.telephone,");
            scriptSQL.append(" itementreestock.idProduto, produit.description");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();
            if (res != null) {

                int code = 0;
                EntreeStockBuilder entreeStockBuilder = new EntreeStockBuilder(0);
                List<ItemEntreeStock> listeItemsEntreeStock = new ArrayList();

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
                            .quantiteProduit(new BigDecimal(res.getString(1)))
                            .build();

                    if (code == entreeStock.getCode()) {
                        listeItemsEntreeStock.add(itemEntreeStock);
                    } else {
                        if (!res.first()) {
                            entreeStockBuilder.itemsEntreeStock(listeItemsEntreeStock);
                            listeEntreesStock.add(entreeStockBuilder.build());
                        }
                        code = entreeStock.getCode();

                        entreeStockBuilder = new EntreeStockBuilder(entreeStock.getCode())
                                .dateHeure(entreeStock.getDateHeure())
                                .tauxCarte(entreeStock.getTauxCarte())
                                .fournisseur(entreeStock.getFournisseur());

                        listeItemsEntreeStock = new ArrayList();
                        listeItemsEntreeStock.add(itemEntreeStock);
                    }

                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listeEntreesStock;
    }

    public EntreeStock selectionnerEntreeStockParCode(int codeEntreeStock) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        EntreeStockBuilder entreeStockBuilder = new EntreeStockBuilder(0);
        List<ItemEntreeStock> listeItemsEntreeStock = new ArrayList();

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {

            scriptSQL = new StringBuilder("SELECT itementreestock.quantiteProduit,");
            scriptSQL.append(" itementreestock.idEntreeStock, entreestock.dateHeure,");
            scriptSQL.append(" entreestock.idTauxCarte, tauxcarte.valeur,");
            scriptSQL.append(" entreestock.idFournisseur, fournisseur.entreprise, fournisseur.responsable, fournisseur.telephone,");
            scriptSQL.append(" itementreestock.idProduto, produit.description");
            scriptSQL.append(" FROM itementreestock");
            scriptSQL.append(" LEFT JOIN entreestock ON itementreestock.idEntreestock = entreestock.code");
            scriptSQL.append(" LEFT JOIN tauxcarte ON entreestock.idTauxCarte = tauxcarte.code");
            scriptSQL.append(" LEFT JOIN fournisseur ON entreestock.idFournisseur = fournisseur.code");
            scriptSQL.append(" LEFT JOIN produit ON itementreestock.idProduit = produit.code");
            scriptSQL.append(" GROUP BY itementreestock.idEntreeStock, entreestock.dateHeure, entreestock.idTauxCarte, tauxcarte.valeur,");
            scriptSQL.append(" itementreestock.prixUSD, itementreestock.prixFC, itementreestock.quantiteProduit,");
            scriptSQL.append(" entreestock.idFournisseur, fournisseur.entreprise, fournisseur.responsable, fournisseur.telephone,");
            scriptSQL.append(" itementreestock.idProduto, produit.description");
            scriptSQL.append(" WHERE itementreestock.idEntreeStock=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codeEntreeStock);
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
                            .quantiteProduit(new BigDecimal(res.getString(3)))
                            .build();

                    listeItemsEntreeStock.add(itemEntreeStock);

                    if (res.first()) {
                        entreeStockBuilder = new EntreeStockBuilder(entreeStock.getCode())
                                .dateHeure(entreeStock.getDateHeure())
                                .tauxCarte(entreeStock.getTauxCarte())
                                .fournisseur(entreeStock.getFournisseur());
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

    public boolean enregistrerEntreeStock(EntreeStock entreeStock) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {

            scriptSQL = new StringBuilder("INSERT INTO entreestock(");
            scriptSQL.append(" idFournisseur, idTauxCarte, dateHeure, code)");
            scriptSQL.append(" VALUES (?, ?, ?, ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setInt(1, entreeStock.getFournisseur().getCode());
            prs.setInt(2, entreeStock.getTauxCarte().getCode());
            prs.setTimestamp(3, new Timestamp(entreeStock.getDateHeure().getTime()));
            prs.setInt(4, entreeStock.getCode());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

    public int selecionaCodeEntreeStockSubsequente() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT Max(code)+1 FROM entreestock");
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
