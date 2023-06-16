package org.example.repository;

import org.example.entity.Room;
import org.example.exceptions.RoomExceptions;
import org.example.util.SerializerGson;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RoomRepository implements IRepository{

    private final File file = new File("src/main/java/org/example/file/RoomFile.json");
    private final SerializerGson gson = new SerializerGson<>();
    private List<Room> rooms;

    public RoomRepository() {
        readFromFile();
    }

    @Override
    public void readFromFile() {
        rooms = (List<Room>) gson.deserializer(file.getPath(), Room.class);
        if (rooms == null) rooms = new ArrayList<>();
    }

    @Override
    public void saveToFile() {
        gson.serializer(this.rooms, file.getPath());
    }

    @Override
    public void add(Room obj) {
        Room toAdd = getById(obj.getDni());

        if (toAdd == null) {
            this.rooms.add(obj);
            saveToFile();
        } else {
            throw new RoomExceptions("La Huesped ya existe!");
        }
    }

    @Override
    public List<Room> list() {
        return this.rooms;
    }

    @Override
    public Room getById(Integer dni) {

        for (Room room : this.rooms) {
            if (room.getDni().equals(dni)) return room;
        }

        return null;
    }

    @Override
    public void update(Room obj) {

        Room toUpdate = getById(obj.getDni());

        if (toUpdate == null) {
            throw new RoomExceptions("La habitación no Existe!");
        } else {
            for (Room room : this.rooms) {
                if (room.getDni().equals(obj.getDni())) {
                    int index = rooms.indexOf(room);
                    rooms.set(index, obj);
                }
            }
        }
        saveToFile();
    }

    @Override
    public void delete(Integer dni) {
        this.rooms.removeIf(room -> room.getDni().equals(dni));
    }

}