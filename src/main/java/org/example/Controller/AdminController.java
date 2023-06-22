package org.example.Controller;

import org.example.entity.Admin;
import org.example.exceptions.AdminExceptions;
import org.example.exceptions.EmployeeExceptions;
import org.example.repository.AdminRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class AdminController {
    AdminRepository adminRepository = new AdminRepository();

    public AdminController() {
    }

    //region [CRUD]
    /**
     * Lists all the admins in the admin repository and prints them to the console.
     */
    public void list() {
        adminRepository.list().forEach(System.out::println);
    }

    /**
     * Retrieves a list of all admins from the admin repository.
     *
     * @return A list of all admins.
     */
    public List<Admin> getAllAdmins(){
        return adminRepository.list();
    }

    /**
     * Adds a new admin to the admin repository.
     * @param newAdmin The admin object to be added.
     */
    public void add(Admin newAdmin) {
        adminRepository.add(newAdmin);
    }

    /**
     * Deletes an admin from the admin repository based on the provided DNI (Document Number Identifier).
     * @param dni The DNI of the admin to delete.
     */
    public void delete(Integer dni){
        adminRepository.delete(dni);
    }

    /**
     * Updates the information of an Admin object.
     * @param updateAdmin The Admin object to be updated.
     */
    public void update(Admin updateAdmin){
        Scanner scanner = new Scanner(System.in);

        String flag = "";
        String option;

        while (flag.equals("S")) {

            if (updateAdmin != null) {
                System.out.println("\nSeleccione atributo a cambiar");
                System.out.println("1. Nombre");
                System.out.println("2. Apellido");
                System.out.println("3. Teléfono");
                System.out.println("4. Email");
                System.out.println("5. Usuario");
                System.out.println("6. Password");
                System.out.print("Opción: ");
                option = scanner.nextLine();

                switch (option) {
                    case "1" -> {
                        System.out.println("Ingrese nuevo nombre");
                        updateAdmin.setName(scanner.nextLine());
                        System.out.println("Modificación exitosa");
                    }
                    case "2" -> {
                        System.out.println("Ingrese nuevo apellido");
                        updateAdmin.setLastName(scanner.nextLine());
                        System.out.println("Modificación exitosa");
                    }
                    case "3" -> {
                        System.out.println("Ingrese nuevo teléfono");

                        while(true){
                            try{
                                System.out.print("Ingrese nuevo teléfono= ");
                                updateAdmin.setPhoneNumber(Long.parseLong(scanner.nextLine()));
                                System.out.println("Modificación exitosa");
                                break;
                            } catch (NumberFormatException e){
                                System.out.println("Ingrese un número valido. Error: " + e.getMessage());
                            }
                        }
                        System.out.println("Modificación exitosa");
                    }
                    case "4" -> {
                        System.out.println("Ingrese nuevo email");
                        updateAdmin.setEmail(scanner.nextLine());
                        System.out.println("Modificación exitosa");
                    }
                    case "5" -> {
                        System.out.println("Ingrese nuevo user");
                        updateAdmin.setUser(scanner.nextLine());
                        System.out.println("Modificación exitosa");
                    }
                    case "6" -> {
                        System.out.println("Ingrese nuevo password");
                        updateAdmin.setPassword(scanner.nextLine());
                        System.out.println("Modificación exitosa");
                    }
                }
            }

            System.out.print("Quiere cambiar otro atributo? S/N: ");
            flag = scanner.nextLine().toUpperCase();

        }
        try {
            adminRepository.update(updateAdmin);
        }catch (EmployeeExceptions e){
            System.out.println("Error: " + e.getMessage());
        }
    }


    /**
     * Retrieves an admin from the admin repository based on the provided DNI (Document Number Identifier).
     *
     * @param dni The DNI of the admin to retrieve.
     * @return The admin object associated with the provided DNI, or null if not found.
     */
    public Admin getById(Integer dni) {
        try {
            return Optional.ofNullable(adminRepository.getById(dni))
                    .orElseThrow(() -> new AdminExceptions("No se encontró el Admin buscado."));
        } catch (AdminExceptions e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    //endregion

    //region [Menu]

    /**
     * Displays the admin menu options.
     */
    public static void viewAdminMenu() {
        System.out.println("\n*-*-*-*-*-*-*-**** UTN Hotel ****-*-*-*-*-*-*");
        System.out.println("*-*-*-*-*-*-*-**** Admin Menu ****-*-*-*-*-*");
        System.out.println("1. Habitaciones");
        System.out.println("2. Huespedes");
        System.out.println("3. Reservas");
        System.out.println("4. Usuarios");
        System.out.println("0. Salir");
        System.out.print("Opción: ");
    }

    public static void adminMenu(Scanner scanner) {

        String option = "";

        while (!option.equals("0")) {

            viewAdminMenu();
            option = scanner.nextLine();

            switch (option) {
                case "1" -> RoomController.roomMenu();
                case "2" -> GuestController.guestMenu();
                case "3" -> BookingController.bookingMenu();
                case "4" -> UserController.userMenu();
                case "0" -> option = "0";
                default ->System.out.println("Opción incorrecta.");
            }
        }
    }
    //endregion
}
