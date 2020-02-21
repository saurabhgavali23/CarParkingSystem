package com.bridgelabz.parkingsystem;

import com.bridgelabz.parkingsystem.enumerate.ParkingSystemEnum;
import com.bridgelabz.parkingsystem.service.ParkingSlotNumberSystem;
import com.bridgelabz.parkingsystem.service.ParkingStatusNotifier;
import com.bridgelabz.parkingsystem.exception.ParkingSystemException;
import com.bridgelabz.parkingsystem.entity.VehicleDetails;

import java.time.LocalDateTime;
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

    public boolean parkTheVehicle(VehicleDetails vehicle, ParkingSystemEnum.TypeOfDriver driverStatus) throws ParkingSystemException {
        int slot = 0;
        if (isVehicleParked(vehicle) == true)
            throw new ParkingSystemException(ParkingSystemException.ExceptionType.VEHICLE_IS_ALREADY_PARKED, "Vehicle_Is_Already_Parked");

        if (this.vehicleList.size() < parkingSlotNumSystem.parkingCapacity) {
            slot = driverStatus.getSlotNum(parkingSlotNumSystem);
            parkingSlotNumSystem.getLotNum(vehicle);
            this.vehicleList.put(slot, vehicle);
            parkingSlotNumSystem.list = (LinkedList) parkingSlotNumSystem.obj[slot];
            parkingSlotNumSystem.list.add(this.vehicleList.get(slot));
            parkingStatusNotifier.setParkedVehicleDateAndTime(this.getTimeAndDate());
            return true;
        }
        parkingStatusNotifier.setParkingStatus(true);
        throw new ParkingSystemException(ParkingSystemException.ExceptionType.PARKING_SLOT_IS_FULL, "Parking_Slot_Is_Full");
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
        throw new ParkingSystemException(ParkingSystemException.ExceptionType.VEHICLE_NOT_FOUND, "Vehicle_Is_Not_Found");
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
            throw new ParkingSystemException(ParkingSystemException.ExceptionType.VEHICLE_NOT_FOUND, "No_Vehicle_Found");
        }
    }

    public LocalDateTime getTimeAndDate() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime;
    }

    public List<VehicleDetails> getVehicleByAttribute(ParkingSystemEnum.TypeOfAttribute... vehicleAttribute) throws ParkingSystemException {
        try {
            List<VehicleDetails> list;
            list = vehicleList.entrySet()
                    .stream()
                    .filter(value -> this.isValuePresent(value, vehicleAttribute))
                    .map(value -> value.getValue())
                    .collect(Collectors.toList());
            return list;
        } catch (IndexOutOfBoundsException i) {
            throw new ParkingSystemException(ParkingSystemException.ExceptionType.ATRRIBUETE_TYPE_OF_VEHICLE_NOT_FOUND, "Vehicle_Not_Found");
        }
    }

    private boolean isValuePresent(Map.Entry<Integer, VehicleDetails> list, ParkingSystemEnum.TypeOfAttribute... vehicleAttribute) {
        for (ParkingSystemEnum.TypeOfAttribute value1 : vehicleAttribute) {
            if (list.toString().toUpperCase().contains(value1.toString()) == false)
                return false;
        }
        return true;
    }

    public List<VehicleDetails> getVehicleByLastTime(int minutes) throws ParkingSystemException {
        try {
            List<VehicleDetails> list;
            list = vehicleList.entrySet()
                    .stream()
                    .filter(value -> vehicleList.get(value.getKey()).dateAndTime.compareTo(LocalDateTime.now().minusMinutes(minutes)) >= 0)
                    .map(value -> value.getValue())
                    .collect(Collectors.toList());
            return list;
        } catch (IndexOutOfBoundsException i) {
            throw new ParkingSystemException(ParkingSystemException.ExceptionType.VEHICLE_NOT_FOUND, "Vehicle_Not_Found");
        }
    }

    public List<VehicleDetails> getVehicleDetailsByLotNum(Integer... lotNum) throws ParkingSystemException {
        try {
            List<VehicleDetails> list = vehicleList.entrySet()
                    .stream()
                    .filter(value -> this.isVehiclePresentInLot(value,lotNum))
                    .map(value -> value.getValue())
                    .collect(Collectors.toList());
            return list;
        } catch (IndexOutOfBoundsException i) {
            throw new ParkingSystemException(ParkingSystemException.ExceptionType.VEHICLE_NOT_FOUND, "Vehicle_Not_Found");
        }
    }

    private boolean isVehiclePresentInLot(Map.Entry<Integer, VehicleDetails> value, Integer... lotNum){
        for (Integer lotNums : lotNum)
            if (value.getValue().lotNum == lotNums)
                return true;
        return false;
    }

    public Map<Integer, VehicleDetails> getVehicleDetails() {
        return vehicleList;
    }
}