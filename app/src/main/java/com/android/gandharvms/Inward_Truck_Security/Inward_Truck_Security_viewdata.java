package com.android.gandharvms.Inward_Truck_Security;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.gandharvms.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Inward_Truck_Security_viewdata extends AppCompatActivity {

    RecyclerView recview;
    ArrayList<In_Truck_security_list> datalist;

    FirebaseFirestore db;

    In_Truck_Security_Adapter inTruckSecurityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inward_truck_security_viewdata);

        recview = (RecyclerView) findViewById(R.id.recyclerview);
        recview.setLayoutManager(new LinearLayoutManager(this));
        datalist = new ArrayList<>();
        inTruckSecurityAdapter = new In_Truck_Security_Adapter(datalist);
        recview.setAdapter(inTruckSecurityAdapter);

        db= FirebaseFirestore.getInstance();
        db.collection("Inward Truck Security").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot d:list)
                        {
                            In_Truck_security_list obj = d.toObject(In_Truck_security_list.class);
                            datalist.add(obj);
                        }
                        inTruckSecurityAdapter.notifyDataSetChanged();
                    }
                });
    }
}