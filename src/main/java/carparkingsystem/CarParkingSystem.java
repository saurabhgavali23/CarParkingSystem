package carparkingsystem;

import java.util.*;

public class CarParkingSystem {

    List vehiclePark = new ArrayList();
    private Object vehicle;

    public boolean parkTheVehicle(Object vehicle) {

        this.vehicle = vehicle;
            return true;
    }

    public boolean unParkTheVehicle(String numPlate) {

        int key = 0;
        if (key != 0) {
            vehiclePark.remove(key);
            new AirportSecuritySystem().changeParkingStatus(false);
            return true;
        }
        return false;
    }
}
