package carparkingtest;

import carparkingsystem.CarParkingSystem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CarParkingTest {

    CarParkingSystem parkingSystem = null;
    Object vehicle = null;

    @Before
    public void setUp() throws Exception {
        parkingSystem = new CarParkingSystem();
        vehicle = new Object();
    }

    // Park The Vehicle
    @Test
    public void givenVehicle_whenIsPark_ItShouldReturnTrue() {

        boolean isParked = parkingSystem.parkTheVehicle(vehicle);
        Assert.assertEquals(isParked, true);
    }

    // UnPark The Car
    @Test
    public void givenParkedVehicle_whenIsUnParked_itShouldReturnTrue() {

        parkingSystem.parkTheVehicle(vehicle);
        boolean vehicle = parkingSystem.unParkTheVehicle(this.vehicle);
        Assert.assertEquals(true, vehicle);
    }

    @Test
    public void givenVehicle_whenItsNotFound_itShouldReturnTrue() {

        parkingSystem.parkTheVehicle(vehicle);
        boolean vehicleParked = parkingSystem.isVehicleParked(new Object());
        Assert.assertTrue(vehicleParked);
    }


    // Parking Slot Is Full OR Not For Parking Owner
    @Test
    public void whenParkingLotFull_itShouldThrowException() {

        for (int i = 1; i <= 101; i++) {
            boolean car = parkingSystem.parkTheVehicle(new Object());
        }
    }

    // Parking Slot Is Full OR Not For Airport Security
    @Test
    public void whenParkingLotFull_itShouldKnowTheAirportSecurity() {

        for (int i = 1; i <= 101; i++) {
            boolean car = parkingSystem.parkTheVehicle(new Object());
        }
    }

    // Parking Slot Has Space Again For Airport Security
    @Test
    public void whenUnParkTheCar_parkingLotHasSpaceAgain_itShouldKnowTheAirportSecurity() {

        parkingSystem.unParkTheVehicle("MH-12-7483");
    }
}
