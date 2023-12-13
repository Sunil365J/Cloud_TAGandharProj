package com.android.gandharvms.Inward_Truck_Security;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.gandharvms.R;

import java.util.ArrayList;

public class In_Truck_Security_Adapter extends RecyclerView.Adapter<In_Truck_Security_Adapter.myviewholder> {

    ArrayList<In_Truck_security_list> datalist;

    public In_Truck_Security_Adapter(ArrayList<In_Truck_security_list> datalist) {
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.in_truck_secuirty_item,parent,false);
        return new myviewholder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {


        holder.etintime.setText(datalist.get(position).getIntime());
        holder.etserialnumber.setText(datalist.get(position).getSerialnumber());
        holder.etvehicalnumber.setText(datalist.get(position).getVehicalNumber());
        holder.etsinvocieno.setText(datalist.get(position).getInvoicenumber());
        holder.etsdate.setText(datalist.get(position).getDate());
        holder.etssupplier.setText(datalist.get(position).getSupplier());
        holder.etsmaterial.setText(datalist.get(position).getMaterial());
        holder.etsqty.setText(datalist.get(position).getQty());
        holder.etsuom.setText(datalist.get(position).getUOM());
        holder.etsnetwt.setText(datalist.get(position).getEtsnetweight());
        holder.etsuom2.setText(datalist.get(position).getUOM2());

    }


    @Override
    public int getItemCount() {
        return datalist.size();
    }

    class myviewholder extends RecyclerView.ViewHolder {
        TextView etintime,etserialnumber,etvehicalnumber,etsinvocieno,etsdate,etssupplier,etsmaterial,etsqty,etsuom,etsnetwt,etsuom2;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            etintime=itemView.findViewById(R.id.listintime);
            etserialnumber= itemView.findViewById(R.id.listserialnumber);
            etvehicalnumber= itemView.findViewById(R.id.listvehiclenumber);
            etsinvocieno= itemView.findViewById(R.id.listinvoiceno);
            etsdate=itemView.findViewById(R.id.listdate);
            etssupplier= itemView.findViewById(R.id.listsupplir);
            etsmaterial= itemView.findViewById(R.id.listmaterial);
            etsqty= itemView.findViewById(R.id.listqty);
            etsuom= itemView.findViewById(R.id.listuom);
            etsnetwt= itemView.findViewById(R.id.listnetweight);
            etsuom2= itemView.findViewById(R.id.listuom2);






        }
    }
}
