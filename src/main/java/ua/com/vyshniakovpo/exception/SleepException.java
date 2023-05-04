package ua.com.vyshniakovpo.exception;

public class SleepException extends RuntimeException {
    public SleepException(String message, Throwable e) {
        super(message, e);
    }
}
