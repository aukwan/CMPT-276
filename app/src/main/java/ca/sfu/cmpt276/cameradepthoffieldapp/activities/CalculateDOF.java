package ca.sfu.cmpt276.cameradepthoffieldapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.EditText;
import android.widget.TextView;

import ca.sfu.cmpt276.cameradepthoffieldapp.R;
import ca.sfu.cmpt276.cameradepthoffieldapp.model.LensManager;

public class CalculateDOF extends AppCompatActivity {

    private LensManager manager;
    private double COC, distance, aperture;
    private EditText inputCOC, inputDistance, inputAperture;
    private double nearFocalDistance, farFocalDistance, hyperfocalDistance;
    private static final String errorMsg = "Required valid values:" +
            "\nCircle of Confusion must be > 0" +
            "\nDistance to subject > 0" +
            "\nSelected aperture (F) >= 1.4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_d_o_f);
        Toolbar toolbar = (Toolbar) findViewById(R.id.dof_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        int index = intent.getIntExtra("lens index", 0);
        TextView displayLens = (TextView) findViewById(R.id.display_lenses);
        manager = LensManager.getInstance();
        displayLens.setText(manager.getLensByIndex(index).toString());

        inputCOC = (EditText) findViewById(R.id.input_coc);
        inputDistance = (EditText) findViewById(R.id.input_distance);
        inputAperture = (EditText) findViewById(R.id.input_aperture);

        calculateDoF();
    }

    private void calculateDoF() {

    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, CalculateDOF.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.menu_calculate_d_o_f, menu);
        return true;
    }
}