package carparkingsystem;

public class CarParkingSystem {

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

    public boolean isVehicleParked(Object vehicle) {

        if(this.vehicle != vehicle)
            return true;
        return false;
    }
}
