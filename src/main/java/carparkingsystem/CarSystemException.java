package carparkingsystem;

public class CarSystemException extends Exception {

    public ExceptionType type;

    public enum ExceptionType {
        DATA_NOT_FOUND;
    }

    public CarSystemException(ExceptionType type, String message) {
        super(message);
        this.type = type;
    }

}
