package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoftmiddleend.ks.AppConfigKS;
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
            Class.forName(AppConfigKS.getInstance().getAppConfig().getJdbcDriver());
            Connection connection = ((Connection) DriverManager
                    .getConnection(AppConfigKS.getInstance().getAppConfig().getUrlHead(),
                            AppConfigKS.getInstance().getAppConfig().getUser(),
                            AppConfigKS.getInstance().getAppConfig().getPassword()));

            Statement statement = connection.createStatement();

            int result = statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + AppConfigKS
                    .getInstance().getAppConfig().getSchema());

            return result == 0 || result == 1;

        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }

    public Connection habiliterConnection() throws ClassNotFoundException, SQLException {
        Class.forName(AppConfigKS.getInstance().getAppConfig().getJdbcDriver());

        return ((Connection) DriverManager
                .getConnection(AppConfigKS.getInstance().getAppConfig().getUrlHead()
                        + AppConfigKS.getInstance().getAppConfig().getSchema(),
                        AppConfigKS.getInstance().getAppConfig().getUser(),
                        AppConfigKS.getInstance().getAppConfig().getPassword()));
    }

}
