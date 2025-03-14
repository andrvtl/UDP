import java.net.*;
import java.io.*;

public class Server {
    private DatagramSocket socket;
    private int porta;
    private byte[] buffer = new byte[1024];

    public Server(int porta) {
        this.porta = porta;
        try {

    // SOCKET ISTANZIATA CON IL PARAMETRO PORTA (SOLO SERVER)
            socket = new DatagramSocket(porta);
            System.out.println("Server UDP in ascolto sulla porta " + porta);
        } catch (SocketException e) {
            System.err.println("Errore nell'apertura della socket: " + e.getMessage());
        }
    }

    public void attendi() {
        try {
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            String messaggio = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Messaggio ricevuto: " + messaggio);
            rispondi(packet);
        } catch (IOException e) {
            System.err.println("Errore nella ricezione del pacchetto: " + e.getMessage());
        }
    }

    private void rispondi(DatagramPacket packet) {
        try {
            String risposta = "Ciao client! Ti aspettavo";
            byte[] rispostaBytes = risposta.getBytes();
            DatagramPacket responsePacket = new DatagramPacket(
                rispostaBytes, rispostaBytes.length, packet.getAddress(), packet.getPort());
    // USA GETADDRESS PER RICAVARE L'INDIRIZZO DAL PACCHETTO RICEVUTO
            socket.send(responsePacket);
            System.out.println("Risposta inviata al client");
        } catch (IOException e) {
            System.err.println("Errore nell'invio della risposta: " + e.getMessage());
        }
    }

    // SOLO IL SERVER PUO CHIUDERE LA CONNESSIONE
    public void termina() {
        socket.close();
        System.out.println("Server UDP terminato");
    }
}
