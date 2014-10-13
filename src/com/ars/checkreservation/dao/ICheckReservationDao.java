package com.ars.checkreservation.dao;

import com.ars.domain.Ticket;

public interface ICheckReservationDao
{
    Ticket selectTicketByReserveNumber(String reserveNumber);
}
