package com.cecilsystems.mistersoftbackend.model;

/**
 * @author Plamedi L. Lusembo
 */
public class ConnectionCredentials {

    private String mysqldLink;
    private String jdbcDriver;
    private String urlHead;
    private String serverHost;
    private int port;
    private String schema;
    private String user;
    private String password;

    public ConnectionCredentials() {
    }

    public String getMysqldLink() {
        return mysqldLink;
    }

    public String getJdbcDriver() {
        return jdbcDriver;
    }

    public String getUrlHead() {
        return urlHead;
    }

    public String getServerHost() {
        return serverHost;
    }

    public int getPort() {
        return port;
    }

    public String getSchema() {
        return schema;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getUrl() {
        return getUrlHead() + "//" + getServerHost() + ":" + getPort() + "/";
    }

}
