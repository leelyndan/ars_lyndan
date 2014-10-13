package com.ars.statistics.service;

import java.io.IOException;
import java.util.List;

import com.ars.common.util.IOUtils;
import com.ars.domain.FlightStaticsInfo;
import com.ars.menu.HomePage;
import com.ars.statistics.dao.FligtStatisticsDao;
import com.ars.statistics.dao.IFligtStatisticsDao;

public class FlightStatisticsProcess
{
    private static final String DATE_REGEX = "\\d{4}-([0][1-9]|[1][012])-([0-2][0-9]|3[12])";
    private static final String QUIT = "Q";
    private static final String PREVIOUS = "P";
    private IFligtStatisticsDao dao = new FligtStatisticsDao();

    public void statisticFlightInfo()
    {

        try
        {
            printProcessTip();
            inputStatisticDate();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    private void printProcessTip()
    {
        IOUtils.printTitle("Flight Statistics");
        IOUtils.println("Please, Input date for inquiring.");
        IOUtils.printControlMenu();
    }

    private void inputStatisticDate() throws IOException
    {
        IOUtils.inputTip();
        String input = IOUtils.inputString();

        if (PREVIOUS.equalsIgnoreCase(input))
        {
            HomePage.getInstance().initializeHome();
        }
        else if (QUIT.equalsIgnoreCase(input))
        {
            return;
        }
        else if (!input.matches(DATE_REGEX))
        {
            IOUtils.println("ERROR!!! You have to input date in right format.");
            inputStatisticDate();
        }
        else
        {

            List<FlightStaticsInfo> statisticInfos = dao
                    .statisticTicketByDate(input);

            if (statisticInfos == null || statisticInfos.isEmpty())
            {
                IOUtils.println("ERROR!!! there is no data.");
                statisticFlightInfo();
            }
            else
            {
                printStatisticsInfo(input, statisticInfos);
                IOUtils.readAnyKey();
                statisticFlightInfo();
            }
        }

    }

    private void printStatisticsInfo(String input,
            List<FlightStaticsInfo> statisticInfos)
    {
        IOUtils.printTitle("Flight Statistics");
        IOUtils.println("Please, Input any key to return to previous");
        IOUtils.println("");
        for (FlightStaticsInfo flightStaticsInfo : statisticInfos)
        {
            IOUtils.println(flightStaticsInfo.getFlight() + ","
                    + flightStaticsInfo.getDepartAirport() + ","
                    + flightStaticsInfo.getArrivalAirport() + ","
                    + flightStaticsInfo.getDepartTime() + " : "
                    + flightStaticsInfo.getAdultNum() + " Adult, "
                    + flightStaticsInfo.getChildNum() + " Child");
        }
        IOUtils.println("****************************************");
        IOUtils.println("Here is statistics on " + input);
    }

}
