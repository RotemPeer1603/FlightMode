package com.rotem.fbairlines_project;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FlightsDataAdapter extends BaseAdapter
{
    List<LandingData> lstItem;

    public FlightsDataAdapter()
    {
        lstItem = new ArrayList<>();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        try
        {
            LandingData item = getItem(position);
            if (view == null)
            {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.landing_item, parent, false);
            }


            TextView lblAppTime = view.findViewById(R.id.lblAppTime);
            TextView lblCity = view.findViewById(R.id.lblCity);
            TextView lblCompanyName = view.findViewById(R.id.lblCompanyName);
            TextView lblFlightNum = view.findViewById(R.id.lblFlightNum);
            TextView lblLanded = view.findViewById(R.id.lblLanded);
            TextView lblFinalTime = view.findViewById(R.id.lblFinalTime);

            lblAppTime.setText(item.getAppTime());
            lblCity.setText(item.getCity());
            lblCompanyName.setText(item.getCompanyName());
            lblFlightNum.setText(item.getNumber());

            String finalTime = item.getLandingTime();
            if (finalTime != null)
            {
                lblFinalTime.setText(finalTime);
                lblLanded.setVisibility(View.VISIBLE);
                lblFinalTime.setVisibility(View.VISIBLE);
            }
            else
            {
                lblLanded.setVisibility(View.INVISIBLE);
                lblFinalTime.setVisibility(View.INVISIBLE);
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return view;
    }

    @Override
    public int getCount()
    {
        return lstItem.size();
    }

    @Override
    public LandingData getItem(int pos)
    {
        return lstItem.get(pos);
    }

    @Override
    public long getItemId(int pos)
    {
        return (long) pos;
    }

    public void updateList(List<LandingData> lstItem)
    {
        this.lstItem.clear();
        this.lstItem.addAll(lstItem);
    }
}