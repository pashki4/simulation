package ua.com.vyshniakovpo.entity;

public class UnknownEntity extends RuntimeException {
    public UnknownEntity(String message) {
        super(message);
    }
}
