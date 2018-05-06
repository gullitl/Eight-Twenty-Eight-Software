package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoftmiddleend.model.AppConfig;

/**
 * @author Plamedi L. Lusembo
 */
public class AppConfigKS {

    private AppConfig appConfig;
    private static AppConfigKS uniqueInstance;

    public AppConfigKS() {
    }

    public static synchronized AppConfigKS getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new AppConfigKS();
        }
        return uniqueInstance;
    }

    public AppConfig getAppConfig() {
        return appConfig;
    }

    public void setAppConfig(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    public static AppConfigKS getUniqueInstance() {
        return uniqueInstance;
    }

    public static void setUniqueInstance(AppConfigKS uniqueInstance) {
        AppConfigKS.uniqueInstance = uniqueInstance;
    }

}
