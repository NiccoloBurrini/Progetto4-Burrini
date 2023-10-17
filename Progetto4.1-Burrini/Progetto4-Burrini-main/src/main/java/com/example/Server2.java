package com.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server2 {
    public static void main(String[] args) throws IOException {
        String nomeFile = "Progetto4-Burrini-main\\src\\main\\resources\\in\\ilfra.png"; // file da inviare al client
        ServerSocket server = new ServerSocket(6789);
        System.out.println("Server in ascolto sulla porta 6789...");
        Socket socket = server.accept();
        System.out.println("Connessione accettata.");

        // stream di scrittura sul socket
        ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream());

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

        // chiusura stream, socket e server
        writer.close();
        reader.close();
        socket.close();
        server.close();
    }

}