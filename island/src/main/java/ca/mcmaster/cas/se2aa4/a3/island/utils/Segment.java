package ca.mcmaster.cas.se2aa4.a3.island.utils;

import java.util.Objects;

public class Segment {
    public final Coordinate start;
    public final Coordinate end;

    public Segment(Coordinate start, Coordinate end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Segment rhs = (Segment) obj;
        return start.equals(rhs.start) && end.equals(rhs.end) || start.equals(rhs.end) && end.equals(rhs.start);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end) + Objects.hash(end, start);
    }
}
