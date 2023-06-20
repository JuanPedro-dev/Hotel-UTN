package org.example.entity;

import java.nio.ByteBuffer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


public final class Ticket
{
    /* [Atributos] */

    private final String ID;
    private final LocalDate checkInDate;
    private final LocalDate checkOutDate;
    private final Room roomUsed;
    private final double moneyPaid;


    /* [Constructor] */


    public Ticket(LocalDate checkInDate, LocalDate checkOutDate, Room roomUsed, double moneyPaid)
    {
        this.ID = shortUUID();
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.roomUsed = roomUsed;
        this.moneyPaid = moneyPaid;
    }


    /* [Métodos] */

    public static String shortUUID() {
        UUID uuid = UUID.randomUUID();
        long l = ByteBuffer.wrap(uuid.toString().getBytes()).getLong();
        return Long.toString(l, Character.MAX_RADIX);
    }

    @Override
    public String toString(){
        return  "\t [Ticket N°" + ID +"] \n\n"+
                "Día de CheckIn: [" + checkInDate +"]\n"+
                "Día de CheckOut: [" + checkOutDate +"]\n"+
                "Habitación utilizada: [" + roomUsed +"]\n"+
                "Total: [" + moneyPaid +"]\n"+
                "***";
    }
}
