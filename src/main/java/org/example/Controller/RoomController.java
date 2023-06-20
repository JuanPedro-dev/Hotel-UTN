package org.example.Controller;

import java.util.Scanner;

public class RoomController {
    //region [Menu]

    /**
     * Displays the room menu options.
     */
    public static void viewRoomMenu(){
        System.out.println("*-*-*-*-*-*-*-***Bates Motel****-*-*-*-*-*-*");
        System.out.println("*-*-*-*-*-*-*-***Menu Reserva****-*-*-*-*-*");
        System.out.println("1. Ver habitaciones disponibles");
        System.out.println("2. Ver habitaciones ocupadas");
        System.out.println("3. Ver todas las habitaciones");
        System.out.println("4. Eliminar Habitacion");
        System.out.println("5. Modificar Habitacion");
        System.out.println("0. Salir");
    }

    public static void roomMenu(Scanner scanner){
        boolean flag = true;
        do {
            viewRoomMenu();
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
                    case "5":
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
