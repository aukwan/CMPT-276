package ca.sfu.cmpt276.cameradepthoffieldapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import ca.sfu.cmpt276.cameradepthoffieldapp.R;

public class CalculateDOF extends AppCompatActivity {

    public static Intent makeIntent(Context context) {
        return new Intent(context, CalculateDOF.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_d_o_f);
    }
}