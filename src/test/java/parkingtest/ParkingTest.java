package parkingtest;

import parkingsystem.ParkingLotOwner;
import parkingsystem.ParkingSystem;
import parkingsystem.ParkingSystemException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingTest {

    ParkingSystem parkingSystem = null;
    Object vehicle = null;

    @Before
    public void setUp() throws Exception {
        parkingSystem = new ParkingSystem(1);
        vehicle = new Object();
    }

    // Park The Vehicle
    @Test
    public void givenVehicle_whenIsPark_ItShouldReturnTrue() {

        try {
            boolean isParked = parkingSystem.parkTheVehicle(vehicle);
            Assert.assertEquals(isParked, true);
        } catch (ParkingSystemException e) {
            e.printStackTrace();
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
            e.printStackTrace();
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

        for (int i = 1; i <= 101; i++) {
            try {
                boolean car = parkingSystem.parkTheVehicle(new Object());
            } catch (ParkingSystemException e) {
                e.printStackTrace();
            }
        }
    }

    // Parking Slot Has Space Again For Airport Security
    @Test
    public void whenUnParkTheCar_parkingLotHasSpaceAgain_itShouldKnowTheAirportSecurity() throws ParkingSystemException {

        parkingSystem.unParkTheVehicle("MH-12-7483");
    }
}
