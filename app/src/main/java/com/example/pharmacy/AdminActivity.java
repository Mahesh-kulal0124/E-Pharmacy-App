package com.example.pharmacy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class AdminActivity extends AppCompatActivity {

    EditText MedicineName,Price,Quantity;
    Button insert,delete,update,view,addimage;

    ImageView image;

    int SELECT_PICTURE =200;

    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        MedicineName = findViewById(R.id.name);
        Price = findViewById(R.id.Price);
        Quantity = findViewById(R.id.quantity);
        image = findViewById(R.id.Image);
        addimage = findViewById(R.id.addImage);

        insert = findViewById(R.id.btnInsert);
        delete = findViewById(R.id.btnDelete);
        update = findViewById(R.id.btnUpdate);
        view = findViewById(R.id.btnView);

        DB = new DBHelper(this);
        image.setImageResource(R.drawable.medicine1);
        addimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageSelect();
            }
        });

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Nametext = MedicineName.getText().toString();
                Double price = Double.valueOf(Price.getText().toString());
                String quantity = Quantity.getText().toString();

                Boolean checkinsertdata = DB.insertmedicinedata(Nametext,price,quantity);
                if(checkinsertdata==true)
                    Toast.makeText(AdminActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(AdminActivity.this, "New Entry not Inserted", Toast.LENGTH_SHORT).show();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Nametext = MedicineName.getText().toString();
                Double price = Double.valueOf(Price.getText().toString());
                String quantity = Quantity.getText().toString();

                Boolean checkupdatedata = DB.updatemedicinedata(Nametext,price,quantity);
                if(checkupdatedata==true)
                    Toast.makeText(AdminActivity.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(AdminActivity.this, "Entry not Updated", Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Nametext = MedicineName.getText().toString();
                Boolean checkdeletedata = DB.deletemedicinedata(Nametext);
                if(checkdeletedata==true)
                    Toast.makeText(AdminActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(AdminActivity.this, "Entry not Deleted", Toast.LENGTH_SHORT).show();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(AdminActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext())
                {
                    buffer.append("\n"+"Medicine Name :"+res.getString(0)+"\n");
                    buffer.append("Price :"+res.getString(1)+"\n");
                    buffer.append("Quantity :"+res.getString(2)+"\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(AdminActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Medicine Data");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

    }
    void imageSelect()
    {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(i,"ADD IMAGE"),SELECT_PICTURE);

    }
    public  void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);

        if(resultCode == RESULT_OK)
        {
            if(requestCode==SELECT_PICTURE)
            {
                Uri selectedImageUri = data.getData();
                if(null != selectedImageUri)
                {

                    image.setImageURI(selectedImageUri);
                }
            }
        }
    }

}
