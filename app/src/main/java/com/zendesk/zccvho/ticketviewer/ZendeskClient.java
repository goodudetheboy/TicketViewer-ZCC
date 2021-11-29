package com.zendesk.zccvho.ticketviewer;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;

public class ZendeskClient {

    public String zendeskUrl = "";
    private String username = "";
    private String password = "";
    private String apiToken = "";

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

    public String getTicketJSONData() throws IOException {
        String authString = "Basic " + Base64.encodeBase64String((username + ":" + password).getBytes());
        URL url = new URL("https://zccvho.zendesk.com//api/v2/tickets.json");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        http.setRequestProperty("Authorization", "Basic " + authString);
        return http.getContentType();
    }

    public boolean authenticate() throws IOException {
        URL url = new URL(zendeskUrl);
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        http.setRequestProperty("Authorization", "Basic " + generateAuthString());
        return http.getResponseCode() == 200;
    }

    private String generateAuthString() {
        return Base64.encodeBase64String((username + ":" + password).getBytes());
    }

    public static void main(String[] args) throws IOException {
        System.out.println(new ZendeskClient().getTicketJSONData());
    }
}
