package com.android.gandharvms.Inward_Tanker_Laboratory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.gandharvms.FcmNotificationsSender;
import com.android.gandharvms.Inward_Tanker;
import com.android.gandharvms.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Inward_Tanker_Laboratory extends AppCompatActivity {

    EditText etintime, etpsample,etvehiclenumber,etpapperance,etpodor,etpcolour,etpdensity,etqty,etPrcstest,etpkv,ethundred,etanline,etflash,etpaddtest,etpsamplere,etpremark,etpsignQc,etpdatesignofsign;
    Button etlabsub;
    Button view;

    FirebaseFirestore dblabroot;

    DatePickerDialog picker,picker1,picker2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inward_tanker_laboratory);
        //Send Notification to all
        FirebaseMessaging.getInstance().subscribeToTopic("all");

        etintime = (EditText) findViewById(R.id.etintime);
        etpsample = (EditText) findViewById(R.id.etpsample);
        etvehiclenumber = (EditText) findViewById(R.id.vehiclenumber);
        etpapperance = (EditText) findViewById(R.id.etpapperance);
        etpodor = (EditText) findViewById(R.id.etpodor);
        etpcolour = (EditText) findViewById(R.id.etpcolour);
        etqty = (EditText) findViewById(R.id.qtycolor);
        etpdensity = (EditText) findViewById(R.id.etpdensity);
        etPrcstest = (EditText) findViewById(R.id.etPrcstest);
        etpkv=(EditText) findViewById(R.id.etpkv);
        ethundred = (EditText)findViewById(R.id.hundered);
        etanline = (EditText) findViewById(R.id.anline);
        etflash = (EditText) findViewById(R.id.flash);
        etpaddtest=(EditText) findViewById(R.id.etpaddtest);
        etpsamplere=(EditText) findViewById(R.id.etpsamplere);
        etpremark =(EditText) findViewById(R.id.etpremark);
        etpsignQc=(EditText) findViewById(R.id.etpsignQc);
        etpdatesignofsign=(EditText) findViewById(R.id.etpdatesignofsign);



        etlabsub=(Button) findViewById(R.id.etlabsub);

        view = findViewById(R.id.viewclick);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Inward_Tanker_Laboratory.this,Inward_Tanker_Lab_Viewdata.class));
            }
        });



        etpsample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                picker = new DatePickerDialog(Inward_Tanker_Laboratory.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        etpsample.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                },year,month,day);
                picker.show();


            }
        });

        etpsamplere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                picker1= new DatePickerDialog(Inward_Tanker_Laboratory.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        etpsamplere.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                },year,month,day);
                picker1.show();
            }
        });

        etpdatesignofsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                picker2 = new DatePickerDialog(Inward_Tanker_Laboratory.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        etpdatesignofsign.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                },year,month,day);
                picker2.show();
            }
        });

        dblabroot=FirebaseFirestore.getInstance();

        etlabsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                labinsertdata();
            }
        });
    }
    public void makeNotification(){
        FcmNotificationsSender notificationsSender = new FcmNotificationsSender("/topics/all",
                "Laboratory Process Done..!",
                "Inward Tanker Process Has Completed",
                getApplicationContext(), Inward_Tanker_Laboratory.this);
        notificationsSender.SendNotifications();
    }
    public void labinsertdata()
    {
        String intime = etintime.getText().toString().trim();
        String sample = etpsample.getText().toString().trim();
        String vehicle = etvehiclenumber.getText().toString().trim();
        String apperance = etpapperance.getText().toString().trim();
        String odor = etpodor.getText().toString().trim();
        String color = etpcolour.getText().toString().trim();
        String qty = etqty.getText().toString().trim();
        String density = etpdensity.getText().toString().trim();
        String rcsTest = etPrcstest.getText().toString().trim();
        String kv = etpkv.getText().toString().trim();
        String hundred = ethundred.getText().toString().trim();
        String anline = etanline.getText().toString().trim();
        String flash = etflash.getText().toString().trim();
        String addTest = etpaddtest.getText().toString().trim();
        String sampleTest = etpsamplere.getText().toString().trim();
        String remark = etpremark.getText().toString().trim();
        String signQc = etpsignQc.getText().toString().trim();
        String dateSignOfSign = etpdatesignofsign.getText().toString().trim();


        if ( intime.isEmpty() || sample.isEmpty() || vehicle.isEmpty() ||  apperance.isEmpty() || odor.isEmpty() || color.isEmpty() || qty.isEmpty()||  anline.isEmpty()|| flash.isEmpty()|| density.isEmpty() || rcsTest.isEmpty() ||
                kv.isEmpty() || addTest.isEmpty() || sampleTest.isEmpty() || remark.isEmpty() || signQc.isEmpty() || dateSignOfSign.isEmpty()){

            Toast.makeText(this, "All fields must be filled", Toast.LENGTH_SHORT).show();
        }else {



            Map<String,String> labitems = new HashMap<>();

            labitems.put("In_Time",etintime.getText().toString().trim());
            labitems.put("sample_reciving",etpsample.getText().toString().trim());
            labitems.put("Vehicle_Number",etvehiclenumber.getText().toString().trim());
            labitems.put("apperance",etpapperance.getText().toString().trim());
            labitems.put("odor",etpodor.getText().toString().trim());
            labitems.put("color",etpcolour.getText().toString().trim());
            labitems.put("Qty",etqty.getText().toString().trim());
            labitems.put("density",etpdensity.getText().toString().trim());
            labitems.put("Rcs_Test",etPrcstest.getText().toString().trim());
            labitems.put("40°KV",etpkv.getText().toString().trim());
            labitems.put("100°KV",ethundred.getText().toString().trim());
            labitems.put("Anline_Point",etanline.getText().toString().trim());
            labitems.put("Flash_Point",etflash.getText().toString().trim());
            labitems.put("Additional_test",etpaddtest.getText().toString().trim());
            labitems.put("sample_test",etpsamplere.getText().toString().trim());
            labitems.put("Remark",etpremark.getText().toString().trim());
            labitems.put("sign_of",etpsignQc.getText().toString().trim());
            labitems.put("Date_and_Time",etpdatesignofsign.getText().toString().trim());


            dblabroot.collection("Inward Tanker Laboratory").add(labitems)
                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {

                            etintime.setText("");
                            etpsample.setText("");
                            etvehiclenumber.setText("");
                            etpapperance.setText("");
                            etpodor.setText("");
                            etpcolour.setText("");
                            etqty.setText("");
                            etpdensity.setText("");
                            etPrcstest.setText("");
                            etpkv.setText("");
                            ethundred.setText("");
                            etanline.setText("");
                            etflash.setText("");
                            etpaddtest.setText("");
                            etpsamplere.setText("");
                            etpremark.setText("");
                            etpsignQc.setText("");
                            etpdatesignofsign.setText("");

                            Toast.makeText(Inward_Tanker_Laboratory.this, "Data Added Successfully", Toast.LENGTH_SHORT).show();



                        }
                    });
            Intent intent= new Intent(this, Inward_Tanker.class);
            startActivity(intent);
            makeNotification();


        }




//
//        Map<String,String> labitems = new HashMap<>();
//
//        labitems.put("sample reciving",etpsample.getText().toString().trim());
//        labitems.put("apperance",etpapperance.getText().toString().trim());
//        labitems.put("odor",etpodor.getText().toString().trim());
//        labitems.put("color",etpcolour.getText().toString().trim());
//        labitems.put("density",etpdensity.getText().toString().trim());
//        labitems.put("Rcs Test",etPrcstest.getText().toString().trim());
//        labitems.put("KV",etpkv.getText().toString().trim());
//        labitems.put("Additional test",etpaddtest.getText().toString().trim());
//        labitems.put("sample test",etpsamplere.getText().toString().trim());
//        labitems.put("Remark",etpremark.getText().toString().trim());
//        labitems.put("sign of",etpsignQc.getText().toString().trim());
//        labitems.put("Date and Time",etpdatesignofsign.getText().toString().trim());
//
//
//        dblabroot.collection("Inward Tanker Laboratory").add(labitems)
//                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
//                    @Override
//                    public void onComplete(@NonNull Task<DocumentReference> task) {
//
//                        etpsample.setText("");
//                        etpapperance.setText("");
//                        etpodor.setText("");
//                        etpcolour.setText("");
//                        etpdensity.setText("");
//                        etPrcstest.setText("");
//                        etpkv.setText("");
//                        etpaddtest.setText("");
//                        etpsamplere.setText("");
//                        etpremark.setText("");
//                        etpsignQc.setText("");
//                        etpdatesignofsign.setText("");
//
//                        Toast.makeText(Inward_Tanker_Laboratory.this, "Data Added Successfully", Toast.LENGTH_SHORT).show();
//
//
//
//
//
//
//
//
//
//
//
//
//                    }
//                });


    }
}