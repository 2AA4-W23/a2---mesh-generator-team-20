package ca.mcmaster.cas.se2aa4.a3.island.soil;

import ca.mcmaster.cas.se2aa4.a3.island.aquifer.AquiferProvider;
import ca.mcmaster.cas.se2aa4.a3.island.lake.LakeProvider;
import ca.mcmaster.cas.se2aa4.a3.island.shape.ShapeProvider;
import ca.mcmaster.cas.se2aa4.a3.island.utils.Coordinate;

public class BasicSoilAbsorptionProvider implements SoilAbsorptionProvider {
    private final ShapeProvider shapeProvider;
    private final LakeProvider lakeProvider;
    private final AquiferProvider aquiferProvider;

    public BasicSoilAbsorptionProvider(ShapeProvider shapeProvider, LakeProvider lakeProvider, AquiferProvider aquiferProvider) {
        this.shapeProvider = shapeProvider;
        this.lakeProvider = lakeProvider;
        this.aquiferProvider = aquiferProvider;
    }

    public double getAbsorptionLevel(Coordinate coordinate) {
        if (shapeProvider.isLand(coordinate)) {
            double absorptionLevel = 0;

            Coordinate nearestSea = shapeProvider.nearestBorder(coordinate);
            double distance = coordinate.distance(nearestSea);
            absorptionLevel += 1 / ((distance + 4) * 0.3);

            Coordinate nearestLake = lakeProvider.nearestBorder(coordinate);
            distance = coordinate.distance(nearestLake);
            absorptionLevel += 1 / ((distance + 4) * 0.3);

            if (aquiferProvider.isAquifer(coordinate)) {
                absorptionLevel += 0.5;
            } else {
                Coordinate nearestAquifer = aquiferProvider.nearestBorder(coordinate);
                distance = coordinate.distance(nearestAquifer);
                absorptionLevel += 1 / ((distance + 4) * 0.1);
            }

            return Math.min(1, absorptionLevel);
        } else {
            return 1;
        }
    }
}
