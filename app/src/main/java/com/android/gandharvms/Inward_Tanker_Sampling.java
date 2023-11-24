package com.android.gandharvms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
import java.util.Map;

public class Inward_Tanker_Sampling extends AppCompatActivity {

    EditText  etssignofproduction, etinvoiceno,etinvoicedate,materialname,etsqty1,suomqty,snetweight,suomnetwt,svesselname,sstoragetn,
            ssuppliername,etscustname,etsdate,etvehicleno;
    Button etssubmit;
    FirebaseFirestore sadbroot;
    DatePickerDialog picker;

    DatePickerDialog pickertr;
    TimePickerDialog tpicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inward_tanker_sampling);

        etssignofproduction=(EditText)findViewById(R.id.etreciving);
        etinvoiceno=(EditText) findViewById(R.id.etsubmitted);
//        etinvoicedate=(EditText) findViewById(R.id.invoicedate);
//        materialname=(EditText) findViewById(R.id.etmaterialname1);
//        etsqty1=(EditText) findViewById(R.id.etsqty);
//        suomqty=(EditText) findViewById(R.id.etsUOMqty);
//        snetweight=(EditText) findViewById(R.id.etsnetweight);
//        suomnetwt=(EditText) findViewById(R.id.etsuomnetwt);
//        svesselname=(EditText) findViewById(R.id.etsvesselname);
//        sstoragetn=(EditText) findViewById(R.id.etstoragetnkn);
//        ssuppliername=(EditText)findViewById(R.id.etssuppliername);
//        etscustname=(EditText)findViewById(R.id.etscustname);
        etsdate=(EditText)findViewById(R.id.etsdate);
        etvehicleno= (EditText)findViewById(R.id.etvehicleno);




                // timepicker


        etssignofproduction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int hours = calendar.get(Calendar.HOUR_OF_DAY);
                int mins = calendar.get(Calendar.MINUTE);


                tpicker = new TimePickerDialog(Inward_Tanker_Sampling.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Calendar c = Calendar.getInstance();
                        c.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        c.set(Calendar.MINUTE,minute);
                        etssignofproduction.setText(hourOfDay +":"+ minute );
                    }
                },hours,mins,false);
                tpicker.show();
            }
        });


        etinvoiceno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int hours = calendar.get(Calendar.HOUR_OF_DAY);
                int mins = calendar.get(Calendar.MINUTE);


                tpicker = new TimePickerDialog(Inward_Tanker_Sampling.this, new TimePickerDialog.OnTimeSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Calendar c = Calendar.getInstance();
                        c.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        c.set(Calendar.MINUTE,minute);
                        // SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
                        // etinvoiceno.setText(sdf.format(hourOfDay +":"+ minute));
                        etinvoiceno.setText(hourOfDay + ":" + minute );
                    }
                },hours,mins,false);
                tpicker.show();
            }
        });



        etsdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);


                picker = new DatePickerDialog(Inward_Tanker_Sampling.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        etsdate.setText(dayOfMonth + "/" + (month +1 ) + "/" + year);
                    }
                } ,year,month,day);
                picker.show();
            }
        });



        etssubmit=(Button) findViewById(R.id.etssubmit);
        sadbroot=FirebaseFirestore.getInstance();

        etssubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sainsertdata();
            }
        });


    }

    public void sainsertdata()
    {
        String etreciving = etssignofproduction.getText().toString().trim();
        String etsubmitted = etinvoiceno.getText().toString().trim();
//        String invoicedate = etinvoicedate.getText().toString().trim();
//        String materialnames = materialname.getText().toString().trim();
//        String Qty = etsqty1.getText().toString().trim();
//        String uomqty = suomqty.getText().toString().trim();
//        String netweight = snetweight.getText().toString().trim();
//        String uomnetwe= suomnetwt.getText().toString().trim();
//        String vesselname = svesselname.getText().toString().trim();
//        String storagetanl = sstoragetn.getText().toString().trim();
//        String suppliername = ssuppliername.getText().toString().trim();
//        String customername = etscustname.getText().toString().trim();
        String  date = etsdate.getText().toString().trim();
        String vehiclenumber =etvehicleno.getText().toString().trim();


        if (vehiclenumber.isEmpty() || etreciving.isEmpty() || date.isEmpty()|| etsubmitted.isEmpty()){
            Toast.makeText(this, "All fields must be filled", Toast.LENGTH_SHORT).show();
        }
        else {


            Map<String,String> saitems = new HashMap<>();

            saitems.put("Sample Reciving Time",etssignofproduction.getText().toString().trim());
            saitems.put("Sample Submitted Time",etinvoiceno.getText().toString().trim());
//            saitems.put("Invoice Date",etinvoicedate.getText().toString().trim());
//            saitems.put("Material Name",materialname.getText().toString().trim());
//            saitems.put("Qty",etsqty1.getText().toString().trim());
//            saitems.put("UOMqty",suomqty.getText().toString().trim());
//            saitems.put("Net Weight",snetweight.getText().toString().trim());
//            saitems.put("UOM Net Weight",suomnetwt.getText().toString().trim());
//            saitems.put("Vessel Name",svesselname.getText().toString().trim());
//            saitems.put("Storage Tank",sstoragetn.getText().toString().trim());
//            saitems.put("Supplier Name",ssuppliername.getText().toString().trim());
//            saitems.put("Customer Name",etscustname.getText().toString().trim());
            saitems.put("Date",etsdate.getText().toString().trim());
            saitems.put("Vehicle Number",etvehicleno.getText().toString().trim());



            sadbroot.collection("Inward Tanker Sampling").add(saitems)

                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {

                            etssignofproduction.setText("");
                            etinvoiceno.setText("");
//                            etinvoicedate.setText("");
//                            materialname.setText("");
//                            etsqty1.setText("");
//                            suomqty.setText("");
//                            snetweight.setText("");
//                            suomnetwt.setText("");
//                            svesselname.setText("");
//                            sstoragetn.setText("");
//                            ssuppliername.setText("");
//                            etscustname.setText("");
                            etsdate.setText("");
                            etvehicleno.setText("");


                            Toast.makeText(Inward_Tanker_Sampling.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();




                        }
                    });
        }


    }

}