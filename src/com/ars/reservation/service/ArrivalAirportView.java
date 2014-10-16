package com.ars.reservation.service;

import java.util.LinkedHashSet;
import java.util.Set;

import com.ars.common.util.IOUtils;
import com.ars.domain.Airline;
import com.ars.domain.Ticket;

public class ArrivalAirportView extends CommonView
{
    private Ticket ticket = new Ticket();
    
    public ArrivalAirportView()
    {
    }
    
    @Override
    protected void handleRequest(Ticket ticket)
    {
        this.ticket = ticket;
        Set<String> arrivalAirportSet = selectArrivalAirport();
        inputArrivalAirport(arrivalAirportSet);
        this.successor.handleRequest(this.ticket);
    }
    
    private Set<String> selectArrivalAirport()
    {
        Set<String> arrivalAirportSet = new LinkedHashSet<String>();
        for (Airline airlineInfo : airlineInfos)
        {
            arrivalAirportSet.add(airlineInfo.getArrivalAirport());
        }
        printTipInfo(arrivalAirportSet, "Arrival Airport");
        
        return arrivalAirportSet;
    }
    
    private void inputArrivalAirport(Set<String> arrivalAirportSet)
    {
        IOUtils.inputTip();
        String input = IOUtils.inputString();
        if (input.equalsIgnoreCase(PREVIOUS))
        {
            this.setSuccessor(DepartAirportView.getInstance());
            return;
        }
        else if (input.equalsIgnoreCase(QUIT))
        {
            return;
        }
        if (!input.matches("\\d"))
        {
            println("ERROR!!! Wrong Input. Select Again");
            inputArrivalAirport(arrivalAirportSet);
        }
        else
        {
            String setItem = getSetItem(arrivalAirportSet, Integer.parseInt(input));
            if (setItem.equals(ticket.getDepartAirport()))
            {
                println("ERROR!!! Can��t select the same airport as Departure.");
                inputArrivalAirport(arrivalAirportSet);
            }
            ticket.setArrivalAirport(setItem);
            this.setSuccessor(new DepartDateView());
            return;
        }
    }
}
