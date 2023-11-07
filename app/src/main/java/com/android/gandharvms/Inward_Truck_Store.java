package com.android.gandharvms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Inward_Truck_Store extends AppCompatActivity {

    String [] items = {"Ton","Litre","KL","Kgs","pcs"};
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;
    EditText etintime,etserialnumber,etvehicalnum,etinvoiceno,etdate,etsupplier,etmaterial,etqty,etoum;
    Button trssubmit;
    DatePickerDialog trspicker;

    FirebaseFirestore trsdbroot;
    TimePickerDialog intruckspicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inward_truck_store);


        //for uom
        autoCompleteTextView =findViewById(R.id.etsuom);
        adapterItems = new ArrayAdapter<String>(this,R.layout.list_itemuom,items);
        autoCompleteTextView.setAdapter(adapterItems);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String items = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Item: "+items, Toast.LENGTH_SHORT).show();
            }
        });


        etintime =(EditText) findViewById(R.id.etintime);
        etserialnumber=(EditText) findViewById(R.id.ettrsserialnumber);
        etvehicalnum = (EditText) findViewById(R.id.ettrsvehical);
        etinvoiceno=(EditText) findViewById(R.id.ettrsinvoice);
        etdate=(EditText) findViewById(R.id.ettrdate);
        etsupplier=(EditText) findViewById(R.id.ettrsupplier);
        etmaterial=(EditText) findViewById(R.id.ettsmaterial);
        etqty=(EditText) findViewById(R.id.etsqty);
        etoum=(EditText) findViewById(R.id.etsuom);




        //intime

        etintime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int hours = calendar.get(Calendar.HOUR_OF_DAY);
                int mins = calendar.get(Calendar.MINUTE);


                intruckspicker = new TimePickerDialog(Inward_Truck_Store.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Calendar c = Calendar.getInstance();
                        c.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        c.set(Calendar.MINUTE,minute);
                        etintime.setText(hourOfDay +":"+ minute );
                    }
                },hours,mins,false);
                intruckspicker.show();
            }
        });
        //date
        etdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                trspicker = new DatePickerDialog(Inward_Truck_Store.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        etdate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                },year,month,day);
                trspicker.show();
            }
        });

        trssubmit=(Button)findViewById(R.id.submit);
        trsdbroot=FirebaseFirestore.getInstance();

        trssubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trsinsert();
            }
        });


    }

    public void trsinsert()
    {
        String intime = etintime.getText().toString().trim();
        String serialnumber = etserialnumber.getText().toString().trim();
        String vehicalnumber = etvehicalnum.getText().toString().trim();
        String invoicenum = etinvoiceno.getText().toString().trim();
        String date = etdate.getText().toString().trim();
        String supplier=etsupplier.getText().toString().trim();
        String material = etmaterial.getText().toString().trim();
        String qty = etqty.getText().toString().trim();
        String oum = etoum.getText().toString().trim();

        if (intime.isEmpty()|| serialnumber.isEmpty()|| vehicalnumber.isEmpty()|| invoicenum.isEmpty()|| date.isEmpty()|| supplier.isEmpty()||
        material.isEmpty()|| qty.isEmpty()|| oum.isEmpty()){
            Toast.makeText(this, "All fields must be filled", Toast.LENGTH_SHORT).show();
        }
        else {

            Map<String,String>trsitens = new HashMap<>();
            trsitens.put("In Time ",etintime.getText().toString().trim());
            trsitens.put("Serial Number",etserialnumber.getText().toString().trim());
            trsitens.put("Vehicle Number",etvehicalnum.getText().toString().trim());
            trsitens.put("Date",etdate.getText().toString().trim());
            trsitens.put("Supplier",etsupplier.getText().toString().trim());
            trsitens.put("Material",etmaterial.getText().toString().trim());
            trsitens.put("Qty",etqty.getText().toString().trim());
            trsitens.put("Oum",etoum.getText().toString().trim());


            trsdbroot.collection("Inward Truck Store").add(trsitens)
                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {


                           etintime.setText("");
                           etserialnumber.setText("");
                           etvehicalnum.setText("");
                           etinvoiceno.setText("");
                           etdate.setText("");
                           etsupplier.setText("");
                           etmaterial.setText("");
                           etqty.setText("");
                           etoum.setText("");

                            Toast.makeText(Inward_Truck_Store.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();

                        }
                    });
        }
    }
}