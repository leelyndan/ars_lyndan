package com.ars.statistics.dao;

import java.util.List;

import com.ars.domain.FlightStaticsInfo;

public interface IFligtStatisticsDao
{
    List<FlightStaticsInfo> statisticTicketByDate(String date);
}
