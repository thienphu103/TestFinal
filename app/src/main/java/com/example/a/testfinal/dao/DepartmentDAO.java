package com.example.a.testfinal.dao;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.a.testfinal.MainActivity;
import com.example.a.testfinal.database.DBHelper;
import com.example.a.testfinal.model.Department;

import java.util.ArrayList;

public class DepartmentDAO {
    DBHelper dbHelper;

    public DepartmentDAO(Context context) {
        dbHelper = new DBHelper(context);

    }

    public ArrayList<Department> viewAll() {
        ArrayList<Department> arrayList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM DEPARTMENT", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String managerID = cursor.getString(2);

            arrayList.add(new Department(name,managerID));
            cursor.moveToNext();
        }
        return arrayList;

    }

    public void insert(Context context, String name, String pass, String gender, String date, String place, String image, String roles) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql_insert = "INSERT INTO MEMBER VALUES(" +
                "'" + name + "'," +
                "'" + pass + "'," +
                "'" + gender + "'," +
                "'" + date + "'," +
                "'" + place + "'," +
                "'" + image + "'," +
                "'" + roles + "')";

        try {
            db.execSQL(sql_insert);
            Toast.makeText(context, "Thêm Thành Công !", Toast.LENGTH_SHORT).show();
            context.startActivity(new Intent(context, MainActivity.class));


        } catch (Exception ex) {
            Toast.makeText(context, "Tạo Trùng tên hoặc lỗi", Toast.LENGTH_SHORT).show();
        }
//        String sql_insert="INSERT INTO MEMBER VALUES("+
//                "'Nam',"+
//                "'123456',"+
//                "'1',"+
//                "'14/2/1998',"+
//                "'HCM',"+
//                "'avatar2',"+
//                "'1')";

    }

    public void update(Context context, String id, String name, String pass, String gender, String date, String place, String image, String roles) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql_update = "UPDATE MEMBER SET " +
                "username=" + "'" + name + "'," +
                "password=" + "'" + pass + "'," +
                "gender=" + "'" + gender + "'," +
                "datefbirth=" + "'" + date + "'," +
                "placeofbirth=" + "'" + place + "'," +
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
