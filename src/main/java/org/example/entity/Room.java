package org.example.entity;


import org.example.entity.enums.RoomType;

public class Room {
    private boolean isAvailable;
    private Integer roomNumber;
    private RoomType roomType;

    public Room() {
    }

    public Room(boolean isAvailable, Integer roomNumber, RoomType roomType)
    {
        this.isAvailable = isAvailable;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    @Override
    public String toString() {
        return  "**********************************"+
                "\t [Habitación N°" + roomNumber +"] \n"+
                "Datos de la habitacion: \n" +
                "Disponibilidad: [" + isAvailable +"]\n"+
                "Tipo: [" + roomType + "]";
    }

}
