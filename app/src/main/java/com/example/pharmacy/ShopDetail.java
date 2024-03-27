package com.example.pharmacy;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pharmacy.Models.MainModel;
import com.example.pharmacy.databinding.ActivityShopDetailBinding;

import java.io.ByteArrayOutputStream;

public class ShopDetail extends AppCompatActivity {
    ActivityShopDetailBinding binding;
    ImageButton cartBtn,back;
    Button Order_btn;
    ImageView imageView;
    TextView pricetext, nametext, desctext;

    @SuppressLint({"DefaultLocale", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShopDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        cartBtn = findViewById(R.id.button);
        back= findViewById(R.id.backArrow);
        Order_btn = findViewById(R.id.Order_btn);
        imageView = findViewById(R.id.image);
        pricetext = findViewById(R.id.price);
        desctext = findViewById(R.id.quantity);
        nametext = findViewById(R.id.name);

        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShopDetail.this, OrdersActivity.class);
                startActivity(intent);
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShopDetail.this, MainActivity.class);
                startActivity(intent);
            }
        });


        Order_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ImageView imageView = findViewById(R.id.image);
//                int image = (int)imageView.getTag();
                imageView.setDrawingCacheEnabled(true);
                imageView.buildDrawingCache();
                Bitmap bitmap = imageView.getDrawingCache();

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();

                Double price = Double.valueOf(pricetext.getText().toString());
                String desc = desctext.getText().toString();
                String name = nametext.getText().toString();
                Intent intent = new Intent(ShopDetail.this, DetailActivity.class);
                intent.putExtra("image", byteArray);
                intent.putExtra("price", price);
                intent.putExtra("desc", desc);
                intent.putExtra("name", name);
                intent.putExtra("type",1);
                startActivity(intent);
            }
        });


        final DBHelper helper = new DBHelper(this);
        if (getIntent().getIntExtra("type", 0) == 1) {

            byte[] byteArray = getIntent().getByteArrayExtra("image");
            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
//                int image = getIntent().getIntExtra("image",0);
                final double price = getIntent().getDoubleExtra("price", 0.0);
                final String name = getIntent().getStringExtra("name");
                final String description = getIntent().getStringExtra("desc");

                int image = byteArrayToInt(byteArray);

                binding.image.setImageBitmap(bitmap);
                binding.price.setText(String.format("%.1f", price));
                binding.name.setText(name);
                binding.quantity.setText(description);



                binding.cartBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isInserted = helper.insertOrder(
                                price,
                                image,
                                name,
                                description

                        );
                        if (isInserted) {
                            Toast.makeText(ShopDetail.this, "Added to Cart Successfully", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(getApplicationContext(), OrdersActivity.class);
                            startActivity(i);
                        } else
                            Toast.makeText(ShopDetail.this, "Error", Toast.LENGTH_LONG).show();
                    }
                });
            }
        else if(getIntent().getIntExtra("type", 0) == 2)
        {
            int image = getIntent().getIntExtra("image",0);
            final double price = getIntent().getDoubleExtra("price", 0.0);
            final String name = getIntent().getStringExtra("name");
            final String description = getIntent().getStringExtra("desc");



            binding.image.setImageResource(image);
            binding.price.setText(String.format("%.1f", price));
            binding.name.setText(name);
            binding.quantity.setText(description);

            binding.cartBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean isInserted = helper.insertOrder(
                            price,
                            image,
                            name,
                            description

                    );
                    if (isInserted) {
                        Toast.makeText(ShopDetail.this, "Added to Cart Successfully", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getApplicationContext(), OrdersActivity.class);
                        startActivity(i);
                    } else
                        Toast.makeText(ShopDetail.this, "Error", Toast.LENGTH_LONG).show();
                }
            });


        }
        }
    public String BitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }
    private int byteArrayToInt(byte[] byteArray) {
        int value = 0;
        int result=0;
        for (int i = 0; i < byteArray.length; i++) {
            value = (value << 8) | (byteArray[i] & 0xFF);
            result=Math.abs(value);
        }
        return result;
    }
    public int bitmapToInt(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] pixels = new int[width * height];

        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);

        int result = 0;

        for (int i = 0; i < pixels.length; i++) {
            result = (result << 8) | (pixels[i] & 0xFF);
        }

        return result;
    }

    }

