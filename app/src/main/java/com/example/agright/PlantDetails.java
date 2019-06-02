package com.example.agright;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class PlantDetails extends AppCompatActivity {
    ProgressBar growthProgress;
    ImageView close, plantImage;
    FirebaseDatabase firebaseDatabase;
    TextView waterTempVal, phVal, ecVal, dhtVal, humVal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_details);
        growthProgress = findViewById(R.id.progressbarGrowth);
        close = findViewById(R.id.closedetails);
        waterTempVal = findViewById(R.id.water_tempVal);
        phVal = findViewById(R.id.phVal);
        ecVal = findViewById(R.id.ecVal);
        dhtVal = findViewById(R.id.tempVal);
        humVal = findViewById(R.id.HumiVal);
        plantImage = findViewById(R.id.plantImage);
        firebaseDatabase = FirebaseDatabase.getInstance();
        final StorageReference storageReference = FirebaseStorage.getInstance().getReference("Pics/plant_image.jpg");
        DatabaseReference Tempref, watertempref, ecref, phref, HumRef;
        Tempref = firebaseDatabase.getReference("sensor/dht/temperature");
        HumRef = FirebaseDatabase.getInstance().getReference("sensor/dht/humidity");
        watertempref = firebaseDatabase.getReference("sensor/water_temperature");
        ecref = firebaseDatabase.getReference("sensor/ec");
        phref = firebaseDatabase.getReference("sensor/ph");

        Log.d("refCheck", watertempref.toString());

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Log.d("URI", uri.toString());
                Picasso.with(getApplicationContext())
                        .load(uri)
                        .into(plantImage);
            }
        });

       // Picasso.with(getApplicationContext()).load("https://firebasestorage.googleapis.com/v0/b/agright-f029e.appspot.com/o/%2Fplant_image?alt=media&token=10743400-0294-46b8-ad6c-c49dc24a9011").into(plantImage);

        phref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    String phValStr = ds.child("ph").getValue().toString();
                    Float phF = Float.parseFloat(phValStr);
                    phValStr = String.format("%.2f", phF);
                    phVal.setText(phValStr);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Tempref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    String dhtValStr = ds.child("temperature").getValue().toString();
                    Float dhtF = Float.parseFloat(dhtValStr);
                    dhtValStr = String.format("%.2f", dhtF);
                    dhtVal.setText(dhtValStr+" c");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        HumRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()) {
                    String humValStr = ds.child("humidity").getValue().toString();
                    Float humiF = Float.parseFloat(humValStr);
                    humValStr = String.format("%.2f", humiF);
                    humVal.setText(humValStr + " %");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        watertempref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    String WaterTempValStr = ds.child("water_temperature").getValue().toString();
                    Float waterTempF = Float.parseFloat(WaterTempValStr);
                    WaterTempValStr = String.format("%.2f", waterTempF);
                    waterTempVal.setText(WaterTempValStr+" c");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        ecref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    String ecValStr = ds.child("ec").getValue().toString();
                    Float ecValF = Float.parseFloat(ecValStr);
                    ecValStr = String.format("%.2f", ecValF);
                    ecVal.setText(ecValStr+" mho");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
