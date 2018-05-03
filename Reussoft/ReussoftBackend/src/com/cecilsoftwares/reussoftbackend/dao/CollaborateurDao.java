package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoftbackend.model.Collaborateur;
import com.cecilsoftwares.reussoftbackend.model.Collaborateur.CollaborateurBuilder;
import com.cecilsoftwares.reussoftbackend.model.ProfilUtilisateur;
import com.cecilsoftwares.reussoftbackend.model.ProfilUtilisateur.ProfilUtilisateurBuilder;
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
public class CollaborateurDao {

    private StringBuilder scriptSQL;
    private static CollaborateurDao uniqueInstance;

    public CollaborateurDao() {
    }

    public static synchronized CollaborateurDao getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new CollaborateurDao();
        }
        return uniqueInstance;
    }

    public Collaborateur login(String utilizateur, String motDePasse) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            scriptSQL = new StringBuilder("SELECT collaborateur.codeCollaborateur, collaborateur.utilizateur, collaborateur.motDePasse,");
            scriptSQL.append(" collaborateur.preNom, collaborateur.nom, collaborateur.postnom, collaborateur.surnom,");
            scriptSQL.append(" profilutilisateur.codeProfilUtilizateur, profilutilisateur.description, profilutilisateur.descriptionAbregee,");
            scriptSQL.append(" shop.codeShop, shop.nom, shop.adresse");
            scriptSQL.append(" FROM collaborateur");
            scriptSQL.append(" LEFT JOIN profilutilisateur");
            scriptSQL.append(" ON collaborateur.idProfilUtilisateur = profilutilisateur.codeProfilUtilizateur");
            scriptSQL.append(" LEFT JOIN shop");
            scriptSQL.append(" ON collaborateur.idShop = shop.codeShop");
            scriptSQL.append(" WHERE collaborateur.utilizateur=? AND collaborateur.motDePasse=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setString(1, utilizateur);
            prs.setString(2, motDePasse);
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
                            .utilizateur(res.getString(2))
                            .motDePasse(res.getString(3))
                            .preNom(res.getString(4))
                            .nom(res.getString(5))
                            .postnom(res.getString(6))
                            .surnom(res.getString(7))
                            .profilUtilisateur(profilUtilisateur)
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

    public List<Collaborateur> lister() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<Collaborateur> listeCollaborateurs;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            scriptSQL = new StringBuilder("SELECT collaborateur.codeCollaborateur, collaborateur.utilizateur, collaborateur.motDePasse,");
            scriptSQL.append(" collaborateur.preNom, collaborateur.nom, collaborateur.postnom, collaborateur.surnom,");
            scriptSQL.append(" profilutilisateur.codeProfilUtilizateur, profilutilisateur.description, profilutilisateur.descriptionAbregee,");
            scriptSQL.append(" shop.codeShop, shop.nom, shop.adresse");
            scriptSQL.append(" FROM collaborateur");
            scriptSQL.append(" LEFT JOIN profilutilisateur");
            scriptSQL.append(" ON collaborateur.idProfilUtilisateur = profilutilisateur.codeProfilUtilizateur");
            scriptSQL.append(" LEFT JOIN shop");
            scriptSQL.append(" ON collaborateur.idShop = shop.codeShop");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();

            listeCollaborateurs = new ArrayList();
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
                            .utilizateur(res.getString(2))
                            .motDePasse(res.getString(3))
                            .preNom(res.getString(4))
                            .nom(res.getString(5))
                            .postnom(res.getString(6))
                            .surnom(res.getString(7))
                            .profilUtilisateur(profilUtilisateur)
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

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            scriptSQL = new StringBuilder("SELECT collaborateur.codeCollaborateur, collaborateur.utilizateur, collaborateur.motDePasse,");
            scriptSQL.append(" collaborateur.preNom, collaborateur.nom, collaborateur.postnom, collaborateur.surnom,");
            scriptSQL.append(" profilutilisateur.codeProfilUtilizateur, profilutilisateur.description, profilutilisateur.descriptionAbregee,");
            scriptSQL.append(" shop.codeShop, shop.nom, shop.adresse");
            scriptSQL.append(" FROM collaborateur");
            scriptSQL.append(" LEFT JOIN profilutilisateur");
            scriptSQL.append(" ON collaborateur.idProfilUtilisateur = profilutilisateur.codeProfilUtilizateur");
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
                            .utilizateur(res.getString(2))
                            .motDePasse(res.getString(3))
                            .preNom(res.getString(4))
                            .nom(res.getString(5))
                            .postnom(res.getString(6))
                            .surnom(res.getString(7))
                            .profilUtilisateur(profilUtilisateur)
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

    public boolean estUtilisateurExistant(Collaborateur collaborateur, boolean modeEdition) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            scriptSQL = new StringBuilder("SELECT codeCollaborateur FROM collaborateur");
            scriptSQL.append(" WHERE utilizateur=?");
            if (!modeEdition) {
                prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
                prs.setString(1, collaborateur.getUtilisateur());
            } else {
                scriptSQL.append(" and codeCollaborateur<>?");
                prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
                prs.setString(1, collaborateur.getUtilisateur());
                prs.setInt(2, collaborateur.getCodeCollaborateur());
            }

            res = prs.executeQuery();
            if (res != null) {
                if (res.next()) {
                    conexao.close();
                    return true;
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return false;
    }

    public boolean sauvegarder(Collaborateur collaborateur) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            scriptSQL = new StringBuilder("INSERT INTO collaborateur(");
            scriptSQL.append(" codeCollaborateur, preNom, nom, postnom, surnom,");
            scriptSQL.append(" utilisateur, idProfilUtilisateur, motDePasse, idShop )");
            scriptSQL.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setInt(1, collaborateur.getCodeCollaborateur());
            prs.setString(2, collaborateur.getPreNom());
            prs.setString(3, collaborateur.getNom());
            prs.setString(4, collaborateur.getPostnom());
            prs.setString(5, collaborateur.getSurnom());
            prs.setString(6, collaborateur.getUtilisateur());
            prs.setInt(7, collaborateur.getProfilUtilisateur().getCodeProfilUtilisateur());
            prs.setString(8, collaborateur.getMotDePasse());
            prs.setInt(9, collaborateur.getShop().getCodeShop());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

    public boolean actualiser(Collaborateur collaborateur) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            scriptSQL = new StringBuilder("UPDATE collaborateur");
            scriptSQL.append(" SET preNom=?, nom=?, postnom=?, surnom=?, utilisateur=?,");
            scriptSQL.append(" idProfilUtilisateur=?, motDePasse=?, idShop=?");
            scriptSQL.append(" WHERE codeCollaborateur=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setInt(1, collaborateur.getCodeCollaborateur());
            prs.setString(2, collaborateur.getPreNom());
            prs.setString(3, collaborateur.getNom());
            prs.setString(4, collaborateur.getPostnom());
            prs.setString(5, collaborateur.getSurnom());
            prs.setString(6, collaborateur.getUtilisateur());
            prs.setInt(7, collaborateur.getProfilUtilisateur().getCodeProfilUtilisateur());
            prs.setString(8, collaborateur.getMotDePasse());
            prs.setInt(9, collaborateur.getShop().getCodeShop());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

    public boolean exclure(int codeCollaborateur) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
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

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
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
