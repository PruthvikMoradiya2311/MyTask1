package com.example.lastliteral;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.lastliteral.Model.userModel;
import com.example.lastliteral.databinding.ActivityAddItemBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddItemActivity extends AppCompatActivity {

    ActivityAddItemBinding binding;
    Calendar myCalendar;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddItemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

         myCalendar = Calendar.getInstance();
         firestore = FirebaseFirestore.getInstance();



        binding.itemBirthDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(AddItemActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        binding.AddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String itemName = binding.itemName.getText().toString();
                String itemHeight = binding.itemHeight.getText().toString();
                String itemWeight = binding.itemWeight.getText().toString();
                String itemBirthDate = binding.itemBirthDate.getText().toString();


                addDataToFirestore(itemName, itemHeight, itemWeight, itemBirthDate);


            }
        });

    }

    private void addDataToFirestore(String itemName, String itemHeight, String itemWeight, String itemBirthDate) {

        CollectionReference dbCourses = firestore.collection("Person");

        userModel userModel = new userModel(itemName, itemHeight, itemWeight, itemBirthDate);

        dbCourses.add(userModel).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {

                Toast.makeText(AddItemActivity.this, "Your user has been added to Firebase Firestore", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddItemActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(AddItemActivity.this, "Fail to add course \n" + e, Toast.LENGTH_SHORT).show();
            }
        });

    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };


    private void updateLabel() {

        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        binding.itemBirthDate.setText(sdf.format(myCalendar.getTime()));
    }


}