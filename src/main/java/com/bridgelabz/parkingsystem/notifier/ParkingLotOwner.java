package com.bridgelabz.parkingsystem.notifier;

public class ParkingLotOwner implements ParkingSystem {

    public static boolean isParking = false;
    public static String parkedDateAndTime;

    public void parkingIsFull(boolean status) {
        this.isParking = status;
    }

    public boolean isParkingFull() {
        return this.isParking;
    }

    public void setDateAndTime(String timeAndDate) {
        this.parkedDateAndTime = timeAndDate;
    }

    public String getDateAndTime(){
        return parkedDateAndTime;
    }
}
