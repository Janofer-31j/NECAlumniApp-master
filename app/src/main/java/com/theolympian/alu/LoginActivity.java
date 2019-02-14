package com.theolympian.alu;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSignin;
    private EditText actvEmail;
    private EditText etPassword;
    private TextView tvRegister;

    private ProgressDialog progressdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnSignin =(Button) findViewById(R.id.btnSignin);
        etPassword = (EditText)findViewById(R.id.etPassword);
        actvEmail = (EditText) findViewById(R.id.actvEmail);
        tvRegister = (TextView) findViewById(R.id.tvRegister);

        progressdialog = new ProgressDialog(this);
        btnSignin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == btnSignin){
            userSignin();
        }
        if(v == tvRegister){
            startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
        }
    }

    private void userSignin() {
        String email = actvEmail.getText().toString();
        String password = etPassword.getText().toString();

        boolean cancel = false;
        final View[] focusView = {null};

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            etPassword.setError(getString(R.string.error_invalid_password));
            focusView[0] = etPassword;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            actvEmail.setError(getString(R.string.error_field_required));
            focusView[0] = actvEmail;
            cancel = true;
        } else if (!isEmailValid(email)) {
            actvEmail.setError(getString(R.string.error_invalid_email));
            focusView[0] = actvEmail;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView[0].requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            progressdialog.setMessage("Signing in");
            progressdialog.show();

            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        }
    }

    private boolean isEmailValid(String email){
        return email.contains("@");
    }

    private boolean isPasswordValid(String password){
        return password.length()>5;
    }

}