package com.example.final_project.adapter;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_project.R;
import com.example.final_project.fragments.FragmentHome;

import java.util.List;

public class StartAdapter {
    private myAdapter adapter;
    private RecyclerView recyclerView;


    public void startAdapterOnCall(View v, List dataSet ,FragmentHome fragmentHome){

        recyclerView = v.findViewById(R.id.myRecyclerView);
        recyclerView.clearAnimation();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));

        adapter = new myAdapter(recyclerView.getContext(), dataSet,fragmentHome);
        recyclerView.setAdapter(adapter);
    }
}
