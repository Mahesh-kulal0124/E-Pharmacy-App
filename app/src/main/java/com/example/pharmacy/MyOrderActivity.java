package com.example.pharmacy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.pharmacy.Adapters.MyOrderAdapter;
import com.example.pharmacy.Adapters.OrdersAdapter;
import com.example.pharmacy.Models.MyOrderModel;
import com.example.pharmacy.Models.OrdersModel;
import com.example.pharmacy.databinding.ActivityOrdersBinding;

import java.util.ArrayList;

public class MyOrderActivity extends AppCompatActivity {

    ActivityOrdersBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityOrdersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


//        list.add(new OrdersModel(R.drawable.eggs,"Eggs","4","123456789"));
//        list.add(new OrdersModel(R.drawable.eggs,"Eggs","4","123456789"));
//        list.add(new OrdersModel(R.drawable.eggs,"Eggs","4","123456789"));
//        list.add(new OrdersModel(R.drawable.eggs,"Eggs","4","123456789"));
//        list.add(new OrdersModel(R.drawable.eggs,"Eggs","4","123456789"));
//        list.add(new OrdersModel(R.drawable.eggs,"Eggs","4","123456789"));\
        DBHelper helper=new DBHelper(this);
        ArrayList<MyOrderModel>list=helper.getmyorders();


        MyOrderAdapter adapter=new MyOrderAdapter(list,this);
        binding.orderRecyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        binding.orderRecyclerView.setLayoutManager(layoutManager);
    }
}