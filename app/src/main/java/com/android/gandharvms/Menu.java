package com.android.gandharvms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

//        Intent intent = new Intent(this,Inward_Tanker.class);
//        startActivity(intent);
    }
    public void Inward_Tanker(View view){
       Intent intent = new Intent(this,Inward_Tanker.class);
        startActivity(intent);
   }


}