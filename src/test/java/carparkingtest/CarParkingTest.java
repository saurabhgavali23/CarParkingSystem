package carparkingtest;

import carparkingsystem.CarParkingSystem;
import carparkingsystem.CarSystemException;
import org.junit.Assert;
import org.junit.Test;

public class CarParkingTest {

    // Park The Car
    @Test
    public void whenGivenCarDetails_IfItIsPark_ItShouldReturnTrue() {

        CarParkingSystem carParkingSystem = new CarParkingSystem();
        boolean car = carParkingSystem.parkTheCar("MH-12-2343", "Maruti", "red");
        Assert.assertEquals(car, true);
    }

    // UnPark The Car
    @Test
    public void whenGivenCarNumberPlate_ifItIsFound_thenUnParkTheCar_itShouldReturnTrue() {

        try {
            CarParkingSystem carParkingSystem = new CarParkingSystem();
            boolean car = carParkingSystem.parkTheCar("MH-12-2343", "Maruti", "red");
            boolean car1 = carParkingSystem.parkTheCar("MH-12-8382", "Suzuki", "black");
            boolean car2 = carParkingSystem.parkTheCar("MH-12-7483", "Honda", "silver");

            boolean searchDetail = carParkingSystem.unParkTheCar("MH-12-8382");
            Assert.assertEquals(true, searchDetail);
        } catch (CarSystemException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenGivenCarNumberPlate_ifItIsNotFound_itShouldThrowException() {

        try {
            CarParkingSystem carParkingSystem = new CarParkingSystem();
            boolean car = carParkingSystem.parkTheCar("MH-12-2343", "Maruti", "red");
            boolean car1 = carParkingSystem.parkTheCar("MH-12-8382", "Suzuki", "black");
            boolean car2 = carParkingSystem.parkTheCar("MH-12-7483", "Honda", "silver");

            carParkingSystem.unParkTheCar("MH-12-7382");
        } catch (CarSystemException e) {
            Assert.assertEquals("DATA_NOT_FOUND", e.getMessage());
        }
    }
}
