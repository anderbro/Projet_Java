package fr.um3.miashs;

import java.util.ArrayList;

public class Engine {

	private ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
	private Plateau plateau;

	public Engine(Plateau grille) {

		plateau = grille;

	}
	
	public void addPlayer (Joueur player) {
		// positionnez aleatoirement le player sur une case vide
		Tuile vide = plateau.getRandomEmptyTile();
		player.setX(vide.getX());
		player.setY(vide.getY());
		vide.setType(TypeTuile.Joueur);
		joueurs.add(player);
		player.addCoordonnees(new Coordonnees (player.getX(),player.getY()) );
	}

	
	 public void addPotion () {
		// positionnez aleatoirement la potion sur une case vide
	  Tuile vide = plateau.getRandomEmptyTile(); 
	  vide.setType(TypeTuile.Potion);
	  
	 }
	 
	 
		
	public void addVictoire () {
		// positionnez aleatoirement la victoire sur une case vide
		  Tuile vide = plateau.getRandomEmptyTile(); 
		  vide.setType(TypeTuile.Sortie);
		
	}
		
	
	
	
	public void move(Joueur j, Mouvement direction) {
		///permet les mouvements du joueur
		int x = 0;
		int y = 0;

		switch (direction) {

		// haut
		case haut:
			y = j.getY() - 1;
			x = j.getX();
			break;

		// bas
		case bas:
			y = j.getY() + 1;
			x = j.getX();
			break;

		// Gauche
		case gauche:
			y = j.getY();
			x = j.getX() - 1;
			break;

		// droite
		case droite:
			y = j.getY();
			x = j.getX() + 1;
			break;
		default:
			System.out.println("entrez une direction valide");

		}

		Tuile res = plateau.getTuile(x, y);
		//test si il est toujours sur la carte
		if (res == null) {
			System.out.println("hors de la carte");
			return;
		}
		j.addCoordonnees(res);
		//detecte la case sur laquelle le joueur se déplace et fait l'interaction correspondante
		switch (res.getType()) {
		
		case Joueur:
			System.out.println("tu perds un point de vie parce que y a un joueur");
			j.conflit();
			return;

		case Mur:
			System.out.println("y a un mur");

			return;

		case Piege:
			System.out.println("y a un piege, -1 hp");
			j.piege();

			break;

		case Potion:
			System.out.println("y a une popo, +1 hp");
			j.potion();
			break;

		case Sortie:
			System.out.println("y a la sortie c'est win");
			j.setOutside();
			return;

		case Vide:
			System.out.println("y a RIEN");

			break;
		}
		//on met une tuile vide sur l'ancienne
		Tuile ancienneTuile = plateau.getTuile(j.getX(), j.getY());
		ancienneTuile.setType(TypeTuile.Vide);
		j.setX(x);
		j.setY(y);
		res.setType(TypeTuile.Joueur);
		
	}

	
	
}
