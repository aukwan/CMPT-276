package ca.sfu.cmpt276.cameradepthoffieldapp.activities;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ca.sfu.cmpt276.cameradepthoffieldapp.R;
import ca.sfu.cmpt276.cameradepthoffieldapp.model.Lens;
import ca.sfu.cmpt276.cameradepthoffieldapp.model.LensManager;

public class MainActivity extends AppCompatActivity {

    private LensManager manager;
    private String[] lenses = new String[0];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        populateListView();
        registerClickCallback();
        setupAddLensButton();

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
    }

    private void populateListView() {
        // Create list of items
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

    private void registerClickCallback() {
        ListView listLenses = (ListView) findViewById(R.id.list_lenses);
        listLenses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                TextView textView = (TextView) viewClicked;
                String message = "Selected " + textView.getText().toString();
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                Intent intent = CalculateDOF.makeIntent(MainActivity.this);
                startActivityForResult(intent, 43);
            }
        });
    }

    private void setupAddLensButton() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Adding new lens.", Toast.LENGTH_SHORT).show();

                Intent intent = AddLens.makeIntent(MainActivity.this);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}