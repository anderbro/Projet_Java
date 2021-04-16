package fr.um3.miashs;

import java.io.IOException;
import java.net.ServerSocket;

public class Serveur {
	ServerSocket socketServeur;
	private Engine gamemaster;
	private ConnexionJoueur gestionnaireConnexion;
	private Jeu game;

	public Serveur(int port) throws IOException {
		this.socketServeur = new ServerSocket(port);
		this.nouvellePartie();
		this.gestionnaireConnexion = new ConnexionJoueur(socketServeur, gamemaster);
	}

	private void nouvellePartie() {
		Plateau game = new Plateau();
		game.remplissageGrille("src\\fr\\um3\\miashs\\resources\\carte1.txt");
		this.gamemaster = new Engine(game);
		this.gamemaster.addPotion();
		this.gamemaster.addPotion();
		this.gamemaster.addVictoire();
		this.game = new Jeu(this.gamemaster);
	}

	public void start() {
		Thread thread1 = new Thread(this.gestionnaireConnexion);
		Thread thread2 = new Thread(this.game);
		thread1.start();
		thread2.start();
	}

	public static void main(String[] args) throws IOException {
		Serveur serveur = new Serveur(1664);
		serveur.start();
	}
}
