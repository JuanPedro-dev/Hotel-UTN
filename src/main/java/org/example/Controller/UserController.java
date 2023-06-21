package org.example.Controller;

import org.example.entity.Admin;
import org.example.entity.Employee;
import org.example.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserController {

    //region [CRUD]

    /**
     * This method allows the user to add a new user to the system.
     * The user can be either an Employee or an Admin, depending on the type selected.
     */
    public static void addUser(){
        Scanner scanner = new Scanner(System.in);

        Employee employee;
        Admin admin;
        int type = 0;

        String controller = "S";
        String name, lastName, email, user, password;
        Integer dni;
        long phone;

        while (controller.equals("S")) {

            while(!(type == 1 || type == 2)){
                try{
                    System.out.println("\n*-*-*-*-*-*-*-**** Crear Usuario ****-*-*-*-*-*-*");
                    System.out.println("INGRESE LOS DATOS");
                    System.out.println("Seleccione el puesto");
                    System.out.println("1. Empleado general.");
                    System.out.println("2. Administrador.");
                    System.out.print("Opción: ");

                    type = Integer.parseInt(scanner.nextLine());
                    if(!(type == 1 || type == 2)) System.out.println("Opción incorrecta.");
                } catch (NumberFormatException e){
                    System.out.println("Ingrese un número valido. Error: " + e.getMessage());
                }
            }

            System.out.print("Nombre: ");
            name = scanner.nextLine();

            System.out.print("Apellido: ");
            lastName = scanner.nextLine();

            System.out.print("Email: ");
            email = scanner.nextLine();

            while(true){
                try{
                    System.out.print("DNI: ");
                    dni = Integer.parseInt(scanner.nextLine());

                    System.out.print("Teléfono: ");
                    phone = Long.parseLong(scanner.nextLine());
                    break;
                } catch (NumberFormatException e){
                    System.out.println("Ingrese un número valido. Error: " + e.getMessage());
                }
            }

            System.out.print("Usuario: ");
            user = scanner.nextLine();

            System.out.print("Password : ");
            password = scanner.nextLine();


            if(type==1) {
                employee = new Employee(name, lastName, dni, user , password, email, phone);
                EmployeeController employeeController = new EmployeeController();
                employeeController.add(employee);
            }
            if(type==2) {
                admin = new Admin(name, lastName, dni,user,password, email, phone);
                AdminController adminController = new AdminController();
                adminController.add(admin);
            }

            System.out.print("Desea cargar otro usuario? S/N: ");
            controller = scanner.nextLine().toUpperCase();
        }
    }


    /**
     * Searches for a user based on user type and user ID.
     */
    public static void updateUser(){
        Scanner scanner = new Scanner(System.in);

        int dni;
        int userType = 0;

        while(!(userType == 1 || userType == 2)){
            try{
                System.out.println("\n*-*-*-*-*-*-*-**** Modificar Usuario ****-*-*-*-*-*-*");
                System.out.println("Seleccione el puesto");
                System.out.println("1. Empleado general.");
                System.out.println("2. Administrador.");
                System.out.print("Opción: ");

                userType = Integer.parseInt(scanner.nextLine());
                if(!(userType == 1 || userType == 2)) System.out.println("Opción incorrecta.");
            } catch (NumberFormatException e){
                System.out.println("Ingrese un número valido. Error: " + e.getMessage());
            }
        }

        switch ( userType ) {
            case 1:
                EmployeeController employeeController = new EmployeeController();

                while(true){
                    try{
                        System.out.print("DNI del Empleado: ");
                        dni = Integer.parseInt(scanner.nextLine());
                        break;
                    } catch (NumberFormatException e){
                        System.out.println("Ingrese un número valido. Error: " + e.getMessage());
                    }
                }

                Employee updateEmployee = employeeController.getById(dni);

                if (updateEmployee != null)
                    employeeController.update(updateEmployee);

                break;
            case 2:
                AdminController adminController = new AdminController();

                while(true){
                    try{
                        System.out.print("DNI del Administrador: ");
                        dni = Integer.parseInt(scanner.nextLine());
                        break;
                    } catch (NumberFormatException e){
                        System.out.println("Ingrese un número valido. Error: " + e.getMessage());
                    }
                }
                Admin updateAdmin = adminController.getById(dni);
                if (updateAdmin != null)
                    adminController.update(updateAdmin);
                break;
            default:
                System.out.println("Opción incorrecta.");
        }
    }

    /**
     * Deletes a user based on user input.
     * The user is prompted to select the type of user to delete (Admin, Employee, or Guest).
     * The user is then prompted to enter the dni of the user to be deleted.
     * If the user is found, it is deleted.
     */
    public static void deleteUser(){

        Scanner scanner = new Scanner(System.in);

        int dni;

        System.out.println("*-*-*-*-*-*-*-**** Borrar Usuario ****-*-*-*-*-*-*");
        System.out.println("Seleccione el puesto");
        System.out.println("1. Empleado general.");
        System.out.println("2. Administrador.");
        System.out.println("Opción: ");

        int userType = scanner.nextInt();

        switch (userType) {
            case 1 -> {
                EmployeeController employeeController = new EmployeeController();
                System.out.print("Ingrese el DNI que desea eliminar: ");
                dni = scanner.nextInt();
                if (employeeController.getById(dni) != null)
                    employeeController.delete(dni);
            }
            case 2 -> {
                AdminController adminController = new AdminController();
                System.out.print("Ingrese el DNI que desea eliminar: ");
                dni = scanner.nextInt();
                if (adminController.getById(dni) != null)
                    adminController.delete(dni);
            }
            default -> System.out.println("Opción incorrecta.");
        }
    }

    //endregion

    //region [Utilities]
    /**
     * Retrieves a list of all users, including admins and employees.
     * @return An ArrayList containing all users.
     */
    public static ArrayList<User> listOfAllUsers(){
        ArrayList<User> users = new ArrayList<>();

        AdminController adminController = new AdminController();
        EmployeeController employeeController = new EmployeeController();

        users.addAll(adminController.getAllAdmins());
        users.addAll(employeeController.getAllEmployees());
        return users;
    }


    /**
     * Checks if a user with the provided username and password is valid.
     * @param username The username to validate.
     * @param pass     The password to validate.
     * @return The User object if a valid user is found, otherwise null.
     */
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
    //endregion

    //region [Menu]
    public static void viewUsersMenu() {
        System.out.println("\n*-*-*-*-*-*-*-**** Menu Usuarios ****-*-*-*-*-*-*");
        System.out.println("1. Listar Usuarios");
        System.out.println("2. Agregar Usuario");
        System.out.println("3. Modificar Usuario");
        System.out.println("4. Borrar Usuario");
        System.out.println("0. Salir");
        System.out.print("Opción: ");
    }


    /**
     * Controller method for the admin menu
     */
    public static void userMenu() {
        Scanner scanner = new Scanner(System.in);

        String option = "";

        while (!option.equals("0")) {

            viewUsersMenu();
            option = scanner.nextLine();

            switch (option) {
                case "1" -> listOfAllUsers().forEach(System.out::println);
                case "2" -> addUser();
                case "3" -> updateUser();
                case "4" -> deleteUser();
                case "0" -> AdminController.adminMenu(scanner);
                default -> System.out.println("Opción incorrecta.");
            }
        }
    }

    //endregion

}
