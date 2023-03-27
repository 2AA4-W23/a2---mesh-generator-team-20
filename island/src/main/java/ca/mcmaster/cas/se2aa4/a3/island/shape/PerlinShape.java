package ca.mcmaster.cas.se2aa4.a3.island.shape;

import ca.mcmaster.cas.se2aa4.a3.island.utils.Coordinate;
import ca.mcmaster.cas.se2aa4.a3.island.utils.NoiseGenerator;

import java.util.HashMap;

public class PerlinShape extends ShapeProvider {
    private final NoiseGenerator noiseGenerator;
    private final HashMap<Coordinate, Boolean> cache = new HashMap<>();

    public PerlinShape(long seed) {
        this.noiseGenerator = new NoiseGenerator(seed);
    }

    public PerlinShape() {
        this.noiseGenerator = new NoiseGenerator();
    }

    @Override
    public boolean isLand(Coordinate coordinate) {
        if (cache.containsKey(coordinate)) {
            return cache.get(coordinate);
        }
        boolean value = noiseGenerator.noise(coordinate.x / 5, coordinate.y / 5) < 0.3;
        cache.put(coordinate, value);
        return value;
    }
}
