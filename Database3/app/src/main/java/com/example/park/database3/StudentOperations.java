package com.example.park.database3;

/**
 * Created by Park on 2015-11-23.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentOperations {

    private MyDatabase dbHelper;
    private String[] STUDENT_TABLE_COLUMNS = {MyDatabase.STUDENT_ID, MyDatabase.STUDENT_NAME};
    private SQLiteDatabase database;
    private int kcount = 0,ncount = 0,jcount = 0;

    public StudentOperations(Context context){
        dbHelper = new MyDatabase(context);
    }

    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public Student addStudent(String name, int k, int n, int j){
        ContentValues values = new ContentValues();
        values.put(MyDatabase.STUDENT_NAME, name);
        long studId = database.insert(MyDatabase.STUDENTS, null, values);
        kcount += k;
        ncount += n;
        jcount += j;

        Cursor cursor = database.query(MyDatabase.STUDENTS,
                STUDENT_TABLE_COLUMNS, MyDatabase.STUDENT_ID+" = "
                        + studId, null, null, null, null);
        cursor.moveToFirst();
        Student newComment = parseStudent(cursor);
        newComment.setk(k);
        newComment.setn(n);
        newComment.setj(j);
        cursor.close();
        return newComment;
    }

    public void deleteStudent(Student comment){
        long id = comment.getId();
        kcount -= comment.getk();
        ncount -= comment.getn();
        jcount -= comment.getj();
        System.out.println("Comment deleted with id : " + id);
        database.delete(MyDatabase.STUDENTS, MyDatabase.STUDENT_ID + " = " + id, null);
    }

    public List getAllStudents(){
        List students = new ArrayList();

        Cursor cursor = database.query(MyDatabase.STUDENTS,
                STUDENT_TABLE_COLUMNS, null, null, null, null, null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){

            Student student = parseStudent(cursor);

            kcount += student.getk();
            ncount += student.getn();
            jcount += student.getj();


            students.add(student);
            cursor.moveToNext();
        }

        cursor.close();
        return students;
    }

    public int[] getcount()
    {
        int[] a;
        a = new int[3];
        a[0] = kcount;
        a[1] = ncount;
        a[2] = jcount;

        return a;
    }

    private Student parseStudent(Cursor cursor){
        Student student = new Student();
        student.setId((cursor.getInt(0)));
        student.setName(cursor.getString(1));
        return student;
    }
}
