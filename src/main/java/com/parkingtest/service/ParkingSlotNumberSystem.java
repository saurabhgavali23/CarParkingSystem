package com.parkingtest.service;

import java.util.LinkedList;

public class ParkingSlotNumberSystem {

    public static int totalSlot;
    public static int parkingCapacity;
    private int i = 0;
    private int j = 0;
    private int count = 0;
    public Object obj[] = null;
    public LinkedList list;

    public ParkingSlotNumberSystem(int capacity, int totalSlot) {
        this.parkingCapacity = capacity;
        this.totalSlot = totalSlot;
    }

    public ParkingSlotNumberSystem() {
        obj = new Object[parkingCapacity];
        for (int i = 0; i < parkingCapacity; i++) {
            obj[i] = new LinkedList();
        }
    }

    public int getSlotNumForHandicapDriver() {
        for (int i = 0; i < parkingCapacity; i++) {
            list = (LinkedList) obj[i];
            if (list.isEmpty()) {
                int slotNo = i;
                return slotNo;
            }
        }
        return 0;
    }

    public int getSlotNumForNormalDriver() {
        int slotNo;
        if (this.count == totalSlot) {
            i = i + 1;
            j = 0;
            count = 0;
        }
        slotNo = i + ((parkingCapacity / totalSlot) * j);
        list = (LinkedList) obj[slotNo];
        if (list.isEmpty())
            return slotNo;
        j = j + 1;
        count = count + 1;
        slotNo = this.getSlotNumForNormalDriver();
        return slotNo;
    }

    public int getSlotNumForLargeVehicleDriver() {
        int slotNo, secSlotNo = 0;
        if (this.count == totalSlot) {
            i = i + 1;
            j = 0;
            count = 0;
        }
        slotNo = i + ((parkingCapacity / totalSlot) * j);
        list = (LinkedList) obj[slotNo];
        secSlotNo = (slotNo + 1);
        if (list.isEmpty() && this.checkEmptySlot(secSlotNo)) {
            return slotNo;
        }
        j = j + 1;
        count = count + 1;
        slotNo = this.getSlotNumForLargeVehicleDriver();
        return slotNo;
    }

    private boolean checkEmptySlot(int secSlotNo) {
        list = (LinkedList) obj[secSlotNo];
        if (list.isEmpty())
            return true;
        return false;
    }
}
