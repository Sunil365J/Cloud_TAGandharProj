package com.android.gandharvms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.gandharvms.Inward_Tanker_Security.Inward_Tanker_Security;
import com.android.gandharvms.Inward_Truck_Security.Inward_Truck_Security;
import com.android.gandharvms.Inward_Truck_Weighment.Inward_Truck_weighment;
import com.android.gandharvms.Inward_Truck_store.Inward_Truck_Store;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Inward_Truck extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://gandharvms-default-rtdb.firebaseio.com/");
    private String userRole;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inward_truck);
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String receivedEmplid = sharedPreferences.getString("EMPLID_KEY", "admin");
        databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userRole = snapshot.child(receivedEmplid).child("role").getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    public void sequirtyinwardtruck(View view) {
        if(userRole.equals("Admin")){
            Intent intent= new Intent(this, Inward_Truck_Security.class);
            startActivity(intent);
        }
        else if(userRole.equals("Security")){
            Intent intent= new Intent(this,Inward_Truck_Security.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(Inward_Truck.this, "You are not in Security Department", Toast.LENGTH_SHORT).show();
        }
    }

    public void Weighmentinwardtruck(View view) {
        if(userRole.equals("Admin")){
            Intent intent= new Intent(this, Inward_Truck_weighment.class);
            startActivity(intent);
        }
       else if(userRole.equals("Weighment")){
            Intent intent = new Intent(this,Inward_Truck_weighment.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(Inward_Truck.this, "You are not in Weighment Department", Toast.LENGTH_SHORT).show();
        }
    }

    public void storeinwardtruck(View view) {
        if(userRole.equals("Admin")){
            Intent intent= new Intent(this, Inward_Truck_Store.class);
            startActivity(intent);
        }
        else if(userRole.equals("Store")){
            Intent intent = new Intent(this,Inward_Truck_Store.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(Inward_Truck.this, "You are not in Store Department", Toast.LENGTH_SHORT).show();
        }
    }
}