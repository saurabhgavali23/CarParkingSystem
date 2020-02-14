package parkingsystem;

public class ParkingLotOwner implements ParkingSystem {

    private static boolean isParking = false;
    private static String parkedDateAndTime;

    public void parkingIsFull(boolean status) {
        this.isParking = status;
    }

    public boolean isParkingFull() {
        return this.isParking;
    }

    public void setDateAndTime(String timeAndDate) {
        this.parkedDateAndTime = timeAndDate;
    }

    public String getDateAndTime(){
        return parkedDateAndTime;
    }
}
