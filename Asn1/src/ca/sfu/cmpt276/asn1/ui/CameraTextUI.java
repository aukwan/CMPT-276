package ca.sfu.cmpt276.asn1.ui;

import ca.sfu.cmpt276.asn1.model.DepthOfFieldCalculator;
import ca.sfu.cmpt276.asn1.model.Lens;
import ca.sfu.cmpt276.asn1.model.LensManager;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Sample (incomplete) text UI to interact with the user.
 * You may change any part of this!
 */
public class CameraTextUI {
    private static final double COC = 0.029;    // "Circle of Confusion" for a "Full Frame" camera
    private LensManager manager;
    private Scanner in = new Scanner(System.in);// Read from keyboard


    public CameraTextUI(LensManager manager) {
        // Accept and store a reference to the lens manager (the model)
        // (this design is called "dependency injection")
        this.manager = manager;

        // Populate lenses (Make, max aperture (smallest supported F number), focal length [mm]):
        manager.add(new Lens("Canon", 1.8, 50));
        manager.add(new Lens("Tamron", 2.8, 90));
        manager.add(new Lens("Sigma", 2.8, 200));
        manager.add(new Lens("Nikon", 4, 200));
    }

    public void show() {
        int isNotDone = 0;
        while (isNotDone == 0) {

            System.out.println("Lenses to pick from:");
            int index = 0;
            for (int i = 0; i < manager.getNumLenses(); i++) {
                System.out.println("  " + index + ". " + manager.getLensByIndex(i));
                index++;
            }
            System.out.println("  (-1 to exit)");
            System.out.print(": ");
            int choice = in.nextInt();
            if (choice == -1) {
                isNotDone = -1;
            } else if (choice < 0 || choice > manager.getNumLenses() - 1) {
                System.out.println("Error: Invalid lens index.\n");
            } else {
                System.out.print("Aperture [the F number]: ");
                double aperture = in.nextDouble();
                if (aperture < manager.getLensByIndex(choice).getMaxAperture()) {
                    System.out.println("ERROR: This aperture is not possible with this lens.\n");
                } else {
                    System.out.print("Distance to subject [m]: ");
                    double distance = in.nextDouble() * 1000;
                    DepthOfFieldCalculator dofCalc = new DepthOfFieldCalculator(manager.getLensByIndex(choice), distance, aperture);
                    String hyperFocalPointInM = formatM(dofCalc.getHyperFocalDistance() / 1000);
                    String nearFocalPointInM = formatM(dofCalc.getNearFocalPoint() / 1000);
                    String farFocalPointInM = formatM(dofCalc.getFarFocalPoint() / 1000);
                    String dofInM = formatM(dofCalc.getDepthOfField() / 1000);
                    System.out.println("  In focus: " + nearFocalPointInM + "m to "
                            + farFocalPointInM + "m [DoF = " + dofInM + "m]");
                    System.out.println("  Hyperfocal point: " + hyperFocalPointInM + "m\n");
                }
            }
        }
    }

    private String formatM(double distanceInM) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(distanceInM);
    }
}
