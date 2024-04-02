package com.example.docqueue;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.docqueue.Models.Doctor;

public class LoginPage extends AppCompatActivity {
    DbConnect dbConnect = new DbConnect(this);
    TextView textView;
    EditText email, password;
    Button button;
    ImageView back3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textView = findViewById(R.id.textView_create);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent start = new Intent(LoginPage.this, SignUpPage.class);
                finish();
                startActivity(start);
            }
        });

        back3 = findViewById(R.id.btn_back3);
        back3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        email = findViewById(R.id.editText);
        password = findViewById(R.id.editText2);
        button = findViewById(R.id.btn_login_doctor);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emailAddress = email.getText().toString();
                String Password = password.getText().toString();
                dbConnect.getReadableDatabase();
                try {
                    if (validate()) {
                        Doctor currentDoctor = dbConnect.checkUser(new Doctor(null, null, null, null, null, emailAddress, Password));
                        if (currentDoctor !=null) {
                            Intent DocDashBoard = new Intent(LoginPage.this, DoctorPage.class);
                            finish();
                            startActivity(DocDashBoard);
                        }
                        else {
                            Toast.makeText(LoginPage.this, "Invalid Username or Password", Toast.LENGTH_LONG).show();
                        }
                    }
                } catch (SQLException e) {
                    Toast.makeText(LoginPage.this, "App Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public String getEmail() {
        return email.getText().toString();
    }
    public String getPass() { return password.getText().toString();}

    public boolean validate() {
        boolean valid = false;
        String emailAddress = email.getText().toString().trim();
        String Password = password.getText().toString().trim();

        if(!Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()) {
            valid = false;
            email.setError("Invalid Email! ");
        }
        else {
            valid = true;
            email.setError(null);
        }
        if (Password.isEmpty()) {
            password.setError("Input Password");
        }
        else {
            valid = true;
            password.setError(null);
        }
        return valid;
    }
}