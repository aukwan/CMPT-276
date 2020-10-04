package ca.sfu.cmpt276.cameradepthoffieldapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import ca.sfu.cmpt276.cameradepthoffieldapp.R;
import ca.sfu.cmpt276.cameradepthoffieldapp.model.LensManager;

public class MainActivity extends AppCompatActivity {

    public static final String ACTIVITY_EXTRA = "result";
    private static final int ADD_LENS_CODE = 42;
    private static final int CALCULATE_DOF_CODE = 43;
    private LensManager manager;
    private String[] lenses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        
        populateListView();
        registerClickCallback();
        setupAddLensButton();
    }

    private void populateListView() {
        // Create list of lenses
        manager = LensManager.getInstance();
        int size = manager.getNumLenses();
        lenses = new String[size];

        for (int i = 0; i < size; i++) {
            lenses[i] = manager.getLensByIndex(i).toString();
        }

        // Build Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,           // Context for the activity
                R.layout.lens_list,     // Layout to use (create)
                lenses);                //Items to be displayed

        // Configure the list view
        ListView listLenses = (ListView) findViewById(R.id.list_lenses);
        listLenses.setAdapter(adapter);
    }

    // Allow user to launch Calculate activity by tapping a lens
    private void registerClickCallback() {
        ListView listLenses = (ListView) findViewById(R.id.list_lenses);
        listLenses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                TextView textView = (TextView) viewClicked;
                String message = "Selected " + textView.getText().toString();
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                Intent intent = CalculateDOF.makeIntent(MainActivity.this);
                intent.putExtra("lens index", position);
                startActivityForResult(intent, 43);
            }
        });
    }

    // Floating action button for adding new lens
    private void setupAddLensButton() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Adding new lens.", Toast.LENGTH_SHORT).show();
                Intent intent = AddLens.makeIntent(MainActivity.this);
                startActivityForResult(intent, ADD_LENS_CODE);
            }
        });
    }

    // Gets called when the add activity we started, finishes.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case ADD_LENS_CODE:
                int answer = data.getIntExtra(ACTIVITY_EXTRA, 0);
                if (answer == 1) {
                    populateListView();
                }
                break;
            case CALCULATE_DOF_CODE:
                int answer2 = data.getIntExtra(ACTIVITY_EXTRA, 1);
                if (answer2 == 1) {
                    populateListView();
                }
                break;
        }
    }
}