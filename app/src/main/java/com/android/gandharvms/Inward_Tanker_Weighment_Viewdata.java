package com.android.gandharvms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Inward_Tanker_Weighment_Viewdata extends AppCompatActivity {

   private ImageView calendarImageView;
    private Button fetchDataButton;

    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inward_tanker_weighment_viewdata);

//        db= FirebaseFirestore.getInstance();
//
//        calendarImageView  = findViewById(R.id.calendarImageView);
//        fetchDataButton = findViewById(R.id.fetchDataButton);
//
//        calendarImageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showDatePickerDialog();
//            }
//        });
//
//        fetchDataButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                fetchFirestoreData();
//            }
//        });
//    }
//
//    private void showDatePickerDialog() {
//        // Get the current date for initialization
//        Calendar calendar = Calendar.getInstance();
//        int year = calendar.get(Calendar.YEAR);
//        int month = calendar.get(Calendar.MONTH);
//        int day = calendar.get(Calendar.DAY_OF_MONTH);
//
//        // Create a DatePickerDialog
//        DatePickerDialog datePickerDialog = new DatePickerDialog(
//                this,
//                new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
//                        // Format the selected date for display
//                        Calendar selectedDateCalendar = Calendar.getInstance();
//                        selectedDateCalendar.set(selectedYear, selectedMonth, selectedDay);
//                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
//                        String formattedDate = dateFormat.format(selectedDateCalendar.getTime());
//
//                        // Update the button text with the selected date
//
//                        fetchDataFromFirestore(formattedDate);
//                    }
//                },
//                year, month, day
//        );
//
//        // Show the DatePickerDialog
//        datePickerDialog.show();
//    }
//
//    private void fetchFirestoreData(String selectedDate) {
//        // Get the selected date from the button text
////        String selectedDate = dateButton.getText().toString();
//
//        // Convert the selected date string to a Date object
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
//        try {
//            Date selectedDateObj = dateFormat.parse(selectedDate);
//
//            // Perform Firestore query based on the selected date
//            CollectionReference collectionReference = db.collection("Inward Tanker Weighment");
//            Query query = collectionReference
//                    .whereEqualTo("Date", selectedDateObj);
//
//            query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                @Override
//                public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                    if (task.isSuccessful()) {
//                        for (QueryDocumentSnapshot document : task.getResult()) {
//                            // Handle document data
//                            String data = document.getData().toString();
//                            Toast.makeText(Inward_Tanker_Weighment_Viewdata.this, data, Toast.LENGTH_SHORT).show();
//                        }
//                    } else {
//                        // Handle errors
//                        Toast.makeText(Inward_Tanker_Weighment_Viewdata.this, "Error fetching data", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//        } catch (ParseException e) {
//            e.printStackTrace();
//            // Handle parsing error if needed
//        }
    }
}