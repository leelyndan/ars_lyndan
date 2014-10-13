package com.ars.reservation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ars.common.dao.BaseDao;
import com.ars.common.util.StringUtils;
import com.ars.domain.Airline;
import com.ars.domain.Ticket;

public class ReservationDao extends BaseDao implements IReservationDao
{
    @Override
    public void saveTicket(Ticket ticket)
    {

        PreparedStatement pstmt = null;
        Connection cn = null;
        try
        {
            cn = getConnection();
            String sql = "insert into ticket(flight,departAirport,arrivalAirport,departDate,departTime,adultNum,childNum,seatClass,totalMoney,promotionMoney,payment,changes,reserveNumber) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            pstmt = cn.prepareStatement(sql);

            pstmt.setString(1, ticket.getFlight());
            pstmt.setString(2, ticket.getDepartAirport());
            pstmt.setString(3, ticket.getArrivalAirport());
            pstmt.setString(4, ticket.getDepartDate());
            pstmt.setString(5, ticket.getDepartTime());
            pstmt.setString(6, String.valueOf(ticket.getPassengerList().get(0)
                    .getNumber()));
            pstmt.setString(7, String.valueOf(ticket.getPassengerList().get(1)
                    .getNumber()));
            pstmt.setString(8, ticket.getSeatClass());
            pstmt.setString(9, String.valueOf(ticket.getTotalMoney()));
            pstmt.setString(10, String.valueOf(ticket.getPromotionPrice()));
            pstmt.setString(11, String.valueOf(ticket.getPayment()));
            pstmt.setString(12, String.valueOf(ticket.getChange()));
            pstmt.setString(13, String.valueOf(ticket.getReserveNumber()));
            pstmt.executeUpdate();

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public List<Airline> selectAllAirlineInfos()
    {

        List<Airline> airlineInfoList = new ArrayList<Airline>();
        PreparedStatement pstmt = null;
        Connection cn = null;
        ResultSet rs = null;
        try
        {
            cn = getConnection();
            String sql = "select flight,departAirport,arrivalAirport,departTime,arrivalTime,firstPrice,businessPrice,economicPrice from flight";
            pstmt = cn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next())
            {
                Airline airlineInfo = constructAirline(rs);
                airlineInfoList.add(airlineInfo);
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

        return airlineInfoList;
    }

    private Airline constructAirline(ResultSet rs) throws SQLException
    {
        Airline airlineInfo = new Airline();
        airlineInfo.setFlight(rs.getString("flight"));
        airlineInfo.setDepartAirport(rs.getString("departAirport"));
        airlineInfo.setArrivalAirport(rs.getString("arrivalAirport"));
        airlineInfo.setDepartTime(rs.getString("departTime"));
        airlineInfo.setArrivalTime(rs.getString("arrivalTime"));
        airlineInfo.setFirstPrice(StringUtils.parse2Double(rs
                .getString("firstPrice")));
        airlineInfo.setBusinessPrice(StringUtils.parse2Double(rs
                .getString("businessPrice")));
        airlineInfo.setEconomicPrice(StringUtils.parse2Double(rs
                .getString("economicPrice")));
        return airlineInfo;
    }
}
