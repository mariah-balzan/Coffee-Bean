package com.example.coffeebean;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.example.coffeebean.ui.favourites.FavouritesItemModel;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class ScreenTwo extends AppCompatActivity {

    ImageView backArrow, ItemImageView;
    TextView itemName, itemPrice, itemDescription;
    Button addButton, setBtn;
    RadioButton small, medium, large;
    RadioButton regular, almond, coconut;
    RadioButton no, choc, van, caramel;
    EditText amountTxt;
    ToggleButton favToggleBtn;

    //Creating a new bundle instance
    public static Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_two);

        //handles the back arrow(May make it more efficient in the future)
        backArrow = findViewById(R.id.previousArrowBtn);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScreenTwo.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        //Getting the name of the selected Item through intent.
        getName();

        //Getting the image of the selected Item through intent.
        getImage();

        //Getting the description of the selected Item through intent.
        getDescription();

        //Creating and instance of the button found on the screen.
        addButton = (Button) findViewById(R.id.addBtn);
        //Add item to database.
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Declaring drink size variable
                String drinkSize = "";
                //Declaring milk variable
                String milk = "";
                //Declaring syrup size variable
                String syrup = "";

                /*Re-defining the variables to make sure that they are available in the current context and redirecting the objects created
                above to the instances that are present on screen using findViewById() */
                //Re-defining the variables to male sure that they are available in the current context and redirecting the objects created above to the instances that are present on screen using findViewById().
                itemName = (TextView) findViewById(R.id.selectedItemName);
                amountTxt = (EditText) findViewById(R.id.amountTxt);
                itemPrice = (TextView) findViewById(R.id.totalPricetxt);

                //Radio buttons(group). Redirecting the objects created above to the instances that are present on screen using findViewById().
                small = (RadioButton) findViewById(R.id.smallRadioBtn);
                medium = (RadioButton) findViewById(R.id.mediumRadiobtn);
                large = (RadioButton) findViewById(R.id.largeRadioBtn);

                //Radio buttons(group). Redirecting the objects created above to the instances that are present on screen using findViewById().
                regular = (RadioButton) findViewById(R.id.regularRadioBtn);
                almond = (RadioButton) findViewById(R.id.almondRadiobtn);
                coconut = (RadioButton) findViewById(R.id.coconutRadioBtn);

                //Radio buttons(group). Redirecting the objects created above to the instances that are present on screen using findViewById().
                no = (RadioButton) findViewById(R.id.noRadioBtn);
                choc = (RadioButton) findViewById(R.id.chocRadiobtn);
                van = (RadioButton) findViewById(R.id.vanRadioBtn);
                caramel = (RadioButton) findViewById(R.id.caramelRadioBtn);

                //Checking which of the drink sizes are selected
                if (small.isChecked())
                    drinkSize = "Small";
                else if (medium.isChecked())
                    drinkSize = "Medium";
                else if (large.isChecked())
                    drinkSize = "Large";

                //Checking which of the milks are selected
                if (regular.isChecked())
                    milk = "Regular";
                else if (almond.isChecked())
                    milk = "Almond";
                else if (coconut.isChecked())
                    milk = "Coconut";

                //Checking which of the syrups are selected
                if (no.isChecked())
                    syrup = "None";
                else if (choc.isChecked())
                    syrup = "Chocolate";
                else if (van.isChecked())
                    syrup = "Vanilla";
                else if (caramel.isChecked())
                    syrup = "Caramel";

                //Storing all the data acquired above into variables.
                String sugarAmt = amountTxt.getText().toString();
                String name = itemName.getText().toString();
                int price = Integer.parseInt(itemPrice.getText().toString());
                //Adding the item to the database by passing it to the corresponding method.
                addItem(name, drinkSize, milk, syrup, sugarAmt, price);
            }
        });

        //Getting the price of the item from the database.
        getPrice();

        //Redirecting the toggleButton that represents the Favourites feature.
        favToggleBtn = (ToggleButton) findViewById(R.id.favToggleBtn);
        //Handles what happens when the Favourites toggle button is toggled.
        favToggleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Validation to check if a size is selected before adding to the favourites.
                if (!small.isChecked() && !medium.isChecked() && !large.isChecked()) {
                    //Generating a toast in case the user does not select a size before adding it to the favourites.
                    Toast.makeText(ScreenTwo.this, "Make sure that you have selected a size option.", Toast.LENGTH_SHORT).show();
                }

                //Validation to check if milk is selected before adding to the favourites.
                else if (!regular.isChecked() && !almond.isChecked() && !coconut.isChecked()) {
                    //Generating a toast in case the user does not select a size before adding it to the favourites.
                    Toast.makeText(ScreenTwo.this, "Make sure that you have selected a milk option.", Toast.LENGTH_SHORT).show();
                }

                //Validation to check if syrup is selected before adding to the favourites.
                else if (!no.isChecked() && !choc.isChecked() && !van.isChecked() && !caramel.isChecked()) {
                    //Generating a toast in case the user does not select a size before adding it to the favourites.
                    Toast.makeText(ScreenTwo.this, "Make sure that you have selected a syrup option.", Toast.LENGTH_SHORT).show();
                } else {
                    //Creating an instance of the database.
                    DataBaseOpenHelper db = new DataBaseOpenHelper(ScreenTwo.this);

                    //Storing the size of the favourites table in a variable.
                    int size = db.getFavouritesItem().size();
                    //Declaring the ID variable.
                    int ID = 0;
                    //Declaring the drink size variable.
                    String drinkSize = "";
                    //Declaring the milk  variable.
                    String milk = "";
                    //Declaring the syrup variable.
                    String syrup = "";

                    //This unnecessary piece of code is added since SQLite could not accept an AUTOINCREMENT data type. :/
                    if (size == 0) {
                        ID = 1;
                    } else {
                        ID = (size + 1);
                    }

                    //Re-defining the variables to make sure that they are available in the current context.
                    itemName = (TextView) findViewById(R.id.selectedItemName);
                    amountTxt = (EditText) findViewById(R.id.amountTxt);
                    itemPrice = (TextView) findViewById(R.id.totalPricetxt);

                    //Radio buttons(group). Redirecting the objects created above to the instances that are present on screen using findViewById().
                    small = (RadioButton) findViewById(R.id.smallRadioBtn);
                    medium = (RadioButton) findViewById(R.id.mediumRadiobtn);
                    large = (RadioButton) findViewById(R.id.largeRadioBtn);

                    //Radio buttons(group). Redirecting the objects created above to the instances that are present on screen using findViewById().
                    regular = (RadioButton) findViewById(R.id.regularRadioBtn);
                    almond = (RadioButton) findViewById(R.id.almondRadiobtn);
                    coconut = (RadioButton) findViewById(R.id.coconutRadioBtn);

                    //Radio buttons(group). Redirecting the objects created above to the instances that are present on screen using findViewById().
                    no = (RadioButton) findViewById(R.id.noRadioBtn);
                    choc = (RadioButton) findViewById(R.id.chocRadiobtn);
                    van = (RadioButton) findViewById(R.id.vanRadioBtn);
                    caramel = (RadioButton) findViewById(R.id.caramelRadioBtn);

                    //Checking which of the drink sizes is selected
                    if (small.isChecked())
                        drinkSize = "Small";
                    else if (medium.isChecked())
                        drinkSize = "Medium";
                    else if (large.isChecked())
                        drinkSize = "Large";

                    //Checking which of the milks is selected
                    if (regular.isChecked())
                        milk = "Regular";
                    else if (almond.isChecked())
                        milk = "Almond";
                    else if (coconut.isChecked())
                        milk = "Coconut";

                    //Checking which of the drink sizes is selected
                    if (no.isChecked())
                        syrup = "None";
                    else if (choc.isChecked())
                        syrup = "Chocolate";
                    else if (van.isChecked())
                        syrup = "Vanilla";
                    else if (caramel.isChecked())
                        syrup = "Caramel";

                    //Storing all data items into variables.
                    String name = itemName.getText().toString();
                    String sugarAmt = amountTxt.getText().toString();
                    int price = Integer.parseInt(String.valueOf(itemPrice.getText()));

                    //Creating a FavouritesItemModel object using the above acquired variable.
                    FavouritesItemModel fav = new FavouritesItemModel(ID, name, drinkSize, milk, syrup, sugarAmt, price);

                    if (favToggleBtn.isChecked()) {
                        //Adding the items to the table.
                        addFavItem(fav.getID(), fav.getName(), fav.getSize(), fav.getMilk(), fav.getSyrup(), fav.getSugarAmt(), fav.getPrice());
                        //Displaying successful message.
                        Toast.makeText(ScreenTwo.this, "Item has been added to favourites :)", Toast.LENGTH_SHORT).show();
                        //Making the icon appear filled.
                        favToggleBtn.setChecked(true);

                    } else {
                        //Delete the item if the favourites icon is de-selected.
                        deleteFavouritesItem(fav);
                        //Displaying message.
                        Toast.makeText(ScreenTwo.this, "Item has been removed from favourites :(", Toast.LENGTH_SHORT).show();
                        //Making the icon appear hollowed.
                        favToggleBtn.setChecked(false);
                    }
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        favToggleBtn = (ToggleButton) findViewById(R.id.favToggleBtn);
        outState.putBoolean("ToggleButtonState", favToggleBtn.isChecked());
        super.onSaveInstanceState(outState);
    }

    public void getName()
    {
        //Creating a new bundle instance and getting the intent through it. The 'getExtras()' method is also used to receive the intent passed from the fragment through the 'putExtra()'.
        Bundle name = getIntent().getExtras();
        //Storing the name received through the intent into a variable.
        String menuItemName = name.getString("Name");

        //Getting the desired textview by id.
        itemName = (TextView) findViewById(R.id.selectedItemName);
        //Setting the text of the above textview with the intent variable received.
        itemName.setText(menuItemName);
    }

    //Getting the image of the selected item and displaying it in this activity.
    public void getImage()
    {
        //Creating a new intent instance that will be able to receive by the 'getIntent()' method is also used to receive the intent passed from the fragment.
        Intent intent = getIntent();
        //Storing the image received from the fragment into a byte[] array.
        byte[] image = intent.getByteArrayExtra("ByteImage");

        //Converting the byte[] array to a byte input string. This is done to make it easier for the Bitmap to display the image
        InputStream stream = new ByteArrayInputStream(image);

        //converting the byte stream to a bitmap in order to be able to display it properly.
        Bitmap bitmap = BitmapFactory.decodeStream(stream);

        //Getting the desired ImageView by id.
        ItemImageView = (ImageView) findViewById(R.id.itemImageView);
        //Setting the image of the above ImageView with the intent variable received.
        ItemImageView.setImageBitmap(bitmap);

    }

    //Getting the description of the selected item and displaying it in this activity.
    public void getDescription()
    {
        //Creating a new bundle instance and getting the intent through it. The 'getExtras()' method is also used to receive the intent passed from the fragment through the 'putExtra()'.
        Bundle description = getIntent().getExtras();
        //Storing the description received through the intent into a variable.
        String menuDescription = description.getString("Description");

        //Getting the desired textview by id.
        itemDescription = (TextView) findViewById(R.id.itemDescriptiontxt);
        //Setting the text of the above textview with the intent variable received.
        itemDescription.setText(menuDescription);
    }

    public void addItem(String name,String size, String milk, String syrup, String sugarAmt, int price)
    {
        //Radio buttons(group). Redirecting the objects created above to the instances that are present on screen using findViewById().
        small = (RadioButton) findViewById(R.id.smallRadioBtn);
        medium = (RadioButton) findViewById(R.id.mediumRadiobtn);
        large = (RadioButton) findViewById(R.id.largeRadioBtn);

        //Radio buttons(group). Redirecting the objects created above to the instances that are present on screen using findViewById().
        regular = (RadioButton) findViewById(R.id.regularRadioBtn);
        almond = (RadioButton) findViewById(R.id.almondRadiobtn);
        coconut = (RadioButton) findViewById(R.id.coconutRadioBtn);

        //Radio buttons(group). Redirecting the objects created above to the instances that are present on screen using findViewById().
        no = (RadioButton) findViewById(R.id.noRadioBtn);
        choc = (RadioButton) findViewById(R.id.chocRadiobtn);
        van = (RadioButton) findViewById(R.id.vanRadioBtn);
        caramel = (RadioButton) findViewById(R.id.caramelRadioBtn);

        //Amount TextView. Redirecting the objects created above to the instances that are present on screen using findViewById().
        amountTxt = (EditText) findViewById(R.id.amountTxt);

        //Validation to check if the item is suitable to be inserted into the database.
        if(!small.isChecked() && !medium.isChecked() && !large.isChecked())
        {
            //Generating a toast to tell the users what they require.
            Toast.makeText(ScreenTwo.this, "Make sure that you have selected a size option.", Toast.LENGTH_SHORT).show();
        }else if(!regular.isChecked() && !almond.isChecked() && !coconut.isChecked())
        {
            //Generating a toast to tell the users what they require.
            Toast.makeText(ScreenTwo.this, "Make sure that you have selected a milk option.", Toast.LENGTH_SHORT).show();
        }else if(!no.isChecked() && !choc.isChecked() && !van.isChecked() && !caramel.isChecked()){
            //Generating a toast to tell the users what they require.
            Toast.makeText(ScreenTwo.this, "Make sure that you have selected a syrup option.", Toast.LENGTH_SHORT).show();
        }
        else if(amountTxt.getText().toString().equals("0"))
        {
            //Generating a toast to tell the users what they require.
            Toast.makeText(ScreenTwo.this, "Make sure to enter the number of sugar needed.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            //Creating a nwe instance of the database.
            DataBaseOpenHelper db = new DataBaseOpenHelper(ScreenTwo.this);
            //Adding the data to the database.
            db.addCartItem(name, size, milk, syrup, sugarAmt, price);
            //Generating a toast to tell the users that the items have been added.
            Toast.makeText(ScreenTwo.this, "Added to cart :)", Toast.LENGTH_SHORT).show();
        }
    }

    public void addFavItem(int ID, String name, String size,String milk, String syrup, String sugarAmt, int price)
    {
        DataBaseOpenHelper db = new DataBaseOpenHelper(ScreenTwo.this);
        db.addFavouritesItem(ID, name, size, milk, syrup, sugarAmt, price);
    }

    public void deleteFavouritesItem(FavouritesItemModel fav)
    {
        DataBaseOpenHelper db = new DataBaseOpenHelper(ScreenTwo.this);
        db.deleteFavouritesItem(fav);
    }

    public void getPrice()
    {
        //Handling radio buttons for the sizes.
        small = (RadioButton) findViewById(R.id.smallRadioBtn);
        medium = (RadioButton) findViewById(R.id.mediumRadiobtn);
        large = (RadioButton) findViewById(R.id.largeRadioBtn);

        //Handling radio buttons for the sizes.
        regular = (RadioButton) findViewById(R.id.regularRadioBtn);
        almond = (RadioButton) findViewById(R.id.almondRadiobtn);
        coconut = (RadioButton) findViewById(R.id.coconutRadioBtn);

        //Handling radio buttons for the sizes.
        no = (RadioButton) findViewById(R.id.noRadioBtn);
        choc = (RadioButton) findViewById(R.id.chocRadiobtn);
        van = (RadioButton) findViewById(R.id.vanRadioBtn);
        caramel = (RadioButton) findViewById(R.id.caramelRadioBtn);

        //Getting the id of the button
        setBtn = (Button) findViewById(R.id.setBtn);

        //Bundle price = getIntent().getExtras();
        //final int[] menuItemPrice = {price.getInt("Price")};

        //Getting the Item price textview by findViewById().
        itemPrice = (TextView) findViewById(R.id.totalPricetxt);
        //The value is initially set to 0.
        itemPrice.setText("0");

        //Handles what happens if the 'small' option from the radio group is chosen.
        small.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Bundle price = getIntent().getExtras();
                int menuItemPrice = price.getInt("Price");

                itemPrice = (TextView) findViewById(R.id.totalPricetxt);
                String finalPrice = String.valueOf(menuItemPrice);
                itemPrice.setText(finalPrice);
            }
        });

        //Handles what happens if the 'medium' option from the radio group is chosen.
        medium.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Bundle price = getIntent().getExtras();
                int menuItemPrice = price.getInt("Price");
                menuItemPrice = (menuItemPrice + 1);

                itemPrice = (TextView) findViewById(R.id.totalPricetxt);
                String finalPrice = String.valueOf(menuItemPrice);
                itemPrice.setText(finalPrice);
            }
        });

        //Handles what happens if the 'large' option from the radio group is chosen.
        large.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Bundle price = getIntent().getExtras();
                int menuItemPrice = price.getInt("Price");
                menuItemPrice = (menuItemPrice + 2);

                itemPrice = (TextView) findViewById(R.id.totalPricetxt);
                String finalPrice = String.valueOf(menuItemPrice);
                itemPrice.setText(finalPrice);

            }
        });

        //Handles what happens if the 'regular' option from the radio group is chosen.
        regular.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Bundle price = getIntent().getExtras();
                int menuItemPrice = price.getInt("Price");
                menuItemPrice = (menuItemPrice);

                itemPrice = (TextView) findViewById(R.id.totalPricetxt);
                String finalPrice = String.valueOf(menuItemPrice);
                itemPrice.setText(finalPrice);

            }
        });

        //Handles what happens if the 'almond' option from the radio group is chosen.
        almond.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Bundle price = getIntent().getExtras();
                int menuItemPrice = price.getInt("Price");
                menuItemPrice = (menuItemPrice + 1);

                itemPrice = (TextView) findViewById(R.id.totalPricetxt);
                String finalPrice = String.valueOf(menuItemPrice);
                itemPrice.setText(finalPrice);

            }
        });

        //Handles what happens if the 'coconut' option from the radio group is chosen.
        coconut.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Bundle price = getIntent().getExtras();
                int menuItemPrice = price.getInt("Price");
                menuItemPrice = (menuItemPrice + 1);

                itemPrice = (TextView) findViewById(R.id.totalPricetxt);
                String finalPrice = String.valueOf(menuItemPrice);
                itemPrice.setText(finalPrice);

            }
        });

        //Handles what happens if the 'no' option from the radio group is chosen.
        no.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Bundle price = getIntent().getExtras();
                int menuItemPrice = price.getInt("Price");
                menuItemPrice = (menuItemPrice + 0);

                itemPrice = (TextView) findViewById(R.id.totalPricetxt);
                String finalPrice = String.valueOf(menuItemPrice);
                itemPrice.setText(finalPrice);

            }
        });

        //Handles what happens if the 'large' option from the radio group is chosen.
        choc.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Bundle price = getIntent().getExtras();
                int menuItemPrice = price.getInt("Price");
                menuItemPrice = (menuItemPrice + 1);

                itemPrice = (TextView) findViewById(R.id.totalPricetxt);
                String finalPrice = String.valueOf(menuItemPrice);
                itemPrice.setText(finalPrice);

            }
        });

        //Handles what happens if the 'large' option from the radio group is chosen.
        van.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Bundle price = getIntent().getExtras();
                int menuItemPrice = price.getInt("Price");
                menuItemPrice = (menuItemPrice + 1);

                itemPrice = (TextView) findViewById(R.id.totalPricetxt);
                String finalPrice = String.valueOf(menuItemPrice);
                itemPrice.setText(finalPrice);

            }
        });

        //Handles what happens if the 'large' option from the radio group is chosen.
        caramel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Bundle price = getIntent().getExtras();
                int menuItemPrice = price.getInt("Price");
                menuItemPrice = (menuItemPrice + 1);

                itemPrice = (TextView) findViewById(R.id.totalPricetxt);
                String finalPrice = String.valueOf(menuItemPrice);
                itemPrice.setText(finalPrice);

            }
        });

        //OnClickListener that handles what happens when the 'Set' button for the amount is pressed.
        setBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //Getting the ItemPrice textview and amountTxt EditText with findViewById().
                itemPrice = (TextView) findViewById(R.id.totalPricetxt);
                amountTxt = (EditText) findViewById(R.id.amountTxt);

                //Getting the current price and converting it into an int variable.
                final int subTotal = Integer.parseInt(itemPrice.getText().toString());
                //Getting the amount chosen by the user and storing it into an int variable.
                int sugarAmt = Integer.parseInt(amountTxt.getText().toString());

                //Creating a new total price by multiplying the current price by the amount chosen by the user.
                int total = (subTotal * sugarAmt);
                //Setting the new price
                itemPrice.setText(String.valueOf(total));

                //Validation
                if(!amountTxt.getText().toString().equals("0") || itemPrice.getText().toString().equals("0"))
                {
                    //Hiding the button once it is pressed.
                    setBtn.setVisibility(view.GONE);
                    //Source: https://stackoverflow.com/questions/9470171/edittext-non-editable
                    //Making the EditText uneditable since the amount has already been chosen.
                    amountTxt.setEnabled(false);
                }

            }
        });
    }
}