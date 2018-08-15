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

        try (Connection connection = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT id, responsable, entreprise, telephone");
            scriptSQL.append(" FROM fournisseur");

            prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();

            listeFournisseurs = new ArrayList();
            if (res != null) {
                while (res.next()) {

                    Fournisseur fournisseur = new Fournisseur(res.getString(1));
                    fournisseur.setResponsable(res.getString(2));
                    fournisseur.setEntreprise(res.getString(3));
                    fournisseur.setTelephone(res.getString(4));

                    listeFournisseurs.add(fournisseur);
                }
            }
            prs.close();
            res.close();
            connection.close();
        }
        return listeFournisseurs;
    }

    public Fournisseur selectionnerFournisseurParId(String idFournisseur) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection connection = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT id, responsable, entreprise, telephone");
            scriptSQL.append(" FROM fournisseur WHERE id=?");

            prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));
            prs.setString(1, idFournisseur);
            res = prs.executeQuery();

            if (res != null) {
                if (res.next()) {

                    Fournisseur fournisseur = new Fournisseur(res.getString(1));
                    fournisseur.setResponsable(res.getString(2));
                    fournisseur.setEntreprise(res.getString(3));
                    fournisseur.setTelephone(res.getString(4));

                    prs.close();
                    res.close();
                    connection.close();

                    return fournisseur;
                }
            }
            prs.close();
            res.close();
            connection.close();
        }
        return null;
    }

    public boolean enregistrerFournisseur(Fournisseur fournisseur) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection connection = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("INSERT INTO fournisseur(");
            scriptSQL.append(" responsable, entreprise, telephone, id )");
            scriptSQL.append(" VALUES (?, ?, ?, ?)");

            prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));

            prs.setString(1, fournisseur.getResponsable());
            prs.setString(2, fournisseur.getEntreprise());
            prs.setString(3, fournisseur.getTelephone());
            prs.setString(4, fournisseur.getId());

            prs.execute();
            prs.close();
            connection.close();
        }
        return true;
    }

    public boolean actualiserFournisseur(Fournisseur fournisseur) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection connection = ConnectionFactory.getInstance().habiliterConnection()) {

            scriptSQL = new StringBuilder("UPDATE fournisseur");
            scriptSQL.append(" SET responsable=?, entreprise=?, telephone=?");
            scriptSQL.append(" WHERE id=?");
            prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));

            prs.setString(1, fournisseur.getResponsable());
            prs.setString(2, fournisseur.getEntreprise());
            prs.setString(3, fournisseur.getTelephone());
            prs.setString(4, fournisseur.getId());

            prs.execute();
            prs.close();
            connection.close();
        }
        return true;
    }

    public boolean exclureFournisseur(String idFournisseur) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection connection = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("DELETE FROM fournisseur WHERE id=?");

            prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));
            prs.setString(1, idFournisseur);

            prs.execute();
            prs.close();
            connection.close();
        }

        return true;
    }

}
