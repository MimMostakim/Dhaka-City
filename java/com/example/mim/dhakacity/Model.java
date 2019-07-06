package com.example.mim.dhakacity;

public class Model {

    String name,image,description,address,id;

    public Model(String name, String image, String description,String address, String id) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.address=address;
        this.id = id;
    }

    public Model() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
