package com.example.covidtraker;

import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.leo.simplearcloader.SimpleArcLoader;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StateListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SimpleArcLoader loader;
    ScrollView stateScrollView;
    TextView tvTotalCases, tvActiveCases, tvActiveCasesNew, tvRecover, tvRecoverNew, tvDeaths, tvDeathsNew;
    List<IndiaModel.StateModel> list;


    List<IndiaModel.StateModel> stateModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_list);

        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.case_in_india);

        stateModelList = new ArrayList<>();

        recyclerView = findViewById(R.id.stateListView);
        stateScrollView = findViewById(R.id.scrollIndia);
        loader = findViewById(R.id.state_loader);

        tvTotalCases = findViewById(R.id.tvTotalCases);
        tvActiveCases = findViewById(R.id.tvActiveCases);
        tvActiveCasesNew = findViewById(R.id.tvActiveCasesNew);
        tvRecover = findViewById(R.id.tvRecover);
        tvRecoverNew = findViewById(R.id.tvRecoverNew);
        tvDeaths = findViewById(R.id.tvDeaths);
        tvDeathsNew = findViewById(R.id.tvDeathsNew);

        getIndianDetails();
    }

    private void setUpRecyclerView(List<IndiaModel.StateModel> stateModelList) {
        this.stateModelList = stateModelList;
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        StateAdapter adapter = new StateAdapter(stateModelList, this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    private WorldApi getApi() {
        return getRetroFit().create(WorldApi.class);
    }

    private Retrofit getRetroFit() {
        return new Retrofit.Builder().baseUrl(WorldApi.IndiaUrl).addConverterFactory(GsonConverterFactory.create()).client(getClient().build()).build();
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

    private void getIndianDetails() {
        loader.start();

        Call<IndiaModel> call = getApi().getIndianData();
        call.enqueue(new Callback<IndiaModel>() {
            @Override
            public void onResponse(@NotNull Call<IndiaModel> call, @NotNull Response<IndiaModel> response) {
                assert response.body() != null;
                IndiaModel model = response.body();


                tvTotalCases.setText(model.getTotalCases());
                tvActiveCases.setText(model.getActiveCases());
                tvActiveCasesNew.setText(model.getActiveCasesNew());
                tvRecover.setText(model.getRecovered());
                tvRecoverNew.setText(model.getRecoveredNew());
                tvDeaths.setText(model.getDeaths());
                tvDeathsNew.setText(model.getDeathsNew());

                list = model.getStateModel();
                setUpRecyclerView(list);
                loader.stop();
                loader.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(@NotNull Call<IndiaModel> call, @NotNull Throwable t) {
                Toast.makeText(StateListActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}