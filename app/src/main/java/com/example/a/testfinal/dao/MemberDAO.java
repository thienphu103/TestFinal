package com.example.a.testfinal.dao;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.a.testfinal.MainActivity;
import com.example.a.testfinal.database.DBHelper;
import com.example.a.testfinal.model.Member;

import java.util.ArrayList;


public class MemberDAO {
    DBHelper dbHelper;

    public MemberDAO(Context context) {
        dbHelper = new DBHelper(context);

    }

    public ArrayList<Member> viewAll() {
        ArrayList<Member> arrayList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM MEMBER", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String username = cursor.getString(0);
            String password = cursor.getString(1);
            boolean gender = Boolean.parseBoolean(cursor.getString(2));
            String datefbirth = cursor.getString(3);
            String placeofbirth = cursor.getString(4);
            int depID = cursor.getInt(5);
            String image = cursor.getString(6);
            int acctype = cursor.getInt(7);
            arrayList.add(new Member(username, password, Integer.parseInt(cursor.getString(2))==1?true:false, datefbirth, placeofbirth,depID, image, acctype));
            for (int i = 0; i < 7; i++) {
               // Log.d("Arraylist",arrayList.get(i).toString());//test show database
                Log.d("Database",cursor.getColumnName(i)+": "+cursor.getString(i));//test show arraylist


            }

            cursor.moveToNext();
        }

        return arrayList;

    }
    public Member viewByID(String name) {
        Member member=new Member();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM MEMBER WHERE username='"+name+"' ", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
           member.setUsername(cursor.getString(0));
            member.setPassword(cursor.getString(1));
            member.setGender(Integer.parseInt(cursor.getString(2))==1?true:false);
            member.setDatefbirth(cursor.getString(3));
            member.setPlaceofbirth(cursor.getString(4));
            member.setDepID(cursor.getInt(5));
            member.setImage( cursor.getString(6));
            member.setAcctype(cursor.getInt(7));

            cursor.moveToNext();
        }
        return member;

    }

    public void insert(Context context, String name, String pass, String gender, String date, String place,String depID,String image, String roles) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql_insert = "INSERT INTO MEMBER VALUES(" +
                "'" + name + "'," +
                "'" + pass + "'," +
                "'" + gender + "'," +
                "'" + date + "'," +
                "'" + place + "'," +
                "'" + depID + "'," +
                "'" + image + "'," +
                "'" + roles + "')";

        try {
            db.execSQL(sql_insert);
            Toast.makeText(context, "Thêm Thành Công !", Toast.LENGTH_SHORT).show();
            context.startActivity(new Intent(context, MainActivity.class));


        } catch (Exception ex) {
            Toast.makeText(context, "Tạo Trùng tên hoặc lỗi", Toast.LENGTH_SHORT).show();
        }

    }

    public void update(Context context, String id, String name, String gender, String date, String place,String depID, String image, String roles) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql_update = "UPDATE MEMBER SET " +
                "username=" + "'" + name + "'," +
                "gender=" + "'" + gender + "'," +
                "datefbirth=" + "'" + date + "'," +
                "placeofbirth=" + "'" + place + "'," +
                "depID=" + "'" + depID + "'," +
                "image=" + "'" + image + "'," +
                "acctype=" + "'" + roles + "' WHERE " +
                "username=" + "'" + id + "'";
        try {
            db.execSQL(sql_update);
            Toast.makeText(context, "Cập Nhật Thành Công !", Toast.LENGTH_SHORT).show();
        } catch (Exception ex) {
            Toast.makeText(context, "Tên không tồn tại hoặc lỗi", Toast.LENGTH_SHORT).show();
        }


    }

    public void delete(Context context, String name) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql_delete = "DELETE FROM MEMBER " +
                "WHERE " +
                "username=" + "'" + name + "'";
        try {
            db.execSQL(sql_delete);
            Toast.makeText(context, "Xóa "+name+" Thành Công !", Toast.LENGTH_SHORT).show();
        } catch (Exception ex) {
            Toast.makeText(context, "Tên không tồn tại hoặc lỗi", Toast.LENGTH_SHORT).show();
        }


    }

    public void changePassword() {

    }
}
