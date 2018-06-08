package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoftmiddleend.model.Collaborateur;
import com.cecilsoftwares.reussoftmiddleend.model.Collaborateur.CollaborateurBuilder;
import com.cecilsoftwares.reussoftmiddleend.model.Produit;
import com.cecilsoftwares.reussoftmiddleend.model.ProfilUtilisateur;
import com.cecilsoftwares.reussoftmiddleend.model.ProfilUtilisateur.ProfilUtilisateurBuilder;
import com.cecilsoftwares.reussoftmiddleend.model.Shop;
import com.cecilsoftwares.reussoftmiddleend.model.Shop.ShopBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class StockDao {

    private StringBuilder scriptSQL;
    private static StockDao uniqueInstance;

    public StockDao() {
    }

    public static synchronized StockDao getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new StockDao();
        }
        return uniqueInstance;
    }

    public List<Collaborateur> lister() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<Collaborateur> listeCollaborateurs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            listeCollaborateurs = new ArrayList();

            scriptSQL = new StringBuilder("SELECT collaborateur.codeCollaborateur, collaborateur.utilizateur, collaborateur.motDePasse,");
            scriptSQL.append(" collaborateur.preNom, collaborateur.nom, collaborateur.postnom, collaborateur.surnom,");
            scriptSQL.append(" groupeutilisateur.codeGroupeUtilizateur, groupeutilisateur.description, groupeutilisateur.descriptionAbregee,");
            scriptSQL.append(" shop.codeShop, shop.nom, shop.adresse");
            scriptSQL.append(" FROM collaborateur");
            scriptSQL.append(" LEFT JOIN groupeutilisateur");
            scriptSQL.append(" ON collaborateur.idGroupeUtilisateur = groupeutilisateur.codeGroupeUtilizateur");
            scriptSQL.append(" LEFT JOIN shop");
            scriptSQL.append(" ON collaborateur.idShop = shop.codeShop");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {

                    ProfilUtilisateur profilUtilisateur = new ProfilUtilisateurBuilder(res.getInt(8))
                            .description(res.getString(9))
                            .descriptionAbregee(res.getString(10))
                            .build();

                    Shop shop = new ShopBuilder(res.getInt(11))
                            .nom(res.getString(12))
                            .adresse(res.getString(13))
                            .build();

                    Collaborateur collaborateur = new CollaborateurBuilder(res.getInt(1))
                            .nom(res.getString(5))
                            .postnom(res.getString(6))
                            .surnom(res.getString(7))
                            .shop(shop)
                            .build();

                    listeCollaborateurs.add(collaborateur);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listeCollaborateurs;
    }

    public Collaborateur selectionner(int codeCollaborateur) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT collaborateur.codeCollaborateur, collaborateur.utilizateur, collaborateur.motDePasse,");
            scriptSQL.append(" collaborateur.preNom, collaborateur.nom, collaborateur.postnom, collaborateur.surnom,");
            scriptSQL.append(" groupeutilisateur.codeGroupeUtilizateur, groupeutilisateur.description, groupeutilisateur.descriptionAbregee,");
            scriptSQL.append(" shop.codeShop, shop.nom, shop.adresse");
            scriptSQL.append(" FROM collaborateur");
            scriptSQL.append(" LEFT JOIN groupeutilisateur");
            scriptSQL.append(" ON collaborateur.idGroupeUtilisateur = groupeutilisateur.codeGroupeUtilizateur");
            scriptSQL.append(" LEFT JOIN shop");
            scriptSQL.append(" ON collaborateur.idShop = shop.codeShop");
            scriptSQL.append(" WHERE collaborateur.codeCollaborateur=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codeCollaborateur);
            res = prs.executeQuery();
            if (res != null) {
                if (res.next()) {

                    ProfilUtilisateur profilUtilisateur = new ProfilUtilisateurBuilder(res.getInt(8))
                            .description(res.getString(9))
                            .descriptionAbregee(res.getString(10))
                            .build();

                    Shop shop = new ShopBuilder(res.getInt(11))
                            .nom(res.getString(12))
                            .adresse(res.getString(13))
                            .build();

                    Collaborateur collaborateur = new CollaborateurBuilder(res.getInt(1))
                            .nom(res.getString(5))
                            .postnom(res.getString(6))
                            .surnom(res.getString(7))
                            .shop(shop)
                            .build();

                    prs.close();
                    res.close();
                    conexao.close();

                    return collaborateur;
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return null;
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

    private boolean actualiserStock(Produit produit, Shop shop, int quantiteMouvement) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        int conteur = 0;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT count(*) FROM stockproduit WHERE idProduit=? AND idShop=?");
            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setInt(1, produit.getCode());
            prs.setInt(2, shop.getCode());
            res = prs.executeQuery();

            if (res != null) {
                while (res.next()) {
                    conteur = res.getInt(1);
                }
            }

            if (conteur > 0) {
                scriptSQL = new StringBuilder("UPDATE stockproduit SET quantiteStock = quantiteStock + ?");
                scriptSQL.append(" WHERE idProduit=? AND idShop=?");

                prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

                prs.setInt(1, quantiteMouvement);
                prs.setInt(2, produit.getCode());
                prs.setInt(3, shop.getCode());

                prs.execute();
                conexao.close();
                prs.close();
                res.close();
                return true;

            } else {
                scriptSQL = new StringBuilder("INSERT INTO stockproduit (quantiteStock, idProduto, idShop)");
                scriptSQL.append(" VALUES (?, ?, ?)");

                prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

                prs.setInt(1, quantiteMouvement);
                prs.setInt(2, produit.getCode());
                prs.setInt(3, shop.getCode());

                prs.execute();
                conexao.close();
                prs.close();
                res.close();
                return true;
            }
        }
    }

    public boolean entrerStock(Produit produit, Shop shop, int quantiteMouvement)
            throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("INSERT INTO entreestoque(");
            scriptSQL.append("idProduto, quantiteProduit)");
            scriptSQL.append(" VALUES (?, ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setInt(1, produit.getCode());
            prs.setInt(2, quantiteMouvement);

            prs.execute();

            actualiserStock(produit, shop, quantiteMouvement);

            prs.close();
            conexao.close();
        }

        return true;
    }

    public boolean sortirStock(Produit produit, Shop shop, int quantiteMouvement)
            throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        StringBuilder scriptSQL;
        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("INSERT INTO sortiestock(");
            scriptSQL.append("idProduto, quantiteProduit)");
            scriptSQL.append(" VALUES (?, ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setInt(1, produit.getCode());
            prs.setInt(2, quantiteMouvement);

            prs.execute();

            actualiserStock(produit, shop, -quantiteMouvement);

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

    public boolean exclureStock(int codeStock) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("DELETE FROM stock WHERE code=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codeStock);

            prs.execute();
            prs.close();
            conexao.close();
        }

        return true;
    }

}
