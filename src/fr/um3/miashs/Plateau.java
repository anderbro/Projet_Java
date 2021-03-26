package fr.um3.miashs;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Random;
//import com.sun.tools.jdeprscan.scan.Scan;

public class Plateau {

	private Tuile[][] grille;
	private int largeur, hauteur;

	// rempli la grille à partie d'un fichier

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
		}

		grille = new Tuile[largeur][hauteur];
		for (Tuile tuile : temp) {

			grille[tuile.getX()][tuile.getY()] = tuile;
		}

	}

	public Tuile getTuile(int x, int y) {

		if (x >= largeur || x < 0 || y >= hauteur || y < 0) {
			return null;
		}

		return grille[x][y];
	}

	public void entréeTouche(Joueur j1) {
		System.out.println("quelle direction ? (haut/bas/gauche/droite");
		Scanner txt = new Scanner(System.in);

		if (txt.equals("z")) {
			System.out.println("haut");
		}

		else if (txt.equals("q")) {
			System.out.println("gauche");
		}

		else if (txt.equals("d")) {
			System.out.println("droite");
		} else if (txt.equals("s")) {
			System.out.println("bas");
		}

	}

	public Tuile getRandomEmptyTile() {
		Tuile result;

		do {
			int x = new Random().nextInt(largeur);
			int y = new Random().nextInt(hauteur);
			result = this.getTuile(x, y);
		} while (result.getType() != TypeTuile.Vide);
		return result;
	}

	public String toString() {
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
