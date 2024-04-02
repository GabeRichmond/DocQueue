package com.example.docqueue;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.docqueue.Models.Doctor;

import java.util.ArrayList;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Doctor> doctorList;

    public DoctorAdapter(Context context, ArrayList<Doctor> doctorList) {
        this.context = context;
        this.doctorList = doctorList;
    }

    @NonNull
    @Override
    public DoctorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.recyclerview, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final DoctorAdapter.ViewHolder holder, int position) {
        final Doctor doctor = doctorList.get(position);
        holder.nameText.setText(doctor.getName());
        holder.specText.setText(doctor.getSpecialization());
        holder.locText.setText(doctor.getAddress());
        holder.schedText.setText(doctor.getSchedule());
        holder.setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent confirm = new Intent(holder.setButton.getContext(), PatientConfirm.class);
                holder.setButton.getContext().startActivity(confirm);
            }
        });
    }

    @Override
    public int getItemCount() {
        return doctorList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView nameText, specText, locText, schedText;
        public View layout;
        public Button setButton;

        public ViewHolder(@NonNull View v) {
            super(v);
            layout = v;

            nameText = v.findViewById(R.id.name);
            specText = v.findViewById(R.id.textView_specialization);
            locText = v.findViewById(R.id.textView_location);
            schedText = v.findViewById(R.id.textView_day);
            setButton = v.findViewById(R.id.btn_book);
        }
    }

    public void add(int position, Doctor doctor) {
        doctorList.add(position, doctor);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        doctorList.remove(position);
        notifyItemRemoved(position);
    }
}
