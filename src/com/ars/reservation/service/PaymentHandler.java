package com.ars.reservation.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.ars.common.util.DateUtils;
import com.ars.common.util.IOUtils;
import com.ars.common.util.StringUtils;
import com.ars.discount.impl.DepartAfterReserveStrategy;
import com.ars.discount.impl.MorningFlightStrategy;
import com.ars.domain.Ticket;

public class PaymentHandler extends CommonView
{
    private Ticket ticket = new Ticket();
    
    @Override
    protected void handleRequest(Ticket ticket)
    {
        this.ticket = ticket;
        ticketFeePayment();
        this.successor.handleRequest(this.ticket);
    }
    
    private void ticketFeePayment()
    {
        showTicketInfo();
        inputMoney();
    }
    
    private void inputMoney()
    {
        IOUtils.inputTip();
        String input = IOUtils.inputString();
        if (input.equalsIgnoreCase(PREVIOUS))
        {
            this.setSuccessor(new SeatClassHandler());
            return;
        }
        else if (input.equalsIgnoreCase(QUIT))
        {
            return;
        }
        else if (!input.matches("\\d{1,6}"))
        {
            println(ERROR_INFO);
            inputMoney();
        }
        else
        {
            Double money = StringUtils.parse2Double(input);
            if (money < calculatePromotionPrice())
            {
                println("ERROR!!! Not enough money.");
                inputMoney();
            }
            else
            {
                ticket.setPayment(money);
                ticket.setChange(money - calculatePromotionPrice());
                ticket.setReserveNumber(getReservationNumber());
                ticket.setTotalMoney(ticket.calculateNormalPrice());
                ticket.setPromotionPrice(calculatePromotionPrice());
                this.setSuccessor(new ReviewHandler());
                return;
            }
        }
    }
    
    private String getReservationNumber()
    {
        return ticket.getFlight() + StringUtils.getFirstChar(ticket.getDepartAirport())
            + StringUtils.getFirstChar(ticket.getArrivalAirport()) + DateUtils.currentDateString();
    }
    
    private void showTicketInfo()
    {
        printTitle("Payment");
        println("Please, Input money for payment.");
        println("");
        printTicketInfo();
        IOUtils.printControlMenu();
    }
    
    private void printTicketInfo()
    {
        println("Flight : " + ticket.getFlight());
        println("Departure Airport : " + ticket.getDepartAirport());
        println("Arrival Airport : " + ticket.getArrivalAirport());
        println("Depart Date : " + ticket.getDepartDate());
        println("Depart Time : " + ticket.getDepartTime());
        println("Passenger : " + getPassengerNumber(0) + " Adult, " + getPassengerNumber(1) + " Child");
        println("Seat Class : " + ticket.getSeatClass());
        println("Total Amount : " + ticket.calculateNormalPrice().intValue() + " Yuan");
        println("Promotion Price : " + calculatePromotionPrice().intValue() + " Yuan");
        println("");
    }
    
    private int getPassengerNumber(int type)
    {
        return ticket.getPassengerList().get(type).getNumber();
    }
    
    private Double calculatePromotionPrice()
    {
        List<Double> priceList = new ArrayList<Double>();
        priceList.add(calculateDepartAfterReservePrice());
        priceList.add(calculateMorningFlightPrice());
        priceList.add(calculateAdultMoreThan4Price());
        return Collections.min(priceList);
    }
    
    private Double calculateAdultMoreThan4Price()
    {
        if (ticket.getPassengerList().get(0).getNumber() >= 4)
        {
            this.ticket.setStrategy(new MorningFlightStrategy());
            return this.ticket.calculateDiscount();
        }
        return ticket.calculateNormalPrice();
    }
    
    private Double calculateMorningFlightPrice()
    {
        String departTime = ticket.getDepartTime();
        if (departTime.contains(":"))
        {
            if (Integer.parseInt(departTime.split(":")[0]) < 12)
            {
                this.ticket.setStrategy(new MorningFlightStrategy());
                return this.ticket.calculateDiscount();
            }
        }
        return ticket.calculateNormalPrice();
    }
    
    private Double calculateDepartAfterReservePrice()
    {
        Date departDate = DateUtils.parse2Date(ticket.getDepartDate());
        Date todayDate = DateUtils.currentDate();
        int monthDiff = DateUtils.getMonthDiff(todayDate, departDate);
        if (monthDiff >= 6)
        {
            this.ticket.setStrategy(new DepartAfterReserveStrategy());
            return this.ticket.calculateDiscount();
        }
        return ticket.calculateNormalPrice();
    }
}
