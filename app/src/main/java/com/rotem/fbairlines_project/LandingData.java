package com.rotem.fbairlines_project;

import java.io.Serializable;

public class LandingData implements Serializable
{

    public String appTime;
    public String city;
    public String landingTime;
    public String companyName;
    public String number;

    public LandingData()
    {

    }

    public String getAppTime()
    {
        return appTime;
    }

    public String getCity()
    {
        return city;
    }

    public String getNumber()
    {
        return number;
    }


    public String getCompanyName()
    {
        return companyName;
    }

    public String getLandingTime()
    {
        return landingTime;
    }
}
