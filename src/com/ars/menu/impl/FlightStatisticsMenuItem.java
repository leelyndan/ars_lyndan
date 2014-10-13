package com.ars.menu.impl;

import com.ars.menu.IMenuItem;
import com.ars.statistics.service.FlightStatisticsProcess;

public class FlightStatisticsMenuItem implements IMenuItem
{

    @Override
    public void initilize()
    {
        new FlightStatisticsProcess().statisticFlightInfo();

    }
}
