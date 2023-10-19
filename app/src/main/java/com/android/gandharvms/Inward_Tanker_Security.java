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

import java.time.Clock;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class Inward_Tanker_Security extends AppCompatActivity {

    EditText etreg,etvehical,etinvoice,etdate,etpartyname,etmaterial,etintime,etnetweight,etqty,etoum;
    Button btnadd;
    FirebaseFirestore dbroot;

    DatePickerDialog picker;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inward_tanker_security);





//        final EditText regnumber = findViewById(R.id.etreg);
//        final EditText vehicalnumber = findViewById(R.id.etvehical);
//        final EditText invoiceno = findViewById(R.id.etinvoice);
//        final EditText partyname = findViewById(R.id.etpartyname);
//        final EditText material = findViewById(R.id.etmaterial);
//        final EditText intime = findViewById(R.id.etintime);
//        final EditText netweight = findViewById(R.id.etnetweight);
//        final EditText qty = findViewById(R.id.etqty);
//        final EditText uom = findViewById(R.id.etoum);
//        final Button submit = findViewById(R.id.submit);

        etreg=(EditText) findViewById(R.id.etreg);
        etvehical=(EditText) findViewById(R.id.etvehical);
        etinvoice=(EditText) findViewById(R.id.etvehical);
        etdate =(EditText) findViewById(R.id.etdate);
        etpartyname=(EditText) findViewById(R.id.etpartyname);
        etmaterial=(EditText)findViewById(R.id.etmaterial);
        etintime=(EditText) findViewById(R.id.etintime);
        etnetweight=(EditText) findViewById(R.id.etnetweight);
        etqty=(EditText) findViewById(R.id.etqty);
        etoum=(EditText) findViewById(R.id.etoum);




        etdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                picker = new DatePickerDialog(Inward_Tanker_Security.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                             etdate.setText(dayOfMonth + "/" + (month +1 ) + "/" + year);
                    }
                } ,year,month,day);
                picker.show();
            }
        });


        btnadd=(Button) findViewById(R.id.submit);
        dbroot=FirebaseFirestore.getInstance();
           btnadd.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   insertdata();
               }
           });




    }

    public void insertdata()
    {
      String serialnumber = etreg.getText().toString().trim();
      String vehicalnumber = etvehical.getText().toString().trim();
      String invoicenumber = etvehical.getText().toString().trim();
      String Date = etdate.getText().toString().trim();
      String partyname = etpartyname.getText().toString().trim();
      String material = etmaterial.getText().toString().trim();
      String qty = etintime.getText().toString().trim();
      String uom = etnetweight.getText().toString().trim();
      String netweight = etqty.getText().toString().trim();
      String intime = etoum.getText().toString().trim();

      if (serialnumber.isEmpty() || vehicalnumber.isEmpty() || invoicenumber.isEmpty() || Date.isEmpty() || partyname.isEmpty()||
              material.isEmpty()|| qty.isEmpty() || uom.isEmpty() || netweight.isEmpty() || intime.isEmpty() ){

          Toast.makeText(this, "All fields must be filled", Toast.LENGTH_SHORT).show();
      }
      else {



          Map<String,String> items= new HashMap<>();
          items.put("SerialNumber",etreg.getText().toString().trim());
          items.put("vehicalnumber",etvehical.getText().toString().trim());
          items.put("invoiceno",etinvoice.getText().toString().trim());
          items.put("date",etdate.getText().toString().trim());
          items.put("partyname",etpartyname.getText().toString());
          items.put("material",etmaterial.getText().toString().trim());
          items.put("qty",etintime.getText().toString().trim());
          items.put("uom",etnetweight.getText().toString().trim());
          items.put("netweight",etqty.getText().toString().trim());
          items.put("intime",etoum.getText().toString().trim());


          dbroot.collection("Inward Tanker Security").add(items)


                  .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                      @Override
                      public void onComplete(@NonNull Task<DocumentReference> task) {
                          etreg.setText("");
                          etvehical.setText("");
                          etinvoice.setText("");
                          etdate.setText("");
                          etpartyname.setText("");
                          etmaterial.setText("");
                          etintime.setText("");
                          etnetweight.setText("");
                          etqty.setText("");
                          etoum.setText("");

                          Toast.makeText(getApplicationContext(),"Inserted Successfully",Toast.LENGTH_SHORT).show();

                      }
                  });

      }

      }




}