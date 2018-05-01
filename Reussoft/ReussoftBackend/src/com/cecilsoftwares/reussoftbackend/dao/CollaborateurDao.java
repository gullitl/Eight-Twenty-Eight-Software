package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoftbackend.model.Collaborateur;
import com.cecilsoftwares.reussoftbackend.model.Collaborateur.CollaborateurBuilder;
import com.cecilsoftwares.reussoftbackend.model.GroupeUtilisateur;
import com.cecilsoftwares.reussoftbackend.model.GroupeUtilisateur.GroupeUtilisateurBuilder;
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
            scriptSQL.append(" groupeutilisateur.codeGroupeUtilizateur, groupeutilisateur.description, groupeutilisateur.descriptionAbregee,");
            scriptSQL.append(" shop.codeShop, shop.nom, shop.adresse");
            scriptSQL.append(" FROM collaborateur");
            scriptSQL.append(" LEFT JOIN groupeutilisateur");
            scriptSQL.append(" ON collaborateur.idGroupeUtilisateur = groupeutilisateur.codeGroupeUtilizateur");
            scriptSQL.append(" LEFT JOIN shop");
            scriptSQL.append(" ON collaborateur.idShop = shop.codeShop");
            scriptSQL.append(" WHERE collaborateur.utilizateur=? AND collaborateur.motDePasse=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setString(1, utilizateur);
            prs.setString(2, motDePasse);
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

                    Collaborateur collaborateur = new CollaborateurBuilder(res.getInt(1))
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

                    GroupeUtilisateur groupeUtilisateur = new GroupeUtilisateurBuilder(res.getInt(8))
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
                            .groupeUtilisateur(groupeUtilisateur)
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

                    GroupeUtilisateur groupeUtilisateur = new GroupeUtilisateurBuilder(res.getInt(8))
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
                            .groupeUtilisateur(groupeUtilisateur)
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
            scriptSQL = new StringBuilder("INSERT INTO colaborateur(");
            scriptSQL.append("cdUsuario, email, senha, nome, fotoPath, idGrupoUsuario)");
            scriptSQL.append(" VALUES (?, ?, ?, ?, ?, ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setInt(1, collaborateur.getCdUsuario());
            prs.setString(2, collaborateur.getEmail());
            prs.setString(3, collaborateur.getSenha());
            prs.setString(4, collaborateur.getNome());
            prs.setString(5, collaborateur.getFotoPath());
            prs.setInt(6, collaborateur.getGrupoUsuario().getCdGrupoUsuario());

            prs.execute();
            prs.close();
            conexao.close();
        }

        return true;
    }

    public boolean atualiza(Usuario usuario) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            scriptSQL = new StringBuilder("UPDATE Usuario");
            scriptSQL.append(" SET email=?, senha=?, nome=?, fotoPath=?, idGrupoUsuario=?");
            scriptSQL.append(" WHERE cdUsuario=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setString(1, usuario.getEmail());
            prs.setString(2, usuario.getSenha());
            prs.setString(3, usuario.getNome());
            prs.setString(4, usuario.getFotoPath());
            prs.setInt(5, usuario.getGrupoUsuario().getCdGrupoUsuario());
            prs.setInt(6, usuario.getCdUsuario());

            prs.execute();
            prs.close();
            conexao.close();
        }

        return true;
    }

    public boolean remove(int cdUsuario) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            scriptSQL = new StringBuilder("DELETE FROM Usuario WHERE cdUsuario=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, cdUsuario);

            prs.execute();
            prs.close();
            conexao.close();
        }

        return true;
    }

    public int selecionaCodigoUsuarioSubsequente() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            scriptSQL = new StringBuilder("SELECT Max(cdUsuario)+1 FROM usuario");
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
