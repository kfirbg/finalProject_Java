package com.example.final_project.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.final_project.R;
import com.example.final_project.adapter.myAdapter;
import com.example.final_project.models.dataModel;
import com.example.final_project.storage.animalsData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class Fragment_animal_data extends Fragment {

    private List<dataModel> dataSet;
    private DatabaseReference databaseref;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_animal_data, container, false);

        TextView textViewNameAnimalData = view.findViewById(R.id.textViewNameAnimalData);
        TextView textViewDataAnimalData = view.findViewById(R.id.textViewDataAnimalData);
        ImageView imageViewAnimalData = view.findViewById(R.id.imageViewAnimalData);

        Bundle bundle = getArguments();

        String name = bundle.getString("name");
        String data = bundle.getString("data");
        String image = bundle.getString("image");



        textViewNameAnimalData.setText(name);
        textViewDataAnimalData.setText(data);
        Picasso.with(getContext()).load(image).into(imageViewAnimalData);

//        imageViewAnimalData.setImageResource(animalsData.drawableArray[position]);


        return  view;
    }

}