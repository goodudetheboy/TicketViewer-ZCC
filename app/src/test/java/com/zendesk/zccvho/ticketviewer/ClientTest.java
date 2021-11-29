package com.zendesk.zccvho.ticketviewer;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;

public class ClientTest {

    public void userAuthTest() throws IOException {
        ZendeskClient client = new ZendeskClient();
        client.setZendeskUrl("https://zccvho.zendesk.com/");
        client.setUsername("xxx");
        client.setPassword("xxx");
        assertTrue(client.authenticate());
    }

    @Test
    public void ticketRetrievalTest() throws IOException {
        // System.out.println(TicketRetriever.fuck());
    }
}