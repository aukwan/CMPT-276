package ca.sfu.cmpt276.cameradepthoffieldapp.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import ca.sfu.cmpt276.cameradepthoffieldapp.R;
import ca.sfu.cmpt276.cameradepthoffieldapp.model.Lens;
import ca.sfu.cmpt276.cameradepthoffieldapp.model.LensManager;

public class AddLens extends AppCompatActivity {

    private String make;
    private double focalLength;
    private double aperture;
    LensManager manager;
    EditText newMake, newFocalLength, newAperture;
    private static final String errorMsg = "Required valid values:" +
            "\nMake length is > 0" +
            "\nFocal length is > 0" +
            "\nAperture (F) >= 1.4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lens);
        Toolbar toolbar = (Toolbar) findViewById(R.id.add_lens_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();

        newMake = (EditText) findViewById(R.id.lens_make);
        newFocalLength = (EditText) findViewById(R.id.lens_focal_length);
        newAperture = (EditText) findViewById(R.id.lens_aperture);
    }

    private boolean setupAddLens() {
        if (newMake.getText().toString().isEmpty() ||
                newFocalLength.getText().toString().isEmpty() ||
                newAperture.getText().toString().isEmpty()) {
            Toast.makeText(AddLens.this, errorMsg, Toast.LENGTH_SHORT).show();
            return false;
        } else {
            make = newMake.getText().toString();
            focalLength = Double.valueOf(newFocalLength.getText().toString());
            aperture = Double.valueOf(newAperture.getText().toString());

            if (make.length() <= 0 || focalLength <= 0 || aperture < 1.4){
                Toast.makeText(AddLens.this, errorMsg, Toast.LENGTH_SHORT).show();
                return false;
            } else {
                Lens newLens = new Lens(make, aperture, focalLength);
                manager = LensManager.getInstance();
                manager.add(newLens);
                Toast.makeText(AddLens.this, "Lens added.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.putExtra(MainActivity.ACTIVITY_EXTRA, 1);
                setResult(Activity.RESULT_OK, intent);
                finish();
                return true;
            }
        }
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, AddLens.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.menu_add_lens, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add_lens_save) {
            return setupAddLens();
        }
        return super.onOptionsItemSelected(item);
    }
}