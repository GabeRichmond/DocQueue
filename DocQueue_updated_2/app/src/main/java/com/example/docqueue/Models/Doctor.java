package com.example.docqueue.Models;

public class Doctor {

    public String name;
    public String specialization;
    public String address;
    public String schedule;
    public String contact;
    public String email;
    public String password;

    public Doctor(){}

    public Doctor(String name, String specialization, String address, String schedule, String contact, String email, String password) {
        this.name = name;
        this.specialization = specialization;
        this.address = address;
        this.schedule = schedule;
        this.contact = contact;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
