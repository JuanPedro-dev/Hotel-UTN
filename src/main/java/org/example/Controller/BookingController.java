package org.example.Controller;

import java.util.Scanner;

public class BookingController {

    //region [Menu]

    /**
     * Displays the booking menu options.
     */
    public static void viewBookingMenu(){
        System.out.println("\n*-*-*-*-*-*-*-**** UTN Motel ****-*-*-*-*-*-*");
        System.out.println("*-*-*-*-*-*-*-**** Menu Reserva ****-*-*-*-*-*");
        System.out.println("1. Check in");
        System.out.println("2. Check out");
        System.out.println("3. Ver todas las reservas");
        System.out.println("4. Servicio al cuarto");
        System.out.println("0. Salir");
        System.out.print("Opción: ");
    }

    public static void bookingMenu(Scanner scanner){

        String option = "";

        while(!option.equals("0")) {

            viewBookingMenu();
            option = scanner.nextLine();

            switch (option) {
                case "1" -> System.out.println("1. Check in");
                case "2" -> System.out.println("2. Check out");
                case "3" -> System.out.println("3. Ver todas las reservas");
                case "4" -> System.out.println("4. Servicio al cuarto");
                case "0" -> option = "0";
                default -> System.out.println("Opción incorrecta.");
            }
        }
    }
    //endregion

}
