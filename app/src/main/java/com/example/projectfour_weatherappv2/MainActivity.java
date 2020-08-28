package com.example.projectfour_weatherappv2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = findViewById(R.id.spinnerID);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.locations, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        if (text.equals("London")) {
            Intent i = new Intent(this, SecondActivity.class);
            i.putExtra("location",text);
            startActivity(i);
        } else if(text.equals("Detroit")) {
            Intent i = new Intent(this, SecondActivity.class);
            i.putExtra("location",text);
            startActivity(i);
        } else if(text.equals("Sydney")) {
            Intent i = new Intent(this, SecondActivity.class);
            i.putExtra("location",text);
            startActivity(i);
        } else if(text.equals("Miami")) {
            Intent i = new Intent(this, SecondActivity.class);
            i.putExtra("location",text);
            startActivity(i);
        } else if(text.equals("Juneau")) {
            Intent i = new Intent(this, SecondActivity.class);
            i.putExtra("location",text);
            startActivity(i);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
