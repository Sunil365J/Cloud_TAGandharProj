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

import org.checkerframework.checker.units.qual.C;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Inward_Tanker_Production extends AppCompatActivity {

    EditText etreq,ettankno,etreqtoDt,etconbyop,tanknoun,etconunloadDateTime;
    Button prosubmit;

    FirebaseFirestore prodbroot;

   EditText datetimeTextview,datetimeTextview1;
   Calendar calendar = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inward_tanker_production);

        etreq= (EditText) findViewById(R.id.etreq);
        ettankno=(EditText) findViewById(R.id.ettankno);
        etreqtoDt=(EditText) findViewById(R.id.etreqtoDt);
        etconbyop=(EditText) findViewById(R.id.etconbyop);
        tanknoun=(EditText) findViewById(R.id.tanknoun);
        etconunloadDateTime=(EditText) findViewById(R.id.etconunloadDateTime);

        prosubmit=(Button) findViewById(R.id.prosubmit);

        //datetimepickertesting
        datetimeTextview =findViewById(R.id.etreqtoDt);
        datetimeTextview1 = findViewById(R.id.etconunloadDateTime);

        datetimeTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        datetimeTextview1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker1();
            }
        });


        prodbroot=FirebaseFirestore.getInstance();

        prosubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proinsertdata();
            }
        });

    }

    public void showDatePicker(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(Inward_Tanker_Production.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                showTimePicker();
            }
        },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    public void showTimePicker(){
        TimePickerDialog timePickerDialog = new TimePickerDialog(Inward_Tanker_Production.this, new TimePickerDialog.OnTimeSetListener() {
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

    private void handleDateTimeSelection(java.util.Date dateTime){

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault());
        etreqtoDt.setText(dateFormat.format(dateTime.getTime()));
//        etconunloadDateTime.setText(dateFormat.format(dateTime.getTime()));

    }



                          // Time and date for confirm unload
    public void showDatePicker1(){

        DatePickerDialog datePickerDialog = new DatePickerDialog(Inward_Tanker_Production.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                showTimePicker1();
            }
        },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();

    }

    public void showTimePicker1(){
        TimePickerDialog timePickerDialog = new TimePickerDialog(Inward_Tanker_Production.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);

                handleDateTimeSelection1(calendar.getTime());

            }
        }, calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
        );
        timePickerDialog.show();
    }

    public void handleDateTimeSelection1(java.util.Date dateTime)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault());
        etconunloadDateTime.setText(dateFormat.format(dateTime.getTime()));

    }



    public void proinsertdata()
    {
        Map<String,String> proitems = new HashMap<>();

        proitems.put("Req to unload",etreq.getText().toString().trim());
        proitems.put("Tank Number Request",ettankno.getText().toString().trim());
        proitems.put("Req to op D/T",etreqtoDt.getText().toString().trim());
        proitems.put("confirm unload",etconbyop.getText().toString().trim());
        proitems.put("Tank Number",tanknoun.getText().toString().trim());
        proitems.put("con unload D/T",etconunloadDateTime.getText().toString().trim());







        prodbroot.collection("Inward Tanker Production").add(proitems)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {

                        etreq.setText("");
                        ettankno.setText("");
                        etreqtoDt.setText("");
                        etconbyop.setText("");
                        tanknoun.setText("");
                        etconunloadDateTime.setText("");

                        Toast.makeText(Inward_Tanker_Production.this, "Data Added Successfully", Toast.LENGTH_SHORT).show();



                    }
                });

    }
}