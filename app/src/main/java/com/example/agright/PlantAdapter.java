package com.example.agright;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class PlantAdapter extends FirestoreRecyclerAdapter<Plant, PlantAdapter.PlantHolder> {

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public PlantAdapter(@NonNull FirestoreRecyclerOptions<Plant> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull PlantHolder holder, int position, @NonNull Plant model) {
        holder.plantName.setText(model.getPlantName());
        holder.plantId.setText(model.getPlantId());
        holder.plantStatus.setText(model.getPlantStatus());
    }

    @NonNull
    @Override
    public PlantHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_custom_layout, viewGroup, false);
        return new PlantHolder(v);
    }

    class PlantHolder extends RecyclerView.ViewHolder{
        TextView plantName, plantId, plantStatus;
        CircleImageView plantImg;

        public PlantHolder(@NonNull View itemView) {
            super(itemView);
            plantId = itemView.findViewById(R.id.plantId);
            plantName = itemView.findViewById(R.id.plantName);
            plantImg = itemView.findViewById(R.id.plantprofilepic);
            plantStatus = itemView.findViewById(R.id.plantStatus);
        }
    }
}
