package com.cecilsoftwares.reussoftbackend.model;

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

    public ConnectionCredentials(ConnectionCredentialsBuilder connectionCredentialsBuilder) {
        mysqldLink = connectionCredentialsBuilder.mysqldLink;
        jdbcDriver = connectionCredentialsBuilder.mysqldLink;
        urlHead = connectionCredentialsBuilder.urlHead;
        serverHost = connectionCredentialsBuilder.serverHost;
        port = connectionCredentialsBuilder.port;
        schema = connectionCredentialsBuilder.schema;
        user = connectionCredentialsBuilder.user;
        password = connectionCredentialsBuilder.password;
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

    public static class ConnectionCredentialsBuilder {

        private String mysqldLink;
        private String jdbcDriver;
        private String urlHead;
        private String serverHost;
        private int port;
        private String schema;
        private String user;
        private String password;

        public ConnectionCredentialsBuilder mysqldLink(String mysqldLink) {
            this.mysqldLink = mysqldLink;
            return this;
        }

        public ConnectionCredentialsBuilder jdbcDriver(String jdbcDriver) {
            this.jdbcDriver = jdbcDriver;
            return this;
        }

        public ConnectionCredentialsBuilder urlHead(String urlHead) {
            this.urlHead = urlHead;
            return this;
        }

        public ConnectionCredentialsBuilder serverHost(String serverHost) {
            this.serverHost = serverHost;
            return this;
        }

        public ConnectionCredentialsBuilder port(int port) {
            this.port = port;
            return this;
        }

        public ConnectionCredentialsBuilder schema(String schema) {
            this.schema = schema;
            return this;
        }

        public ConnectionCredentialsBuilder user(String user) {
            this.user = user;
            return this;
        }

        public ConnectionCredentialsBuilder password(String password) {
            this.password = password;
            return this;
        }

    }

}
