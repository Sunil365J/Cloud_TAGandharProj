package com.android.gandharvms.Inward_Tanker_Security;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Inward_Tanker_Security extends AppCompatActivity implements View.OnClickListener {

    String [] items = {"Capital Register", "General Register","Inward Register"};


    //uom
    String [] qtyuom = {"Ton","Litre","KL","Kgs","pcs"};

    String [] netweuom = {"Ton","Litre","KL","Kgs","pcs"};

    AutoCompleteTextView autoCompleteTextView,autoCompleteTextView1,autoCompleteTextView2;
    ArrayAdapter<String> registeritem;
    ArrayAdapter<String> qtyuomdrop;
    ArrayAdapter<String> netweuomdrop;


    EditText etreg,etvehical,etinvoice,etdate,etpartyname,etmaterial,etintime,etnetweight,etqty,etoum,etregister,etqtyoum,etnetoum;
    Button btnadd,button1,dbbutton;
    EditText editmaterial,editqty,edituom;


    FirebaseFirestore dbroot;

    DatePickerDialog picker;

    List<String> teamList = new ArrayList<>();
    LinearLayout linearLayout;
    AppCompatSpinner spinner;
    TimePickerDialog tpicker;

    private SharedPreferences sharedPreferences;
    private int autoGeneratedNumber;
    private final int MAX_LENGTH=10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inward_tanker_security);



        //Send Notification to all
        //Send Notification to all
        FirebaseMessaging.getInstance().subscribeToTopic("dPpp-6hoRLmKIK_2skqeQ2:APA91bFc5tJGfJufPJ-pC5fUX-8t-yK9cFrATFK7eeMzORTE9zz1DjYUuNFZxHhkykFcItfoIb3or8VCrhs0Lc4Avdlwfuhb1jkNlEY-Cd6sjkYUNWFRAoj6sRAxJd7TgMJc3vSF-1K9");


        //for register dropdown
//        autoCompleteTextView = findViewById(R.id.etregister);
//        registeritem = new ArrayAdapter<String>(this,R.layout.security_registerlist,items);
//        autoCompleteTextView.setAdapter(registeritem);
//        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String items = parent.getItemAtPosition(position).toString();
//                Toast.makeText(getApplicationContext(),"Item: "+items,Toast.LENGTH_SHORT).show();
//            }
//        });

                   //uom and netwe dropdown
        autoCompleteTextView1 = findViewById(R.id.qtyuom);
        qtyuomdrop = new ArrayAdapter<String>(this,R.layout.in_ta_se_qty,qtyuom);
        autoCompleteTextView1.setAdapter(qtyuomdrop);
        autoCompleteTextView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String qtyuom = parent.getItemAtPosition(position).toString();
                Toast.makeText(Inward_Tanker_Security.this, "qtyuom: "+qtyuom, Toast.LENGTH_SHORT).show();
            }
        });

        autoCompleteTextView2 = findViewById(R.id.netweuom);
       netweuomdrop = new ArrayAdapter<String>(this,R.layout.in_ta_se_nw,netweuom);
       autoCompleteTextView2.setAdapter(netweuomdrop);

       autoCompleteTextView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               String neweuom = parent.getItemAtPosition(position).toString();
               Toast.makeText(Inward_Tanker_Security.this, "netwe: "+neweuom, Toast.LENGTH_SHORT).show();
           }
       });
        autoCompleteTextView1 = findViewById(R.id.qtyuom);
        qtyuomdrop = new ArrayAdapter<String>(this,R.layout.in_ta_se_qty,qtyuom);
        autoCompleteTextView1.setAdapter(qtyuomdrop);
        autoCompleteTextView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String qtyuom = parent.getItemAtPosition(position).toString();
                Toast.makeText(Inward_Tanker_Security.this, "qtyuom: "+qtyuom, Toast.LENGTH_SHORT).show();
            }
        });








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

//        etregister=(AutoCompleteTextView)findViewById(R.id.etregister);
        etreg=(EditText) findViewById(R.id.etserialnumber);
        etvehical=(EditText) findViewById(R.id.etvehical);
        etinvoice=(EditText) findViewById(R.id.etinvoice);
        etdate =(EditText) findViewById(R.id.etdate);
        etpartyname=(EditText) findViewById(R.id.etpartyname);
        etmaterial=(EditText)findViewById(R.id.etmaterial);
        etintime=(EditText) findViewById(R.id.etqty);
        etnetweight=(EditText) findViewById(R.id.etnetweight);
        etqty=(EditText) findViewById(R.id.etintime);
//        etoum=(EditText) findViewById(R.id.etoum);
        etqtyoum=(EditText) findViewById(R.id.qtyuom);
        etnetoum=(EditText)findViewById(R.id.netweuom);



             // for Auto Genrated serial number
        sharedPreferences = getSharedPreferences("VehicleManagementPrefs", MODE_PRIVATE);


        //
        linearLayout= findViewById(R.id.layout_list);
        button1=findViewById(R.id.button_add);
        button1.setOnClickListener(this);
        dbbutton=findViewById(R.id.dbview);


        teamList.add("Ton");
        teamList.add("Litre");
        teamList.add("KL");
        teamList.add("Kgs");
        teamList.add("Pcs");

                                      //listdata button
        dbbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Inward_Tanker_Security.this, Inward_Tanker_Security_Viewdata.class));
            }
        });



        etdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                // Array of month abbreviations
                String[] monthAbbreviations = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
                picker = new DatePickerDialog(Inward_Tanker_Security.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Use the month abbreviation from the array
                        String monthAbbreviation = monthAbbreviations[month];
                        etdate.setText(dayOfMonth + "/" + monthAbbreviation + "/" + year);
                    }
                }, year, month, day);
                picker.show();
            }
        });

        etqty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int hours = calendar.get(Calendar.HOUR_OF_DAY);
                int mins = calendar.get(Calendar.MINUTE);
                tpicker = new TimePickerDialog(Inward_Tanker_Security.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Calendar c = Calendar.getInstance();
                        c.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        c.set(Calendar.MINUTE,minute);

                        // Set the formatted time to the EditText
                        etqty.setText(hourOfDay +":"+ minute );
                    }
                },hours,mins,false);
                tpicker.show();
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


        etvehical.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() > MAX_LENGTH) {
                    etvehical.removeTextChangedListener(this);
                    String trimmedText = editable.toString().substring(0, MAX_LENGTH);
                    etvehical.setText(trimmedText);
                    etvehical.setSelection(MAX_LENGTH); // Move cursor to the end
                    etvehical.addTextChangedListener(this);
                } else if (editable.length() < MAX_LENGTH) {
                    // Show an error message for less than 10 digits
                    etvehical.setError("Invalid format. Enter 10 Character. \n Vehicle No Format - ST00AA9999 OR YYBR9999AA");
                } else {
                    // Clear any previous error message when valid
                    etvehical.setError(null);
                }
            }
        });

                         // AUTO GENRATED SERIAL NUMBER


        int lastDay = sharedPreferences.getInt("lastDay", -1);
        int currentDay = Integer.parseInt(getDay());
        if (currentDay != lastDay) {
            // Day has changed, reset auto-generated number to 1
            sharedPreferences.edit().putInt("autoGeneratedNumber", 1).apply();
            sharedPreferences.edit().putInt("lastDay", currentDay).apply();
        }



        if (sharedPreferences != null) {
            autoGeneratedNumber = sharedPreferences.getInt("autoGeneratedNumber", 1);
            String autoGeneratedNumberString = String.format("%03d", autoGeneratedNumber);
            // Create the serial number
            String serialNumber = "I" + GetYear() + getMonth() + getDay() + autoGeneratedNumberString;
            // Set the serial number in the EditText
            etreg.setText(serialNumber);
            // Increment and store the auto-generated number for the next vehicle

        } else {
            // Handle the case where sharedPreferences is null
            // This might involve displaying an error message or taking appropriate action
            Log.e("MainActivity", "SharedPreferences is null");
        }

    }

    private String GetYear() {
        SimpleDateFormat yearFormat = new SimpleDateFormat("yy", Locale.getDefault());
        return yearToLetter(yearFormat.format(new Date()));
    }

    private String getMonth() {
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM", Locale.getDefault());
        return monthToLetter(monthFormat.format(new Date()));
    }

    private String getDay() {
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd", Locale.getDefault());
        return dayFormat.format(new Date());
    }

    private String monthToLetter(String month) {
        switch (month) {
            case "01":
                return "A";
            case "02":
                return "B";
            case "03":
                return "C";
            case "04":
                return "D";
            case "05":
                return "E";
            case "06":
                return "F";
            case "07":
                return "G";
            case "08":
                return "H";
            case "09":
                return "I";
            case "10":
                return "J";
            case "11":
                return "K";
            case "12":
                return "L";
            default:
                return null; // Default to January (A) if month is not recognized
        }
    }
    private String yearToLetter(String month) {
        switch (month) {
            case "23":
                return "A";
            case "24":
                return "B";
            case "25":
                return "C";
            case "26":
                return "D";
            case "28":
                return "E";
            case "29":
                return "F";
            case "30":
                return "G";
            case "31":
                return "H";
            case "32":
                return "I";
            case "33":
                return "J";
            case "34":
                return "K";
            case "35":
                return "L";
            case "36":
                return "M";
            case "37":
                return "N";
            case "38":
                return "O";
            case "39":
                return "P";
            case "40":
                return "Q";
            case "41":
                return "R";
            case "42":
                return "S";
            case "43":
                return "T";
            case "44":
                return "U";
            case "45":
                return "V";
            case "46":
                return "W";
            case "47":
                return "X";
            case "48":
                return "Y";
            case "49":
                return "Z";
            default:
                return null;
            // Default to January (A) if month is not recognized
        }
    }
               // finished


    public void onClick(View v){
        addview();
    }
    private void addview(){

        View materialview = getLayoutInflater().inflate(R.layout.row_add_material,null,false);


//        EditText editText = (EditText) materialview.findViewById(R.id.editmaterial);
//        EditText editqty = (EditText) materialview.findViewById(R.id.editqty);
//        EditText edituom = (EditText) materialview.findViewById(R.id.edituom);
//        ImageView img = (ImageView) materialview.findViewById(R.id.editcancel);

        EditText editText = (EditText) materialview.findViewById(R.id.editmaterial);
        EditText editqty = (EditText) materialview.findViewById(R.id.editqty);
        AppCompatSpinner spinner =  (AppCompatSpinner) materialview.findViewById(R.id.spinner_team);
        ImageView img = (ImageView) materialview.findViewById(R.id.editcancel);

        linearLayout.addView(materialview);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,teamList);
        spinner.setAdapter(arrayAdapter);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeView(materialview);
            }
        });

    }
    private void removeView(View view){

        linearLayout.removeView(view);
    }



    public void makeNotification(String vehicleNo,String outTime){
        FcmNotificationsSender notificationsSender = new FcmNotificationsSender("dPpp-6hoRLmKIK_2skqeQ2:APA91bFc5tJGfJufPJ-pC5fUX-8t-yK9cFrATFK7eeMzORTE9zz1DjYUuNFZxHhkykFcItfoIb3or8VCrhs0Lc4Avdlwfuhb1jkNlEY-Cd6sjkYUNWFRAoj6sRAxJd7TgMJc3vSF-1K9",
                "Inward Tanker Security Process Done..!",
                "Vehicle Number:-" + vehicleNo + " has completed security process at " + outTime,
                getApplicationContext(), Inward_Tanker_Security.this);
        notificationsSender.SendNotifications();
    }
    private String getCurrentTime() {
        // Get the current time
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
        return sdf.format(new Date());
    }

    public void insertdata()
    {

//        String register = etregister.getText().toString().trim();
      String serialnumber = etreg.getText().toString().trim();
      String vehicalnumber = etvehical.getText().toString().trim();
      String invoicenumber = etinvoice.getText().toString().trim();
      String Date = etdate.getText().toString().trim();
      String partyname = etpartyname.getText().toString().trim();
      String material = etmaterial.getText().toString().trim();
      String qty = etqty.getText().toString().trim();
//      String uom = etoum.getText().toString().trim();
      String netweight = etnetweight.getText().toString().trim();
      String intime = etintime.getText().toString().trim();
        String outTime = getCurrentTime();//Insert out Time Directly to the Database
        String qtyuom=etqtyoum.getText().toString().trim();
        String netweuom=etnetoum.getText().toString().trim();

//      String qty = etintime.getText().toString().trim();
//      String uom = etnetweight.getText().toString().trim();
//      String netweight = etqty.getText().toString().trim();
//      String intime = etoum.getText().toString().trim();


      //

      if ( vehicalnumber.isEmpty() || invoicenumber.isEmpty() || Date.isEmpty() || partyname.isEmpty()||
              material.isEmpty()|| qty.isEmpty() || netweight.isEmpty() || intime.isEmpty()  ){

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
//          items.put("uom",.getText().toString().trim());
          items.put("netweight",etnetweight.getText().toString().trim());
          items.put("intime",etqty.getText().toString().trim());
//          items.put("SelectRegister",etregister.getText().toString().trim());
          items.put("outTime",outTime.toString());
          items.put("qtyuom",etqtyoum.getText().toString().trim());
          items.put("netweightuom",etnetoum.getText().toString().trim());


          makeNotification(etvehical.getText().toString().trim(),outTime.toString());
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
//                          etoum.setText("");
//                          etregister.setText("");
                          etnetoum.setText("");
                          etqtyoum.setText("");

                          Toast.makeText(getApplicationContext(),"Inserted Successfully",Toast.LENGTH_SHORT).show();

                      }
                  });
          Intent intent= new Intent(this, Inward_Tanker.class);
          startActivity(intent);


                         // Auto Genrated serial number
          sharedPreferences.edit().putInt("autoGeneratedNumber", autoGeneratedNumber + 1).apply();

      }

      }





    //uppercase vehicle number
//    EditText editText = findViewById(R.id.etvehical);
//
//    public void beforeTextchanged(CharSequence s, int start, int after){
//
//    }
//    public void onTextChanged(CharSequence s, int start, int before, int count){
//    }
//
//    public void aftertextchanged(Editable s) {
//        editText.removeTextChangedListener((TextWatcher) this);
//        String upperCaseText = s.toString().toUpperCase();
//        editText.setText(upperCaseText);
//        editText.setSelection(upperCaseText.length());
//        editText.addTextChangedListener((TextWatcher) this);
//    }



}