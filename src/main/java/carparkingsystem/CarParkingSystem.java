package carparkingsystem;

import java.util.*;

public class CarParkingSystem {

    int slotNo = 1;
    Map<Integer,CarDetails> carPark = new HashMap<>();

    public boolean parkTheCar(int numPlate, String model, String color) {

        carPark.put(this.getSlotNum(),new CarDetails(numPlate,model,color));

        return true;
    }

    public void getParkingDetail() {

        carPark.forEach((k,v)->System.out.println("Slot No: "+k + " = " + v));
    }

    public int getSlotNum(){
        return slotNo++;
    }
}
