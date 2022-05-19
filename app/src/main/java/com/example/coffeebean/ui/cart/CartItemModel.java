package com.example.coffeebean.ui.cart;

public class CartItemModel
{
    /*In the cart, all the data of the item will be displayed. The Name as primary text, and the rest as secondary, offering some description
    of the item that the user has put in the cart. */
    public String Name;
    public String Size;
    public String Milk;
    public String Syrup;
    public String SugarAmt;
    public int Price;

    //Constructor.
    public CartItemModel(String name, String size, String milk, String syrup, String sugarAmt, int price)
    {
        Name = name;
        Size = size;
        Milk = milk;
        Syrup = syrup;
        SugarAmt = sugarAmt;
        Price = price;
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

        return Name;
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
