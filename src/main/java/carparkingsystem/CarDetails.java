package carparkingsystem;

import java.util.Date;

public class CarDetails {

    int slotNo;
    private String numPlate;
    private String model;
    private String colour;
    private Date dateAndTime = new Date();


    public CarDetails(String numPlate, String model, String colour) {
        this.numPlate = numPlate;
        this.model = model;
        this.colour = colour;
    }

    public CarDetails() {
        this.slotNo = 1;
    }

    public int getSlotNum(){
        return slotNo++;
    }

    public String getNumPlate() {
        return numPlate;
    }

    public String getModel() {
        return model;
    }

    public String getColour() {
        return colour;
    }

    public Date getDateAndTime() {
        return dateAndTime;
    }

    @Override
    public String toString() {
        return "CarDetails{" +
                "numPlate=" + numPlate +
                ", model='" + model + '\'' +
                ", colour='" + colour + '\'' +
                ", date=" + dateAndTime +
                '}';
    }
}
