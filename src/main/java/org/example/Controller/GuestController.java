package org.example.Controller;

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


    /**
     * Lists all the guest in the guest repository and prints them to the console.
     */
    public void list() {
        guestRepository.list().forEach(System.out::println);
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
     * @param newGuest The admin object to be added.
     */
    public void add(Guest newGuest) {
        guestRepository.add(newGuest);
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
     *Updates the information of a guest.
     *
     *
     *@param scanner The scanner object for user input.
     */
    public void update(Guest updateGuest){
        String flag = null;
        String option;
        do {
            if (updateGuest != null) {
                System.out.println("Seleccione atributo a cambiar");
                System.out.println("1. Nombre");
                System.out.println("2. Apellido");
                System.out.println("3. Teléfono");
                System.out.println("4. Email");
                option = scanner.nextLine();
                //scanner.nextLine();             //cleaned buffer
                if (HotelController.isInteger(option)) {
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
                    }
                }
            }
            //scanner.nextLine();             //cleaned buffer
            System.out.println("Quiere cambiar otro atributo? S/N");
            flag = scanner.nextLine().toUpperCase();
        } while (flag.equals("S"));
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
}
