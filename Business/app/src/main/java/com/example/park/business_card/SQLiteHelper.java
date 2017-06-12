package com.example.park.business_card;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

import java.util.ArrayList;

/**
 * Created by Park on 2015-12-03.
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "BusinessCard"; // DB name

    private static final String TABLE_CARD = "Card";        // SQlite의 table 이름

    private static final String PRIMARY_KEY_ID = "id";      // id values 설정 하지 않으면 자동적으로 설정, 증가
    private static final String CARD_COMPANY = "company";   // 각 명함의 company 값
    private static final String CARD_NAME = "name";         // 각 명함의 name 값
    private static final String CARD_CONTACT = "contact";   // 각 명함의 contact 값
    private static final String CARD_MEMO = "memo";         // 각 명함의 Memo 값
    private static final String CARD_RATING = "rating";     // 각 명함의 중요도 값


    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // createTableQuery = CREATE TABLE CARD(id INTEGER PRIMARY KEY, Company TEXT, Name TEXT, Contact TEXT);

        String createTableQuery = "create table " + TABLE_CARD + "("
                + PRIMARY_KEY_ID + " integer primary key autoincrement, "
                + CARD_COMPANY + " text not null, "
                + CARD_NAME + " text, "
                + CARD_CONTACT + " text, "
                + CARD_MEMO + " text, "
                + CARD_RATING + " text)";

        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String dropOldVersionQuery = "DROP TABLE IF EXISTS " + TABLE_CARD;
        db.execSQL(dropOldVersionQuery);

        onCreate(db);
    }


    // Insert

    public void insert(Card card) {
        SQLiteDatabase db = this.getWritableDatabase();

        // value 에 일단 넣고
        ContentValues values = new ContentValues();

        values.put(CARD_COMPANY, card.getCompany());
        values.put(CARD_NAME, card.getName());
        values.put(CARD_CONTACT, card.getContact());
        values.put(CARD_MEMO, card.getMemo());
        values.put(CARD_RATING, card.getRating());

        // db에 넣기
        db.insert(TABLE_CARD, null, values);
        db.close();

        Sort();
    }

    //Delete

    public void delete(Card card) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_CARD, PRIMARY_KEY_ID + " = ?",
                new String[]{String.valueOf(card.getId())});

        db.close();
    }

    // Select All data to List

    public List selectAllCard() {

        List<Card> cardList = new ArrayList<Card>();

        String selectAllQuery = "SELECT * FROM " + TABLE_CARD;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectAllQuery, null);

        if (cursor.moveToFirst()) {
            do {
                cardList.add(new Card(
                        Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5)));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return cardList;
    }

    // Update Data

    public int updateCard(Card card) {
        int updateRow;

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(CARD_COMPANY, card.getCompany());
        values.put(CARD_NAME, card.getName());
        values.put(CARD_CONTACT, card.getContact());
        values.put(CARD_MEMO, card.getMemo());
        values.put(CARD_RATING, card.getRating());

        updateRow = db.update(TABLE_CARD, values, PRIMARY_KEY_ID + " = ?",
                new String[]{String.valueOf(card.getId())});

        db.close();

        Sort();

        return updateRow;
    }

    // Delete All Data

    public void deleteall() {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("delete from " + TABLE_CARD);
    }


    // Sort

    public void Sort() {
        List<Card> cardList = new ArrayList<Card>();

        cardList = selectAllCard();

        q_sort(cardList, 0, cardList.size() - 1);

        deleteall();

        int i = 0 ;

        while(i < cardList.size()) {

            SQLiteDatabase db = this.getWritableDatabase();

            // value 에 일단 넣고
            ContentValues values = new ContentValues();

            values.put(CARD_COMPANY, cardList.get(i).getCompany());
            values.put(CARD_NAME, cardList.get(i).getName());
            values.put(CARD_CONTACT, cardList.get(i).getContact());
            values.put(CARD_MEMO, cardList.get(i).getMemo());
            values.put(CARD_RATING, cardList.get(i).getRating());

            // db에 넣기
            db.insert(TABLE_CARD, null, values);
            db.close();

            ++i;
        }
    }

    // Quick Sort

    void q_sort(List<Card> list, int row, int high)
    {
        int left = row;
        int right = high;

        Card pivot = new Card(list.get((row+high)/2).getId(), list.get((row+high)/2).getCompany(), list.get((row+high)/2).getName()
                    , list.get((row+high)/2).getContact(), list.get((row+high)/2).getMemo(), list.get((row+high)/2).getRating());

        while (left <= right) {

                while (Double.parseDouble(list.get(left).getRating()) > Double.parseDouble(pivot.getRating())) {
                    left++;
                }

                while (Double.parseDouble(list.get(right).getRating()) < Double.parseDouble(pivot.getRating())) {
                    right--;
                }

                if (left <= right) {
                    Card temp = new Card(list.get(left).getId(), list.get(left).getCompany(), list.get(left).getName()
                            , list.get(left).getContact(), list.get(left).getMemo(), list.get(left).getRating());

                    list.get(left).set_Company(list.get(right).getCompany());
                    list.get(left).set_Name(list.get(right).getName());
                    list.get(left).set_Contact(list.get(right).getContact());
                    list.get(left).set_Memo(list.get(right).getMemo());
                    list.get(left).set_Rating(list.get(right).getRating());

                    list.get(right).set_Company(temp.getCompany());
                    list.get(right).set_Name(temp.getName());
                    list.get(right).set_Contact(temp.getContact());
                    list.get(right).set_Memo(temp.getMemo());
                    list.get(right).set_Rating(temp.getRating());

                    left++;
                    right--;
                }
            }

            if(row < right)q_sort(list, row, right);
            if(high > left)q_sort(list, left, high);
        }

}
