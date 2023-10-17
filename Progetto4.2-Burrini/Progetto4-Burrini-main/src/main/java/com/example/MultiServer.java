package com.example;

import java.net.*;

public class MultiServer {
    public void startServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(6789);
            for (;;) {
                System.out.println("Server in ascolto sulla porta 6789...");
                Socket socket = serverSocket.accept();
                System.out.println("Connessione accettata.");
                ServerThread serverThread = new ServerThread(socket);
                serverThread.start();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server !");
            System.exit(1);
        }
    }
}
