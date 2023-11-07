package com.android.gandharvms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Inward_Truck extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inward_truck);
    }

    public void sequirtyinwardtruck(View view){
        Intent intent = new Intent(this,Inward_Truck_Security.class);
        startActivity(intent);

    }
    public void Weighmentinwardtruck(View view){
        Intent intent = new Intent(this,Inward_Truck_weighment.class);
        startActivity(intent);
    }
    public void storeinwardtruck(View view){
        Intent intent = new Intent(this, Inward_Truck_Store.class);
        startActivity(intent);
    }
}