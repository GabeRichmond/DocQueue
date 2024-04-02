package com.example.docqueue;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.docqueue.Models.Doctor;

public class SignUpPage extends AppCompatActivity {

    DbConnect dbConnect;

    EditText inputName, inputSpec, inputHours, inputLocation, inputContact, inputEmail, inputPassword;
    Button btnSubmit;
    ImageView back4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        inputName = findViewById(R.id.nameEditText);
        inputSpec = findViewById(R.id.SpecializationEditText);
        inputLocation = findViewById(R.id.LocationEditText);
        inputHours = findViewById(R.id.HoursEditText);
        inputContact = findViewById(R.id.ContactEditText);
        inputEmail = findViewById(R.id.EmailEditText);
        inputPassword = findViewById(R.id.PasswordEditText);

        btnSubmit = findViewById(R.id.btn_register);
            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    saveDoctor();
                }
            });

        back4 = findViewById(R.id.btn_back4);
        back4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void saveDoctor(){
        String name = inputName.getText().toString();
        String spec = inputSpec.getText().toString();
        String location = inputLocation.getText().toString();
        String sched = inputHours.getText().toString();
        String contact = inputContact.getText().toString();
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();
        dbConnect = new DbConnect(this);

        try {
            if (name.isEmpty()) {
                inputName.setError("Please Input Name");
            }
            if (spec.isEmpty()) {
                inputSpec.setError("Please Input Specialization");
            }
            if (location.isEmpty()) {
                inputLocation.setError("Please Input Clinic Location");
            }
            if (sched.isEmpty()) {
                inputHours.setError("Please Input Clinic Hours");
            }
            if (contact.isEmpty()) {
                inputContact.setError("Please Input Contact Details");
            }
            if (email.isEmpty()) {
                inputEmail.setError("Please Input Email Address");
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                inputEmail.setError("Please input a valid Email Address");
            }
            if (password.isEmpty()) {
                inputPassword.setError("Please Input Password");
            }
            else {
                Doctor doctor = new Doctor(name, spec, location, sched, contact, email, password);
                dbConnect.addDoctor(doctor);
                Toast.makeText(SignUpPage.this, "Account Created! ", Toast.LENGTH_LONG).show();
                Intent i = new Intent(SignUpPage.this, LoginPage.class);
                finishActivity(0);
                startActivity(i);
            }
        } catch (SQLException e) {
            Toast.makeText(SignUpPage.this, "Error Saving Details! ", Toast.LENGTH_LONG).show();
        }
    }
}
