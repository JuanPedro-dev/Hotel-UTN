package org.example.Controller;

import org.example.entity.Booking;
import org.example.repository.BookingRepository;

import java.util.List;
import java.util.Scanner;

public class BookingController {
    private final BookingRepository bookingRepository = new BookingRepository();

    /**
     * Displays the booking menu options.
     */
    public static void viewBookingMenu(){
        System.out.println("\n*-*-*-*-*-*-*-**** UTN Motel ****-*-*-*-*-*-*");
        System.out.println("*-*-*-*-*-*-*-**** Menu Reserva ****-*-*-*-*-*");
        System.out.println("1. Check in");
        System.out.println("2. Check out");
        System.out.println("3. Ver todas las reservas");
        System.out.println("4. Servicio al cuarto");
        System.out.println("0. Salir");
        System.out.print("Opción: ");
    }
    /**
     * Booking menu options.
     */
    public static void bookingMenu(Scanner scanner){
        BookingController bookingController = new BookingController();

        String option = "";

        while(!option.equals("0")) {

            viewBookingMenu();
            option = scanner.nextLine();

            switch (option) {
                case "1" -> bookingController.checkIn();
                case "2" -> bookingController.checkOut();
                case "3" -> bookingController.listAll();
                case "4" -> bookingController.serviceRoom();
                case "0" -> option = "0";
                default -> System.out.println("Opción incorrecta.");
            }
        }
    }

    public void checkIn(){
        //Todo
        System.out.println("1. Check in");
    }

    public void checkOut(){
        //Todo
        System.out.println("2. Check out");
    }
    public void listAll(){
        List<Booking> bookings = bookingRepository.list();

        System.out.println("\n*-*-*-*-*-*-*-**** UTN Motel ****-*-*-*-*-*-*");
        System.out.println("*-*-*-*-*-*-*-**** Lista Booking ****-*-*-*-*-*");

        if( bookings.size() == 0 ){
            System.out.println("No hay Bookings cargadas en el sistema");
            return;
        }

        System.out.println(bookings);
    }
    public void serviceRoom(){
        //Todo
        System.out.println("4. Servicio al cuarto");
    }


}
