package com.ars.reservation.service;

import java.util.List;

import com.ars.common.util.IOUtils;
import com.ars.domain.Airline;
import com.ars.domain.Passenger;
import com.ars.domain.Ticket;

public class PassengerView extends CommonView
{
    private static final String ADULT = "Adult";
    
    private static final String CHILD = "Child";
    
    private Ticket ticket = new Ticket();
    
    @Override
    protected void handleRequest(Ticket ticket)
    {
        this.ticket = ticket;
        selectPassengers();
        this.successor.handleRequest(this.ticket);
    }
    
    private void selectPassengers()
    {
        showPassenger(ADULT);
        inputPassenger(airlineInfos, ADULT);
        showPassenger(CHILD);
        inputPassenger(airlineInfos, CHILD);
    }
    
    private void showPassenger(String type)
    {
        printTitle("Passenger");
        println("Please, Input the number of " + type + ".(0 ~ 10)");
        IOUtils.printControlMenu();
        
    }
    
    private void inputPassenger(List<Airline> airlineInfos, String type)
    {
        IOUtils.inputTip();
        String input = IOUtils.inputString();
        if (PREVIOUS.equalsIgnoreCase(input))
        {
            this.setSuccessor(new DepartTimeView());
        }
        else if (QUIT.equalsIgnoreCase(input))
        {
            return;
        }
        else if (!input.matches("[0-9]|10"))
        {
            println("Error!!! Input the number of " + type + "(0~10)");
            inputPassenger(airlineInfos, type);
        }
        else
        {
            addPassenger(type, input);
            checkPassengerNumber(airlineInfos, type);
        }
        
    }
    
    private void addPassenger(String type, String input)
    {
        if (ADULT.equals(type))
        {
            Passenger.ADULT.setNumber(Integer.parseInt(input));
            ticket.getPassengerList().add(Passenger.ADULT);
        }
        else
        {
            Passenger.CHILD.setNumber(Integer.parseInt(input));
            ticket.getPassengerList().add(Passenger.CHILD);
        }
    }
    
    private void checkPassengerNumber(List<Airline> airlineInfos, String type)
    {
        
        if (CHILD.equals(type))
        {
            if (calculatePassengerAmount() <= 0)
            {
                println("ERROR!!! You have to input more than 0.");
                selectPassengers();
            }
            this.setSuccessor(new SeatClassView());
        }
    }
    
    private int calculatePassengerAmount()
    {
        return getPassengerNumber(0) + getPassengerNumber(1);
    }
    
    private int getPassengerNumber(int type)
    {
        return ticket.getPassengerList().get(type).getNumber();
    }
}
