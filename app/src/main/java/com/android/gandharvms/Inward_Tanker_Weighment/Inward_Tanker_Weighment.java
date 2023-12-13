package com.android.gandharvms.Inward_Tanker_Weighment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.gandharvms.FcmNotificationsSender;
import com.android.gandharvms.Inward_Tanker;
import com.android.gandharvms.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;


public class Inward_Tanker_Weighment extends AppCompatActivity {

    EditText etint,etserialnumber,etvehicalno,etsuppliername,etmaterialname,etcustname,etdriverno,etoano,etdate,
              etgrossweight,ettareweight,etnetweight,etdensity, etbatchno,etsignby,etweDatetime,etcontainer,etshortagedip,etshortageweight ;
    Button wesubmit;
    FirebaseFirestore wedbroot;

    EditText datetimeTextview;
    Calendar calendar = Calendar.getInstance();

    DatePickerDialog picker;

    Button view;

    private static final int REQUEST_IMAGE_CAPTURE = 1;
//    private static final int REQUEST_IMAGE_CAPTURE_2 = 2;
    private ImageView imageView1;
//    private ImageView imageView2;
    private Uri imageUri1;
//    private Uri imageUri2;
    private FirebaseStorage storage;
    private StorageReference storageReference;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inward_tanker_weighment);

        //Send Notification to all
        FirebaseMessaging.getInstance().subscribeToTopic("all");

        imageView1 = findViewById(R.id.imageView1);
//        imageView2 = findViewById(R.id.imageView2);



//        etserialnumber,etvehicalno,
//                etsuppliername,etmaterialname,
//                etcustname,etdrivername,
//                etoano,etdate,
//                etgrossweight,ettareweight,
//                etbatchno,etsignby,
//                etweDatetime,wesubmit

        etint = (EditText) findViewById(R.id.etintime);
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
        etcontainer= (EditText)findViewById(R.id.container);
        etshortagedip = (EditText)findViewById(R.id.shortagedip);
        etshortageweight =(EditText) findViewById(R.id.shortageweight);

        datetimeTextview=findViewById(R.id.etweDatetime);

        imageView1 = (ImageView) findViewById(R.id.imageView1);

        // Adding Gross weight and Tare weight
        etgrossweight.addTextChangedListener(textWatcher);
        ettareweight.addTextChangedListener(textWatcher);


        //view button
        view = findViewById(R.id.dbview);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Inward_Tanker_Weighment.this, Inward_Tanker_Weighment_Viewdata.class));
            }
        });

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
                weinsertdata(imageView1.toString());
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

    public void makeNotification(){
        FcmNotificationsSender notificationsSender = new FcmNotificationsSender("/topics/all",
                "Weighment Process Done..!",
                "Sampling Can Start there Work",
                getApplicationContext(), Inward_Tanker_Weighment.this);
        notificationsSender.SendNotifications();
    }


   public  void weinsertdata(String imageView1){

        String inte = etint.getText().toString().trim();
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
       String container = etcontainer.getText().toString().trim();
       String shortagedip = etshortagedip.getText().toString().trim();
       String shortageweight = etshortageweight.getText().toString().trim();




       if (  inte.isEmpty()||  serialnumber.isEmpty() || vehicelnumber.isEmpty() || suppliername.isEmpty() || materialname.isEmpty() ||
               custname.isEmpty() || driverno.isEmpty() || oan.isEmpty() || date.isEmpty() || grossweight.isEmpty() ||
               tareweight.isEmpty() || netweight.isEmpty() ||  density.isEmpty() || batchno.isEmpty() ||
               signby.isEmpty() || datetime.isEmpty() || container.isEmpty() || shortagedip.isEmpty() || shortageweight.isEmpty() ){
           Toast.makeText(this, "All fields must be filled", Toast.LENGTH_SHORT).show();
       }
       else {


           Map<String,String> weitems = new HashMap<>();
           weitems.put("In_Time",etint.getText().toString().trim());
           weitems.put("serial_number",etserialnumber.getText().toString().trim());
           weitems.put("vehicle_number",etvehicalno.getText().toString().trim());
           weitems.put("supplier_name",etsuppliername.getText().toString().trim());
           weitems.put("material_name",etmaterialname.getText().toString().trim());
           weitems.put("Customer_Name",etcustname.getText().toString().trim());
           weitems.put("Driver_Number",etdriverno.getText().toString().trim());
           weitems.put("OA_number",etoano.getText().toString().trim());
           weitems.put("Date",etdate.getText().toString().trim());
           weitems.put("Gross_Weight",etgrossweight.getText().toString().trim());
           weitems.put("Tare_Weight",ettareweight.getText().toString().trim());
           weitems.put("Net_Weight",etnetweight.getText().toString().trim());
           weitems.put("Density",etdensity.getText().toString().trim());
           weitems.put("Batch_Number",etbatchno.getText().toString().trim());
           weitems.put("Sign_By",etsignby.getText().toString().trim());
           weitems.put("We_Date_Time",etweDatetime.getText().toString().trim());
           weitems.put("Container_No",etcontainer.getText().toString().trim());
           weitems.put("shortage_Dip",etshortagedip.getText().toString().trim());
           weitems.put("shortage_weight",etshortageweight.getText().toString().trim());

           weitems.put("ImageURL",imageView1.toString());




           wedbroot.collection("Inward Tanker Weighment").add(weitems)
                   .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                       @Override
                       public void onComplete(@NonNull Task<DocumentReference> task) {

                           etint.setText("");
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
                           etcontainer.setText("");
                           etshortagedip.setText("");
                           etshortageweight.setText("");




                           Toast.makeText(Inward_Tanker_Weighment.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();


                       }
                   });
           Intent intent= new Intent(this, Inward_Tanker.class);
           startActivity(intent);
           makeNotification();




       }

       }

                                       // Adding Gross weight and Tare weight above two lines
     private TextWatcher textWatcher = new TextWatcher() {
         @Override
         public void beforeTextChanged(CharSequence s, int start, int count, int after) {

         }

         @Override
         public void onTextChanged(CharSequence s, int start, int before, int count) {

         }

         @Override
         public void afterTextChanged(Editable s) {

             calculateNetWeight();
         }
     };

    public void calculateNetWeight(){

        String grossweightStr = etgrossweight.getText().toString().trim();
        String tareweightStr = ettareweight.getText().toString().trim();

        double grossWeight = grossweightStr.isEmpty() ? 0.0 : Double.parseDouble(grossweightStr);
        double tareWeight = tareweightStr.isEmpty() ? 0.0 : Double.parseDouble(tareweightStr);

        double netweight = grossWeight + tareWeight;

        etnetweight.setText("Net weight: " + netweight);
    }

                   //  image upload firebase

    public void  captureImageFromCamera1(android.view.View view){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent,REQUEST_IMAGE_CAPTURE);
        }
    }
//    public void captureImageFromCamera2(android.view.View view){
//        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        if (takePictureIntent.resolveActivity(getPackageManager()) != null){
//            startActivityForResult(takePictureIntent,REQUEST_IMAGE_CAPTURE_2);
//        }
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK && data != null){
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");


            imageUri1 = getImageUri(imageBitmap);

            imageView1.setImageBitmap(imageBitmap);

            uploadImageToStorage(imageView1.toString());

        }
//        else if (resultCode == REQUEST_IMAGE_CAPTURE_2 && resultCode == RESULT_OK && data != null) {
//            Bundle extras = data.getExtras();
//            Bitmap imagBitmap = (Bitmap) extras.get("data");
//
//            imageUri2 = getImageUri(imagBitmap);
//
//            imageView2.setImageBitmap(imagBitmap);
//
//            uploadImageToStorage(imageUri2);
//
//        }
    }

    private Uri getImageUri (Bitmap bitmap){
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,bytes);
        String path = MediaStore.Images.Media.insertImage(getContentResolver(),bitmap,"Title",null);
        return Uri.parse(path);
    }

    private void uploadImageToStorage(String s){
        try {
            if (imageUri1 != null){
                StorageReference storageRef = FirebaseStorage.getInstance().getReference().child("Inward_Tanker_Weighment/" + UUID.randomUUID() + ".jpg");

                storageRef.putFile(imageUri1)
                        .addOnSuccessListener(taskSnapshot -> {
                            storageRef.getDownloadUrl().addOnSuccessListener(uri -> {


                                weinsertdata(imageView1.toString());
                            });
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(this, "Failed ", Toast.LENGTH_SHORT).show();
                        });
                    }
            }
        catch (Exception e){
            e.printStackTrace();
        }
       }
       private void saveImageUrlToFirestore(String imageUrl){

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        Map<String,Object> image = new HashMap<>();
        image.put("imageUrl",imageUrl);
//        image.put("In Driver", imageUrl);

           firestore.collection("Inward Tanker Weighment")
                .add(image)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(this, "Image Upload succesfully", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Failed to save", Toast.LENGTH_SHORT).show();
                });
       }
    }


