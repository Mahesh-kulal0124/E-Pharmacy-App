package com.example.pharmacy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import com.example.pharmacy.databinding.ActivityPharmacyBinding;

import java.io.ByteArrayOutputStream;

public class pharmacy extends AppCompatActivity {
    LinearLayout linearLayoutShop,linearLayoutProduct;
    ImageView imageItemShop;
    TextView tvItemNameShop;

    ActivityPharmacyBinding binding;

    int[] imagesShop={R.drawable.farmaco,R.drawable.farmaco2,R.drawable.docter,R.drawable.jeringuilla,R.drawable.medicina3,R.drawable.medicina_2,R.drawable.medicina_21};
     String[] namesItemsShop={"Medicines","Health Care","Doctors","Lab Tests","Health","Omega","Vitamins"};
    //Product
    int[] imagesProduct={R.drawable.medicina3,R.drawable.medicina_4 ,R.drawable.pastillas,R.drawable.pastillas_2};
    String[] namesItemProduct={"Multi Vitamins","Thayrosafe","Omega 3","Sore capsule"};
    String[] pillsItemsProduct={"90 pills","180 pills","132 pills","60 capsules"};
    String[] finalPriceProduct ={"$217","$342","$165","$135"};
    Double[] priceProduct={287.0,392.0,185.0,165.0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityPharmacyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        linearLayoutShop =findViewById(R.id.linear_layout_shop);
        linearLayoutProduct=findViewById(R.id.linear_layout_products);

        loadShop();
        loadProduct();
    }
    public void loadProduct(){
        LayoutInflater inflator = LayoutInflater.from(this);
        int i;
        for( i=0;i<imagesProduct.length;i++){
            View view=inflator.inflate(R.layout.item_product,linearLayoutProduct,false);
            ImageView imageProduct =view.findViewById(R.id.image_product);
            TextView tvName=view.findViewById(R.id.tv_name_product);
            TextView tvPills=view.findViewById(R.id.tv_pills_product);
            TextView tvFinalPrice=view.findViewById(R.id.tv_final_price_product);
            TextView tvPrice=view.findViewById(R.id.tv_price_product);
            imageProduct.setImageResource(imagesProduct[i]);
            tvName.setText(namesItemProduct[i]);
            tvPills.setText(pillsItemsProduct[i]);
            tvFinalPrice.setText(finalPriceProduct[i]);
            tvPrice.setText(priceProduct[i]+"");
            final int aux=i;

            imageProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ImageView imageView = findViewById(R.id.image_product);
//                    int image = (int) imageView.getTag();
                    imageView.setDrawingCacheEnabled(true);
                    imageView.buildDrawingCache();
                    Bitmap bitmap = imageView.getDrawingCache();
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();


                    String name = tvName.getText().toString();
                    Double price = Double.valueOf(tvPrice.getText().toString());
                    String quantity = tvPills.getText().toString();
                    String itemSeleccionado = namesItemProduct[aux];
                    Toast.makeText(getApplicationContext(),"Item "+itemSeleccionado,Toast.LENGTH_SHORT).show();
                    Intent shop = new Intent(getApplicationContext(),ShopDetail.class);
                    shop.putExtra("image",byteArray);
                    shop.putExtra("name",name);
                    shop.putExtra("price", price);
                    shop.putExtra("desc",quantity);
                    shop.putExtra("type",1);

                    startActivity(shop);

                }
            });

            linearLayoutProduct.addView(view);
        }
    }

    public void loadShop()
    {
        LayoutInflater inflater=LayoutInflater.from(this);
        int i;
        for(i=0;i<imagesShop.length;i++){
            View view=inflater.inflate(R.layout.item_shop,linearLayoutShop,false);
            imageItemShop=view.findViewById(R.id.image_item_shop);
            tvItemNameShop=view.findViewById(R.id.tv_item_name_shop);
            imageItemShop.setImageResource(imagesShop[i]);
            tvItemNameShop.setText(namesItemsShop[i]);
            final int aux=i;

            imageItemShop.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view)
                {
                    String itemSeleccionado =namesItemsShop[aux];
                    Toast.makeText(getApplicationContext(),"Item "+itemSeleccionado,Toast.LENGTH_SHORT).show();
                    Intent shop = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(shop);
                }
            });

            linearLayoutShop.addView(view);
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId=item.getItemId();
        if(itemId==R.id.myorders)
        {
            startActivity(new Intent(pharmacy.this,MyOrderActivity.class));
        }


        return super.onOptionsItemSelected(item);
    }
}