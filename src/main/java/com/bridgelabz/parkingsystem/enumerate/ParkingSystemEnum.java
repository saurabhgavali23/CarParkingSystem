package com.bridgelabz.parkingsystem.enumerate;

import com.bridgelabz.parkingsystem.service.ParkingSlotNumberSystem;

public class ParkingSystemEnum {

    public enum TypeOfVehicle {

        HD {
            @Override
            public int getSlotNum(ParkingSlotNumberSystem parkingSlotNumSystem) {
                return parkingSlotNumSystem.getSlotNumForHandicapDriver();
            }
        }, ND {
            @Override
            public int getSlotNum(ParkingSlotNumberSystem parkingSlotNumSystem) {
                return parkingSlotNumSystem.getSlotNumForNormalDriver();
            }
        }, LCD {
            @Override
            public int getSlotNum(ParkingSlotNumberSystem parkingSlotNumSystem) {
                return parkingSlotNumSystem.getSlotNumForLargeVehicleDriver();
            }
        };

        public abstract int getSlotNum(ParkingSlotNumberSystem parkingSlotNumSystem);
    }
}
