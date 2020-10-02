package ca.sfu.cmpt276.cameradepthoffieldapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ca.sfu.cmpt276.cameradepthoffieldapp.R;
import ca.sfu.cmpt276.cameradepthoffieldapp.model.LensManager;

public class AddLens extends AppCompatActivity {

    private LensManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lens);

        manager = LensManager.getInstance();

        setupAddLensButton();
        setupEndActivityButton();


    }

    private void setupAddLensButton() {
        Button btn = (Button) findViewById(R.id.add_new_lens);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void setupEndActivityButton() {
        Button btn = (Button) findViewById(R.id.end_add_lens);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, AddLens.class);
    }
}