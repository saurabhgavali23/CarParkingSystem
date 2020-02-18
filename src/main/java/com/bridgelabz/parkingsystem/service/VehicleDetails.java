package com.bridgelabz.parkingsystem.service;

public class VehicleDetails {

    VehicleParkingSystem parkingSystem = new VehicleParkingSystem();
    public String color;
    String dateAndTime = parkingSystem.getTimeAndDate();;
    public VehicleDetails(String color) {
        this.color = color;
    }

    public VehicleDetails() {
    }

    @Override
    public String toString() {
        return "VehicleDetails{" +
                "color='" + color + '\'' +
                ", dateAndTime='" + dateAndTime + '\'' +
                '}';
    }
}
