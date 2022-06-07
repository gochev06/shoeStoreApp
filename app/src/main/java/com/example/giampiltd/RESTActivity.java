package com.example.giampiltd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class RESTActivity extends AppCompatActivity {
    protected String urlAddress = "https://gp.pashev.com:93/testTels/service.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest);
    }
}