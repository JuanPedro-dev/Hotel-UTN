package org.example.Entities;

import java.util.Objects;

public class Admin extends User {
    //region Attributes

    //endregion

    //region Constructors
    public Admin() {
    }

    public Admin(String name, String lastName, int dni, String user, String password, String email, long phoneNumber) {
        super(name, lastName, dni, user, password, email, phoneNumber);
    }
    //endregion

    //region Overrides

    @Override
    public String toString() {
        return "Admin{}" + super.toString();
    }

    //endregion

}
