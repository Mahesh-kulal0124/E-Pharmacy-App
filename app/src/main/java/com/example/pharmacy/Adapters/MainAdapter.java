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

import com.example.pharmacy.DetailActivity;
import com.example.pharmacy.Models.MainModel;
import com.example.pharmacy.R;
import com.example.pharmacy.ShopDetail;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.viewholder> {

    ArrayList<MainModel>list;
    Context context;

    public MainAdapter(ArrayList<MainModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.sample_maingrocery,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        final MainModel model=list.get(position);
        holder.groceryImage.setImageResource(model.getImage());
        holder.mainName.setText(model.getName());
        holder.price.setText(model.getPrice()+"");
        holder.description.setText(model.getDescription());

       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(context, ShopDetail.class);
               intent.putExtra("image",model.getImage());
               intent.putExtra("price",model.getPrice());
               intent.putExtra("desc",model.getDescription());
               intent.putExtra("name",model.getName());
               intent.putExtra("type",2);
               context.startActivity(intent);
           }
       });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {

        ImageView groceryImage;
        TextView mainName,price,description;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            groceryImage=itemView.findViewById(R.id.image);
            mainName=itemView.findViewById(R.id.name);
            price=itemView.findViewById(R.id.Price);
            description=itemView.findViewById(R.id.quantity);

        }
    }
}
