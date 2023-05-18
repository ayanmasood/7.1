package com.example.a71;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.a71.data.DatabaseHelper;

public class Advert extends AppCompatActivity {
EditText uName, uPhone, uDescrip, uDate, uLocation;
Button sSave;
RadioButton uLost, uFound;
DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advert);

        uName=findViewById(R.id.Name);
        uPhone=findViewById(R.id.Phone);
        uDescrip=findViewById(R.id.Description);
        uDate=findViewById(R.id.Date);
        uLocation=findViewById(R.id.Location);

        sSave=findViewById(R.id.Save);

        uLost=findViewById(R.id.Lost);
        uFound=findViewById(R.id.Found);

        db = new DatabaseHelper(this);
        uFound.setChecked(false);
        uLost.setChecked(true);

        uFound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Advert.this,Advertf2.class));



            }
        });

        sSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //variables input to put into advert class
                String name = uName.getText().toString();
                String description = uDescrip.getText().toString();
                String date = uDate.getText().toString();
                String location = uLocation.getText().toString();
                //so that it doesnot crash when parsing null or incorrect number
                    if (TextUtils.isEmpty(uPhone.getText().toString())) {
                        Toast.makeText(Advert.this, "Please enter a phone number", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    int phone;
                    if (TextUtils.isDigitsOnly(uPhone.getText().toString())) {
                        phone = Integer.parseInt(uPhone.getText().toString());
                    } else {
                        // Handle the case where the phone number is not a valid integer
                        Toast.makeText(Advert.this, "Invalid phone number", Toast.LENGTH_SHORT).show();
                        return; // Exit the onClick() method to prevent further execution
                    }
                //new advert
                com.example.a71.model.Advert advert= new com.example.a71.model.Advert(0,name,phone,description,date,location);
                db.insertAdvert(advert);
                Toast.makeText(Advert.this, "Advert successfully added to database", Toast.LENGTH_SHORT).show();
            }
        });




    }
}