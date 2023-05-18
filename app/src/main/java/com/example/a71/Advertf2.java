package com.example.a71;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.a71.data.DatabaseHelper;

public class Advertf2 extends AppCompatActivity {
    EditText uName, uPhone, uDescrip, uDate, uLocation;
    Button sSave;
    RadioButton uLost, uFound;
    DatabaseHelper db;
    boolean canBeDeleted;


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

        db =new DatabaseHelper(this);
        uFound.setChecked(true);
        uLost.setChecked(false);
        uLost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Advertf2.this,Advert.class));

            }
        });

        sSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //find advert from database
                boolean result= db.fetchAdvert(uName.getText().toString(),uPhone.getText().toString(),uDescrip.getText().toString(),uLocation.getText().toString());
                if (result==true){
                    Toast.makeText(Advertf2.this, "Item Found", Toast.LENGTH_SHORT).show();


                    SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("canBeDeleted", true);
                    editor.apply();
                    Intent intent = new Intent(Advertf2.this, LostFound.class);


                    startActivity(intent);
                }
                else{
                    //item cant be deleted and give toast message
                    canBeDeleted=false;
                    Toast.makeText(Advertf2.this, "Item does not exist", Toast.LENGTH_SHORT).show();
                }
            }
        });





    }
}