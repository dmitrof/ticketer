package ru.splat.mvc.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.splat.mvc.features.EventInfo;
import ru.splat.mvc.features.MarketInfo;
import ru.splat.mvc.features.OutcomeInfo;

import java.util.List;


public class EventRepository
{

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<EventInfo> getEvents()
    {
        RowMapper<EventInfo> rm = (rs, rowNum) ->
        {
            EventInfo eventInfo = new EventInfo();
            eventInfo.setId(rs.getInt("id"));
            eventInfo.setName(rs.getString("name"));
            return eventInfo;
        };

        String SQL_SELECT_PUNTER_LIMITS = "SELECT event.id, event.name FROM event LIMIT 10";
        return  jdbcTemplate.query(SQL_SELECT_PUNTER_LIMITS, rm);
    }



    public List<MarketInfo> getMarkets(int event_id)
    {
        RowMapper<MarketInfo> rm = (rs, rowNum) ->
        {
            MarketInfo marketInfo = new MarketInfo();
            marketInfo.setId(rs.getInt("id"));
            marketInfo.setName(rs.getString("name"));
            marketInfo.setEventId(rs.getInt("event_id"));
            return marketInfo;
        };

        String SQL_SELECT_PUNTER_LIMITS = "SELECT place.id, place.name, place.event_id FROM place WHERE event_id="+event_id;
        return  jdbcTemplate.query(SQL_SELECT_PUNTER_LIMITS, rm);
    }

    public List<OutcomeInfo> getOutcomes(int market_id)
    {
        RowMapper<OutcomeInfo> rm = (rs, rowNum) ->
        {
            OutcomeInfo outcomeInfo = new OutcomeInfo();
            outcomeInfo.setId(rs.getInt("id"));
            outcomeInfo.setName(rs.getString("name"));
            outcomeInfo.setCoefficient(rs.getDouble("current_koef"));
            outcomeInfo.setMarketId(rs.getInt("place_id"));
            return outcomeInfo;
        };

        String SQL_SELECT_PUNTER_LIMITS = "SELECT seat.id, seat.name, seat.current_koef, seat.place_id FROM seat WHERE place_id="+market_id;
        return  jdbcTemplate.query(SQL_SELECT_PUNTER_LIMITS, rm);
    }

}