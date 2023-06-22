package org.example.entity;

import org.example.entity.enums.BookingState;

import java.nio.ByteBuffer;
import java.time.LocalDate;
import java.util.UUID;

import static java.time.temporal.ChronoUnit.DAYS;

public class Booking {
//region [Atributos]
    private String bookingId ; // id unico de 12 caracteres
    private Guest guest;
    private Room room;
    private BookingState bookingState;  // ir seteando de acuerdo a la fecha
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private double spentMoney;

//endregion

//region [Constructores]


    public Booking(Guest guest, Room room, LocalDate checkInDate, LocalDate checkOutDate, BookingState bookingState) {
        this.guest = guest;
        this.room = room;
        this.bookingState = bookingState;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.bookingId= shortUUID();
        this.spentMoney= reservedDays(checkInDate, checkOutDate)* room.getRoomType().getValue(); //inicia con el valor por noche de la habitacion multiplicado por la cantidad de dias que se hospeda
    }

    public Booking() {
    }
//endregion

//region [Getters & Setters]


    public BookingState getBookingState() {
        return bookingState;
    }

    public void setBookingState(BookingState bookingState) {
        this.bookingState = bookingState;
    }

    public String getBookingId() {
        return bookingId;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
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
//endregion

//region [Métodos]

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

//endregion

//region Overrides
    @Override
    public String toString() {
        return
                "\n***** Reserva ***** "+
                "\nDatos: " +

                "\n\tCódigo de la reserva= " + bookingId +
                "\n\tHuesped= " + guest.getLastName() + " " + guest.getName() +
                "\n\tHabitación reservada= " + room.getRoomNumber() +
                "\n\tEstado de la reserva= " + bookingState +
                "\n\tDía de ingreso= " + checkInDate +
                "\n\tDía de egreso= " + checkOutDate +
                "\tDinero gastado= " + spentMoney +
                "\n*****\n";
    }
//endregion
}
