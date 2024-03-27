package com.example.pharmacy.Models;

public class LocationModel {
    int image;
    String shopname,address,phonenum;

    public LocationModel(int image, String shopname, String address, String phonenum) {
        this.image = image;
        this.shopname = shopname;
        this.address = address;
        this.phonenum = phonenum;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }
}
