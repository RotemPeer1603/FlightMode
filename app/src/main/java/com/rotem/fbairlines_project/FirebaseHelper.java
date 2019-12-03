package com.rotem.fbairlines_project;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class FirebaseHelper
{

    DatabaseReference db;
    Boolean saved = null;
    FlightsDataAdapter adapter;


    public FirebaseHelper(DatabaseReference db, FlightsDataAdapter adapter)
    {
        this.db = db;
        this.adapter = adapter;
    }

    //WRITE
    public boolean save(LandingData landingData)
    {
        if (landingData == null)
        {
            saved = false;
        }
        else
        {
            try
            {
                db.child("landingData").push().setValue(landingData);
                saved = true;

            }
            catch (DatabaseException e)
            {
                e.printStackTrace();
                saved = false;
            }
        }

        return saved;
    }

    //READ
    public void retrieve()
    {
        db.addChildEventListener(new ChildEventListener()
        {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s)
            {
                fetchData(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s)
            {
                fetchData(dataSnapshot);

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot)
            {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s)
            {

            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        });
    }

    private void fetchData(DataSnapshot dataSnapshot)
    {
        List<LandingData> lstLandingData = new ArrayList<>();
        lstLandingData.clear();

        for (DataSnapshot ds : dataSnapshot.getChildren( ))
        {
            LandingData landingData = ds.getValue(LandingData.class);
            lstLandingData.add(landingData);
        }
        filterOnlyOnAir(lstLandingData);
        filterOnlyLanded(lstLandingData);
        adapter.updateList(lstLandingData);
        adapter.notifyDataSetChanged();
    }

    private void filterOnlyOnAir(List<LandingData> lstLandingData)
    {
        for (int index = lstLandingData.size() - 1; index >= 0; index--)
        {
            LandingData landingData = lstLandingData.get(index);
            String landingTime = landingData.getLandingTime();
            if (MainActivity.showOnlyOnAir && landingTime != null)
            {
                lstLandingData.remove(index);
            }
        }
    }

    private void filterOnlyLanded(List<LandingData> lstLandingData)
    {
        for (int index = lstLandingData.size() - 1; index >= 0; index--)
        {
            LandingData landingData = lstLandingData.get(index);
            String landingTime = landingData.getLandingTime();
            if (MainActivity.showOnlyLanded && landingTime == null)
            {
                lstLandingData.remove(index);
            }
        }
    }
}