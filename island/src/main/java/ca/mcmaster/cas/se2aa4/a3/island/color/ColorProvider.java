package ca.mcmaster.cas.se2aa4.a3.island.color;

import ca.mcmaster.cas.se2aa4.a3.island.utils.Coordinate;

public interface ColorProvider {
    Color getColor(Coordinate coordinate);
}