package com.example.park.database1;

/**
 * Created by Park on 2015-11-23.
 */
public class Student {

    private int id;
    private String name;

    public long getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }
}
