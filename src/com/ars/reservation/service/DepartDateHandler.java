package com.ars.reservation.service;

import java.util.Date;

import com.ars.common.util.DateUtils;
import com.ars.common.util.IOUtils;
import com.ars.domain.Ticket;

public class DepartDateHandler extends CommonView
{
    private Ticket ticket = new Ticket();
    
    @Override
    protected void handleRequest(Ticket ticket)
    {
        this.ticket = ticket;
        selectDepartDate();
        this.successor.handleRequest(this.ticket);
    }
    
    protected void selectDepartDate()
    {
        inputDepartDateTip();
        inputDepartDate();
    }
    
    private void inputDepartDateTip()
    {
        printTitle("Depart Date");
        println("Please, Input depart date. (YYYY-MM-DD)");
        IOUtils.printControlMenu();
    }
    
    private void inputDepartDate()
    {
        IOUtils.inputTip();
        String inputedDate = IOUtils.inputString();
        if (PREVIOUS.equalsIgnoreCase(inputedDate))
        {
            this.setSuccessor(new ArrivalAirportHandler());
        }
        else if (QUIT.equalsIgnoreCase(inputedDate))
        {
            return;
        }
        else if (inputedDate.matches("\\d{4}-([0][1-9]|[1][012])-([0-2][0-9]|3[12])"))
        {
            
            Date departDate = DateUtils.parse2Date(inputedDate);
            Date tomorrowDate = DateUtils.addDate(0, 0, 1);
            Date maxDate = DateUtils.addDate(1, 0, 0);
            if (departDate.after(maxDate) || departDate.before(tomorrowDate))
            {
                println("ERROR!!! You can input date from " + DateUtils.parse2String(tomorrowDate) + " to "
                    + DateUtils.parse2String(maxDate) + ")");
                inputDepartDate();
            }
            ticket.setDepartDate(inputedDate);
            this.setSuccessor(new DepartTimeHandler());
        }
        else
        {
            println("ERROR!!! You have to input date in right format.");
            inputDepartDate();
        }
    }
}
