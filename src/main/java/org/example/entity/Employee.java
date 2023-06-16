package org.example.entity;

public class Employee extends User{
    public Employee() {
    }

    public Employee(String name, String lastName, Integer dni, String user, String password, String email, long phoneNumber) {
        super(name, lastName, dni, user, password, email, phoneNumber);
    }

    @Override
    public String toString() {
        return "Employee{}" + super.toString();
    }

}
