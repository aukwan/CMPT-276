package ca.sfu.cmpt276.asn1;

import ca.sfu.cmpt276.asn1.model.LensManager;
import ca.sfu.cmpt276.asn1.ui.CameraTextUI;

/**
 * Launch application
 */
public class Main {
    public static void main(String args[]) {
        LensManager manager = new LensManager();
        CameraTextUI ui = new CameraTextUI(manager);
        ui.show();
    }
}
