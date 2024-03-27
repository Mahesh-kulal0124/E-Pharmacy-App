package com.example.pharmacy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.pharmacy.Models.MyOrderModel;
import com.example.pharmacy.Models.OrdersModel;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    final static String DBNAME = "mydatabase.db";
    final static int DBVERSION = 7;

    public DBHelper(@Nullable Context context) {
        super(context, DBNAME, null, DBVERSION);
    }


    public boolean insertData(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("username", username);
        values.put("password", password);

        long result = db.insert("users", null, values);
        if (result == -1) return false;
        else
            return true;
    }

    public boolean checkusername(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username=?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public boolean checkusernamepassword(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username=? and password=?", new String[]{username, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table users(username TEXT primary key,password TEXT)");

        sqLiteDatabase.execSQL(
                "create table orders" +
                        "(id integer primary key autoincrement," +
                        "name text," +
                        "price double," +
                        "image int," +
                        "description text)");
        sqLiteDatabase.execSQL(
                "create table myorders" +
                        "(id integer primary key autoincrement," +
                        "name text," +
                        "image int," +
                        "description text," +
                        "price double)");

        sqLiteDatabase.execSQL("create table MedicineData"
                +"(MedicineName TEXT primary key,"+
                "Price DOUBLE,"+
                "Quantity TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists users");
        sqLiteDatabase.execSQL("DROP table if exists orders");
        sqLiteDatabase.execSQL("DROP table if exists myorders");
        sqLiteDatabase.execSQL("DROP table if exists MedicineData");
        onCreate(sqLiteDatabase);

    }
    public Boolean insertmedicinedata(String MedicineName, Double Price, String Quantity)
    {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("MedicineName", MedicineName);
        values.put("Price", Price);
//        values.put("image", image);
        values.put("Quantity", Quantity);
        long id = database.insert("MedicineData", null, values);
        if (id <= 0) {
            return false;
        } else {
            return true;
        }
    }
    public Boolean updatemedicinedata(String MedicineName, Double Price, String Quantity)
    {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("Price", Price);
//        values.put("image", image);
        values.put("Quantity", Quantity);
        Cursor cursor = database.rawQuery("Select * from MedicineData where MedicineName=?",new String[]{MedicineName});
        if(cursor.getCount()>0) {
            long id = database.update("MedicineData", values, "MedicineName=?", new String[]{MedicineName});
            if (id <= 0) {
                return false;
            } else {
                return true;
            }
        }
        else {
            return false;
        }
    }

    public Boolean deletemedicinedata(String MedicineName)
    {
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("Select * from MedicineData where MedicineName=?",new String[]{MedicineName});
        if(cursor.getCount()>0) {
            long id = database.delete("MedicineData", "MedicineName=?", new String[]{MedicineName});
            if (id <= 0) {
                return false;
            } else {
                return true;
            }
        }
        else {
            return false;
        }
    }

    public Cursor getdata()
    {
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("Select * from MedicineData ",null);
         return cursor;
    }





    public ArrayList<OrdersModel> getOrders() {
        ArrayList<OrdersModel> orders = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select id,name,image,price from orders", null);
        if (cursor.moveToFirst()) {
            while (cursor.moveToNext()) {
                OrdersModel model = new OrdersModel();
                model.setOrderNumber(cursor.getInt(0)+"");
                model.setSoldItemName(cursor.getString(1));
                model.setOrderImage(cursor.getInt(2));
                model.setPrice(cursor.getDouble(3));
                orders.add(model);
            }
        }
        cursor.close();
        database.close();
        return orders;
    }

    public Cursor getOrderById(int id) {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from orders where id= " + id, null);

        if (cursor != null)
            cursor.moveToFirst();

        return cursor;
    }

    public int deletedOrder(String id) {
        SQLiteDatabase database = this.getWritableDatabase();
        return database.delete("orders", "id=" + id, null);
    }

    public boolean insertOrder(double price, int image, String name, String description) {
            SQLiteDatabase database = getReadableDatabase();
            ContentValues values = new ContentValues();
        /*
        id=0
        name=1
        phone=2
        price=3
        image=4
        desc=5
        groceryname=6
        quantity=7
         */
            values.put("name", name);
            values.put("price", price);
            values.put("image", image);
            values.put("description", description);
            long id = database.insert("orders", null, values);
            if (id <= 0) {
                return false;
            } else {
                return true;
            }
        }

    public boolean insertmyorders(double productPrice, int productImage, String productName, String description) {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
        /*
        id=0
        name=1
        phone=2
        price=3
        image=4
        desc=5
        groceryname=6
        quantity=7
         */
        values.put("name", productName);
        values.put("price", productPrice);
        values.put("image", productImage);
        values.put("description", description);
        long id = database.insert("myorders", null, values);
        if (id <= 0) {
            return false;
        } else {
            return true;
        }

    }
    public ArrayList<MyOrderModel> getmyorders() {
        ArrayList<MyOrderModel> orders = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select id,name,image,price from myorders", null);
        if (cursor.moveToFirst()) {
            while (cursor.moveToNext()) {
                MyOrderModel model = new MyOrderModel();
                model.setOrderNumber(cursor.getInt(0)+"");
                model.setSoldItemName(cursor.getString(1));
                model.setOrderImage(cursor.getInt(2));
                model.setPrice(cursor.getDouble(3));
                orders.add(model);
            }
        }
        cursor.close();
        database.close();
        return orders;
    }

    public Cursor getmyorderById(int id) {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from myorders where id= " + id, null);

        if (cursor != null)
            cursor.moveToFirst();

        return cursor;
    }
}






