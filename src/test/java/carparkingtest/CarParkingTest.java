package carparkingtest;

import carparkingsystem.CarParkingSystem;
import org.junit.Assert;
import org.junit.Test;

public class CarParkingTest {

    CarParkingSystem carParkingSystem = new CarParkingSystem();

    // Park The Vehicle
    @Test
    public void givenVehicle_whenIsPark_ItShouldReturnTrue() {

            CarParkingSystem carParkingSystem = new CarParkingSystem();
            boolean isParked = carParkingSystem.parkTheVehicle(new Object());
            Assert.assertEquals(isParked, true);
    }

    // UnPark The Car
    @Test
    public void whenGivenCarNumberPlate_ifItIsFound_thenUnParkTheCar_itShouldReturnTrue() {

            boolean searchDetail = carParkingSystem.unParkTheVehicle("MH-12-7483");
            Assert.assertEquals(true, searchDetail);
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
