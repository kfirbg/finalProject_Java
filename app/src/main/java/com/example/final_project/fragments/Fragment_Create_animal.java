package com.example.final_project.fragments;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.final_project.MainActivity;
import com.example.final_project.R;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;


public class Fragment_Create_animal extends Fragment {

    EditText nameText;
    EditText dataText;
    ImageView imageView;
    Button createButton;
    Button imageButton;
    private boolean image_flag = false;
    private Uri uri;


    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
                image_flag = true;
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

                if(nameText.getText().toString().isEmpty()){
                    Snackbar.make(mainActivity,v,"Please full name on animal",Snackbar.LENGTH_SHORT).show();

                }else if(dataText.getText().toString().isEmpty()){
                    Snackbar.make(mainActivity,v,"Please full data on animal",Snackbar.LENGTH_SHORT).show();
                }
                else if(image_flag == false){
                    Snackbar.make(mainActivity,v,"Image is empty, please choose image",Snackbar.LENGTH_SHORT).show();
                }
                else{
                    mainActivity.sendData(nameText,dataText,uri);
                    Navigation.findNavController(view).navigate(R.id.action_fragment_Create_animal_to_fragmentHome);
                }
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