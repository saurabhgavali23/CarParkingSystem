package com.bridgelabz.parkingsystem.exception;

public class ParkingSystemException extends Exception {

    public ExceptionType type;

    public enum ExceptionType {
        VEHICLE_NOT_FOUND, VEHICLE_IS_ALREADY_PARKED, PARKING_SLOT_IS_FULL, ATRRIBUETE_TYPE_OF_VEHICLE_NOT_FOUND;
    }

    public ParkingSystemException(ExceptionType type,String message) {
        super(message);
        this.type = type;
    }
}
