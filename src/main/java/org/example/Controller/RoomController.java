package org.example.Controller;

import org.example.entity.Room;
import org.example.entity.enums.RoomType;
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

    /**
     * Delete room.
     */
    public static void deleteRoom(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n*-*-*-*-*-*-*-**** UTN Motel ****-*-*-*-*-*-*");
        System.out.println("*-*-*-*-*-*-*-**** Borrar Habitación ****-*-*-*-*-*");
        System.out.print("Ingrese el nro de habitación: ");
        Integer roomID = scanner.nextInt();

        RoomRepository roomRepository = new RoomRepository();

        Room room = roomRepository.getById(roomID);

        if (room == null) {
            System.out.println("La habitación no existe. ");
            return;
        }

        roomRepository.delete(roomID);

        System.out.println("Habitación " + roomID + " eliminada.");
    }

    /**
     * Updates the information of a Room object.
     *
     */
    public static void updateRoom(){

        Scanner scanner = new Scanner(System.in);
        RoomRepository roomRepository = new RoomRepository();

        String flag = "S";
        String option;

        System.out.println("\n*-*-*-*-*-*-*-**** UTN Motel ****-*-*-*-*-*-*");
        System.out.println("*-*-*-*-*-*-*-**** Modificar Habitación ****-*-*-*-*-*");
        System.out.print("Ingrese el nro de habitación: ");
        String roomId = scanner.nextLine();

        Room roomToUpdate = null;

        try{
            roomToUpdate = roomRepository.getById(Integer.valueOf(roomId));
        } catch (NumberFormatException e){
            System.out.println("Ingrese un número valido. Error: " + e);
        }

        if( roomToUpdate  == null ) {
            System.out.println("La habitación '" + roomId + "' no existe.");
            return;
        }

        while (flag.equals("S")) {

            if (roomToUpdate != null) {

                System.out.println("Seleccione atributo a cambiar");
                System.out.println("1. Disponible");
                System.out.println("2. Tipo de Habitación.");
                System.out.print("Opción: ");

                option = scanner.nextLine();

                switch (option) {
                    case "1" -> {
                        String optionDisponible = "";
                        System.out.println("1. Disponible");
                        System.out.println("2. Ocupado");
                        System.out.print("Opción: ");

                        optionDisponible = scanner.nextLine();

                        if (optionDisponible.equals("1")) roomToUpdate.setAvailable(true);
                        if (optionDisponible.equals("2")) roomToUpdate.setAvailable(false);
                        if (!optionDisponible.equals("1") && !optionDisponible.equals("2")){
                            System.out.println("Opción incorrecta.");
                            break;
                        }
                        System.out.println("Modificación exitosa");
                    }
                    case "2" -> {
                        roomToUpdate = changeTypeRoom( roomToUpdate );
                        roomRepository.update(roomToUpdate);
                    }
                    default -> System.out.println("Opción incorrecta.");
                }
            }

            System.out.print("Quiere cambiar otro atributo? S/N: ");
            flag = scanner.nextLine().toUpperCase();

        }
        roomRepository.update(roomToUpdate);
    }

    public static RoomType typeOfRoom() {
        Scanner scanner = new Scanner(System.in);
        RoomType type = null;
        System.out.println("Ingrese el tipo de habitación que quiere reservar: ");
        roomTypeMenu();
        int roomType = scanner.nextInt();
        switch (roomType) {
            case 1:
                type = RoomType.SINGLE;
                break;
            case 2:
                type = RoomType.TWIN;
                break;
            case 3:
                type = RoomType.MATRIMONIAL;
                break;
            case 4:
                type = RoomType.TRIPLE;
                break;
            case 5:
                type = RoomType.QUAD;
                break;
            default:
                System.out.println("Ingreso incorrectamente.");
        }
        return type;
    }

    public static int roomPicked(RoomType type){
        int option;
        boolean flag=false;
        Scanner scanner = new Scanner(System.in);
        RoomRepository roomRepository = new RoomRepository();

        for (Room variable : roomRepository.list()) {
            if (variable.getRoomType() == type) System.out.println(variable.toString());
        }
        do {
            System.out.print("Escriba el numero de la habitación que desea reservar: ");
            option = scanner.nextInt();
            for (Room r : roomRepository.list()) {
                if (option == r.getRoomNumber()){
                    return option;
                }
                else flag = false;
            }
            if(!flag) System.out.println("Debe ingresar un numero de habitación correcto");
        }while(!flag);

        return option;
    }

    public static Room changeTypeRoom(Room roomToUpdate) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Seleccione el tipo de habitación.");
        System.out.println("1. Single");
        System.out.println("2. Twin");
        System.out.println("3. Matrimonial");
        System.out.println("4. Triple");
        System.out.println("5. Quad");
        System.out.print("Opción: ");

        String option = scanner.nextLine();

        switch (option){
            case "1" -> {
                roomToUpdate.setRoomType(RoomType.SINGLE);
                System.out.println("Modificación exitosa");
            }
            case "2" -> {
                roomToUpdate.setRoomType(RoomType.TWIN);
                System.out.println("Modificación exitosa");
            }
            case "3" -> {
                roomToUpdate.setRoomType(RoomType.MATRIMONIAL);
                System.out.println("Modificación exitosa");
            }
            case "4" -> {
                roomToUpdate.setRoomType(RoomType.TRIPLE);
                System.out.println("Modificación exitosa");
            }
            case "5" -> {
                roomToUpdate.setRoomType(RoomType.QUAD);
                System.out.println("Modificación exitosa");
            }
            default -> System.out.println("Opción incorrecta.");
        }
        return roomToUpdate;
    }

    public static void roomMenu(Scanner scanner) {

        String option = "";

        while (!option.equals("0")) {

            viewRoomMenu();
            option = scanner.nextLine();

            switch (option) {
                case "0" -> option = "0";
                case "1" -> viewListRoomAvailable();
                case "2" -> viewListRoomNotAvailable();
                case "3" -> viewListRoom();
                case "4" -> deleteRoom();
                case "5" -> updateRoom();
                default -> System.out.println("Opción incorrecta.");
            }
        }
    }

    public static void roomTypeMenu(){
        System.out.println("*-*-*-*-*-*-*-***Habitaciones****-*-*-*-*-*-*");
        System.out.println("1. Single");
        System.out.println("2. Twin");
        System.out.println("3. Matrimonial");
        System.out.println("4. Triple");
        System.out.println("5. Quad");
    }

    public static Room getRoom(int roomNumber) {
        RoomRepository roomRepository = new RoomRepository();
        List<Room> rooms = roomRepository.list();
        if (rooms != null) {
            for (Room room : rooms) {
                if (room.getRoomNumber() == roomNumber) {
                    return room;
                }
            }
        }
        return null;
    }

}
