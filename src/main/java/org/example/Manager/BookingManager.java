import java.util.HashSet;

public class BookingManager {
    private HashSet<Booking> bookingList;

    public BookingManager(HashSet<Booking> bookingList) {
        this.bookingList = bookingList;
    }

    public void addBooking(Booking booking) {
        bookingList.add(booking);
    }

    public void removeBooking(Booking booking) {
        bookingList.remove(booking);
    }

    public HashSet<Booking> getBookingList() {
        return bookingList;
    }
}

