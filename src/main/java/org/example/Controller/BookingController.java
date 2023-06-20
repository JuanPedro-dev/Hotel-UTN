package org.example.Controller;

import java.util.Scanner;

public class BookingController {

    //region [Menu]

    /**
     * Displays the booking menu options.
     */
    public static void viewBookingMenu(){
        System.out.println("*-*-*-*-*-*-*-***Bates Motel****-*-*-*-*-*-*");
        System.out.println("*-*-*-*-*-*-*-***Menu Reserva****-*-*-*-*-*");
        System.out.println("1. Check in");
        System.out.println("2. Check out");
        System.out.println("3. Ver todas las reservas");
        System.out.println("3. Servicio al cuarto");
        System.out.println("0. Salir");
    }

    public static void bookingMenu(Scanner scanner){
        boolean flag = true;
        do {
            viewBookingMenu();
            String option = scanner.nextLine();
            if (HotelController.isInteger(option)) {
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
                        EmployeeController.employeeMenu(scanner);
                        break;
                    default:
                        System.out.println("Ingreso incorrectamente.");
                }
            } else flag = HotelController.messageError();
        }while(flag);
    }
    //endregion

}
