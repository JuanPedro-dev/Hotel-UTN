package org.example.Controller;

import org.example.entity.Admin;
import org.example.entity.Employee;
import org.example.entity.Guest;
import org.example.exceptions.AdminExceptions;
import org.example.exceptions.GuestExceptions;
import org.example.repository.GuestRepository;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class GuestController {
    private static final Scanner scanner = new Scanner(System.in);
    GuestRepository guestRepository = new GuestRepository();

    //region [CRUD]
    /**
     * Lists all the guest in the guest repository and prints them to the console.
     */
    public void list() {
        List<Guest> guests = guestRepository.list();
        if (guests.isEmpty())
            System.out.println("No hay huespedes");
        else
            guests.forEach(System.out::println);
    }


    /**
     * Retrieves a list of all guest from the guest repository.
     *
     * @return A list of all guest.
     */
    public List<Guest> getAllGuests(){
        return guestRepository.list();
    }


    /**
     * Adds a new guest to the guest repository.
     *
     * @param scanner the Scanner object used for user input
     */
    public void add(Scanner scanner) {
            Guest newGuest = new Guest();
            String controller = "N";
            System.out.println("*-*-*-*-*-*-*-***Crear Huesped****-*-*-*-*-*-*");

        while (controller.equals("S")) {

                //ToDo borrar comentarios si no es necesario el clear buffer
//                System.out.println("Enter para continuar..");
//                scanner.nextLine();             //cleaned buffer

                System.out.println("INGRESE LOS DATOS");
                System.out.println("Nombre: ");
                newGuest.setName(scanner.nextLine());
                System.out.println("Apellido: ");
                newGuest.setLastName(scanner.nextLine());
                System.out.println("DNI: ");
                newGuest.setDni(scanner.nextInt());
                scanner.nextLine();
                System.out.println("Email: ");
                newGuest.setEmail(scanner.nextLine());
                System.out.println("Telefono: ");
                newGuest.setPhoneNumber(scanner.nextLong());

//                scanner.nextLine();             //cleaned buffer
//                System.out.println("Enter para continuar..");
//                scanner.nextLine();             //cleaned buffer
                guestRepository.add(newGuest);

                System.out.print("Desea cargar otro usuario? S/N: ");
                controller = scanner.nextLine().toUpperCase();
            }
    }


    /**
     * Deletes a guest from the guest repository based on the provided DNI (Document Number Identifier).
     *
     * @param dni The DNI of the guest to delete.
     */
    public void delete(Integer dni){
        guestRepository.delete(dni);
    }

    /**
     * Updates the information of a guest.
     *
     * @param updateGuest the guest object containing the updated information
     */
    public void update(Guest updateGuest){
        String flag = "";
        String option;

        while (flag.equals("S")) {

            if (updateGuest != null) {
                System.out.println("Seleccione atributo a cambiar");
                System.out.println("1. Nombre");
                System.out.println("2. Apellido");
                System.out.println("3. Teléfono");
                System.out.println("4. Email");
                option = scanner.nextLine();

                switch (option) {
                    case "1":
                        System.out.println("Ingrese nuevo nombre");
                        updateGuest.setName(scanner.nextLine());
                        System.out.println("Modificación exitosa");
                        break;
                    case "2":
                        System.out.println("Ingrese nuevo apellido");
                        updateGuest.setLastName(scanner.nextLine());
                        System.out.println("Modificación exitosa");
                        break;
                    case "3":
                        System.out.println("Ingrese nuevo teléfono");
                        updateGuest.setPhoneNumber(scanner.nextInt());
                        System.out.println("Modificación exitosa");
                        break;
                    case "4":
                        System.out.println("Ingrese nuevo email");
                        updateGuest.setEmail(scanner.nextLine());
                        System.out.println("Modificación exitosa");
                        break;
                    default:
                        System.out.println("Opción incorrecta.");

                }
            }

            System.out.print("Quiere cambiar otro atributo? S/N: ");

            flag = scanner.nextLine().toUpperCase();

        }

        guestRepository.update(updateGuest);
    }


    /**
     * Retrieves a guest from the guest repository based on the provided DNI (Document Number Identifier).
     *
     * @param dni The DNI of the guest to retrieve.
     * @return The guest object associated with the provided DNI, or null if not found.
     */
    public Guest getById(Integer dni) {
        try{
            return Optional.ofNullable(guestRepository.getById(dni))
                    .orElseThrow(()-> new GuestExceptions("No se encontro el huesped buscado."));
        } catch (GuestExceptions e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    //endregion

    //region [Menu]

    /**
     * Displays the guest menu options.
     */
    public static void viewGuestMenu(){
        System.out.println("*-*-*-*-*-*-*-***UTN Motel****-*-*-*-*-*-*");
        System.out.println("*-*-*-*-*-*-*-***Menu Huesped****-*-*-*-*-*");
        System.out.println("1. Crear un Huesped");
        System.out.println("2. Listar Huespedes");
        System.out.println("3. Modificar un Huesped");
        System.out.println("4. Eliminar un Huesped");
        System.out.println("0. Salir");
    }

    /**
     * Displays the guest menu and handles user interactions.
     *
     * @param scanner the Scanner object used for user input
     */
    public static void guestMenu(Scanner scanner){
        int dni;

        GuestController guestController = new GuestController();

        String option = "";

        while(!option.equals("0")) {
            viewGuestMenu();
            option = scanner.nextLine();
            switch (option) {
                case "1":
                    //ToDo PROBADO
                    guestController.add(scanner);
                    break;
                case "2":
                    //ToDo PROBADO
                    guestController.list();
                    break;
                case "3":
                    //ToDo PROBADO
                    System.out.println("Ingrese DNI del huesped");
                    dni = scanner.nextInt();
                    Guest updateGuest = guestController.getById(dni);
                    if (updateGuest != null)
                        guestController.update(updateGuest);
                    break;
                case "4":
                    //ToDo PROBADO
                    System.out.println("Ingrese el DNI del huesped que desea eliminar");
                    dni = scanner.nextInt();
                    if (guestController.getById(dni) != null)
                        guestController.delete(dni);
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
