package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoftmiddleend.model.Fournisseur;
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

    public List<Fournisseur> listerTousLesFournisseurs() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<Fournisseur> listeFournisseurs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT code, responsable, entreprise, telephone");
            scriptSQL.append(" FROM fournisseur");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();

            listeFournisseurs = new ArrayList();
            if (res != null) {
                while (res.next()) {

                    Fournisseur fournisseur = new Fournisseur(res.getInt(1));
                    fournisseur.setResponsable(res.getString(2));
                    fournisseur.setEntreprise(res.getString(3));
                    fournisseur.setTelephone(res.getString(4));

                    listeFournisseurs.add(fournisseur);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listeFournisseurs;
    }

    public Fournisseur selectionnerFournisseurParCode(int codeFournisseur) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT code, responsable, entreprise, telephone");
            scriptSQL.append(" FROM fournisseur WHERE code=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codeFournisseur);
            res = prs.executeQuery();

            if (res != null) {
                if (res.next()) {

                    Fournisseur fournisseur = new Fournisseur(res.getInt(1));
                    fournisseur.setResponsable(res.getString(2));
                    fournisseur.setEntreprise(res.getString(3));
                    fournisseur.setTelephone(res.getString(4));

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

    public boolean enregistrerFournisseur(Fournisseur fournisseur) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            if (fournisseur.getCode() == 0) {

                scriptSQL = new StringBuilder("INSERT INTO fournisseur(");
                scriptSQL.append(" responsable, entreprise, telephone, code )");
                scriptSQL.append(" VALUES (?, ?, ?, ?)");
            } else {
                scriptSQL = new StringBuilder("UPDATE fournisseur");
                scriptSQL.append(" SET responsable=?, entreprise=?, telephone=?");
                scriptSQL.append(" WHERE code=?");
            }
            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setString(1, fournisseur.getResponsable());
            prs.setString(2, fournisseur.getEntreprise());
            prs.setString(3, fournisseur.getTelephone());
            prs.setInt(4, fournisseur.getCode());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

    public boolean exclureFournisseur(int codeFournisseur) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("DELETE FROM fournisseur WHERE code=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codeFournisseur);

            prs.execute();
            prs.close();
            conexao.close();
        }

        return true;
    }

}
