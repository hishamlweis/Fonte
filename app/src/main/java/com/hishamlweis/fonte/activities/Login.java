package com.hishamlweis.fonte.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.hishamlweis.fonte.helper.InputValidation;
import com.hishamlweis.fonte.sql.DBHelper;
import com.hishamlweis.fonte.R;
import com.hishamlweis.fonte.utils.PreferenceUtils;

public class Login extends AppCompatActivity {

    TextInputLayout textInputLayoutEmail, textInputLayoutPassword;
    TextInputEditText textInputEditTextEmail, textInputEditTextPassword;
    Button btnLogin;
    TextView Forgotpassword, signup;

    InputValidation inputValidation;
    DBHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);
        btnLogin = (Button) findViewById(R.id.customerLoginButton);
        signup = (TextView) findViewById(R.id.customerDontHaveAnAccount);

        databaseHelper = new DBHelper(this);
        inputValidation = new InputValidation(this);

        PreferenceUtils utils = new PreferenceUtils();

        if (utils.getEmail(this) != null) {
            Intent intent = new Intent(Login.this, MainActivity2.class);
            startActivity(intent);
            finish();
        } else {

        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyFromSQLite();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Registration.class);
                startActivity(intent);
            }
        });

    }

    private void verifyFromSQLite() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_email))) {
            return;
        }
        String email = textInputEditTextEmail.getText().toString().trim();
        String password = textInputEditTextPassword.getText().toString().trim();

        if (databaseHelper.checkUser(email, password)) {
            PreferenceUtils.saveEmail(email, this);
            PreferenceUtils.savePassword(password, this);
            Intent accountsIntent = new Intent(Login.this, MainActivity2.class);
            accountsIntent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
            emptyInputEditText();
            startActivity(accountsIntent);
            finish();
        } else {
            Toast.makeText(this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
        }
    }

    private void emptyInputEditText() {
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
    }
}