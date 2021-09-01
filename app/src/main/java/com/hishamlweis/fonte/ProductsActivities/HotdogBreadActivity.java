package com.hishamlweis.fonte.ProductsActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hishamlweis.fonte.CartDatabase.OrderContract;
import com.hishamlweis.fonte.R;
import com.hishamlweis.fonte.activities.ShoppingActivity;

public class HotdogBreadActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    ImageView imageView;
    ImageButton plusQuantity, minusQuantity;
    TextView quantityNumber, productName, productPrice;
    Button addToCart;

    int quantity;
    public Uri mCurrentCartUri;
    boolean hasAllRequiredValues = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        imageView = findViewById(R.id.imageViewInfo);
        plusQuantity = findViewById(R.id.addquantity);
        minusQuantity = findViewById(R.id.subquantity);
        quantityNumber = findViewById(R.id.quantity);
        productName = findViewById(R.id.productNameinInfo);
        productPrice = findViewById(R.id.productPrice);
        addToCart = findViewById(R.id.addtocart);

        productName.setText("Hotdog Bread");
        imageView.setImageResource(R.drawable.hotdogbread);
        productPrice.setText("5 SAR");

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HotdogBreadActivity.this, ShoppingActivity.class);
                startActivity(intent);

                // once this button is clicked we want to save our values in the database and send those values to summary activity where we display our order

                SaveCart();
            }
        });

        plusQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Product Price
                int basePrice = 5;
                quantity++;
                displayQuantity();
                int productsPrice = basePrice * quantity;
                String setNewPrice = String.valueOf(productsPrice);
                productPrice.setText(setNewPrice + " SAR");
            }
        });

        minusQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int basePrice = 5;
                if (quantity == 0) {
                    Toast.makeText(HotdogBreadActivity.this, "Cant decrease quantity < 0", Toast.LENGTH_SHORT).show();
                } else {
                    quantity--;
                    displayQuantity();
                    int productsPrice = basePrice * quantity;
                    String setNewPrice = String.valueOf(productsPrice);
                    productPrice.setText(setNewPrice + " SAR");
                }
            }
        });
    }

    private boolean SaveCart() {
        String name = productName.getText().toString();
        String price = productPrice.getText().toString();
        String quantity = quantityNumber.getText().toString();

        ContentValues values = new ContentValues();
        values.put(OrderContract.OrderEntry.COLUMN_NAME, name);
        values.put(OrderContract.OrderEntry.COLUMN_PRICE, price);
        values.put(OrderContract.OrderEntry.COLUMN_QUANTITY, quantity);

        if (mCurrentCartUri == null) {
            Uri newUri = getContentResolver().insert(OrderContract.OrderEntry.CONTENT_URI, values);
            if (newUri == null) {
                Toast.makeText(this, "Failed to add to cart.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Added to cart successfully.", Toast.LENGTH_SHORT).show();
            }
        }

        hasAllRequiredValues = true;
        return hasAllRequiredValues;
    }

    private void displayQuantity() {
        quantityNumber.setText(String.valueOf(quantity));
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {OrderContract.OrderEntry._ID,
                OrderContract.OrderEntry.COLUMN_NAME,
                OrderContract.OrderEntry.COLUMN_PRICE,
                OrderContract.OrderEntry.COLUMN_QUANTITY
        };

        return new CursorLoader(this, mCurrentCartUri,
                projection,
                null,
                null,
                null);
    }


    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

        if (cursor == null || cursor.getCount() < 1) {
            return;
        }

        if (cursor.moveToFirst()) {

            int name = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_NAME);
            int price = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_PRICE);
            int quantity = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_QUANTITY);

            String nameOfProduct = cursor.getString(name);
            String priceOfProduct = cursor.getString(price);
            String quantityOfProduct = cursor.getString(quantity);

            productName.setText(nameOfProduct);
            productPrice.setText(priceOfProduct);
            quantityNumber.setText(quantityOfProduct);
        }


    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        productName.setText("");
        productPrice.setText("");
        quantityNumber.setText("");

    }
}