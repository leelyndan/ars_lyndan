package com.ars.reservation.service;

import java.util.LinkedHashSet;
import java.util.Set;

import com.ars.common.util.IOUtils;
import com.ars.domain.Airline;
import com.ars.domain.Ticket;
import com.ars.menu.HomePage;

public class DepartAirportHandler extends Handler
{
   
    private Ticket ticket = new Ticket();
    private static DepartAirportHandler instance;

    private DepartAirportHandler()
    {
    }

    public static DepartAirportHandler getInstance()
    {

        if (instance == null)
        {
            instance = new DepartAirportHandler();

        }
        return instance;
    }

    public void showDepartAirport()
    {
        Set<String> departAirportSet = selectDepartAirport();
        inputDepartAirport(departAirportSet);
    }

    private Set<String> selectDepartAirport()
    {
        Set<String> departAirportSet = new LinkedHashSet<String>();
        for (Airline airlineInfo : airlineInfos)
        {
            departAirportSet.add(airlineInfo.getDepartAirport());
        }
        printTipInfo(departAirportSet, "Departure Airport");
        return departAirportSet;
    }

    private void inputDepartAirport(Set<String> departAirportSet)
    {
        IOUtils.inputTip();
        String input = IOUtils.inputString();
        if (input.equalsIgnoreCase(PREVIOUS))
        {
            HomePage.getInstance().initializeHome();
        }
        else if (input.equalsIgnoreCase(QUIT))
        {
            return;
        }
        if (!input.matches("\\d"))
        {
            println(ERROR_INFO);
            inputDepartAirport(departAirportSet);
        }
        else
        {
            ticket.setDepartAirport(getSetItem(departAirportSet,
                    Integer.parseInt(input)));
            this.setSuccessor(ArrivalAirportHandler.getInstance());
            return;
        }
    }

    @Override
    public void handleRequest(Ticket ticket)
    {
        this.ticket = ticket;
        showDepartAirport();
        this.successor.handleRequest(this.ticket);
    }
}
