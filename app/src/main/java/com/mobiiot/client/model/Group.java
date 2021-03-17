package com.mobiiot.client.model;

public class Group {

    String idGroupe;
    String nameGroupe;

    public Group(String idGroupe, String nameGroupe) {
        this.idGroupe = idGroupe;
        this.nameGroupe = nameGroupe;
    }

    public String getIdGroupe() {
        return idGroupe;
    }

    public void setIdGroupe(String idGroupe) {
        this.idGroupe = idGroupe;
    }

    public String getNameGroupe() {
        return nameGroupe;
    }

    public void setNameGroupe(String nameGroupe) {
        this.nameGroupe = nameGroupe;
    }

    @Override
    public String toString() {
        return "Group{" +
                "idGroupe='" + idGroupe + '\'' +
                ", nameGroupe='" + nameGroupe + '\'' +
                '}';
    }
}
