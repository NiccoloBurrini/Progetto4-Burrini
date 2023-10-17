package com.example;

public class ClientMain {
    public static void main(String[] args) {
        Client client = new Client();
        client.connetti();
        try {
            client.comunica();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}