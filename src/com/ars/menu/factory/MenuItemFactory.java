package com.ars.menu.factory;

import com.ars.menu.IMenuItem;
import com.ars.menu.impl.CheckReservationMenuItem;
import com.ars.menu.impl.FlightStatisticsMenuItem;
import com.ars.menu.impl.QuitMenuItem;
import com.ars.menu.impl.ReservationMenuItem;

public class MenuItemFactory
{
    
    private static MenuItemFactory instance;
    
    public static MenuItemFactory getInstance()
    {
        
        if (instance == null)
        {
            instance = new MenuItemFactory();
            
        }
        return instance;
    }
    
    private MenuItemFactory()
    {
    }
    
    public IMenuItem createMenuItem(String index)
    {
        if ("1".equals(index))
        {
            return new ReservationMenuItem();
        }
        else if ("2".equals(index))
        {
            return new CheckReservationMenuItem();
        }
        else if ("3".equals(index))
        {
            return new FlightStatisticsMenuItem();
        }
        return new QuitMenuItem();
    }
}
