package org.example.repository;

import org.example.entity.Guest;
import org.example.exceptions.GuestExceptions;
import org.example.util.SerializerGson;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GuestRepository implements IRepository<Guest, Integer> {
    private final File file = new File("src/main/java/org/example/file/GuestFile.json");
    private final SerializerGson gson = new SerializerGson<>();
    private List<Guest> guests;

    public GuestRepository() {
        readFromFile();
    }

    @Override
    public void readFromFile() {
        guests = (List<Guest>) gson.deserializer(file.getPath(), Guest.class);
        if (guests == null) guests = new ArrayList<>();
    }

    @Override
    public void saveToFile() {
        gson.serializer(this.guests, file.getPath());
    }

    @Override
    public void add( Guest obj ) {

        Guest toAdd = getById( obj.getDni() );

        if (toAdd == null) {
            this.guests.add( obj );
            saveToFile();
        } else {
            System.out.println("El Huesped ya existe!");
        }
    }

    @Override
    public List<Guest> list() {
        return this.guests;
    }

    @Override
    public Guest getById(Integer dni) {

        for (Guest guest : this.guests) {
            if (Objects.equals(guest.getDni(), dni)) return guest;
        }

        return null;
    }

    @Override
    public void update(Guest obj) {

        Guest toUpdate = getById( obj.getDni() );

        if (toUpdate == null) {
            throw new GuestExceptions("El huesped no Existe!");
        } else {
            for (Guest guest : this.guests) {
                if (guest.getDni().equals(obj.getDni())) {
                    int index = guests.indexOf(guest);
                    guests.set(index, obj);
                }
            }
        }
        saveToFile();
    }

    @Override
    public void delete(Integer dni) {
        if (this.guests.removeIf(guest -> guest.getDni().equals(dni))) {
            saveToFile();
            System.out.println("Eliminado exitosamente");
        } else {
            throw new GuestExceptions("No se pudo eliminar el huesped");
        }

    }

}