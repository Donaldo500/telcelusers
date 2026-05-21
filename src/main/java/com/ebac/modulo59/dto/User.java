package com.ebac.modulo59.dto;

public class User {
    private int idUser;
    private String name;
    private String lastName;
    private int age;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Usuario{" + "idUser=" + idUser + ", name=" + name + ", lastName=" + lastName + ", age=" + age + '}';
    }
}
