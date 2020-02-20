package com.bridgelabz.parkingsystem.enumerate;

import com.bridgelabz.parkingsystem.service.ParkingSlotNumberSystem;

public class ParkingSystemEnum {

    public enum TypeOfDriver {

        HANDICAP_DRIVER {
            @Override
            public int getSlotNum(ParkingSlotNumberSystem parkingSlotNumSystem) {
                return parkingSlotNumSystem.getSlotNumForHandicapDriver();
            }
        }, NORMAL_DRIVER {
            @Override
            public int getSlotNum(ParkingSlotNumberSystem parkingSlotNumSystem) {
                return parkingSlotNumSystem.getSlotNumForNormalDriver();
            }
        }, LARGE_CAR_DRIVER {
            @Override
            public int getSlotNum(ParkingSlotNumberSystem parkingSlotNumSystem) {
                return parkingSlotNumSystem.getSlotNumForLargeVehicleDriver();
            }
        };

        public abstract int getSlotNum(ParkingSlotNumberSystem parkingSlotNumSystem);
    }
}
