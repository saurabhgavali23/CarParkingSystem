package parkingtest;

import parkingsystem.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingTest {

    VehicleParkingSystem parkingSystem = null;
    Object[] vehicle = new Object[11];

    @Before
    public void setUp() throws Exception {
        parkingSystem = new VehicleParkingSystem(10, 2);
        for (int i = 0; i < vehicle.length; i++)
            vehicle[i] = new Object();
    }

    // Park The Vehicle
    @Test
    public void givenVehicle_whenIsPark_ItShouldReturnTrue() {

        try {
            boolean isParked = parkingSystem.parkTheVehicle(vehicle[0]);
            Assert.assertTrue(isParked);
        } catch (ParkingSystemException e) {
        }
    }

    // UnPark The Car
    @Test
    public void givenParkedVehicle_whenIsUnParked_itShouldReturnTrue() {

        try {
            parkingSystem.parkTheVehicle(vehicle[1]);
            boolean vehicle = parkingSystem.unParkTheVehicle(this.vehicle[1]);
            Assert.assertTrue(vehicle);
        } catch (ParkingSystemException e) {
        }
    }

    @Test
    public void givenVehicle_whenItsNotFound_itShouldReturnFalse() {

        try {
            parkingSystem.parkTheVehicle(vehicle);
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
                parkingSystem.parkTheVehicle(vehicle[i]);
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
                parkingSystem.parkTheVehicle(vehicle[i]);
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
            parkingSystem.parkTheVehicle(vehicle[0]);
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
            parkingSystem.parkTheVehicle(vehicle[0]);
            boolean parkingFull = new AirportSecuritySystem().isParkingFull();
            Assert.assertFalse(parkingFull);
        } catch (ParkingSystemException e) {
        }
    }

    // Search The Car
    @Test
    public void givenVehicle_whenVehicleIsFound_ShouldReturnTrue() {

        try {
            parkingSystem.parkTheVehicle(vehicle[0]);
            parkingSystem.parkTheVehicle(vehicle[1]);
            boolean vehicleParked = parkingSystem.isVehicleParked(vehicle[1]);
            Assert.assertTrue(vehicleParked);
        } catch (ParkingSystemException e) {
        }
    }

    // Parked Car By Time
    @Test
    public void givenVehicleAndParkedTime_whenVehicleIsParked_ShouldReturnTrue() {

        try {
            parkingSystem.parkTheVehicle(vehicle[0]);
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
            boolean vehicle = parkingSystem.parkTheVehicle(this.vehicle[0]);
            boolean vehicle1 = parkingSystem.parkTheVehicle(this.vehicle[1]);
            Assert.assertEquals(vehicle, vehicle1);
        } catch (ParkingSystemException e) {
        }
    }
}
