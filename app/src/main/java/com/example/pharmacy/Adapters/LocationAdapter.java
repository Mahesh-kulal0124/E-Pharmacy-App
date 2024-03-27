package com.example.pharmacy.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pharmacy.MainActivity;
import com.example.pharmacy.Models.LocationModel;
import com.example.pharmacy.R;

import java.util.ArrayList;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.viewholder> {
    ArrayList<LocationModel> list;
    Context context;

    public LocationAdapter(ArrayList<LocationModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.sample_location,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        final LocationModel model=list.get(position);
        holder.shopimage.setImageResource(model.getImage());
        holder.shopname.setText(model.getShopname());
        holder.address.setText(model.getAddress());
        holder.phonenum.setText(model.getPhonenum());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView shopimage;
        TextView shopname,address,phonenum;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            shopimage=itemView.findViewById(R.id.shopPic);
            shopname=itemView.findViewById(R.id.shopname);
            address=itemView.findViewById(R.id.address);
            phonenum=itemView.findViewById(R.id.phonenum);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int position=getAdapterPosition();
            Intent intent=new Intent(context, MainActivity.class);
            context.startActivity(intent);

        }
    }

}
