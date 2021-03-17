package com.mobiiot.client.model;

public class Key {
    String nameKey;
    String urlKey;

    public Key(String nameKey, String urlKey) {
        this.nameKey = nameKey;
        this.urlKey = urlKey;
    }

    @Override
    public String toString() {
        return "Key{" +
                "nameKey='" + nameKey + '\'' +
                ", urlKey='" + urlKey + '\'' +
                '}';
    }

    public String getNameKey() {
        return nameKey;
    }

    public void setNameKey(String nameKey) {
        this.nameKey = nameKey;
    }

    public String getUrlKey() {
        return urlKey;
    }

    public void setUrlKey(String urlKey) {
        this.urlKey = urlKey;
    }
}
