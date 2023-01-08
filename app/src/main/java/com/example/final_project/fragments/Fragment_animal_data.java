package com.example.final_project.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.final_project.R;
import com.squareup.picasso.Picasso;

public class Fragment_animal_data extends Fragment {

    TextView textViewDataAnimalData;
    TextView textViewNameAnimalData;
    ImageView imageViewAnimalData;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.fragment_animal_data, container, false);

        textViewNameAnimalData = view.findViewById(R.id.textViewNameAnimalData);
        textViewDataAnimalData = view.findViewById(R.id.textViewDataAnimalData);
        imageViewAnimalData = view.findViewById(R.id.imageViewAnimalData);

        textViewDataAnimalData.setMovementMethod(new ScrollingMovementMethod());

        Bundle bundle = getArguments();

        String name = bundle.getString("name");
        String data = bundle.getString("data");
        String image = bundle.getString("image");

        textViewNameAnimalData.setText(name);
        textViewDataAnimalData.setText(data);
        Picasso.with(getContext()).load(image).into(imageViewAnimalData);

        return  view;
    }
}