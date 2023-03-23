package ca.mcmaster.cas.se2aa4.a3.island.river;

import ca.mcmaster.cas.se2aa4.a3.island.utils.Segment;

public interface RiverProvider {
    // 0: no river
    double isRiver(Segment segment);
}
