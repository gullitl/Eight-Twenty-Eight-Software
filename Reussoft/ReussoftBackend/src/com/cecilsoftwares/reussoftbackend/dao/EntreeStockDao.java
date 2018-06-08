package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoftmiddleend.model.EntreeStock;
import com.cecilsoftwares.reussoftmiddleend.model.EntreeStock.EntreeStockBuilder;
import com.cecilsoftwares.reussoftmiddleend.model.Fournisseur;
import com.cecilsoftwares.reussoftmiddleend.model.Fournisseur.FournisseurBuilder;
import com.cecilsoftwares.reussoftmiddleend.model.MouvementStock;
import com.cecilsoftwares.reussoftmiddleend.model.MouvementStock.MouvementStockBuilder;
import com.cecilsoftwares.reussoftmiddleend.model.Produit;
import com.cecilsoftwares.reussoftmiddleend.model.Produit.ProduitBuilder;
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

    public List<EntreeStock> listerToutesLesEntreeStocks() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<EntreeStock> listeEntreeStocks;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            listeEntreeStocks = new ArrayList();

            scriptSQL = new StringBuilder("SELECT entreestock.code, entreestock.dateHeure, entreestock.observation,");
            scriptSQL.append(" entreestock.prixUSD, entreestock.prixFC, entreestock.qunatiteProduit,");
            scriptSQL.append(" entreestock.idFournisseur, fournisseur.entreprise, fournisseur.responsable,");
            scriptSQL.append(" fournisseur.telephone, fournisseur.observation,");
            scriptSQL.append(" entreestock.idProduit, produit.description, produit.observation,");
            scriptSQL.append(" entreestock.idTauxCarte, tauxcarte.dateHeure, tauxcarte.valeur, ");
            scriptSQL.append(" FROM entreestock");
            scriptSQL.append(" LEFT JOIN fournisseur ON entreestock.idFournisseur = fournisseur.code");
            scriptSQL.append(" LEFT JOIN produit ON fournisseur.idProduit = produit.code");
            scriptSQL.append(" LEFT JOIN tauxcarte ON entreestock.idTauxCarte = tauxcarte.code");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {

                    MouvementStock mouvementStock = new MouvementStockBuilder(res.getInt(1)).build();

                    Fournisseur fournisseur = new FournisseurBuilder(res.getInt(6))
                            .entreprise(res.getString(7))
                            .responsable(res.getString(8))
                            .telephone(res.getString(9))
                            .observation(res.getString(10))
                            .build();

                    Produit produit = new ProduitBuilder(res.getInt(11))
                            .description(res.getString(12))
                            .observation(res.getString(13))
                            .build();

                    EntreeStock entreeStock = new EntreeStockBuilder(mouvementStock, fournisseur, produit)
                            .prixAchatUSD(new BigDecimal(res.getString(4)))
                            .prixAchatFC(new BigDecimal(res.getString(5)))
                            .quantiteProduit(new BigDecimal(res.getString(6)))
                            .build();

                    listeEntreeStocks.add(entreeStock);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listeEntreeStocks;
    }

    public EntreeStock selectionnerEntreeStockParCode(int codeEntreeStock) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {

            scriptSQL = new StringBuilder("SELECT entreestock.code, entreestock.dateHeure, entreestock.observation,");
            scriptSQL.append(" entreestock.prixUSD, entreestock.prixFC, entreestock.qunatiteProduit,");
            scriptSQL.append(" entreestock.idFournisseur, fournisseur.entreprise, fournisseur.responsable,");
            scriptSQL.append(" fournisseur.telephone, fournisseur.observation,");
            scriptSQL.append(" entreestock.idProduit, produit.description, produit.observation,");
            scriptSQL.append(" entreestock.idTauxCarte, tauxcarte.dateHeure, tauxcarte.valeur, ");
            scriptSQL.append(" FROM entreestock");
            scriptSQL.append(" LEFT JOIN fournisseur ON entreestock.idFournisseur = fournisseur.code");
            scriptSQL.append(" LEFT JOIN produit ON fournisseur.idProduit = produit.code");
            scriptSQL.append(" LEFT JOIN tauxcarte ON entreestock.idTauxCarte = tauxcarte.code");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codeEntreeStock);
            res = prs.executeQuery();
            if (res != null) {
                if (res.next()) {

                    MouvementStock mouvementStock = new MouvementStockBuilder(res.getInt(1)).build();

                    Fournisseur fournisseur = new FournisseurBuilder(res.getInt(6))
                            .entreprise(res.getString(7))
                            .responsable(res.getString(8))
                            .telephone(res.getString(9))
                            .observation(res.getString(10))
                            .build();

                    Produit produit = new ProduitBuilder(res.getInt(11))
                            .description(res.getString(12))
                            .observation(res.getString(13))
                            .build();

                    EntreeStock entreeStock = new EntreeStockBuilder(mouvementStock, fournisseur, produit)
                            .prixAchatUSD(new BigDecimal(res.getString(4)))
                            .prixAchatFC(new BigDecimal(res.getString(5)))
                            .quantiteProduit(new BigDecimal(res.getString(6)))
                            .build();

                    prs.close();
                    res.close();
                    conexao.close();

                    return entreeStock;
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return null;
    }

    public boolean enregistrerEntreeStock(EntreeStock entreeStock) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {

            scriptSQL = new StringBuilder("INSERT INTO entreestock(");
            scriptSQL.append(" dateHeure, prixAchatUSD, prixAchatFC, quantiteProduit, observation,");
            scriptSQL.append("  idFournisseur, idProduit, idTauxCarte, code)");
            scriptSQL.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setInt(1, entreeStock.getMouvementStock().getCode());
            prs.setInt(7, entreeStock.getFournisseur().getCode());
            prs.setInt(8, entreeStock.getProduit().getCode());
            prs.setBigDecimal(3, entreeStock.getPrixAchatUSD());
            prs.setBigDecimal(4, entreeStock.getPrixAchatFC());
            prs.setBigDecimal(5, entreeStock.getQuantiteProduit());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

}
