package ca.sfu.cmpt276.cameradepthoffieldapp.model;

/*
  Depth of Field Calculator class computes the depth of field values.
 */

public class DepthOfFieldCalculator {
    private Lens lens;
    private double distance;
    private double aperture;
    private double COC;

    public DepthOfFieldCalculator(Lens lens, double distance, double aperture, double COC) {
        this.lens = lens;
        this.distance = distance;
        this.aperture = aperture;
        this.COC = COC;
    }

    public double getHyperFocalDistance() {
        return Math.pow(this.lens.getFocalLength(), 2) /
                (this.aperture * COC);
    }

    public double getNearFocalPoint() {
        return (getHyperFocalDistance() * this.distance) /
                (getHyperFocalDistance() + (this.distance - this.lens.getFocalLength()));
    }

    public double getFarFocalPoint() {
        if (this.distance > getHyperFocalDistance()) {
            return Double.POSITIVE_INFINITY;
        } else {
            return (getHyperFocalDistance() * this.distance) /
                    (getHyperFocalDistance() - (this.distance - this.lens.getFocalLength()));
        }
    }

    public double getDepthOfField() {
        return getFarFocalPoint() - getNearFocalPoint();
    }
}
