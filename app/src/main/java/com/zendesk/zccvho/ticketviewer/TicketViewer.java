package com.zendesk.zccvho.ticketviewer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TicketViewer {

    public static boolean quitChecker(String input) {
        return input.equals("q") || input.equals("quit");
    }

    public static void quit() {
        System.out.println("Thank you for using Zendesk Ticket Viewer. See you again!");
        System.exit(0);
    }
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader( System.in ));
        System.out.println("Welcome to Zendesk Ticket Viewer");
        System.out.println("Input your credentials to view your Zendesk tickets, or type \"quit\" at any time to quit this viewer.");

        String input = "";
        ZendeskClient client;

        while (true) {
            try {
                client = new ZendeskClient();
                // get Zendesk URL
                System.out.print("Zendesk URL: ");
                input = br.readLine();
                if (quitChecker(input)) { quit(); break; }
                client.setZendeskUrl(input);
                System.out.println("Your Zendesk URL is " + client.getZendeskUrl());

                // get email
                System.out.print("\nEmail: ");
                input = br.readLine();
                if (quitChecker(input)) { quit(); break; }
                client.setUsername(input);

                // get password
                System.out.print("\nPassword: ");
                input = br.readLine();
                if (quitChecker(input)) { quit(); break; }
                client.setPassword(input);

                // authenticating username and password
                if (!client.authenticate()) {
                    System.out.println("\nAuthentication failed, please try again.");
                } else {
                    System.out.println("\nAuthentication successful. Welcome back, " + client.getUsername() + "!");
                    break;
                }
            } catch (IOException e) {
                System.out.println("\nAuthentication failed, please try again.");
            }
        }
        int curPage = 1;
        int curTicketPerPage = 25;
        TicketData ticketData = null;
        try {
            ticketData = refresh(client, curPage, curTicketPerPage);
        } catch (IOException e1) {
            System.out.println("Problem occurs while retrieving tickets data");
        }

        while (true) {
            try { 
                System.out.println("\n\tWhat can I do for you today?");
                System.out.println("\t(Type a number to tell me what to do)");
                System.out.println("\t\t[1] View all tickets");
                System.out.println("\t\t[2] View a ticket using id");
                System.out.println("\t\t[3] Refresh ticket data");
    
                System.out.print("\n\tYour choice: ");
                input = br.readLine();
                if (quitChecker(input)) { quit(); break; }
                switch (input) {
                    case "1":
                        boolean exit = false;
                        while (!exit) {
                            checkNullTicketData(ticketData);
                            System.out.println("\n\t\tPage " + curPage);
                            System.out.println(ticketData.toString());
                            if (curPage > 1) System.out.println("\t\t[<] View previous page");
                            System.out.println("\t\t[>] View next page");
                            System.out.println("\t\t[x] Exit tickets viewer");
                            while (true) {
                                System.out.print("\n\tYour choice: ");
                                input = br.readLine();
                                if (quitChecker(input)) { quit(); break; }
                                if (input.equals("<")) {
                                        if (curPage > 1) {
                                            curPage--;
                                        }
                                        break;
                                } else if (input.equals(">")) {
                                        curPage++;
                                        break;
                                } else if (input.equals("x")) {
                                        exit = true;
                                        break;
                                } else {
                                        System.out.println("\t\tInvalid input, please try again.");
                                }
                            } 
                            ticketData = refresh(client, curPage, curTicketPerPage);
                        }
                        break;
                    case "2":
                        checkNullTicketData(ticketData);
                        System.out.print("\n\tEnter the id of the ticket you want to view: ");
                        input = br.readLine();
                        if (quitChecker(input)) { quit(); break; }
                        int id = Integer.parseInt(input);
                        while (id < 1) {
                            System.out.println("\n\t\tInvalid id, please try again.");
                            input = br.readLine();
                            if (quitChecker(input)) { quit(); break; }
                            id = Integer.parseInt(input);
                        }
                        Ticket foundTicket = ticketData.findTicketById(id);
                        if (foundTicket != null) {
                            System.out.println("\n" + foundTicket.toDetailedString());
                        } else {
                            System.out.println("\n\t\tNo ticket found with id " + id);
                        }
                        break;
                    case "3":
                        ticketData = client.getTicketData(curPage, curTicketPerPage);
                        System.out.println("\tPage " + curPage);
                        System.out.println(ticketData.toString());
                        break;
                    default:
                        System.out.println("\n\tInvalid input, please try again.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("\n\tA problem has occured!");
            }
        }
    }

    private static void checkNullTicketData(TicketData ticketData) {
        if (ticketData == null) {
            System.out.println("\n\tNo ticket data available, please try refreshing!");
        }
    }

    private static TicketData refresh(ZendeskClient client, int curPage, int curTicketPerPage) throws IOException {
        System.out.println("Retrieving tickets data...");
        TicketData ticketData = null;
        ticketData = client.getTicketData(curPage, curTicketPerPage);
        int totalRetrieved = (curTicketPerPage <= ticketData.total) ? curTicketPerPage : ticketData.total;
        System.out.println("Retrieved " + totalRetrieved + " tickets out of " + ticketData.total + " tickets.");
        return ticketData;
    }
}
