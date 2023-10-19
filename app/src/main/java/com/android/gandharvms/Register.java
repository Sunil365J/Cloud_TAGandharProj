package com.android.gandharvms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity {


    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://gandharvms-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        final EditText name = findViewById(R.id.name);
        final EditText emplid =findViewById(R.id.emplid);
        final EditText departmentname = findViewById(R.id.departmentname);
        final EditText emailid = findViewById(R.id.emailid);
        final EditText phoneno = findViewById(R.id.phoneno);
        final EditText password = findViewById(R.id.password);

        final Button register = findViewById(R.id.btnregister);
        final TextView loginnowbtn = findViewById(R.id.loginlink);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nameTxt = name.getText().toString();
                final String emplidTxt = emplid.getText().toString();
                final String departmentnameTxt = departmentname.getText().toString();
                final String emailidTxt = emailid.getText().toString();
                final String phoneTxt = phoneno.getText().toString();
                final String passwordTxt = password.getText().toString();

                if (nameTxt.isEmpty() || emplidTxt.isEmpty() || departmentnameTxt.isEmpty() || emailidTxt.isEmpty() ||
                     phoneTxt.isEmpty() || passwordTxt.isEmpty()){
                    Toast.makeText(Register.this, "Please fill all Fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(emplidTxt)){
                                Toast.makeText(Register.this, "Empl ID is Already Registered", Toast.LENGTH_SHORT).show();
                            }
                            else {

                                databaseReference.child("users").child(emplidTxt).child("name").setValue(nameTxt);
                                databaseReference.child("users").child(emplidTxt).child("departmentname").setValue(departmentnameTxt);
                                databaseReference.child("users").child(emplidTxt).child("emailid").setValue(emailidTxt);
                                databaseReference.child("users").child(emplidTxt).child("phoneno").setValue(phoneTxt);
                                databaseReference.child("users").child(emplidTxt).child("password").setValue(passwordTxt);

                                Toast.makeText(Register.this, "User Register succesfully", Toast.LENGTH_SHORT).show();
                                finish();

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                }
            }
        });
        loginnowbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}