package ca.mcmaster.cas.se2aa4.a3.island.aquifer;

import ca.mcmaster.cas.se2aa4.a3.island.utils.Coordinate;

public interface AquiferProvider {
    boolean isAquifer(Coordinate coordinate);
}
