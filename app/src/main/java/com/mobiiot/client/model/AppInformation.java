package com.mobiiot.client.model;

public class AppInformation {
    String packageNAme;
    String appName;

    public AppInformation(String packageNAme, String appName) {
        this.packageNAme = packageNAme;
        this.appName = appName;
    }

    public AppInformation() {
    }

    public String getPackageNAme() {
        return packageNAme;
    }

    public void setPackageNAme(String packageNAme) {
        this.packageNAme = packageNAme;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    @Override
    public String toString() {
        return "AppInformation{" +
                "packageNAme='" + packageNAme + '\'' +
                ", appName='" + appName + '\'' +
                '}';
    }
}
