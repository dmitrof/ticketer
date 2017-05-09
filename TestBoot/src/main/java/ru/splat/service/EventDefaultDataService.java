package ru.splat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.splat.repository.EventRepository;

import static ru.splat.Constant.EVENT_COUNT;
import static ru.splat.Constant.PLACE_COUNT;
import static ru.splat.Constant.SEAT_COUNT;

public class EventDefaultDataService
{
    @Autowired
    private EventRepository eventRepository;

    @Transactional
    public void insertDefaultData()
    {
        eventRepository.insertEvent(EVENT_COUNT);
        eventRepository.insertPlace(PLACE_COUNT *EVENT_COUNT);
        eventRepository.insertSeat(PLACE_COUNT * SEAT_COUNT *EVENT_COUNT);
    }

    public boolean isEmptyEvent()
    {
       return (eventRepository.isExistEvent() == null || eventRepository.isExistEvent().isEmpty());
    }

    public void deleteData()
    {
        eventRepository.delete();
    }


}
