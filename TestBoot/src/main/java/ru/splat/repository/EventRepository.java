package ru.splat.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.splat.Constant;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EventRepository
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final double AVERAGE_PRICE = 100;
    private static final String STATUS = "NOT DESIGNED";
    private static final int LIMIT = ((Constant.PUNTER_COUNT2 - Constant.PUNTER_COUNT1) * 8)/10;
    private static final long LIMIT_TIME = 180000;

    public List<Integer> isExistEvent()
    {
        return jdbcTemplate.query("SELECT id FROM event LIMIT 1", new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt("id");
            }
        });
    }

    public void delete()
    {
        jdbcTemplate.update("delete from seat;delete from place; delete from event;");
    }

    public void insertEvent(Integer eventCount)
    {
        String SQL_INSERT_EVENT= "INSERT INTO event (id,name,status) VALUES (?,?, 'NOT DESIGNED')";
        jdbcTemplate.batchUpdate(SQL_INSERT_EVENT, new BatchPreparedStatementSetter()
        {
            public void setValues(PreparedStatement ps, int i) throws SQLException
            {
                ps.setInt(1, i);
                ps.setString(2, "Событиё " + i + " ");
            }
            public int getBatchSize() {
                return eventCount;
            }
        });
    }

    public void insertPlace(int placeCount)
    {
        String SQL_INSERT_MARKET= "INSERT INTO place (id,name,event_id) VALUES (?,?,?)";
        jdbcTemplate.batchUpdate(SQL_INSERT_MARKET, new BatchPreparedStatementSetter()
        {
            public void setValues(PreparedStatement ps, int i) throws SQLException
            {
                ps.setInt(1, i);
                ps.setString(2, "Стадион " + i + " ");
                ps.setInt(3, i/Constant.PLACE_COUNT);
            }

            public int getBatchSize() {
                return placeCount;
            }
        });
    }

    public void insertSeat(int outcomeCount)
    {
        String SQL_INSERT_OUTCOME = "INSERT INTO seat (id,name,price, status, place_id, lim, limit_time) VALUES (?,?,?,CAST (? AS status),?,?,?)";
        jdbcTemplate.batchUpdate(SQL_INSERT_OUTCOME, new BatchPreparedStatementSetter()
        {
            public void setValues(PreparedStatement ps, int i) throws SQLException
            {
                ps.setInt(1, i);
                ps.setString(2, "Место №" + (i % Constant.SEAT_COUNT)+ " ");
                ps.setDouble(3, AVERAGE_PRICE);
                ps.setString(4, STATUS);
                ps.setInt(5,i/Constant.SEAT_COUNT);
                ps.setInt(6,LIMIT );
                ps.setLong(7, LIMIT_TIME);
            }

            public int getBatchSize() {
                return outcomeCount;
            }
        });
    }
}
