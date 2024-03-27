package com.example.pharmacy;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pharmacy.databinding.ActivityDetailBinding;

import java.io.ByteArrayOutputStream;

public class DetailActivity extends AppCompatActivity {

    ActivityDetailBinding binding;
    Button payment;

    EditText username,phone,address,landmark;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        payment = findViewById(R.id.payment);
        username=findViewById(R.id.nameBox);
        phone=findViewById(R.id.phoneBox);
        address=findViewById(R.id.addressBox);
        landmark=findViewById(R.id.AgeBox);

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(DetailActivity.this,Payment.class);
                startActivity(intent);
            }
        });




        final DBHelper helper = new DBHelper(this);
        if (getIntent().getIntExtra("type", 0) == 1) {
            byte[] byteArray = getIntent().getByteArrayExtra("image");
            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

            ImageView imageView = findViewById(R.id.detailImage);

            int image = byteArrayToInt(byteArray);



//            int image = getIntent().getIntExtra("image",0);
            final double price = getIntent().getDoubleExtra("price", 0.0);
            final String name = getIntent().getStringExtra("name");
            final String description = getIntent().getStringExtra("desc");

            binding.detailImage.setImageBitmap(bitmap);
            binding.priceLbl.setText(String.format("%.1f", price));
            binding.groName.setText(name);
            binding.detailDescription.setText(description);

//            String image = BitMapToString(bitmap);
            binding.payment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean isInserted = helper.insertmyorders(
                            price,
                            image,
                            name,
                            description

                    );
                    if (isInserted) {
                        Toast.makeText(DetailActivity.this, "Added details Successfully", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), Payment.class);
                        intent.putExtra("price", price);
                        startActivity(intent);
                    } else
                        Toast.makeText(DetailActivity.this, "Error", Toast.LENGTH_LONG).show();
                }
            });


        }
        else if(getIntent().getIntExtra("type", 0) == 2) {
            int image = getIntent().getIntExtra("image",0);
            final double price = getIntent().getDoubleExtra("price", 0.0);
            final String name = getIntent().getStringExtra("name");
            final String description = getIntent().getStringExtra("desc");

            binding.priceLbl.setText(String.format("%.1f", price));
            binding.groName.setText(name);
            binding.detailDescription.setText(description);

            binding.payment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String user=username.getText().toString();
                    String phon=phone.getText().toString();
                    String addres=address.getText().toString();
                    String land=landmark.getText().toString();

                    if (TextUtils.isEmpty(user) || TextUtils.isEmpty(phon) || TextUtils.isEmpty(addres) || TextUtils.isEmpty(land)) {
                        Toast.makeText(DetailActivity.this, "All fields are Required", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        boolean isInserted = helper.insertmyorders(
                                price,
                                image,
                                name,
                                description

                        );
                        if (isInserted) {
                            Toast.makeText(DetailActivity.this, "Added details Successfully", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), Payment.class);
                            intent.putExtra("price", price);
                            startActivity(intent);
                        } else {
                            Toast.makeText(DetailActivity.this, "Error", Toast.LENGTH_LONG).show();
                        }
                    }
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
        for (int i = 0; i < byteArray.length; i++) {
            value = (value << 8) | (byteArray[i] & 0xFF);
        }
        return value;
    }
}