package com.example.final_project.models;

public class dataModel {

    private String name;
    private String dataAnimal;
    private String imageUri;
    private int id;

    public dataModel(){

    }

    public dataModel(String name, String dataAnimal,String image ){
        this.name = name;
        this.dataAnimal = dataAnimal;
        this.imageUri = image;
//        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataAnimal() {
        return dataAnimal;
    }

    public void setDataAnimal(String dataAnimal) {
        this.dataAnimal = dataAnimal;
    }

    public String getImage() {
        return imageUri;
    }

    public void setImage(String image) {
        this.imageUri = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
