package com.ars.reservation.service;

import com.ars.common.util.IOUtils;
import com.ars.domain.Ticket;
import com.ars.menu.HomePage;

public class ReviewHandler extends Handler
{
    private static ReviewHandler instance;
    private Ticket ticket = new Ticket();

    private ReviewHandler()
    {
    }

    public static ReviewHandler getInstance()
    {

        if (instance == null)
        {
            instance = new ReviewHandler();

        }
        return instance;
    }

    @Override
    protected void handleRequest(Ticket ticket)
    {
        this.ticket = ticket;
        showBillContent();
        saveTicket();
        acceptAnyInput();
        HomePage.getInstance().initializeHome();
    }

    private void showBillContent()
    {
        printTitle("Review");
        println("Please, Input any key.");
        println("");
        printTicketInfo();
        println("Payment : " + ticket.getPayment().intValue() + " Yuan");
        println("Change : " + ticket.getChange().intValue() + " Yuan");
        println("Reservation Number : " + ticket.getReserveNumber());
        println("****************************************");
        println("Thank you for reservation!");
    }

    private void saveTicket()
    {
        dao.saveTicket(ticket);
    }

    private void acceptAnyInput()
    {
        IOUtils.readAnyKey();
        HomePage.getInstance().initializeHome();
    }

    private void printTicketInfo()
    {
        println("Flight : " + ticket.getFlight());
        println("Departure Airport : " + ticket.getDepartAirport());
        println("Arrival Airport : " + ticket.getArrivalAirport());
        println("Depart Date : " + ticket.getDepartDate());
        println("Depart Time : " + ticket.getDepartTime());
        println("Passenger : " + getPassengerNumber(0) + " Adult, "
                + getPassengerNumber(1) + " Child");
        println("Seat Class : " + ticket.getSeatClass());
        println("Total Amount : " + ticket.calculateNormalPrice().intValue()
                + " Yuan");
        println("Promotion Price : " + ticket.getPromotionPrice().intValue()
                + " Yuan");
        println("");
    }

    private int getPassengerNumber(int type)
    {
        return ticket.getPassengerList().get(type).getNumber();
    }
}
