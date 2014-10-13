package com.ars.checkreservation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ars.common.dao.BaseDao;
import com.ars.domain.Passenger;
import com.ars.domain.Ticket;

public class CheckReservationDao extends BaseDao implements ICheckReservationDao
{
    @Override
    public Ticket selectTicketByReserveNumber(String reserveNumber)
    {
        Ticket ticket = new Ticket();
        PreparedStatement pstmt = null;
        Connection cn = null;
        ResultSet rs = null;
        try
        {
            cn = getConnection();
            String sql = "select flight,departAirport,arrivalAirport,departDate,departTime,adultNum,childNum,seatClass,totalMoney,promotionMoney,payment,changes,reserveNumber from ticket where reserveNumber=?";
            pstmt = cn.prepareStatement(sql);
            pstmt.setString(1, reserveNumber);
            rs = pstmt.executeQuery();
            while (rs.next())
            {
                ticket = constructTicket(rs);
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

        return ticket;
    }

    private Ticket constructTicket(ResultSet rs) throws SQLException
    {
        Ticket ticket = new Ticket();
        ticket.setFlight(rs.getString("flight"));
        ticket.setDepartAirport(rs.getString("departAirport"));
        ticket.setArrivalAirport(rs.getString("arrivalAirport"));
        ticket.setDepartDate(rs.getString("departDate"));
        ticket.setDepartTime(rs.getString("departTime"));
        Passenger adult = Passenger.ADULT;
        adult.setNumber(Integer.parseInt(rs.getString("adultNum")));
        Passenger child = Passenger.CHILD;
        child.setNumber(Integer.parseInt(rs.getString("childNum")));
        ticket.getPassengerList().add(adult);
        ticket.getPassengerList().add(child);
        ticket.setSeatClass(rs.getString("seatClass"));
        ticket.setTotalMoney(Double.parseDouble(rs.getString("totalMoney")));
        ticket.setPromotionPrice(Double.parseDouble(rs
                .getString("promotionMoney")));
        ticket.setPayment(Double.valueOf(rs.getString("payment")));
        ticket.setChange(Double.valueOf(rs.getString("changes")));
        ticket.setReserveNumber(rs.getString("reserveNumber"));
        return ticket;

    }

}
