package com.example.coffeebean.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeebean.R;
import com.example.coffeebean.ui.cart.CartItemModel;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>
{
    private List<CartItemModel> cartItems;

    public CartAdapter(List<CartItemModel> cart)
    {
        this.cartItems = cart;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        //Getting the context of the current class.
        Context context = parent.getContext();
        //The inflater allows us to create the UI without XML, just code. In this case we are using Java to inflate the layout of the recyclerview.
        LayoutInflater inflater = LayoutInflater.from(context);
        //Setting the view to the card (layout object) that corresponds to the MenuItems.
        View itemView = inflater.inflate(R.layout.layout_cart_card, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        //Getting the item at the desired position from the recyclerview.
        CartItemModel item = cartItems.get(position);
        //Creating a new textview that its value is passed through the ViewHolder.
        TextView primaryTextView = holder.pTextView;
        TextView secondaryTextView = holder.sTextView;

        //Specific String to be displayed as a subtext.
        String secondary = "Sugar Amount: " + item.SugarAmt + "     Size: " + item.Size + "     Milk: " + item.Milk + "     Syrup: " + item.Syrup + "     Price: " + "€" +item.Price;
        //The below parameter for the setText was cast to a string via a toString() method. However this was not working since we do not want a variable
        //of type sting, we want it of MenuItemModel. So instead we call the Name attribute of the object in the list via the .Name command.
        primaryTextView.setText(item.Name);
        secondaryTextView.setText(secondary);
    }

    @Override
    public int getItemCount()
    {
        return cartItems.size();
    }

    /*This ViewHolder will allow us to access any item from the recyclerview without out it looking it up each time. This is what allows us to interact
    with each item in the recyclerview without using the 'finViewById()' method. */
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        //Creating the required objects that are present in each card.
        public ImageView ImageView;
        public TextView pTextView, sTextView;
        public ImageView cartDelete;

        public ViewHolder(final View itemView)
        {
            super(itemView);
            ImageView = (ImageView) itemView.findViewById(R.id.cart_list_item_icon);
            pTextView = (TextView) itemView.findViewById(R.id.cart_primary_text);
            sTextView = (TextView) itemView.findViewById(R.id.cart_secondary_text);
        }

        //When one of the cards in the recyclerview is clicked!!
        public void itemOnClick(View v)
        {
            //cartDelete.deleteCartItem(cartItems);
        }

    }
}
