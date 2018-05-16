package com.cecilsoftwares.reussoftmiddleend.model;

/**
 * @author Plamedi L. Lusembo
 */
public class AppConfig {

    private final String jdbcDriver;
    private final String urlHead;
    private final String serverHost;
    private final int port;
    private final String schema;
    private final String user;
    private final String password;

    public AppConfig(AppConfigBuilder appConfigBuilder) {
        jdbcDriver = appConfigBuilder.jdbcDriver;
        urlHead = appConfigBuilder.urlHead;
        serverHost = appConfigBuilder.serverHost;
        port = appConfigBuilder.port;
        schema = appConfigBuilder.schema;
        user = appConfigBuilder.user;
        password = appConfigBuilder.password;
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

    public static class AppConfigBuilder {

        private String jdbcDriver;
        private String urlHead;
        private String serverHost;
        private int port;
        private String schema;
        private String user;
        private String password;

        public AppConfigBuilder jdbcDriver(String jdbcDriver) {
            this.jdbcDriver = jdbcDriver;
            return this;
        }

        public AppConfigBuilder urlHead(String urlHead) {
            this.urlHead = urlHead;
            return this;
        }

        public AppConfigBuilder serverHost(String serverHost) {
            this.serverHost = serverHost;
            return this;
        }

        public AppConfigBuilder port(int port) {
            this.port = port;
            return this;
        }

        public AppConfigBuilder schema(String schema) {
            this.schema = schema;
            return this;
        }

        public AppConfigBuilder user(String user) {
            this.user = user;
            return this;
        }

        public AppConfigBuilder password(String password) {
            this.password = password;
            return this;
        }

        public AppConfig build() {
            return new AppConfig(this);
        }

    }

}
