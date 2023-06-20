import java.util.ArrayList;
import java.util.HashSet;

public class Hotel {
    private RoomManager roomManager;
    private BookingManager bookingManager;
    private GuestManager guestManager;
    private AdminManager adminManager;
    private EmployeeManager employeeManager;

    public Hotel() {
        // Inicializar los gestores con las listas vacías
        this.roomManager = new RoomManager(new ArrayList<>());
        this.bookingManager = new BookingManager(new HashSet<>());
        this.guestManager = new GuestManager(new ArrayList<>());
        this.adminManager = new AdminManager(new ArrayList<>());
        this.employeeManager = new EmployeeManager(new ArrayList<>());
    }

    public RoomManager getRoomManager() {
        return roomManager;
    }

    public BookingManager getBookingManager() {
        return bookingManager;
    }

    public GuestManager getGuestManager() {
        return guestManager;
    }

    public AdminManager getAdminManager() {
        return adminManager;
    }

    public EmployeeManager getEmployeeManager() {
        return employeeManager;
    }
}