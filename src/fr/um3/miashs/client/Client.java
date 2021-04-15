package fr.um3.miashs.client;

import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.io.*;

public class Client {
	final static int port = 1664;

	private Socket socket = null;
	private Scanner scanner = new Scanner(System.in);

	UserInput userInput = null;
	ServerInput serverInput = null;

	public Client() {
		InetAddress serveur;
		try {
			serveur = InetAddress.getLocalHost();
			socket = new Socket(serveur, port);

			this.userInput = new UserInput(socket, scanner);
			this.serverInput = new ServerInput(socket);

			System.out.println("Entrez votre nom");
			String nom = scanner.nextLine();

			// Envoi de la r�ponse au serveur
			userInput.sendToServer(nom);

		} catch (UnknownHostException e) {
			e.printStackTrace();
			cleanup();
		} catch (IOException ioex) {
			ioex.printStackTrace();
			cleanup();
		}
	}

	private void cleanup() {
		System.out.println("Cleaning up resources.");
		try {
			if (socket != null && !socket.isClosed())
				socket.close();

			scanner.close();
		} catch (IOException e) {
			System.err.println("Error killing connection. Exiting anyway.");
		}
	}

	public void run() {
		ExecutorService taskExecutor = Executors.newFixedThreadPool(2);
		ArrayList<Callable<Void>> tasks = new ArrayList<Callable<Void>>();

		tasks.add(this.userInput);
		tasks.add(this.serverInput);

		// invokeAll() returns when all tasks are complete
		try {
			taskExecutor.invokeAll(tasks);
		} catch (InterruptedException e) {
			System.out.println("An error occured in one of the threads");
		}
	}

	public static void main(String[] args) {
		Client client = new Client();
		client.run();
		client.cleanup();

		System.exit(1);
	}
}