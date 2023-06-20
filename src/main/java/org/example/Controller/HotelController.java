package org.example.Controller;

import org.example.entity.Admin;
import org.example.entity.Employee;
import org.example.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HotelController {

    private static final Scanner scanner = new Scanner(System.in);
    public static void login()
    {
        String username, password;
        int option;
        User userFound;
        boolean flag = true;
        System.out.println("*-*-*-*-*-*-*-***UTN Hotel***-*-*-*-*-*-*");
        System.out.println("*-*-*-*-*-*-*-***LOGIN***-*-*-*-*-*-*");
        System.out.println("1. Login ");
        System.out.println("0. Exit");
        option = scanner.nextInt();
        switch (option) {
            case 1:
                do {
                    scanner.nextLine();             //cleaned buffer
                    System.out.print("User: ");
                    username = scanner.nextLine();
                    System.out.print("Password: ");
                    password = scanner.nextLine();

                    userFound = UserController.isValidUser(username, password);
                    if (userFound != null) {
                        if(userFound instanceof Admin) AdminController.adminMenu(scanner);
                        if(userFound instanceof Employee) EmployeeController.employeeMenu(scanner);
                    }else {
                        flag = messageError();
                        if (!flag) {
                            System.out.println("No se ha encontrado su usuario... \n");
                            System.out.println("El programa se cerrara \n");
                            scanner.nextLine();             //cleaned buffer
                            System.out.println("Enter para continuar..");
                        }
                    }
                } while (flag);
                break;
            case 0:
                System.out.println("*-*-***Gracias por utilizar UTN Hotel***-*-*");
                System.exit(0);
                break;
        }
    }

    static boolean messageError () {
        String answ;
        System.out.println("Enter para continuar..");
        scanner.nextLine();             //cleaned buffer
        System.out.print("Ingreso incorrectamente. Desea volver a intentarlo? S/N");
        answ = scanner.nextLine().toUpperCase();
        if(answ.equals("S")) return true;
        return false;
    }

    static boolean isInteger (String str){
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
