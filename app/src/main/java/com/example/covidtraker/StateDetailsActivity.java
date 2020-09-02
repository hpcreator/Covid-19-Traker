package com.example.covidtraker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class StateDetailsActivity extends AppCompatActivity {

    TextView name, caseTotal, newCase, recovered, newRecovered, deaths, newDeaths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_details);

        name = findViewById(R.id.caseTotal);
        caseTotal = findViewById(R.id.cases);
        newCase = findViewById(R.id.caseToday);
        recovered = findViewById(R.id.recovered);
        newRecovered = findViewById(R.id.recoverToday);
        deaths = findViewById(R.id.totalDeaths);
        newDeaths = findViewById(R.id.deathsToday);

        SharedPreferences preferences = this.getSharedPreferences("StateDetails", Context.MODE_PRIVATE);

        name.setText(preferences.getString("stateName", "0"));
        caseTotal.setText(preferences.getString("totalCase", "0"));
        newCase.setText(preferences.getString("todayCases", "0"));
        recovered.setText(preferences.getString("recovered", "0"));
        newRecovered.setText(preferences.getString("recoveredToday", "0"));
        deaths.setText(preferences.getString("deaths", "0"));
        newDeaths.setText(preferences.getString("deathsToday", "0"));
    }
}