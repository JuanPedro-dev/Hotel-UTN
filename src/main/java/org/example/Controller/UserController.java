package org.example.Controller;

import org.example.entity.Admin;
import org.example.entity.Employee;
import org.example.entity.Guest;
import org.example.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserController {

    //region [CRUD]

    /**
     * This method allows the user to add a new user to the system.
     *
     *The user can be either an Employee or an Admin, depending on the type selected.
     * @param scanner the Scanner object used for user input
     */
    //ToDo PROBADO
    public static void addUser(Scanner scanner){
        Employee employee;
        Admin admin;
        int type;
        String controller = "N";
        String name, lastName, email, user, password;
        Integer dni;
        long phone;
        System.out.println("*-*-*-*-*-*-*-***Crear Usuario****-*-*-*-*-*-*");
        do {
            System.out.println("Enter para continuar..");
            scanner.nextLine();             //cleaned buffer
            System.out.println("INGRESE LOS DATOS");
            System.out.println("Nombre: ");
            name = scanner.nextLine();
            System.out.println("Apellido: ");
            lastName = scanner.nextLine();
            System.out.println("DNI: ");
            dni = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Email: ");
            email = scanner.nextLine();
            System.out.println("Telefono: ");
            phone = scanner.nextLong();
            scanner.nextLine();             //cleaned buffer
            System.out.println("Usuario");
            user = scanner.nextLine();
            System.out.println("Password");
            password = scanner.nextLine();
            System.out.println("Ingrese 1.Empleado o 2.Admin, según permisos que tendrá");
            type = scanner.nextInt();

            if(type==1) {
                employee = new Employee(name, lastName, dni,user,password, email, phone);
                EmployeeController employeeController = new EmployeeController();
                employeeController.add(employee);
            }
            if(type==2) {
                admin = new Admin(name, lastName, dni,user,password, email, phone);
                AdminController adminController = new AdminController();
                adminController.add(admin);
            }
            System.out.println("Enter para continuar..");
            scanner.nextLine();             //cleaned buffer
            System.out.println("Desea cargar otro usuario? S/N");
            controller = scanner.nextLine().toUpperCase();
        } while (controller.equals("S"));
    }


    /**
     * Searches for a user based on user type and user ID.
     *
     * @param scanner The Scanner object for user input.
     */
    //ToDo PROBADO -> bug en el menu
    public static void updateUser(Scanner scanner){
        int dni;
        System.out.println("*-*-*-*-*-*-*-***Modificar Usuario****-*-*-*-*-*-*");
        System.out.println("Enter para continuar..");
        scanner.nextLine();             //cleaned buffer
        System.out.println("Ingrese el tipo de usuario que desea modificar: \n");
        System.out.println(" 1. Admin \n 2. Employee");
        int userType = scanner.nextInt();
        scanner.nextLine();             //cleaned buffer

        switch (userType) {
            case 1:
                AdminController adminController = new AdminController();
                System.out.println("Ingrese DNI del admin");
                dni = scanner.nextInt();
                Admin updateAdmin = adminController.getById(dni);
                if (updateAdmin != null)
                    adminController.update(updateAdmin);
                break;
            case 2:
                EmployeeController employeeController = new EmployeeController();
                System.out.println("Ingrese DNI del employee");
                dni = scanner.nextInt();
                Employee updateEmployee = employeeController.getById(dni);
                if (updateEmployee != null)
                    employeeController.update(updateEmployee);
                break;
            default:
                System.out.println("Invalid user type.");
                break;
        }
    }

    /**
     * Deletes a user based on user input.
     * The user is prompted to select the type of user to delete (Admin, Employee, or Guest).
     * The user is then prompted to enter the dni of the user to be deleted.
     * If the user is found, it is deleted.
     * @param scanner The Scanner object used for user input.
     */
    //ToDo PROBADO -> bug en el menu
    public static void deleteUser(Scanner scanner){
        //ToDo PROBADO -> BORRA OK
        //ToDo Falta implementar Guest y Employee
        int dni;
        System.out.println("*-*-*-*-*-*-*-***Borrar Usuario****-*-*-*-*-*-*");
        System.out.println("Presione Enter para continuar..");
        scanner.nextLine(); // Limpiar el búfer

        System.out.println("Ingrese el tipo de usuario que desea borrar:");
        System.out.println("1. Admin");
        System.out.println("2. Employee");
        int userType = scanner.nextInt();
        scanner.nextLine(); // Limpiar el búfer

        switch (userType) {
            case 1:
                AdminController adminController = new AdminController();
                System.out.println("Ingrese el DNI que desea eliminar");
                dni = scanner.nextInt();
                if (adminController.getById(dni) != null)
                    adminController.delete(dni);
                break;
            case 2:
                EmployeeController employeeController = new EmployeeController();
                System.out.println("Ingrese el DNI que desea eliminar");
                dni = scanner.nextInt();
                if(employeeController.getById(dni) != null)
                    employeeController.delete(dni);
                break;
            default:
                System.out.println("Invalid user type.");
                break;
        }
    }

    //endregion

    //region [Utilities]
    /**
     * Retrieves a list of all users, including admins and employees.
     *
     * @return An ArrayList containing all users.
     */
    //ToDo PROBADO
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
     *
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
        System.out.println("*-*-*-*-*-*-*-***Menu Usuarios****-*-*-*-*-*-*");
        System.out.println("1. Listar Usuarios");
        System.out.println("2. Agregar Usuario");
        System.out.println("3. Modificar Usuario");
        System.out.println("4. Borrar Usuario");
        System.out.println("0. Salir");
    }


    /**
     * Controller method for the admin menu
     * @param scanner
     */
    public static void UserMenu(Scanner scanner) {
        String option = "";

        while (!option.equals("0")) {
            viewUsersMenu();
            option = scanner.nextLine();

            switch (option) {
                case "1":
                    listOfAllUsers().forEach(System.out::println);
                    break;
                case "2":
                    addUser(scanner);
                    break;
                case "3":
                    updateUser(scanner);
                    break;
                case "4":
                    deleteUser(scanner);
                    break;
                case "0":
                    AdminController.adminMenu(scanner);
                    break;
                default:
                    System.out.println("Incorrect Input.");
            }
        }
    }

    //endregion

}
