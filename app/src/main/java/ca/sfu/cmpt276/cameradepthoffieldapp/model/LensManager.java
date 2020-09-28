package ca.sfu.cmpt276.cameradepthoffieldapp.model;

/*
  Lens Manager class to store a collection of lenses.
  Allows for addition of new lenses, retrieving lens by index,
  and calculating the number of lenses.
 */

import java.util.ArrayList;
import java.util.List;

public class LensManager {
    private List<Lens> lenses = new ArrayList<>();

    public void add(Lens lens) {
        lenses.add(lens);
    }

    public Lens getLensByIndex(int index) {
        return lenses.get(index);
    }

    public int getNumLenses() {
        return lenses.size();
    }

}
