package com.find.lostandfound;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class lost_items {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id private Long id;

    private String userName;
    private String description;
    private String image;
    private boolean claimed = false;
    private String location;


    public lost_items() {
    }


    public lost_items( String description, String image,String userName, String location){

        this.image = image;
        this.description=description;
        this.location = location;
        this.userName = userName;


    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id=id;

    }
    public String getLocation(){
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isClaimed() {
        return claimed;
    }
    public void setClaimed(boolean claimed){
        this.claimed = claimed;
    }

    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description=description;
    }
    public String getImage(){
        return image;
    }
    public void setImage(String image){
        this.image=image;

    }

    public String getUserName(){
        return userName;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }

    }

