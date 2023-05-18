package com.example.a71;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a71.data.DatabaseHelper;
import com.example.a71.model.Advert;
import com.example.a71.util.Util;

import java.util.ArrayList;

public class LostFound extends AppCompatActivity {

    ListView lfList;
    ArrayList<String> listItem;
    ArrayAdapter adapter;
    DatabaseHelper db;
    Cursor cursor;
    Advert advert;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_found);


        lfList=findViewById(R.id.list);

        db= new DatabaseHelper(this);
        listItem= new ArrayList<>();

        viewData();

        lfList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                String text= lfList.getItemAtPosition(i).toString();
                String clickedDescription = lfList.getItemAtPosition(i).toString();


                //getting the advert and its details
                Advert advert = db.fetchAdvertDes(clickedDescription);

                Toast.makeText(LostFound.this, "Clicked " + text, Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(LostFound.this, Remove.class);
                intent.putExtra("description", clickedDescription);
                intent.putExtra("advert", advert);



                startActivity(intent);
            }
        });





    }

    private void viewData() {
        Cursor cursor =db.viewData();

        if (cursor.getCount()==0){
            Toast.makeText(this, "no items to be displayed", Toast.LENGTH_SHORT).show();
        }else {
            //showing all items in database
            while (cursor.moveToNext()){
                //3rd item which is description to be displayed in list
                listItem.add(cursor.getString(3));





            }
            //the view to be showed
            adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItem);
            lfList.setAdapter(adapter);
        }
        }

    }
