package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoft.enumarable.DispatchEnum;
import com.cecilsoftwares.reussoftbackend.model.CategorieProduit;
import com.cecilsoftwares.reussoftbackend.model.CategorieProduit.CategorieProduitBuilder;
import com.cecilsoftwares.reussoftbackend.model.EntreeStock;
import com.cecilsoftwares.reussoftbackend.model.EntreeStock.EntreeStockBuilder;
import com.cecilsoftwares.reussoftbackend.model.Fournisseur;
import com.cecilsoftwares.reussoftbackend.model.Fournisseur.FournisseurBuilder;
import com.cecilsoftwares.reussoftbackend.model.Produit;
import com.cecilsoftwares.reussoftbackend.model.Produit.ProduitBuilder;
import com.cecilsoftwares.reussoftbackend.model.Reseau;
import com.cecilsoftwares.reussoftbackend.model.Reseau.ReseauBuilder;
import com.cecilsoftwares.reussoftbackend.model.Shop;
import com.cecilsoftwares.reussoftbackend.model.Shop.ShopBuilder;
import com.cecilsoftwares.reussoftbackend.model.TauxCarte;
import com.cecilsoftwares.reussoftbackend.model.TauxCarte.TauxCarteBuilder;
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

    public List<EntreeStock> lister() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<EntreeStock> listeEntreeStocks;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            listeEntreeStocks = new ArrayList();

            scriptSQL = new StringBuilder("SELECT entreestock.codeEntreeStock, entreestock.dispatch, entreestock.prixUSD,");
            scriptSQL.append(" entreestock.prixFC, entreestock.qtdProduit, entreestock.dateHeure,");
            scriptSQL.append(" produit.codeProduit, produit.description, produit.prixUSD, produit.prixFC,");
            scriptSQL.append(" reseau.codeReseau, reseau.nom, reseau.nomAbrege,");
            scriptSQL.append(" categorieproduit.codeCategorieProduit, categorieproduit.description, categorieproduit.descriptionAbregee,");
            scriptSQL.append(" shop.codeShop, shop.nom, shop.adresse");
            scriptSQL.append(" fournisseur.codeFournisseur, fournisseur.entreprise, fournisseur.responsable,");
            scriptSQL.append(" tauxcarte.codeTauxCarte, tauxcarte.tauxCarte, tauxcarte.dateHeure");
            scriptSQL.append(" FROM entreestock");
            scriptSQL.append(" LEFT JOIN produit");
            scriptSQL.append(" ON entreestock.idProduit = produit.codeProduit");
            scriptSQL.append(" LEFT JOIN reseau");
            scriptSQL.append(" ON produit.idReseau = reseau.codeReseau");
            scriptSQL.append(" LEFT JOIN categorieproduit");
            scriptSQL.append(" ON produit.idCategorieProduit = categorieproduit.codeCategorieProduit");
            scriptSQL.append(" LEFT JOIN shop");
            scriptSQL.append(" ON entreeStock.idShop = shop.codeShop");
            scriptSQL.append(" LEFT JOIN fournisseur");
            scriptSQL.append(" ON entreestock.idFournisseur = fournisseur.codeFournisseur");
            scriptSQL.append(" LEFT JOIN tauxcarte");
            scriptSQL.append(" ON entreestock.idTauxCarte = tauxcarte.codeTauxCarte");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {

                    CategorieProduit categorieProduit = new CategorieProduitBuilder(res.getInt(14))
                            .description(res.getString(15))
                            .descriptionAbregee(res.getString(16))
                            .build();

                    Reseau reseau = new ReseauBuilder(res.getInt(11))
                            .nomAbrege(res.getString(12))
                            .nom(res.getString(13))
                            .build();

                    Produit produit = new ProduitBuilder(res.getInt(7))
                            .description(res.getString(8))
                            .prixUSD(res.getBigDecimal(9))
                            .prixFC(res.getBigDecimal(10))
                            .categorieProduit(categorieProduit)
                            .reseau(reseau)
                            .build();

                    Shop shop = new ShopBuilder(res.getInt(17))
                            .nom(res.getString(18))
                            .adresse(res.getString(19))
                            .build();

                    Fournisseur fournisseur = new FournisseurBuilder(res.getInt(20))
                            .entreprise(res.getString(21))
                            .responsable(res.getString(22))
                            .build();

                    TauxCarte tauxCarte = new TauxCarteBuilder(res.getInt(23))
                            .tauxCarte(res.getBigDecimal(24))
                            .dateHeure(res.getTimestamp(25))
                            .build();

                    EntreeStock entreeStock = new EntreeStockBuilder(res.getInt(1))
                            .dispatchEnum(res.getString(2).charAt(0) == DispatchEnum.VRAI.getType() ? DispatchEnum.VRAI : DispatchEnum.FAUX)
                            .prixUSD(res.getBigDecimal(3))
                            .prixFC(res.getBigDecimal(4))
                            .qtdProduit(res.getInt(5))
                            .dateHeure(res.getTimestamp(6))
                            .produit(produit)
                            .shop(shop)
                            .fournisseur(fournisseur)
                            .tauxCarte(tauxCarte)
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

    public EntreeStock selectionner(int codeEntreeStock) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            scriptSQL = new StringBuilder("SELECT entreestock.codeEntreeStock, entreestock.dispatch, entreestock.prixUSD,");
            scriptSQL.append(" entreestock.prixFC, entreestock.qtdProduit, entreestock.dateHeure,");
            scriptSQL.append(" produit.codeProduit, produit.description, produit.prixUSD, produit.prixFC,");
            scriptSQL.append(" reseau.codeReseau, reseau.nom, reseau.nomAbrege,");
            scriptSQL.append(" categorieproduit.codeCategorieProduit, categorieproduit.description, categorieproduit.descriptionAbregee,");
            scriptSQL.append(" shop.codeShop, shop.nom, shop.adresse");
            scriptSQL.append(" fournisseur.codeFournisseur, fournisseur.entreprise, fournisseur.responsable,");
            scriptSQL.append(" tauxcarte.codeTauxCarte, tauxcarte.tauxCarte, tauxcarte.dateHeure");
            scriptSQL.append(" FROM entreestock");
            scriptSQL.append(" LEFT JOIN produit");
            scriptSQL.append(" ON entreestock.idProduit = produit.codeProduit");
            scriptSQL.append(" LEFT JOIN reseau");
            scriptSQL.append(" ON produit.idReseau = reseau.codeReseau");
            scriptSQL.append(" LEFT JOIN categorieproduit");
            scriptSQL.append(" ON produit.idCategorieProduit = categorieproduit.codeCategorieProduit");
            scriptSQL.append(" LEFT JOIN shop");
            scriptSQL.append(" ON entreeStock.idShop = shop.codeShop");
            scriptSQL.append(" LEFT JOIN fournisseur");
            scriptSQL.append(" ON entreestock.idFournisseur = fournisseur.codeFournisseur");
            scriptSQL.append(" LEFT JOIN tauxcarte");
            scriptSQL.append(" ON entreestock.idTauxCarte = tauxcarte.codeTauxCarte");
            scriptSQL.append(" WHERE entreestock.codeEntreeStock=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codeEntreeStock);
            res = prs.executeQuery();
            if (res != null) {
                if (res.next()) {

                    CategorieProduit categorieProduit = new CategorieProduitBuilder(res.getInt(14))
                            .description(res.getString(15))
                            .descriptionAbregee(res.getString(16))
                            .build();

                    Reseau reseau = new ReseauBuilder(res.getInt(11))
                            .nomAbrege(res.getString(12))
                            .nom(res.getString(13))
                            .build();

                    Produit produit = new ProduitBuilder(res.getInt(7))
                            .description(res.getString(8))
                            .prixUSD(res.getBigDecimal(9))
                            .prixFC(res.getBigDecimal(10))
                            .categorieProduit(categorieProduit)
                            .reseau(reseau)
                            .build();

                    Shop shop = new ShopBuilder(res.getInt(17))
                            .nom(res.getString(18))
                            .adresse(res.getString(19))
                            .build();

                    Fournisseur fournisseur = new FournisseurBuilder(res.getInt(20))
                            .entreprise(res.getString(21))
                            .responsable(res.getString(22))
                            .build();

                    TauxCarte tauxCarte = new TauxCarteBuilder(res.getInt(23))
                            .tauxCarte(res.getBigDecimal(24))
                            .dateHeure(res.getTimestamp(25))
                            .build();

                    EntreeStock entreeStock = new EntreeStockBuilder(res.getInt(1))
                            .dispatchEnum(res.getString(2).charAt(0) == DispatchEnum.VRAI.getType() ? DispatchEnum.VRAI : DispatchEnum.FAUX)
                            .prixUSD(res.getBigDecimal(3))
                            .prixFC(res.getBigDecimal(4))
                            .qtdProduit(res.getInt(5))
                            .dateHeure(res.getTimestamp(6))
                            .produit(produit)
                            .shop(shop)
                            .fournisseur(fournisseur)
                            .tauxCarte(tauxCarte)
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

    public boolean sauvegarder(EntreeStock entreeStock) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            scriptSQL = new StringBuilder("INSERT INTO entreestock(");
            scriptSQL.append(" codeEntreeStock, idProduit, idShop, idFournisseur, dispatch,");
            scriptSQL.append(" prixUSD, prixFC, idTauxCarte, qtdProduit, dateHeure )");
            scriptSQL.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setInt(1, entreeStock.getCodeEntreeStock());
            prs.setInt(2, entreeStock.getProduit().getCodeProduit());
            prs.setInt(3, entreeStock.getShop().getCodeShop());
            prs.setInt(4, entreeStock.getFournisseur().getCodeFournisseur());
            prs.setString(5, String.valueOf(entreeStock.getDispatchEnum().getType()));
            prs.setBigDecimal(6, entreeStock.getPrixUSD());
            prs.setBigDecimal(7, entreeStock.getPrixFC());
            prs.setInt(7, entreeStock.getTauxCarte().getCodeTauxCarte());
            prs.setInt(8, entreeStock.getQtdProduit());
            prs.setInt(10, entreeStock.getShop().getCodeShop());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

    public int selectionnerCodeEntreeStockSubsequent() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            scriptSQL = new StringBuilder("SELECT Max(codeEntreeStock)+1 FROM entreestock");
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
