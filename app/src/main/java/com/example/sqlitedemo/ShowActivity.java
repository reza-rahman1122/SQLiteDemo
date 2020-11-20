package com.example.sqlitedemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity {

    ListView listView;
    DBHelper dbHelper;
    ArrayList arrayList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        getSupportActionBar().setTitle("Donor List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listView=findViewById(R.id.id_list_view);
        dbHelper = new DBHelper(this);
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        view_data();




    }


    public void view_data() {


        arrayList = new ArrayList<>();

        Cursor cursor = dbHelper.display();
        if (cursor.getCount() == 0) {

            return;
        } else
            {
            StringBuffer stringBuffer=new StringBuffer();

            while (cursor.moveToNext()) {

               // stringBuffer.append("Id: "+cursor.getString(0)+"\n");
                //stringBuffer.append("Name: "+cursor.getString(1)+"\n");
                //stringBuffer.append("Phone: "+cursor.getString(2)+"\n");
                //stringBuffer.append("Blood group: "+cursor.getString(3)+"\n");


                arrayList.add("Id: "+cursor.getString(0) + " \n" + "Name: "+cursor.getString(1)+"\n"+"Phone No: "+cursor.getString(2)+"\n"+"Blood Group:"+cursor.getString(3));


            }
        }


        adapter = new ArrayAdapter<String>(this, R.layout.list_item,R.id.id_list_view_text, arrayList);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);


    }
}