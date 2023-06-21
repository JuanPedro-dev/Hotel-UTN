package org.example.Controller;

import java.util.Scanner;

public class BookingController {

    //region [Menu]

    /**
     * Displays the booking menu options.
     */
    public static void viewBookingMenu(){
        System.out.println("*-*-*-*-*-*-*-***UTN Motel****-*-*-*-*-*-*");
        System.out.println("*-*-*-*-*-*-*-***Menu Reserva****-*-*-*-*-*");
        System.out.println("1. Check in");
        System.out.println("2. Check out");
        System.out.println("3. Ver todas las reservas");
        System.out.println("3. Servicio al cuarto");
        System.out.println("0. Salir");
    }

    public static void bookingMenu(Scanner scanner){
        String option = "";

        while(!option.equals("0")) {
            viewBookingMenu();
            option = scanner.nextLine();

            switch (option) {
                case "1":
                    // ToDo falta implementacion
                    break;
                case "2":
                    // ToDo falta implementacion
                    break;
                case "3":
                    // ToDo falta implementacion
                    break;
                case "4":
                    // ToDo falta implementacion
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Ingreso incorrectamente.");
            }
        }
    }
    //endregion

}
