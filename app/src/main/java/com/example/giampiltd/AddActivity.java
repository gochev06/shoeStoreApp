package com.example.giampiltd;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class AddActivity extends AppCompatActivity {

    EditText nameInput, numberSizeInput, lengthSizeInput, upperMaterialInput,
            outSoleMaterialInput, priceInput;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        nameInput = findViewById(R.id.input_name);
        numberSizeInput = findViewById(R.id.input_number_size);
        lengthSizeInput = findViewById(R.id.input_length_size);
        upperMaterialInput = findViewById(R.id.input_upper_material);
        outSoleMaterialInput = findViewById(R.id.input_outsole_material);
        priceInput = findViewById(R.id.input_price);
        addButton = findViewById(R.id.insertBtn);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBmain db = new DBmain(AddActivity.this);
                db.addShoe(
                        nameInput.getText().toString().trim(),
                        Integer.parseInt(numberSizeInput.getText().toString().trim()),
                        Integer.parseInt(lengthSizeInput.getText().toString().trim()),
                        upperMaterialInput.getText().toString().trim(),
                        outSoleMaterialInput.getText().toString().trim(),
                        Integer.parseInt(priceInput.getText().toString().trim())
                        );
            }
        });
    }
}