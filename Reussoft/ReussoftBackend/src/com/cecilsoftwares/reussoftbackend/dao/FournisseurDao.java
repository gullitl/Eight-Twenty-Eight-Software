package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoftmiddleend.model.Fournisseur;
import com.cecilsoftwares.reussoftmiddleend.model.Fournisseur.FournisseurBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class FournisseurDao {

    private StringBuilder scriptSQL;
    private static FournisseurDao uniqueInstance;

    public FournisseurDao() {
    }

    public static synchronized FournisseurDao getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new FournisseurDao();
        }
        return uniqueInstance;
    }

    //valide = true
    public List<Fournisseur> listerTousLesFournisseurs() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<Fournisseur> listeFournisseurs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT code, responsable, entreprise, telephone, observation");
            scriptSQL.append(" FROM fournisseur");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();

            listeFournisseurs = new ArrayList();
            if (res != null) {
                while (res.next()) {

                    Fournisseur fournisseur = new FournisseurBuilder(res.getInt(1))
                            .responsable(res.getString(2))
                            .entreprise(res.getString(3))
                            .telephone(res.getString(4))
                            .observation(res.getString(5))
                            .build();

                    listeFournisseurs.add(fournisseur);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listeFournisseurs;
    }

    //valide = true
    public Fournisseur selectionnerFournisseurParCode(int codeFournisseur) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT code, responsable, entreprise, telephone, observation");
            scriptSQL.append(" FROM fournisseur WHERE code=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codeFournisseur);
            res = prs.executeQuery();

            if (res != null) {
                if (res.next()) {

                    Fournisseur fournisseur = new FournisseurBuilder(res.getInt(1))
                            .responsable(res.getString(2))
                            .entreprise(res.getString(3))
                            .telephone(res.getString(4))
                            .observation(res.getString(5))
                            .build();

                    prs.close();
                    res.close();
                    conexao.close();

                    return fournisseur;
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return null;
    }

    //Valide = true
    public boolean enregistrerFournisseur(Fournisseur fournisseur) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("INSERT INTO fournisseur(");
            scriptSQL.append(" code, responsable, entreprise, telephone, observation)");
            scriptSQL.append(" VALUES (?, ?, ?, ?, ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setInt(1, fournisseur.getCode());
            prs.setString(2, fournisseur.getResponsable());
            prs.setString(3, fournisseur.getEntreprise());
            prs.setString(4, fournisseur.getTelephone());
            prs.setString(5, fournisseur.getObservation());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

    //valide = true
    public boolean actualiserFournisseur(Fournisseur fournisseur) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("UPDATE fournisseur");
            scriptSQL.append(" SET responsable=?, entreprise=?, telephone=?, observation=?");
            scriptSQL.append(" WHERE codeFournisseur=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setString(1, fournisseur.getResponsable());
            prs.setString(2, fournisseur.getEntreprise());
            prs.setString(3, fournisseur.getTelephone());
            prs.setString(4, fournisseur.getObservation());
            prs.setInt(5, fournisseur.getCode());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

    //valide = true
    public int selectionnerCodeFournisseurSubsequent() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT Max(code)+1 FROM fournisseur");
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
