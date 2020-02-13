package parkingsystem;

public class ParkingSystem {

    private final int parkingCapacity;
    private Object vehicle;
    private int currentCapacity;

    public ParkingSystem(int capacity) {
        this.parkingCapacity = capacity;
        this.currentCapacity = 0;
    }

    public boolean parkTheVehicle(Object vehicle) throws ParkingSystemException {

        if (this.currentCapacity != this.parkingCapacity) {
            this.vehicle = vehicle;
            this.currentCapacity++;
            return true;
        }
        new ParkingLotOwner().parkingIsFull();
        throw new ParkingSystemException("Parking_Slot_Is_Full");
    }

    public boolean unParkTheVehicle(Object vehicle) throws ParkingSystemException {

        if (this.vehicle == vehicle) {
            this.vehicle = null;
            return true;
        }
        throw new ParkingSystemException("Vehicle_Is_Not_Found");
    }

    public boolean isVehicleParked(Object vehicle) {

        if (this.vehicle != vehicle)
            return true;
        return false;
    }
}