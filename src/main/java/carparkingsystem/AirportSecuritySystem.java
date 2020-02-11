package carparkingsystem;

public class AirportSecuritySystem {

    static boolean isParking;

    public static void changeParkingStatus(boolean status) {
        isParking = status;
    }

    public boolean isParkingFull() {
        return this.isParking;
    }
}
