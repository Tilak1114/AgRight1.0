package com.example.agright;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.agright.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class HomeFragment extends Fragment implements PlantAdapter.ItemclickListener{
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference plantRef;
    RecyclerView recyclerView;
    PlantAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View homeFragView = inflater.inflate(R.layout.home_fragment, container, false);
        recyclerView = homeFragView.findViewById(R.id.activePlantRv);
        plantRef = db.collection("Plants/TestPlant/Info");

        setupRecyclerView();

        return homeFragView;
    }

    private void setupRecyclerView() {
        Query query = plantRef;
        FirestoreRecyclerOptions<Plant> options = new FirestoreRecyclerOptions.Builder<Plant>().setQuery(query, Plant.class).build();
        adapter = new PlantAdapter(options, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void onItemClick() {
        startActivity(new Intent(getActivity(), PlantDetails.class));
    }
}
