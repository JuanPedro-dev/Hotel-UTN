package org.example.Controller;

import org.example.entity.Booking;
import org.example.entity.Guest;
import org.example.entity.enums.BookingState;
import org.example.entity.enums.RoomType;
import org.example.repository.BookingRepository;

import java.time.DateTimeException;
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

    /**
     *Performs the check-in process for a guest.
     *
     * This method prompts the user for necessary information, such as guest details, check-in date, and room selection,
     * and creates a new booking based on the provided information. If the guest does not exist in the system, the method
     * offers to create a new guest. After creating the booking, it asks for confirmation to proceed with the reservation.
     *
     * If the user confirms the reservation, the booking is added to the booking repository and a success message is displayed.
     * Otherwise, a cancellation message is shown.
     */
    public void checkIn(){
        //ToDo PROBAR
        GuestController guestController = new GuestController();
        Scanner scanner = new Scanner(System.in);
        int duration, roomNumber;
        Booking addNewBooking;
        LocalDate checkInDate;
        int dni;
        String controller = "S";
        System.out.println("\n*-*-*-*-*-*-*-**** UTN Motel ****-*-*-*-*-*-*");
        System.out.println("\n*-*-*-*-*-*-*-**** Check in ****-*-*-*-*-*-*");
        System.out.println("Ingrese el DNi del usuario a alojarse");
        dni = scanner.nextInt();
        Guest guest = guestController.getById(dni);
        if (guest == null) {
            System.out.println("Para realizar una reserva, debe crear el huesped");
            System.out.println("Desea crear el huesped? S/N");
            scanner.nextLine();
            controller = scanner.nextLine().toUpperCase();
            if (controller.equalsIgnoreCase("s")){
                guestController.add();
            }
            else {
                System.out.println("volvera al menu principal...");
                bookingMenu();
            }
        } else {
            RoomType typeOfRoom = RoomController.typeOfRoom();
            roomNumber = RoomController.roomPicked(typeOfRoom);
            System.out.println("Ingrese el dia de checkIn: ");
            scanner.nextLine();
            checkInDate = createLocalDate();
            System.out.print("Ingrese cantidad de días a alojarse: ");
            duration = scanner.nextInt();
            System.out.println("Tipo de habitación: " + typeOfRoom);
            System.out.println("Número: " + roomNumber);
            System.out.println("Check in: " + checkInDate);
            System.out.println("Check out: " + checkInDate.plusDays(duration));

            System.out.println("Desea enviar la reserva? S/N");
            scanner.nextLine();
            controller = scanner.nextLine().toUpperCase();

            if (controller.equalsIgnoreCase("S")){
                addNewBooking = new Booking(guest,RoomController.getRoom(roomNumber),checkInDate,checkInDate.plusDays(duration), BookingState.INITIATED);
                bookingRepository.add(addNewBooking);
                System.out.println("Reserva realizada.");
            } else System.out.println("Reserva cancelada");
        }
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

    public static LocalDate createLocalDate(){
        Scanner scanner = new Scanner(System.in);
        int year, month, dayOfMonth;

        LocalDate localDate=null;
        do {
            System.out.print("Año <format:yyyy>: ");
            year = scanner.nextInt();
            if (year > 2025 || year<2023) {
                System.out.println("Año incorrecto, vuelva a intentarlo");
            }
        }while(year<2021 || year>2025);

        do{
            System.out.print("Mes <format:m>: ");
            month = scanner.nextInt();
            if(month>12 || month<1 || month<LocalDate.now().getMonthValue()){
                System.out.println("Mes incorrecto, vuelva a intentarlo");
            }
        }while(month>12 || month<1 || month<LocalDate.now().getMonthValue());

        do{
            System.out.print("Dia: <format:d>: ");
            dayOfMonth = scanner.nextInt();
            if(dayOfMonth<1 || dayOfMonth>31){
                System.out.println("Dia incorrecto, vuelva a intentarlo");
            }
        }while(dayOfMonth<1 || dayOfMonth>31);

        try{
            localDate = LocalDate.of(year,month,dayOfMonth);
        }catch (DateTimeException e){
            System.out.println("No fue posible cargar la fecha, vuelva a intentarlo");
            createLocalDate();
        }
        return localDate;
    }

}
