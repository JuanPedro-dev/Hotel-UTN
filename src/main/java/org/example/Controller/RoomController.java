package org.example.Controller;

import org.example.entity.Room;
import org.example.repository.RoomRepository;

import java.util.List;
import java.util.Scanner;

public class RoomController {

    /**
     * Displays the room menu options.
     */
    public static void viewRoomMenu(){
        System.out.println("\n*-*-*-*-*-*-*-**** UTN Motel ****-*-*-*-*-*-*");
        System.out.println("*-*-*-*-*-*-*-**** Menu Reserva ****-*-*-*-*-*");
        System.out.println("1. Ver habitaciones disponibles");
        System.out.println("2. Ver habitaciones ocupadas");
        System.out.println("3. Ver todas las habitaciones");
        System.out.println("4. Eliminar Habitacion");
        System.out.println("5. Modificar Habitacion");
        System.out.println("0. Salir");
        System.out.print("Opción: ");
    }

    /**
     * Displays List of room available.
     */
    public static void viewListRoomAvailable(){
        System.out.println("\n*-*-*-*-*-*-*-**** UTN Motel ****-*-*-*-*-*-*");
        System.out.println("*-*-*-*-*-*-*-**** Lista Habitaciones Disponibles ****-*-*-*-*-*");

        RoomRepository roomRepository = new RoomRepository();
        List<Room> rooms = roomRepository.list();

        if( rooms.size() == 0 ){
            System.out.println("No hay habitaciones cargadas en el sistema");
            return;
        }

        for ( Room room : rooms ){
            if( room.isAvailable() ) System.out.println(room);
        }
    }

    /**
     * Displays List of room NOT available.
     */
    public static void viewListRoomNotAvailable(){
        System.out.println("\n*-*-*-*-*-*-*-**** UTN Motel ****-*-*-*-*-*-*");
        System.out.println("*-*-*-*-*-*-*-**** Lista Habitaciones Ocupadas ****-*-*-*-*-*");

        RoomRepository roomRepository = new RoomRepository();
        List<Room> rooms = roomRepository.list();

        if( rooms.size() == 0 ){
            System.out.println("No hay habitaciones cargadas en el sistema");
            return;
        }

        for ( Room room : rooms ){
            if( !room.isAvailable() ) System.out.println(room);
        }
    }

    /**
     * Displays List of room NOT available.
     */
    public static void viewListRoom(){
        System.out.println("\n*-*-*-*-*-*-*-**** UTN Motel ****-*-*-*-*-*-*");
        System.out.println("*-*-*-*-*-*-*-**** Lista Habitaciones Ocupadas ****-*-*-*-*-*");

        RoomRepository roomRepository = new RoomRepository();
        List<Room> rooms = roomRepository.list();

        if( rooms.size() == 0 ){
            System.out.println("No hay habitaciones cargadas en el sistema");
            return;
        }

        System.out.println(rooms);

    }

    public static void roomMenu(Scanner scanner){

        String option = "";

        while (!option.equals("0")) {

            viewRoomMenu();
            option = scanner.nextLine();



            System.out.println("0. Salir");
            switch (option) {
                case "1":
                    viewListRoomAvailable();
                    break;
                case "2":
                    viewListRoomNotAvailable();
                    break;
                case "3":
                    viewListRoom();
                    break;
                case "4":
                    System.out.println("4. Eliminar Habitacion");
                    // ToDo falta implementacion
                    break;
                case "5":
                    System.out.println("5. Modificar Habitacion");
                    // ToDo falta implementacion
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Opción incorrecta.");
            }
        }

    }



}
