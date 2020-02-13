package parkingtest;

import parkingsystem.AirportSecuritySystem;
import parkingsystem.ParkingLotOwner;
import parkingsystem.VehicleParkingSystem;
import parkingsystem.ParkingSystemException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingTest {

    VehicleParkingSystem parkingSystem = null;
    Object vehicle = null;

    @Before
    public void setUp() throws Exception {
        parkingSystem = new VehicleParkingSystem(1);
        vehicle = new Object();
    }

    // Park The Vehicle
    @Test
    public void givenVehicle_whenIsPark_ItShouldReturnTrue() {

        try {
            boolean isParked = parkingSystem.parkTheVehicle(vehicle);
            Assert.assertEquals(isParked, true);
        } catch (ParkingSystemException e) {
        }
    }

    // UnPark The Car
    @Test
    public void givenParkedVehicle_whenIsUnParked_itShouldReturnTrue() {

        try {
            parkingSystem.parkTheVehicle(vehicle);
            boolean vehicle = parkingSystem.unParkTheVehicle(this.vehicle);
            Assert.assertEquals(true, vehicle);
        } catch (ParkingSystemException e) {
        }
    }

    @Test
    public void givenVehicle_whenItsNotFound_itShouldReturnTrue() {

        try {
            parkingSystem.parkTheVehicle(vehicle);
            boolean vehicleParked = parkingSystem.isVehicleParked(new Object());
            Assert.assertTrue(vehicleParked);
        } catch (ParkingSystemException e) {
            e.printStackTrace();
        }
    }

    // Parking Slot Is Full OR Not For Parking Owner
    @Test
    public void whenParkingLotFull_itShouldThrowException() {

        try {
            boolean vehicle1 = parkingSystem.parkTheVehicle(vehicle);
            boolean vehicle2 = parkingSystem.parkTheVehicle(vehicle);
            Assert.assertTrue(vehicle1 && vehicle2);
        } catch (ParkingSystemException e) {
        }
        boolean parkingFull = new ParkingLotOwner().isParkingFull();
        Assert.assertTrue(parkingFull);
    }

    // Parking Slot Is Full OR Not For Airport Security
    @Test
    public void whenParkingLotFull_itShouldKnowTheAirportSecurity() {

        try {
            parkingSystem.parkTheVehicle(vehicle);
            parkingSystem.parkTheVehicle(vehicle);
        } catch (ParkingSystemException e) {
        }
        boolean parkingFull = new AirportSecuritySystem().isParkingFull();
        Assert.assertTrue(parkingFull);
    }

    // Parking Slot Has Space Again For Airport Security
    @Test
    public void whenUnParkTheCar_parkingLotHasSpaceAgain_itShouldKnowTheAirportSecurity() throws ParkingSystemException {

        parkingSystem.unParkTheVehicle("MH-12-7483");
    }
}
