package com.rotem.fbairlines_project;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class SettingsActivity extends AppCompatActivity
{
    Spinner spinShowLanded;
    Spinner spinShowOnAir;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        spinShowLanded = findViewById(R.id.spinShowLanded);
        spinShowOnAir = findViewById(R.id.spinShowOnAir);
        Button btnSave = findViewById(R.id.btnSave);

        List<String> arrayList = new ArrayList<>();
        arrayList.add("לא");
        arrayList.add("כן");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinShowLanded.setAdapter(arrayAdapter);
        spinShowOnAir.setAdapter(arrayAdapter);

        if (MainActivity.showOnlyLanded)
        {
            spinShowLanded.setSelection(1);
        }
        else
        {
            spinShowLanded.setSelection(0);
        }

        if (MainActivity.showOnlyOnAir)
        {
            spinShowOnAir.setSelection(1);
        }
        else
        {
            spinShowOnAir.setSelection(0);
        }


        btnSave.setOnClickListener(new SaveSettingsListener());

    }


    private class SaveSettingsListener implements View.OnClickListener
    {

        @Override
        public void onClick(View v)
        {
            try
            {
                int showLandedPosition = spinShowLanded.getSelectedItemPosition();
                int showOnAirPosition = spinShowOnAir.getSelectedItemPosition();
                if (showLandedPosition == 1)
                {
                    MainActivity.showOnlyLanded = true;
                }
                else
                {
                    MainActivity.showOnlyLanded = false;
                }

                if (showOnAirPosition == 1)
                {
                    MainActivity.showOnlyOnAir = true;
                }
                else
                {
                    MainActivity.showOnlyOnAir = false;
                }
                finish();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

}
