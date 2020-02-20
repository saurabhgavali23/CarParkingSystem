package com.parkingtest;

import com.parkingtest.service.ParkingSlotNumberSystem;
import com.parkingtest.exception.ParkingSystemException;
import com.parkingtest.service.ParkingLotOwner;
import com.parkingtest.enumerate.ParkingSystemEnum;
import com.parkingtest.service.VehicleDetails;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.parkingtest.service.AirportSecuritySystem;

import java.time.LocalDateTime;
import java.util.List;

public class VehicleParkingTest {

    VehicleParkingSystem parkingSystem = null;
    ParkingSlotNumberSystem parkingSlotNumSystem = null;
    VehicleDetails[] vehicle = new VehicleDetails[11];

    @Before
    public void setUp() {
        parkingSlotNumSystem = new ParkingSlotNumberSystem(10, 2);
        parkingSystem = new VehicleParkingSystem();
        for (int i = 0; i < vehicle.length; i++)
            vehicle[i] = new VehicleDetails();
    }

    // Park The Vehicle
    @Test
    public void givenVehicle_whenIsPark_ItShouldReturnTrue() {

        try {
            boolean isParked = parkingSystem.parkTheVehicle(vehicle[0], ParkingSystemEnum.TypeOfVehicle.ND);
            parkingSystem.parkTheVehicle(vehicle[1], ParkingSystemEnum.TypeOfVehicle.ND);
            Assert.assertTrue(isParked);
        } catch (ParkingSystemException e) {
        }
    }

    // Park The Same Vehicle Two Times
    @Test
    public void givenSameVehicle_whenIsNotPark_ItShouldThrowException() {
        try {
            parkingSystem.parkTheVehicle(vehicle[0], ParkingSystemEnum.TypeOfVehicle.ND);
            boolean isParked = parkingSystem.parkTheVehicle(vehicle[0], ParkingSystemEnum.TypeOfVehicle.ND);
            Assert.assertFalse(isParked);
        } catch (ParkingSystemException e) {
        }
    }

    // UnPark The Car
    @Test
    public void givenParkedVehicle_whenIsUnParked_itShouldReturnTrue() {

        try {
            parkingSystem.parkTheVehicle(vehicle[1], ParkingSystemEnum.TypeOfVehicle.ND);
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
            parkingSystem.parkTheVehicle(vehicle[0], ParkingSystemEnum.TypeOfVehicle.ND);
            boolean vehicleParked = parkingSystem.isVehicleParked(new VehicleDetails());
            Assert.assertFalse(vehicleParked);
        } catch (ParkingSystemException e) {
        }
    }

    // Parking Slot Is Full OR Not For Parking Owner
    @Test
    public void whenParkingLotFull_itShouldThrowException() {
        try {
            for (int i = 0; i < vehicle.length; i++)
                parkingSystem.parkTheVehicle(vehicle[i], ParkingSystemEnum.TypeOfVehicle.ND);
        } catch (ParkingSystemException e) {
        }
        boolean parkingFull = new ParkingLotOwner().isParkingFull();
        Assert.assertTrue(parkingFull);
    }

    // Parking Slot Is Full OR Not For Airport Security
    @Test
    public void whenParkingLotFull_itShouldKnowTheAirportSecurity() {
        try {
            for (int i = 0; i <= vehicle.length; i++)
                parkingSystem.parkTheVehicle(vehicle[i], ParkingSystemEnum.TypeOfVehicle.ND);
        } catch (ParkingSystemException e) {
        }
        boolean parkingFull = new AirportSecuritySystem().isParkingFull();
        Assert.assertTrue(parkingFull);
    }

    // Parking Slot Has Space Again For Airport Security
    @Test
    public void whenUnParkTheCar_parkingLotHasSpaceAgain_itShouldKnowTheAirportSecurity() {

        try {
            parkingSystem.parkTheVehicle(vehicle[0], ParkingSystemEnum.TypeOfVehicle.ND);
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
            parkingSystem.parkTheVehicle(this.vehicle[0], ParkingSystemEnum.TypeOfVehicle.ND);
            int vehicleKey = parkingSystem.getVehicleKey(this.vehicle[0]);
            Assert.assertEquals(0, vehicleKey);
        } catch (ParkingSystemException e) {
        }
    }

    // Search The Car
    @Test
    public void givenVehicle_whenVehicleIsFound_ShouldReturnTrue() {

        try {
            parkingSystem.parkTheVehicle(vehicle[0], ParkingSystemEnum.TypeOfVehicle.ND);
            parkingSystem.parkTheVehicle(vehicle[1], ParkingSystemEnum.TypeOfVehicle.ND);
            boolean vehicleParked = parkingSystem.isVehicleParked(vehicle[1]);
            Assert.assertTrue(vehicleParked);
        } catch (ParkingSystemException e) {
        }
    }

    // Parked Car By Time
    @Test
    public void givenVehicleAndParkedTime_whenVehicleIsParked_ShouldReturnTrue() {

        try {
            parkingSystem.parkTheVehicle(vehicle[0], ParkingSystemEnum.TypeOfVehicle.ND);
            LocalDateTime timeAndDate = new VehicleParkingSystem().getTimeAndDate();
            parkingSystem.unParkTheVehicle(vehicle[0]);
            Assert.assertEquals(timeAndDate, new ParkingLotOwner().getDateAndTime());
        } catch (ParkingSystemException e) {
        }
    }

    // Park The Car Evenly In Lot
    @Test
    public void givenVehicleList_whenVehicleParkedEvenly_ShouldReturnTrue() {

        try {
            parkingSystem.parkTheVehicle(this.vehicle[0], ParkingSystemEnum.TypeOfVehicle.ND);
            int vehicleKey = parkingSystem.getVehicleKey(this.vehicle[0]);
            parkingSystem.parkTheVehicle(this.vehicle[1], ParkingSystemEnum.TypeOfVehicle.ND);
            int vehicleKey1 = parkingSystem.getVehicleKey(this.vehicle[1]);
            Assert.assertEquals(0, vehicleKey);
            Assert.assertEquals(5, vehicleKey1);
        } catch (ParkingSystemException e) {
        }
    }

    // Park The Handicap Vehicle In Nearest Problem
    @Test
    public void givenHandicapDriverVehicle_whenVehicleParkedNearestPlace_ShouldReturnTrue() {

        try {
            boolean vehicle = parkingSystem.parkTheVehicle(this.vehicle[0], ParkingSystemEnum.TypeOfVehicle.HD);
            parkingSystem.parkTheVehicle(this.vehicle[1], ParkingSystemEnum.TypeOfVehicle.ND);
            boolean vehicle1 = parkingSystem.parkTheVehicle(this.vehicle[2], ParkingSystemEnum.TypeOfVehicle.HD);
            parkingSystem.parkTheVehicle(this.vehicle[3], ParkingSystemEnum.TypeOfVehicle.ND);
            Assert.assertEquals(vehicle, vehicle1);
        } catch (ParkingSystemException e) {
        }
    }

    // Park The Large Vehicle Where The Free Space Available
    @Test
    public void givenLargeVehicle_whenLargeVehicleParked_ItShouldReturnTrue() {
        try {
            parkingSystem.parkTheVehicle(this.vehicle[3], ParkingSystemEnum.TypeOfVehicle.ND);
            parkingSystem.parkTheVehicle(this.vehicle[1], ParkingSystemEnum.TypeOfVehicle.ND);
            boolean vehicle = parkingSystem.parkTheVehicle(this.vehicle[0], ParkingSystemEnum.TypeOfVehicle.LCD);
            boolean vehicle1 = parkingSystem.parkTheVehicle(this.vehicle[2], ParkingSystemEnum.TypeOfVehicle.LCD);
            Assert.assertEquals(vehicle, vehicle1);
        } catch (ParkingSystemException e) {
        }
    }

    // Park The Vehicle With White Color
    @Test
    public void givenVehicleWithWhiteColor_whenIsPark_ItShouldReturnTrue() {
        try {
            parkingSystem.parkTheVehicle(vehicle[1], ParkingSystemEnum.TypeOfVehicle.ND);
            parkingSystem.parkTheVehicle(vehicle[0], ParkingSystemEnum.TypeOfVehicle.ND);
            vehicle[0].color = "White";
            parkingSystem.parkTheVehicle(vehicle[2], ParkingSystemEnum.TypeOfVehicle.ND);
            vehicle[2].color = "white";
            List<VehicleDetails> list = parkingSystem.getVehicleByAttribute("white");
            Assert.assertEquals("white", list.get(0).color);
        } catch (ParkingSystemException e) {
        }
    }

    // Park The Vehicle With Wrong Color
    @Test
    public void givenVehicleWithOtherColor_whenIsNotFound_ItShouldThrowException() {
        List<VehicleDetails> list = null;
        try {
            parkingSystem.parkTheVehicle(vehicle[1], ParkingSystemEnum.TypeOfVehicle.ND);
            parkingSystem.parkTheVehicle(vehicle[0], ParkingSystemEnum.TypeOfVehicle.ND);
            vehicle[0].color = "white";
            list = parkingSystem.getVehicleByAttribute("Red");
        } catch (ParkingSystemException e) {
            Assert.assertEquals("white", list.get(0).color);
        }
    }

    // Park The Vehicle With blue Color, Num Plate, Name, Model
    @Test
    public void givenVehicleWithBlueColorAndToyotaModelName_whenIsPark_ItShouldReturnTrue() {
        try {
            parkingSystem.parkTheVehicle(vehicle[0], ParkingSystemEnum.TypeOfVehicle.ND);
            parkingSystem.parkTheVehicle(vehicle[1], ParkingSystemEnum.TypeOfVehicle.ND);
            vehicle[1].color = "blue";
            vehicle[1].carModel = "toyota";
            vehicle[1].name = "John";
            vehicle[1].numPlate = "MH-12_AB-6251";
            List<VehicleDetails> list = parkingSystem.getVehicleByAttribute("blue", "toyota");
            Assert.assertEquals("blue", list.get(0).color);
            Assert.assertEquals("toyota", list.get(0).carModel);
        } catch (ParkingSystemException e) {
        }
    }

    // Park The Vehicle With blue Color, Num Plate, Name, Wrong Model
    @Test
    public void givenVehicleWithBlueColorAndOtherModelName_whenIsPark_ItShouldReturnTrue() {
        List<VehicleDetails> list = null;
        try {
            parkingSystem.parkTheVehicle(vehicle[0], ParkingSystemEnum.TypeOfVehicle.ND);
            parkingSystem.parkTheVehicle(vehicle[1], ParkingSystemEnum.TypeOfVehicle.ND);
            vehicle[1].color = "Blue";
            vehicle[1].carModel = "Toyota";
            vehicle[1].name = "John";
            vehicle[1].numPlate = "MH-12_AB-6251";
            list = parkingSystem.getVehicleByAttribute("Blue", "Suzuki");
        } catch (ParkingSystemException e) {
            Assert.assertEquals("Blue", list.get(0).color);
            Assert.assertEquals("Toyota", list.get(0).carModel);
        }
    }

    // Park The Vehicle With Model Name BMW
    @Test
    public void givenVehicleWithModelNameBMW_whenIsPark_ItShouldReturnTrue() {
        try {
            parkingSystem.parkTheVehicle(vehicle[0], ParkingSystemEnum.TypeOfVehicle.ND);
            vehicle[0].carModel = "bmw";
            parkingSystem.parkTheVehicle(vehicle[1], ParkingSystemEnum.TypeOfVehicle.ND);
            vehicle[1].carModel = "bmw";
            List<VehicleDetails> list = parkingSystem.getVehicleByAttribute("bmw");
            Assert.assertEquals("bmw", list.get(0).carModel);
            Assert.assertEquals("bmw", list.get(1).carModel);
        } catch (ParkingSystemException e) {
        }
    }

    // Park The Vehicle With Wrong Model Name
    @Test
    public void givenVehicleWithWrongModelName_whenIsPark_ItShouldThrowException() {
        List<VehicleDetails> list = null;
        try {
            parkingSystem.parkTheVehicle(vehicle[0], ParkingSystemEnum.TypeOfVehicle.ND);
            vehicle[0].carModel = "BMW";
            parkingSystem.parkTheVehicle(vehicle[1], ParkingSystemEnum.TypeOfVehicle.ND);
            vehicle[1].carModel = "BMW";
            list = parkingSystem.getVehicleByAttribute("SUV");
        } catch (ParkingSystemException e) {
            Assert.assertEquals("BMW", list.get(0).carModel);
            Assert.assertEquals("BMW", list.get(1).carModel);
        }
    }

    // Find The Vehicle Was Parked Last 30 Minutes
    @Test
    public void givenVehicle_whenIsParkedLast30Minutes_ItShouldReturnTrue() {
        try {
            parkingSystem.parkTheVehicle(vehicle[0], ParkingSystemEnum.TypeOfVehicle.ND);
            parkingSystem.parkTheVehicle(vehicle[1], ParkingSystemEnum.TypeOfVehicle.ND);
            List<VehicleDetails> list = parkingSystem.getVehicleByLastTime(30);
            Assert.assertEquals(list.get(0).dateAndTime.minusMinutes(30), parkingSystem.getTimeAndDate().plusMonths(30));
        } catch (ParkingSystemException e) {
        }
    }
}
