package org.example.entity;

import java.util.Objects;

public abstract class  User {

    //region [Attributes]
    private String name;
    private String lastName;
    private Integer dni;
    private String user;
    private String password;
    private String email;
    private Long phoneNumber;
    //endregion

    //region [Constructors]

    public User() {
    }

    public User(String name, String lastName, Integer dni, String user, String password, String email, Long phoneNumber) {
        this.name = name;
        this.lastName = lastName;
        this.dni = dni;
        this.user = user;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public User(String name, String lastName, Integer dni, String email, Long phoneNumber) {
        this.name = name;
        this.lastName = lastName;
        this.dni = dni;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
    //endregion

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    //region [Overrides]

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return dni == user.dni;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

    @Override
    public String toString() {
        return
                "\n\tNombre= " + name +
                "\n\tApellido= " + lastName +
                "\n\tDNI= " + dni +
                "\n\tUser= " + user +
                "\n\tpassword= " + password +
                "\n\temail= " + email +
                "\n\tTeléfono= " + phoneNumber;

    }
//endregion
}
