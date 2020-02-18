package com.parkingtest;

import com.bridgelabz.parkingsystem.exception.ParkingSystemException;
import com.bridgelabz.parkingsystem.notifier.ParkingLotOwner;
import com.bridgelabz.parkingsystem.service.VehicleParkingSystem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.bridgelabz.parkingsystem.notifier.AirportSecuritySystem;

public class VehicleParkingTest {

    VehicleParkingSystem parkingSystem = null;
    Object[] vehicle = new Object[11];

    @Before
    public void setUp() {
        parkingSystem = new VehicleParkingSystem(10, 2);
        for (int i = 0; i < vehicle.length; i++)
            vehicle[i] = new Object();
    }

    // Park The Vehicle
    @Test
    public void givenVehicle_whenIsPark_ItShouldReturnTrue() {

        try {
            boolean isParked = parkingSystem.parkTheVehicle(vehicle[0], null);
            Assert.assertTrue(isParked);
        } catch (ParkingSystemException e) {
        }
    }

    // Park The Same Vehicle Two Times
    @Test
    public void givenSameVehicle_whenIsNotPark_ItShouldThrowException() {
        try {
            parkingSystem.parkTheVehicle(vehicle[0], null);
            boolean isParked = parkingSystem.parkTheVehicle(vehicle[0], null);
            Assert.assertFalse(isParked);
        } catch (ParkingSystemException e) {
        }
    }

    // UnPark The Car
    @Test
    public void givenParkedVehicle_whenIsUnParked_itShouldReturnTrue() {

        try {
            parkingSystem.parkTheVehicle(vehicle[1], null);
            boolean vehicle = parkingSystem.unParkTheVehicle(this.vehicle[1]);
            Assert.assertTrue(vehicle);
        } catch (ParkingSystemException e) {
        }
    }

    // UnPark The UnParked Vehicle
    @Test
    public void givenUnParkedVehicle_whenIsNotFound_itShouldThrowException() {
        try {
            boolean vehicle = parkingSystem.unParkTheVehicle(this.vehicle[1]);
            Assert.assertFalse(vehicle);
        } catch (ParkingSystemException e) {
        }
    }

    // Check Vehicle Is Parked
    @Test
    public void givenVehicle_whenItsNotFound_itShouldReturnFalse() {

        try {
            parkingSystem.parkTheVehicle(vehicle[0], null);
            boolean vehicleParked = parkingSystem.isVehicleParked(new Object());
            Assert.assertFalse(vehicleParked);
        } catch (ParkingSystemException e) {
        }
    }

    // Parking Slot Is Full OR Not For Parking Owner
    @Test
    public void whenParkingLotFull_itShouldThrowException() {
        try {
            for (int i = 0; i <= vehicle.length; i++)
                parkingSystem.parkTheVehicle(vehicle[i], null);
        } catch (ParkingSystemException e) {
        }
        boolean parkingFull = new ParkingLotOwner().isParkingFull();
        Assert.assertTrue(parkingFull);
    }

    // Parking Slot Is Full OR Not For Airport Security
    @Test
    public void whenParkingLotFull_itShouldKnowTheAirportSecurity() {

        try {
            for (int i = 0; i <= vehicle.length; i++) {
                parkingSystem.parkTheVehicle(vehicle[i], null);
            }
        } catch (ParkingSystemException e) {
        }
        boolean parkingFull = new AirportSecuritySystem().isParkingFull();
        Assert.assertTrue(parkingFull);
    }

    // Parking Slot Has Space Again For Airport Security
    @Test
    public void whenUnParkTheCar_parkingLotHasSpaceAgain_itShouldKnowTheAirportSecurity() {

        try {
            parkingSystem.parkTheVehicle(vehicle[0], null);
            parkingSystem.unParkTheVehicle(vehicle[0]);
            boolean parkingFull = new AirportSecuritySystem().isParkingFull();
            Assert.assertFalse(parkingFull);
        } catch (ParkingSystemException e) {
        }
    }

    // Park The Car By Position
    @Test
    public void givenParkingPosition_whenVehicleIsParked_ShouldReturnTrue() {

        try {
            parkingSystem.parkTheVehicle(this.vehicle[0], null);
            int vehicleKey = parkingSystem.getVehicleKey(this.vehicle[0]);
            Assert.assertEquals(0,vehicleKey);
        } catch (ParkingSystemException e) {
        }
    }

    // Search The Car
    @Test
    public void givenVehicle_whenVehicleIsFound_ShouldReturnTrue() {

        try {
            parkingSystem.parkTheVehicle(vehicle[0], null);
            parkingSystem.parkTheVehicle(vehicle[1], null);
            boolean vehicleParked = parkingSystem.isVehicleParked(vehicle[1]);
            Assert.assertTrue(vehicleParked);
        } catch (ParkingSystemException e) {
        }
    }

    // Parked Car By Time
    @Test
    public void givenVehicleAndParkedTime_whenVehicleIsParked_ShouldReturnTrue() {

        try {
            parkingSystem.parkTheVehicle(vehicle[0], null);
            String timeAndDate = new VehicleParkingSystem().getTimeAndDate();
            parkingSystem.unParkTheVehicle(vehicle[0]);
            Assert.assertEquals(timeAndDate, new ParkingLotOwner().getDateAndTime());
        } catch (ParkingSystemException e) {
        }
    }

    // Park The Car Evenly In Lot
    @Test
    public void givenVehicleList_whenVehicleParkedEvenly_ShouldReturnTrue() {

        try {
            boolean vehicle = parkingSystem.parkTheVehicle(this.vehicle[0], null);
            boolean vehicle1 = parkingSystem.parkTheVehicle(this.vehicle[1], null);
            Assert.assertEquals(vehicle, vehicle1);
        } catch (ParkingSystemException e) {
        }
    }

    // Park The Handicap Vehicle In Nearest Problem
    @Test
    public void givenHandicapDriverVehicle_whenVehicleParkedNearestPlace_ShouldReturnTrue() {

        try {
            boolean vehicle = parkingSystem.parkTheVehicle(this.vehicle[0], true);
            parkingSystem.parkTheVehicle(this.vehicle[1], null);
            boolean vehicle1 = parkingSystem.parkTheVehicle(this.vehicle[2], true);
            parkingSystem.parkTheVehicle(this.vehicle[3], null);
            parkingSystem.parkTheVehicle(this.vehicle[4], null);
            Assert.assertEquals(vehicle, vehicle1);
        } catch (ParkingSystemException e) {
        }
    }
}
