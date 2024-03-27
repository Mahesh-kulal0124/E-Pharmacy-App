package com.example.pharmacy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.pharmacy.Adapters.MainAdapter;
import com.example.pharmacy.Models.MainModel;
import com.example.pharmacy.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    ImageButton cartBtn,back;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        cartBtn = findViewById(R.id.button);
        back = findViewById(R.id.backArrow);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,pharmacy.class);
                startActivity(intent);
            }
        });
        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,OrdersActivity.class);
                startActivity(intent);
            }
        });

        ArrayList<MainModel>list=new ArrayList<>();
        list.add(new MainModel(R.drawable.medicine1,"Medicine1",10.0,"20"));
        list.add(new MainModel(R.drawable.medicine4,"Medicine4",36.0,"20"));
        list.add(new MainModel(R.drawable.medicine5,"Medicine5",18.0,"10"));
        list.add(new MainModel(R.drawable.allergen,"allergen",65.0,"10"));
        list.add(new MainModel(R.drawable.blisterpack,"blisterpack",65.0,"20"));
        list.add(new MainModel(R.drawable.chemical,"chemical",65.0,"30"));
        list.add(new MainModel(R.drawable.drug,"drug",65.0,"20"));
        list.add(new MainModel(R.drawable.firstaidbox,"firstaidbox",65.0,"10"));
        list.add(new MainModel(R.drawable.hygieneproducts,"huygieneproducts",65.0,"30"));
        list.add(new MainModel(R.drawable.organicproduct,"organicproduct",65.0,"10"));
        list.add(new MainModel(R.drawable.package1,"package",65.0,"04"));
        list.add(new MainModel(R.drawable.pills,"pills",65.0,"50"));
        list.add(new MainModel(R.drawable.shampoo,"shampoo",65.0,"100"));
        list.add(new MainModel(R.drawable.soap,"soap",65.0,"40"));
        list.add(new MainModel(R.drawable.syrup,"syrup",65.0,"10"));
        list.add(new MainModel(R.drawable.tablet,"tablet",65.0,"10"));
        list.add(new MainModel(R.drawable.tablets,"tablet",65.0,"30"));
        list.add(new MainModel(R.drawable.wheyprotein,"wheyprotein",65.0,"10"));


        MainAdapter adapter=new MainAdapter(list,this);
        binding.recylerview.setAdapter(adapter);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        binding.recylerview.setLayoutManager(layoutManager);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId=item.getItemId();
        if(itemId==R.id.myorders)
        {
            startActivity(new Intent(MainActivity.this,MyOrderActivity.class));
        }


        return super.onOptionsItemSelected(item);
    }
}