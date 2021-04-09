package fr.um3.miashs.client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class UserInput implements Callable<Void> {
    Scanner scanner;
    PrintStream writer;
    Socket socket;

    public UserInput(Socket socket, Scanner scanner) throws IOException {
        this.writer = new PrintStream(socket.getOutputStream());
        this.scanner = scanner;
        this.socket = socket;
    }

    @Override
    public Void call() {
        while (!socket.isClosed()) {
            String input = scanner.nextLine();

            if (input.equals("exit") || input.equals("quit") || input.equals("quitter") || input.equals("sortir")) {
                System.out.println("En cours de sortie...");

                // Notification du serveur
                writer.println("sortie");
                writer.flush();

                try {
                    socket.close();
                } catch (IOException e) {
                    System.err.println("Erreur, sortie de séssion.");
                }

                scanner.close();
                return null;
            }

            writer.println(input);
        }

        return null;
    }

    public void sendToServer(String message) {
        this.writer.println(message);
    }
}
