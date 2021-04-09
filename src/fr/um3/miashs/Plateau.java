package fr.um3.miashs;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Plateau {

	private Tuile[][] grille;
	private int largeur, hauteur;

	// rempli la grille a  partie d'un fichier en passant par une arraylist (facilite
	// par rapport a la taille de la map si elle change)

	public void remplissageGrille(String cheminCarte) {
		ArrayList<Tuile> temp = new ArrayList<Tuile>();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(cheminCarte));
		} catch (FileNotFoundException e) {
			System.out.println("erreur " + cheminCarte + " introuvable !");
			return;
		}

		try {
			String line = reader.readLine();
			if (line != null) {
				largeur = line.length();
			}
			int numeroLigne = 0;
			while (line != null) {

				for (int i = 0; i < line.length(); i++) {
					char car = line.charAt(i);
					Tuile tuile = new Tuile(car, i, numeroLigne);
					temp.add(tuile);
				}
				line = reader.readLine();
				numeroLigne++;

			}
			hauteur = numeroLigne;
		} catch (IOException e) {
			System.out.println("Erreur de fichier");
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		grille = new Tuile[largeur][hauteur];
		for (Tuile tuile : temp) {

			grille[tuile.getX()][tuile.getY()] = tuile;
		}

	}

	public Tuile getTuile(int x, int y) {
		// permet de rï¿½cuperer une tuile aux coordonnï¿½s x et y
		if (x >= largeur || x < 0 || y >= hauteur || y < 0) {
			return null;
		}

		return grille[x][y];
	}

	public Tuile getRandomEmptyTile() {
		// permet de retourner une tuile alï¿½atoire et vide
		Tuile result;

		do {
			int x = new Random().nextInt(largeur);
			int y = new Random().nextInt(hauteur);
			result = this.getTuile(x, y);
		} while (result.getType() != TypeTuile.Vide);
		return result;
	}

	public String toString(ArrayList<Coordonnees> historique) {
		// permet d'avoir le brouillard de guerre/historique
		boolean[][] chemin = new boolean[largeur][hauteur];

		for (Coordonnees coord : historique) {

			chemin[coord.getX()][coord.getY()] = true;
		}

		String result = "";
		for (int y = 0; y < hauteur; y++) {
			for (int x = 0; x < largeur; x++) {
				if (chemin[x][y]) {
					result += grille[x][y].toChar();
				} else {
					result += '?';
				}

			}
			result += "\n";
		}
		return result;

	}

	public String toString() {
		// affichage du plateau
		String result = "";
		for (int y = 0; y < hauteur; y++) {
			for (int x = 0; x < largeur; x++) {
				result += grille[x][y].toChar();
			}
			result += "\n";
		}
		return result;
	}

}
