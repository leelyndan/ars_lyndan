package com.ars.reservation.service;

import java.util.LinkedHashSet;
import java.util.Set;

import com.ars.common.util.IOUtils;
import com.ars.domain.Airline;
import com.ars.domain.Ticket;

public class DepartTimeHandler extends Handler
{
    private static DepartTimeHandler instance;
    private Ticket ticket = new Ticket();

    private DepartTimeHandler()
    {

    }

    public static DepartTimeHandler getInstance()
    {

        if (instance == null)
        {
            instance = new DepartTimeHandler();

        }
        return instance;
    }

    @Override
    protected void handleRequest(Ticket ticket)
    {
        this.ticket = ticket;
        Set<String> departTimeSet = selectDepartTime();
        inputDepartTime(departTimeSet);
        this.successor.handleRequest(this.ticket);
    }

    private Set<String> selectDepartTime()
    {
        Set<String> arrivalAirportSet = new LinkedHashSet<String>();
        for (Airline airlineInfo : airlineInfos)
        {
            if (airlineInfo.getDepartAirport()
                    .equals(ticket.getDepartAirport())
                    && airlineInfo.getArrivalAirport().equals(
                            ticket.getArrivalAirport()))
            {
                arrivalAirportSet.add(airlineInfo.getDepartTime());
            }
        }
        printTipInfo(arrivalAirportSet, "Depart Time");
        return arrivalAirportSet;
    }

    private void inputDepartTime(Set<String> departTimeSet)
    {
        IOUtils.inputTip();
        String input = IOUtils.inputString();
        if (input.equalsIgnoreCase(PREVIOUS))
        {
            this.setSuccessor(DepartDateHandler.getInstance());
            return;
        }
        else if (input.equalsIgnoreCase(QUIT))
        {
            return;
        }
        if (!input.matches("\\d"))
        {
            println("ERROR!!! Wrong Input. Select Again");
            inputDepartTime(departTimeSet);
        }
        else
        {
            String setItem = getSetItem(departTimeSet, Integer.parseInt(input));
            if (setItem.equals(ticket.getDepartAirport()))
            {
                println("ERROR!!! Can¡¯t select the same airport as Departure.");
                inputDepartTime(departTimeSet);
            }
            ticket.setDepartTime(setItem);
            this.setSuccessor(PassengerHandler.getInstance());
            return;
        }
    }
}
