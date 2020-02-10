package carparkingsystem;

import java.util.Date;

public class CarDetails {

    private String numPlate;
    private String model;
    private String colour;
    private Date dateAndTime = new Date();


    public CarDetails(String numPlate, String model, String colour) {
        this.numPlate = numPlate;
        this.model = model;
        this.colour = colour;
    }

    public String getNumPlate() {
        return numPlate;
    }

    public void setNumPlate(String numPlate) {
        this.numPlate = numPlate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
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
