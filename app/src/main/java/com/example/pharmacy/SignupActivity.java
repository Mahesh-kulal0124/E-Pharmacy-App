package com.example.pharmacy;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {
    EditText username,password,repassword;
    Button signup;
    DBHelper DB;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        repassword=findViewById(R.id.repassword);
        signup=findViewById(R.id.signup);
        DB=new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=username.getText().toString();
                String pass=password.getText().toString();
                String repass=repassword.getText().toString();

                if(TextUtils.isEmpty(user)||TextUtils.isEmpty(pass)||TextUtils.isEmpty(repass))
                    Toast.makeText(SignupActivity.this, "All fields are Required", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser=DB.checkusername(user);
                        if(checkuser==false){
                            Boolean insert=DB.insertData(user,pass);
                            if(insert==true){
                                Toast.makeText(SignupActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(getApplicationContext(),Activitymain.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(SignupActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(SignupActivity.this, "User already Exists", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(SignupActivity.this, "Password are not matching", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}