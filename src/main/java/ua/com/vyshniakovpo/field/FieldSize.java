package ua.com.vyshniakovpo.field;

public class FieldSize {
    public final Integer horizontal;
    public final Integer vertical;

    public FieldSize(Integer horizontal, Integer vertical) {
        if (horizontal < 0 || vertical < 0) {
            throw new FieldSizeException("coordinates cannot be negative");
        }
        this.horizontal = horizontal;
        this.vertical = vertical;
    }
}
