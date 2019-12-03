package com.rotem.fbairlines_project;

import android.app.Application;

import com.google.firebase.FirebaseApp;

/**
 * Created by eti52 on 09/08/2017.
 */

public class MyApplication extends Application
{
    public void onCreate()
    {
        super.onCreate();
        FirebaseApp.initializeApp(this);
    }
}