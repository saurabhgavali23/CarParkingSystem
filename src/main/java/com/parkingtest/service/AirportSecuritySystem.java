package com.parkingtest.service;

public class AirportSecuritySystem implements ParkingSystem {

    public static boolean isParking = false;

    public void parkingIsFull(boolean status) {
        this.isParking = status;
    }

    public boolean isParkingFull() {
        return this.isParking;
    }
}
