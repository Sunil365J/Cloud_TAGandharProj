package com.android.gandharvms;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.gandharvms.databinding.ActivityInwardTruckSecurityBinding;
import com.android.gandharvms.databinding.ActivityMainBinding;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;

public class Inward_Truck_Security extends AppCompatActivity {

    String [] items = {"Ton","Litre","KL","Kgs","pcs"};

    String [] items1 = {"Ton","Litre","KL","Kgs","pcs"};
    AutoCompleteTextView autoCompleteTextView;
    AutoCompleteTextView autoCompleteTextView1;
    ArrayAdapter<String> adapterItems;
    ArrayAdapter<String> adapterItems1;


    ActivityResultLauncher<String> launcher;
    EditText etintime,etserialnumber,etvehicalnumber,etsinvocieno,etsdate,etssupplier,etsmaterial,etsqty,etsuom,etsnetwt,etsuom2;
    ImageView img1,img2,img3,img4;
    Button wesubmit;
    FirebaseFirestore intrsdbroot;
    DatePickerDialog pickertr;
    TimePickerDialog tpicker;
    FirebaseStorage storage;
    Uri image1,image2,image3,image4;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inward_truck_security);

                                  // Uom work
        autoCompleteTextView= findViewById(R.id.etsuom);
        autoCompleteTextView1=findViewById(R.id.etsuom2);

        adapterItems = new ArrayAdapter<String>(this,R.layout.list_itemuom,items);
        adapterItems1 = new ArrayAdapter<String>(this,R.layout.in_tr_se_nwe_list,items1);

        autoCompleteTextView.setAdapter(adapterItems);
        autoCompleteTextView1.setAdapter(adapterItems1);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String items = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Item"+items, Toast.LENGTH_SHORT).show();
            }
        });
        autoCompleteTextView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String items1 = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Item"+items1, Toast.LENGTH_SHORT).show();
            }
        });


        etintime= (EditText) findViewById(R.id.etintime);
        etserialnumber= (EditText) findViewById(R.id.etserialnumber);
        etvehicalnumber= (EditText) findViewById(R.id.etvehicalnumber);
        etsinvocieno=(EditText) findViewById(R.id.etsinvocieno);
        etsdate=(EditText) findViewById(R.id.etsdate);
        etssupplier=(EditText) findViewById(R.id.etssupplier);
        etsmaterial=(EditText) findViewById(R.id.etsmaterial);
        etsqty=(EditText) findViewById(R.id.etsqty);
        etsuom=(EditText) findViewById(R.id.etsuom);
        etsnetwt=(EditText) findViewById(R.id.etsnetwt);
        etsuom2=(EditText) findViewById(R.id.etsuom2);

        img1=(ImageView)findViewById(R.id.intrtrlrco);
        img2=(ImageView)findViewById(R.id.intrdech);
        img3=(ImageView)findViewById(R.id.intrtain);
        img4=(ImageView)findViewById(R.id.intrewabi);

        storage = FirebaseStorage.getInstance();


      //for imgpicker
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGetContent.launch("image/*");
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m1GetContent.launch("image/*");
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m2GetContent.launch("image/*");
            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m3GetContent.launch("image/*");
            }
        });








        etsdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                pickertr = new DatePickerDialog(Inward_Truck_Security.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        etsdate.setText(dayOfMonth + "/" + (month +1 ) + "/" + year);
                    }
                } ,year,month,day);
                pickertr.show();
            }
        });

        etintime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Calendar calendar = Calendar.getInstance();
             int hours = calendar.get(Calendar.HOUR_OF_DAY);
             int mins = calendar.get(Calendar.MINUTE);


             tpicker = new TimePickerDialog(Inward_Truck_Security.this, new TimePickerDialog.OnTimeSetListener() {
                 @Override
                 public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                   Calendar c = Calendar.getInstance();
                   c.set(Calendar.HOUR_OF_DAY,hourOfDay);
                   c.set(Calendar.MINUTE,minute);
                   etintime.setText(hourOfDay +":"+ minute );
                 }
             },hours,mins,false);
             tpicker.show();
            }
        });




        wesubmit = (Button)findViewById(R.id.wesubmit);
        intrsdbroot=FirebaseFirestore.getInstance();



        wesubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trsedata();
                uploadimg();
            }
        });


    }

    //img

    ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),

            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri result) {

                    if (result !=  null){
                        img1.setImageURI(result);
                        image1=result;

                    }
                }
            });

    ActivityResultLauncher<String> m1GetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri result) {
                    if (result != null){
                        img2.setImageURI(result);
                    }

                }
            });
    ActivityResultLauncher<String> m2GetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri result) {
                    if (result != null){
                        img3.setImageURI(result);
                    }

                }
            });
    ActivityResultLauncher<String> m3GetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri result) {
                    if (result != null){
                        img4.setImageURI(result);
                    }

                }
            });



    public void trsedata(){

        String intime =  etintime.getText().toString().trim();
        String serialnumber = etserialnumber.getText().toString().trim();
        String vehicalnumber = etvehicalnumber.getText().toString().trim();
        String invoiceno =  etsinvocieno.getText().toString().trim();
        String date = etsdate.getText().toString().trim();
        String supplier = etssupplier.getText().toString().trim();
        String material = etsmaterial.getText().toString().trim();
        String qty = etsqty.getText().toString().trim();
        String uom = etsuom.getText().toString().trim();
        String netwt = etsnetwt.getText().toString().trim();
        String uom2 = etsuom2.getText().toString().trim();

        if (intime.isEmpty()|| serialnumber.isEmpty()|| vehicalnumber.isEmpty()|| invoiceno.isEmpty()|| date.isEmpty()|| supplier.isEmpty()|| material.isEmpty()
           || qty.isEmpty()|| uom.isEmpty()|| netwt.isEmpty()|| uom2.isEmpty()){
            Toast.makeText(this, "All fields must be filled", Toast.LENGTH_SHORT).show();
        }
        else {

            Map<String,String> trseitems = new HashMap<>();
            trseitems.put("Intime",etintime.getText().toString().trim());
            trseitems.put("serialnumber",etserialnumber.getText().toString().trim());
            trseitems.put("VehicalNumber",etvehicalnumber.getText().toString().trim());
            trseitems.put("invoicenumber",etsinvocieno.getText().toString().trim());
            trseitems.put("date",etsdate.getText().toString().trim());
            trseitems.put("Supplier",etssupplier.getText().toString().trim());
            trseitems.put("Material",etsmaterial.getText().toString().trim());
            trseitems.put("Qty",etsqty.getText().toString().trim());
            trseitems.put("UOM",etsuom.getText().toString().trim());
            trseitems.put("etsnetweight",etsnetwt.getText().toString().trim());
            trseitems.put("UOM2",etsuom2.getText().toString().trim());

            intrsdbroot.collection("Inward Truck Security").add(trseitems)
                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            etintime.setText("");
                            etserialnumber.setText("");
                            etvehicalnumber.setText("");
                            etsinvocieno.setText("");
                            etsdate.setText("");
                            etssupplier.setText("");
                            etsmaterial.setText("");
                            etsqty.setText("");
                            etsuom.setText("");
                            etsnetwt.setText("");
                            etsuom2.setText("");

                            Toast.makeText(Inward_Truck_Security.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();


                        }
                    });

        }

    }

    public void uploadimg(){
        List<Uri> imageUris = new ArrayList<>();
      if (image1 != null ){
          imageUris.add(image1);
//          StorageReference reference = storage.getReference().child("image/*" + UUID.randomUUID().toString());
      }
      if (image2 != null){
          imageUris.add(image2);
      }
      if (image3 != null){
          imageUris.add(image3);
      }
      if (image4 != null){
          imageUris.add(image4);
      }

      StorageReference storageReference = storage.getReference();
      for (Uri imageuris : imageUris){
          String Transport_LR_Copy = "image1"+UUID.randomUUID().toString();
          String Delivery_Chllan = "image2"+UUID.randomUUID().toString();
          String Tax_Invoice = "image3"+UUID.randomUUID().toString();
          String E_Way_Bill = "image4"+UUID.randomUUID().toString();


          StorageReference imgref1 = storageReference.child("images1/"+ Transport_LR_Copy);
          StorageReference imgref2 = storageReference.child("images2/"+Delivery_Chllan);
          StorageReference imgref3 = storageReference.child("images3/"+Tax_Invoice);
          StorageReference imgref4 = storageReference.child("images4/"+E_Way_Bill);

          imgref1.putFile(imageuris)
                  .addOnSuccessListener(taskSnapshot -> {
                     imgref1.getDownloadUrl().addOnSuccessListener(uri -> {
                         String imageUrl = uri.toString();
                     });
                  }).addOnFailureListener(e -> {

                  });
          imgref2.putFile(imageuris)
                  .addOnSuccessListener(taskSnapshot -> {
                      imgref2.getDownloadUrl().addOnSuccessListener(uri -> {
                          String imageurl = uri.toString();
                      });
                  }).addOnFailureListener(e -> {

                  });
          imgref3.putFile(imageuris)
                  .addOnSuccessListener(taskSnapshot -> {
                      imgref3.getDownloadUrl().addOnSuccessListener(uri -> {
                          String imageurl = uri.toString();
                      });
                  }).addOnFailureListener(e -> {

                  });
          imgref4.putFile(imageuris)
                  .addOnSuccessListener(taskSnapshot -> {
                      imgref4.getDownloadUrl().addOnSuccessListener(uri -> {
                          String imageuri = uri.toString();
                      });
                  }).addOnFailureListener(e -> {

                  });


      }
    }


}