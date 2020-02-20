package com.parkingtest.service;

import com.parkingtest.VehicleParkingSystem;

import java.time.LocalDateTime;

public class VehicleDetails {

    VehicleParkingSystem parkingSystem = new VehicleParkingSystem();
    public LocalDateTime dateAndTime = parkingSystem.getTimeAndDate();
    public String color;
    public String name;
    public String carModel;
    public String numPlate;

    public VehicleDetails() {
    }

    @Override
    public String toString() {
        return "VehicleDetails{" +
                "parkingSystem=" + parkingSystem +
                ", dateAndTime='" + dateAndTime + '\'' +
                ", color='" + color + '\'' +
                ", name='" + name + '\'' +
                ", carModel='" + carModel + '\'' +
                ", numPlate='" + numPlate + '\'' +
                '}';
    }
}
