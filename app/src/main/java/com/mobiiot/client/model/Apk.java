package com.mobiiot.client.model;

public class Apk {
    String packageApk;
    String nameApk;
    String urlApk;
    String versionApk;

    public Apk(String packageApk, String nameApk, String urlApk, String versionApk) {
        this.packageApk = packageApk;
        this.nameApk = nameApk;
        this.urlApk = urlApk;
        this.versionApk = versionApk;
    }

    public String getPackageApk() {
        return packageApk;
    }

    public void setPackageApk(String packageApk) {
        this.packageApk = packageApk;
    }

    public String getNameApk() {
        return nameApk;
    }

    public void setNameApk(String nameApk) {
        this.nameApk = nameApk;
    }

    public String getUrlApk() {
        return urlApk;
    }

    public void setUrlApk(String urlApk) {
        this.urlApk = urlApk;
    }

    public String getVersionApk() {
        return versionApk;
    }

    public void setVersionApk(String versionApk) {
        this.versionApk = versionApk;
    }

    @Override
    public String toString() {
        return "Apk{" +
                "packageApk='" + packageApk + '\'' +
                ", nameApk='" + nameApk + '\'' +
                ", urlApk='" + urlApk + '\'' +
                ", versionApk='" + versionApk + '\'' +
                '}';
    }
}
