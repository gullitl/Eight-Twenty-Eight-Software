package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoftmiddleend.model.Client;
import com.cecilsoftwares.reussoftmiddleend.model.Client.ClientBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class ClientDao {

    private StringBuilder scriptSQL;
    private static ClientDao uniqueInstance;

    public ClientDao() {
    }

    public static synchronized ClientDao getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ClientDao();
        }
        return uniqueInstance;
    }

    public List<Client> lister() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<Client> listeClients;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            scriptSQL = new StringBuilder("SELECT code, responsable, entreprise");
            scriptSQL.append(" FROM client");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();

            listeClients = new ArrayList();
            if (res != null) {
                while (res.next()) {

                    Client client = new ClientBuilder(res.getInt(1))
                            .responsable(res.getString(2))
                            .entreprise(res.getString(3))
                            .build();

                    listeClients.add(client);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listeClients;
    }

    public Client selectionner(int code) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            scriptSQL = new StringBuilder("SELECT code, responsable, entreprise");
            scriptSQL.append(" FROM client");
            scriptSQL.append(" WHERE code=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, code);
            res = prs.executeQuery();
            if (res != null) {
                if (res.next()) {

                    Client client = new ClientBuilder(res.getInt(1))
                            .responsable(res.getString(2))
                            .entreprise(res.getString(3))
                            .build();

                    prs.close();
                    res.close();
                    conexao.close();

                    return client;
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return null;
    }

    public boolean sauvegarder(Client client) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            scriptSQL = new StringBuilder("INSERT INTO client(");
            scriptSQL.append(" code, responsable, entreprise )");
            scriptSQL.append(" VALUES (?, ?, ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setInt(1, client.getCode());
            prs.setString(2, client.getResponsable());
            prs.setString(3, client.getEntreprise());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

    public boolean actualiser(Client client) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            scriptSQL = new StringBuilder("UPDATE client");
            scriptSQL.append(" SET responsable=?, entreprise=?");
            scriptSQL.append(" WHERE codeClient=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setString(1, client.getResponsable());
            prs.setString(2, client.getEntreprise());
            prs.setInt(3, client.getCode());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

    public boolean exclure(int code) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            scriptSQL = new StringBuilder("DELETE FROM client WHERE code=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, code);

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

    public int selectionnerCodeClientSubsequent() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            scriptSQL = new StringBuilder("SELECT Max(code)+1 FROM client");
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
