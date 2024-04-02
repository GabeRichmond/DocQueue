package com.example.docqueue;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DocProfileFrag extends Fragment {

    Button update;
    DbConnect dbConnect;
    TextView location, hours, email, contact;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        dbConnect = new DbConnect(getActivity());
        location = view.findViewById(R.id.textView_location);
        hours = view.findViewById(R.id.textView_hours);
        contact = view.findViewById(R.id.textView_contact);
        email = view.findViewById(R.id.textView_email);

        location.setText(dbConnect.getClinicAddress());
        hours.setText(dbConnect.getClinicHours());
        contact.setText(dbConnect.getDocContactNumber());
        email.setText(dbConnect.getDocEmailAddress());

        update = view.findViewById(R.id.btn_edit);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent edit = new Intent(getActivity(), EditProfile.class);
                startActivity(edit);
            }
        });
        return view;
    }
}




