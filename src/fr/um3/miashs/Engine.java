package fr.um3.miashs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Pattern;

public class Engine implements Runnable {
	// runnable, callable = sous partie de thread, ca reviens au meme que d'extend thread juste plus opti

	private ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
	private Plateau plateau;
	private final Object lock = new Object();

	public final Object running = new Object();

	public Engine(Plateau grille) {
		plateau = grille;
	}

	@Override
	public void run() {
		try {
			// regarde si la partie est finie puis si elle contient un joueur, et la lance
			// enfin si ces conditions sont valides
			while (!this.partieFinie()) {
				if (this.hasPlayers())
					this.tourPlateau();
				else {
					synchronized (this.running) {
						// permet de marcher ca tourne pas en rond lol + synchronized pour un accés
						// exclusif sur le lock
						// pour synchro deux thread accedant aux memes données
						this.running.wait();
					}
				}
			}
			System.out.println("partie finie !!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addPlayer(Joueur player) {
		// positionnez aleatoirement le player sur une case vide
		Tuile vide = plateau.getRandomEmptyTile();

		synchronized (lock) {
			// protection de addplayer d'acces concurenciel entre deux threads
			player.setX(vide.getX());
			player.setY(vide.getY());
			vide.setType(TypeTuile.Joueur);
			joueurs.add(player);
			player.addCoordonnees(new Coordonnees(player.getX(), player.getY()));
		}
	}

	private void removePlayer(Joueur player) {
		//permet d'enlever le joueur mort du jeu
		if (!player.estDehors() && !player.isDead())
			this.plateau.getTuile(player.getX(), player.getY()).setType(TypeTuile.Vide);

		joueurs.remove(player);
	}

	private void removePlayers(Collection<Joueur> playersToDelete) {
		// Supprime les joueurs, une liste de joueur
		synchronized (lock) {
			for (Joueur player : playersToDelete) {
				this.removePlayer(player);
			}

		}

	}

	public int howManyPlayers() {
		//compte le nombre de joueurs dans la partie
		return this.joueurs.size();
	}

	public void addPotion() {
		// positionnez aleatoirement la potion sur une case vide
		Tuile vide = plateau.getRandomEmptyTile();
		vide.setType(TypeTuile.Potion);
	}

	public void addVictoire() {
		// positionnez aleatoirement la victoire sur une case vide
		Tuile vide = plateau.getRandomEmptyTile();
		vide.setType(TypeTuile.Sortie);

	}

	private void tourJoueur(int id) throws IOException {
		// ancien "main" donc tours entre les joueurs
		if (id > this.joueurs.size() || id <= 0) {
			return;
		}

		Joueur j = this.joueurs.get(id - 1);

		try {
			j.message("C'est a votre tour");
			String reponse;
			do {
				j.message("Veuillez saisir 4 déplacements valides, parmis z q s d ");
				reponse = j.ecoute();
			} while (reponse != null && !Pattern.matches("[zqsd]{4}", reponse)); // Le matches trop bien ici pour les
																					// deux conditions en meme temps

			// Le joueur a probablement des problemes de connexion
			if (reponse == null)
				return;

			for (int i = 0; i < 4; i++) {
				char direction = reponse.charAt(i);

				if (direction == 'z') {
					this.move(j, Mouvement.haut);
				} else if (direction == 'q') {
					this.move(j, Mouvement.gauche);
				} else if (direction == 's') {
					this.move(j, Mouvement.bas);
				} else if (direction == 'd') {
					this.move(j, Mouvement.droite);
				}
			}

			// On envoie à l'utilisateur le plateau à afficher
			j.message(this.plateau.toString(j.getHistorique()));

			String listeJoueurs = "Liste des joueurs : ";

			for (Joueur joueur : this.joueurs) {
				listeJoueurs += joueur.getNom() + " (" + joueur.getHp() + " PV)" + ", ";
			}

			j.message(listeJoueurs.substring(0, listeJoueurs.length() - 2));

		} catch (IOException ioex) {
			System.out.println("Erreur de connexion " + j.getNom());
		}
	}

	public void tourPlateau() throws IOException {
		// lance un tour de jeu
		for (int id = 1; id <= this.joueurs.size(); id++) {
			this.tourJoueur(id);
		}
	}

	public boolean hasPlayers() {
		// est ce qu'il y a un joueur ?
		// et gestion de la suppresion des joueurs
		ArrayList<Joueur> toDelete = new ArrayList<Joueur>();

		for (int i = 0; i < this.joueurs.size(); i++) {
			Joueur p = this.joueurs.get(i);

			if (!p.isConnected()) {
				toDelete.add(p);
				System.out.println("Joueur " + p.getNom() + " dï¿½connectï¿½.");
			}
		}

		this.removePlayers(toDelete);

		return this.joueurs.size() > 0;
	}

	public boolean partieFinie() {
		// regarde si la partie est finie
		for (Joueur j : this.joueurs) {
			if (j.estDehors())
				return true;
		}
		return false;
	}

	public void move(Joueur j, Mouvement direction) {
		/// permet les mouvements du joueur
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
		// test si il est toujours sur la carte
		if (res == null) {
			j.message("hors de la carte");
			return;
		}
		j.addCoordonnees(res);
		// detecte la case sur laquelle le joueur se déplace et fait l'interaction
		// correspondante
		switch (res.getType()) {
		case Joueur:
			j.message("tu perds un point de vie parce que y a un joueur");
			j.conflit();
			
			return;
		case Mur:
			j.message("y a un mur");
			return;
		case Piege:
			j.message("y a un piege, -1 hp");
			j.piege();
			break;
		case Potion:
			j.message("y a une potion, +1 hp");
			j.potion();
			this.addPotion();
			break;
		case Sortie:
			j.message("y a la sortie c'est win");
			j.setOutside();
			return;
		case Vide:
			j.message("y a RIEN");
			break;
		}
		
		if(j.isDead()) {
			//regarde si le joueur est mort, puis le fait mourir dans l'interraction client serveur
			j.message("t'es mort");
			this.removePlayer(j);
			j.disconnect();
		}
		// on met une tuile vide sur l'ancienne
		Tuile ancienneTuile = plateau.getTuile(j.getX(), j.getY());
		ancienneTuile.setType(TypeTuile.Vide);
		j.setX(x);
		j.setY(y);
		res.setType(TypeTuile.Joueur);

	}

}
