package com.example.final_project.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

    TextView textViewDataAnimalData;
    TextView textViewNameAnimalData;
    ImageView imageViewAnimalData;
    Button returnHome;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_animal_data, container, false);

        textViewNameAnimalData = view.findViewById(R.id.textViewNameAnimalData);
        textViewDataAnimalData = view.findViewById(R.id.textViewDataAnimalData);
        imageViewAnimalData = view.findViewById(R.id.imageViewAnimalData);
        returnHome = view.findViewById(R.id.returnHome);

        Bundle bundle = getArguments();

        String name = bundle.getString("name");
        String data = bundle.getString("data");
        String image = bundle.getString("image");

        textViewNameAnimalData.setText(name);
        textViewDataAnimalData.setText(data);
        Picasso.with(getContext()).load(image).into(imageViewAnimalData);

        returnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Bundle bundle = new Bundle();
//                bundle.putInt("pos",1);
////                Log.d("mag","POS Before: "+bundle.getInt("pos"));
//
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//                FragmentHome fragmentHome = new FragmentHome();
//                fragmentHome.setArguments(bundle);
//
//                fragmentTransaction.replace(R.id.fragment_animal_data, fragmentHome).commit();
            }
        });


        return  view;
    }

}