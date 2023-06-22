package org.example.Controller;

import org.example.entity.Booking;
import org.example.entity.enums.BookingState;
import org.example.repository.BookingRepository;

import java.time.LocalDate;
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
//        System.out.println("4. Servicio al cuarto");
        System.out.println("0. Salir");
        System.out.print("Opción: ");
    }
    /**
     * Booking menu options.
     */
    public static void bookingMenu(){
        Scanner scanner = new Scanner(System.in);

        BookingController bookingController = new BookingController();

        String option = "";

        while(!option.equals("0")) {

            viewBookingMenu();
            option = scanner.nextLine();

            switch (option) {
                case "1" -> bookingController.checkIn();
                case "2" -> bookingController.checkOut();
                case "3" -> bookingController.listAll();
//                case "4" -> bookingController.serviceRoom();
                case "0" -> option = "0";
                default -> System.out.println("Opción incorrecta.");
            }
        }
    }

    public void checkIn(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n*-*-*-*-*-*-*-**** UTN Motel ****-*-*-*-*-*-*");
        System.out.println("*-*-*-*-*-*-*-**** Check-in ****-*-*-*-*-*");


        System.out.print("Ingrese el ID de la reserva: ");
        String bookingId = scanner.nextLine();

        Booking booking = bookingRepository.getById(bookingId);
        if (booking == null) {
            System.out.println("No se encontró la reserva con el ID especificado.");
            return;
        }

        if (booking.getBookingState() != BookingState.PENDING) {
            System.out.println("La reserva ya ha sido check-in o check-out.");
            return;
        }

        LocalDate currentDate = LocalDate.now();
        if (currentDate.isBefore(booking.getCheckInDate())) {
            System.out.println("No es posible realizar el check-in antes de la fecha de check-in programada.");
            return;
        }

        booking.setBookingState(BookingState.CHECKED_IN);

        bookingRepository.update(booking);

        System.out.println("Check-in realizado con éxito.");
    }

    public void checkOut(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n*-*-*-*-*-*-*-**** UTN Motel ****-*-*-*-*-*-*");
        System.out.println("*-*-*-*-*-*-*-**** Check-out ****-*-*-*-*-*");


        System.out.print("Ingrese el ID de la reserva: ");
        String bookingId = scanner.nextLine();

        Booking booking = bookingRepository.getById(bookingId);

        if (booking == null) {
            System.out.println("No se encontró la reserva con el ID especificado.");
            return;
        }

        if (booking.getBookingState() != BookingState.CHECKED_IN) {
            System.out.println("La reserva no ha sido check-in o ya ha sido check-out.");
            return;
        }

        LocalDate currentDate = LocalDate.now();

        if (currentDate.isBefore(booking.getCheckOutDate())) {
            System.out.println("No es posible realizar el check-out antes de la fecha de check-out programada.");
            return;
        }

        booking.setBookingState(BookingState.CHECKED_OUT);
        bookingRepository.update(booking);

        System.out.println("Check-out realizado con éxito.");

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

        System.out.println("Solicitando servicio al cuarto...");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el ID de la reserva: ");
        String bookingId = scanner.nextLine();

        Booking booking = bookingRepository.getById(bookingId);
        if (booking == null) {
            System.out.println("No se encontró la reserva con el ID especificado.");
            return;
        }

        // ToDo Lógica para tomar la orden del servicio
        System.out.print("Ingrese la orden del servicio: ");
        String order = scanner.nextLine();


        System.out.println("Servicio al cuarto solicitado con éxito.");
    }


}
