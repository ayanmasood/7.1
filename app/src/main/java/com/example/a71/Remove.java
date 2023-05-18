package com.example.a71;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a71.data.DatabaseHelper;

public class Remove extends AppCompatActivity {


    TextView fItem, fDate, fLocation;
    Button rRemove;
com.example.a71.model.Advert advert;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove);


        //getting the boolean and its saved state
        SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", MODE_PRIVATE);
        final boolean canBeDeleted = sharedPreferences.getBoolean("canBeDeleted", false);


        fItem=findViewById(R.id.fItem);
        fDate=findViewById(R.id.fDate);
        fLocation=findViewById(R.id.fLocation);
        rRemove=findViewById(R.id.Remove);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        //getting intents from activity
        String description = extras.getString("description");
        advert = (com.example.a71.model.Advert) getIntent().getSerializableExtra("advert");
        Integer i = extras.getInt("i");

        //setting text from intent
        fItem.setText(description);
        fDate.setText(advert.getDate());
        fLocation.setText(advert.getLocation());

        rRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // Delete the Advert from the database
                DatabaseHelper dbHelper = new DatabaseHelper(Remove.this);
                //if boolean is true (item found) delete item and reset boolean
                if (canBeDeleted) {
                    dbHelper.deleteAdvert(description);
                    SharedPreferences.Editor editor = getSharedPreferences("myPrefs", MODE_PRIVATE).edit();
                    editor.putBoolean("canBeDeleted", false);
                    editor.apply();



                }


            }
        });





    }
}