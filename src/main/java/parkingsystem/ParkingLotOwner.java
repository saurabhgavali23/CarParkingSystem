package parkingsystem;

public class ParkingLotOwner implements ParkingSystem{

    private static boolean isParking = false;

    public void parkingIsFull(boolean status) {
        this.isParking = status;
    }

    public boolean isParkingFull() {
        return this.isParking;
    }
}
