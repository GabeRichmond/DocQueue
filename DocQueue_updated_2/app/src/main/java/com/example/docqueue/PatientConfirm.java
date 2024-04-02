package com.example.docqueue;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class PatientConfirm extends AppCompatActivity {

    ImageView back2;
    Button submit;
    EditText date, time, name, age, sex, contact, email;
    DbConnect dbConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        back2 = findViewById(R.id.btn_back2);
        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        date = findViewById(R.id.edit_date);
        time = findViewById(R.id.edit_time);
        name = findViewById(R.id.edit_name);
        age = findViewById(R.id.edit_age);
        sex = findViewById(R.id.edit_sex);
        contact = findViewById(R.id.edit_contact);
        email = findViewById(R.id.edit_email);
        dbConnect = new DbConnect(this);
        dbConnect.getWritableDatabase();

        submit = findViewById(R.id.btn_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*try {
                    dbConnect.confirmPatientData(name.getText().toString(), age.getText().toString(), sex.getText().toString(), null, contact.getText().toString(), null, email.getText().toString());
                } catch (Exception e) {
                    Toast.makeText(PatientConfirm.this, "Error", Toast.LENGTH_LONG).show();
                }*/
                AlertDialog.Builder builder = new AlertDialog.Builder(PatientConfirm.this);
                builder.setMessage("Are all of your details correct?");
                builder.setCancelable(false);
                builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //SAVE TO DATABASE

                        Toast.makeText(PatientConfirm.this, "Appointment Set", Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                        Intent success = new Intent(PatientConfirm.this, PatientHome.class);
                        startActivity(success);
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });
    }
}
