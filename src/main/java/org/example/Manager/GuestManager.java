import java.util.ArrayList;

public class GuestManager {
    private ArrayList<Guest> passengerList;

    public GuestManager(ArrayList<Guest> passengerList) {
        this.passengerList = passengerList;
    }

    public void addPassenger(Guest passenger) {
        passengerList.add(passenger);
    }

    public void removePassenger(Guest passenger) {
        passengerList.remove(passenger);
    }

    public ArrayList<Guest> getPassengerList() {
        return passengerList;
    }
}
