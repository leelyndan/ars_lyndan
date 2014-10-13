package com.ars.menu.impl;

import com.ars.checkreservation.service.CheckReservationProcess;
import com.ars.menu.IMenuItem;

public class CheckReservationMenuItem implements IMenuItem
{

    @Override
    public void initilize()
    {

        new CheckReservationProcess().checkReservation();
    }

}
