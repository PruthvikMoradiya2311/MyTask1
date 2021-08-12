package com.example.lastliteral;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.lastliteral.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class SignUpActivity extends AppCompatActivity {

    ActivitySignUpBinding binding;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore fireStore;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String mobilePattern = "[0-9]{10}";
    String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();
        fireStore = FirebaseFirestore.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();


        if (user != null) {
            finish();
            startActivity(new Intent(SignUpActivity.this, MainActivity.class));
        }



        binding.signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = binding.etName.getText().toString();
                String email = binding.etEmail.getText().toString();
                String password = binding.etPassword.getText().toString();
                String city = binding.etCity.getText().toString();
                String phone = binding.etPhone.getText().toString();


                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password) ||
                        TextUtils.isEmpty(city) || TextUtils.isEmpty(phone)){

                    Toast.makeText(SignUpActivity.this, "Please Enter all the details", Toast.LENGTH_SHORT).show();
                }else if (!email.matches(emailPattern)){

                    Toast.makeText(SignUpActivity.this, "Email pattern is not verified", Toast.LENGTH_SHORT).show();
                }else if (!phone.matches(mobilePattern)){

                    Toast.makeText(SignUpActivity.this, "Mobile Pattern is not Verified", Toast.LENGTH_SHORT).show();
                }else if (password.length() < 8){

                    Toast.makeText(SignUpActivity.this, "Password length is to short", Toast.LENGTH_SHORT).show();
                }else {

                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()){

                                Toast.makeText(SignUpActivity.this, "User is Created", Toast.LENGTH_SHORT).show();
                               userId =  firebaseAuth.getCurrentUser().getUid();
                                DocumentReference documentReference = fireStore.collection("users").document(userId);
                                Map<String, Object> user = new HashMap<>();
                                user.put("name", name);
                                user.put("email", email);
                                user.put("password", password);
                                user.put("city", city);
                                user.put("phone", phone);

                                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {

                                        Log.d(TAG,"user is successfully created" + userId);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                        Log.d(TAG,"On Failed:" + e.toString());

                                    }
                                });

                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                finishAffinity();

                            }else {

                                Toast.makeText(SignUpActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }

            }
        });


        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);

            }
        });


    }
}