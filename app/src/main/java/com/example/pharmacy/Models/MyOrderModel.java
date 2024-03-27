package com.example.pharmacy.Models;

public class MyOrderModel {
    int orderImage,orderDelete;
    double price;
    String soldItemName,orderNumber;

    public MyOrderModel(int orderImage, int orderDelete,String soldItemName, double price, String orderNumber) {
        this.orderImage = orderImage;
        this.soldItemName = soldItemName;
        this.price = price;
        this.orderNumber = orderNumber;
        this.orderDelete = orderDelete;
    }

    public MyOrderModel() {

    }


    public int getOrderDelete()
    {return orderDelete;}
    public void setOrderDelete(int orderDelete) {
        this.orderDelete = orderDelete;
    }
    public int getOrderImage() {
        return orderImage;
    }

    public void setOrderImage(int orderImage) {
        this.orderImage = orderImage;
    }

    public String getSoldItemName() {
        return soldItemName;
    }

    public void setSoldItemName(String soldItemName) {
        this.soldItemName = soldItemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

}
