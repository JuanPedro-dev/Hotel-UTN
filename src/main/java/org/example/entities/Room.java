package org.example.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Room {
    private ArrayList<Room> roomList;
    private HashSet<Booking> bookingList;
    private ArrayList<Passenger> passengerList;
    private ArrayList<Admin> adminList;
    private ArrayList<Employee> employeeList;

    /* [Constructores] */

    public Hotel()
    {
        this.bookingList = FileHelper.getBookingsFromJson();
        this.roomList = FileHelper.getRoomsFromJson();
        this.passengerList = FileHelper.getPassengersFromJson();
        this.adminList = FileHelper.getAdminFromJson();
        this.employeeList = FileHelper.getEmployeeFromJson();
    }

    public void save()
    {
        FileHelper.setPassengersToJson(this.passengerList);
        FileHelper.setAdminToJson(this.adminList);
        FileHelper.setEmployeeToJson(this.employeeList);
        FileHelper.setRoomsToJson(this.roomList);
        FileHelper.setBookingsToJson(this.bookingList);
    }

    /* [Métodos] */

    /*******************************Booking**********************************/
    public boolean removeBooking(Booking bookingDeleted)
    {
        if (bookingList != null) {
            for (Booking variable : bookingList) {
                if (bookingDeleted == variable) {
                    bookingList.remove(variable);

                    return true;
                }
            }
        }
        return false;
    }

    public boolean verifyRoom(LocalDate checkInRequested, LocalDate checkOutRequested, int roomNumber)
    {
        if (roomList != null) {
            if (bookingList.isEmpty()) {
                return true;
            }
            else {
                for (Booking bookingPreviouslyMade : bookingList) {
                    if (bookingPreviouslyMade.getBookedRoom().getRoomNumber() == roomNumber) {

                        if (checkInRequested.isAfter(bookingPreviouslyMade.getCheckInDate()) && checkInRequested.isBefore(bookingPreviouslyMade.getCheckOutDate())) {
                            return false;
                        }
                        else if (checkOutRequested.isAfter(bookingPreviouslyMade.getCheckInDate()) && checkOutRequested.isBefore(bookingPreviouslyMade.getCheckOutDate())) {
                            return false;
                        }
                        else if (checkInRequested.isEqual(bookingPreviouslyMade.getCheckInDate()) && checkOutRequested.isEqual(bookingPreviouslyMade.getCheckOutDate()))
                            return false;

                    }
                    return true;
                }
            }
        }
        return false;
    }

    public Booking makeBooking(LocalDate checkIn, LocalDate checkOut, Passenger bookingPassenger, int roomNumber)
    {
        Room roomBoked;

        if (verifyRoom(checkIn, checkOut, roomNumber)) {

            roomBoked = getRoom(roomNumber);

            Booking newBooking = new Booking(bookingPassenger, roomBoked, checkIn, checkOut, BookingState.PENDING);
            insertBooking(newBooking);

            return newBooking;
        }
        else
            System.out.println("Esa habitación no esta disponible \n");

        return null;
    }

    public List<Booking> getBookingByRoom(Room room)
    {

        if (bookingList != null) {
            int flag = 0;
            List<Booking> bookings = new ArrayList<>();

            for (Booking booking : bookingList) {

                if (booking.getBookedRoom().equals(room)) {  // consultar si hace falta sobreescribir el método equals y hashcode

                    flag = 1;
                    bookings.add(booking);
                }
            }

            if (flag != 0) {
                return bookings;
            }
            else
                return null;
        }
        return null;
    }

    public void insertBooking(Booking booking)
    {

        bookingList.add(booking);

    }

    //getBookingByDni - retorna List porque puede haber mas de un Booking realizado por una misma persona

    public Booking getBookingByDni(String wantedDni)
    {
        if (bookingList != null)
        {
            for (Booking variable : bookingList)
            {
                if (variable.getBookingPassenger().getDni().equals(wantedDni))
                {
                    return variable;
                }
            }
        }
        return null;
    }

    public boolean checkIn(Booking booking)
    {
        if (roomList != null) {
            for (Room variable : roomList) {
                if (booking.getBookedRoom().getRoomNumber() == variable.getRoomNumber()) {
                    if (booking.getBookedRoom().isAvailable()) {
                        booking.getBookedRoom().setAvailable(false);
                    } else booking.getBookedRoom().setAvailable(true);
                    booking.setBookingState(BookingState.INITIATED);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkOut(Booking booking)
    {
        if (roomList != null) {
            for (Room variable : roomList) {
                if (booking.getBookedRoom().getRoomNumber() == variable.getRoomNumber()) {
                    if (booking.getBookedRoom().isAvailable()) {
                        booking.getBookedRoom().setAvailable(false);
                    } else booking.getBookedRoom().setAvailable(true);
                    booking.setBookingState(BookingState.FINALIZED);
                    booking.getBookingPassenger().setHistory(getTicket(booking.getBookingPassenger().getDni()));

                    return true;

                }
            }
        }

        return false;
    }

    public Ticket getTicket(String dni)
    {
        if (this.bookingList != null) {

            for (Booking booking : bookingList) {

                if (booking.getBookingPassenger().getDni().equals(dni)) {

                    Ticket newTicket = new Ticket(booking.getCheckInDate(), booking.getCheckOutDate(), booking.getBookedRoom(), booking.getSpentMoney());

                    return newTicket;
                }
            }
        }
        return null;
    }

    /*******************************Passenger**********************************/
    public boolean addPassenger(Passenger newPassenger)
    {
        return passengerList.add(newPassenger);
    }

    public Passenger getPassengerbyDni(String dni)
    {
        if (passengerList != null) {
            for (Passenger user : passengerList) {
                if (user.getDni().equals(dni)) {
                    return user;
                }
            }
        }
        return null;
    }

    /*******************************Room**********************************/

    public void setProductToRoomService(int productChoice, Booking booking)
    {

        for (Product product : Product.values()) {

            if (product.ordinal() == productChoice) {

                booking.setSpentMoney(booking.getSpentMoney() + product.getValue());
            }
        }
    }


    public int getHotelSize()
    {
        if (roomList != null) {
            int roomCounter = 0;

            for (Room variable : roomList) {
                roomCounter++;
            }

            return roomCounter;
        }
        else
            return 0;
    }


    public Room getRoom(int roomNumber)
    {
        if (roomList != null) {
            for (Room variable : roomList) {
                if (variable.getRoomNumber() == roomNumber) {
                    return variable;
                }

            }

        }

        return null;
    }

    public void seeRooms ()
    {
        if (roomList != null)
        {
            for (Room variable : roomList)
            {
                System.out.println(variable.toString());
            }
        }
    }



    public void uploadRooms()
    {
        //ArrayList<Room> uploadedRooms = new ArrayList<>();

        Room newRoom = new Room(true,101,RoomType.TRIPLE);
        Room newRoom2 = new Room(true,102,RoomType.MATRIMONIAL);
        Room newRoom3 = new Room(true,103,RoomType.QUAD);
        Room newRoom4 = new Room(true,104,RoomType.SINGLE);
        Room newRoom5 = new Room(true,105,RoomType.TWIN);


        Room newRoom6 = new Room(true,201,RoomType.TRIPLE);
        Room newRoom7 = new Room(true,202,RoomType.MATRIMONIAL);
        Room newRoom8 = new Room(true,203,RoomType.QUAD);
        Room newRoom9 = new Room(true,204,RoomType.SINGLE);
        Room newRoom10 = new Room(true,205,RoomType.TWIN);


        Room newRoom11 = new Room(true,301,RoomType.TRIPLE);
        Room newRoom12 = new Room(true,302,RoomType.MATRIMONIAL);
        Room newRoom13 = new Room(true,303,RoomType.QUAD);
        Room newRoom14 = new Room(true,304,RoomType.SINGLE);
        Room newRoom15 = new Room(true,305,RoomType.TWIN);

        roomList.add(newRoom);
        roomList.add(newRoom2);
        roomList.add(newRoom3);
        roomList.add(newRoom4);
        roomList.add(newRoom5);
        roomList.add(newRoom6);
        roomList.add(newRoom7);
        roomList.add(newRoom8);
        roomList.add(newRoom9);
        roomList.add(newRoom10);
        roomList.add(newRoom11);
        roomList.add(newRoom12);
        roomList.add(newRoom13);
        roomList.add(newRoom14);
        roomList.add(newRoom15);

        FileHelper.setRoomsToJson(roomList);

    }

    public void uploadEmployee()
    {
        ArrayList<Employee> e = new ArrayList<>();
        Employee employee1 = new Employee("Maria Marta", "Serra Lima", "23535421", "lamari@gmail.com", "serra","lima",223458475);
        Employee employee2 = new Employee("Fernando", "Fernandez", "50232520", "fernandito@gmail.com", "fer","nando",22354214);
        e.add(employee1);
        e.add(employee2);
        employeeList = e;
        FileHelper.setEmployeeToJson(employeeList);
    }

    public void uploadAdmin(){

        ArrayList<Admin> a = new ArrayList<>();
        Admin admin = new Admin("Alex", "Turner", "84524135", "turner@gmail.com", "elalex","123",223458475);
        a.add(admin);
        adminList = a;
        FileHelper.setAdminToJson(adminList);
    }

    public void uploadPassenger(){
        ArrayList<Passenger> p = new ArrayList<>();
        Passenger passenger = new Passenger("Mercedez", "Sosa", "1256324","mercedita@gmail.com", "merced", "123", 52363648, "Chaco", "Buenos aires");
        p.add(passenger);
        passengerList = p;
        FileHelper.setPassengersToJson(passengerList);
    }

    /********************************Getters & Setters ***********************************/
    public ArrayList<Room> getRoomList()
    {
        return roomList;
    }

    public void setRoomList(ArrayList<Room> roomList)
    {
        this.roomList = roomList;
    }

    public HashSet<Booking> getBookingList()
    {
        return bookingList;
    }

    public void setBookingList(HashSet<Booking> bookingList)
    {
        this.bookingList = bookingList;
    }

    public ArrayList<Passenger> getPassengerList() {
        return passengerList;
    }

    public void setPassengerList(ArrayList<Passenger> passengerList) {
        this.passengerList = passengerList;
    }

    public ArrayList<Admin> getAdminList() {
        return adminList;
    }

    public void setAdminList(ArrayList<Admin> adminList) {
        this.adminList = adminList;
    }

    public ArrayList<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(ArrayList<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public boolean addEmployee(Employee newEmployee)
    {
        return employeeList.add(newEmployee);
    }

    public boolean addAdmin(Admin newAdmin)
    {
        return adminList.add(newAdmin);
    }
}
