// ClientUDP.java
import java.net.*;
import java.io.*;

public class Client {
    private DatagramSocket socket;
    private InetAddress address;
    private int porta;

    public Client(String serverAddress, int porta) {
        this.porta = porta;
        try {
    // SOCKET ISTANZIATA SENZA PORTA (SOLO CLIENT)
            socket = new DatagramSocket();
            address = InetAddress.getByName(serverAddress);
        } catch (UnknownHostException e) {
            System.err.println("Errore nella risoluzione dell'host: " + e.getMessage());
        } catch (SocketException e) {
            System.err.println("Errore nell'apertura della socket: " + e.getMessage());
        }
    }

    public void invia(String messaggio) {
        try {
            byte[] buffer = messaggio.getBytes();

            // PACKET DI OUTPUT ISTANZIATO CON INDIRIZZO E PORTA OLTRE ALL'ARRAY E LA LUNGHEZZA
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, porta);
            socket.send(packet);
        } catch (IOException e) {
            System.err.println("Errore nell'invio del pacchetto: " + e.getMessage());
        }
    }

    public void ricevi() {
        try {
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            String risposta = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Risposta del server: " + risposta);
        } catch (IOException e) {
            System.err.println("Errore nella ricezione della risposta: " + e.getMessage());
        }
    }

    public void chiudi() {
        socket.close();
        System.out.println("Connessione chiusa");
    }
}