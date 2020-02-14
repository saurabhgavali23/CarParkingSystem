package parkingsystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VehicleParkingSystem {

    private final int parkingCapacity;

    ArrayList<Object> vehicleList;

    public VehicleParkingSystem(int capacity) {
        this.parkingCapacity = capacity;
        this.vehicleList = new ArrayList(Arrays.asList(new Object[capacity + 1]));

    }

    public boolean parkTheVehicle(Object vehicle, int... pos) throws ParkingSystemException {
        if (this.vehicleList.size() != this.parkingCapacity) {
            if (pos != null)
                this.vehicleList.add(pos[0], vehicle);
            this.vehicleList.add(vehicle);
            System.out.println(this.vehicleList);
            return true;
        }
        new ParkingLotOwner().parkingIsFull(true);
        new AirportSecuritySystem().parkingIsFull(true);
        throw new ParkingSystemException("Parking_Slot_Is_Full");
    }

    public boolean unParkTheVehicle(Object vehicle) throws ParkingSystemException {

        if (this.isVehicleParked(vehicle)) {
            this.vehicleList.remove(vehicle);
            new AirportSecuritySystem().parkingIsFull(false);
            return true;
        }
        throw new ParkingSystemException("Vehicle_Is_Not_Found");
    }

    public boolean isVehicleParked(Object vehicle) {

        if (this.vehicleList.contains(vehicle))
            return true;
        return false;
    }
}
