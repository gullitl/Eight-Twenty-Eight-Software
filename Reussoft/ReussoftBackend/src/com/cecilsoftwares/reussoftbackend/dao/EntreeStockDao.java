package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoftbackend.model.EntreeStock;
import com.cecilsoftwares.reussoftbackend.model.EntreeStock.EntreeStockBuilder;
import com.cecilsoftwares.reussoftbackend.model.GroupeUtilisateur;
import com.cecilsoftwares.reussoftbackend.model.GroupeUtilisateur.GroupeUtilisateurBuilder;
import com.cecilsoftwares.reussoftbackend.model.Produit;
import com.cecilsoftwares.reussoftbackend.model.Shop;
import com.cecilsoftwares.reussoftbackend.model.Shop.ShopBuilder;
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
            scriptSQL.append(" entreestock.prixFC, entreestock.qtdProduit, entreestock.dateHeure, entreeStock.surnom,");
            scriptSQL.append(" produit.codeProduit, produit.idReseau, produit.description, produit.prixUSD, produit.prixFC,");
            scriptSQL.append(" produit.idCategorieProduit, shop.codeShop, shop.nom, shop.adresse");
            scriptSQL.append(" fournisseur.codeFournisseur, fournisseur.entreprise, fournisseur.responsable");
            scriptSQL.append(" tauxcarte.codeTauxCarte, tauxcarte.idShop, tauxcarte.tauxCarte, tauxcarte.dateHeure");
            scriptSQL.append(" FROM entreestock");
            scriptSQL.append(" LEFT JOIN fournisseur");
            scriptSQL.append(" ON entreestock.idFournisseur = fournisseur.codeFournisseur");
            scriptSQL.append(" LEFT JOIN shop");
            scriptSQL.append(" ON entreeStock.idShop = shop.codeShop");
            scriptSQL.append(" LEFT JOIN tauxcarte");
            scriptSQL.append(" ON entreestock.idTauxCarte = tauxcarte.codeTauxCarte");

//            codeEntreeStock : int
//  idProduit : int
//  idShop : int
//  idFournisseur : int
//  dispatch : char
//  prixUSD : decimal
//  prixFC : decimal
//  idTauxCarte : int
//  qtdProduit : int
//  dateHeure : varchar
            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {

                    Produit produit = new GroupeUtilisateurBuilder(res.getInt(8))
                            .description(res.getString(9))
                            .descriptionAbregee(res.getString(10))
                            .build();

                    Shop shop = new ShopBuilder(res.getInt(11))
                            .nom(res.getString(12))
                            .adresse(res.getString(13))
                            .build();

                    EntreeStock entreeStock = new EntreeStockBuilder(res.getInt(1))
                            .utilizateur(res.getString(2))
                            .motDePasse(res.getString(3))
                            .preNom(res.getString(4))
                            .nom(res.getString(5))
                            .postnom(res.getString(6))
                            .surnom(res.getString(7))
                            .groupeUtilisateur(produit)
                            .shop(shop)
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
            scriptSQL = new StringBuilder("SELECT entreeStock.codeEntreeStock, entreeStock.utilizateur, entreeStock.motDePasse,");
            scriptSQL.append(" entreeStock.preNom, entreeStock.nom, entreeStock.postnom, entreeStock.surnom,");
            scriptSQL.append(" groupeutilisateur.codeGroupeUtilizateur, groupeutilisateur.description, groupeutilisateur.descriptionAbregee,");
            scriptSQL.append(" shop.codeShop, shop.nom, shop.adresse");
            scriptSQL.append(" FROM entreeStock");
            scriptSQL.append(" LEFT JOIN groupeutilisateur");
            scriptSQL.append(" ON entreeStock.idGroupeUtilisateur = groupeutilisateur.codeGroupeUtilizateur");
            scriptSQL.append(" LEFT JOIN shop");
            scriptSQL.append(" ON entreeStock.idShop = shop.codeShop");
            scriptSQL.append(" WHERE entreeStock.codeEntreeStock=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codeEntreeStock);
            res = prs.executeQuery();
            if (res != null) {
                if (res.next()) {

                    GroupeUtilisateur groupeUtilisateur = new GroupeUtilisateurBuilder(res.getInt(8))
                            .description(res.getString(9))
                            .descriptionAbregee(res.getString(10))
                            .build();

                    Shop shop = new ShopBuilder(res.getInt(11))
                            .nom(res.getString(12))
                            .adresse(res.getString(13))
                            .build();

                    EntreeStock entreeStock = new EntreeStockBuilder(res.getInt(1))
                            .utilizateur(res.getString(2))
                            .motDePasse(res.getString(3))
                            .preNom(res.getString(4))
                            .nom(res.getString(5))
                            .postnom(res.getString(6))
                            .surnom(res.getString(7))
                            .groupeUtilisateur(groupeUtilisateur)
                            .shop(shop)
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
            scriptSQL = new StringBuilder("INSERT INTO entreeStock(");
            scriptSQL.append(" codeEntreeStock, preNom, nom, postnom, surnom,");
            scriptSQL.append(" utilisateur, idGroupeUtilisateur, motDePasse, idShop )");
            scriptSQL.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setInt(1, entreeStock.getCodeEntreeStock());
            prs.setString(2, entreeStock.getPreNom());
            prs.setString(3, entreeStock.getNom());
            prs.setString(4, entreeStock.getPostnom());
            prs.setString(5, entreeStock.getSurnom());
            prs.setString(6, entreeStock.getUtilisateur());
            prs.setInt(7, entreeStock.getGroupeUtilisateur().getCodeGroupeUtilisateur());
            prs.setString(8, entreeStock.getMotDePasse());
            prs.setInt(9, entreeStock.getShop().getCodeShop());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

    public boolean actualiser(EntreeStock entreeStock) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            scriptSQL = new StringBuilder("UPDATE entreeStock");
            scriptSQL.append(" SET preNom=?, nom=?, postnom=?, surnom=?, utilisateur=?,");
            scriptSQL.append(" idGroupeUtilisateur=?, motDePasse=?, idShop=?");
            scriptSQL.append(" WHERE codeEntreeStock=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setInt(1, entreeStock.getCodeEntreeStock());
            prs.setString(2, entreeStock.getPreNom());
            prs.setString(3, entreeStock.getNom());
            prs.setString(4, entreeStock.getPostnom());
            prs.setString(5, entreeStock.getSurnom());
            prs.setString(6, entreeStock.getUtilisateur());
            prs.setInt(7, entreeStock.getGroupeUtilisateur().getCodeGroupeUtilisateur());
            prs.setString(8, entreeStock.getMotDePasse());
            prs.setInt(9, entreeStock.getShop().getCodeShop());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

    public boolean exclure(int codeEntreeStock) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            scriptSQL = new StringBuilder("DELETE FROM entreeStock WHERE codeEntreeStock=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codeEntreeStock);

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
            scriptSQL = new StringBuilder("SELECT Max(codeEntreeStock)+1 FROM entreeStock");
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
