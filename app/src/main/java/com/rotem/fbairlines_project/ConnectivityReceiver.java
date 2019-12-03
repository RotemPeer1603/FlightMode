package com.rotem.fbairlines_project;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.widget.Toast;

/**
 * Created by Itay kan on 9/16/2019.
 */

public class ConnectivityReceiver  extends BroadcastReceiver {

    private MainActivity _ma;
    public ConnectivityReceiver(MainActivity ma)
    {
        _ma = ma;
    }
    @Override
    public void onReceive(final Context context, final Intent intent) {

        Toast.makeText(context, "Flight mode Changed!", Toast.LENGTH_SHORT).show();
        boolean isAirplaneModeOn = intent.getBooleanExtra("state", false);
        if(isAirplaneModeOn){

            // handle Airplane Mode on
            _ma.listView.setAlpha(0.75f);
            _ma.listView.setBackgroundColor(Color.GRAY);
            _ma.listView.setEnabled(false);
        } else {
            // handle Airplane Mode off
            _ma.listView.setAlpha(1f);
            _ma.listView.setBackgroundColor(Color.WHITE);
            _ma.listView.setEnabled(true);
        }




    }
}