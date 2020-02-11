package carparkingsystem;

public class AirportSecuritySystem {

    boolean isParking;

    public void changeParkingStatus(boolean status) {
        this.isParking = status;
    }

    public boolean isParkingFull(){
        return this.isParking;
    }

}
