package com.ars.checkreservation.service;

import com.ars.checkreservation.dao.CheckReservationDao;
import com.ars.checkreservation.dao.ICheckReservationDao;
import com.ars.common.util.IOUtils;
import com.ars.domain.Ticket;
import com.ars.menu.HomePage;

public class CheckReservationProcess
{
    private static final String QUIT = "Q";
    private static final String PREVIOUS = "P";

    public void checkReservation()
    {
        checkTip();
        inputReservationNumber();
    }

    private void inputReservationNumber()
    {
        IOUtils.inputTip();
        String input = IOUtils.inputString();

        if (PREVIOUS.equalsIgnoreCase(input))
        {
            HomePage.getInstance().initializeHome();

        }
        else if (QUIT.equalsIgnoreCase(input))
        {
            return;
        }
        else
        {

            Ticket ticket = findTicketInfoByReserveNum(input);
            if (ticket.getReserveNumber() != null)
            {
                showBillContent(ticket);
                inputItem();
            }
            else
            {
                System.out.println("ERROR!!! There is no such data.");
                inputReservationNumber();
            }
        }
    }

    private void inputItem()
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

    }

    private void showBillContent(Ticket ticket)
    {
        IOUtils.printTitle("Review");
        println("");
        println("Flight : " + ticket.getFlight());
        println("Departure Airport : " + ticket.getDepartAirport());
        println("Arrival Airport : " + ticket.getArrivalAirport());
        println("Depart Date : " + ticket.getDepartDate());
        println("Depart Time : " + ticket.getDepartTime());
        println("Passenger : " + getPassengerNumber(ticket, 0) + " Adult, "
                + getPassengerNumber(ticket, 1) + " Child");
        println("Seat Class : " + ticket.getSeatClass());
        println("Total Amount : " + ticket.getTotalMoney().intValue() + " Yuan");
        println("Promotion Price : " + ticket.getPromotionPrice().intValue()
                + " Yuan");
        println("Payment : " + ticket.getPayment().intValue() + " Yuan");
        println("Change : " + ticket.getChange().intValue() + " Yuan");
        println("Reservation Number : " + ticket.getReserveNumber());
        IOUtils.printControlMenu();
    }

    private int getPassengerNumber(Ticket ticket, int type)
    {
        return ticket.getPassengerList().get(type).getNumber();
    }

    private void println(String str)
    {
        IOUtils.println(str);
    }

    private Ticket findTicketInfoByReserveNum(String input)
    {
        ICheckReservationDao dao = new CheckReservationDao();
        return dao.selectTicketByReserveNumber(input);

    }

    private void checkTip()
    {
        IOUtils.println("****************************************");
        IOUtils.println("Check Reservation");
        IOUtils.println("****************************************");
        IOUtils.println("Pease, Input reservation number.");
        IOUtils.println("");
        IOUtils.printControlMenu();

    }
}
