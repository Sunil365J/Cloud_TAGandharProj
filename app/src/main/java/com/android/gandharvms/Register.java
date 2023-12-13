package com.android.gandharvms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity {

    private String setRole="";


    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://gandharvms-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        final EditText name = findViewById(R.id.name);
        final EditText emplid =findViewById(R.id.emplid);
//        final EditText departmentname = findViewById(R.id.departmentname);
        final EditText emailid = findViewById(R.id.emailid);
        final EditText phoneno = findViewById(R.id.phoneno);
        final EditText password = findViewById(R.id.password);

        final Button register = findViewById(R.id.btnregister);
        final TextView loginnowbtn = findViewById(R.id.loginlink);



        final CheckBox security=findViewById(R.id.isSecurity);
        final CheckBox weighment=findViewById(R.id.isWeighment);
        final CheckBox sampling=findViewById(R.id.isSampling);
        final CheckBox production=findViewById(R.id.isProduction);
        final CheckBox laboratary=findViewById(R.id.isLaboratary);
        final CheckBox stores=findViewById(R.id.isStores);


        security.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked()){
                    weighment.setChecked(false);
                    sampling.setChecked(false);
                    production.setChecked(false);
                    laboratary.setChecked(false);
                    stores.setChecked(false);
                }
            }
        });

        weighment.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked()){
                    security.setChecked(false);
                    sampling.setChecked(false);
                    production.setChecked(false);
                    laboratary.setChecked(false);
                    stores.setChecked(false);
                }
            }
        });

        sampling.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked()){
                    weighment.setChecked(false);
                    security.setChecked(false);
                    production.setChecked(false);
                    laboratary.setChecked(false);
                    stores.setChecked(false);
                }
            }
        });
        production.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked()){
                    weighment.setChecked(false);
                    sampling.setChecked(false);
                    security.setChecked(false);
                    laboratary.setChecked(false);
                    stores.setChecked(false);
                }
            }
        });
        laboratary.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked()){
                    weighment.setChecked(false);
                    sampling.setChecked(false);
                    production.setChecked(false);
                    security.setChecked(false);
                    stores.setChecked(false);
                }
            }
        });

        stores.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {
                if(buttonView.isChecked()){
                    weighment.setChecked(false);
                    sampling.setChecked(false);
                    production.setChecked(false);
                    security.setChecked(false);
                    laboratary.setChecked(false);
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nameTxt = name.getText().toString();
                final String emplidTxt = emplid.getText().toString();
//                final String departmentnameTxt = departmentname.getText().toString();
                final String emailidTxt = emailid.getText().toString();
                final String phoneTxt = phoneno.getText().toString();
                final String passwordTxt = password.getText().toString();

                if(security.isChecked()){
                    setRole="Security";
                } else if (weighment.isChecked()) {
                    setRole="Weighment";
                } else if (sampling.isChecked()) {
                    setRole="Sampling";
                } else if (production.isChecked()) {
                    setRole="Production";
                } else if (laboratary.isChecked()) {
                    setRole="Laboratory";
                } else if (stores.isChecked()) {
                    setRole="Store";
                }

                if (nameTxt.isEmpty() || emplidTxt.isEmpty() || emailidTxt.isEmpty() ||
                     phoneTxt.isEmpty() || passwordTxt.isEmpty()){
                    Toast.makeText(Register.this, "Please fill all Fields", Toast.LENGTH_SHORT).show();
                }
                //Checkbox Validation
                if(!(security.isChecked() || weighment.isChecked() || sampling.isChecked() || production.isChecked() || laboratary.isChecked() || stores.isChecked()))
                {
                    Toast.makeText(Register.this, "Please Select the Department", Toast.LENGTH_SHORT).show();
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
//                                databaseReference.child("users").child(emplidTxt).child("departmentname").setValue(departmentnameTxt);
                                databaseReference.child("users").child(emplidTxt).child("emailid").setValue(emailidTxt);
                                databaseReference.child("users").child(emplidTxt).child("phoneno").setValue(phoneTxt);
                                databaseReference.child("users").child(emplidTxt).child("password").setValue(passwordTxt);
                                databaseReference.child("users").child(emplidTxt).child("role").setValue(setRole);

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