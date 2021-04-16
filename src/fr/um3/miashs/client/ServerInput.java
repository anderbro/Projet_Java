package fr.um3.miashs.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.Callable;

public class ServerInput implements Callable<Void> {

	//callable, sous partie de thread
    private BufferedReader serverReader;
    private Socket socket;

    public ServerInput(Socket socket) throws IOException {
        this.serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.socket = socket;
    }

    @Override
    public Void call() {
        while (!socket.isClosed()) {
            try {
                String input = serverReader.readLine();


                if (input.equals("exit") ){
                    this.socket.close();  
                    return null;
                }
                if (!input.equals("ping") )
                    System.out.println(input);
                
            } catch (IOException e) {
                System.err.println("Erreur de connexion.");
                return null;
            }
        }

        return null;
    }
}
