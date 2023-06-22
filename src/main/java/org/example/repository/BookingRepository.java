package org.example.repository;

import org.example.entity.Booking;
import org.example.exceptions.BookingExceptions;
import org.example.util.SerializerGson;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BookingRepository implements IRepository<Booking, String>{
    private final File file = new File("src/main/java/org/example/file/BookingFile.json");
    private final SerializerGson gson =new SerializerGson<>();
    private List<Booking> bookings;

    public BookingRepository() {
        readFromFile();
    }

    @Override
    public void readFromFile() {
        bookings = (List<Booking>) gson.deserializer(file.getPath(), Booking.class);
        if(bookings == null) bookings = new ArrayList<>();
    }

    @Override
    public void saveToFile() {
        gson.serializer(this.bookings, file.getPath());
    }

    @Override
    public void add(Booking obj) {
        Booking toAdd = getById(obj.getBookingId());

        if ( toAdd == null ) {
            this.bookings.add(obj);
            saveToFile();
        } else{
            throw new BookingExceptions("El libro ya existe!");
        }
    }

    @Override
    public List<Booking> list() {
        return this.bookings;
    }

    @Override
    public Booking getById( String dni ) {

        for ( Booking booking: this.bookings ) {
            if(booking.getBookingId().equals(dni)) return booking;
        }

        return null;
    }

    @Override
    public void update(Booking obj) {

        Booking toUpdate = getById(obj.getBookingId());

        if ( toUpdate == null ) {
            throw new BookingExceptions("El libro no Existe!");
        } else{
            for (Booking booking : this.bookings) {
                if(booking.getBookingId().equals(obj.getBookingId())){
                    int index = bookings.indexOf(booking);
                    bookings.set( index, obj );
                }
            }
        }

        saveToFile();
    }

    @Override
    public void delete(String dni) {
        this.bookings.removeIf(booking -> booking.getBookingId().equals(dni));
        saveToFile();
    }


}
