package org.example.Controller;

import org.example.entity.Admin;
import org.example.exceptions.AdminExceptions;
import org.example.repository.AdminRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class AdminController {

    private static final Scanner scanner = new Scanner(System.in);
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
     *
     * @param newAdmin The admin object to be added.
     */
    public void add(Admin newAdmin) {
        adminRepository.add(newAdmin);
    }


    /**
     * Deletes an admin from the admin repository based on the provided DNI (Document Number Identifier).
     *
     * @param dni The DNI of the admin to delete.
     */
    public void delete(Integer dni){
        adminRepository.delete(dni);
    }

    /**
     * Updates the information of an Admin object.
     *
     * @param updateAdmin The Admin object to be updated.
     */
    public void update(Admin updateAdmin){
        String flag = null;
        String option;
        do {
            if (updateAdmin != null) {
                System.out.println("Seleccione atributo a cambiar");
                System.out.println("1. Nombre");
                System.out.println("2. Apellido");
                System.out.println("3. Teléfono");
                System.out.println("4. Email");
                System.out.println("5. Usuario");
                System.out.println("6. Password");
                option = scanner.nextLine();
                //scanner.nextLine();             //cleaned buffer
                if (HotelController.isInteger(option)) {
                    switch (option) {
                        case "1":
                            System.out.println("Ingrese nuevo nombre");
                            updateAdmin.setName(scanner.nextLine());
                            System.out.println("Modificación exitosa");
                            break;
                        case "2":
                            System.out.println("Ingrese nuevo apellido");
                            updateAdmin.setLastName(scanner.nextLine());
                            System.out.println("Modificación exitosa");
                            break;
                        case "3":
                            System.out.println("Ingrese nuevo teléfono");
                            updateAdmin.setPhoneNumber(scanner.nextInt());
                            System.out.println("Modificación exitosa");
                            break;
                        case "4":
                            System.out.println("Ingrese nuevo email");
                            updateAdmin.setEmail(scanner.nextLine());
                            System.out.println("Modificación exitosa");
                            break;
                        case "5":
                            System.out.println("Ingrese nuevo user");
                            updateAdmin.setUser(scanner.nextLine());
                            System.out.println("Modificación exitosa");
                            break;
                        case "6":
                            System.out.println("Ingrese nuevo password");
                            updateAdmin.setPassword(scanner.nextLine());
                            System.out.println("Modificación exitosa");
                            break;
                    }
                } //Se podria sacar el if ya que pregunto antes en el UpdateUser
            }
            System.out.println("Quiere cambiar otro atributo? S/N");
            flag = scanner.nextLine().toUpperCase();
        } while (flag.equals("S"));
        adminRepository.update(updateAdmin);
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
        System.out.println("*-*-*-*-*-*-*-***UTN Hotel****-*-*-*-*-*-*");
        System.out.println("*-*-*-*-*-*-*-***Admin Menu****-*-*-*-*-*");
        System.out.println("1. Habitaciones");
        System.out.println("2. Huespedes");
        System.out.println("3. Reservas");
        System.out.println("4. Usuarios");
        System.out.println("0. Salir");
        System.out.println("Ingrese una opcion: ");
    }

    public static void adminMenu(Scanner scanner) {
        boolean flag = true;
        do {
            viewAdminMenu();
            String option = scanner.nextLine();
            if (HotelController.isInteger(option)) {
                switch (option) {
                    case "1":
                        //ToDo menu de rooms
                        RoomController.roomMenu(scanner);
                        break;
                    case "2":
                        //ToDo menu de guest
                        GuestController.guestMenu(scanner);
                        break;
                    case "3":
                        //ToDo menu de booking
                        BookingController.bookingMenu(scanner);
                        break;
                    case "4":
                        UserController.UserMenu(scanner);
                        break;
                    case "0":
                        HotelController.login();
                        break;
                    default:
                        System.out.println("Ingreso incorrectamente.");
                }
            }
            else flag = HotelController.messageError();
        } while (flag);
    }
    //endregion
}
