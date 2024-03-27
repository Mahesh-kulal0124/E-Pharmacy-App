package com.example.pharmacy;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.pharmacy.R;
import com.example.pharmacy.DBHelper;
import com.example.pharmacy.Models.OrdersModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class Payment extends AppCompatActivity {

    Toolbar toolbar;
    TextView subTotal;
    TextView gst;
    TextView shipping;
    TextView total;
    Button payment;

    private double amount = 0.0;
    private DBHelper databaseHelper;
    private ArrayList<OrdersModel> orderedProducts;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        //toolbar
//        toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });

        // Initialize DatabaseHelper
        databaseHelper = new DBHelper(this);

        Intent intent = getIntent();
        orderedProducts = (ArrayList<OrdersModel>) intent.getSerializableExtra("orderedProducts");
        String sourceActivity = intent.getStringExtra("sourceActivity");

        double totalPrice = intent.getDoubleExtra("price", 0.0);

        amount = totalPrice;

        int gt = (int) (amount * 0.18);
        int ship = (int) (amount * 0.10);
        int sum = (int) (amount + gt + ship);

        subTotal = findViewById(R.id.ProductPrice);
        gst = findViewById(R.id.GST);
        shipping = findViewById(R.id.Shipping);
        total = findViewById(R.id.total);
        payment = findViewById(R.id.payment);

        subTotal.setText("Rs." + amount);
        gst.setText("Rs." + gt);
        shipping.setText("Rs." + ship);
        total.setText("Rs." + sum);

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                // Iterate through the orderedProducts list and store each order in SQLite
//                for (OrdersModel order : orderedProducts) {
//                    double productName = order.getSoldItemName();
//                    int productImage = order.getOrderImage();
//                    String productPrice = order.getPrice();
//                    String description = order.getOrderNumber();
//
//                    // Store the order in SQLite
//                    boolean isInserted = databaseHelper.insertmyorders(productName, productImage, productPrice,description);
//                    if (isInserted) {
//                        // Order inserted successfully
//                        Toast.makeText(Payment.this, "Order placed successfully!", Toast.LENGTH_SHORT).show();
//                    } else {
//                        // Failed to insert order
//                        Toast.makeText(Payment.this, "Failed to place the order!", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                Intent intent = new Intent(Payment.this, pharmacy.class);
//                intent.putExtra("amount", amount);
//                startActivity(intent);

                Toast.makeText(Payment.this, "Order placed successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Payment.this, pharmacy.class);
//
//               startActivity(intent);
            }
        });
    }
}