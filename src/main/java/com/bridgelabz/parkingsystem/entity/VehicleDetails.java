package com.bridgelabz.parkingsystem.entity;

import com.bridgelabz.parkingsystem.VehicleParkingSystem;

import java.time.LocalDateTime;

public class VehicleDetails {

    VehicleParkingSystem parkingSystem = new VehicleParkingSystem();
    public LocalDateTime dateAndTime = parkingSystem.getTimeAndDate();
    public String color;
    public String name;
    public String carModel;
    public String numPlate;
    public int lotNum;

    public VehicleDetails() {
    }

    @Override
    public String toString() {
        return "VehicleDetails{" +
                "parkingSystem=" + parkingSystem +
                ", dateAndTime=" + dateAndTime +
                ", color='" + color + '\'' +
                ", name='" + name + '\'' +
                ", carModel='" + carModel + '\'' +
                ", numPlate='" + numPlate + '\'' +
                ", lotNum='" + lotNum + '\'' +
                '}';
    }
}
