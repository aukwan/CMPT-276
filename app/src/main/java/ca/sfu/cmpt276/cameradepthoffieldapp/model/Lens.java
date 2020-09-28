package ca.sfu.cmpt276.cameradepthoffieldapp.model;

/*
  Lens class to store info about a single lens.
  Stores the make, maximum aperture, and focal length.
 */

public class Lens {
    private String make;
    private double maxAperture;
    private double focalLength;

    public Lens(String make, double maxAperture, double focalLength) {
        this.make = make;
        this.maxAperture = maxAperture;
        this.focalLength = focalLength;
    }

    public double getMaxAperture() {
        return maxAperture;
    }

    public double getFocalLength() {
        return focalLength;
    }

    @Override
    public String toString() {
        return make + " "
                + focalLength + "mm F"
                + maxAperture;
    }
}
