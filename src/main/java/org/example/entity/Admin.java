package org.example.entity;

public class Admin extends User {

    public Admin() {
    }

    public Admin(String name, String lastName, Integer dni, String user, String password, String email, long phoneNumber) {
        super(name, lastName, dni, user, password, email, phoneNumber);
    }

    @Override
    public String toString() {
        return
                "\n***** Administrador ***** \n"+
                        "Datos:" +
                        super.toString();
    }

}
