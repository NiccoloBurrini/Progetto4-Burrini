package com.example;

import java.io.*;
import java.net.*;


public class Client {
    String nomeServer = "localhost";
    int porta = 6789;
    String nomeFile = "Progetto4-Burrini-main\\src\\main\\resources\\out\\ilfra-copia.png";
    Socket mioSocket;

    ObjectInputStream reader = null;
    FileOutputStream writer = null;

    public Socket connetti() {
        System.out.println("Client connesso con il Server");

        try {
            mioSocket = new Socket(nomeServer, porta);
            // stream di lettura dal socket
            reader = new ObjectInputStream(mioSocket.getInputStream());

            // stream di scrittura su file
            writer = new FileOutputStream(nomeFile);
        } catch (UnknownHostException e) {
            System.err.println("Host sconosciuto.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la connessione.");
            System.exit(1);
        }
        return mioSocket;

    }

    public void comunica() throws Exception {
        // leggo i byte dal socket e li scrivo sul file
        byte[] buffer = new byte[1024];
        int lengthRead;
        while ((lengthRead = reader.read(buffer)) > 0) {
            writer.write(buffer, 0, lengthRead);
            // writer.flush();
        }

        System.out.println("File ricevuto.");

        // chiusura stream e socket
        writer.close();
        reader.close();
        mioSocket.close();

    }
}
