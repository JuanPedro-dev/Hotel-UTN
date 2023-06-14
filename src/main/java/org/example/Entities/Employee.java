package org.example.Entities;

public class Employee extends User{
    //region Constructors
    public Employee() {
    }

    public Employee(String name, String lastName, int dni, String user, String password, String email, long phoneNumber) {
        super(name, lastName, dni, user, password, email, phoneNumber);
    }
    //endregion

    //region Overrides

    @Override
    public String toString() {
        return "Employee{}" + super.toString();
    }

    //endregion
}
