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

public class Inward_Tanker_Weighment extends AppCompatActivity {

    EditText  etserialnumber,etvehicalno,etsuppliername,etmaterialname,etcustname,etdriverno,etoano,etdate,
              etgrossweight,ettareweight,etnetweight,etdensity, etbatchno,etsignby,etweDatetime ;
    Button wesubmit;
    FirebaseFirestore wedbroot;

    EditText datetimeTextview;
    Calendar calendar = Calendar.getInstance();

    DatePickerDialog picker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inward_tanker_weighment);


//        etserialnumber,etvehicalno,
//                etsuppliername,etmaterialname,
//                etcustname,etdrivername,
//                etoano,etdate,
//                etgrossweight,ettareweight,
//                etbatchno,etsignby,
//                etweDatetime,wesubmit

        etserialnumber =(EditText) findViewById(R.id.etserialnumber);
        etvehicalno =(EditText) findViewById(R.id.etvehicalno);
        etsuppliername=(EditText) findViewById(R.id.etsuppliername);
        etmaterialname=(EditText) findViewById(R.id.etmaterialname);
        etcustname=(EditText) findViewById(R.id.etcustname);
        etdriverno=(EditText) findViewById(R.id.etdriverno);
        etoano=(EditText) findViewById(R.id.etoano);
        etdate=(EditText) findViewById(R.id.etdate);
        etgrossweight=(EditText) findViewById(R.id.etgrossweight);
        ettareweight=(EditText) findViewById(R.id.ettareweight);
        etnetweight=(EditText)findViewById(R.id.etnetweight);
        etdensity=(EditText)findViewById(R.id.etdensity);
        etbatchno=(EditText) findViewById(R.id.etbatchno);
        etsignby=(EditText) findViewById(R.id.etsignby);
        etweDatetime=(EditText) findViewById(R.id.etweDatetime);

        datetimeTextview=findViewById(R.id.etweDatetime);

        datetimeTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });



        etdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                picker = new DatePickerDialog(Inward_Tanker_Weighment.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        etdate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                },year,month,day);
                picker.show();
            }
        });


        wesubmit=(Button) findViewById(R.id.wesubmit);
        wedbroot=FirebaseFirestore.getInstance();

        wesubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weinsertdata();
            }
        });
    }

    public void showDatePicker()
    {
        DatePickerDialog datePickerDialog = new DatePickerDialog(Inward_Tanker_Weighment.this, new DatePickerDialog.OnDateSetListener() {
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
        TimePickerDialog timePickerDialog = new TimePickerDialog(Inward_Tanker_Weighment.this, new TimePickerDialog.OnTimeSetListener() {
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
        etweDatetime.setText(dateFormat.format(dateTime.getTime()));

    }


   public  void weinsertdata(){

       String serialnumber  = etserialnumber.getText().toString().trim();
       String vehicelnumber = etvehicalno.getText().toString().trim();
       String suppliername = etsuppliername.getText().toString().trim();
       String materialname = etmaterialname.getText().toString().trim();
       String custname = etcustname.getText().toString().trim();
       String driverno = etdriverno.getText().toString().trim();
       String oan = etoano.getText().toString().trim();
       String   date  = etdate.getText().toString().trim();
       String grossweight = etgrossweight.getText().toString().trim();
       String tareweight = ettareweight.getText().toString().trim();
       String netweight = etnetweight.getText().toString().trim();
       String density = etdensity.getText().toString().trim();
       String batchno = etbatchno.getText().toString().trim();
       String signby = etsignby.getText().toString().trim();
       String datetime = etweDatetime.getText().toString().trim();


       if (serialnumber.isEmpty() || vehicelnumber.isEmpty() || suppliername.isEmpty() || materialname.isEmpty() ||
               custname.isEmpty() || driverno.isEmpty() || oan.isEmpty() || date.isEmpty() || grossweight.isEmpty() ||
               tareweight.isEmpty() || netweight.isEmpty() ||  density.isEmpty() || batchno.isEmpty() ||
               signby.isEmpty() || datetime.isEmpty()){
           Toast.makeText(this, "All fields must be filled", Toast.LENGTH_SHORT).show();
       }
       else {



           Map<String,String> weitems = new HashMap<>();
           weitems.put("serial number",etserialnumber.getText().toString().trim());
           weitems.put("vehicle number",etvehicalno.getText().toString().trim());
           weitems.put("supplier name",etsuppliername.getText().toString().trim());
           weitems.put("material name",etmaterialname.getText().toString().trim());
           weitems.put("Customer Name",etcustname.getText().toString().trim());
           weitems.put("Driver Number",etdriverno.getText().toString().trim());
           weitems.put("OA number",etoano.getText().toString().trim());
           weitems.put("Date",etdate.getText().toString().trim());
           weitems.put("Gross Weight",etgrossweight.getText().toString().trim());
           weitems.put("Tare Weight",ettareweight.getText().toString().trim());
           weitems.put("Net Weight",etnetweight.getText().toString().trim());
           weitems.put("Density",etdensity.getText().toString().trim());
           weitems.put("Batch Number",etbatchno.getText().toString().trim());
           weitems.put("Sign By",etsignby.getText().toString().trim());
           weitems.put("We Date Time",etweDatetime.getText().toString().trim());


           wedbroot.collection("Inward Tanker Weighment").add(weitems)
                   .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                       @Override
                       public void onComplete(@NonNull Task<DocumentReference> task) {

                           etserialnumber.setText("");
                           etvehicalno.setText("");
                           etsuppliername.setText("");
                           etmaterialname.setText("");
                           etcustname.setText("");
                           etvehicalno.setText("");
                           etoano.setText("");
                           etdate.setText("");
                           etgrossweight.setText("");
                           ettareweight.setText("");
                           etbatchno.setText("");
                           etsignby.setText("");
                           etweDatetime.setText("");

                           Toast.makeText(Inward_Tanker_Weighment.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();


                       }
                   });



       }

       }











}