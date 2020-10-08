package ca.sfu.cmpt276.cameradepthoffieldapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.google.gson.Gson;

import ca.sfu.cmpt276.cameradepthoffieldapp.R;
import ca.sfu.cmpt276.cameradepthoffieldapp.model.Lens;
import ca.sfu.cmpt276.cameradepthoffieldapp.model.LensManager;

public class MainActivity extends AppCompatActivity {

    public static final String ACTIVITY_EXTRA = "result";
    private static final int ADD_LENS_CODE = 42;
    private static final int CALCULATE_DOF_CODE = 43;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    public static final String PREF_NAME = "lensList";
    LensManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        sharedPreferences = getPreferences(MODE_PRIVATE);
        editor = sharedPreferences.edit();
        retrieveLenses();

        populateListView();
        registerClickCallback();
        setupAddLensButton();
    }

    private void populateListView() {
        manager = LensManager.getInstance();

        // Build Adapter
        ArrayAdapter<Lens> adapter = new ArrayAdapter<>(
                this,           // Context for the activity
                R.layout.lens_list,     // Layout to use (create)
                manager.getLenses());   //Items to be displayed

        // Configure the list view
        ListView listLenses = (ListView) findViewById(R.id.list_lenses);
        listLenses.setAdapter(adapter);
        adapter.notifyDataSetChanged();
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
                startActivityForResult(intent, CALCULATE_DOF_CODE);
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

    // Gets called when the add activity the user started, finishes.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_LENS_CODE) {
            int answer = data.getIntExtra(ACTIVITY_EXTRA, 0);
            if (answer == 1) {
                populateListView();
            }
        } else {
            populateListView();
        }
    }

    private void retrieveLenses() {
        Gson gson = new Gson();
        String serializedObject = sharedPreferences.getString("lenses", "");
        if (serializedObject == null || serializedObject.equals("") || serializedObject.length() <= 0) {
            manager = LensManager.getInstance();
        } else {
            manager = LensManager.retrieveLensList(gson.fromJson(serializedObject, LensManager.class));
        }
    }

    private void saveLenses() {
        Gson gson = new Gson();
        String json;
        if (manager.getNumLenses() > 0) {
            json = gson.toJson(manager);
        } else {
            json = "";
        }
        editor.putString("lenses", json);
        editor.commit();
    }

    public void onDestroy() {
        saveLenses();
        super.onDestroy();
    }
}