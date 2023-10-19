package com.android.gandharvms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.LinearLayout;




public class Inward_Tanker extends AppCompatActivity {

//    LinearLayout linearLayout,linearLayout2;
//    CardView one,two,three,four,five,six,wone;

//    CardView cardView;
//    CardView cardView2;
//
//    CardView cardview3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inward_tanker);


//      cardView = findViewById(R.id.sequirity);
//      cardView2 = findViewById(R.id.Weighmentid);
//      cardview3 = findViewById(R.id.sampling);

//      cardView.setOnClickListener(new View.OnClickListener() {
//          @Override
//          public void onClick(View view) {
//
//              Fragment fragment = new InTA_Se();
//
//              FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//              fragmentTransaction.replace(R.id.sequirity,fragment).commit();
//          }
//      });
//      cardView2.setOnClickListener(new View.OnClickListener() {
//          @Override
//          public void onClick(View view) {
//
//              Fragment fragment = new InTA_two();
//
//              FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//              fragmentTransaction.replace(R.id.Weighmentid,fragment).commit();
//          }
//      });

//      cardview3.setOnClickListener(new View.OnClickListener() {
//          @Override
//          public void onClick(View view) {
//
//              Fragment fragment = new InTA_Three();
//
//              FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//              fragmentTransaction.replace(R.id.sampling,fragment).commit();
//          }
//      });

//        one = findViewById(R.id.one);
//        two = findViewById(R.id.two);
//        three =findViewById(R.id.three);
//        four = findViewById(R.id.four);
//        five = findViewById(R.id.five);
//        six = findViewById(R.id.six);
//        linearLayout=findViewById(R.id.seqlayout);
//
//        linearLayout2=findViewById(R.id.weiglayout);
//        wone=findViewById(R.id.wone);





    }
    public void sequirityinwardT(View view){
        Intent intent= new Intent(this,Inward_Tanker_Security.class);
        startActivity(intent);
    }

    public void Weighmentclick(View view){
        Intent intent = new Intent(this,Inward_Tanker_Weighment.class);
        startActivity(intent);
    }
    public void samplicgclick(View view){
        Intent intent = new Intent(this, Inward_Tanker_Sampling.class);
        startActivity(intent);
    }

    public void productionclick (View view){
        Intent intent = new Intent(this,Inward_Tanker_Production.class);
        startActivity(intent);
    }
    public void Laboratoryclick (View view){
        Intent intent = new Intent(this, Inward_Tanker_Laboratory.class);
        startActivity(intent);
    }




//    public void expandable(View view) {
//        int v = (one.getVisibility()== View.GONE)? view.VISIBLE: view.GONE;
//        int a = (two.getVisibility()== View.GONE)? view.VISIBLE: view.GONE;
//        int b = (three.getVisibility()== View.GONE)? view.VISIBLE: view.GONE;
//        int c = (four.getVisibility()== View.GONE)? view.VISIBLE: view.GONE;
//        int d = (five.getVisibility()== View.GONE)? view.VISIBLE: view.GONE;
//        int e = (five.getVisibility()== View.GONE)? view.VISIBLE: view.GONE;
//
//
//        int f = (wone.getVisibility()== View.GONE)? view.VISIBLE: view.GONE;
//
//        TransitionManager.beginDelayedTransition(linearLayout,new AutoTransition());
//        one.setVisibility(v);
//        two.setVisibility(a);
//        three.setVisibility(b);
//        four.setVisibility(c);
//        five.setVisibility(d);
//        six.setVisibility(e);
//
//        wone.setVisibility(f);
//    }
//    two.getVisibility();three.getVisibility();four.getVisibility();five.getVisibility() == view.gone)
}