package com.ars.menu;

import java.util.Arrays;
import java.util.List;

import com.ars.common.util.IOUtils;
import com.ars.menu.factory.MenuItemFactory;

public class HomePage
{

    private static HomePage instance;

    private HomePage()
    {
    }

    public static HomePage getInstance()
    {

        if (instance == null)
        {
            instance = new HomePage();

        }
        return instance;
    }

    public void initializeHome()
    {

        printHomeTipInfo();
        String input = inputMenuItem();
        MenuItemFactory.getInstance().createMenuItem(input).initilize();
    }

    private String inputMenuItem()
    {
        IOUtils.inputTip();
        List<String> itemList = Arrays.asList(new String[]
        { "1", "2", "3", "Q" });
        String input = IOUtils.inputString();
        if (!itemList.contains(input))
        {
            System.out.println("ERROR!!! Wrong Input. Select Again");
            inputMenuItem();
        }
        return input;
    }

    private void printHomeTipInfo()
    {
        IOUtils.printTitle("Welcome to Xi'an Airline ");
        System.out.println("1. Reservation");
        System.out.println("2. Check Reservation");
        IOUtils.println("3. Flight Statistics");
        System.out.println("Q. Quit");
        System.out.println("*************************************");
    }

}
