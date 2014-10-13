package com.ars.common.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ars.domain.Airline;
import com.ars.domain.Passenger;
import com.ars.domain.Ticket;

public class FileUtils
{
    public static String readFile(String fileName)
    {
        File file = new File(fileName);
        StringBuffer content = new StringBuffer();
        BufferedReader in = null;
        if (!file.exists())
        {
            System.err.println("Can't Find " + fileName);
        }

        try
        {
            in = new BufferedReader(new FileReader(file));
            String str;
            while ((str = in.readLine()) != null)
            {
                content.append(str + "\r\n");
            }

        }
        catch (IOException e)
        {
            try
            {
                in.close();
            }
            catch (Exception e2)
            {
            }
        }
        return content.toString();
    }

    public static void writeFile(String pathname, String content)
    {
        // String relativelyPath = System.getProperty("user.dir");
        File destFile = new File(pathname);
        if (!destFile.exists())
        {
            try
            {
                destFile.createNewFile();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        BufferedWriter output = null;
        try
        {
            output = new BufferedWriter(new FileWriter(destFile, true));
            output.write(content + "\r\n");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                output.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

    }

    public static List<Airline> readFlightInfos(String fileName)
    {
        List<Airline> airlineInfoList = new ArrayList<Airline>();

        File file = new File(fileName);
        BufferedReader in = null;
        if (!file.exists())
        {
            System.err.println("Can't Find " + fileName);
        }
        try
        {
            in = new BufferedReader(new FileReader(file));
            String str;
            while ((str = in.readLine()) != null)
            {
                if (str.contains(StringUtils.COMMA))
                {
                    constructAirlineInfo(airlineInfoList, str);
                }
            }

        }
        catch (IOException e)
        {
            try
            {
                in.close();
            }
            catch (Exception e2)
            {
            }
        }
        return airlineInfoList;
    }

    private static void constructAirlineInfo(List<Airline> airlineInfoList,
            String str)
    {
        Airline airlineInfo = new Airline();
        String[] split = str.split(StringUtils.COMMA);
        airlineInfo.setFlight(split[0]);
        airlineInfo.setDepartAirport(split[1]);
        airlineInfo.setArrivalAirport(split[2]);
        airlineInfo.setDepartTime(split[3]);
        airlineInfo.setArrivalTime(split[4]);
        airlineInfo.setFirstPrice(StringUtils.parse2Double(split[5]));
        airlineInfo.setBusinessPrice(StringUtils.parse2Double(split[6]));
        airlineInfo.setEconomicPrice(StringUtils.parse2Double(split[7]));
        airlineInfoList.add(airlineInfo);
    }

    public List<Ticket> queryTicketByReserveNumber(String fileName)
    {

        List<Ticket> ticketListList = new ArrayList<Ticket>();
        File file = new File(fileName);
        BufferedReader in = null;
        if (!file.exists())
        {
            System.err.println("Can't Find " + fileName);
        }
        try
        {
            in = new BufferedReader(new FileReader(file));
            String str;
            while ((str = in.readLine()) != null)
            {
                if (str.contains(StringUtils.COMMA))
                {
                    ticketListList.add(constructTicket(str));
                }
            }

        }
        catch (IOException e)
        {
            try
            {
                in.close();
            }
            catch (Exception e2)
            {
            }
        }
        return ticketListList;
    }

    private Ticket constructTicket(String str)
    {
        Ticket ticket = new Ticket();
        String[] infos = str.split(StringUtils.COMMA);
        ticket.setFlight(infos[0]);
        ticket.setDepartAirport(infos[1]);
        ticket.setArrivalAirport(infos[2]);
        ticket.setDepartDate(infos[3]);
        ticket.setDepartTime(infos[4]);
        Passenger adult = Passenger.ADULT;
        adult.setNumber(Integer.parseInt(infos[5]));
        Passenger child = Passenger.CHILD;
        child.setNumber(Integer.parseInt(infos[6]));
        ticket.getPassengerList().add(adult);
        ticket.getPassengerList().add(child);
        ticket.setSeatClass(infos[7]);
        ticket.setTotalMoney(Double.parseDouble(infos[8]));
        ticket.setPromotionPrice(Double.parseDouble(infos[9]));
        ticket.setPayment(Double.valueOf(infos[10]));
        ticket.setChange(Double.valueOf(infos[11]));
        ticket.setReserveNumber(infos[12]);
        return ticket;

    }

    public static void main(String[] args)
    {
        // System.out.println(readFile("plane.properties"));
        writeFile("reservation_history.dat", "a");
        System.out.println("12:24".matches("([01])?[1-2]:\\d{2}"));
    }

}
