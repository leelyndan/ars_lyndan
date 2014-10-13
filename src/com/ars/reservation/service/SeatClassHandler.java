package com.ars.reservation.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ars.common.util.IOUtils;
import com.ars.domain.Airline;
import com.ars.domain.Ticket;

public class SeatClassHandler extends Handler
{
    private static SeatClassHandler instance;
    private Ticket ticket = new Ticket();

    private SeatClassHandler()
    {
    }

    public static SeatClassHandler getInstance()
    {

        if (instance == null)
        {
            instance = new SeatClassHandler();

        }
        return instance;
    }

    @Override
    protected void handleRequest(Ticket ticket)
    {
        this.ticket = ticket;
        Airline selectedFlight = selectFlight(airlineInfos);
        selectSeatClass(selectedFlight);
        inputSeatClass(selectedFlight);
        this.successor.handleRequest(this.ticket);
    }

    private Airline selectFlight(List<Airline> airlineInfos)
    {
        for (Airline airlineInfo : airlineInfos)
        {
            if (airlineInfo.getDepartAirport()
                    .equals(ticket.getDepartAirport())
                    && airlineInfo.getArrivalAirport().equals(
                            ticket.getArrivalAirport())
                    && airlineInfo.getDepartTime().equals(
                            ticket.getDepartTime()))
            {
                this.ticket.setFlight(airlineInfo.getFlight());
                return airlineInfo;
            }
        }
        return new Airline();

    }

    private void selectSeatClass(Airline airlineInfos)
    {
        printTitle("Seat Class");
        println("1. First Class (" + airlineInfos.getFirstPrice() + " Yuan)");
        println("2. Business Class (" + airlineInfos.getBusinessPrice()
                + " Yuan)");
        println("3. Economy Class (" + airlineInfos.getEconomicPrice()
                + " Yuan)");
        IOUtils.printControlMenu();
    }

    private void inputSeatClass(Airline selectedFlight)
    {
        IOUtils.inputTip();
        String inputString = IOUtils.inputString();
        if (PREVIOUS.equals(inputString))
        {
            this.setSuccessor(PassengerHandler.getInstance());
            return;
        }
        else if (QUIT.equals(inputString))
        {
            return;
        }
        else
        {
            if (inputString.matches("[1-3]"))
            {
                Double price = getSeatPriceMap(selectedFlight).get(inputString);
                ticket.setPrice(price);
                ticket.setSeatClass(getSeatClassMap().get(inputString));
                this.setSuccessor(PaymentHandler.getInstance());
                return;
            }
            else
            {
                IOUtils.println(ERROR_INFO);
                inputSeatClass(selectedFlight);
            }
        }
    }

    private Map<String, String> getSeatClassMap()
    {
        Map<String, String> map = new HashMap<String, String>();
        map.put("1", "First Class");
        map.put("2", "Business Class");
        map.put("3", "Economy Class");
        return map;
    }

    private Map<String, Double> getSeatPriceMap(Airline selectedFlight)
    {
        Map<String, Double> map = new HashMap<String, Double>();
        map.put("1", selectedFlight.getFirstPrice());
        map.put("2", selectedFlight.getBusinessPrice());
        map.put("3", selectedFlight.getEconomicPrice());
        return map;
    }
}
