package com.example.missing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    ListView lis ;
    SearchView searchView;
    Database db = new Database(Home.this);
    BottomNavigationView navigationView;
    ArrayList<String> listdata;
    ArrayAdapter arrayadpter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        lis = (ListView) findViewById(R.id.listview);
        searchView = (SearchView) findViewById(R.id.search);
        showdata();

        navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        Intent intent1 = new Intent(Home.this, MainActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.person:
                        Intent intent = new Intent(Home.this, LostPerson.class);
                        startActivity(intent);
                        break;
                    case R.id.setting:
                        Intent intent2 = new Intent(Home.this, AboutUs.class);
                        startActivity(intent2);
                        break;
                    case R.id.add:
                        Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(camera_intent, 123);
                        break;
                }
            }
        });
    }

    public void showdata()
    {
        listdata = db.getallrecordes();
        arrayadpter = new ArrayAdapter(Home.this, android.R.layout.simple_list_item_1,listdata);
        lis.setAdapter(arrayadpter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                arrayadpter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                arrayadpter.getFilter().filter(newText);
                return false;
            }
        });
    }
}