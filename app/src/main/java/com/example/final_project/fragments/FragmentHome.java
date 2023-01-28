package com.example.final_project.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.final_project.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.final_project.adapter.StartAdapter;
import com.example.final_project.models.dataModel;
import com.example.final_project.recyclerViewInterface;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.google.mlkit.nl.translate.Translation;
import com.google.mlkit.nl.translate.Translator;
import com.google.mlkit.nl.translate.TranslatorOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;


public class FragmentHome extends Fragment implements recyclerViewInterface{

    private DatabaseReference databaseref;
    private List<dataModel> dataSet;
    private Button  multipleLanguagesBtn;
    Translator englishHebrewTranslator;
    private StartAdapter startAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        dataSet = new ArrayList<>();


            databaseref = FirebaseDatabase.getInstance().getReference("animals");
            databaseref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    dataSet.clear();
                    for (DataSnapshot postSnapeshot : snapshot.getChildren()){
                        dataModel dataMod = postSnapeshot.getValue(dataModel.class);

                        dataSet.add(dataMod);
                    }
                    startAdapter=new StartAdapter();
                    startAdapter.startAdapterOnCall(view,dataSet,FragmentHome.this);

                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


            databaseref = FirebaseDatabase.getInstance().getReference("animals");
            databaseref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    dataSet.clear();
                    for (DataSnapshot postSnapeshot : snapshot.getChildren()){
                        dataModel dataMod = postSnapeshot.getValue(dataModel.class);
                        dataSet.add(dataMod);
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            TranslatorOptions options =
                    new TranslatorOptions.Builder()
                            .setSourceLanguage(TranslateLanguage.ENGLISH)
                            .setTargetLanguage(TranslateLanguage.HEBREW)
                            .build();
            englishHebrewTranslator = Translation.getClient(options);

            englishHebrewTranslator.downloadModelIfNeeded().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {

                    for (int i=0; i<dataSet.size();i++ ){
                        int finalI = i;

                        englishHebrewTranslator.translate(dataSet.get(i).getName().toString()).addOnSuccessListener(new OnSuccessListener<String>() {
                            @Override
                            public void onSuccess(String s) {
//                                startAdapter=new StartAdapter();
                                dataSet.get(finalI).setName(s.toString());

                            }
                        });
                        englishHebrewTranslator.translate(dataSet.get(i).getDataAnimal().toString()).addOnSuccessListener(new OnSuccessListener<String>() {
                            @Override
                            public void onSuccess(String s) {
                                dataSet.get(finalI).setDataAnimal(s.toString());


                            }
                        });

                    }
                    startAdapter=new StartAdapter();
                    startAdapter.startAdapterOnCall(view,dataSet,FragmentHome.this);
                }
            });


        Button createButton = view.findViewById(R.id.createButton);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_fragmentHome_to_fragment_Create_animal);
            }
        });


        multipleLanguagesBtn = view.findViewById(R.id.multipleLanguagesButton);
        multipleLanguagesBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                TranslatorOptions options =
                        new TranslatorOptions.Builder()
                                .setSourceLanguage(TranslateLanguage.ENGLISH)
                                .setTargetLanguage(TranslateLanguage.HEBREW)
                                .build();
                englishHebrewTranslator = Translation.getClient(options);

                englishHebrewTranslator.downloadModelIfNeeded().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                        for (int i=0; i<dataSet.size();i++ ){
                            int finalI = i;

                            englishHebrewTranslator.translate(dataSet.get(i).getName().toString()).addOnSuccessListener(new OnSuccessListener<String>() {
                                @Override
                                public void onSuccess(String s) {
                                    dataSet.get(finalI).setName(s.toString());

                                }
                            });
                            englishHebrewTranslator.translate(dataSet.get(i).getDataAnimal().toString()).addOnSuccessListener(new OnSuccessListener<String>() {
                                @Override
                                public void onSuccess(String s) {
                                    dataSet.get(finalI).setDataAnimal(s.toString());
                                }
                            });
                        }
                        startAdapter=new StartAdapter();
                        startAdapter.startAdapterOnCall(view,dataSet,FragmentHome.this);
                    }
                });
            }
        });

        return  view;
    }



    public void onItemClick(int pos, View view) {

        Bundle bundle = new Bundle();

        dataModel animal =  dataSet.get(pos);
        bundle.putString("name",animal.getName());
        bundle.putString("data",animal.getDataAnimal());

        String mHttps = "https://firebasestorage.googleapis.com/v0/b/animalfttdb.appspot.com/o/animals%2F";
        String nameImageAnimal  = animal.getImage().replace("/animals/", "");
        bundle.putString("image",mHttps + nameImageAnimal + "?alt=media");

        Fragment_animal_data fragmentAnimalData = new Fragment_animal_data();
        fragmentAnimalData.setArguments(bundle);

        Navigation.findNavController(view).navigate(R.id.action_fragmentHome_to_fragment_animal_data, bundle);
    }


}


