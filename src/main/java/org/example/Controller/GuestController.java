package org.example.Controller;

import org.example.entity.Admin;
import org.example.entity.Employee;
import org.example.entity.Guest;
import org.example.entity.Room;
import org.example.exceptions.AdminExceptions;
import org.example.exceptions.GuestExceptions;
import org.example.repository.GuestRepository;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class GuestController {
    GuestRepository guestRepository = new GuestRepository();

    //region [CRUD]


    /**
     * Adds a new guest to the guest repository.
     *
     */
    public void add() {
        Scanner scanner = new Scanner(System.in);
        Guest newGuest = new Guest();

        String controller = "S";
        System.out.println("*-*-*-*-*-*-*-**** Crear Huesped *****-*-*-*-*-*-*");

        while (controller.equals("S")) {

            System.out.println("INGRESE LOS DATOS");
            System.out.print("Nombre: ");
            newGuest.setName(scanner.nextLine());
            System.out.print("Apellido: ");
            newGuest.setLastName(scanner.nextLine());
            System.out.print("Email: ");
            newGuest.setEmail(scanner.nextLine());

            boolean hasError = true;

            while(hasError){
                try{
                    System.out.print("DNI: ");
                    newGuest.setDni(Integer.parseInt(scanner.nextLine()));
                    System.out.print("Teléfono: ");
                    newGuest.setPhoneNumber(Long.parseLong(scanner.nextLine()));
                    hasError = false;
                } catch (NumberFormatException e){
                    System.out.println("Ingrese un número valido. Error: " + e);
                }
            }

            guestRepository.add(newGuest);

            System.out.print("Desea cargar otro usuario? S/N: ");
            controller = scanner.nextLine().toUpperCase();
        }
    }

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
     * Modify guest.
     */
    public void modifyGuest() {
        Scanner scanner = new Scanner(System.in);


        System.out.print("Ingrese DNI del huesped: ");
        Integer dni = scanner.nextInt();

        Guest updateGuest = guestRepository.getById(dni);

        if (updateGuest != null) {
            this.update(updateGuest);
        } else System.out.println("No se encuentra el DNI. ");

    }

    /**
     * Deletes a guest from the guest repository based on the provided DNI (Document Number Identifier).
     */
    public void delete(){
        Scanner scanner = new Scanner(System.in);
        Integer dni = null;

        boolean hasError = true;

        while(hasError){
            try{
                System.out.print("Ingrese el DNI del huesped que desea eliminar: ");
                dni = Integer.parseInt(scanner.nextLine());
                hasError = false;
            } catch (Exception e){
                System.out.println("Ingrese un número valido. Error: " + e);
            }
        }

        try{
            guestRepository.delete(dni);
        }catch (GuestExceptions e){
            System.out.println("Error el dni no existe. " + e.getMessage());
        }
    }

    /**
     * Updates the information of a guest.
     *
     * @param updateGuest the guest object containing the updated information
     */
    public void update(Guest updateGuest){

        Scanner scanner = new Scanner(System.in);
        String flag = "S";
        String option;

        while (flag.equals("S")) {

            if (updateGuest != null) {
                System.out.println("\nSeleccione atributo a cambiar");
                System.out.println("1. Nombre");
                System.out.println("2. Apellido");
                System.out.println("3. Teléfono");
                System.out.println("4. Email");
                System.out.print("Opción: ");

                option = scanner.nextLine();

                switch (option) {
                    case "1":
                        System.out.print("Ingrese nuevo nombre: ");
                        updateGuest.setName(scanner.nextLine());
                        System.out.println("Modificación exitosa");
                        break;
                    case "2":
                        System.out.print("Ingrese nuevo apellido: ");
                        updateGuest.setLastName(scanner.nextLine());
                        System.out.println("Modificación exitosa");
                        break;
                    case "3":
                        boolean hasError = true;

                        while(hasError){
                            try{
                                System.out.print("Ingrese nuevo teléfono: ");
                                updateGuest.setPhoneNumber(Long.parseLong(scanner.nextLine()));
                                hasError = false;
                            } catch (NumberFormatException e){
                                System.out.println("Ingrese un número valido. Error: " + e);
                            }
                        }
                        System.out.println("Modificación exitosa");
                        break;
                    case "4":
                        System.out.print("Ingrese nuevo email: ");
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
        System.out.println("\n*-*-*-*-*-*-*-**** UTN Motel ****-*-*-*-*-*-*");
        System.out.println("*-*-*-*-*-*-*-**** Menu Huesped ****-*-*-*-*-*");
        System.out.println("1. Crear un Huesped");
        System.out.println("2. Listar Huespedes");
        System.out.println("3. Modificar un Huesped");
        System.out.println("4. Eliminar un Huesped");
        System.out.println("0. Salir");
        System.out.print("Opción: ");
    }

    /**
     * Displays the guest menu and handles user interactions.
     */
    public static void guestMenu(){
        Scanner scanner = new Scanner(System.in);

        Integer dni;

        GuestController guestController = new GuestController();

        String option = "";

        while(!option.equals("0")) {
            viewGuestMenu();
            option = scanner.nextLine();

            switch (option) {
                case "1" -> guestController.add();
                case "2" -> guestController.list();
                case "3" -> guestController.modifyGuest();
                case "4" -> guestController.delete();
                case "0" -> option = "0";
                default -> System.out.println("Opción incorrecta.");
            }
        }
    }
    //endregion
}
