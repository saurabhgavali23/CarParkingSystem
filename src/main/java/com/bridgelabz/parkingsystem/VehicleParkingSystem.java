package com.bridgelabz.parkingsystem;

import com.bridgelabz.parkingsystem.service.ParkingSlotNumberSystem;
import com.bridgelabz.parkingsystem.service.ParkingStatusNotifier;
import com.bridgelabz.parkingsystem.exception.ParkingSystemException;
import com.bridgelabz.parkingsystem.enumerate.ParkingSystemEnum;
import com.bridgelabz.parkingsystem.service.VehicleDetails;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class VehicleParkingSystem {

    Map<Integer, VehicleDetails> vehicleList;
    ParkingStatusNotifier parkingStatusNotifier = null;
    ParkingSlotNumberSystem parkingSlotNumSystem = null;

    public VehicleParkingSystem() {
        this.vehicleList = new HashMap<>();
        this.parkingStatusNotifier = new ParkingStatusNotifier();
        this.parkingSlotNumSystem = new ParkingSlotNumberSystem();
    }

    public boolean parkTheVehicle(VehicleDetails vehicle, ParkingSystemEnum.TypeOfVehicle driverStatus) throws ParkingSystemException {
        int slot = 0;
        if (isVehicleParked(vehicle) == true)
            throw new ParkingSystemException("Vehicle_Is_Already_Park");

        if (this.vehicleList.size() < parkingSlotNumSystem.parkingCapacity) {
            slot = driverStatus.getSlotNum(parkingSlotNumSystem);

            this.vehicleList.put(slot, vehicle);
            parkingSlotNumSystem.list = (LinkedList) parkingSlotNumSystem.obj[slot];
            parkingSlotNumSystem.list.add(this.vehicleList.get(slot));
            parkingStatusNotifier.setParkedVehicleDateAndTime(this.getTimeAndDate());
            return true;
        }
        parkingStatusNotifier.setParkingStatus(true);
        throw new ParkingSystemException("Parking_Slot_Is_Full");
    }

    public boolean unParkTheVehicle(VehicleDetails vehicle) throws ParkingSystemException {

        if (isVehicleParked(vehicle)) {
            int vehicleKey = this.getVehicleKey(vehicle);
            parkingSlotNumSystem.list = (LinkedList) parkingSlotNumSystem.obj[vehicleKey];
            if (parkingSlotNumSystem.list.contains(vehicle)) {
                parkingSlotNumSystem.list.remove(vehicle);
                parkingStatusNotifier.setParkingStatusForFreeSpaceToAirPortSecurity(false);
                return true;
            }
        }
        throw new ParkingSystemException("Vehicle_Is_Not_Found");
    }

    public boolean isVehicleParked(VehicleDetails vehicle) {

        try {
            int vehicleKey = this.getVehicleKey(vehicle);
            parkingSlotNumSystem.list = (LinkedList) parkingSlotNumSystem.obj[vehicleKey];
            if (parkingSlotNumSystem.list.contains(vehicle)) {
                return true;
            }
        } catch (ParkingSystemException e) {
        }
        return false;
    }

    public int getVehicleKey(VehicleDetails vehicle) throws ParkingSystemException {
        try {
            Integer vehicleKey = vehicleList.keySet()
                    .stream()
                    .filter(key -> vehicle.equals(vehicleList.get(key)))
                    .findFirst().get();
            return vehicleKey;
        } catch (NoSuchElementException e) {
            throw new ParkingSystemException("No_Vehicle_Found");
        }
    }

    public String getTimeAndDate() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy hh:mm:ss");
        return dateFormat.format(date);
    }

    public List<VehicleDetails> getVehicleByAttribute(String... vehicleAttribute) throws ParkingSystemException {
        try {
            List<VehicleDetails> list = vehicleList.entrySet()
                    .stream()
                    .filter(value -> {
                        for (String value1 : vehicleAttribute) {
                            if (vehicleList.get(value.getKey()).toString().contains(value1) == false)
                                return false;
                        }
                        return true;
                    })
                    .map(value -> value.getValue())
                    .collect(Collectors.toList());
            return list;
        } catch (IndexOutOfBoundsException i) {
            throw new ParkingSystemException("Vehicle_Not_Found");
        }
    }

    public void showList() {

        System.out.println("Capacity :" + parkingSlotNumSystem.parkingCapacity + "  Lots :" + parkingSlotNumSystem.totalSlot + "\n");

        for (int i = 0; i < parkingSlotNumSystem.obj.length; i++) {
            parkingSlotNumSystem.list = (LinkedList) parkingSlotNumSystem.obj[i];

            System.out.print("Slot No :" + (i + 1) + ":=>");
            System.out.print(parkingSlotNumSystem.list);
            System.out.println();
        }
    }
}