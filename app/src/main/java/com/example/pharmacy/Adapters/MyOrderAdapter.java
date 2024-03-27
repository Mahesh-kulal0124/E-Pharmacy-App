package com.example.pharmacy.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pharmacy.DBHelper;
import com.example.pharmacy.DetailActivity;
import com.example.pharmacy.Models.MyOrderModel;
import com.example.pharmacy.Models.OrdersModel;
import com.example.pharmacy.MyOrderActivity;
import com.example.pharmacy.R;

import java.util.ArrayList;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.viewHolder> {
    ArrayList<MyOrderModel> list;
    Context context;

    public MyOrderAdapter(ArrayList<MyOrderModel> list, Context context) {
        this.list = list;
        this.context = context;
    }



    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_sample, parent, false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        final MyOrderModel model = list.get(position);
        holder.orderImage.setImageResource(model.getOrderImage());
        holder.soldItemName.setText(model.getSoldItemName());
        holder.orderNumber.setText(model.getOrderNumber());
        holder.price.setText((String.format("%.1f",model.getPrice())));
        holder.orderDelete.setImageResource(model.getOrderDelete());


//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, DetailActivity.class);
//                intent.putExtra("id", Integer.parseInt(model.getOrderNumber()));
//                intent.putExtra("price",model.getPrice());
//                intent.putExtra("image", model.getOrderImage());
//                intent.putExtra("name", model.getSoldItemName());
//                intent.putExtra("type",2);
//
//                context.startActivity(intent);
//            }
//        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                DBHelper helper=new DBHelper(context);
                if(helper.deletedOrder(model.getOrderNumber())>0){
                    Toast.makeText(context, "Deleted", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(context, "Error", Toast.LENGTH_LONG).show();
                }
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        ImageView orderImage,orderDelete;
        TextView soldItemName, orderNumber, price;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            orderImage = itemView.findViewById(R.id.orderImage);
            orderDelete=itemView.findViewById(R.id.deleteic);
            soldItemName = itemView.findViewById(R.id.orderItemName);
            orderNumber = itemView.findViewById(R.id.orderNumber);
            price = itemView.findViewById(R.id.orderPriceO);
        }
    }
}

