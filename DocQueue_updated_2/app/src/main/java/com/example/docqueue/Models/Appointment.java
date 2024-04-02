package com.example.docqueue.Models;

public class Appointment {

    public String date;
    public String time;
    public String patientName;
    public String age;
    public String sex;
    public String patientContact;
    public String patientEmail;
    public String doctor;

    public Appointment(String date, String time, String patientName, String age, String sex, String patientContact, String patientEmail) {
        this.date = date;
        this.time = time;
        this.patientName = patientName;
        this.age = age;
        this.sex = sex;
        this.patientContact = patientContact;
        this.patientEmail = patientEmail;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPatientContact() {
        return patientContact;
    }

    public void setContact(String contact) {
        this.patientContact = contact;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }
}
