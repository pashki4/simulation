package ua.com.vyshniakovpo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public record Coordinates(int horizontal, int vertical) {


    public <T extends Coordinates> List<T> getNeighbors() {
        List<T> result = new ArrayList<>();
        result.add((T) new Coordinates(horizontal + 1, vertical));
        result.add((T) new Coordinates(horizontal - 1, vertical));
        result.add((T) new Coordinates(horizontal, vertical + 1));
        result.add((T) new Coordinates(horizontal, vertical - 1));
        return result;
    }

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
