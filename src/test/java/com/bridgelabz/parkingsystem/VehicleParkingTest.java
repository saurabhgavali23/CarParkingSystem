package com.bridgelabz.parkingsystem;

import com.bridgelabz.parkingsystem.service.ParkingSlotNumberSystem;
import com.bridgelabz.parkingsystem.exception.ParkingSystemException;
import com.bridgelabz.parkingsystem.service.ParkingLotOwner;
import com.bridgelabz.parkingsystem.enumerate.ParkingSystemEnum;
import com.bridgelabz.parkingsystem.entity.VehicleDetails;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.bridgelabz.parkingsystem.service.AirportSecuritySystem;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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
    public void givenVehicle_whenIsPark_ShouldReturnTrue() {

        try {
            boolean isParked = parkingSystem.parkTheVehicle(vehicle[0], ParkingSystemEnum.TypeOfDriver.NORMAL_DRIVER);
            parkingSystem.parkTheVehicle(vehicle[1], ParkingSystemEnum.TypeOfDriver.NORMAL_DRIVER);
            Assert.assertTrue(isParked);
        } catch (ParkingSystemException e) {
        }
    }

    // Park The Same Vehicle Two Times
    @Test
    public void givenSameVehicle_whenIsNotPark_ShouldThrowException() {
        try {
            parkingSystem.parkTheVehicle(vehicle[0], ParkingSystemEnum.TypeOfDriver.NORMAL_DRIVER);
            parkingSystem.parkTheVehicle(vehicle[0], ParkingSystemEnum.TypeOfDriver.NORMAL_DRIVER);
        } catch (ParkingSystemException e) {
            Assert.assertEquals(ParkingSystemException.ExceptionType.VEHICLE_IS_ALREADY_PARKED, e.type);
        }
    }

    // UnPark The Car
    @Test
    public void givenParkedVehicle_whenIsUnParked_ShouldReturnTrue() {

        try {
            parkingSystem.parkTheVehicle(vehicle[1], ParkingSystemEnum.TypeOfDriver.NORMAL_DRIVER);
            boolean vehicle = parkingSystem.unParkTheVehicle(this.vehicle[1]);
            Assert.assertTrue(vehicle);
        } catch (ParkingSystemException e) {
        }
    }

    // UnPark The UnParked Vehicle
    @Test
    public void givenUnParkedVehicle_whenIsNotFound_ShouldThrowException() {
        try {
            parkingSystem.parkTheVehicle(this.vehicle[1], ParkingSystemEnum.TypeOfDriver.NORMAL_DRIVER);
            parkingSystem.unParkTheVehicle(this.vehicle[1]);
            parkingSystem.unParkTheVehicle(this.vehicle[1]);
        } catch (ParkingSystemException e) {
            Assert.assertEquals(ParkingSystemException.ExceptionType.VEHICLE_NOT_FOUND, e.type);
        }
    }

    // Check Vehicle Is Parked
    @Test
    public void givenVehicle_whenItsNotFound_itShouldReturnFalse() {

        try {
            parkingSystem.parkTheVehicle(vehicle[0], ParkingSystemEnum.TypeOfDriver.NORMAL_DRIVER);
            parkingSystem.isVehicleParked(new VehicleDetails());
        } catch (ParkingSystemException e) {
            Assert.assertEquals(ParkingSystemException.ExceptionType.VEHICLE_NOT_FOUND, e.type);
        }
    }

    // Parking Slot Is Full OR Not For Parking Owner
    @Test
    public void whenParkingLotFull_ShouldThrowException() {
        try {
            for (int i = 0; i < vehicle.length; i++)
                parkingSystem.parkTheVehicle(vehicle[i], ParkingSystemEnum.TypeOfDriver.NORMAL_DRIVER);
        } catch (ParkingSystemException e) {
            Assert.assertEquals(ParkingSystemException.ExceptionType.PARKING_SLOT_IS_FULL, e.type);
        }
    }

    // Parking Slot Is Full OR Not For Airport Security
    @Test
    public void whenParkingLotFull_ShouldKnowTheAirportSecurityAndThrowException() {
        try {
            for (int i = 0; i <= vehicle.length; i++)
                parkingSystem.parkTheVehicle(vehicle[i], ParkingSystemEnum.TypeOfDriver.NORMAL_DRIVER);
        } catch (ParkingSystemException e) {
            Assert.assertEquals(ParkingSystemException.ExceptionType.PARKING_SLOT_IS_FULL, e.type);
        }
    }

    // Parking Slot Has Space Again For Airport Security
    @Test
    public void whenUnParkTheCar_parkingLotHasSpaceAgain_ShouldKnowTheAirportSecurity() {
        try {
            parkingSystem.parkTheVehicle(vehicle[0], ParkingSystemEnum.TypeOfDriver.NORMAL_DRIVER);
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
            parkingSystem.parkTheVehicle(this.vehicle[0], ParkingSystemEnum.TypeOfDriver.NORMAL_DRIVER);
            int vehicleKey = parkingSystem.getVehicleKey(this.vehicle[0]);
            Assert.assertEquals(0, vehicleKey);
        } catch (ParkingSystemException e) {
        }
    }

    // Search The Car
    @Test
    public void givenVehicle_whenVehicleIsFound_ShouldReturnTrue() {
        try {
            parkingSystem.parkTheVehicle(vehicle[0], ParkingSystemEnum.TypeOfDriver.NORMAL_DRIVER);
            parkingSystem.parkTheVehicle(vehicle[1], ParkingSystemEnum.TypeOfDriver.NORMAL_DRIVER);
            boolean vehicleParked = parkingSystem.isVehicleParked(vehicle[1]);
            Assert.assertTrue(vehicleParked);
        } catch (ParkingSystemException e) {
        }
    }

    // Parked Car By Time
    @Test
    public void givenVehicleAndParkedTime_whenVehicleIsParked_ShouldReturnTrue() {
        try {
            parkingSystem.parkTheVehicle(vehicle[0], ParkingSystemEnum.TypeOfDriver.NORMAL_DRIVER);
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
            parkingSystem.parkTheVehicle(this.vehicle[0], ParkingSystemEnum.TypeOfDriver.NORMAL_DRIVER);
            int vehicleKey = parkingSystem.getVehicleKey(this.vehicle[0]);
            parkingSystem.parkTheVehicle(this.vehicle[1], ParkingSystemEnum.TypeOfDriver.NORMAL_DRIVER);
            int vehicleKey1 = parkingSystem.getVehicleKey(this.vehicle[1]);
            Assert.assertEquals(0, vehicleKey);
            Assert.assertEquals(5, vehicleKey1);
        } catch (ParkingSystemException e) {
        }
    }

    // Park The Handicap Vehicle In Nearest Place
    @Test
    public void givenHandicapDriverVehicle_whenVehicleParkedNearestPlace_ShouldReturnTrue() {
        try {
            boolean vehicle = parkingSystem.parkTheVehicle(this.vehicle[0], ParkingSystemEnum.TypeOfDriver.HANDICAP_DRIVER);
            parkingSystem.parkTheVehicle(this.vehicle[1], ParkingSystemEnum.TypeOfDriver.NORMAL_DRIVER);
            boolean vehicle1 = parkingSystem.parkTheVehicle(this.vehicle[2], ParkingSystemEnum.TypeOfDriver.HANDICAP_DRIVER);
            parkingSystem.parkTheVehicle(this.vehicle[3], ParkingSystemEnum.TypeOfDriver.NORMAL_DRIVER);
            Assert.assertEquals(vehicle, vehicle1);
        } catch (ParkingSystemException e) {
        }
    }

    // Park The Large Vehicle Where The Free Space Available
    @Test
    public void givenLargeVehicle_whenLargeVehicleParked_ItShouldReturnTrue() {
        try {
            parkingSystem.parkTheVehicle(this.vehicle[3], ParkingSystemEnum.TypeOfDriver.NORMAL_DRIVER);
            parkingSystem.parkTheVehicle(this.vehicle[1], ParkingSystemEnum.TypeOfDriver.NORMAL_DRIVER);
            boolean vehicle = parkingSystem.parkTheVehicle(this.vehicle[0], ParkingSystemEnum.TypeOfDriver.LARGE_CAR_DRIVER);
            boolean vehicle1 = parkingSystem.parkTheVehicle(this.vehicle[2], ParkingSystemEnum.TypeOfDriver.LARGE_CAR_DRIVER);
            Assert.assertEquals(vehicle, vehicle1);
        } catch (ParkingSystemException e) {
        }
    }

    // Park The Vehicle With White Color
    @Test
    public void givenVehicleWithWhiteColor_whenIsPark_ItShouldReturnTrue() {
        try {
            parkingSystem.parkTheVehicle(vehicle[1], ParkingSystemEnum.TypeOfDriver.NORMAL_DRIVER);
            parkingSystem.parkTheVehicle(vehicle[0], ParkingSystemEnum.TypeOfDriver.NORMAL_DRIVER);
            vehicle[0].color = "White";
            parkingSystem.parkTheVehicle(vehicle[2], ParkingSystemEnum.TypeOfDriver.NORMAL_DRIVER);
            vehicle[2].color = "White";
            List<VehicleDetails> list = parkingSystem.getVehicleByAttribute(ParkingSystemEnum.TypeOfAttribute.WHITE);
            Assert.assertEquals("White", list.get(0).color);
        } catch (ParkingSystemException e) {
        }
    }

    // Park The Vehicle With Wrong Color
    @Test
    public void givenVehicleWithOtherColor_whenIsNotFound_ShouldThrowException() {
        try {
            parkingSystem.parkTheVehicle(vehicle[1], ParkingSystemEnum.TypeOfDriver.NORMAL_DRIVER);
            parkingSystem.parkTheVehicle(vehicle[0], ParkingSystemEnum.TypeOfDriver.NORMAL_DRIVER);
            vehicle[0].color = "White";
            parkingSystem.getVehicleByAttribute(ParkingSystemEnum.TypeOfAttribute.RED);
        } catch (ParkingSystemException e) {
            Assert.assertEquals(ParkingSystemException.ExceptionType.ATRRIBUETE_TYPE_OF_VEHICLE_NOT_FOUND, e.type);
        }
    }

    // Park The Vehicle With blue Color, Num Plate, Name, Model
    @Test
    public void givenVehicleWithBlueColorAndToyotaModelName_whenIsPark_ShouldReturnTrue() {
        try {
            parkingSystem.parkTheVehicle(vehicle[0], ParkingSystemEnum.TypeOfDriver.NORMAL_DRIVER);
            parkingSystem.parkTheVehicle(vehicle[1], ParkingSystemEnum.TypeOfDriver.NORMAL_DRIVER);
            vehicle[1].color = "blue";
            vehicle[1].carModel = "toyota";
            vehicle[1].name = "John";
            vehicle[1].numPlate = "MH-12_AB-6251";
            List<VehicleDetails> list = parkingSystem.getVehicleByAttribute(ParkingSystemEnum.TypeOfAttribute.BLUE, ParkingSystemEnum.TypeOfAttribute.TOYOTA);
            Assert.assertEquals("blue", list.get(0).color);
            Assert.assertEquals("toyota", list.get(0).carModel);
        } catch (ParkingSystemException e) {
        }
    }

    // Park The Vehicle With blue Color, Num Plate, Name, Wrong Model
    @Test
    public void givenVehicleWithBlueColorAndOtherModelName_whenIsPark_ShouldThrowException() {
        try {
            parkingSystem.parkTheVehicle(vehicle[0], ParkingSystemEnum.TypeOfDriver.NORMAL_DRIVER);
            parkingSystem.parkTheVehicle(vehicle[1], ParkingSystemEnum.TypeOfDriver.NORMAL_DRIVER);
            vehicle[1].color = "Blue";
            vehicle[1].carModel = "Toyota";
            vehicle[1].name = "John";
            vehicle[1].numPlate = "MH-12_AB-6251";
            parkingSystem.getVehicleByAttribute(ParkingSystemEnum.TypeOfAttribute.BLUE, ParkingSystemEnum.TypeOfAttribute.SUZUKI);
        } catch (ParkingSystemException e) {
            Assert.assertEquals(ParkingSystemException.ExceptionType.ATRRIBUETE_TYPE_OF_VEHICLE_NOT_FOUND, e.type);
        }
    }

    // Park The Vehicle With Model Name BMW
    @Test
    public void givenVehicleWithModelNameBMW_whenIsPark_ShouldReturnTrue() {
        try {
            parkingSystem.parkTheVehicle(vehicle[0], ParkingSystemEnum.TypeOfDriver.NORMAL_DRIVER);
            vehicle[0].carModel = "BMW";
            parkingSystem.parkTheVehicle(vehicle[1], ParkingSystemEnum.TypeOfDriver.NORMAL_DRIVER);
            vehicle[1].carModel = "BMW";
            List<VehicleDetails> list = parkingSystem.getVehicleByAttribute(ParkingSystemEnum.TypeOfAttribute.BMW);
            Assert.assertEquals("BMW", list.get(0).carModel);
            Assert.assertEquals("BMW", list.get(1).carModel);
        } catch (ParkingSystemException e) {
        }
    }

    // Park The Vehicle With Wrong Model Name
    @Test
    public void givenVehicleWithWrongModelName_whenIsPark_ShouldThrowException() {
        try {
            parkingSystem.parkTheVehicle(vehicle[0], ParkingSystemEnum.TypeOfDriver.NORMAL_DRIVER);
            vehicle[0].carModel = "BMW";
            parkingSystem.parkTheVehicle(vehicle[1], ParkingSystemEnum.TypeOfDriver.NORMAL_DRIVER);
            vehicle[1].carModel = "BMW";
            parkingSystem.getVehicleByAttribute(ParkingSystemEnum.TypeOfAttribute.SUV);
        } catch (ParkingSystemException e) {
            Assert.assertEquals(ParkingSystemException.ExceptionType.ATRRIBUETE_TYPE_OF_VEHICLE_NOT_FOUND, e.type);
        }
    }

    // Find The Vehicle Was Parked Last 30 Minutes
    @Test
    public void givenVehicle_whenIsParkedLast30Minutes_ItShouldReturnTrue() {
        try {
            parkingSystem.parkTheVehicle(vehicle[0], ParkingSystemEnum.TypeOfDriver.NORMAL_DRIVER);
            vehicle[0].dateAndTime = LocalDateTime.now().minusMinutes(15);
            parkingSystem.parkTheVehicle(vehicle[1], ParkingSystemEnum.TypeOfDriver.NORMAL_DRIVER);
            vehicle[1].dateAndTime = LocalDateTime.now().minusMinutes(33);
            List<VehicleDetails> list = parkingSystem.getVehicleByLastTime(30);
            Assert.assertEquals(1, list.size());
        } catch (ParkingSystemException e) {
        }
    }

    // Find Handicap Vehicle From Lot Num
    @Test
    public void givenLotNumber_whenHandicapVehicleIsParked_ShouldReturnTrue() {
        try {
            for (int i = 0; i < 10; i++)
                parkingSystem.parkTheVehicle(vehicle[i], ParkingSystemEnum.TypeOfDriver.HANDICAP_DRIVER);
            parkingSystem.getVehicleDetailsByLotNum(0, 1);
            Assert.assertEquals(1, vehicle[5].lotNum);
        } catch (ParkingSystemException e) {
        }
    }

    // Get Parked Vehicle Details
    @Test
    public void givenVehicle_whenVehiclesAreParked_ShouldReturnTrue() {
        try {
            parkingSystem.parkTheVehicle(vehicle[0], ParkingSystemEnum.TypeOfDriver.NORMAL_DRIVER);
            vehicle[0].name="abc";
            vehicle[0].carModel="suzuki";
            vehicle[0].color="black";
            vehicle[0].numPlate="mh-12 vc_2333";
            parkingSystem.parkTheVehicle(vehicle[1], ParkingSystemEnum.TypeOfDriver.NORMAL_DRIVER);
            vehicle[0].name="xyz";
            vehicle[0].carModel="suv";
            vehicle[0].color="white";
            vehicle[0].numPlate="mh-12 vc_2933";
            Map<Integer, VehicleDetails> vehicleDetails = parkingSystem.getVehicleDetails();
            Assert.assertEquals(vehicleDetails.get(0),vehicle[0]);
        } catch (ParkingSystemException e) {
        }
    }
}
