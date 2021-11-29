package com.zendesk.zccvho.ticketviewer;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;

public class ClientTest {

    public static ZendeskClient createTestClient() throws IOException {
        ZendeskClient client = new ZendeskClient();
        client.setZendeskUrl("https://zccvho.zendesk.com/");
        client.setUsername("xxx"); // change value here to test
        client.setPassword("xxx"); // change value here to test
        assertTrue("Can't connect with input username and password", client.authenticate());
        return client;
    }

    @Test
    public void userAuthTest() throws IOException {
        ZendeskClient client = new ZendeskClient();
        client.setZendeskUrl("https://zccvho.zendesk.com/");
        client.setUsername("xxx");
        client.setPassword("xxx");
        assertTrue(client.authenticate());
    }

    @Test
    public void ticketRetrievalTest() throws IOException {
        ZendeskClient client = createTestClient();
        TicketData ticketData = client.getTicketData(1, 10);
        assertEquals(1, ticketData.tickets.get(0).id);
    }
}
