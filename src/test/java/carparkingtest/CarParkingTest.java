package carparkingtest;

import carparkingsystem.CarParkingSystem;
import org.junit.Assert;
import org.junit.Test;

public class CarParkingTest {

    @Test
    public void whenGivenCarDetails_IfItIsPark_ItShouldReturnTrue() {

        CarParkingSystem carParkingSystem = new CarParkingSystem();
        boolean parkOrNot = carParkingSystem.parkTheCar(1243, "maruti", "red");
        carParkingSystem.getParkingDetail();
        Assert.assertEquals(parkOrNot,true);
    }
}
