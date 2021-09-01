package com.hishamlweis.fonte.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.hishamlweis.fonte.CaptionedImagesAdapter;
import com.hishamlweis.fonte.Items;
import com.hishamlweis.fonte.R;
import com.hishamlweis.fonte.utils.PreferenceUtils;

import java.util.ArrayList;
import java.util.List;

public class ShoppingActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;


    List<Items> itemsList;
    RecyclerView recyclerView;
    CaptionedImagesAdapter captionedImagesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_main);

//        Drawer
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        //Create an array list
        itemsList = new ArrayList<>();
        itemsList.add(new Items("Sliced White Bread", "Click to view (5 SAR)", R.drawable.slicedwhitebread));
        itemsList.add(new Items("Sliced Brown Bread", "Click to view (5 SAR)", R.drawable.slicedbrownbread));
        itemsList.add(new Items("Sliced Milk Bread", "Click to view (5 SAR)", R.drawable.slicedmilkbread));
        itemsList.add(new Items("Sesame Burger Bun", "Click to view (4 SAR)", R.drawable.sesameburgerbunjumbo));
        itemsList.add(new Items("Sub Sandwich Sesame", "Click to view (3 SAR)", R.drawable.subsandwichsesamejumbo));
        itemsList.add(new Items("Mini Milk Bread", "Click to view (3 SAR)", R.drawable.minimilkbread));
        itemsList.add(new Items("Hotdog Bread", "Click to view (5 SAR)", R.drawable.hotdogbread));
        itemsList.add(new Items("Arabic White Bread", "Click to view (1 SAR)", R.drawable.arabicwhitebread));
        itemsList.add(new Items("Arabic Brown Bread", "Click to view (1 SAR)", R.drawable.arabicbrownbread));
        itemsList.add(new Items("White Wraps", "Click to view (6 SAR)", R.drawable.tortillawhite));
        itemsList.add(new Items("Brown Wraps", "Click to view (7 SAR)", R.drawable.tortillabrown));
        itemsList.add(new Items("Spinach Wraps", "Click to view (7 SAR)", R.drawable.tortillaspinach));
        itemsList.add(new Items("Spicy Wraps", "Click to view (7 SAR)", R.drawable.tortillaspicy));
        itemsList.add(new Items("Tomato Wraps", "Click to view (7 SAR)", R.drawable.tortillatomato));
        itemsList.add(new Items("Frozen Wraps", "Click to view (7 SAR)", R.drawable.tortillafrozen));


        //Recycler view
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(null));

        //adapter
        captionedImagesAdapter = new CaptionedImagesAdapter(this, itemsList);
        recyclerView.setAdapter(captionedImagesAdapter);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_product:
                Intent intent = new Intent(this, ShoppingActivity.class);
                startActivity(intent);
                break;

            case R.id.nav_cart:
                Intent intent2 = new Intent(this, SummaryActivity.class);
                startActivity(intent2);
                break;

            case R.id.nav_logout:
                PreferenceUtils.savePassword(null, this);
                PreferenceUtils.saveEmail(null, this);
                Intent intent3 = new Intent(this, Login.class);
                startActivity(intent3);
                finish();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}