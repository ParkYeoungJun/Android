package com.example.park.business_card;

/**
 * Created by Park on 2015-12-03.
 */
public class Card {

    private int id;
    private String data_company;
    private String data_name;
    private String data_contact;
    private String data_memo;
    private String data_rating;

    public Card(int id, String company, String name, String contact, String memo, String rating)
    {
        this.set_Id(id);
        this.set_Company(company);
        this.set_Contact(contact);
        this.set_Name(name);
        this.set_Memo(memo);
        this.set_Rating(rating);
    }

    public Card(String company, String name, String contact, String memo, String rating)
    {
        this.set_Company(company);
        this.set_Name(name);
        this.set_Contact(contact);
        this.set_Memo(memo);
        this.set_Rating(rating);
    }

    public Card(String company, String name, String contact, String rating)
    {
        this.set_Id(id);
        this.set_Company(company);
        this.set_Contact(contact);
        this.set_Name(name);
        this.set_Rating(rating);
    }


    public int getId()
    {
        return id;
    }

    public void set_Id(int id)
    {
        this.id = id;
    }

    public void set_Company(String com)
    {
        data_company = com;
    }

    public String getCompany()
    {
        return data_company;
    }

    public void set_Name(String na)
    {
        data_name = na;
    }

    public String getName()
    {
        return data_name;
    }

    public void set_Contact(String con)
    {
        data_contact = con;
    }

    public String getContact()
    {
        return data_contact;
    }

    public void set_Memo(String me)
    {
        data_memo = me;
    }

    public String getMemo()
    {
        return data_memo;
    }

    public void set_Rating(String rating)
    {
        data_rating = rating;
    }

    public String getRating()
    {
        return data_rating;
    }


}
