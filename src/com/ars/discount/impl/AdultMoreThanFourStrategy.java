package com.ars.discount.impl;

import com.ars.discount.IStrategy;
import com.ars.domain.Passenger;
import com.ars.domain.Ticket;

public class AdultMoreThanFourStrategy implements IStrategy
{

    @Override
    public Double calcPrice(Ticket ticket)
    {
        Passenger adult = ticket.getPassengerList().get(0);
        Passenger children = ticket.getPassengerList().get(1);
        Double price = ticket.getPrice();
        return adult.getNumber() * price * (1 - 0.1) + children.getNumber()
                * (price / 2);
    }
}
