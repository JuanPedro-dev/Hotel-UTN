package org.example.Entities;

import org.example.Entities.User;

public class Guest extends User {

    //region Constructors
    public Guest() {
    }

    public Guest(String name, String lastName, int dni, String user, String password, String email, long phoneNumber) {
        super(name, lastName, dni, user, password, email, phoneNumber);
    }
    //endregion

    //region Overrides

    @Override
    public String toString() {
        return "Guest{}" + super.toString();
    }

    //endregion
}
