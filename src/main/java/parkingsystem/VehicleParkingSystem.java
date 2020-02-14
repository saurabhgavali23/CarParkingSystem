package parkingsystem;

import java.util.*;

public class VehicleParkingSystem {

    private final int parkingCapacity;

    Map<Integer, Object> vehicleList;

    public VehicleParkingSystem(int capacity) {
        this.parkingCapacity = capacity;
        this.vehicleList = new HashMap<>();
    }

    public boolean parkTheVehicle(Object vehicle) throws ParkingSystemException {

        if (this.vehicleList.size() < this.parkingCapacity) {
            this.vehicleList.put(this.getTheParkingSlot(), vehicle);
            return true;
        }
        new ParkingLotOwner().parkingIsFull(true);
        new AirportSecuritySystem().parkingIsFull(true);
        throw new ParkingSystemException("Parking_Slot_Is_Full");
    }

    private int getTheParkingSlot() {
        Random random = new Random();
        return 1 + random.nextInt(100);
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
        if (this.vehicleList.containsValue(vehicle))
            return true;
        return false;
    }
}
