package com.example.pharmacy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import com.example.pharmacy.R;

public class Activitymain extends AppCompatActivity {

    Button signup,login,admin;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitymain);

        signup =findViewById(R.id.signup);
        login =findViewById(R.id.login);
        admin =findViewById(R.id.Admin);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                opensignup();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openlogin();
            }
        });
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openadmin();
            }
        });
    }
    public void opensignup(){
        Intent intent=new Intent(this,SignupActivity.class);
        startActivity(intent);
    }
    public void openlogin(){
        Intent intent =new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
    public void openadmin(){
        Intent intent =new Intent(this,AdminLoginActivity.class);
        startActivity(intent);
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int itemId=item.getItemId();
//        if(itemId==R.id.login)
//        {
//            startActivity(new Intent(Activitymain.this,AdminLoginActivity.class));
//        }
//
//
//        return super.onOptionsItemSelected(item);
//    }
}
