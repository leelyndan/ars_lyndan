package com.ars.reservation.dao;

import java.util.List;

import com.ars.domain.Airline;
import com.ars.domain.Ticket;

public interface IReservationDao
{
    void saveTicket(Ticket ticket);

    List<Airline> selectAllAirlineInfos();
}
