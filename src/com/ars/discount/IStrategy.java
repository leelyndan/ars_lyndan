package com.ars.discount;

import com.ars.domain.Ticket;

public interface IStrategy
{
    Double calcPrice(Ticket ticket);
}
