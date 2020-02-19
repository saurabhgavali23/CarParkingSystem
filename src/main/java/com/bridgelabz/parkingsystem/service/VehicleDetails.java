package com.bridgelabz.parkingsystem.service;

import com.bridgelabz.parkingsystem.VehicleParkingSystem;

public class VehicleDetails {

    VehicleParkingSystem parkingSystem = new VehicleParkingSystem();
    String dateAndTime = parkingSystem.getTimeAndDate();
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
