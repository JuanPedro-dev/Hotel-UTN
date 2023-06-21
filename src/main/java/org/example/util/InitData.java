package org.example.util;

import org.example.entity.Admin;
import org.example.entity.Employee;
import org.example.entity.Guest;
import org.example.entity.Room;
import org.example.entity.enums.RoomType;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Si queremos que ya tenga datos ejecutar previamente esta clase
 */
public class InitData {public static void main(String[] args) {
    initRoom();
    initUser();
    initEmployee();
    initGuest();
    // TODO repetir con los otros, crear un initClase NO Olvidar poner bien la ruta del archivo
}

    public static void initRoom(){
        SerializerGson serializerGson = new SerializerGson<>();

        File roomFile = new File("src/main/java/org/example/file/RoomFile.json");

        List<Room> rooms = new ArrayList<>(Arrays.asList(
                new Room(true,101, RoomType.TRIPLE),
                new Room(true,102,RoomType.MATRIMONIAL),
                new Room(true,103,RoomType.QUAD),
                new Room(true,104,RoomType.SINGLE),
                new Room(true,105,RoomType.TWIN),


                new Room(true,201,RoomType.TRIPLE),
                new Room(true,202,RoomType.MATRIMONIAL),
                new Room(true,203,RoomType.QUAD),
                new Room(true,204,RoomType.SINGLE),
                new Room(true,205,RoomType.TWIN),


                new Room(true, 301,RoomType.TRIPLE),
                new Room(true,302,RoomType.MATRIMONIAL),
                new Room(false,303,RoomType.QUAD),
                new Room(false,304,RoomType.SINGLE),
                new Room(false,305,RoomType.TWIN)
        ));

        serializerGson.serializer(rooms, roomFile.getPath());
    }

    public static void initUser(){
        SerializerGson serializerGson = new SerializerGson<>();

        File adminFile = new File("src/main/java/org/example/file/AdminFile.json");

        List<Admin> admins = new ArrayList<>(Arrays.asList(
                new Admin("Administrador", "Administrador", 99, "admin", "admin", "Administrador@gmail.com", 123456789)
        ));

        serializerGson.serializer(admins, adminFile.getPath());
    }

    public static void initEmployee(){
        SerializerGson serializerGson = new SerializerGson<>();

        File employeeFile = new File("src/main/java/org/example/file/EmployeeFile.json");

        List<Employee> employees = new ArrayList<>(Arrays.asList(
                new Employee("Empleado", "Empleado",123456789, "empleado", "empleado", "Empleado@gmail.com",123456789)
        ));

        serializerGson.serializer(employees, employeeFile.getPath());
    }

    public static void initGuest(){
        SerializerGson serializerGson = new SerializerGson<>();

        File guestFile = new File("src/main/java/org/example/file/GuestFile.json");

        List<Guest> guests = new ArrayList<>(Arrays.asList(
                new Guest("Huesped", "Huesped", 123456789, "Huesped@gmail.com",123456789)
        ));

        serializerGson.serializer(guests, guestFile.getPath());
    }



}