package com.ars.domain;

public class Airline
{
    private String flight;
    private String departAirport;
    private String arrivalAirport;
    private String departTime;
    private String arrivalTime;
    private Double firstPrice;
    private Double businessPrice;
    private Double economicPrice;

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

    public String getDepartTime()
    {
        return departTime;
    }

    public void setDepartTime(String departTime)
    {
        this.departTime = departTime;
    }

    public String getArrivalTime()
    {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime)
    {
        this.arrivalTime = arrivalTime;
    }

    public Double getFirstPrice()
    {
        return firstPrice;
    }

    public void setFirstPrice(Double firstPrice)
    {
        this.firstPrice = firstPrice;
    }

    public Double getBusinessPrice()
    {
        return businessPrice;
    }

    public void setBusinessPrice(Double businessPrice)
    {
        this.businessPrice = businessPrice;
    }

    public Double getEconomicPrice()
    {
        return economicPrice;
    }

    public void setEconomicPrice(Double economicPrice)
    {
        this.economicPrice = economicPrice;
    }

}
