package com.example.final_project.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_project.MainActivity;
import com.example.final_project.models.dataModel;
import com.example.final_project.R;
import com.example.final_project.recyclerViewInterface;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class myAdapter extends RecyclerView.Adapter<myAdapter.myHolder>{

    private Context context;
    private List<dataModel> fullDataSet;
//    public FirebaseStorage mStorage;
    private  final recyclerViewInterface recyclerViewInterface;


    public myAdapter(Context context,  List<dataModel> dataModel, com.example.final_project.recyclerViewInterface recyclerViewInterface){
        this.fullDataSet = dataModel;
        this.recyclerViewInterface = recyclerViewInterface;
        this.context = context;
    }

        public static class myHolder extends RecyclerView.ViewHolder{
            TextView textViewName;
            TextView textViewDataAnimal;
            ImageView imageView;

            public myHolder(View item ,recyclerViewInterface recyclerViewInterface){
                super(item);
                textViewName =  item.findViewById(R.id.textViewName);
                textViewDataAnimal =  item.findViewById(R.id.textViewDataAnimal);
                imageView = item.findViewById(R.id.imageView);

                item.setOnClickListener(view -> {
                    if(recyclerViewInterface != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(position,view);

                        }
                    }
                });
            }
        }

        @NonNull
        @Override
        public myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);

            myHolder myViewHolder = new myHolder(view, recyclerViewInterface);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull myHolder holder, int position) {

            dataModel datarec = fullDataSet.get(position);
            holder.textViewName.setText(datarec.getName());
            holder.textViewDataAnimal.setText(datarec.getDataAnimal());
            String mImage = datarec.getImage();

            String mHttps = "https://firebasestorage.googleapis.com/v0/b/animalfttdb.appspot.com/o/animals%2F";
            String nameAnimal  = mImage.replace("/animals/", "");

            Picasso.with(context).load(mHttps + nameAnimal + "?alt=media").into(holder.imageView);
        }

        @Override
        public int getItemCount() {
            return  fullDataSet.size();
        }
}
