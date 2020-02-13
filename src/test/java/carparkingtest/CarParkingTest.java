package carparkingtest;

import carparkingsystem.CarParkingSystem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CarParkingTest {

    CarParkingSystem carParkingSystem = null;
    Object vehicle = null;
    @Before
    public void setUp() throws Exception {
        carParkingSystem = new CarParkingSystem();
         vehicle = new Object();
    }

    // Park The Vehicle
    @Test
    public void givenVehicle_whenIsPark_ItShouldReturnTrue() {

            boolean isParked = carParkingSystem.parkTheVehicle(vehicle);
            Assert.assertEquals(isParked, true);
    }

    // UnPark The Car
    @Test
    public void givenParkedVehicle_whenIsUnParked_itShouldReturnTrue() {

        carParkingSystem.parkTheVehicle(vehicle);
        boolean vehicle = carParkingSystem.unParkTheVehicle(this.vehicle);
        Assert.assertEquals(true, vehicle);
    }

    @Test
    public void whenGivenCarNumberPlate_ifItIsNotFound_itShouldThrowException() {

            carParkingSystem.unParkTheVehicle("MH-12-7382");
    }

    // Parking Slot Is Full OR Not For Parking Owner
    @Test
    public void whenParkingLotFull_itShouldThrowException() {

            for (int i = 1; i <= 101; i++) {
                boolean car = carParkingSystem.parkTheVehicle(new Object());
            }
    }

    // Parking Slot Is Full OR Not For Airport Security
    @Test
    public void whenParkingLotFull_itShouldKnowTheAirportSecurity() {

            for (int i = 1; i <= 101; i++) {
                boolean car = carParkingSystem.parkTheVehicle(new Object());
            }
    }

    // Parking Slot Has Space Again For Airport Security
    @Test
    public void whenUnParkTheCar_parkingLotHasSpaceAgain_itShouldKnowTheAirportSecurity() {

            carParkingSystem.unParkTheVehicle("MH-12-7483");
    }
}
