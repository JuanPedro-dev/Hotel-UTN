package org.example.entities;

import java.nio.ByteBuffer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import static java.time.temporal.ChronoUnit.DAYS;


public class Booking {

    /*[Atributos]*/

    private String bookingId ; // id unico de 12 caracteres
    private Passenger bookingPassenger;
    private Room bookedRoom;
    private BookingState bookingState;  // ir seteando de acuerdo a la fecha
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private double spentMoney;


    /*[Constructores]*/

    public Booking(Passenger bookingPassenger , Room bookedRoom, LocalDate checkInDate, LocalDate checkOutDate, BookingState bookingState) {
        this.bookingPassenger= bookingPassenger;
        this.bookedRoom = bookedRoom;
        this.bookingState = bookingState;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.bookingId= shortUUID();
        this.spentMoney= reservedDays(checkInDate, checkOutDate)* bookedRoom.getRoomType().getValue(); //inicia con el valor por noche de la habitacion multiplicado por la cantidad de dias que se hospeda
    }

    public Booking() {
    }

    /*[Métodos]*/

    // calcular los dias entre checkin y check out
    public static long reservedDays (LocalDate checkInDate, LocalDate checkOutDate){
        long daysBetween= DAYS.between(checkInDate,checkOutDate);
        return daysBetween;
    }


    public static String shortUUID() {
        UUID uuid = UUID.randomUUID();
        long l = ByteBuffer.wrap(uuid.toString().getBytes()).getLong();
        return Long.toString(l, Character.MAX_RADIX);
    }


    /*[Getters & Setters]*/

    public BookingState getBookingState() {
        return bookingState;
    }

    public void setBookingState(BookingState bookingState) {
        this.bookingState = bookingState;
    }

    public String getBookingId() {
        return bookingId;
    }

    public Passenger getBookingPassenger() {
        return bookingPassenger;
    }

    public void setBookingPassenger(Passenger bookingPassenger) {
        this.bookingPassenger = bookingPassenger;
    }

    public Room getBookedRoom() {
        return bookedRoom;
    }

    public void setBookedRoom(Room bookedRoom) {
        this.bookedRoom = bookedRoom;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public double getSpentMoney() {
        return spentMoney;
    }

    public void setSpentMoney(double spentMoney) {
        this.spentMoney = spentMoney;
    }

    @Override
    public String toString() {
        return "\t [Reserva]\n" +
                "Numero de reserva: [" + bookingId + "] \n" +
                "Pasajero autor de la reserva: " + bookingPassenger +" \n"+
                "Habitacion reservada:" + bookedRoom.toString() +"\n"+
                "Estado de la reserva: [" + bookingState +"] \n"+
                "Día de check in: [" + checkInDate +"]\n"+
                "Día de check out: [" + checkOutDate +"]\n"+
                "Dinero gastado: [" + spentMoney +"] \n" +
                "***";
    }
}