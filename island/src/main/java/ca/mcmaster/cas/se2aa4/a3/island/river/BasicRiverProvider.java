package ca.mcmaster.cas.se2aa4.a3.island.river;

import ca.mcmaster.cas.se2aa4.a3.island.elevation.ElevationProvider;
import ca.mcmaster.cas.se2aa4.a3.island.shape.ShapeProvider;
import ca.mcmaster.cas.se2aa4.a3.island.utils.Coordinate;
import ca.mcmaster.cas.se2aa4.a3.island.utils.Segment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BasicRiverProvider implements RiverProvider {
    private final HashMap<Segment, Double> rivers = new HashMap<>();

    public BasicRiverProvider(List<Segment> segments, ShapeProvider shapeProvider, ElevationProvider elevationProvider, double density) {
        for (Segment segment : segments) {
            boolean nextToSea = shapeProvider.contains(segment.start) && !shapeProvider.contains(segment.end) || shapeProvider.contains(segment.end) && !shapeProvider.contains(segment.start);
            if (nextToSea && Math.random() < density) {
                rivers.put(segment, 4.0);
            }
        }

        for (int i = 0; i < 6; i++) {
            List<Segment> riverSegments = new ArrayList<>(rivers.keySet());
            for (Segment riverSegment : riverSegments) {
                double thickness = rivers.get(riverSegment);
                boolean startConnected = false;
                boolean endConnected = false;
                for (Segment otherSegment : riverSegments) {
                    if (otherSegment != riverSegment) {
                        if (!shapeProvider.contains(riverSegment.start) || riverSegment.start.equals(otherSegment.start) || riverSegment.start.equals(otherSegment.end)) {
                            startConnected = true;
                        } else if (!shapeProvider.contains(riverSegment.end) || riverSegment.end.equals(otherSegment.start) || riverSegment.end.equals(otherSegment.end)) {
                            endConnected = true;
                        }
                    }
                }
                if (startConnected && endConnected) {
                    continue;
                }

                Coordinate newRiverStart = startConnected ? riverSegment.end : riverSegment.start;
                double newRiverStartElevation = elevationProvider.getElevation(newRiverStart);
                Coordinate newRiverEnd = null;
                double newRiverEndElevation = Double.MIN_VALUE;
                for (Segment potentialRiverSegment : segments) {
                    if (potentialRiverSegment != riverSegment && (potentialRiverSegment.start.approxEquals(newRiverStart) || potentialRiverSegment.end.approxEquals(newRiverStart))) {
                        Coordinate otherEnd = potentialRiverSegment.start.approxEquals(newRiverStart) ? potentialRiverSegment.end : potentialRiverSegment.start;
                        double otherEndElevation = elevationProvider.getElevation(otherEnd);
                        if (otherEndElevation > newRiverEndElevation) {
                            newRiverEnd = otherEnd;
                            newRiverEndElevation = otherEndElevation;
                        }
                    }
                }

                if (newRiverEnd != null && newRiverEndElevation > newRiverStartElevation) {
                    rivers.put(new Segment(newRiverStart, newRiverEnd), thickness - 0.5);
                }
            }
        }
    }

    public int isRiver(Segment segment) {
        return (int) (double) rivers.getOrDefault(segment, 0.0);
    }
}
