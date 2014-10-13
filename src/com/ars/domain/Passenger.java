package com.ars.domain;

public enum Passenger
{
    ADULT("adult", 0), CHILD("child", 0);
    private final String type;
    private int number;

    Passenger(String type, int number)
    {
        this.type = type;
        this.number = number;
    }

    public int getNumber()
    {
        return number;
    }

    public void setNumber(int number)
    {
        this.number = number;
    }

    public String getType()
    {
        return type;
    }

}
