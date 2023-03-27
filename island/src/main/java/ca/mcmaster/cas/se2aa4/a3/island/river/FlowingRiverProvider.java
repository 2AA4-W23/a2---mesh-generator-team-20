package ca.mcmaster.cas.se2aa4.a3.island.river;

import ca.mcmaster.cas.se2aa4.a3.island.elevation.ElevationProvider;
import ca.mcmaster.cas.se2aa4.a3.island.lake.LakeProvider;
import ca.mcmaster.cas.se2aa4.a3.island.shape.ShapeProvider;
import ca.mcmaster.cas.se2aa4.a3.island.utils.Coordinate;
import ca.mcmaster.cas.se2aa4.a3.island.utils.Segment;

import java.util.*;

public class FlowingRiverProvider implements RiverProvider {
    private final HashMap<Segment, RiverSegment> rivers = new HashMap<>();

    private static class RiverSegment {
        Segment segment;
        double flow;

        RiverSegment(Segment segment, double flow) {
            this.segment = segment;
            this.flow = flow;
        }
    }

    public FlowingRiverProvider(List<Segment> segments, ShapeProvider shapeProvider, LakeProvider lakeProvider, ElevationProvider elevationProvider, int riversCount, long seed) {
        Queue<RiverSegment> queue = new ArrayDeque<>();
        Random random = new Random(seed);
        while (rivers.size() < riversCount) {
            Segment segment = segments.get(random.nextInt(segments.size()));
            if (!shapeProvider.contains(segment.start)){
                continue;
            }
            boolean nextToLake = lakeProvider.isLake(segment.start) && !lakeProvider.isLake(segment.end) || lakeProvider.isLake(segment.end) && !lakeProvider.isLake(segment.start);
            if (nextToLake) {
                RiverSegment riverSegment = new RiverSegment(segment, 1);
                rivers.put(segment, riverSegment);
                queue.add(riverSegment);
            }
        }

        // pass 1: create rivers
        outer:
        while (!queue.isEmpty()) {
            RiverSegment riverSegment = queue.remove();
            Segment segment = riverSegment.segment;

            Coordinate newSegmentStart = elevationProvider.getElevation(segment.start) > elevationProvider.getElevation(segment.end) ? segment.end : segment.start;
            if (!shapeProvider.contains(newSegmentStart)) {
                continue;
            }
            double newSegmentStartElevation = elevationProvider.getElevation(newSegmentStart);

            // try find existing river segment
            for (RiverSegment otherRiverSegment : rivers.values()) {
                if (otherRiverSegment.segment.approxEquals(segment)){
                    continue;
                }

                Coordinate newSegmentEnd = null;
                if (otherRiverSegment.segment.start.approxEquals(newSegmentStart)) {
                    newSegmentEnd = otherRiverSegment.segment.end;
                } else if (otherRiverSegment.segment.end.approxEquals(newSegmentStart)) {
                    newSegmentEnd = otherRiverSegment.segment.start;
                }

                if (newSegmentEnd != null) {
                    double newSegmentEndElevation = elevationProvider.getElevation(newSegmentEnd);

                    if (newSegmentEndElevation < newSegmentStartElevation) {
                        // already have downstream segment
                        continue outer;
                    }
                }
            }

            // find potential new segment
            Coordinate newSegmentEnd = null;
            double newSegmentEndElevation = Double.POSITIVE_INFINITY;
            for (Segment potentialSegment : segments) {
                if (potentialSegment != segment &&
                        !rivers.containsKey(potentialSegment) &&
                        (potentialSegment.start.approxEquals(newSegmentStart) || potentialSegment.end.approxEquals(newSegmentStart))) {
                    Coordinate potentialNewSegmentEnd = potentialSegment.start.approxEquals(newSegmentStart) ? potentialSegment.end : potentialSegment.start;
                    double potentialEndElevation = elevationProvider.getElevation(potentialNewSegmentEnd);
                    if (potentialEndElevation < newSegmentEndElevation) {
                        newSegmentEndElevation = potentialEndElevation;
                        newSegmentEnd = potentialNewSegmentEnd;
                    }
                }
            }

            // add segment
            if (newSegmentEnd != null) {
                Segment newSegment = new Segment(newSegmentStart, newSegmentEnd);
                for (Segment potentialSegment : segments) {
                    if (potentialSegment.approxEquals(newSegment)) {
                        newSegment = potentialSegment;
                    }
                }
                RiverSegment newRiverSegment = new RiverSegment(newSegment, 1);
                rivers.put(newSegment, newRiverSegment);
                queue.add(newRiverSegment);
            }
        }

        // pass 2: calculate flow
        // TODO
    }

    public int isRiver(Segment segment) {
        return (int) rivers.getOrDefault(segment, new RiverSegment(segment, 0)).flow;
    }
}
