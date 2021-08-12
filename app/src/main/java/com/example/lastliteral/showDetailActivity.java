package com.example.lastliteral;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class showDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String height = intent.getStringExtra("height");
        String weight = intent.getStringExtra("weight");
        String birthdate = intent.getStringExtra("birthdate");


        TextView nameTextView = findViewById(R.id.showName);
        nameTextView.setText(name);

        TextView heightTextView = findViewById(R.id.showHeight);
        heightTextView.setText(height);

        TextView weightTextView = findViewById(R.id.showWeight);
        weightTextView.setText(weight);

        TextView birthdateTextView = findViewById(R.id.showBirthdate);
        birthdateTextView.setText(birthdate);


    }
}