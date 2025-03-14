import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainServer {    
    public static void main(String[] args) {
    Server server = new Server(2000);
    System.out.println("Quanti client devono inviare messaggi?");

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int numero = 0;
    try {
        numero = Integer.parseInt(br.readLine());
    } catch (IOException e) {
        System.err.println("Errore nell'inserimento del numero");
    }

    for (int i = 0; i < numero; i++) {
        System.out.println("\nClient num." + i);
        server.attendi();
    }

    server.termina();
}
}
