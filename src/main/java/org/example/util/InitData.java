package org.example.util;

import org.example.entity.Room;
import org.example.entity.enums.RoomType;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Si queremos que ya tenga datos ejecutar previamente esta clase
 */
public class InitData {
    public static void main(String[] args) {
        initRoom();
        // TODO repetir con los otros, crear un initClase NO Olvidar poner bien la ruta del archivo
    }

    public static void initRoom(){
        SerializerGson serializerGson = new SerializerGson<>();

        File fileUser = new File("src/main/java/org/example/file/RoomFile.json");

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
                new Room(true,303,RoomType.QUAD),
                new Room(true,304,RoomType.SINGLE),
                new Room(true,305,RoomType.TWIN)
        ));

        serializerGson.serializer(rooms, fileUser.getPath());
    }



}
