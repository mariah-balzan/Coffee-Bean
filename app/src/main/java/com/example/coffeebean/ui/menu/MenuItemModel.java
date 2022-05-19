package com.example.coffeebean.ui.menu;

public class MenuItemModel {
    //Since in the menu we only need to display the name of the item, only the name is required.
    public String Name;

    //Constructor of the model.
    public MenuItemModel(String name)
    {
        this.Name = name;
    }

    //Getter and Setter
    public String getName() {

        return Name;
    }

    public void setName(String name) {

        Name = name;
    }
}
