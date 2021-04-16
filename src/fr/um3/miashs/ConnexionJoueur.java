package fr.um3.miashs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnexionJoueur implements Runnable {

	private ServerSocket _socketServer;
	private Engine engine;

	public ConnexionJoueur(ServerSocket socket, Engine engine) {
		this._socketServer = socket;
		this.engine = engine;
	}

	@Override
	public void run() {
		try {

			System.out.println("Lancement du serveur");
			// 5sec avant timeout du serveur s'il y a personne dessus
			// socketServeur.setSoTimeout(5000);
			// définir une boucle sans fin
			while (true) {
				// méthode accept() qui renvoie une socket lors d'une nouvelle connexion
				Socket socketClient = _socketServer.accept();
				// obtenir un flux en entrée et en sortie é partir de la socket

				this.bienvenue(socketClient);

				synchronized (engine.running) {
					engine.running.notifyAll();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void bienvenue(Socket socket) {
		try {

			System.out.println("Connexion avec le client : " + socket.getInetAddress());

			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintStream out = new PrintStream(socket.getOutputStream());

			String name;
			name = in.readLine();
			out.println("Bonjour ! " + name + " !");

			Joueur player = new Joueur(name, socket);

			this.engine.addPlayer(player);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
