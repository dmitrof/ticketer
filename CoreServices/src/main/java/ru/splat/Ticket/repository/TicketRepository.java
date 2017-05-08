package ru.splat.Ticket.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;
import ru.splat.Ticket.feautures.TicketInfo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Transactional
public class TicketRepository
{
    @Autowired
    JdbcTemplate jdbcTemplate;

    public int getCurrentSequenceVal()
    {
        String SQL_SELECT_CURRVAL = "SELECT nextval('ticket_id_seq')";
        RowMapper<Integer> rm = (rs, rowNum) -> rs.getInt(1);
        return jdbcTemplate.query(SQL_SELECT_CURRVAL, rm).get(0);
    }

    public void addBet(List<TicketInfo> ticketInfoList)
    {
        if (ticketInfoList == null || ticketInfoList.isEmpty())
            return;

        String SQL_INSERT_BET = "INSERT INTO ticket (id, blob, ticket_state) VALUES (nextval('ticket_id_seq'), ?,CAST (? as state))";

        jdbcTemplate.batchUpdate(SQL_INSERT_BET, new BatchPreparedStatementSetter()
        {

            public void setValues(PreparedStatement ps, int i) throws SQLException
            {
                TicketInfo ticketInfo = ticketInfoList.get(i);
                ps.setBytes(1, ticketInfo.getBlob().toByteArray());
                ps.setString(2, "UNDEFINED");
            }
            public int getBatchSize() {
                return ticketInfoList.size();
            }
        });

    }

    public void fixBetState(List<TicketInfo> ticketInfoList, String state)
    {
        if (ticketInfoList == null || ticketInfoList.isEmpty())
            return;

        String SQL_CANCEL_BET = "UPDATE ticket SET ticket_state = CAST (? AS state) where id = ?";
        jdbcTemplate.batchUpdate(SQL_CANCEL_BET, new BatchPreparedStatementSetter()
        {

            public void setValues(PreparedStatement ps, int i) throws SQLException
            {
                TicketInfo ticketInfo = ticketInfoList.get(i);
                ps.setString(1,state);
                ps.setLong(2, ticketInfo.getId());
            }

            public int getBatchSize() {
                return ticketInfoList.size();
            }
        });
    }


}
