package com.parkingtest.service;

import java.time.LocalDateTime;

public class ParkingLotOwner implements ParkingSystem {

    public static boolean isParking = false;
    public static LocalDateTime parkedDateAndTime;

    public void parkingIsFull(boolean status) {
        this.isParking = status;
    }

    public boolean isParkingFull() {
        return this.isParking;
    }

    public void setDateAndTime(LocalDateTime timeAndDate) {
        this.parkedDateAndTime = timeAndDate;
    }

    public LocalDateTime getDateAndTime() {
        return parkedDateAndTime;
    }
}
