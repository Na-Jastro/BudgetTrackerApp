package com.example.budgettrackerapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {

    private EditText mEmail, mPassword;
    private Button mRegisterButton;
    private TextView mLoginHere;

    private ProgressDialog mDialog;
    //firebase

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        mAuth= FirebaseAuth.getInstance();
        mDialog = new ProgressDialog(this);
        initializeUI();
        setOnClickListeners();
    }

    private void initializeUI() {
        mEmail = findViewById(R.id.email_reg);
        mPassword = findViewById(R.id.password_reg);
        mRegisterButton = findViewById(R.id.btn_reg);
        mLoginHere = findViewById(R.id.login_here);
    }

    private void setOnClickListeners() {
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleRegistration();
            }
        });

        mLoginHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Correct context: RegistrationActivity.this
                //finish();  // Finish the registration activity and return to login
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }

    private void handleRegistration() {
        String email = mEmail.getText().toString().trim();
        String password = mPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            mEmail.setError("Email is required.");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            mPassword.setError("Password is required.");
            return;
        }

        mDialog.setMessage("Processing ..");
        mDialog.show();

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    mDialog.dismiss();
                    Toast.makeText(getApplicationContext(),"Registration Successfull",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                }
                else {
                    mDialog.dismiss();
                    Toast.makeText(getApplicationContext(),"Regsitration Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}


