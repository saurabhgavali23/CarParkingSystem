package parkingsystem;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class VehicleParkingSystem {

    private int parkingCapacity;

    Map<Integer, Object> vehicleList;

    public VehicleParkingSystem(int capacity) {
        this.parkingCapacity = capacity;
        this.vehicleList = new HashMap<>();
    }

    public VehicleParkingSystem() { }

    public boolean parkTheVehicle(Object vehicle) throws ParkingSystemException {

        if (this.vehicleList.size() < this.parkingCapacity) {
            this.vehicleList.put(this.getTheParkingSlot(), (vehicle+" "+this.getTimeAndDate()));
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
            new ParkingLotOwner().setDateAndTime(this.getTimeAndDate());
            new AirportSecuritySystem().parkingIsFull(false);
            return true;
        }
        throw new ParkingSystemException("Vehicle_Is_Not_Found");
    }

    public boolean isVehicleParked(Object vehicle) {
        if (this.vehicleList.containsValue(vehicle+" "+this.getTimeAndDate()))
            return true;
        return false;
    }

    public String getTimeAndDate(){
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy hh:mm:ss");
        return dateFormat.format(date);
    }
}
