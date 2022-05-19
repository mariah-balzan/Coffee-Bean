package com.example.coffeebean;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.coffeebean.databinding.ActivityOrderHistoryBinding;

public class orderHistory extends AppCompatActivity {

    //Creating the required objects by the activity
    ImageView previousBtn;
    Button clearBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        //Redirecting the imageview that represents the back arrow.
        previousBtn = (ImageView) findViewById(R.id.previousArrowBtn);
        //handles the back arrow(May make it more efficient in the future)
        previousBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //Creating a new intent instance, with context of this page and the page that the intent will redirect to.
                Intent intent = new Intent(orderHistory.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        //Redirecting the button created above the teh button 'Clear order history' that is present on screen.
        clearBtn = (Button) findViewById(R.id.clearBtn);
        //Handles what happens when the above button is clicked.
        clearBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //Creating a new instance of the database.
                DataBaseOpenHelper db = new DataBaseOpenHelper(view.getContext());
                //Deleting all the items from the 'Order' table.
                db.deleteAllInOrderHistory();
                //refreshing the page to make it look more dynamic.
                recreate();
            }
        });
    }
}