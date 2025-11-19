package com.find.lostandfound;

import jakarta.persistence.*;

@Entity
public class lost_items {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;


    private String student_id;

    private String name;
    private String description;
    private String location;

    private boolean claimed = false;

    public lost_items() {
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
       this . student_id = student_id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
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
}
