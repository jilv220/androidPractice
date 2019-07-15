package com.example.myapplication.netModel;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id ) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public  void setName(String name) {
        this.name = name;
    }
    public void setAge (int age) {
        this.age = age;
    }
}
