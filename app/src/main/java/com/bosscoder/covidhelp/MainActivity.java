package com.bosscoder.covidhelp;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.bosscoder.covidhelp.AboutUs.AboutUsActivity;
import com.bosscoder.covidhelp.CovidTracker.TrackerMainActivity;
import com.bosscoder.covidhelp.CovidVacciine.VaccineActivity;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nav = findViewById(R.id.nav);
        drawerLayout = findViewById(R.id.drawer);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_vaccine:
                        Intent intent = new Intent(getApplicationContext(), VaccineActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.menu_tracker:
                        Intent intent1 = new Intent(getApplicationContext(), TrackerMainActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.menu_info:
                        Intent intent2 = new Intent(getApplicationContext(), AboutUsActivity.class);
                        startActivity(intent2);
                        break;
                }
                return true;
            }
        });
    }
}