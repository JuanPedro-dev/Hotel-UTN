package org.example.Controller;

import org.example.entity.Admin;
import org.example.entity.Employee;
import org.example.entity.User;

import java.util.Scanner;

public class HotelController {

    private static final Scanner scanner = new Scanner(System.in);
    public static void login()
    {
        String username, password;
        String option = "s";
        User userFound;
        boolean flag = true;

        System.out.println("\n*-*-*-*-*-*-*-***UTN Hotel***-*-*-*-*-*-*");
        System.out.println("*-*-*-*-*-*-*-***LOGIN***-*-*-*-*-*-*");

        while(!option.equals("0")) {
            System.out.println("1. Login ");
            System.out.println("0. Exit");
            System.out.print("Opción: ");
            option = scanner.nextLine();

            switch (option) {
                case "1":
                    while (flag) {

                        System.out.print("User: ");
                        username = scanner.nextLine();
                        System.out.print("Password: ");
                        password = scanner.nextLine();

                        userFound = UserController.isValidUser(username, password);

                        if (userFound != null) {
                            if (userFound instanceof Admin) AdminController.adminMenu(scanner);
                            if (userFound instanceof Employee) EmployeeController.employeeMenu(scanner);
                            login();
                        } else {
                            flag = messageError();
                            if (!flag) System.out.println("No se ha encontrado su usuario... \n");
                        }

                    }
                case "0":
                    System.out.println("*-*-***Gracias por utilizar UTN Hotel***-*-*");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción incorrecta.");
                    login();
            }
        }
    }

    static boolean messageError () {
        String answ;

        System.out.print("Ingreso incorrectamente. Desea volver a intentarlo? S/N: ");

        answ = scanner.nextLine().toUpperCase().trim();

        return answ.equals("S");
    }



}
