package com.example.mim.dhakacity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private FragmentManager fragmentManager;
    private  DrawerLayout drawer;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private LoginPreference loginPreference;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginPreference = new LoginPreference(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mAuth=FirebaseAuth.getInstance();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment())
                            .commit();
                    break;

                case  R.id.nav_log:
                    startActivity(new Intent(MainActivity.this,AuthinticateActivity.class));
                    break;
                case R.id.nav_travel:
                    //startActivity(new Intent(MainActivity.this,ListActivity.class));
                  startActivity(new Intent(MainActivity.this,ListActivity.class));
                    break;

                case  R.id.nav_hotel:
                    startActivity(new Intent(MainActivity.this,HotelActivity.class));
                    //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HotelFragment())
                            //.commit();
                    break;

                case R.id.nav_hospital:

                    startActivity(new Intent(MainActivity.this,HospitalActivity.class));
                   // getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HospitalFragment())
                            //.commit();
                    break;

                case  R.id.nav_resturent:

                    startActivity( new Intent(MainActivity.this,RestaurentActivity.class));

                    break;

                case  R.id.nav_atm:
                    startActivity(new Intent(MainActivity.this,ATMActivity.class));
                    break;

                case  R.id.nav_map:
                   Intent intent = new Intent(MainActivity.this,MapsActivity.class);
                    startActivity(intent);
                    break;

                case R.id.nav_admin:
                    FirebaseAuth.getInstance().signOut();
                    finish();
                    Toast.makeText(this, " You are Signed out", Toast.LENGTH_SHORT).show();
                    break;



            }
            // set item as selected to persist highlight
            item.setChecked(true);




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
