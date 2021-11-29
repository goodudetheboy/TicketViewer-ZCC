package com.zendesk.zccvho.ticketviewer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;

public class ZendeskClient {

    private String zendeskUrl;
    private String username;
    private String password;
    private String apiToken;

    public ZendeskClient() {
        // empty
    }

    public ZendeskClient(String zendeskUrl, String username, String password, String apiToken) {
        this.zendeskUrl = zendeskUrl;
        this.username = username;
        this.password = password;
        this.apiToken = apiToken;
    }

    public String getZendeskUrl() {
        return zendeskUrl;
    }
        
    public void setZendeskUrl(String zendeskUrl) {
        this.zendeskUrl = zendeskUrl;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    /**
     * Retrieve ticket data from Zendesk API server using HTTP GET method. The
     * resulting {@link TicketData} object at the specified page and tickets per page 
     * will be returned.
     * 
     * @param page The page number
     * @param ticketPerPage The number of tickets per page
     * @return The {@link TicketData} object
     * @throws IOException If there is an error in the HTTP connection
     */
    public TicketData getTicketData(int page, int ticketPerPage) throws IOException {
        HttpURLConnection ticketConnection = connect("api/v2/tickets.json?per_page=" + ticketPerPage + "&page=" + page);
        switch (ticketConnection.getResponseCode()) {
            case 200:
            case 201:
                BufferedReader br = new BufferedReader(new InputStreamReader(ticketConnection.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line+"\n");
                }
                br.close();
                return TicketData.stringToTicketData(sb.toString());
            default:
                throw new IOException("HTTP response code: " + ticketConnection.getResponseCode());
        }
    }

    /**
     * Authenticate with Zendesk API.
     * 
     * @return true if authentication is successful, false otherwise
     * @throws IOException if there is an error connecting to Zendesk, or bad
     *      username or password
     */
    public boolean authenticate() throws IOException {
        return connect().getResponseCode() == 200 || connect().getResponseCode() == 201;
    }

    /**
     * Connect to Zendesk API, with no options.
     * 
     * @return the HttpURLConnection object whose connection is to the Zendesk API
     * @throws IOException if there is an error connecting to Zendesk
     */
    public HttpURLConnection connect() throws IOException {
        return connect("");
    }

    /**
     * Connect to Zendesk API, with options.
     * 
     * @param options the options to be appended to the URL
     * @return the HttpURLConnection object whose connection is to the Zendesk API
     * @throws IOException if there is an error connecting to Zendesk
     */
    public HttpURLConnection connect(String options) throws IOException {
        String urlString = zendeskUrl + options;
        URL url = new URL(urlString);
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestProperty("Content-Type", "application/json");
        http.setRequestProperty("Authorization", "Basic " + generateAuthString());
        return http;
    }

    /**
     * Generate the base 64 encoded string of the username and password for use
     * in the Authorization header in the cURL HTTP request.
     * 
     * @return the base 64 encoded string of the username and password
     */
    private String generateAuthString() {
        return Base64.encodeBase64String((username + ":" + password).getBytes());
    }
}
