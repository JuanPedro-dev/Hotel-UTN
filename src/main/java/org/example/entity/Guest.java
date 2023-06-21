package org.example.entity;

public class Guest extends User {

    //region [Constructors]
    public Guest() {
    }

    public Guest(String name, String lastName, Integer dni, String email, Long phoneNumber) {
        super(name, lastName, dni, email, phoneNumber);
    }
    //endregion

    //region [Overrides]

    @Override
    public String toString() {
        return
                "\n***** Huesped ***** \n"+
                "Datos:" +
                        super.toString();
    }

    //endregion
}
