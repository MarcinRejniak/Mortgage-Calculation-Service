package main.model.exception;

public class RateCalculationException extends RuntimeException{
    public RateCalculationException() {
        super("Rate calculate case not supported");
    }

    public RateCalculationException(String message) {
        super(message);
    }
}
