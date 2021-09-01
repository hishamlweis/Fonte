package com.hishamlweis.fonte;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.hishamlweis.fonte.CartDatabase.OrderContract;

public class CartAdapter extends CursorAdapter {

    public CartAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.cartlist, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView productName, price, quantity;

        productName = view.findViewById(R.id.productNameinOrderSummary);
        price = view.findViewById(R.id.priceinOrderSummary);
        quantity = view.findViewById(R.id.quantityinOrderSummary);

        int name = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_NAME);
        int priceofproduct = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_PRICE);
        int quantityofproduct = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_QUANTITY);

        String nameOfProduct = cursor.getString(name);
        String priceOfProduct = cursor.getString(priceofproduct);
        String quantityOfProduct = cursor.getString(quantityofproduct);

        productName.setText(nameOfProduct);
        price.setText(priceOfProduct);
        quantity.setText(quantityOfProduct);

    }
}
