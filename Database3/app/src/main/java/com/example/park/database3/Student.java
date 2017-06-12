package com.example.park.database3;

/**
 * Created by Park on 2015-11-23.
 */
public class Student {

    private int id;
    private String name;
    private int k,n,j;

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

    public void setk(int k)
    {
        this.k = k;
    }
    public void setn(int n)
    {
        this.n = n;
    }
    public void setj(int j)
    {
        this.j = j;
    }

    public int getk()
    {
        return k;
    }
    public int getn()
    {
        return n;
    }
    public int getj()
    {
        return j;
    }

}
