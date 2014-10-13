package com.ars.discount.impl;

import com.ars.discount.IStrategy;
import com.ars.domain.Ticket;

public class DepartAfterReserveStrategy implements IStrategy
{

    @Override
    public Double calcPrice(Ticket ticket)
    {
        return ticket.calculateNormalPrice() * (1 - 0.15);
    }

}
