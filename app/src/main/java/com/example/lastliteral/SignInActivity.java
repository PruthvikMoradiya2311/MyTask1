package com.example.lastliteral;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.lastliteral.databinding.ActivitySignInBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity {

    ActivitySignInBinding binding;
    FirebaseAuth firebaseAuth;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();


        if (user != null) {
            finish();
            startActivity(new Intent(SignInActivity.this, MainActivity.class));
        }

        binding.signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String useremail = binding.etEmail.getText().toString();
                String userpassword = binding.etPassword.getText().toString();

                if (TextUtils.isEmpty(useremail) || TextUtils.isEmpty(userpassword)){

                    Toast.makeText(SignInActivity.this, "All field are required", Toast.LENGTH_SHORT).show();
                }else if (!useremail.matches(emailPattern)){

                    Toast.makeText(SignInActivity.this, "Enter valid Email!", Toast.LENGTH_SHORT).show();
                }else if (userpassword.length() < 8){

                    Toast.makeText(SignInActivity.this, "Invalid Password!", Toast.LENGTH_SHORT).show();
                }else {

                    firebaseAuth.signInWithEmailAndPassword(useremail, userpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            
                            if (task.isSuccessful()){
                                
                                Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                                startActivity(intent);
                                finishAffinity();
                                Toast.makeText(SignInActivity.this, "Successfully login", Toast.LENGTH_SHORT).show();
                            }else {

                                Toast.makeText(SignInActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }

            }
        });

        binding.createNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
            }
        });
    }
}