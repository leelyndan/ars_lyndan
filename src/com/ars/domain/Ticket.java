package com.ars.domain;

import java.util.ArrayList;
import java.util.List;

import com.ars.discount.IStrategy;
import com.ars.discount.impl.NormalStrategy;

public class Ticket
{
    private String flight;
    private String departAirport;
    private String arrivalAirport;
    private String departDate;
    private String departTime;
    private List<Passenger> passengerList = new ArrayList<Passenger>();
    private String seatClass;
    private Double price;
    private Double payment;
    private Double change;
    private String reserveNumber;
    private Double promotionPrice;
    private IStrategy strategy = new NormalStrategy();
    private Double totalMoney;

    public IStrategy getStrategy()
    {
        return strategy;
    }

    public void setStrategy(IStrategy strategy)
    {
        this.strategy = strategy;
    }

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

    public List<Passenger> getPassengerList()
    {
        return passengerList;
    }

    public void setPassengerList(List<Passenger> passengerList)
    {
        this.passengerList = passengerList;
    }

    public String getSeatClass()
    {
        return seatClass;
    }

    public void setSeatClass(String seatClass)
    {
        this.seatClass = seatClass;
    }

    public Double calculateDiscount()
    {
        return this.strategy.calcPrice(this);
    }

    public Double getPrice()
    {
        return price;
    }

    public void setPrice(Double price)
    {
        this.price = price;
    }

    public Double getPayment()
    {
        return payment;
    }

    public void setPayment(Double payment)
    {
        this.payment = payment;
    }

    public Double getChange()
    {
        return change;
    }

    public void setChange(Double change)
    {
        this.change = change;
    }

    public String getReserveNumber()
    {
        return reserveNumber;
    }

    public void setReserveNumber(String reserveNumber)
    {
        this.reserveNumber = reserveNumber;
    }

    public Double getTotalMoney()
    {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney)
    {
        this.totalMoney = totalMoney;
    }

    public Double getPromotionPrice()
    {
        return promotionPrice;
    }

    public void setPromotionPrice(Double promotionPrice)
    {
        this.promotionPrice = promotionPrice;
    }

    public Double calculateNormalPrice()
    {
        Double price = getPrice();
        double adultTotal = getPassengerNumber(0) * price;
        double childrenTotal = getPassengerNumber(1) * (price / 2);
        double totalAmount = adultTotal + childrenTotal;
        return totalAmount;
    }

    private int getPassengerNumber(int type)
    {
        return getPassengerList().get(type).getNumber();
    }

    public String toString()
    {
        return getFlight() + "," + getDepartAirport() + ","
                + getArrivalAirport() + "," + getDepartDate() + ","
                + getDepartTime() + "," + getPassengerNumber(0) + ","
                + getPassengerNumber(1) + "," + getSeatClass() + ","
                + calculateNormalPrice().intValue() + ","
                + calculateDiscount().intValue() + ","
                + getPayment().intValue() + "," + getChange().intValue() + ","
                + getReserveNumber();
    }
}
