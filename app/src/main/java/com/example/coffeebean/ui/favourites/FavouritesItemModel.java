package com.example.coffeebean.ui.favourites;

public class FavouritesItemModel {
    public int ID;
    public String Name;
    public String Size;
    public String Milk;
    public String Syrup;
    public String SugarAmt;
    public int Price;

    //Constructor
    public FavouritesItemModel(int ID, String name, String size, String milk, String syrup, String sugarAmt, int price)
    {
        this.ID = ID;
        Name = name;
        Size = size;
        Milk = milk;
        Syrup = syrup;
        SugarAmt = sugarAmt;
        Price = price;
    }

    //Getters & Setters
    public int getID() {

        return ID;
    }

    public void setID(int ID) {

        this.ID = ID;
    }

    public String getName() {

        return Name;
    }

    public void setName(String name) {

        Name = name;
    }

    public String getSize() {

        return Size;
    }

    public void setSize(String size) {

        Size = size;
    }
    public String getMilk() {

        return Milk;
    }

    public void setMilk(String milk) {

        Milk = milk;
    }

    public String getSyrup() {
        return Syrup;
    }

    public void setSyrup(String syrup) {

        Syrup = syrup;
    }

    public String getSugarAmt() {

        return SugarAmt;
    }

    public void setSugarAmt(String sugarAmt) {

        SugarAmt = sugarAmt;
    }

    public int getPrice() {

        return Price;
    }

    public void setPrice(int price) {

        Price = price;
    }
}
