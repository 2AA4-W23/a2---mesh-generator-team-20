package ca.mcmaster.cas.se2aa4.a3.island.color;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

public class Color {
    final double red;
    final double green;
    final double blue;
    public int alpha;

    public Color(double red, double green, double blue, int alpha) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

    public String toString() {
        return red + "," + green + "," + blue + "," + alpha;
    }

    public Structs.Property toProperty() {
        return Structs.Property.newBuilder().setKey("rgb_color").setValue(toString()).build();
    }
}
