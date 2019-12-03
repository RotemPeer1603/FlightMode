package com.rotem.fbairlines_project;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    public static boolean showOnlyLanded;
    public static boolean showOnlyOnAir;

    private FirebaseDatabase mDatabase;
    private FlightsDataAdapter adapter;
    public ListView listView;
    private List<LandingData> lstLandingData = new ArrayList<>();
    private FirebaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter intentFilter = new IntentFilter("android.intent.action.AIRPLANE_MODE");

        getBaseContext().registerReceiver(new ConnectivityReceiver(this), intentFilter);
        mDatabase = FirebaseDatabase.getInstance();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DatabaseReference dbRef = mDatabase.getReference("landings");
        //dbRef.setValue(null);

        Toolbar t = findViewById(R.id.toolbar);
        t.setOverflowIcon(ContextCompat.getDrawable(getApplicationContext(),
                android.R.drawable.btn_star));

        listView = findViewById(R.id.flightLV);
        adapter = new FlightsDataAdapter();
        listView.setAdapter(adapter);
        helper = new FirebaseHelper(dbRef, adapter);
    }

    private void search()
    {
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        helper.retrieve();
    }

    private void openSettings()
    {
        Intent myIntent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(myIntent);
    }

    private void buildAllFlights()
    {
        List<String> lstFlights = new ArrayList<>();
        lstFlights.add("EL AL,LY 396,MADRID,17:50,17:47");
        lstFlights.add("Arkia,IZ 826,RAMON,17:50,");
        lstFlights.add("American Airlines,AA 8392,MADRID,17:50,");
        lstFlights.add("AEROMEXICO,AM 7859,MADRID,17:50,17:47");
        lstFlights.add("Iberia,IB 2395,MADRID,17:50,17:47");
        lstFlights.add("AEROLINEASARGENTINAS,AR 7872,MADRID,17:50,");
        lstFlights.add("American Airlines,AA 8377,BRUSSELS,18:05,18:02");
        lstFlights.add("RYANAIR,FR 4103,PAPHOS,18:05,");
        lstFlights.add("EL AL,LY 334,BRUSSELS,18:05,");
        lstFlights.add("Georgian Airways,A9 699,TBILISI,18:10,18:10");
        lstFlights.add("EasyJetEurope,EJU 3737,PARIS,18:15,");
        lstFlights.add("Arkia,IZ 414,BATUMI,18:15,");
        lstFlights.add("Israir,6H 440,RAMON,18:35,18:35");
        lstFlights.add("EL AL,LY 312,LONDON,18:35,18:21");
        lstFlights.add("EasyJet,EZY 2085,LONDON,18:40,18:50");
        lstFlights.add("Alitalia, AZ 812, ROME, 18:55, 19:00 ");
        lstFlights.add("Aegean Airlines,A3 924,ATHENS,19:00,19:00");
        lstFlights.add("Lufthansa German Airlines,LH 694,FRANKFURT,19:10,18:50");
        lstFlights.add("LAUDA MOTION,OE 186,VIENNA,19:10,19:10");
        lstFlights.add("Air Canada,AC 9617,FRANKFURT,19:10,18:50");
        lstFlights.add("Brussels Airlines,SN 7167,FRANKFURT,19:10,18:50");
        lstFlights.add("United Airlines,UA 9240,FRANKFURT,19:10,18:50");
        lstFlights.add("INDIGO,6E 4132,ISTANBUL,19:20,19:20");
        lstFlights.add("Turkish Airlines,TK 788,ISTANBUL,19:20,19:20");
        for (String singleFlightStr : lstFlights)
        {
            String strArr[] = singleFlightStr.split(",");
            buildSingleFlight(strArr);
        }

    }

    private void buildSingleFlight(String strArr[])
    {
        LandingData landingData = new LandingData();
        landingData.companyName = strArr[0];
        landingData.number = strArr[1];
        landingData.city = strArr[2];
        landingData.appTime = strArr[3];
        if (strArr.length > 4)
        {
            landingData.landingTime = strArr[4];
        }
        helper.save(landingData);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            openSettings();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
