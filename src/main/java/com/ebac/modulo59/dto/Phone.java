package com.ebac.modulo59.dto;

public class Phone {
    private int idPhone;
    private int idUser;
    private String phone;
    private String type;

    public int getIdPhone() {
        return idPhone;
    }

    public void setIdPhone(int idPhone) {
        this.idPhone = idPhone;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Telefono{" + "idPhone=" + idPhone + ", idUser=" + idUser + ", phone=" + phone + ", type=" + type + '}';
    }
}
