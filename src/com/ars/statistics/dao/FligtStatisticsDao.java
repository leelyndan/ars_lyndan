package com.ars.statistics.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ars.common.dao.BaseDao;
import com.ars.domain.FlightStaticsInfo;

public class FligtStatisticsDao extends BaseDao implements
        IFligtStatisticsDao
{
    @Override
    public List<FlightStaticsInfo> statisticTicketByDate(String date)
    {
        List<FlightStaticsInfo> ticketList = new ArrayList<FlightStaticsInfo>();
        PreparedStatement pstmt = null;
        Connection cn = null;
        ResultSet rs = null;
        try
        {
            cn = getConnection();
            String sql = "select flight,departAirport,arrivalAirport,departDate,departTime,sum(adultNum) as adultNum ,sum(childNum) as childNum "
                    + "from ticket "
                    + "where departDate=? "
                    + "group by flight,departAirport,arrivalAirport,departTime";
            pstmt = cn.prepareStatement(sql);
            pstmt.setString(1, date);
            rs = pstmt.executeQuery();
            while (rs.next())
            {
                FlightStaticsInfo ticket = constructFlightStaticsInfo(rs);
                ticketList.add(ticket);
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                rs.close();
                pstmt.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }

        }

        return ticketList;
    }

    private FlightStaticsInfo constructFlightStaticsInfo(ResultSet rs)
            throws SQLException
    {
        FlightStaticsInfo statisticInfo = new FlightStaticsInfo();
        statisticInfo.setFlight(rs.getString("flight"));
        statisticInfo.setDepartAirport(rs.getString("departAirport"));
        statisticInfo.setArrivalAirport(rs.getString("arrivalAirport"));
        statisticInfo.setDepartTime(rs.getString("departTime"));
        statisticInfo.setAdultNum(rs.getInt("adultNum"));
        statisticInfo.setChildNum(rs.getInt("childNum"));
        return statisticInfo;
    }
}
