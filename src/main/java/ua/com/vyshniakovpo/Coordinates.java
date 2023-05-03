package ua.com.vyshniakovpo;

import java.util.Objects;

public record Coordinates(int horizontal, int vertical) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return horizontal == that.horizontal && vertical == that.vertical;
    }

    @Override
    public int hashCode() {
        return Objects.hash(horizontal, vertical);
    }

    @Override
    public String toString() {
        return horizontal + ", " + vertical;
    }
}
