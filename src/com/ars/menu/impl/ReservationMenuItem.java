package com.ars.menu.impl;

import com.ars.domain.Ticket;
import com.ars.menu.IMenuItem;
import com.ars.reservation.service.DepartAirportView;

public class ReservationMenuItem implements IMenuItem
{

    @Override
    public void initilize()
    {
        // new ReservationProcess_backup().reserveTicket();
        DepartAirportView.getInstance().handleRequest(new Ticket());
    }

}
