package com.example.pharmacy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;

import com.example.pharmacy.Adapters.MainAdapter;
import com.example.pharmacy.Models.MainModel;
import com.example.pharmacy.databinding.ActivityMainBinding;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class Shop extends AppCompatActivity implements View.OnClickListener {
    ActivityMainBinding binding;
    MaterialCardView cardShop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_shop);
        cardShop = findViewById(R.id.card_shop);
        cardShop.setOnClickListener(this);
    }

        @Override
        public void onClick (View view){
            switch (view.getId()) {
                case R.id.card_shop:
                    shopDetail();
                    break;
            }

        }
        public void shopDetail () {
            Intent shopDetail = new Intent(getApplicationContext(), ShopDetail.class);
            startActivity(shopDetail);

    }
}





