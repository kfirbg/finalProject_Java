package com.example.final_project.fragments;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.final_project.MainActivity;
import com.example.final_project.R;
import com.example.final_project.models.dataModel;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.squareup.picasso.Picasso;

import java.time.Instant;
import java.util.ArrayList;


public class Fragment_Create_animal extends Fragment {

    EditText nameText;
    EditText dataText;
    ImageView imageView;
    Button createButton;
    Button imageButton;

    private Uri uri;


    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__create_animal, container, false);

        nameText = view.findViewById(R.id.editTextTextPersonName);
        dataText = view.findViewById(R.id.editTextTextPersonData);
        imageView = view.findViewById(R.id.createImageView);
        createButton = view.findViewById(R.id.createButton);
        imageButton = view.findViewById(R.id.button2);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 100);
            }
        });

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.sendData(nameText,dataText,imageView,uri);
                Bundle bundle = new Bundle();
//        bundle.putInt("pos",1);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                FragmentHome fragmentHome = new FragmentHome();
                fragmentHome.setArguments(bundle);

                fragmentTransaction.replace(R.id.fragmentContainerView, fragmentHome).commit();
            }
        });


        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && requestCode==100 && data != null && data.getData() != null){
            uri = data.getData();
            Picasso.with(this.getContext()).load(uri).into(imageView);

        }
    }
}