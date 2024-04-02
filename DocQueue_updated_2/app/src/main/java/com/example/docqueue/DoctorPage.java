package com.example.docqueue;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.docqueue.ui.TabbedActivity.SectionsPagerAdapter;

public class DoctorPage extends AppCompatActivity {

    ImageView back7;
    TextView docName, docSpec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_page);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        Bundle bundle = getIntent().getExtras();

        DbConnect dbConnect = new DbConnect(this);
        docName = findViewById(R.id.doctor_page_name);
        docSpec = findViewById(R.id.doctor_page_spec);

        docName.setText(dbConnect.getDoctorName());
        docSpec.setText(dbConnect.getDoctorSpec());

        back7 = findViewById(R.id.btn_back7);
        back7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DoctorPage.this);
                builder.setMessage("Are you sure you wanna log out?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(DoctorPage.this, "Logged Out", Toast.LENGTH_LONG);
                        dialog.dismiss();
                        Intent success = new Intent(DoctorPage.this, WelcomePage.class);
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