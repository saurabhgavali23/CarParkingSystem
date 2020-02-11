package carparkingtest;

import carparkingsystem.AirportSecuritySystem;
import carparkingsystem.CarParkingSystem;
import carparkingsystem.CarSystemException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CarParkingTest {

    CarParkingSystem carParkingSystem = new CarParkingSystem();

    @Before
    public void SetUp() {
        try {
            boolean car = carParkingSystem.parkTheCar("MH-12-2343", "Maruti", "red");
            boolean car1 = carParkingSystem.parkTheCar("MH-12-8382", "Suzuki", "black");
            boolean car2 = carParkingSystem.parkTheCar("MH-12-7483", "Honda", "silver");
        } catch (CarSystemException e) {
            e.printStackTrace();
        }
    }

    // Park The Car
    @Test
    public void whenGivenCarDetails_IfItIsPark_ItShouldReturnTrue() {

        try {
            CarParkingSystem carParkingSystem = new CarParkingSystem();
            boolean car = carParkingSystem.parkTheCar("MH-12-2343", "Maruti", "red");
            Assert.assertEquals(car, true);
        } catch (CarSystemException e) {
            e.printStackTrace();
        }
    }

    // UnPark The Car
    @Test
    public void whenGivenCarNumberPlate_ifItIsFound_thenUnParkTheCar_itShouldReturnTrue() {

        try {
            boolean searchDetail = carParkingSystem.unParkTheCar("MH-12-7483");
            Assert.assertEquals(true, searchDetail);
        } catch (CarSystemException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenGivenCarNumberPlate_ifItIsNotFound_itShouldThrowException() {

        try {
            carParkingSystem.unParkTheCar("MH-12-7382");
        } catch (CarSystemException e) {
            Assert.assertEquals(CarSystemException.ExceptionType.DATA_NOT_FOUND, e.type);
        }
    }

    // Parking Slot Is Full OR Not For Parking Owner
    @Test
    public void whenParkingLotFull_itShouldThrowException() {

        try {
            for (int i = 1; i <= 101; i++) {
                boolean car = carParkingSystem.parkTheCar(i + "MH-12-2343", i + "Maruti", i + "red");
            }
        } catch (CarSystemException e) {
            Assert.assertEquals(CarSystemException.ExceptionType.PARKING_SLOT_FULL, e.type);
        }
    }

    // Parking Slot Is Full OR Not For Airport Security
    @Test
    public void whenParkingLotFull_itShouldKnowTheAirportSecurity() {

        try {

            for (int i = 1; i <= 101; i++) {
                boolean car = carParkingSystem.parkTheCar(i + "MH-12-2343", i + "Maruti", i + "red");
            }
        } catch (CarSystemException e) {

            Assert.assertEquals(true, new AirportSecuritySystem().isParkingFull());
        }
    }

    // Parking Slot Has Space Again For Airport Security
    @Test
    public void whenUnParkTheCar_parkingLotHasSpaceAgain_itShouldKnowTheAirportSecurity() {

        try {
            carParkingSystem.unParkTheCar("MH-12-7483");
        } catch (CarSystemException e) {
            Assert.assertEquals(false, new AirportSecuritySystem().isParkingFull());
        }
    }
}
