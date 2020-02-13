package parkingsystem;

public class ParkingLotOwner {

    private static boolean isParking = false;

    public void parkingIsFull() {
        this.isParking = true;
    }

    public boolean isParkingFull() {
        return this.isParking;
    }
}
