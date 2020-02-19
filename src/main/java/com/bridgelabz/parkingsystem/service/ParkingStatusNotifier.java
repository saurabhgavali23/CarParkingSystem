package com.bridgelabz.parkingsystem.service;

import com.bridgelabz.parkingsystem.service.AirportSecuritySystem;
import com.bridgelabz.parkingsystem.service.ParkingLotOwner;

public class ParkingStatusNotifier {

    private ParkingLotOwner lotOwner = new ParkingLotOwner();
    private AirportSecuritySystem securitySystem = new AirportSecuritySystem();

    public void setParkingStatus(boolean status) {
        lotOwner.parkingIsFull(status);
        securitySystem.parkingIsFull(status);
    }

    public void setParkingStatusForFreeSpaceToAirPortSecurity(boolean status) {
        securitySystem.parkingIsFull(status);
    }

    public void setParkedVehicleDateAndTime(String dateAndTime) {
        lotOwner.setDateAndTime(dateAndTime);
    }
}
