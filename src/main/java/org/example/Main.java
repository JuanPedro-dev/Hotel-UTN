package org.example;


import org.example.Controller.HotelController;

public class Main {

    // En caso de que no existan las habitaciones, admin, employee, etc ejecutar el InitData (carga datos a los archivos)
    public static void main(String[] args) {
        HotelController.login();

    }

}