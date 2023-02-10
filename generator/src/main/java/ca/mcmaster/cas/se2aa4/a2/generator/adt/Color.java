package ca.mcmaster.cas.se2aa4.a2.generator.adt;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

public class Color {
    public double red;
    public double green;
    public double blue;

    public Color(double red, double green, double blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public Structs.Property toProperty() {
        return Structs.Property.newBuilder().setKey("rgb_color").setValue(red + "," + green + "," + blue).build();
    }
}