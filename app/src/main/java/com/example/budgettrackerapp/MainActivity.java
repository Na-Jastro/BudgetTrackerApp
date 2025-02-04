package com.example.budgettrackerapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText mEmail;
    private EditText mPass;
    private Button btnLogin;
    private TextView mForgetPassword;
    private TextView mSignuphere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeUI();
        setOnClickListeners();
    }

    private void initializeUI() {
        mEmail = findViewById(R.id.email_login);
        mPass = findViewById(R.id.password_login);
        btnLogin = findViewById(R.id.btn_login);
        mForgetPassword = findViewById(R.id.forget_password);
        mSignuphere = findViewById(R.id.signup_registration);
    }

    private void setOnClickListeners() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleLogin();
            }
        });

        mSignuphere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Correct context: MainActivity.this
                startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
            }
        });

        mForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ResetActivity.class));
            }
        });
    }

    private void handleLogin() {
        String email = mEmail.getText().toString().trim();
        String pass = mPass.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            mEmail.setError("Email Required..");
            return;
        }

        if (TextUtils.isEmpty(pass)) {
            mPass.setError("Password Required..");
        }

        // Add actual login validation here, if necessary
    }
}
