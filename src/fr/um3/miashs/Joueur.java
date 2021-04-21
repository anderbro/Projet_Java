package fr.um3.miashs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Joueur {

	private int x;
	private int y;
	private int hp = 3;
	private String nom;
	private boolean outside = false;
	private Socket connexion;
	private final Object lock = new Object();
	private ArrayList<Coordonnees> historique = new ArrayList<Coordonnees>();
	
	public boolean isDead() {
		//boolean qui gere la mort
		return this.hp <= 0;
	}

	public boolean estDehors() {
		//boolean qui gere si il est dehors ou non
		return this.outside;
	}



	public void message(String msg) {
		//permet d'envoyer un message au client (qui est un joueur)
		synchronized (lock) {
			PrintStream out;
			try {
				out = new PrintStream(this.connexion.getOutputStream());
				out.println(msg);
			} catch (IOException e) {
				System.err.print("Error sending message to '" + this.nom + "'");
			}
		}
	}

	public String ecoute() throws IOException {
		//permet "l'écoute" du client
		synchronized (lock) {
			BufferedReader in = new BufferedReader(new InputStreamReader(this.connexion.getInputStream()));

			String response = in.readLine();
			return response;
		}
	}

	public Joueur(String nom, Socket connexion) {
		//constructeur
		this.connexion = connexion;
		this.nom = nom;
	}

	public Socket getConnexion() {
		//permet d'avoir la connexion
		return connexion;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public void potion() {
		//gestion de la potion sur les hps
		if (hp < 5) {
			this.hp = hp + 1;
			this.message("vous avez desormais " + hp + " point(s) de vie");
		}
	}

	public void setOutside() {
		this.outside = true;

	}

	public void piege() {
		//gestion du piege sur les hps

		if (hp > 0) {
			this.hp = hp - 1;
			this.message("vous avez desormais " + hp + " point(s) de vie");
		}
	}

	public void conflit() {
		//gestion du conflit entre deux joueurs sur les hps
		if (hp > 0) {
			this.hp -= 1;
			this.message("vous avez desormais " + hp + " point(s) de vie");
		}
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		if (nom != null && nom.length() > 1)
			this.nom = nom;
		else
			System.err.println("[setNom] erreur : " + nom);
	}

	

	public void addCoordonnees(Coordonnees coord) {

		this.historique.add(coord);

	}

	public ArrayList<Coordonnees> getHistorique() {
		//historique du joueur, ce que le joueur verra a la fin
		return historique;
	}

	public void disconnect()  {
		//méthode pour déconnecter ce joueur
		try {
			this.message("exit");
			this.connexion.close();
		}
		catch(Exception ex) {
			System.out.println(this.nom + " est déconnecté");
		}
	}

	public boolean isConnected() {
		//boolean pour savoir si le joueur est connecté ou pas
		try {
			PrintWriter out = new PrintWriter(this.connexion.getOutputStream(), true);
			out.println("ping");

			if (out.checkError()) {
				try {
					this.connexion.close();
				} catch (IOException e1) {
					return false;
				}
			}
		} catch (Exception ex) {
			return false;
		}

		return true;
	}
}
