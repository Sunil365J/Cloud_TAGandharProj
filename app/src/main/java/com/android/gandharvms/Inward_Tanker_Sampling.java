package com.android.gandharvms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Inward_Tanker_Sampling extends AppCompatActivity {

    EditText etvehicleno,etinvoiceno,etinvoicedate,materialname,etsqty1,etsUOMnetwt,etsvessalname,etstoragetankno,etssuppliername,etsdensity,
            etsbatchnumber,etscustname,etsdate,etssignofproduction;
    Button etssubmit;
    FirebaseFirestore sadbroot;
    DatePickerDialog picker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inward_tanker_sampling);
        etvehicleno= (EditText)findViewById(R.id.etvehicleno);
        etinvoiceno=(EditText) findViewById(R.id.etinvoiceno);
        etinvoicedate=(EditText) findViewById(R.id.invoicedate);
        materialname=(EditText) findViewById(R.id.etmaterialname1);
        etsqty1=(EditText) findViewById(R.id.etsqty);
        etsUOMnetwt=(EditText) findViewById(R.id.etsUOMnetwt);
        etsvessalname=(EditText) findViewById(R.id.etsvessalname);
        etstoragetankno=(EditText) findViewById(R.id.etstoragetankno);
        etssuppliername=(EditText) findViewById(R.id.etssuppliername);
        etsdensity=(EditText) findViewById(R.id.etsdensity);
        etsbatchnumber=(EditText)findViewById(R.id.etsbatchnumber);
        etscustname=(EditText)findViewById(R.id.etscustname);
        etsdate=(EditText)findViewById(R.id.etsdate);
        etssignofproduction=(EditText)findViewById(R.id.etssignofproduction);

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
        Map<String,String> saitems = new HashMap<>();

        saitems.put("Vehicle Number",etvehicleno.getText().toString().trim());
        saitems.put("Material Name",etinvoiceno.getText().toString().trim());
        saitems.put("Quantity",etinvoicedate.getText().toString().trim());
        saitems.put("UOM Qty",materialname.getText().toString().trim());
        saitems.put("Net.Wet",etsqty1.getText().toString().trim());
        saitems.put("UOM Net.wt",etsUOMnetwt.getText().toString().trim());
        saitems.put("vessel name",etsvessalname.getText().toString().trim());
        saitems.put("storage Tank name",etsvessalname.getText().toString().trim());
        saitems.put("Supplier Name",etssuppliername.getText().toString().trim());
        saitems.put("Density",etsdensity.getText().toString().trim());
        saitems.put("Batch Number",etsbatchnumber.getText().toString().trim());
        saitems.put("Customer Name",etscustname.getText().toString().trim());
        saitems.put("Date",etsdate.getText().toString().trim());
        saitems.put("sign of production",etssignofproduction.getText().toString().trim());


        sadbroot.collection("Inward Tanker Sampling").add(saitems)

                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {

                        etvehicleno.setText("");
                        etinvoiceno.setText("");
                        etinvoicedate.setText("");
                        materialname.setText("");
                        etsqty1.setText("");
                        etsUOMnetwt.setText("");
                        etsvessalname.setText("");
                        etsvessalname.setText("");
                        etssuppliername.setText("");
                        etsdensity.setText("");
                        etsbatchnumber.setText("");
                        etscustname.setText("");
                        etsdate.setText("");
                        etssignofproduction.setText("");

                        Toast.makeText(Inward_Tanker_Sampling.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();




                    }
                });


    }

}