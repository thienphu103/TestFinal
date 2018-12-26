package com.example.a.testfinal.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {


    public DBHelper(Context context) {
        super(context, "Database_ver5", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        PRIMARY KEY AUTOINCREMENT
        String sql = "CREATE TABLE IF NOT EXISTS MEMBER(" +
                "username primary key," +
                "password text," +
                "gender text," +
                "datefbirth text," +
                "placeofbirth text," +
                "depID integer," +
                "image integer," +
                "acctype integer)";
        sqLiteDatabase.execSQL(sql);
        String sql_department = "CREATE TABLE IF NOT EXISTS DEPARTMENT(" +
                "depID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name text," +
                "managerID text)";
        sqLiteDatabase.execSQL(sql_department);

        String sql_insert = "INSERT INTO MEMBER VALUES(" +
                "'Danh'," +//name
                "'123456'," +//pass
                "'1'," +//gender
                "'14/2/1998'," +//date of birth
                "'HCM'," +// place of birth
                "1," +// id department design
                "'avatar2'," + //image
                "'1')";////acc type
        sqLiteDatabase.execSQL(sql_insert);
        sql_insert = "INSERT INTO MEMBER VALUES(" +
                "'Thanh'," +//name
                "'123456'," +//pass
                "'1'," +//gender
                "'14/2/1998'," +//date of birth
                "'HCM'," +// place of birth
                "2," +// id department database
                "'avatar2'," + //image
                "'1')";////acc type
        sqLiteDatabase.execSQL(sql_insert);
        sql_insert = "INSERT INTO MEMBER VALUES(" +
                "'Phú'," +//name
                "'123456'," +//pass
                "'1'," +//gender
                "'14/2/1998'," +//date of birth
                "'HCM'," +// place of birth
                "2," +// id department database
                "'avatar2'," + //image
                "'1')";////acc type
        sqLiteDatabase.execSQL(sql_insert);
        sql_insert = "INSERT INTO MEMBER VALUES(" +
                "'Nam'," +//name
                "'123456'," +//pass
                "'1'," +//gender
                "'14/2/1998'," +//date of birth
                "'HCM'," +// place of birth
                "1," +// id department design
                "'avatar2'," + //image
                "'1')";////acc type
        sqLiteDatabase.execSQL(sql_insert);
        sql_insert = "INSERT INTO MEMBER VALUES(" +
                "'Khanh'," +//name
                "'123456'," +//pass
                "'1'," +//gender
                "'14/2/1998'," +//date of birth
                "'HCM'," +// place of birth
                "2," +// id department database
                "'avatar2'," + //image
                "'1')";////acc type
        sqLiteDatabase.execSQL(sql_insert);

        String sql_insert_department = "INSERT INTO DEPARTMENT VALUES(" +
                "null," +  //id department mac dinh 1
                "'Thiết kế UI'," +//name department
                "'Nam')";// name manager
        sqLiteDatabase.execSQL(sql_insert_department);
        sql_insert_department = "INSERT INTO DEPARTMENT VALUES(" +
                "null," + //id mac dinh 2
                "'Thiết kế Database'," +
                "'Thanh')";// name manager
        sqLiteDatabase.execSQL(sql_insert_department);




    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
