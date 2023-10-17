package com.example;

import java.net.*;
import java.io.*;

public class ServerThread extends Thread {
    ServerSocket server = null;
    Socket client = null;
    String nomeFile = "Progetto4-Burrini-main\\src\\main\\resources\\in\\ilfra.png"; // file da inviare al client

    public ServerThread(Socket socket) {
        this.client = socket;
    }

    public void run() {
        try {
            comunica();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public void comunica() throws Exception {

        // stream di scrittura sul socket
        ObjectOutputStream writer = new ObjectOutputStream(client.getOutputStream());

        // stream di lettura dal file
        FileInputStream reader = new FileInputStream(nomeFile);

        // leggo i byte dal file e li scrivo sul socket
        byte[] buffer = new byte[1024];
        int lengthRead;
        while ((lengthRead = reader.read(buffer)) > 0) {
            writer.write(buffer, 0, lengthRead);
            // writer.flush();
        }

        System.out.println("File inviato.");

        // chiusura stream e socket
        writer.close();
        reader.close();
        client.close();

    }

}
