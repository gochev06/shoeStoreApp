package com.example.giampiltd;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton addButton;

    DBmain dbMain;
    ArrayList<String> shoeId, shoeName, shoeNumberSize, shoeLengthSize,
                    shoeUpperMaterial, shoeOutsoleMaterial, shoePrice;

    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE
        }, PackageManager.PERMISSION_GRANTED);

        recyclerView = findViewById(R.id.recyclerView);
        addButton = findViewById(R.id.btn_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        dbMain = new DBmain(MainActivity.this);
        shoeId = new ArrayList<>();
        shoeName = new ArrayList<>();
        shoeNumberSize = new ArrayList<>();
        shoeLengthSize = new ArrayList<>();
        shoeUpperMaterial = new ArrayList<>();
        shoeOutsoleMaterial = new ArrayList<>();
        shoePrice = new ArrayList<>();

        StoreDataInArrays();

        customAdapter = new CustomAdapter(MainActivity.this, this, shoeId, shoeName,
                shoeNumberSize, shoeLengthSize, shoeUpperMaterial, shoeOutsoleMaterial, shoePrice);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }

    void StoreDataInArrays() {
        Cursor cursor = dbMain.getAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data fetched", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()) {
                shoeId.add(cursor.getString(0));
                shoeName.add(cursor.getString(1));
                shoeNumberSize.add(cursor.getString(2));
                shoeLengthSize.add(cursor.getString(3));
                shoeUpperMaterial.add(cursor.getString(4));
                shoeOutsoleMaterial.add(cursor.getString(5));
                shoePrice.add(cursor.getString(6));
            }
        }
    }
}