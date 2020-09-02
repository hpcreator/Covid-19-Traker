package com.example.covidtraker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StateAdapter extends RecyclerView.Adapter<StateAdapter.StateViewHolder> {

    List<IndiaModel.StateModel> stateList;
    Context context;

    public StateAdapter(List<IndiaModel.StateModel> stateList, Context context) {
        this.context = context;
        this.stateList = stateList;
    }


    @NonNull
    @Override
    public StateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_state, parent, false);
        return new StateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StateViewHolder holder, int position) {
        holder.stateName.setText(stateList.get(position).getRegion());
        holder.caseCount.setText(stateList.get(position).getTotalInfected());
        holder.layout.setOnClickListener(view -> {
            sendToNext(position);
            context.startActivity(new Intent(context, StateDetailsActivity.class));
        });
    }

    public void sendToNext(int position) {
        SharedPreferences preferences = context.getSharedPreferences("StateDetails", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

            String stateName = stateList.get(position).getRegion();
            String totalCase = stateList.get(position).getTotalInfected();
            String todayCases = stateList.get(position).getNewInfected();
            String recovered = stateList.get(position).getRecovered();
            String recoveredToday = stateList.get(position).getNewRecovered();
            String deaths = stateList.get(position).getDeaths();
            String deathsToday = stateList.get(position).getNewDeaths();

            editor.putString("stateName", stateName);
            editor.putString("totalCase", totalCase);
            editor.putString("todayCases", todayCases);
            editor.putString("recovered", recovered);
            editor.putString("recoveredToday", recoveredToday);
            editor.putString("deaths", deaths);
            editor.putString("deathsToday", deathsToday);
            editor.apply();
    }

    @Override
    public int getItemCount() {
        return stateList.size();
    }

    public static class StateViewHolder extends RecyclerView.ViewHolder {
        TextView stateName, caseCount;
        LinearLayout layout;

        public StateViewHolder(@NonNull View itemView) {
            super(itemView);

            stateName = itemView.findViewById(R.id.stateName);
            caseCount = itemView.findViewById(R.id.caseCount);
            layout = itemView.findViewById(R.id.stateLayout);
        }
    }
}
