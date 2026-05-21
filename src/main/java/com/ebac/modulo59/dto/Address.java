package com.ebac.modulo59.dto;

public class Address {
    private int idAddress;
    private int idUser;
    private String street;
    private int number;
    private String state;

    public int getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(int idAddress) {
        this.idAddress = idAddress;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Direccion{" +
                "|idAddress=" + idAddress +
                ", idUser=" + idUser +
                ", street='" + street + '\'' +
                ", number=" + number +
                ", state='" + state + '\'' +
                '}';
    }
}
