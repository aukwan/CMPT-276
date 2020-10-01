package ca.sfu.cmpt276.cameradepthoffieldapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddLens extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lens);

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