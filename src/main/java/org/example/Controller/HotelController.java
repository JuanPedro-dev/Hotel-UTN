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

                    userFound = isValidUser(username, password);
                    if (userFound != null) {
                        if(userFound instanceof Admin) System.out.println("Menu Admin"); //ToDo mandar menu de admin ;
                        if(userFound instanceof Employee) System.out.println("Menu Employee");//ToDo mandar menu de employee ;
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

    public static ArrayList<User> listOfAllUsers(){
        ArrayList<User> users = new ArrayList<>();

        AdminController adminController = new AdminController();
        /*EmployeeRepository employeeController = new EmployeeRepository();
        GuestRepository guestController = new GuestRepository();*/

        //ToDo pasar listas desde el controller
        users.addAll(adminController.getAllAdmins());
//        users.addAll(employeeController.getAllAdmins());
//        users.addAll(guestController.getAllAdmins());
        return users;
    }

    public static User isValidUser(String username, String pass) {
        User userFound = null;
        List<User> users = listOfAllUsers();
        if (!users.isEmpty()) {
            for (User u : users) {
                if (u.getUser().equals(username)) {
                    if (u.getPassword().equals(pass)) {
                        userFound = u;
                    }
                }
            }
        }
        return userFound;
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

}
