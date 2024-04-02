package com.example.docqueue;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.docqueue.Models.Doctor;
import com.example.docqueue.DbConnect;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class PatientHome extends AppCompatActivity {

    private RecyclerView docView;
    private RecyclerView.LayoutManager layoutManager;
    private DoctorAdapter adapter;
    private SQLiteDatabase database;
    DbConnect db;
    private ImageView back1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        docView = findViewById(R.id.listView);
        docView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        docView.setLayoutManager(layoutManager);

        db = new DbConnect(PatientHome.this);
        database = db.getWritableDatabase();

        adapter = new DoctorAdapter(this, db.doctorList());
        docView.setAdapter(adapter);

        back1 = findViewById(R.id.btn_back);
        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
