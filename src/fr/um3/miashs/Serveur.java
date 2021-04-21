package fr.um3.miashs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Serveur implements Runnable {
	//on va avoir besoin d'une liste de thread et d'une liste de Engine, une pour chaque partie lancée
	private ServerSocket socketServeur;
	private ArrayList<Engine> gamemasters = new ArrayList<Engine>();
	private ArrayList<Thread> games = new ArrayList<Thread>();
	private Plateau plateau = new Plateau();

	public Serveur(int port) throws IOException {
		//constructeur, on rempli le plateau en meme temps 
		plateau.remplissageGrille("src\\fr\\um3\\miashs\\resources\\carte1.txt");

		this.socketServeur = new ServerSocket(port);
	}

	private Engine nouvellePartie() {
		//met en place UNE nouvelle partie, l'ajoute dans la liste
		Engine engine = new Engine(plateau);

		engine.addPotion();
		engine.addPotion();
		engine.addPotion();
		engine.addVictoire();

		this.gamemasters.add(engine);
		//fd

		return engine;
	}

	public void startPartie(Engine engine) {
		//on ajoute le thread de la partie dans la liste de threads, puis on le lance.
		Thread thread = new Thread(engine);

		this.games.add(thread);
		thread.start();
	}

	public void addPlayer(Joueur joueur) {
		// gérer l'ajout d'un player
		boolean hasJoined = false;

		for (Engine engine : this.gamemasters) {
			if (engine.howManyPlayers() < 5 && !engine.partieFinie()) {
				engine.addPlayer(joueur);
				hasJoined = true;

				synchronized (engine.running) {
					engine.running.notifyAll();
					// deverouille le wait dans engine
					//le synchronized permet de prendre un accés excxlusif sur le lock le temps de l'execution de ce dernier
					//et donc de proteger les variables temporairement. 
					//le premier thread qui obtient le lock oblige le 2e à attendre
				}
			}
		}

		if (hasJoined)
			return;

		Engine partie = this.nouvellePartie();
		partie.addPlayer(joueur);
		this.startPartie(partie);
	}

	@Override
	public void run() {
		try {

			System.out.println("Lancement du serveur");
			// 5sec avant timeout du serveur s'il y a personne dessus
			// socketServeur.setSoTimeout(5000);
			// définir une boucle sans fin
			while (true) {
				// mÃ©thode accept() qui renvoie une socket lors d'une nouvelle connexion
				Socket socketClient = this.socketServeur.accept();
				// obtenir un flux en entrée et en sortie à  partir de la socket

				this.bienvenue(socketClient);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void bienvenue(Socket socket) {
		try {
			//Entrée du joueur dans la partie a laquelle il s'est connecté.
			//il doit entrer son nom pour commencer le jeu
			System.out.println("Connexion avec le client : " + socket.getInetAddress());

			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintStream out = new PrintStream(socket.getOutputStream());

			String name;
			name = in.readLine();
			out.println("Bonjour ! " + name + " !");

			Joueur player = new Joueur(name, socket);

			this.addPlayer(player);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		Serveur serveur = new Serveur(1664);
		// serveur.start();

		Thread serverThread = new Thread(serveur);
		serverThread.start();
	}
}
