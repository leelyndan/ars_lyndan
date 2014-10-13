package com.ars.reservation.service;

import java.util.List;
import java.util.Set;

import com.ars.common.util.IOUtils;
import com.ars.domain.Airline;
import com.ars.domain.Ticket;
import com.ars.reservation.dao.IReservationDao;
import com.ars.reservation.dao.ReservationDao;

public abstract class Handler
{
    protected static final String QUIT = "Q";
    protected static final String PREVIOUS = "P";
    protected Handler successor;
    protected IReservationDao dao = new ReservationDao();
    protected static final String ERROR_INFO = "ERROR!!! Wrong Input. Select Again";
    protected static List<Airline> airlineInfos;
    {
        airlineInfos = intializeDataSource();
    }

    private List<Airline> intializeDataSource()
    {

        return dao.selectAllAirlineInfos();
    }

    public void setSuccessor(Handler successor)
    {

        this.successor = successor;

    }

    /**
     * 
     * 示意处理请求的方法，虽然这个示意方法是没有传入参数，
     * 
     * 但实际是可以传入参数的，根据具体需要来选择是否传递参数
     */
    protected void printTitle(String title)
    {
        IOUtils.printTitle(title);
    }

    protected void println(String str)
    {
        IOUtils.println(str);
    }

    protected String getSetItem(Set<String> set, int index)
    {
        int temp = 0;
        for (String string : set)
        {
            if (temp == (index - 1))
            {
                return string;
            }
            temp++;

        }
        return "";
    }

    protected void printTipInfo(Set<String> departAirportSet, String title)
    {
        printTitle(title);
        int index = 1;
        for (String airport : departAirportSet)
        {
            println(index + "." + airport);
            index++;
        }
        IOUtils.printControlMenu();
    }

    protected abstract void handleRequest(Ticket ticket);
}
