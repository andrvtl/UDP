// MainClientUDP.java
public class MainClient {
    public static void main(String[] args) {
        Client client = new Client("localhost", 2000);
        client.invia("Eccomi!");
        client.ricevi();
        client.chiudi();
    }
}