package com.ars.domain;

public class FlightStaticsInfo
{
    private String flight;
    private String departAirport;
    private String arrivalAirport;
    private String departDate;
    private String departTime;
    private int adultNum;
    private int childNum;

    public String getFlight()
    {
        return flight;
    }

    public void setFlight(String flight)
    {
        this.flight = flight;
    }

    public String getDepartAirport()
    {
        return departAirport;
    }

    public void setDepartAirport(String departAirport)
    {
        this.departAirport = departAirport;
    }

    public String getArrivalAirport()
    {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport)
    {
        this.arrivalAirport = arrivalAirport;
    }

    public String getDepartDate()
    {
        return departDate;
    }

    public void setDepartDate(String departDate)
    {
        this.departDate = departDate;
    }

    public String getDepartTime()
    {
        return departTime;
    }

    public void setDepartTime(String departTime)
    {
        this.departTime = departTime;
    }

    public int getAdultNum()
    {
        return adultNum;
    }

    public void setAdultNum(int adultNum)
    {
        this.adultNum = adultNum;
    }

    public int getChildNum()
    {
        return childNum;
    }

    public void setChildNum(int childNum)
    {
        this.childNum = childNum;
    }

}
