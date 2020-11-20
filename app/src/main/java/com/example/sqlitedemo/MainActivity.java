package com.example.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edit_name,edit_id,edit_phone,edit_group;
    String ed_id,name,phone_no,bg_group;
    DBHelper dbHelper;

    Button save,show,delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        edit_name=findViewById(R.id.id_name);
        edit_id=findViewById(R.id.id);
        edit_phone=findViewById(R.id.id_phone_no);
        edit_group=findViewById(R.id.id_blood_group);
        save=findViewById(R.id.id_button_save);
        show=findViewById(R.id.id_button_show);
        delete=findViewById(R.id.id_button_delete);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ed_id=edit_id.getText().toString();
                name=edit_name.getText().toString();
                phone_no=edit_phone.getText().toString();
                bg_group=edit_group.getText().toString();

                if (ed_id.equals("")||name.equals("") || phone_no.equals("")||bg_group.equals("")) {

                    Toast.makeText(getApplicationContext(), "Empty Text!", Toast.LENGTH_SHORT).show();

                }
                else {
                    long id = dbHelper.save_data(ed_id,name, phone_no,bg_group);

                    if (id > -1) {

                        Toast.makeText(MainActivity.this, "Data insert success full!!", Toast.LENGTH_LONG).show();

                    } else {

                        Toast.makeText(MainActivity.this, "Data is not inserted.", Toast.LENGTH_LONG).show();

                    }
                }

            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this,ShowActivity.class));

            }

        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String get_id = edit_id.getText().toString();

                if (get_id.equals("")) {

                    Toast.makeText(MainActivity.this,"Please Enter an id",Toast.LENGTH_SHORT).show();

                } else {

                    Integer n = dbHelper.delete_item(get_id);
                    if (n > 0)
                        Toast.makeText(MainActivity.this, "Data is  deleted", Toast.LENGTH_LONG).show();

                    else
                        Toast.makeText(MainActivity.this, "Data is not deleted", Toast.LENGTH_LONG).show();

                }
            }
        });


    }

}