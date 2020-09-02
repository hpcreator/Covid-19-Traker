package com.example.covidtraker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.jetbrains.annotations.NotNull;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView tvCases, tvRecovered, tvCritical, tvActive, tvTodayCases, tvTotalDeaths, tvTodayDeaths, tvAffectedCountries;
    SimpleArcLoader simpleArcLoader;
    ScrollView scrollView;
    PieChart pieChart;
    Button btn_track;
    Boolean doubleBack = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCases = findViewById(R.id.tvCases);
        tvRecovered = findViewById(R.id.tvRecovered);
        tvCritical = findViewById(R.id.tvCritical);
        tvActive = findViewById(R.id.tvActive);
        tvTodayCases = findViewById(R.id.tvTodayCases);
        tvTotalDeaths = findViewById(R.id.tvTotalDeaths);
        tvTodayDeaths = findViewById(R.id.tvTodayDeaths);
        tvAffectedCountries = findViewById(R.id.tvAffectedCountries);
        btn_track = findViewById(R.id.btn_track);

        simpleArcLoader = findViewById(R.id.loader);
        scrollView = findViewById(R.id.scrollStates);
        pieChart = findViewById(R.id.piechart);

        fetchData();

        btn_track.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, StateListActivity.class)));
    }

    private void fetchData() {
        //String url = "https://corona.lmao.ninja/v2/all";
        simpleArcLoader.start();

        Call<WorldModel> call = getApi().getWorldData();
        call.enqueue(new Callback<WorldModel>() {
            @Override
            public void onResponse(@NotNull Call<WorldModel> call, @NotNull Response<WorldModel> response) {
                assert response.body() != null;
                tvCases.setText(response.body().getCases());
                tvRecovered.setText(response.body().getRecovered());
                tvCritical.setText(response.body().getCritical());
                tvActive.setText(response.body().getActive());
                tvTodayCases.setText(response.body().getTodayCases());
                tvTotalDeaths.setText(response.body().getDeaths());
                tvTodayDeaths.setText(response.body().getTodayDeaths());
                tvAffectedCountries.setText(response.body().getAffectedCountries());

                pieChart.addPieSlice(new PieModel("Cases", Integer.parseInt(tvCases.getText().toString()), Color.parseColor("#FFA726")));
                pieChart.addPieSlice(new PieModel("Recoverd", Integer.parseInt(tvRecovered.getText().toString()), Color.parseColor("#66BB6A")));
                pieChart.addPieSlice(new PieModel("Deaths", Integer.parseInt(tvTotalDeaths.getText().toString()), Color.parseColor("#EF5350")));
                pieChart.addPieSlice(new PieModel("Active", Integer.parseInt(tvActive.getText().toString()), Color.parseColor("#29B6F6")));
                pieChart.startAnimation();

                simpleArcLoader.stop();
                simpleArcLoader.setVisibility(View.GONE);
                scrollView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(@NotNull Call<WorldModel> call, @NotNull Throwable t) {

                Toast.makeText(MainActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private WorldApi getApi() {
        return getRetroFit().create(WorldApi.class);
    }

    private Retrofit getRetroFit() {
        return new Retrofit.Builder().baseUrl(WorldApi.WorldUrl).addConverterFactory(GsonConverterFactory.create()).client(getClient().build()).build();
    }

    private OkHttpClient.Builder getClient() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(getInterceptor());
        return client;
    }

    private HttpLoggingInterceptor getInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logging;

    }

    @Override
    public void onBackPressed() {
        if (doubleBack) {
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
        } else {
            doubleBack = true;
            Toast.makeText(this, "press again to exit..", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBack = false;
                }
            }, 2000);
        }
    }
}