package com.example.giampiltd;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText nameInput, numberSizeInput, lengthSizeInput,
            upperMaterialInput, outsoleMaterialInput, priceInput;
    Button updateBtn, deleteBtn;
    String id, name, numberSize, lengthSize, upperMaterial, outsoleMaterial, price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        nameInput = findViewById(R.id.input_name_upd);
        numberSizeInput = findViewById(R.id.input_number_size_upd);
        lengthSizeInput = findViewById(R.id.input_length_size_upd);
        upperMaterialInput = findViewById(R.id.input_upper_material_upd);
        outsoleMaterialInput = findViewById(R.id.input_outsole_material_upd);
        priceInput = findViewById(R.id.input_price_upd);
        updateBtn = findViewById(R.id.insertBtn_upd);
        deleteBtn = findViewById(R.id.delete_btn);

        GetAndSetIntentDate();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(name);
        }

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBmain dbMain = new DBmain(UpdateActivity.this);
                dbMain.UpdateData(
                        id,
                        nameInput.getText().toString().trim(),
                        numberSizeInput.getText().toString().trim(),
                        lengthSizeInput.getText().toString().trim(),
                        upperMaterialInput.getText().toString().trim(),
                        outsoleMaterialInput.getText().toString().trim(),
                        priceInput.getText().toString().trim()
                        );
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfirmDialog();
            }
        });

    }

    void GetAndSetIntentDate() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("name")
                && getIntent().hasExtra("number_size") && getIntent().hasExtra("length_size")
                && getIntent().hasExtra("upper_material") && getIntent().hasExtra("outsole_material")
                && getIntent().hasExtra("price")) {
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            numberSize = getIntent().getStringExtra("number_size");
            lengthSize = getIntent().getStringExtra("length_size");
            upperMaterial = getIntent().getStringExtra("upper_material");
            outsoleMaterial = getIntent().getStringExtra("outsole_material");
            price = getIntent().getStringExtra("price");

            nameInput.setText(name);
            numberSizeInput.setText(numberSize);
            lengthSizeInput.setText(lengthSize);
            upperMaterialInput.setText(upperMaterial);
            outsoleMaterialInput.setText(outsoleMaterial);
            priceInput.setText(price);
        }
        else {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }

    void ConfirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Изтрий " + name +" ?");
        builder.setMessage("Наистина ли искате да изтриете " + name + " ?");
        builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DBmain dbMain = new DBmain(UpdateActivity.this);
                dbMain.DeleteRow(id);
                finish();
            }
        });
        builder.setNegativeButton("Не", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}