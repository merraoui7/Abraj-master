package com.zeneo.abraj;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.zeneo.abraj.Adapter.ViewPagerAdapter;
import com.zeneo.abraj.Fragment.ChineseFragment;
import com.zeneo.abraj.Fragment.HoroscopesFragment;
import com.zeneo.abraj.Fragment.HoroscopesYearlyFragment;
import com.zeneo.abraj.Fragment.ZodiacFeaturesFragment;
import com.zeneo.abraj.View.CustomViewPager;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    CustomViewPager pager;

    BroadcastReceiver broadcastReceiver;
    boolean youMustUnregisterReciver = false;

    public static int img_res [] = {
            R.drawable.ic_aries,
            R.drawable.ic_taurus,
            R.drawable.ic_gemini,
            R.drawable.ic_cancer,
            R.drawable.ic_leo,
            R.drawable.ic_virgo,
            R.drawable.ic_libra,
            R.drawable.ic_scorpio,
            R.drawable.ic_sagittarius,
            R.drawable.ic_capricorn,
            R.drawable.ic_aquarius,
            R.drawable.ic_pisces
    };

    public static String zodiak [] = {
            "الحمل",
            "الثور",
            "الجوزاء",
            "السرطان",
            "الأسد",
            "العذراء",
            "الميزان",
            "العقرب",
            "القوس",
            "الجدي",
            "الدلو",
            "الحوت"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        broadcastReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context arg0, Intent intent) {
                String action = intent.getAction();
                if (action.equals("finish_activity")) {
                    youMustUnregisterReciver = true;
                    finish();
                    // DO WHATEVER YOU WANT.
                }
            }
        };
        registerReceiver(broadcastReceiver, new IntentFilter("finish_activity"));

        navigationView.getHeaderView(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),PickActivity.class);
                intent.putExtra("from","main");
                startActivity(intent);
            }
        });
        setupViewPager();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int index = preferences.getInt("index",0);
        ((ImageView)navigationView.getHeaderView(0).findViewById(R.id.horo_img)).setImageResource(img_res[index]);
        ((TextView)navigationView.getHeaderView(0).findViewById(R.id.horo_title)).setText("برج "+zodiak[index]);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            pager.setCurrentItem(0);
            getSupportActionBar().setTitle("أبراجي");
        } else if (id == R.id.nav_features) {
            pager.setCurrentItem(1);
            getSupportActionBar().setTitle("مميزات برجك");
        } else if (id == R.id.nav_year) {
            pager.setCurrentItem(2);
            getSupportActionBar().setTitle("برجك هذه السنة");
        } else if (id == R.id.nav_chinese) {
            pager.setCurrentItem(3);
            getSupportActionBar().setTitle("الأبراج الصينية");
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_rate) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    void setupViewPager (){

        HoroscopesFragment horoscopesFragment = new HoroscopesFragment();

        pager = findViewById(R.id.view_pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag("Horoscopes",horoscopesFragment);
        adapter.addFrag("Features",new ZodiacFeaturesFragment());
        adapter.addFrag("Yearly",new HoroscopesYearlyFragment());
        adapter.addFrag("Chinese",new ChineseFragment());

        pager.setAdapter(adapter);

    }



}
