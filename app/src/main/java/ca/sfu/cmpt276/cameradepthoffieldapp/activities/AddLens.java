package ca.sfu.cmpt276.cameradepthoffieldapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ca.sfu.cmpt276.cameradepthoffieldapp.R;
import ca.sfu.cmpt276.cameradepthoffieldapp.model.Lens;
import ca.sfu.cmpt276.cameradepthoffieldapp.model.LensManager;

public class AddLens extends AppCompatActivity {

    private String make;
    private double focalLength;
    private double aperture;
    private LensManager manager;
    private static final String errorMsg = "Required valid values:" +
            "\nMake length  > 0" +
            "\nFocal length > 0" +
            "\nAperture (F) >= 1.4";

    private EditText newMake, newFocalLength, newAperture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lens);

        Intent intent = getIntent();

        newMake = (EditText) findViewById(R.id.lens_make);
        newFocalLength = (EditText) findViewById(R.id.lens_focal_length);
        newAperture = (EditText) findViewById(R.id.lens_aperture);

        setupAddLensButton();
        setupEndActivityButton();


    }

    private void setupAddLensButton() {
        Button btn = (Button) findViewById(R.id.add_new_lens);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (newMake.getText().toString().isEmpty() ||
                        newFocalLength.getText().toString().isEmpty() ||
                        newAperture.getText().toString().isEmpty()) {
                    Toast.makeText(AddLens.this, errorMsg, Toast.LENGTH_SHORT).show();
                } else {
                    make = newMake.getText().toString();
                    focalLength = Double.valueOf(newFocalLength.getText().toString());
                    aperture = Double.valueOf(newAperture.getText().toString());

                    if (make.length() <= 0 || focalLength <= 0 || aperture < 1.4){
                        Toast.makeText(AddLens.this, errorMsg, Toast.LENGTH_SHORT).show();
                    } else {
                        Lens newLens = new Lens(make, aperture, focalLength);
                        manager = LensManager.getInstance();
                        manager.add(newLens);
                        Toast.makeText(AddLens.this, "Lens added.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.putExtra(MainActivity.ACTIVITY_EXTRA, 1);
                        setResult(Activity.RESULT_OK, intent);
                        finish();
                    }
                }
            }
        });
    }

    private void setupEndActivityButton() {
        Button btn = (Button) findViewById(R.id.end_add_lens);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(Activity.RESULT_CANCELED, intent);
                finish();
            }
        });
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, AddLens.class);
    }
}