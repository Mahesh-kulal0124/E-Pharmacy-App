package com.example.pharmacy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.pharmacy.Adapters.LocationAdapter;
import com.example.pharmacy.Models.LocationModel;
import com.example.pharmacy.databinding.ActivityLocationBinding;

import java.util.ArrayList;

public class LocationActivity extends AppCompatActivity {

    ActivityLocationBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLocationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<LocationModel>list=new ArrayList<>();
        list.add(new LocationModel(R.drawable.shop,"D Mart","Attavar","6364757171"));
        list.add(new LocationModel(R.drawable.shop,"Ragavendra Stores","Adyar","6547384637"));
        list.add(new LocationModel(R.drawable.shop,"C Mart","Adyar","6364757171"));
        list.add(new LocationModel(R.drawable.shop,"Annapurna Stores","Kannur","6364757171"));
        list.add(new LocationModel(R.drawable.shop,"Seema Departmental Stores","BC road","6364757171"));
        list.add(new LocationModel(R.drawable.shop,"Dayand","pumpwell","6364757171"));



        LocationAdapter adapter=new LocationAdapter(list,this);
        binding.recylerview.setAdapter(adapter);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        binding.recylerview.setLayoutManager(layoutManager);




    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId=item.getItemId();
        if(itemId==R.id.myorders)
        {
            startActivity(new Intent(LocationActivity.this,MainActivity.class));
        }


        return super.onOptionsItemSelected(item);
    }
}