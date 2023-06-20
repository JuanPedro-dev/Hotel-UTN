package org.example.manager;

import java.util.ArrayList;

public class RoomManager {
    private ArrayList<Room> roomList;

    public RoomManager(ArrayList<Room> roomList) {
        this.roomList = roomList;
    }

    public void addRoom(Room room) {
        roomList.add(room);
    }

    public void removeRoom(Room room) {
        roomList.remove(room);
    }

    public ArrayList<Room> getRoomList() {
        return roomList;
    }
}