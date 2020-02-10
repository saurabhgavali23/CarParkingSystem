package carparkingsystem;

import java.util.*;

public class CarParkingSystem {

    int slotNo = 1;
    Map<Integer, CarDetails> carPark = new HashMap<>();

    public boolean parkTheCar(String numPlate, String model, String color) {

        carPark.put(this.getSlotNum(), new CarDetails(numPlate, model, color));

        return true;
    }

    public void getParkingDetail() {

        carPark.forEach((k, v) -> System.out.println("Slot No: " + k + " = " + v));
    }

    public int getSlotNum() {
        return slotNo++;
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
