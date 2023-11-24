package com.android.gandharvms;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Inward_Tanker_Security_Viewdata extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<In_Tanker_Security_list>inTankerSecurityLists;
    In_Tanker_Se_Adapter in_tanker_se_adapter;

    FirebaseFirestore db;
    ProgressDialog progressDialog;
//    AlertDialog alertDialog;

    SearchView searchdata;
    Spinner spinner;
//    Context context;

                //date filter
    private DatePicker startDatePicker;
    private DatePicker endDatePicker;
    private Button fetchDataButton;
    private Button dateButton;

    private FirebaseFirestore dbs;

    EditText input_start,input_end;
    Button btn_start,btn_end;
    Calendar calendar = Calendar.getInstance();
    Context context;
    LayoutInflater layoutInflater;
    View showInput;
    Date date_start;
    Date date_end;
    Locale id = new Locale("in","ID");
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMMM-YYYY",id);

    Button subm;
    FirebaseFirestore rootdb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inward_tanker_security_viewdata);

//        ProgressDialog progressDialog1 = new ProgressDialog(this);
//        AlertDialog.Builder alertDialog1 = new AlertDialog.Builder(this);
//        alertDialog1.setCancelable(false);
//        alertDialog1.setMessage("Fetching Data.....");
//        alertDialog1.show();


//        progressDialog.setCancelable(false);
//        progressDialog.setMessage("Fetching Data.....");
//        progressDialog.show();


        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
//        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        inTankerSecurityLists = new ArrayList<In_Tanker_Security_list>();
        in_tanker_se_adapter = new In_Tanker_Se_Adapter(Inward_Tanker_Security_Viewdata.this,inTankerSecurityLists);

        recyclerView.setAdapter(in_tanker_se_adapter);

        db = FirebaseFirestore.getInstance();
        db.collection("Inward Tanker Security").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot d:list)
                        {
                            In_Tanker_Security_list obj = d.toObject(In_Tanker_Security_list.class);
                            inTankerSecurityLists.add(obj);
                        }
                        //update Adapter
                        in_tanker_se_adapter.notifyDataSetChanged();
                    }
                });


//
//        dbs = FirebaseFirestore.getInstance();
//
//        dateButton = findViewById(R.id.dateButton);
//        fetchDataButton = findViewById(R.id.fetch);
//
//        dateButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showDatePickerDialog();
//            }
//        });
//        fetchDataButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                fetchFirestoreData();
//            }
//        });
//
//
//   }
//
//   private void showDatePickerDialog()
//   {
//       Calendar calendar = Calendar.getInstance();
//       int year = calendar.get(Calendar.YEAR);
//       int month = calendar.get(Calendar.MONTH);
//       int day = calendar.get(Calendar.DAY_OF_MONTH);
//
//       DatePickerDialog datePickerDialog = new DatePickerDialog(this,
//               new DatePickerDialog.OnDateSetListener(){
//           public void onDateSet(DatePicker view, int selectedYear,int selectedMonth, int selectedDay ){
//
//               Calendar selectedDateCalendar = Calendar.getInstance();
//               selectedDateCalendar.set(selectedYear, selectedMonth, selectedDay);
//               SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
//               String formattedDate = dateFormat.format(selectedDateCalendar.getTime());
//
//               dateButton.setText(formattedDate);
//           }
//               },
//             year,month,day
//
//       );
//
//       datePickerDialog.show();
//   }
//
//   public void fetchFirestoreData(){
//        String selectedDate = dateButton.getText().toString();
//
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
//        try {
//            Date selectedDateObj = dateFormat.parse(selectedDate);
//
//            CollectionReference collectionReference = dbs.collection("Inward Tanker Security");
//            Query query = collectionReference
//                    .whereEqualTo("date",selectedDateObj);
//
//            query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                @Override
//                public void onComplete(@NonNull Task<QuerySnapshot> task) {
//
//                    if (task.isSuccessful()){
//                        for (QueryDocumentSnapshot document : task.getResult()){
//                            String data = document.getData().toString();
//                            Toast.makeText(Inward_Tanker_Security_Viewdata.this, data, Toast.LENGTH_SHORT).show();
//                        }
//                    }else {
//                        Toast.makeText(Inward_Tanker_Security_Viewdata.this, "Error fetching data", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//        } catch (ParseException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//
//        }



//        input_start = findViewById(R.id.inputstart);
//        input_end = findViewById(R.id.inputout);
//        btn_start = findViewById(R.id.btn_start);
//        btn_end = findViewById(R.id.btn_end);
//        subm = findViewById(R.id.sub);
//        rootdb = FirebaseFirestore.getInstance();
//
//        btn_start.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//
//                        calendar.set(year,month,dayOfMonth);
//                        input_start.setText(simpleDateFormat.format(calendar.getTime()));
//                        date_start = calendar.getTime();
//
//                        String input1 = input_start.getText().toString();
//                        String input2 = input_end.getText().toString();
//
//                        if (input1.isEmpty() && input2.isEmpty()){
//
//                        }
//                    }
//                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
//                datePickerDialog.show();
//
//            }
//        });
//        btn_end.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//
//                        calendar.set(year,month,dayOfMonth);
//                        input_end.setText(simpleDateFormat.format(calendar.getTime()));
//                        date_end = calendar.getTime();
//
//                        String input1 = input_end.getText().toString();
//                        String input2 = input_start.getText().toString();
//
//                        if (input1.isEmpty() && input2.isEmpty()){
//
//                        }
//                    }
//                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
//                datePickerDialog.show();
//            }
//        });
//
//        subm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                CollectionReference collectionReference = rootdb.collection("Inward Tanker Security");
//                Query query = collectionReference
//                        .whereGreaterThanOrEqualTo("date",date_start)
//                        .whereLessThanOrEqualTo("date",date_end);
//
//                query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()){
//                            for (QueryDocumentSnapshot document : task.getResult()){
//                                Log.d("FirestoreData",document.getId() + "=>" + document.getData());
//                            }
//                        }
//                        else {
//                            Log.w("FirestoreData","Error getting documents.",task.getException());
//                        }
//                    }
//                });
//            }
//            private Date convertStringToDate(String dateString){
//                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
//                try {
//                    return dateFormat.parse(dateString);
//                }catch (ParseException e){
//                    e.printStackTrace();
//                    return null;
//                }
//            }
//        });
//
//

        Button selectDateButton = findViewById(R.id.selectDateButton);

        selectDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

   }
    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
                        // Handle the selected date
                        String selectedDate = selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDay;
                        fetchDataFromFirestore(selectedDate);
                    }
                },
                year, month, day
        );

        datePickerDialog.show();
    }

    private void fetchDataFromFirestore(String selectedDate) {
        // Convert the selected date string to a Date object if needed
        // Perform a Firestore query to get data for the selected date

        CollectionReference collectionReference = FirebaseFirestore.getInstance().collection("Inward Tanker Security");

        // Example query: Get documents where "dateField" is equal to the selected date
        Query query = collectionReference.whereEqualTo("etdate", selectedDate);

        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        // Handle document data
                        String data = document.getData().toString();
                        Log.d("FirestoreData", data);
                    }
                } else {
                    // Handle errors
                    Log.w("FirestoreData", "Error getting documents.", task.getException());
                }
            }
        });
    }




}
