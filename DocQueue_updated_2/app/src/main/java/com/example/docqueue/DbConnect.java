package com.example.docqueue;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.docqueue.Models.Appointment;
import com.example.docqueue.Models.Doctor;

import java.util.ArrayList;


public class DbConnect extends SQLiteOpenHelper {
    LoginPage loginPage;

    public static final String DATABASE_NAME = "DocQueue.db";
    public static final String DOC_TABLE_NAME = "doctor_table";
    public static final String DOC_COL1 = "Email";
    public static final String DOC_COL2 = "Name";
    public static final String DOC_COL3 = "Specialization";
    public static final String DOC_COL4 = "ClinicAddress";
    public static final String DOC_COL5 = "ClinicHours";
    public static final String DOC_COL6 = "ContactNumber";
    public static final String DOC_COL7 = "Password";

    public static final String P_TABLE_NAME = "appointment_table";
    public static final String P_COL1 = "Date";
    public static final String P_COL2 = "Time";
    public static final String P_COL3 = "Name";
    public static final String P_COL4 = "Age";
    public static final String P_COL5 = "Sex";
    public static final String P_COL6 = "ContactNumber";
    public static final String P_COL7 = "Email";
    public static final String P_COL8 = "Doctor";

    public DbConnect(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL(" CREATE TABLE " + DOC_TABLE_NAME + " ( " +
                    DOC_COL1 + " TEXT PRIMARY KEY, " +
                    "" + DOC_COL2 + " TEXT NOT NULL, " +
                    "" + DOC_COL3 + " TEXT NOT NULL, " +
                    "" + DOC_COL4 + " TEXT NOT NULL, " +
                    "" + DOC_COL5 + " TEXT NOT NULL, " +
                    "" + DOC_COL6 + " TEXT NOT NULL, " +
                    "" + DOC_COL7 + " TEXT NOT NULL) "
            );

            db.execSQL(" CREATE TABLE " + P_TABLE_NAME + " ( " +
                    P_COL1 + " TEXT PRIMARY KEY, " +
                    "" + P_COL2 + " TEXT NOT NULL, " +
                    "" + P_COL3 + " TEXT NOT NULL, " +
                    "" + P_COL4 + " TEXT NOT NULL, " +
                    "" + P_COL5 + " TEXT NOT NULL, " +
                    "" + P_COL6 + " TEXT NOT NULL, " +
                    "" + P_COL7 + " TEXT NOT NULL, " +
                    "" + P_COL8 + " TEXT NOT NULL, " +
                    " FOREIGN KEY ("+P_COL8+") " +
                    " REFERENCES "+DOC_TABLE_NAME+"("+DOC_COL1+"));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        db.execSQL("DROP TABLE IF EXISTS " + DOC_TABLE_NAME);
        this.onCreate(db);
    }

    //add Doctor
    public void addDoctor(Doctor doctor){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DOC_COL1, doctor.getEmail());
        values.put(DOC_COL2, doctor.getName());
        values.put(DOC_COL3, doctor.getSpecialization());
        values.put(DOC_COL4, doctor.getAddress());
        values.put(DOC_COL5, doctor.getSchedule());
        values.put(DOC_COL6, doctor.getContact());
        values.put(DOC_COL7, doctor.getPassword());

        db.insert(DOC_TABLE_NAME, null, values);
        db.close();
    }

    //add Appointment
    public void addAppointment(Appointment patient){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(P_COL1, patient.getDate());
        values.put(P_COL2, patient.getTime());
        values.put(P_COL3, patient.getPatientName());
        values.put(P_COL4, patient.getAge());
        values.put(P_COL5, patient.getSex());
        values.put(P_COL6, patient.getPatientContact());
        values.put(P_COL8, patient.getDoctor());

        db.insert(P_TABLE_NAME, null, values);
        db.close();
    }

    public void updateDoctorData(String name, String specialization, String clinicLocation, String clinicHours, String contactNumber, String emailAddress, String password) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DOC_COL2, name);
        contentValues.put(DOC_COL3, specialization);
        contentValues.put(DOC_COL4, clinicLocation);
        contentValues.put(DOC_COL5, clinicHours);
        contentValues.put(DOC_COL6, contactNumber);
        contentValues.put(DOC_COL1, emailAddress);
        contentValues.put(DOC_COL7, password);
        sqLiteDatabase.update(DOC_TABLE_NAME, contentValues, DOC_COL1 + "=?", null);
    }

    public Doctor checkUser(Doctor doctor) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DOC_TABLE_NAME,
                new String[]{DOC_COL1, DOC_COL2, DOC_COL3, DOC_COL4, DOC_COL5, DOC_COL6, DOC_COL7},
                DOC_COL1 + " =?",
                new String[]{doctor.email},
                null, null, null);
        if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0) {
            Doctor doctor1 = new Doctor(cursor.getString(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3), cursor.getString(4),
                    cursor.getString(5), cursor.getString(6));
            if(doctor.password.equalsIgnoreCase(doctor1.password)) {
                return doctor1;
            }
        }
        return null;
    }

    public ArrayList<Doctor> doctorList(){
        String query;
        query = " SELECT * FROM " + DOC_TABLE_NAME;

        ArrayList<Doctor> docList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Doctor doctor;

        if (cursor.moveToFirst()) {
            do {
                doctor = new Doctor();

                doctor.setEmail(cursor.getString(cursor.getColumnIndex(DOC_COL1)));
                doctor.setName(cursor.getString(cursor.getColumnIndex(DOC_COL2)));
                doctor.setSpecialization(cursor.getString(cursor.getColumnIndex(DOC_COL3)));
                doctor.setAddress(cursor.getString(cursor.getColumnIndex(DOC_COL4)));
                doctor.setSchedule(cursor.getString(cursor.getColumnIndex(DOC_COL5)));
                doctor.setContact(cursor.getString(cursor.getColumnIndex(DOC_COL6)));
                doctor.setPassword(cursor.getString(cursor.getColumnIndex(DOC_COL7)));
                docList.add(doctor);
            } while (cursor.moveToNext());
        }
        return docList;
    }

    /*public Doctor getDoctor(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT  * FROM " + DOC_TABLE_NAME + " WHERE Email="+ DOC_COL1;
        Cursor cursor = db.rawQuery(query, null);

        Doctor receivedDoctor = new Doctor();
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();

            receivedDoctor.setName(cursor.getString(cursor.getColumnIndex(DOC_COL2)));
            receivedDoctor.setSpecialization(cursor.getString(cursor.getColumnIndex(DOC_COL3)));
            receivedDoctor.setAddress(cursor.getString(cursor.getColumnIndex(DOC_COL4)));
            receivedDoctor.setSchedule(cursor.getString(cursor.getColumnIndex(DOC_COL5)));
        }

        return receivedDoctor;
    }*/

    public void viewDoctors() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        sqLiteDatabase.execSQL("SELECT " + DOC_COL2 + ", " + DOC_COL3 + ", " + DOC_COL4 + ", " + DOC_COL5 + ", " +DOC_COL6 + ", " + DOC_COL7 + " FROM " + DOC_TABLE_NAME);
    }

    public String getDoctorName() {
        String username = "";
        Cursor cursor = this.getReadableDatabase().query(
                DOC_TABLE_NAME, new String[] { DOC_COL2},
                null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                username = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return username;
    }

    public String getDoctorSpec() {
        String spec = "";
        Cursor cursor = this.getReadableDatabase().query(
                DOC_TABLE_NAME, new String[] { DOC_COL3},
                null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                spec = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return spec;
    }

    public String getClinicAddress() {
        String loc = "";
        Cursor cursor = this.getReadableDatabase().query(
                DOC_TABLE_NAME, new String[] { DOC_COL4},
                null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                loc = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return loc;
    }

    public String getClinicHours() {
        String hours = "";
        Cursor cursor = this.getReadableDatabase().query(
                DOC_TABLE_NAME, new String[] { DOC_COL5},
                null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                hours = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return hours;
    }

    public String getDocContactNumber() {
        String number = "";
        Cursor cursor = this.getReadableDatabase().query(
                DOC_TABLE_NAME, new String[] { DOC_COL6},
                null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                number = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return number;
    }

    public String getDocEmailAddress() {
        String email = "";
        Cursor cursor = this.getReadableDatabase().query(
                DOC_TABLE_NAME, new String[] { DOC_COL1},
                null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                email = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return email;
    }
}
