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

    EditText etvehicleno,etinvoiceno,etinvoicedate,materialname,etsqty1,suomqty,snetweight,suomnetwt,svesselname,sstoragetn,
            ssuppliername,etscustname,etsdate,etssignofproduction;
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
        suomqty=(EditText) findViewById(R.id.etsUOMqty);
        snetweight=(EditText) findViewById(R.id.etsnetweight);
        suomnetwt=(EditText) findViewById(R.id.etsuomnetwt);
        svesselname=(EditText) findViewById(R.id.etsvesselname);
        sstoragetn=(EditText) findViewById(R.id.etstoragetnkn);
        ssuppliername=(EditText)findViewById(R.id.etssuppliername);
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
        String vehiclenumber =etvehicleno.getText().toString().trim();
        String invoicenumber = etinvoiceno.getText().toString().trim();
        String invoicedate = etinvoicedate.getText().toString().trim();
        String materialnames = materialname.getText().toString().trim();
        String Qty = etsqty1.getText().toString().trim();
        String uomqty = suomqty.getText().toString().trim();
        String netweight = snetweight.getText().toString().trim();
        String uomnetwe= suomnetwt.getText().toString().trim();
        String vesselname = svesselname.getText().toString().trim();
        String storagetanl = sstoragetn.getText().toString().trim();
        String suppliername = ssuppliername.getText().toString().trim();
        String customername = etscustname.getText().toString().trim();
        String  date = etsdate.getText().toString().trim();
        String signofproduction = etssignofproduction.getText().toString().trim();

        if (vehiclenumber.isEmpty() || invoicenumber.isEmpty() || invoicedate.isEmpty() || materialnames.isEmpty() ||Qty.isEmpty() ||uomqty.isEmpty()||
             netweight.isEmpty()|| uomnetwe.isEmpty()|| vesselname.isEmpty()|| storagetanl.isEmpty()|| suppliername.isEmpty() || customername.isEmpty()||
        date.isEmpty()|| signofproduction.isEmpty()){
            Toast.makeText(this, "All fields must be filled", Toast.LENGTH_SHORT).show();
        }
        else {


            Map<String,String> saitems = new HashMap<>();

            saitems.put("Vehicle Number",etvehicleno.getText().toString().trim());
            saitems.put("Invoice Number",etinvoiceno.getText().toString().trim());
            saitems.put("Invoice Date",etinvoicedate.getText().toString().trim());
            saitems.put("Material Name",materialname.getText().toString().trim());
            saitems.put("Qty",etsqty1.getText().toString().trim());
            saitems.put("UOMqty",suomqty.getText().toString().trim());
            saitems.put("Net Weight",snetweight.getText().toString().trim());
            saitems.put("UOM Net Weight",suomnetwt.getText().toString().trim());
            saitems.put("Vessel Name",svesselname.getText().toString().trim());
            saitems.put("Storage Tank",sstoragetn.getText().toString().trim());
            saitems.put("Supplier Name",ssuppliername.getText().toString().trim());
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
                            suomqty.setText("");
                            snetweight.setText("");
                            suomnetwt.setText("");
                            svesselname.setText("");
                            sstoragetn.setText("");
                            ssuppliername.setText("");
                            etscustname.setText("");
                            etsdate.setText("");
                            etssignofproduction.setText("");

                            Toast.makeText(Inward_Tanker_Sampling.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();




                        }
                    });
        }


    }

}