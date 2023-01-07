package com.example.final_project.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_project.MainActivity;
import com.example.final_project.R;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.final_project.adapter.myAdapter;
import com.example.final_project.models.dataModel;
import com.example.final_project.recyclerViewInterface;
import com.example.final_project.storage.animalsData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class FragmentHome extends Fragment implements recyclerViewInterface{

    private RecyclerView recyclerView;
    private myAdapter adapter;
    private DatabaseReference databaseref;
    private List<dataModel> dataSet;

//    private LinearLayoutManager layoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);

        Button createButton = view.findViewById(R.id.createButton);

        recyclerView = view.findViewById(R.id.myRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        dataSet = new ArrayList<>();
        databaseref = FirebaseDatabase.getInstance().getReference("animals");
        databaseref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapeshot : snapshot.getChildren()){
                    dataModel dataMod = postSnapeshot.getValue(dataModel.class);
                    dataSet.add(dataMod);
                }

                adapter = new myAdapter(recyclerView.getContext(), dataSet,FragmentHome.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("pos",1);
                Log.d("mag","POS Before: "+bundle.getInt("pos"));

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                Fragment_Create_animal fragment_create_animal = new Fragment_Create_animal();
                fragment_create_animal.setArguments(bundle);

                fragmentTransaction.replace(R.id.fragmentContainerView, fragment_create_animal).commit();
            }
        });
        return  view;
    }


    public void onItemClick(int pos, View view) {

        Bundle bundle = new Bundle();
//        bundle.putInt("pos",pos);
        Log.d("mag","POS Before: "+bundle.getInt("pos"));
        dataModel animal =  dataSet.get(pos);
        bundle.putString("name",animal.getName());
        bundle.putString("data",animal.getDataAnimal());

        String mHttps = "https://firebasestorage.googleapis.com/v0/b/animalfttdb.appspot.com/o/animals%2F";
        String nameImageAnimal  = animal.getImage().replace("/animals/", "");
        bundle.putString("image",mHttps + nameImageAnimal + "?alt=media");

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment_animal_data fragmentAnimalData = new Fragment_animal_data();
        fragmentAnimalData.setArguments(bundle);

        fragmentTransaction.replace(R.id.fragmentContainerView, fragmentAnimalData).commit();
    }
}


