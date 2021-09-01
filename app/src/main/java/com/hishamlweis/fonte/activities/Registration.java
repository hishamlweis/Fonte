package com.hishamlweis.fonte.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.hishamlweis.fonte.helper.InputValidation;
import com.hishamlweis.fonte.model.User;
import com.hishamlweis.fonte.sql.DBHelper;
import com.hishamlweis.fonte.R;

public class Registration extends AppCompatActivity {

    TextInputLayout textInputLayoutName;
    TextInputLayout textInputLayoutEmail;
    TextInputLayout textInputLayoutPassword;
    TextInputLayout textInputLayoutConfirmPassword;

    TextInputEditText textInputEditTextName;
    TextInputEditText textInputEditTextEmail;
    TextInputEditText textInputEditTextPassword;
    TextInputEditText textInputEditTextConfirmPassword;

    Button btnSignUp, btnSignIn;

    InputValidation inputValidation;
    DBHelper databaseHelper;
    User user;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        getSupportActionBar().hide();

        textInputLayoutName = (TextInputLayout) findViewById(R.id.textInputLayoutName);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputLayoutConfirmPassword = (TextInputLayout) findViewById(R.id.textInputLayoutConfirmPassword);

        textInputEditTextName = (TextInputEditText) findViewById(R.id.textInputEditTextName);
        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);
        textInputEditTextConfirmPassword = (TextInputEditText) findViewById(R.id.textInputEditTextConfirmPassword);

        btnSignUp = (Button) findViewById(R.id.signupBtnCustomer);
        btnSignIn = (Button) findViewById(R.id.emailBTNCustomer);

        inputValidation = new InputValidation(this);
        databaseHelper = new DBHelper(this);
        user = new User();

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postDataToSQLite();
                Intent intent = new Intent(Registration.this, Login.class);
                startActivity(intent);
                finish();
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void postDataToSQLite() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextName, textInputLayoutName, getString(R.string.error_message_name))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_password))) {
            return;
        }
        if (!inputValidation.isInputEditTextMatches(textInputEditTextPassword, textInputEditTextConfirmPassword,
                textInputLayoutConfirmPassword, getString(R.string.error_password_match))) {
            return;
        }

        if (!databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim())) {

            user.setName(textInputEditTextName.getText().toString().trim());
            user.setEmail(textInputEditTextEmail.getText().toString().trim());
            user.setPassword(textInputEditTextPassword.getText().toString().trim());

            databaseHelper.addUser(user);

            Toast.makeText(this, "Registered Successfully.", Toast.LENGTH_SHORT).show();
            emptyInputEditText();


        } else {
            Toast.makeText(this, "Email already exists.", Toast.LENGTH_SHORT).show();
        }


    }

    private void emptyInputEditText() {
        textInputEditTextName.setText(null);
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
        textInputEditTextConfirmPassword.setText(null);
    }

}