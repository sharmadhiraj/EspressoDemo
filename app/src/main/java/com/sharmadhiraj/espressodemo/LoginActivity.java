package com.sharmadhiraj.espressodemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import static com.sharmadhiraj.espressodemo.Utils.isValidPassword;
import static com.sharmadhiraj.espressodemo.Utils.isValidUsername;

public class LoginActivity extends AppCompatActivity {

    private EditText edtUsername;
    private EditText edtPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
        btnLogin.setOnClickListener(v -> login());
    }

    private void initViews() {
        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        btnLogin = findViewById(R.id.btn_login);
    }

    private void login() {
        if (edtUsername.getText().toString().isEmpty()) {
            edtUsername.setError(getString(R.string.txt_empty_username));
        } else if (!isValidUsername(edtUsername.getText().toString())) {
            edtUsername.setError(getString(R.string.txt_invalid_username));
        } else if (edtPassword.getText().toString().isEmpty()) {
            edtPassword.setError(getString(R.string.txt_empty_password));
        } else if (!isValidPassword(edtPassword.getText().toString())) {
            edtPassword.setError(getString(R.string.txt_invalid_password));
        } else {
            navigateToHome();
        }
    }

    private void navigateToHome() {
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }

}
