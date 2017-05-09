package ru.splat.service;


import com.google.gson.Gson;
import org.apache.log4j.Logger;
import ru.splat.Constant;
import ru.splat.messages.proxyup.ticket.TicketInfo;
import ru.splat.messages.proxyup.ticket.NewResponseClone;
import ru.splat.messages.uptm.trmetadata.ticket.TicketDetail;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class BootService
{
    private static final String URL_ADRESS = "http://172.17.51.54:8080/SpringMVC/dobet";
    private Logger LOGGER = Logger.getLogger(BootService.class);
    private int punterId;


    public BootService(int punterId)
    {
        this.punterId = punterId;
    }

    private TicketInfo generateBet()
    {

        int eventId1 = ThreadLocalRandom.current().nextInt(Constant.EVENT_COUNT - 1);
        int eventId2 = ThreadLocalRandom.current().nextInt(Constant.EVENT_COUNT - 1);

        while (eventId1 == eventId2)
        {
            eventId2 = ThreadLocalRandom.current().nextInt(Constant.EVENT_COUNT);
        }

        int outcomeId1 = ThreadLocalRandom.current().nextInt(Constant.SEAT_COUNT -1) + eventId1*Constant.PLACE_COUNT *Constant.SEAT_COUNT;
        int outcomeId2 = ThreadLocalRandom.current().nextInt(Constant.SEAT_COUNT -1) + eventId2*Constant.PLACE_COUNT *Constant.SEAT_COUNT;

        TicketDetail ticketDetail1 = new TicketDetail(null,eventId1,outcomeId1,Math.random() + 1);
        TicketDetail ticketDetail2 = new TicketDetail(null,eventId2,outcomeId2,Math.random() + 1);
        Set<TicketDetail> set = new HashSet<>(2);
        set.add(ticketDetail1);
        set.add(ticketDetail2);
        TicketInfo ticketInfo = new TicketInfo(-1L,punterId, ThreadLocalRandom.current().nextInt(Constant.AVERAGE_SUM) + 50 ,set);
        return ticketInfo;
    }


    public NewResponseClone doRequest() throws Exception
    {
        Gson g = new Gson();

        NewResponseClone newResponse;
        URL url = new URL(URL_ADRESS);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");

        TicketInfo ticketInfo = generateBet();
        String json = g.toJson(ticketInfo);
        LOGGER.info("JSON for Server: " + json);


        connection.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.writeBytes(json);
        wr.flush();
        wr.close();

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((null != (inputLine = in.readLine())) && !(Thread.currentThread().isInterrupted())) {
            response.append(inputLine);
        }
        in.close();

        newResponse = g.fromJson(response.toString(), NewResponseClone.class);


        return newResponse;
    }
}
