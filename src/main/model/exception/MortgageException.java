package main.model.exception;

public class MortgageException extends RuntimeException{
    public MortgageException(final String message) {
        super(message);
    }
}
