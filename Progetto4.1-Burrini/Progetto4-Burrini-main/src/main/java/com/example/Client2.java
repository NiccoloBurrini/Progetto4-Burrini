package com.example;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client2 {
    public static void main(String[] args) throws UnknownHostException, IOException {
        String nomeFile = "Progetto4-Burrini-main\\src\\main\\resources\\out\\ilfra-copia.png";
        Socket socket = new Socket("127.0.0.1", 6789);
        System.out.println("Client connesso con il Server");

        // stream di lettura dal socket
        ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());

        // stream di scrittura su file
        FileOutputStream writer = new FileOutputStream(nomeFile);

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
        socket.close();

    }

}