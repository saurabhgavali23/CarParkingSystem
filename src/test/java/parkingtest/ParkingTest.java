package parkingtest;

import parkingsystem.AirportSecuritySystem;
import parkingsystem.ParkingLotOwner;
import parkingsystem.VehicleParkingSystem;
import parkingsystem.ParkingSystemException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingTest {

    int capacity;
    VehicleParkingSystem parkingSystem = null;
    Object[] vehicle = null;

    @Before
    public void setUp() throws Exception {
        capacity = 2;
        parkingSystem = new VehicleParkingSystem(capacity);
        vehicle = new Object[capacity];
        for (int i = 0; i < capacity; i++)
            vehicle[i] = new Object();
    }

    // Park The Vehicle
    @Test
    public void givenVehicle_whenIsPark_ItShouldReturnTrue() {

        try {
            boolean isParked = parkingSystem.parkTheVehicle(vehicle[0], null);
            Assert.assertEquals(isParked, true);
        } catch (ParkingSystemException e) {
        }
    }

    // UnPark The Car
    @Test
    public void givenParkedVehicle_whenIsUnParked_itShouldReturnTrue() {

        try {
            parkingSystem.parkTheVehicle(vehicle, null);
            boolean vehicle = parkingSystem.unParkTheVehicle(this.vehicle[1]);
            Assert.assertEquals(true, vehicle);
        } catch (ParkingSystemException e) {
        }
    }

    @Test
    public void givenVehicle_whenItsNotFound_itShouldReturnFalse() {

        try {
            parkingSystem.parkTheVehicle(vehicle, null);
            boolean vehicleParked = parkingSystem.isVehicleParked(new Object());
            Assert.assertFalse(vehicleParked);
        } catch (ParkingSystemException e) {
        }
    }

    // Parking Slot Is Full OR Not For Parking Owner
    @Test
    public void whenParkingLotFull_itShouldThrowException() {
        boolean vehicle1 = false;
        try {
            for (int i = 0; i < capacity; i++) {
                vehicle1 = parkingSystem.parkTheVehicle(vehicle[i], null);
            }
            boolean vehicle2 = parkingSystem.parkTheVehicle(vehicle, null);
            Assert.assertTrue(vehicle1 && vehicle2);
        } catch (ParkingSystemException e) {
        }
        boolean parkingFull = new ParkingLotOwner().isParkingFull();
        Assert.assertTrue(parkingFull);
    }

    // Parking Slot Is Full OR Not For Airport Security
    @Test
    public void whenParkingLotFull_itShouldKnowTheAirportSecurity() {

        boolean vehicle1 = false;
        try {
            for (int i = 0; i < capacity; i++) {
                vehicle1 = parkingSystem.parkTheVehicle(vehicle[i], null);
            }
            boolean vehicle2 = parkingSystem.parkTheVehicle(vehicle, null);
            Assert.assertTrue(vehicle1 && vehicle2);
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
            parkingSystem.parkTheVehicle(vehicle[0], 1);
            parkingSystem.unParkTheVehicle(vehicle[0]);
            boolean parkingFull = new AirportSecuritySystem().isParkingFull();
            Assert.assertFalse(parkingFull);
        } catch (ParkingSystemException e) {
        }
    }
}
