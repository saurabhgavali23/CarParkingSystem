package carparkingsystem;

import java.util.*;

public class CarParkingSystem {

    int SLOT_SIZE = 100;
    Map<Integer, CarDetails> carPark = new HashMap<>();
    CarDetails carDetails = new CarDetails();

    public boolean parkTheCar(String numPlate, String model, String color) throws CarSystemException {

        if (SLOT_SIZE >= carPark.size()) {

            carPark.put(carDetails.getSlotNum(), new CarDetails(numPlate, model, color));

            return true;
        }
        throw new CarSystemException(CarSystemException.ExceptionType.PARKING_SLOT_FULL, "PARKING_SLOT_FULL");
    }

    public void getParkingDetail() {

        carPark.forEach((k, v) -> System.out.println("Slot No: " + k + " = " + v));
    }

    public boolean unParkTheCar(String numPlate) throws CarSystemException {

        for (Map.Entry<Integer, CarDetails> list : carPark.entrySet()) {
            if (numPlate.equals(list.getValue().getNumPlate())) {
                Integer key = list.getKey();
                carPark.remove(key);
                return true;
            }
        }
        throw new CarSystemException(CarSystemException.ExceptionType.DATA_NOT_FOUND, "DATA_NOT_FOUND");
    }
}
