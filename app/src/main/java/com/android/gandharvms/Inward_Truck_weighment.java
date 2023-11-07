package com.android.gandharvms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Inward_Truck_weighment extends AppCompatActivity {

    EditText etserialnumber,etvehicalnumber,etsupplier,etmaterial,etcustomer,etdriver,etoanumber,etdate,etgrossweight,ettareweight,etnetweight,etdensity,etbatchno,etsignby,etdatetime;

    Button intsubmit;
    FirebaseFirestore trwdbroot;
    DatePickerDialog trpicker;
    EditText datetimeTextview;
    Calendar calendar = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inward_truck_weighment);

        etserialnumber=(EditText) findViewById(R.id.ettrwserialnumber);
        etvehicalnumber=(EditText) findViewById(R.id.ettrwvehicalno);
        etsupplier=(EditText) findViewById(R.id.ettrwsupplier);
        etmaterial=(EditText) findViewById(R.id.ettrwmaterial);
        etcustomer=(EditText) findViewById(R.id.ettrwcustomer);
        etdriver=(EditText) findViewById(R.id.ettrdriver);
        etoanumber=(EditText) findViewById(R.id.ettroa);
        etdate= (EditText) findViewById(R.id.ettrdate);
        etgrossweight=(EditText) findViewById(R.id.ettrgrossweight);
        ettareweight=(EditText) findViewById(R.id.ettrtareweight);
        etnetweight=(EditText) findViewById(R.id.ettrnetweight);
        etdensity=(EditText) findViewById(R.id.ettrdensity);
        etbatchno =(EditText) findViewById(R.id.ettrbatch);
        etsignby =(EditText) findViewById(R.id.ettrsignby);
        etdatetime=(EditText) findViewById(R.id.ettrdatetime);


        //datetime
        datetimeTextview=findViewById(R.id.ettrdatetime);
        datetimeTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        //single date

        etdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                trpicker = new DatePickerDialog(Inward_Truck_weighment.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        etdate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                },year,month,day);
                trpicker.show();
            }
        });








        intsubmit=(Button) findViewById(R.id.wesubmit);
        trwdbroot=FirebaseFirestore.getInstance();



        intsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intrinsert();
            }
        });
    }


    public void showDatePicker()
    {
        DatePickerDialog datePickerDialog = new DatePickerDialog(Inward_Truck_weighment.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);


                showTimePicker();
            }
        },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    public void showTimePicker()
    {
        TimePickerDialog timePickerDialog = new TimePickerDialog(Inward_Truck_weighment.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {


                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);

                handleDateTimeSelection(calendar.getTime());

            }
        }, calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
        );
        timePickerDialog.show();
    }

    public void handleDateTimeSelection(java.util.Date dateTime)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault());
        datetimeTextview.setText(dateFormat.format(dateTime.getTime()));

    }


    public void intrinsert()
    {
        String serial_Number=etserialnumber.getText().toString().trim();
        String vehicalnumber=etvehicalnumber.getText().toString().trim();
        String supplier=etsupplier.getText().toString().trim();
        String material=etmaterial.getText().toString().trim();
        String customer = etcustomer.getText().toString().trim();
        String oanumber = etoanumber.getText().toString().trim();
        String date = etdate.getText().toString().trim();
        String Grossweight = etgrossweight.getText().toString().trim();
        String Tareweight = ettareweight.getText().toString().trim();
        String netweight = etnetweight.getText().toString().trim();
        String Density = etdensity.getText().toString().trim();
        String Batchno = etbatchno.getText().toString().trim();
        String signby = etsignby.getText().toString().trim();
        String DateTime = etdatetime.getText().toString().trim();


        if (serial_Number.isEmpty()|| vehicalnumber.isEmpty()|| supplier.isEmpty()|| material.isEmpty()|| customer.isEmpty()||oanumber.isEmpty()|| date.isEmpty()||Grossweight.isEmpty()
        || Tareweight.isEmpty()|| netweight.isEmpty()|| Density.isEmpty()|| Batchno.isEmpty()|| signby.isEmpty()|| DateTime.isEmpty()){
            Toast.makeText(this, "All fields must be filled", Toast.LENGTH_SHORT).show();
        }
        else {


            Map<String,String>trweitems= new HashMap<>();
            trweitems.put("Serial Number",etserialnumber.getText().toString().trim());
            trweitems.put("Vehicle Number",etvehicalnumber.getText().toString().trim());
            trweitems.put("Supplier",etsupplier.getText().toString().trim());
            trweitems.put("Material",etmaterial.getText().toString().trim());
            trweitems.put("Customer",etcustomer.getText().toString().trim());
            trweitems.put("OA Number ",etoanumber.getText().toString().trim());
            trweitems.put("Date",etdate.getText().toString().trim());
            trweitems.put("Gross Weight",etgrossweight.getText().toString().trim());
            trweitems.put("Tare Weight",ettareweight.getText().toString().trim());
            trweitems.put("Net Weight ",etnetweight.getText().toString().trim());
            trweitems.put("Density",etdensity.getText().toString().trim());
            trweitems.put("Batch No ",etbatchno.getText().toString().trim());
            trweitems.put("Sign By ",etsignby.getText().toString().trim());
            trweitems.put("Date/Time",etdatetime.getText().toString().trim());


            trwdbroot.collection("Inward Truck Weighment").add(trweitems)
                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {

                            etserialnumber.setText("");
                            etvehicalnumber.setText("");
                            etsupplier.setText("");
                            etmaterial.setText("");
                            etcustomer.setText("");
                            etoanumber.setText("");
                            etdate.setText("");
                            etgrossweight.setText("");
                            ettareweight.setText("");
                            etnetweight.setText("");
                            etdensity.setText("");
                            etbatchno.setText("");
                            etsignby.setText("");
                            etdatetime.setText("");

                            Toast.makeText(Inward_Truck_weighment.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                        }
                    });


        }
    }
}