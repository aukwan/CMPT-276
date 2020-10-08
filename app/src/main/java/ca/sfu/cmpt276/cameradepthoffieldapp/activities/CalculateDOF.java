package ca.sfu.cmpt276.cameradepthoffieldapp.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.text.DecimalFormat;

import ca.sfu.cmpt276.cameradepthoffieldapp.R;
import ca.sfu.cmpt276.cameradepthoffieldapp.model.DepthOfFieldCalculator;
import ca.sfu.cmpt276.cameradepthoffieldapp.model.Lens;
import ca.sfu.cmpt276.cameradepthoffieldapp.model.LensManager;

public class CalculateDOF extends AppCompatActivity {
    private int lensIndex;
    private double COC, distance, aperture;
    private String nearFocalDistance, farFocalDistance, hyperfocalDistance, dof;
    private static final String errorMsg = "Required valid values:" +
            "\nCircle of Confusion must be > 0" +
            "\nDistance to subject > 0" +
            "\nSelected aperture (F) >= 1.4";
    LensManager manager;
    EditText inputCOC, inputDistance, inputAperture;
    TextView nearFocalResult, farFocalResult, hyperfocalResult, dofResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_d_o_f);
        Toolbar toolbar = (Toolbar) findViewById(R.id.dof_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        lensIndex = intent.getIntExtra("lens index", 0);
        TextView displayLens = (TextView) findViewById(R.id.display_lenses);
        manager = LensManager.getInstance();
        displayLens.setText(manager.getLensByIndex(lensIndex).toString());

        inputCOC = (EditText) findViewById(R.id.input_coc);
        inputDistance = (EditText) findViewById(R.id.input_distance);
        inputAperture = (EditText) findViewById(R.id.input_aperture);

        nearFocalResult = (TextView) findViewById(R.id.near_focal_dist);
        farFocalResult = (TextView) findViewById(R.id.far_focal_dist);
        hyperfocalResult = (TextView) findViewById(R.id.hyperfocal_dist);
        dofResult = (TextView) findViewById(R.id.d_o_f);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                    calculateDoF(lensIndex);
            }
        };

        inputCOC.addTextChangedListener(textWatcher);
        inputDistance.addTextChangedListener(textWatcher);
        inputAperture.addTextChangedListener(textWatcher);

    }

    // Displays calculated focal distances and depth of field values
    private void calculateDoF(int index) {
        if (!(inputCOC.getText().toString().isEmpty() ||
                inputDistance.getText().toString().isEmpty() ||
                inputAperture.getText().toString().isEmpty()) )
        {
            COC = Double.parseDouble(inputCOC.getText().toString());
            distance = Double.parseDouble(inputDistance.getText().toString()) * 1000;
            aperture = Double.parseDouble(inputAperture.getText().toString());

            if (COC <= 0 || distance <= 0 || aperture < 1.4) {
                Toast.makeText(CalculateDOF.this, errorMsg, Toast.LENGTH_SHORT).show();
            } else {
                manager = LensManager.getInstance();
                Lens selectedLens = manager.getLensByIndex(index);
                DepthOfFieldCalculator calculator = new DepthOfFieldCalculator(selectedLens,
                        distance,
                        aperture,
                        COC);
                nearFocalDistance = formatM(calculator.getNearFocalPoint() / 1000);
                farFocalDistance = formatM(calculator.getFarFocalPoint() / 1000);
                hyperfocalDistance = formatM(calculator.getHyperFocalDistance() / 1000);
                dof = formatM(calculator.getDepthOfField() / 1000);
                nearFocalResult.setText(nearFocalDistance + "m");
                farFocalResult.setText(farFocalDistance + "m");
                hyperfocalResult.setText(hyperfocalDistance + "m");
                dofResult.setText(dof + "m");
            }
        }
    }

    // Formats focal distances and depth of field values to two decimal places and then into strings.
    private String formatM(double distanceInM) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(distanceInM);
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, CalculateDOF.class);
    }

    // Generates the back button on app bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.menu_calculate_d_o_f, menu);
        return true;
    }
}