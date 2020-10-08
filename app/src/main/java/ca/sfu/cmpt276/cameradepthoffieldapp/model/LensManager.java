package ca.sfu.cmpt276.cameradepthoffieldapp.model;

/*
  Lens Manager class to store a collection of lenses.
  Allows for addition of new lenses, retrieving lens by index,
  and calculating the number of lenses.
 */

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import ca.sfu.cmpt276.cameradepthoffieldapp.activities.MainActivity;

public class LensManager {
    private List<Lens> lenses = new ArrayList<>();

    // Normal Object Code
    public void add(Lens lens) {
        lenses.add(lens);
    }

    public Lens getLensByIndex(int index) {
        return lenses.get(index);
    }

    public int getNumLenses() {
        return lenses.size();
    }

    public List<Lens> getLenses() {
        return lenses;
    }

    // Singleton Support
    private static LensManager instance;

    private LensManager() {
        // Private to prevent anyone else from instantiating
    }

    public static LensManager getInstance() {
        if (instance == null) {
            instance = new LensManager();
            // Add sample lenses from Assignment 1 if empty
            instance.add(new Lens("Canon", 1.8, 50));
            instance.add(new Lens("Tamron", 2.8, 90));
            instance.add(new Lens("Sigma", 2.8, 200));
            instance.add(new Lens("Nikon", 4, 200));
        }
        return instance;
    }

    public static LensManager retrieveLensList(LensManager fromJson) {
        if (instance == null) {
            instance = fromJson;
        }
        return instance;
    }
}
