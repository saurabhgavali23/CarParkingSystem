package com.bridgelabz.parkingsystem.service;

import com.bridgelabz.parkingsystem.notifier.ParkingStatusNotifier;
import com.bridgelabz.parkingsystem.exception.ParkingSystemException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class VehicleParkingSystem {

    private int totalSlot;
    private int parkingCapacity;
    private int i = 0;
    private int j = 0;
    private int count = 0;
    Map<Integer, Object> vehicleList;
    Object obj[] = null;
    ParkingStatusNotifier parkingStatusNotifier = null;

    public VehicleParkingSystem(int capacity, int totalSlot) {
        this.parkingCapacity = capacity;
        this.totalSlot = totalSlot;
        this.vehicleList = new HashMap<>();
        this.parkingStatusNotifier = new ParkingStatusNotifier();

        obj = new Object[this.parkingCapacity];
        for (int i = 0; i < this.parkingCapacity; i++) {
            obj[i] = new LinkedList();
        }
    }

    public VehicleParkingSystem() {
    }

    public boolean parkTheVehicle(Object vehicle, Boolean... driverStatus) throws ParkingSystemException {
        int slot = 0;
        if (isVehicleParked(vehicle) == true)
            throw new ParkingSystemException("Vehicle_Is_Already_Park");

        if (this.vehicleList.size() < this.parkingCapacity) {
            if (driverStatus != null)
                slot = this.getSlotNoForHandicapDriver();

            if (driverStatus == null)
                slot = this.getTheParkingSlot();

            this.vehicleList.put(slot, vehicle);
            LinkedList list = (LinkedList) obj[slot];
            list.add(this.vehicleList.get(slot));
            return true;
        }
        parkingStatusNotifier.setParkingStatus(true);
        throw new ParkingSystemException("Parking_Slot_Is_Full");
    }

    private int getSlotNoForHandicapDriver() {

        for (int i = 0; i < parkingCapacity; i++) {
            LinkedList list = (LinkedList) obj[i];
            if (list.isEmpty()) {
                int slotNo = i;
                return slotNo;
            }
        }
        return 0;
    }

    private int getTheParkingSlot() {
        int slotNo;

        if (count == totalSlot) {
            i = i + 1;
            j = 0;
            count = 0;
        }
        slotNo = i + ((parkingCapacity / totalSlot) * j);
        LinkedList list = (LinkedList) obj[slotNo];
        if (list.isEmpty())
            return slotNo;
        j = j + 1;
        count = count + 1;
        slotNo = this.getTheParkingSlot();
        return slotNo;
    }

    public boolean unParkTheVehicle(Object vehicle) throws ParkingSystemException {

        if (isVehicleParked(vehicle)) {
            int vehicleKey = this.getVehicleKey(vehicle);
            LinkedList list = (LinkedList) obj[vehicleKey];
            if (list.contains(vehicle)) {
                list.remove(vehicle);
                parkingStatusNotifier.setParkingStatusForFreeSpaceToAirPortSecurity(false);
                parkingStatusNotifier.setUnParkedVehicleDateAndTime(this.getTimeAndDate());
                return true;
            }
        }
        throw new ParkingSystemException("Vehicle_Is_Not_Found");
    }

    public boolean isVehicleParked(Object vehicle) {

        try {
            int vehicleKey = this.getVehicleKey(vehicle);
            LinkedList list = (LinkedList) obj[vehicleKey];
            if (list.contains(vehicle)) {
                return true;
            }
        } catch (ParkingSystemException e) {
        }
        return false;
    }

    public int getVehicleKey(Object vehicle) throws ParkingSystemException {
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

    public void showList() {

        System.out.println("Capacity :" + parkingCapacity + "  Lots :" + totalSlot + "\n");

        for (int i = 0; i < obj.length; i++) {
            LinkedList list = (LinkedList) obj[i];

            System.out.print("Slot No :" + (i + 1) + ":=>");
            System.out.print(list);
            System.out.println();
        }
    }
}