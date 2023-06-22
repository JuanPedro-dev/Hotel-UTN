package org.example.entity;

public class Guest extends User {

    public Guest() {
    }

    public Guest(String name, String lastName, Integer dni, String email, Long phoneNumber) {
        super(name, lastName, dni, email, phoneNumber);
    }

    @Override
    public String toString() {
        return
                "\n***** Huesped ***** \n"+
                "Datos:" +
                    "\n\tNombre= " + super.getName() +
                    "\n\tApellido= " + super.getLastName() +
                    "\n\tDNI= " + super.getDni() +
                    "\n\temail= " + super.getEmail() +
                    "\n\tTeléfono= " + super.getPhoneNumber();
    }

}
