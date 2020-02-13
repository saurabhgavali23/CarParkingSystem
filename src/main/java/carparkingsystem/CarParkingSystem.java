package carparkingsystem;

import java.util.*;

public class CarParkingSystem {

    List vehiclePark = new ArrayList();
    private Object vehicle;

    public boolean parkTheVehicle(Object vehicle) {

        this.vehicle = vehicle;
            return true;
    }

    public boolean unParkTheVehicle(Object vehicle) {

        if(this.vehicle == vehicle) {
            this.vehicle = null;
            return true;
        }
        return false;
    }
}
