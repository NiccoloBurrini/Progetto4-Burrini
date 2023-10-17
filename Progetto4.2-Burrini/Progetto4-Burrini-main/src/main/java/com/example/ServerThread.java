package com.example;

import java.net.*;
import java.io.*;

public class ServerThread extends Thread {
    ServerSocket server = null;
    Socket client = null;
    String nomeFile = "Progetto4-Burrini-main\\src\\main\\resources\\in\\ilfra.png"; // file da inviare al client
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;

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

        inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
        outVersoClient = new DataOutputStream(client.getOutputStream());

        for (;;) {
            String stringaRicevuta = inDalClient.readLine();
            int numeroRicevuto = Integer.parseInt(stringaRicevuta);

            try {
                if (stringaRicevuta == null) {
                    outVersoClient.writeBytes("Inserisci un numero valido" + '\n');
                } else {
                    if (numeroRicevuto == 1) {
                        nomeFile = "Progetto4-Burrini-main\\src\\main\\resources\\in\\ilfra.png";
                        // stream di scrittura sul socket
                        ObjectOutputStream writer = new ObjectOutputStream(client.getOutputStream());

                        // stream di lettura dal file
                        FileInputStream reader = new FileInputStream(nomeFile);

                        byte[] buffer = new byte[1024];
                        int lengthRead;
                        while ((lengthRead = reader.read(buffer)) > 0) {
                            writer.write(buffer, 0, lengthRead);
                            // writer.flush();
                        }
                        System.out.println("File inviato.");
                    } else if (numeroRicevuto == 2) {
                        nomeFile = "Progetto4-Burrini-main\\src\\main\\resources\\in\\ilbro.png";
                        // stream di scrittura sul socket
                        ObjectOutputStream writer = new ObjectOutputStream(client.getOutputStream());

                        // stream di lettura dal file
                        FileInputStream reader = new FileInputStream(nomeFile);

                        byte[] buffer = new byte[1024];
                        int lengthRead;
                        while ((lengthRead = reader.read(buffer)) > 0) {
                            writer.write(buffer, 0, lengthRead);
                            // writer.flush();
                        }
                        System.out.println("File inviato.");
                    } else {
                        outVersoClient.writeBytes("Arrivederci" + '\n');
                        client.close();
                        break;
                    }
                }
            } catch (Exception e) {
                outVersoClient.writeBytes("Non hai inserito un numero!" + '\n');
            }

            client.close();

        }

    }
}
