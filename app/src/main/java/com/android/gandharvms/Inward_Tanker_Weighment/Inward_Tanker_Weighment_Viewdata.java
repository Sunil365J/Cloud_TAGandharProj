package com.android.gandharvms.Inward_Tanker_Weighment;

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

public class Inward_Tanker_Weighment_Viewdata extends AppCompatActivity {

   RecyclerView recview;
   ArrayList<In_Tanker_Weighment_list> datalist;
   FirebaseFirestore db;
   In_Tanker_we_Adapter in_tanker_we_adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inward_tanker_weighment_viewdata);

        recview = (RecyclerView) findViewById(R.id.recyclerview);
        recview.setLayoutManager(new LinearLayoutManager(this));
        datalist= new ArrayList<>();
        in_tanker_we_adapter = new In_Tanker_we_Adapter(datalist);
        recview.setAdapter(in_tanker_we_adapter);

        db= FirebaseFirestore.getInstance();
        db.collection("Inward Tanker Weighment").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                      List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                      for (DocumentSnapshot d:list)
                      {
                          In_Tanker_Weighment_list obj = d.toObject(In_Tanker_Weighment_list.class);
                          datalist.add(obj);
                      }
                      in_tanker_we_adapter.notifyDataSetChanged();
                    }
                });


    }
}