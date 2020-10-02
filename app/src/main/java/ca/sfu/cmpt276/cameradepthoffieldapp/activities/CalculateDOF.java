package ca.sfu.cmpt276.cameradepthoffieldapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import ca.sfu.cmpt276.cameradepthoffieldapp.R;
import ca.sfu.cmpt276.cameradepthoffieldapp.model.LensManager;

public class CalculateDOF extends AppCompatActivity {

    private LensManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_d_o_f);

        Intent intent = getIntent();
        int index = intent.getIntExtra("lens index", 0);
        manager = LensManager.getInstance();
        TextView displayLens = (TextView) findViewById(R.id.display_lenses);
        displayLens.setText(manager.getLensByIndex(index).toString());

    }
    public static Intent makeIntent(Context context) {
        return new Intent(context, CalculateDOF.class);
    }
}