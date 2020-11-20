package com.example.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_Name="information.db";
    private static final String TABLE_NAME ="information_details";
    private static final int Version =1;
    private static final String ID="Id";
    private static final String NAME="Name";
    private static final String PHONE="Phone";
    private static final String BG="bggroup";

    private Context context;

    public DBHelper(Context context) {
        super(context, DB_Name, null, Version);

        this.context=context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try{
            Toast.makeText(context,"Table created", Toast.LENGTH_SHORT).show();

            db.execSQL("CREATE TABLE "+TABLE_NAME+"("+ID+" VARCHAR(100) PRIMARY KEY ,"+NAME+" VARCHAR(250),"+PHONE+" VARCHAR(250),"+BG+" VARCHAR(250))");

        }

        catch (Exception e)
        {
            Toast.makeText(context,"Something wrong!", Toast.LENGTH_SHORT).show();

        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

try{

    db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
    onCreate(db);

}

catch (Exception e)
{


}




    }



    public long save_data(String id, String name, String phone, String bg)

    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(ID,id);
        contentValues.put(NAME,name);
        contentValues.put(PHONE,phone);
        contentValues.put(BG,bg);
       long row_id= sqLiteDatabase.insert(TABLE_NAME,null,contentValues);

      return row_id;


    }


    public int delete_item(String id)

    {

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
       int value= sqLiteDatabase.delete(TABLE_NAME,ID +" =?",new String[]{id});

        return value ;

    }


    Cursor display()

    {

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();

       Cursor cursor= sqLiteDatabase.rawQuery("SELECT*FROM "+TABLE_NAME+"",null);

      return cursor;


    }
}
