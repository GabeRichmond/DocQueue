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

import com.example.docqueue.Models.Doctor;

import java.sql.SQLException;

public class EditProfile extends AppCompatActivity {

    Button update;
    ImageView back5;
    EditText name, spec, address, hours, contact, email, pass;
    DbConnect dbConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        dbConnect = new DbConnect(this);
        dbConnect.getWritableDatabase();

        name = findViewById(R.id.update_name);
        spec = findViewById(R.id.update_specialization);
        address = findViewById(R.id.update_address);
        hours = findViewById(R.id.update_sched);
        contact = findViewById(R.id.update_contact);
        email = findViewById(R.id.update_email);
        pass = findViewById(R.id.update_pass);

        //auto-fill form
        name.setText(dbConnect.getDoctorName());
        spec.setText(dbConnect.getDoctorSpec());
        address.setText(dbConnect.getClinicAddress());
        hours.setText(dbConnect.getClinicHours());
        contact.setText(dbConnect.getDocContactNumber());
        email.setText(dbConnect.getDocEmailAddress());


        update = findViewById(R.id.btn_update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // UPDATE DATABASE
                try {
                    dbConnect.updateDoctorData(name.getText().toString(), spec.getText().toString(),
                            address.getText().toString(), hours.getText().toString(), contact.getText().toString(),
                            email.getText().toString(), pass.getText().toString());

                    Toast.makeText(EditProfile.this, "Successfully updated Data! ", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(EditProfile.this, "Error Updating Data! ", Toast.LENGTH_LONG).show();
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(EditProfile.this);
                builder.setMessage("Save Profile Updates");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //SAVE TO DATABASE

                        Toast.makeText(EditProfile.this, "Profile Updated", Toast.LENGTH_LONG);
                        dialog.dismiss();
                        Intent success = new Intent(EditProfile.this, DoctorPage.class);
                        finish();
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

        back5 = findViewById(R.id.btn_back5);
        back5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}