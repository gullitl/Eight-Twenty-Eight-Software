package com.cecilsystems.mistersoftbackend.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Plamedi L. Lusembo
 */
public class ConnectionFactory {

    private static ConnectionFactory uniqueInstance;

    public ConnectionFactory() {

    }

    public static synchronized ConnectionFactory getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ConnectionFactory();
        }
        return uniqueInstance;
    }

    public boolean criaEsquemaCasoNaoExiste() {
        try {
            Class.forName(ConnectionCredentialsKS.getInstance().getConnectionCredentials().getJdbcDriver());
            Connection connection = ((Connection) DriverManager
                    .getConnection(ConnectionCredentialsKS.getInstance().getConnectionCredentials().getUrl(),
                            ConnectionCredentialsKS.getInstance().getConnectionCredentials().getUser(),
                            ConnectionCredentialsKS.getInstance().getConnectionCredentials().getPassword()));

            Statement statement = connection.createStatement();

            int result = statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + ConnectionCredentialsKS
                    .getInstance().getConnectionCredentials().getSchema());

            return result == 0 || result == 1;

        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }

    public Connection abreNovaConexao() throws ClassNotFoundException, SQLException {
        Class.forName(ConnectionCredentialsKS.getInstance().getConnectionCredentials().getJdbcDriver());

        return ((Connection) DriverManager
                .getConnection(ConnectionCredentialsKS.getInstance().getConnectionCredentials().getUrl()
                        + ConnectionCredentialsKS.getInstance().getConnectionCredentials().getSchema(),
                        ConnectionCredentialsKS.getInstance().getConnectionCredentials().getUser(),
                        ConnectionCredentialsKS.getInstance().getConnectionCredentials().getPassword()));
    }

}
