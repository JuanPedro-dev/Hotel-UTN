package org.example.entity;

public class Guest extends User {

    //region [Constructors]
    public Guest() {
    }

    public Guest(String name, String lastName, int dni, String email, long phoneNumber) {
        super(name, lastName, dni, email, phoneNumber);
    }
    //endregion

    //region [Overrides]

    @Override
    public String toString() {
        return "Guest" + super.toString();
    }

    //endregion
}
