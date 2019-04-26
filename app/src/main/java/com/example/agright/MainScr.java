package com.example.agright;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainScr extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Fragment segmentSelected = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_scr);
        bottomNavigationView = findViewById(R.id.botnavbar);

        getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, new HomeFragment()).addToBackStack(null).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.action_home:
                        segmentSelected = new HomeFragment();
                        break;
                    case R.id.action_community:
                        segmentSelected = new CommunityFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, segmentSelected)
                        .addToBackStack(null).commit();
                return true;
            }
        });
    }
}
